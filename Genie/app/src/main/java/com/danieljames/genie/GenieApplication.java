package com.danieljames.genie;

import android.app.Application;

import com.layer.sdk.LayerClient;

public class GenieApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        LayerClient.applicationCreated(this);
    }
}
