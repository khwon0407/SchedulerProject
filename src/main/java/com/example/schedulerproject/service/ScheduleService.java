package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.*;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ScheduleService {
    SaveScheduleResponseDto saveSchedule(SaveScheduleRequestDto requestDto);
    List<FindScheduleResponseDto> findAllSchedule(FindScheduleRequestDto requestDto);
    FindScheduleResponseDto findOneSchedule(Long id);

    UpdateScheduleResponseDto updateSchedule(Long id, UpdateScheduleRequestDto requestDto);

    void deleteScheduleById(Long id, DeleteScheduleRequestDto requestDto);

    List<FindScheduleResponseDto> findAllScheduleWithUserId(Long userId);

    Page<FindScheduleResponseDto> findScheduleWithPage(int page, int size);
}
