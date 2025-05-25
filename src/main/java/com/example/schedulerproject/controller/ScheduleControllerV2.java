package com.example.schedulerproject.controller;

import com.example.schedulerproject.dto.FindScheduleRequestDto;
import com.example.schedulerproject.dto.FindScheduleResponseDto;
import com.example.schedulerproject.entity.Member;
import com.example.schedulerproject.service.MemberService;
import com.example.schedulerproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/scheduleV2")
public class ScheduleControllerV2 {

    private final ScheduleService scheduleService;
    private final MemberService memberService;
    @GetMapping
    public ResponseEntity<List<FindScheduleResponseDto>> findAllSchedule(
            @RequestParam Long userId
    ) {
        List<FindScheduleResponseDto> listAllSchedule = scheduleService.findAllScheduleWithUserId(userId);

        return new ResponseEntity<>(listAllSchedule, HttpStatus.OK);
    }
}
