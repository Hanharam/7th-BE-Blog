package com.example.blog.service;

import com.example.blog.dto.StringResponse;
import org.springframework.stereotype.Service;

@Service
public class StringService {

  public StringResponse repeat(String value) {
    return new StringResponse(value, value);
  }
}
