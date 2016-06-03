package com.springapp.mvc.controller;

import com.springapp.mvc.dao.MessageDAO;
import com.springapp.mvc.model.Greeting;
import com.springapp.mvc.model.Message;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Controller
public class MessagesController {
    private ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring-config.xml");
    private MessageDAO messageDAO = (MessageDAO) context.getBean("messageDAO");
    private static final String TEMPLATE = "Hello, %s!";

    @RequestMapping("/greeting")
    @ResponseBody
    public HttpEntity<Greeting> greeting(
            @RequestParam(value = "name", required = false, defaultValue = "World") String name) {

        Greeting greeting = new Greeting(String.format(TEMPLATE, name), "meeeeeee");
        greeting.add(linkTo(methodOn(MessagesController.class).greeting(name)).withSelfRel());

        return new ResponseEntity<Greeting>(greeting, HttpStatus.OK);
    }

    @RequestMapping("/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getMessages(){
        List<Message> messages = messageDAO.getMessages();
        for (Message message : messages){
            message.add(linkTo(methodOn(MessagesController.class).getMessage(message.getMId())).withSelfRel());
            message.add(linkTo(methodOn(MessagesController.class).getMessages()).withRel("messages"));
            message.add(linkTo(methodOn(UserController.class).getUser(message.getSenderId())).withRel("sender"));
            message.add(linkTo(methodOn(RoomController.class).getRoom(message.getRoomId())).withRel("room"));
        }
        return new ResponseEntity<List<Message>>(messages, HttpStatus.OK);
    }

    @RequestMapping("/message")
    @ResponseBody
    public ResponseEntity<Message> getMessage(@RequestParam(value = "id", required = true)int id){
        Message message = messageDAO.getMessageById(id);
        message.add(linkTo(methodOn(MessagesController.class).getMessage(id)).withSelfRel());
        message.add(linkTo(methodOn(MessagesController.class).getMessages()).withRel("messages"));
        message.add(linkTo(methodOn(UserController.class).getUser(message.getSenderId())).withRel("sender"));
        message.add(linkTo(methodOn(RoomController.class).getRoom(message.getRoomId())).withRel("room"));
        return new ResponseEntity<Message>(message, HttpStatus.OK);
    }
}