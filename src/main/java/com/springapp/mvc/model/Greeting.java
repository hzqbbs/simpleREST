package com.springapp.mvc.model;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Greeting extends ResourceSupport {

    private final String content;
    private String ne;
    @JsonCreator
    public Greeting(@JsonProperty("content") String content, @JsonProperty("ne") String ne) {
        this.content = content;
        this.ne = ne;
    }

    public String getContent() {
        return content;
    }

    public String getNe() {
        return ne;
    }
}