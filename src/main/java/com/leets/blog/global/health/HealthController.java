package com.leets.blog.global.health;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Health", description = "서버 정상 작동 확인을 위한 API")
@RestController
@RequestMapping("/api")
public class HealthController {

    /*
    * 서버 정상 작동 확인 API
    **/
    @Operation(summary = "서버 정삭 작동 확인", description = "서버가 정상적으로 작동하는지 확인합니다.")
    @GetMapping("/health")
    public ResponseEntity<String> health() {
        return ResponseEntity.ok("ok");
    }
}
