package com.example.schedulerproject.controller;

import com.example.schedulerproject.dto.SaveScheduleRequestDto;
import com.example.schedulerproject.dto.SaveScheduleResponseDto;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<SaveScheduleResponseDto> saveSchedule(@RequestBody SaveScheduleRequestDto requestDto) {
        //SaveScheduleResponseDto saved = scheduleService.saveSchedule();

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
