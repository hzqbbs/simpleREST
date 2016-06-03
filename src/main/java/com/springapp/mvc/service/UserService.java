package com.springapp.mvc.service;

import com.springapp.mvc.dao.*;
import com.springapp.mvc.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Asus on 02.06.2016.
 */
public class UserService implements UserDAO {

    private DataSource dataSource;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public synchronized User getUser(int id) {
        User user = new User();
        String SQL = "SELECT name, id FROM user WHERE id = ?";
        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1,  id);
            resultSet = statement.executeQuery();
            if(resultSet.next()){
                user.setuId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
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
        return user;
    }

    @Override
    public synchronized List<User> getUsers() {
        List<User> users = new LinkedList<User>();
        String SQL = "SELECT id, name FROM user";
        try{
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setuId(resultSet.getInt("id"));
                user.setName(resultSet.getString("name"));
                users.add(user);
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
        return users;
    }
}
