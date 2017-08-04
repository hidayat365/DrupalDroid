package com.voidberg.drupaldroid;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class NodeServices {
    private ServicesClient client;

    public NodeServices(ServicesClient c) {
        client = c;
    }

    public void retrieve(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get("node/" + url, params, responseHandler);
    }

    public void create(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post("node", params, responseHandler);
    }
    public void create(JSONObject params, AsyncHttpResponseHandler responseHandler) {
        client.post("node", params, responseHandler);
    }
}