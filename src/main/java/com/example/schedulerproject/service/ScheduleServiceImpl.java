package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.SaveScheduleRequestDto;
import com.example.schedulerproject.dto.SaveScheduleResponseDto;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService{

    private final ScheduleRepository scheduleRepository;
    @Override
    public SaveScheduleResponseDto saveSchedule(SaveScheduleRequestDto requestDto) {
        Schedule newSchedule = new Schedule(
                requestDto.getTitle(),
                requestDto.getContents(),
                requestDto.getName(),
                requestDto.getPassword()
        );

        return scheduleRepository.saveSchedule(newSchedule);
    }
}
