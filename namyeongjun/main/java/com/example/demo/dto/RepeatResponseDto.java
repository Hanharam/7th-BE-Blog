package com.example.demo.dto;

import lombok.Getter;

@Getter
public class RepeatResponseDto {
    private final String string_one;
    private final String string_two;
    public RepeatResponseDto(String value_one,String value_two) {
        string_one = value_one;
        string_two = value_two;
    }
}
