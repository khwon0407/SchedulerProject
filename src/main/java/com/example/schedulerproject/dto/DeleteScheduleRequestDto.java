package com.example.schedulerproject.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class DeleteScheduleRequestDto {

    //Lv 6. 무결성 검사
    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
