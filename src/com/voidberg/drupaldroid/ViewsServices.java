package com.voidberg.drupaldroid;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class ViewsServices {
    private ServicesClient client;

    public ViewsServices(ServicesClient c) {
        client = c;
    }

    public void retrieve(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get("views/" + url, params, responseHandler);
    }
}
