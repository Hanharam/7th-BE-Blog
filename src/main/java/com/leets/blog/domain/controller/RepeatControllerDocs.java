package com.leets.blog.domain.controller;

import com.leets.blog.domain.dto.req.RepeatReqDTO;
import com.leets.blog.domain.dto.res.RepeatResDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

public interface RepeatControllerDocs {

    @Operation(
            summary = "입력한 문장을 두 번 반환하는 API",
            description = """
                    입력받은 문장을 두 번 반환합니다.
                    
                    - 예외 처리: value 값이 null, 빈 문자열, 공백일 때 에러 반환
                    """
    )
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/string/repeat")
    HttpEntity<RepeatResDTO> repeat(
            @Valid @RequestBody RepeatReqDTO request
    );
}
