package com.example.schedulerproject.repository;

import com.example.schedulerproject.dto.CreateMemberRequestDto;
import com.example.schedulerproject.entity.Member;

public interface MemberRepository {

    void createMember(CreateMemberRequestDto requestDto);

    Member findMember(Long userId);
}
