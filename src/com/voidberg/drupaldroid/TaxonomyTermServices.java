package com.voidberg.drupaldroid;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONObject;

public class TaxonomyTermServices {
    private ServicesClient client;

    public TaxonomyTermServices(ServicesClient c) {
        client = c;
    }

    public void retrieve(RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get("taxonomy_term/", params, responseHandler);
    }
    public void retrieve(String url, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get("taxonomy_term/" + url, params, responseHandler);
    }

}
