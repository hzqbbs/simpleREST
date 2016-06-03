package com.springapp.mvc.dao;

import com.springapp.mvc.model.Room;

import java.util.List;

/**
 * Created by Asus on 02.06.2016.
 */
public interface RoomDAO {

    public List<Room> getRooms ();

    public  Room getRoomById(int id);
}
