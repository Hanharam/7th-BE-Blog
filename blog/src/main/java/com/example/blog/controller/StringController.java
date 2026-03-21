package com.example.blog.controller;

import com.example.blog.dto.StringRequest;
import com.example.blog.dto.StringResponse;
import com.example.blog.service.StringService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class StringController {

  private final StringService stringService;

  @PostMapping("/string/repeat")
  public StringResponse repeat(@RequestBody StringRequest request) {
    return stringService.repeat(request.getValue());
  }
}
