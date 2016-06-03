package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class Message extends ResourceSupport {

    private int id;
    private String text;
    private String sender;
    private int senderId;
    private int roomId;
    private String date;

    public Message() {
    }

    @JsonCreator
    public Message(@JsonProperty("id")int id, @JsonProperty("text")String text, @JsonProperty("sender")String sender, @JsonProperty("roomId")int roomId, @JsonProperty("date")String date) {
        this.id = id;
        this.text = text;
        this.sender = sender;
        this.roomId = roomId;
        this.date = date;
    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getMId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
