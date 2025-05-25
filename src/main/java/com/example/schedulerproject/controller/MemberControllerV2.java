package com.example.schedulerproject.controller;

import com.example.schedulerproject.dto.CreateMemberRequestDto;
import com.example.schedulerproject.dto.CreateMemberResponseDto;
import com.example.schedulerproject.service.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Lv 3. 테이블 추가 및 분리
@RestController
@RequiredArgsConstructor
@RequestMapping("/memberV2")
public class MemberControllerV2 {

    private final MemberService memberService;
    @PostMapping
    public ResponseEntity<CreateMemberResponseDto> createMember(@RequestBody @Valid CreateMemberRequestDto requestDto) {
        CreateMemberResponseDto newMember = memberService.createMember(requestDto);
        //return ResponseEntity.status(HttpStatus.CREATED).body("회원 생성 완료");
        return new ResponseEntity<>(newMember, HttpStatus.CREATED);
    }
}
