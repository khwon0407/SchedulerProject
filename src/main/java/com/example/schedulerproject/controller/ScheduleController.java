package com.example.schedulerproject.controller;

import com.example.schedulerproject.dto.*;
import com.example.schedulerproject.entity.Schedule;
import com.example.schedulerproject.service.ScheduleService;
import com.mysql.cj.x.protobuf.MysqlxCrud;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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
            @RequestBody @Valid SaveScheduleRequestDto requestDto
    ) {
        SaveScheduleResponseDto saved = scheduleService.saveSchedule(requestDto);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FindScheduleResponseDto>> findAllSchedule(
            @RequestBody @Valid FindScheduleRequestDto requestDto
            ){
        List<FindScheduleResponseDto> listAllSchedule = scheduleService.findAllSchedule(requestDto);

        return new ResponseEntity<>(listAllSchedule, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FindScheduleResponseDto> findOneSchedule(
            @PathVariable Long id
    ) {
        FindScheduleResponseDto findSchedule = scheduleService.findOneSchedule(id);

        return new ResponseEntity<>(findSchedule, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<FindScheduleResponseDto>> findScheduleWithPage(
            @RequestParam int page,
            @RequestParam int size
    ) {
        Page<FindScheduleResponseDto> pagingSchedule = scheduleService.findScheduleWithPage(page, size);

        return new ResponseEntity<>(pagingSchedule, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateScheduleResponseDto> updateSchedule(
            @PathVariable Long id,
            @RequestBody @Valid UpdateScheduleRequestDto requestDto
    ) {
        UpdateScheduleResponseDto updatedSchedule = scheduleService.updateSchedule(id, requestDto);

        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long id,
            @RequestBody @Valid DeleteScheduleRequestDto requestDto
    ) {
        scheduleService.deleteScheduleById(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK); //Void는 상태코드만 반환
    }
}
