package com.danieljames.genie.domain.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

public class MessageModel {

    @JsonIgnore
    private int id;
    @JsonIgnore
    @JsonProperty("message")
    private String message;
    @JsonIgnore
    @JsonProperty("token")
    private String token;
    @JsonIgnore
    private String sender;
    @JsonProperty("text")
    private String text;

    @JsonProperty("image")
    private String imageUrl;

    public MessageModel() {
    }

    public MessageModel(int id, String message, String token) {
        this.id = id;
        this.message = message;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
