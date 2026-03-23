package com.leets.blog.repeat.controller;

import com.leets.blog.repeat.dto.request.RepeatRequest;
import com.leets.blog.repeat.dto.response.RepeatResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;

@Tag(name = "Repeat | 가상의 repeat 도메인", description = "1주차 연습용")
public interface RepeatControllerDocs {

    @Operation(
            summary = "입력한 문장을 두 번 반환하는 API",
            description = """
                    입력받은 문장을 두 번 반환합니다.
                    
                    - 예외 처리: value 값이 null, 빈 문자열, 공백일 때 에러 반환
                    """
    )
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "성공"),
            @ApiResponse(responseCode = "400", description = "실패")
    })
    @PostMapping("/string/repeat")
    RepeatResponse repeat(
            RepeatRequest request
    );
}
