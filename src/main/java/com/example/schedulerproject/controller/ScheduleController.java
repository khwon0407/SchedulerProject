package com.example.schedulerproject.controller;

import com.example.schedulerproject.dto.SaveScheduleRequestDto;
import com.example.schedulerproject.dto.SaveScheduleResponseDto;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping
    public ResponseEntity<SaveScheduleResponseDto> saveSchedule(
            @RequestBody SaveScheduleRequestDto requestDto
    ) {
        SaveScheduleResponseDto saved = scheduleService.saveSchedule(requestDto);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }
    /*
    @GetMapping
    public List<FindScheduleResponseDto> findAllSchedule(){
        List<FindScheduleResponseDto> listAllSchedule = scheduleService.
    }
    */



}
