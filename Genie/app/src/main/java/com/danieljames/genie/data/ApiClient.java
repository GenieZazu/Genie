package com.danieljames.genie.data;


import com.danieljames.genie.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = Constants.API_BASE_URL2;
    public static Retrofit buildClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(JacksonConverterFactory.create())
                .build();
        return retrofit;
    }
}
