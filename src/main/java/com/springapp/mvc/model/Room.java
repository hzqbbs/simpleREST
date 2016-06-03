package com.springapp.mvc.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.ResourceSupport;

public class Room extends ResourceSupport{

    private int rId;
    private String name;
    private String description;
    private int creatorId;

    @JsonCreator
    public Room(@JsonProperty("id")int rId, @JsonProperty("name")String name,@JsonProperty("description") String description, @JsonProperty("creatorId")int creatorId) {
        this.rId = rId;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
    }

    public Room() {
    }

    public int getrId() {
        return rId;
    }

    public void setrId(int rId) {
        this.rId = rId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }
}
