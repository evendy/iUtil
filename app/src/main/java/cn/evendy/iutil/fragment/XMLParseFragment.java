package cn.evendy.iutil.fragment;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import cn.evendy.iutil.R;
import cn.evendy.iutil.module.Person;
import cn.evendy.iutil_lib.base.BaseFragment;


/**
 * Created by evendy on 2015/5/4.
 */
public class XMLParseFragment extends BaseFragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_xmlparse, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        InputStream is = null;
        AssetManager am = getContext().getAssets();
        try {
            is = am.open("list.xml");
            pullparseXML(is);
            is = am.open("list.xml");
            saxXML(is);
            is = am.open("list.xml");
            domXML(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        TextView txt = findViewById(R.id.txt);
        txt.setText(sb.toString());
    }


    List<Person> list;
    Person person;

    private void domXML(InputStream is) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory
                    .newInstance();
            DocumentBuilder dom = factory.newDocumentBuilder();
            Document doc = dom.parse(is);
            Element root = doc.getDocumentElement();
            list = new ArrayList<Person>();
            NodeList nodes = root.getElementsByTagName("person");
            for (int i = 0; i < nodes.getLength(); i++) {
                person = new Person();
                Element item = (Element) nodes.item(i);
                person.setName(item.getAttribute("name"));
                NodeList attrs = item.getChildNodes();
                for (int j = 0; j < attrs.getLength(); j++) {
                    Node attr = attrs.item(j);
                    if (attr.getNodeName().equals("age")) {
                        int age = Integer.parseInt(attr.getTextContent());
                        person.setAge(age);
                    } else if (attr.getNodeName().equals("phone")) {
                        person.setPhone(attr.getTextContent());
                    }
                }
                list.add(person);
                person = null;
            }
            log("dom", list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                is = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void saxXML(InputStream is) {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            parser.parse(is, new DefaultHandler() {
                private String pElement = "";

                @Override
                public void startDocument() throws SAXException {
                    super.startDocument();
                    list = new ArrayList<Person>();
                }

                @Override
                public void endDocument() throws SAXException {
                    super.endDocument();
                    log("sax", list);
                }

                @Override
                public void startElement(String uri, String localName,
                                         String qName, Attributes attributes)
                        throws SAXException {
                    if (qName.equals("person")) {
                        person = new Person();
                        person.setName(attributes.getValue("name"));
                    } else {
                        pElement = qName;
                    }
                }

                @Override
                public void endElement(String uri, String localName,
                                       String qName) throws SAXException {
                    if (qName.equals("person")) {
                        list.add(person);
                        person = null;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length)
                        throws SAXException {
                    String str = new String(ch, start, length);
                    if (pElement.equals("age"))
                        person.setAge(Integer.parseInt(str));
                    else if (pElement.equals("phone"))
                        person.setPhone(str);
                    pElement = "";
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                is = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void pullparseXML(InputStream is) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = factory.newPullParser();
            parser.setInput(is, "utf-8");
            int type = parser.getEventType();// 触发第一个解析事件
            while (type != XmlPullParser.END_DOCUMENT) {
                switch (type) {
                    case XmlPullParser.START_DOCUMENT: {
                        list = new ArrayList<Person>();
                        break;
                    }
                    case XmlPullParser.START_TAG: {
                        if (parser.getName().equals("person")) {
                            person = new Person();
                            person.setName(parser.getAttributeName(0));
                        } else if (parser.getName().equals("age")) {
                            int age = Integer.parseInt(parser.nextText());
                            person.setAge(age);
                        } else if (parser.getName().equals("phone")) {
                            person.setPhone(parser.nextText());
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        if (parser.getName().equals("person")) {
                            list.add(person);
                            person = null;
                        }
                        break;
                    }
                    default:
                        break;
                }
                type = parser.next();
            }
            log("pull parser", list);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                is = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    StringBuffer sb;

    private void log(String type, List<Person> list) {
        if (sb == null) {
            sb = new StringBuffer();
        }

        sb.append("--------" + type + "---------------");
        sb.append("\n");
        sb.append("list.size...." + list.size());
        sb.append("\n");
        for (Person p : list) {
            sb.append(p.toString());
            sb.append("\n");
        }
        sb.append("-----------------------");
        sb.append("\n");
    }
}
