package com.hello.myfirstwebsite.dto;

import lombok.Data;

@Data
public class PostSearchCond {

    private String title;
    private String name;

    public PostSearchCond() {
    }

    public PostSearchCond(String title, String name) {
        this.title = title;
        this.name = name;
    }
}
