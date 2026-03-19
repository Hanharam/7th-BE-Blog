package com.example.demo.dto;

import lombok.Getter;

@Getter
public class RepeatResponseDto {
    private final String string_one;
    private final String string_two;
    public RepeatResponseDto(String value) {
        string_one = value;
        string_two = value;
    }
}
