package com.leets.blog.repeat.controller;

import com.leets.blog.repeat.dto.request.RepeatRequest;
import com.leets.blog.repeat.dto.response.RepeatResponse;
import com.leets.blog.repeat.service.RepeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class RepeatController implements RepeatControllerDocs{

    private final RepeatService repeatService;

    @Override
    @PostMapping("/string/repeat")
    public RepeatResponse repeat(
            @Valid @RequestBody RepeatRequest request
    ) {
        return repeatService.repeat(request.value());
    }
}
