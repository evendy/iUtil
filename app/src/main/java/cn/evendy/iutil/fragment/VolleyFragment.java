package cn.evendy.iutil.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.custom.GsonRequest;
import com.android.volley.custom.XmlRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.xmlpull.v1.XmlPullParser;
import cn.evendy.iutil.R;
import cn.evendy.iutil.module.Weather;
import cn.evendy.iutil_lib.base.BaseFragment;

/**
 * @author: evendy
 * @time: 2015/5/14 14:50
 */
public class VolleyFragment extends BaseFragment {
    private TextView volley_text;
    private TextView volley_gson;
    private TextView volley_xml;
    private ImageView volley_img;
    private RequestQueue requestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        requestQueue = Volley.newRequestQueue(getContext());
        return inflater.inflate(R.layout.fragment_volley, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        volley_text = findViewById(R.id.volley_string);
        volley_gson = findViewById(R.id.volley_gson);
        volley_xml = findViewById(R.id.volley_xml);
        volley_img = findViewById(R.id.volley_img);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, "https://www.baidu.com", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                volley_text.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volley_text.setText(error.getMessage());
            }
        });
        requestQueue.add(stringRequest);


        GsonRequest<Weather> gsonRequest = new GsonRequest<Weather>("http://www.weather.com.cn/adat/sk/101010100.html", Weather.class, new Response.Listener<Weather>() {
            @Override
            public void onResponse(Weather weather) {
                volley_gson.setText(weather.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volley_gson.setText(error.getMessage());
            }
        });
        requestQueue.add(gsonRequest);

        XmlRequest xmlRequest = new XmlRequest("http://flash.weather.com.cn/wmaps/xml/china.xml", new Response.Listener<XmlPullParser>() {
            @Override
            public void onResponse(XmlPullParser xmlPullParser) {
                try {
                    StringBuffer sb = new StringBuffer();
                    boolean isMid = false;
                    int eventType = xmlPullParser.getEventType();
                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_TAG: {
                                String node = xmlPullParser.getName();
                                if ("city".equalsIgnoreCase(node)) {
                                    if (isMid) {
                                        sb.append(",");
                                    }
                                    sb.append(xmlPullParser.getAttributeValue(0));
                                    isMid = true;
                                }
                                break;
                            }
                        }
                        eventType = xmlPullParser.next();
                    }
                    volley_xml.setText(sb);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                volley_xml.setText(error.getMessage());
            }
        });
        requestQueue.add(xmlRequest);
    }
}
