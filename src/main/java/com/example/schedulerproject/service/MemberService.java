package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.CreateMemberRequestDto;

public interface MemberService {
    void createMember(CreateMemberRequestDto requestDto);
}
