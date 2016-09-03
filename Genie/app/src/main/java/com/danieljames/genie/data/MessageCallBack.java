package com.danieljames.genie.data;


public interface MessageCallBack<T> {

    void onSuccess(T messageModel);
    void onError(String errorMessage);
}
