package com.leets.blog.domain.service;

import com.leets.blog.domain.dto.res.RepeatResDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepeatService {
    public RepeatResDTO repeat(String value) {
        return RepeatResDTO.builder()
                .string_one(value)
                .string_two(value)
                .build();
    }
}
