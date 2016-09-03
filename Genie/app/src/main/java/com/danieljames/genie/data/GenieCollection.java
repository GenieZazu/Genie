package com.danieljames.genie.data;


import android.util.Log;

import com.danieljames.genie.domain.model.MessageModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class GenieCollection implements Collection{
    private Retrofit client;
    public static GenieCollection Instance;
    private String TAG = "genie";

    public static synchronized GenieCollection getInstance(){
        if (Instance == null){
            Instance = new GenieCollection();
        }
        return Instance;
    }

    public GenieCollection() {
        client = ApiClient.buildClient();
    }

    @Override
    public void sendMessage(final MessageModel messageModel, MessageCallBack callback) {

        getData(new Supplier<Call<MessageModel>>() {
            @Override
            public Call<MessageModel> get() {
                return client.create(Endpoint.class).sendMessage(messageModel);
            }
        }, callback);

    }

    private <T> void getData(Supplier<Call<T>> callSupplier, final MessageCallBack callback) {
        Call<T> call = callSupplier.get();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                if (response.body() != null) {
                    callback.onSuccess(response.body());
                    Log.e("TAG", "onResponse: " + response.body());
                }

            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage() );
                System.out.print(t.getMessage());
            }
        });
    }
}
