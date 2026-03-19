package com.example.demo.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class RepeatRequestDto {
    @NotBlank(message = "내용을 입력해주세요.")
    private String value;
}
