package com.example.schedulerproject.repository;

import com.example.schedulerproject.dto.FindScheduleRequestDto;
import com.example.schedulerproject.dto.FindScheduleResponseDto;
import com.example.schedulerproject.dto.SaveScheduleResponseDto;
import com.example.schedulerproject.dto.UpdateScheduleRequestDto;
import com.example.schedulerproject.entity.Schedule;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ScheduleRepository {
    SaveScheduleResponseDto saveSchedule(Schedule newSchedule);
    List<FindScheduleResponseDto> findAllSchedule(FindScheduleRequestDto requestDto);
    Schedule findOneSchedule(Long id);

    int updateSchedule(Long id, UpdateScheduleRequestDto requestDto);

    int deleteMemoById(Long id);

    List<FindScheduleResponseDto> findAllScheduleWithUserId(Long userId);

    List<FindScheduleResponseDto> findScheduleWithPage(Pageable pageable);

    int countAll();

    SaveScheduleResponseDto saveScheduleWithUserId(Schedule newSchedule);

    List<FindScheduleResponseDto> findScheduleWithPageV2(Pageable pageable);
}
