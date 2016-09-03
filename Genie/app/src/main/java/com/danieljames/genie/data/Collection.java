package com.danieljames.genie.data;


import com.danieljames.genie.domain.model.MessageModel;

public interface Collection {

    void sendMessage(MessageModel messageModel, MessageCallBack callback);
}
