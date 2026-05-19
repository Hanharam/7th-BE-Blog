package com.leets.blog.authentication.adapter.in.web.dto.request;

import com.leets.blog.authentication.application.port.in.command.dto.SignUpCommand;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Schema(description = "회원가입 요청")
public record SignUpRequest(
        @Schema(description = "이름", example = "홍길동")
        @NotBlank(message = "이름은 필수입니다.")
        String name,

        @Schema(description = "닉네임", example = "길동이")
        @NotBlank(message = "닉네임은 필수입니다.")
        String nickname,

        @Schema(description = "로그인 이메일", example = "test@example.com")
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "이메일 형식이 올바르지 않습니다.")
        String email,

        @Schema(description = "비밀번호", example = "test1234")
        @NotBlank(message = "비밀번호는 필수입니다.")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,64}$",
                message = "비밀번호는 8자 이상 64자 이하이며 영문과 숫자를 포함해야 합니다."
        )
        String password
) {
    public SignUpCommand toCommand() {
        return new SignUpCommand(name, nickname, email, password);
    }
}
