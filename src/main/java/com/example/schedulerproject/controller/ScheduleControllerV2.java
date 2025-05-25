package com.example.schedulerproject.controller;

import com.example.schedulerproject.dto.*;
import com.example.schedulerproject.entity.Member;
import com.example.schedulerproject.service.MemberService;
import com.example.schedulerproject.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

//Lv 3. 테이블 추가 및 분리
@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduleV2")
public class ScheduleControllerV2 {

    private final ScheduleService scheduleService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<SaveScheduleResponseDto> saveScheduleWithUserId(
            @RequestBody @Valid SaveScheduleRequestDtoV2 requestDto,
            @RequestParam Long userId
    ) {
        SaveScheduleResponseDto saved = scheduleService.saveScheduleWithUserId(userId, requestDto);

        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<FindScheduleResponseDto>> findAllSchedule(
            @RequestParam Long userId
    ) {
        List<FindScheduleResponseDto> listAllSchedule = scheduleService.findAllScheduleWithUserId(userId);

        return new ResponseEntity<>(listAllSchedule, HttpStatus.OK);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<FindScheduleResponseDto>> findScheduleWithPageV2(
            @RequestParam int page,
            @RequestParam int size
    ) {
        Page<FindScheduleResponseDto> pagingSchedule = scheduleService.findScheduleWithPageV2(page, size);

        return new ResponseEntity<>(pagingSchedule, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UpdateScheduleResponseDto> updateScheduleV2(
            @PathVariable Long id,
            @RequestBody @Valid UpdateScheduleRequestDto requestDto
    ) {
        UpdateScheduleResponseDto updatedSchedule = scheduleService.updateScheduleV2(id, requestDto);

        return new ResponseEntity<>(updatedSchedule, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteScheduleV2(
            @PathVariable Long id,
            @RequestBody @Valid DeleteScheduleRequestDto requestDto
    ) {
        scheduleService.deleteScheduleByIdV2(id, requestDto);
        return new ResponseEntity<>(HttpStatus.OK); //Void는 상태코드만 반환
    }
}
