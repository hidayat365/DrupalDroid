package com.voidberg.drupaldroid;

import android.util.Log;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.PersistentCookieStore;
import com.loopj.android.http.RequestParams;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import org.json.JSONObject;
import java.io.*;

public class ServicesClient {
    private String url;
    private String rootUrl;
    private String token;

    private String sessionId;
    private String sessionName;

    public static AsyncHttpClient client = new AsyncHttpClient();

    public ServicesClient(String server, String base) {
        this.url = server + '/' + base + '/';
        this.rootUrl = server + '/';
        this.token = "";
        this.sessionId = "";
        this.sessionName = "";
        client.setTimeout(60000);
    }

    public String getToken() {
        return token;
    }
    public void getToken(AsyncHttpResponseHandler responseHandler) {
        this.getRoot("services/session/token", new RequestParams(), responseHandler);
    }
    public void setToken(String token) {
        this.token = token;
    }

    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getSessionName() {
        return sessionName;
    }
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    private void setHeaders() {
        // token
        if (!token.isEmpty()) {
            client.addHeader("X-CSRF-Token", token);
        } else {
            getToken(new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    String result = new String(bytes);
                    setToken(result);
                }
                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                    token = "";
                }
            });
        }
        // session
        if (!sessionId.isEmpty() && !sessionName.isEmpty()) {
            client.addHeader("Cookie", sessionName + "=" + sessionId);
        }
    }

    public void setCookieStore(PersistentCookieStore cookieStore) {
        client.setCookieStore(cookieStore);
    }

    private String getAbsoluteUrl(String relativeUrl) {
        return this.url + relativeUrl;
    }

    private String getAbsoluteRootUrl(String relativeUrl) {
      return this.rootUrl + relativeUrl;
    }

    public void getRoot(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
      client.get(getAbsoluteRootUrl(url), params, responseHandler);
    }

    public void get(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(getAbsoluteUrl(url), params, responseHandler);
    }

    public void post(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        this.setHeaders();
        client.post(getAbsoluteUrl(url), params, responseHandler);
    }

    public void post(String url, JSONObject params, AsyncHttpResponseHandler responseHandler) {
        this.setHeaders();
        StringEntity se;
        try {
            se = new StringEntity(params.toString(), HTTP.UTF_8);
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.post(null, getAbsoluteUrl(url), se, "application/json", responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void put(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        this.setHeaders();
        client.put(getAbsoluteUrl(url), params, responseHandler);
    }

    public void put(String url, JSONObject params, AsyncHttpResponseHandler responseHandler) {
        this.setHeaders();
        StringEntity se;
        try {
            se = new StringEntity(params.toString(), HTTP.UTF_8);
            se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
            client.put(null, getAbsoluteUrl(url), se, "application/json", responseHandler);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
