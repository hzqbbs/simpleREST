package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

/**
 * Created by Asus on 02.06.2016.
 */


public class User extends ResourceSupport{

    private int uId;
    private String name;

    @JsonCreator
    public User(@JsonProperty("uId")int uId, @JsonProperty("name")String name) {
        this.uId = uId;
        this.name = name;
    }

    public User() {
    }

    public int getuId() {
        return uId;
    }

    public void setuId(int uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
