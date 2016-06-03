package com.springapp.mvc.controller;

import com.springapp.mvc.dao.RoomDAO;
import com.springapp.mvc.model.Room;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Asus on 02.06.2016.
 */
@Controller
public class RoomController {

    private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    private RoomDAO roomDAO = (RoomDAO) context.getBean("roomDAO");

    @RequestMapping(value = "/rooms")
    @ResponseBody
    public ResponseEntity <List<Room>> getRooms() {
        List<Room> rooms = roomDAO.getRooms();
        for (Room room : rooms) {
            room.add(linkTo(methodOn(RoomController.class).getRooms()).withRel("rooms"));
            room.add(linkTo(methodOn(RoomController.class).getRoom(room.getrId())).withSelfRel());
        }
        return new ResponseEntity<List<Room>>(rooms, HttpStatus.OK );
    }

    @RequestMapping(value = "/room")
    @ResponseBody
    public ResponseEntity<Room> getRoom(@RequestParam(value = "id", required = true)int rId) {
        Room room = roomDAO.getRoomById(rId);
        room.add(linkTo(methodOn(RoomController.class).getRoom(room.getrId())).withSelfRel());
        room.add(linkTo(methodOn(RoomController.class).getRooms()).withRel("rooms"));
        room.add(linkTo(methodOn(UserController.class).getUser(room.getCreatorId())).withRel("creator"));
        return new ResponseEntity<Room>(room, HttpStatus.OK);
    }
}
