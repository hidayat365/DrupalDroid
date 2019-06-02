package com.voidberg.drupaldroid;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class ApiServices {
    private ServicesClient client;

    public ApiServices(ServicesClient c) {
        client = c;
    }

    public void index(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get(url, params, responseHandler);
    }
    public void retrieve(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.post(url, params, responseHandler);
    }

}
