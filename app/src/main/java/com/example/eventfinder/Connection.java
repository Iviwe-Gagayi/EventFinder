package com.example.eventfinder;
import java.util.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.Callback;
import okhttp3.Call;
import okhttp3.MediaType;
import org.json.JSONObject;
import java.io.IOException;
import org.json.JSONException;

public class Connection {
    public String email;
    public String password;
    private final OkHttpClient client = new OkHttpClient();

//3 methods for the 3 types of requests, the actual onFailure/onSuccess specifics will be in the actual code for a specific action
   public void getRequest(String url, Callback callback){
       Request request = new Request.Builder().url(url).build();
       client.newCall(request).enqueue(callback);
    }

    public void postRequest(JSONObject object,String url,Callback callback){
       RequestBody body = RequestBody.create(object.toString(),MediaType.parse("application/json"));
       Request request = new Request.Builder().url(url).post(body).build();
        client.newCall(request).enqueue(callback);
    }

    public void deleteRequest(String url,Callback callback){
    Request request = new Request.Builder().url(url).delete().build();
    client.newCall(request).enqueue(callback);
    }

}
