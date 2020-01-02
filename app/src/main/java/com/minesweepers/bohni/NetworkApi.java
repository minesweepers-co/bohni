package com.minesweepers.bohni;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkApi {

    private static final String BASE_URL = "https://us-central1-bohni-67cbf.cloudfunctions.net/";
    private static final String HEALTHCHECK_URL = "https://us-central1-bohni-67cbf.cloudfunctions.net/helloWorld";

    interface NetworkApiListener {
        void onSuccess();

        void onFailure(Exception e);
    }

    private OkHttpClient httpClient;

    private static NetworkApi instance = null;

    private NetworkApi() {
        httpClient = new OkHttpClient.Builder().build();
    }

    public static NetworkApi getInstance() {
        if (instance == null) {
            synchronized (NetworkApi.class) {
                if (instance == null) {
                    instance = new NetworkApi();
                }
            }
        }
        return instance;
    }

    public void healthCheck(final NetworkApiListener listener) {
        Request request = new Request.Builder().get().url(HEALTHCHECK_URL).build();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                listener.onFailure(e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) {
                if (response.isSuccessful()) {
                    listener.onSuccess();
                } else {
                    listener.onFailure( new Exception("call failed without http exception"));
                }
            }
        });
    }
}
