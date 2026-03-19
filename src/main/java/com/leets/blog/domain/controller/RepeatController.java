package com.leets.blog.domain.controller;

import com.leets.blog.domain.dto.req.RepeatReqDTO;
import com.leets.blog.domain.dto.res.RepeatResDTO;
import com.leets.blog.domain.service.RepeatService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<RepeatResDTO> repeat(
            @Valid @RequestBody RepeatReqDTO request
    ) {
        return ResponseEntity.ok(repeatService.repeat(request.value()));
    }
}
