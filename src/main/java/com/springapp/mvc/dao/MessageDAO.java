package com.springapp.mvc.dao;

import com.springapp.mvc.model.Message;

import java.util.List;

/**
 * Created by Asus on 02.06.2016.
 */
public interface MessageDAO {

    public List<Message> getMessages();

    public Message getMessageById(int id);

}
