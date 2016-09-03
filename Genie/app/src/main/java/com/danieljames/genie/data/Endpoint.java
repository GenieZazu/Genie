package com.danieljames.genie.data;


import com.danieljames.genie.domain.model.MessageModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Endpoint {

    @POST("web_mobile")
    Call<MessageModel> sendMessage(@Body MessageModel messageModel);
}
