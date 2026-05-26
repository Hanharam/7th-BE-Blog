package com.leets.blog.test.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@Profile("local | dev") // local, dev 에서만 작동
@Tag(name = "000 Test | 일반 테스트", description = "개발 및 테스트 용 API")
@RestController
@Slf4j
@RequestMapping("/test")
public class TestController {

    /*
    * 서버 정상 작동 확인 API
    **/
    @Operation(summary = "서버 정삭 작동 확인", description = "서버가 정상적으로 작동하는지 확인합니다.")
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("OK");
    }

    /**
     * 로그 설정 확인 API
     */
    @Operation(summary = "로그 확인", description = "환경별 로그 테스트")
    @GetMapping("/log-test")
    public void logTest() {
        log.trace("TRACE");
        log.debug("DEBUG");
        log.info("INFO");
        log.warn("WARN");
        log.error("ERROR");
    }
}
