package com.example.schedulerproject.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

//LV 1. 스케쥴 테이블 추가
@Getter
@AllArgsConstructor
public class Schedule {
    private Long id;
    private String title;
    private String contents;
    private String name;
    private String password;
    private Timestamp createdAt;
    private Timestamp modifiedAt;
    
    //LV 3. 연관 관계 설정
    private Long userId;

    public Schedule(Long id, String title, String contents, String name, String password, Timestamp createdAt, Timestamp modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.password = password;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

    public Schedule(String title, String contents, String name, String password) {
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.password = password;
    }

    public Schedule(String title, String contents, String name, String password, Long userId) {
        this.title = title;
        this.contents = contents;
        this.name = name;
        this.password = password;
        this.userId = userId;
    }
}
