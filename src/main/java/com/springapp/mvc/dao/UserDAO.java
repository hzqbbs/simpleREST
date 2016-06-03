package com.springapp.mvc.dao;

import com.springapp.mvc.model.User;

import java.util.List;

/**
 * Created by Asus on 02.06.2016.
 */
public interface UserDAO {

    public User getUser(int id);

    public List<User> getUsers();
}
