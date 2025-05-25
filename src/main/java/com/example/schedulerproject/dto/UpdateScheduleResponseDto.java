package com.example.schedulerproject.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;

@Getter
@RequiredArgsConstructor
public class UpdateScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String contents;
    private final String name;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "Asia/Seoul")
    private final Timestamp modifiedAt;
}
