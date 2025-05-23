package com.example.schedulerproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

//Level 1. 일정 생성
@Getter
@RequiredArgsConstructor
public class SaveScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String name;
    private final String password;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final Timestamp createdAt;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final Timestamp modifiedAt;
}
