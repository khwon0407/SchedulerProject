package com.example.schedulerproject.repository;

import com.example.schedulerproject.dto.*;
import com.example.schedulerproject.entity.Schedule;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleRepository {
    SaveScheduleResponseDto saveSchedule(Schedule newSchedule);
    List<FindScheduleResponseDto> findAllSchedule(FindScheduleRequestDto requestDto);
    Schedule findOneSchedule(Long id);

    int updateSchedule(Long id, UpdateScheduleRequestDto requestDto);

    int deleteScheduleById(Long id);

    List<FindScheduleResponseDto> findAllScheduleWithUserId(Long userId);

    List<FindScheduleResponseDto> findScheduleWithPage(Pageable pageable);

    int countAll();

    SaveScheduleResponseDto saveScheduleWithUserId(Schedule newSchedule);

    List<FindScheduleResponseDto> findScheduleWithPageV2(Pageable pageable);

    int updateScheduleV2(Long id, UpdateScheduleRequestDto requestDto);


    Schedule findOneScheduleV2(Long id);

    int deleteScheduleByIdV2(Long id);
}
