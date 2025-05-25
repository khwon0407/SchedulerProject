package com.example.schedulerproject.service;

import com.example.schedulerproject.dto.CreateMemberRequestDto;
import com.example.schedulerproject.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService{
    private final MemberRepository memberRepository;

    public void createMember(CreateMemberRequestDto requestDto) {

        memberRepository.createMember(requestDto);
    }
}
