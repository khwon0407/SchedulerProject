package com.example.schedulerproject.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

//Lv 3. 테이블 분리
//Lv 6. 무결성 감사
@Getter
@RequiredArgsConstructor
public class SaveScheduleRequestDtoV2 {
    @NotBlank(message = "할 일은 필수입니다.")
    @Size(max = 200, message = "할 일은 200자 이내여야 합니다.")
    private final String title;
    @NotBlank(message = "할 일은 필수입니다.")
    @Size(max = 200, message = "할 일은 200자 이내여야 합니다.")
    private final String contents;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String password;
}
