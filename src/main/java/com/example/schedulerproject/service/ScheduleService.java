package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.SaveScheduleRequestDto;
import com.example.schedulerproject.dto.SaveScheduleResponseDto;

public interface ScheduleService {
    SaveScheduleResponseDto saveSchedule(SaveScheduleRequestDto requestDto);
}
