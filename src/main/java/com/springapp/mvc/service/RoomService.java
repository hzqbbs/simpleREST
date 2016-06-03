package com.springapp.mvc.service;

import com.springapp.mvc.dao.RoomDAO;
import com.springapp.mvc.model.Room;

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
public class RoomService implements RoomDAO {

    private DataSource dataSource;
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Room> getRooms() {
        List<Room> rooms = new LinkedList<Room>();
        String SQL = "SELECT * FROM rooms";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                Room room = new Room();
                room.setrId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                room.setDescription(resultSet.getString("description"));
                room.setCreatorId(resultSet.getInt("creatorID"));
                rooms.add(room);
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
        return rooms;
    }

    @Override
    public synchronized Room getRoomById(int id) {
        Room room = new Room();
        String SQL = "SELECT * FROM rooms WHERE id=?";
        try {
            connection = dataSource.getConnection();
            statement = connection.prepareStatement(SQL);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            if (resultSet.next()){
                room.setrId(resultSet.getInt("id"));
                room.setName(resultSet.getString("name"));
                room.setDescription(resultSet.getString("description"));
                room.setCreatorId(resultSet.getInt("creatorID"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return room;
    }
}
