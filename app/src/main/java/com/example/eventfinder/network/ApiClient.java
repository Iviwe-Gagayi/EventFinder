package com.example.eventfinder.network;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.json.JSONObject;

public class ApiClient {

    //storing base url so we can just reuse it instead of entering the entire thing everytime
    private static final String BASE_URL = "https://wmc.ms.wits.ac.za/students/s2993801/api/";

    // same thing. just storing it once bc we'll use it a ton
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    //instead of creating a new instance of the client everytime we make a call we can just reuse a single
    //instance/client for all our requests
    private static ApiClient instance;
    private final OkHttpClient client;

    // private constructor so no other class can randomly create a new client
    private ApiClient() {
        client = new OkHttpClient();
    }

    //If we have a client return it otherwise create it then return it
    //synchronoise just makes sure that if multiple functions try and create a client
    //they have to wait. The "instance" variable just acts as a way to access the "ApiClient" object.
    public static synchronized ApiClient getInstance() {
        if (instance == null) {
            instance = new ApiClient();
        }
        return instance;
    }

    //You bascially did these perfectly. Just cleaned them up a bit
    public void getRequest(String endpoint, Callback callback) {
        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .get()
                .build();
        client.newCall(request).enqueue(callback);
    }

    public void postRequest(String endpoint, JSONObject jsonBody, Callback callback) {
        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .post(body)
                .build();
        client.newCall(request).enqueue(callback);
    }

    //changed  this function to accept a body since one or two endpoints need one. That's it.
    public void deleteRequest(String endpoint, JSONObject jsonBody, Callback callback) {
        RequestBody body = RequestBody.create(jsonBody.toString(), JSON);
        Request request = new Request.Builder()
                .url(BASE_URL + endpoint)
                .delete(body)
                .build();
        client.newCall(request).enqueue(callback);
    }
}