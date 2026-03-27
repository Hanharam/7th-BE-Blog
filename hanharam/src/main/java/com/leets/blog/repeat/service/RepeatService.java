package com.leets.blog.repeat.service;

import com.leets.blog.repeat.dto.response.RepeatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RepeatService {

    public RepeatResponse repeat(String value) {
        return RepeatResponse.from(value);
    }
}
