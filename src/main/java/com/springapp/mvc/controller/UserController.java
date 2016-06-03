package com.springapp.mvc.controller;

import com.springapp.mvc.dao.UserDAO;
import com.springapp.mvc.model.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

/**
 * Created by Asus on 02.06.2016.
 */
@Controller
public class UserController {

    private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    private UserDAO userDAO = (UserDAO) context.getBean("userDAO");

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<List<User>> getUsers() {
        List <User> users = userDAO.getUsers();
        for (User user : users) {
            user.add(linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
            user.add(linkTo(methodOn(UserController.class).getUser(user.getuId())).withSelfRel());
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
    }

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<User> getUser(@RequestParam (value = "id", required = true) int id) {
        User user = userDAO.getUser(id);
        user.add(linkTo(methodOn(UserController.class).getUser(id)).withSelfRel());
        user.add(linkTo(methodOn(UserController.class).getUsers()).withRel("users"));
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

}
