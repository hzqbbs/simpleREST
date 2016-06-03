package com.springapp.mvc.service;

import com.springapp.mvc.dao.MessageDAO;
import com.springapp.mvc.model.Message;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 02.06.2016.
 */
public class MessageService implements MessageDAO {

    private DataSource dataSource;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public synchronized List<Message> getMessages() {
        List<Message> messages = new ArrayList<>();
        String SQL = "SELECT messages.id, messages.senderID, messages.text,user.name, messages.roomID, messages.senderID, messages.date FROM new_test.messages join user on messages.senderID=user.id;";
        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Message message = new Message();
                message.setId(resultSet.getInt("id"));
                message.setText(resultSet.getString("text"));
                message.setSender(resultSet.getString("name"));
                message.setRoomId(resultSet.getInt("roomID"));
                message.setDate(resultSet.getString("date"));
                message.setSenderId(resultSet.getInt("senderID"));
                messages.add(message);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return messages;
    }

    @Override
    public synchronized Message getMessageById(int id) {
        Message message = new Message();
        String SQL = "SELECT messages.id, messages.senderID, messages.text,user.name, messages.roomID, messages.senderID, messages.date FROM new_test.messages join user on messages.senderID=user.id where messages.id=?;";
        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                message.setId(resultSet.getInt("id"));
                message.setText(resultSet.getString("text"));
                message.setSender(resultSet.getString("name"));
                message.setRoomId(resultSet.getInt("roomID"));
                message.setDate(resultSet.getString("date"));
                message.setSenderId(resultSet.getInt("senderID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                connection.close();
                statement.close();
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return message;
    }
}
