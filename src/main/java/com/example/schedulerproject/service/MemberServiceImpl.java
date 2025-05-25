package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.CreateMemberRequestDto;
import com.example.schedulerproject.dto.CreateMemberResponseDto;
import com.example.schedulerproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

//Lv 3. 테이블 분리
@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public CreateMemberResponseDto createMember(CreateMemberRequestDto requestDto) {

        return memberRepository.createMember(requestDto);
    }
}
