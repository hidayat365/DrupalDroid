package com.voidberg.drupaldroid;

import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONException;
import org.json.JSONObject;

public class UserServices {
    private ServicesClient client;

    public UserServices(ServicesClient c) {
        client = c;
    }

    public void login(String username, String password, AsyncHttpResponseHandler responseHandler) {
        JSONObject params = new JSONObject();
        try {
            params.put("username", username);
            params.put("password", password);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        client.post("user/login", params, responseHandler);
    }

    public void logout(AsyncHttpResponseHandler responseHandler) {
        client.post("user/logout", new JSONObject(), responseHandler);
    }

    public void retrieve(String uid, AsyncHttpResponseHandler responseHandler) {
        client.get("user/"+uid, new RequestParams(), responseHandler);
    }
    public void retrieve(String uid, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.get("user/"+uid, params, responseHandler);
    }

    public void update(String uid, AsyncHttpResponseHandler responseHandler) {
        client.put("user/"+uid, new RequestParams(), responseHandler);
    }
    public void update(String uid, RequestParams params, AsyncHttpResponseHandler responseHandler) {
        client.put("user/"+uid, params, responseHandler);
    }
    public void update(String uid, JSONObject params, AsyncHttpResponseHandler responseHandler) {
        client.put("user/"+uid, params, responseHandler);
    }
}
