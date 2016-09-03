package com.danieljames.genie.presentation.ui.activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.danieljames.genie.Constants;
import com.danieljames.genie.R;
import com.danieljames.genie.data.GenericRequest;
import com.danieljames.genie.data.GenieCollection;
import com.danieljames.genie.data.MessageCallBack;
import com.danieljames.genie.data.utils.TypeFace;
import com.danieljames.genie.data.utils.TypeFaceManager;
import com.danieljames.genie.domain.model.MessageModel;
import com.danieljames.genie.presentation.ui.adapter.ChatListAdapter;

import java.util.ArrayList;
import java.util.List;

public class ChatActivity extends AppCompatActivity {

    private GenieCollection collection;
    private EditText messageBox;
    private RecyclerView messageList;
    private ChatListAdapter adapter;
    private List<MessageModel> chatList;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeComponents();
        initializeRecyClerView();
        setTypeFace();
    }

    private void setTypeFace() {
        TypeFaceManager.setTypeface(this, messageBox, TypeFace.RalewaySemiBold);
    }

    private void initializeRecyClerView() {
        MessageModel messageModel = new MessageModel();
        messageModel.setText("Ahoy there");
        messageModel.setSender("Genie");
        chatList = new ArrayList<>();
        chatList.add(messageModel);
        messageList = (RecyclerView) findViewById(R.id.chat_recycler_view);
        adapter = new ChatListAdapter(chatList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        layoutManager.setStackFromEnd(true);
//        layoutManager.setSmoothScrollbarEnabled(false);
        layoutManager.scrollToPosition(chatList.size());
        messageList.setLayoutManager(layoutManager);
        messageList.setAdapter(adapter);
    }

    private void initializeComponents() {
        fab = (FloatingActionButton) findViewById(R.id.fab_btn);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage();
                fab.setEnabled(false);
                messageBox.setText("");
                Log.e("TAG", "onSuccess: " );
            }
        });
        messageBox = (EditText) findViewById(R.id.message_box);
    }


    private void sendMessage() {
        final String messageBody = messageBox.getText().toString().trim();
        MessageModel messageModel = new MessageModel();
        messageModel.setMessage(messageBody);
        messageModel.setSender("Me");
        chatList.add(messageModel);
        adapter.notifyItemInserted(chatList.size()-1);
        GenieCollection collection = new GenieCollection();
        collection.sendMessage(messageModel, new MessageCallBack() {
            @Override
            public void onSuccess(Object messageModel) {
                MessageModel mess = (MessageModel) messageModel;
                mess.setSender("Genie");
                chatList.add(mess);
                adapter.notifyItemInserted(chatList.size() - 1);
                fab.setEnabled(true);
                Log.e("TAG", "onSuccess: " + mess.getText() );
            }

            @Override
            public void onError(String errorMessage) {
                MessageModel messageModel = new MessageModel();
                String error = "oOOps!, my bad i think you have a poor internet connection";
                messageModel.setMessage(error);
                messageModel.setSender("Genie");
                chatList.add(messageModel);
                adapter.notifyItemInserted(chatList.size()-1);
                fab.setEnabled(true);
            }
        });
    }

    private void send() {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constants.API_BASE_URL;
        String messageBody = messageBox.getText().toString().trim();
        MessageModel message = new MessageModel();
        message.setMessage(messageBody);

        GenericRequest<MessageModel> request = new GenericRequest<>(Request.Method.POST, url, MessageModel.class, message, new Response.Listener<MessageModel>() {
            @Override
            public void onResponse(MessageModel response) {
                Log.e("TAG", "onResponse: " + response.getMessage() );
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        }, null);

//        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.e("Response is: ", " "+ response.toString());
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("TAG", "onErrorResponse: " );
//            }
//        });
//
       queue.add(request);
    }
}
