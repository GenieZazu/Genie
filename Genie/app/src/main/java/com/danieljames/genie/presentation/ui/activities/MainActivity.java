package com.danieljames.genie.presentation.ui.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.danieljames.genie.R;
import com.layer.atlas.AtlasConversationsRecyclerView;
import com.layer.sdk.LayerClient;

public class MainActivity extends AppCompatActivity {


    private AtlasConversationsRecyclerView conversationsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
