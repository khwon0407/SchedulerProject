package com.example.schedulerproject.dto;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CreateMemberRequestDto {
    private String name;

    @Email(message = "유효한 이메일 형식이 아닙니다.")
    private String email;
}
