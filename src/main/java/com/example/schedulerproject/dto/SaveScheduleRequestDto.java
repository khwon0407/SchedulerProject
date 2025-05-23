package com.example.schedulerproject.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Date;

//Level 1. 일정 생성
@Getter
@RequiredArgsConstructor
public class SaveScheduleRequestDto {
    private final String title;
    private final String contents;
    private final String name;
    private final String password;
    //private final Date createdAt;
    //private final Date modifiedAt;
}
