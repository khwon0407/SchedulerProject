package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.CreateMemberRequestDto;
import com.example.schedulerproject.dto.CreateMemberResponseDto;

public interface MemberService {
    CreateMemberResponseDto createMember(CreateMemberRequestDto requestDto);
}
