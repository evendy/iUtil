package com.android.volley.custom;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;

/**
 * @author: evendy
 * @time: 2015/5/14 15:33
 */
public class XmlRequest extends Request<XmlPullParser> {
    private Response.Listener listener;

    public XmlRequest(int method, String url, Response.Listener listener, Response.ErrorListener errorListener) {
        super(method, url, errorListener);
        this.listener = listener;
    }

    public XmlRequest(String url, Response.Listener listener, Response.ErrorListener errorListener) {
        this(Method.GET, url, listener, errorListener);
    }

    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse response) {
        try {
            String xmlStr = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlStr));
            return Response.success(xmlPullParser,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (Exception e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(XmlPullParser response) {
        listener.onResponse(response);
    }
}
