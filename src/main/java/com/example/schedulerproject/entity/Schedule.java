package com.example.schedulerproject.entity;

import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Schedule {
    private Long id;
    private String title;
    private String contents;
    private String name;
    private String password;
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public Schedule(String title, String contents, String name, String password) {
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.password = password;
    }
}
