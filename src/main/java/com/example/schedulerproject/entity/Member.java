package com.example.schedulerproject.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.List;

//Lv 3. 멤버 테이블 추가
@Getter
@RequiredArgsConstructor
public class Member {
    private final Long id;
    private final String name;
    private final String email;
    private final Timestamp createdAt;
    private final Timestamp modifiedAt;
}
