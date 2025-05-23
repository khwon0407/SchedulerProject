package com.example.schedulerproject.repository;

import com.example.schedulerproject.dto.SaveScheduleResponseDto;
import com.example.schedulerproject.entity.Schedule;

public interface ScheduleRepository {
    SaveScheduleResponseDto saveSchedule(Schedule newSchedule);
}
