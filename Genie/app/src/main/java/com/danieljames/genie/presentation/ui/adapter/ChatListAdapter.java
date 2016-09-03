package com.danieljames.genie.presentation.ui.adapter;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.danieljames.genie.R;
import com.danieljames.genie.data.utils.TypeFace;
import com.danieljames.genie.data.utils.TypeFaceManager;
import com.danieljames.genie.domain.model.MessageModel;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatListViewHolder> {

    private List<MessageModel> messageModelList;
    private Context context;

    public ChatListAdapter(List<MessageModel> messageModelList) {
        this.messageModelList = messageModelList;
    }

    @Override
    public ChatListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.chat_items, parent, false);
        return new ChatListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ChatListViewHolder holder, int position) {
        MessageModel messageModel = messageModelList.get(position);
        if (messageModel.getMessage() != null){
            holder.message.setBackgroundColor(context.getResources().getColor(R.color.me_color));
            holder.message.setText(messageModel.getSender() + ": " + messageModel.getMessage());
        }
        else{
            holder.message.setBackgroundColor(context.getResources().getColor(R.color.genie_color));
            holder.message.setText(messageModel.getSender() + ": " + messageModel.getText());
            if (messageModel.getImageUrl() != null){
                Log.e("Image", "onBindViewHolder: " + messageModel.getImageUrl() );
                loadImage(holder.imageView, messageModel.getImageUrl());
            }
        }

    }

    private void loadImage(ImageView imageView, String imageUrl){
        if (imageUrl != null){

            Picasso.with(context).load("http://i.imgur.com/DvpvklR.png").into(imageView);
        }
    }

    @Override
    public int getItemCount() {
        return messageModelList.size();
    }

    public class ChatListViewHolder extends RecyclerView.ViewHolder{
        TextView message;
        ImageView imageView;

        public ChatListViewHolder(View itemView) {
            super(itemView);
            message = (TextView) itemView.findViewById(R.id.message);
            imageView = (ImageView) itemView.findViewById(R.id.image);
            TypeFaceManager.setTypeface(context, message, TypeFace.RalewaySemiBold);
        }
    }
}
