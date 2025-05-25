package com.example.schedulerproject.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDate;

//Lv 1. 조회에 필요한 Dto
@Getter
@RequiredArgsConstructor
public class FindScheduleRequestDto {

    private final String name;
    private final LocalDate modifiedAt;
}
