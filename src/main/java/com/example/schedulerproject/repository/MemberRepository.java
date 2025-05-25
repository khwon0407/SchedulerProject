package com.example.schedulerproject.repository;

import com.example.schedulerproject.dto.CreateMemberRequestDto;
import com.example.schedulerproject.dto.CreateMemberResponseDto;
import com.example.schedulerproject.entity.Member;

//Lv 3. 테이블 분리
public interface MemberRepository {

    CreateMemberResponseDto createMember(CreateMemberRequestDto requestDto);

    Member findMember(Long userId);
}
