package com.leets.blog.authentication.application.port.in.command.dto;

import java.util.Objects;

public record SignUpCommand(
        String name,
        String nickname,
        String email,
        String password
) {
    public SignUpCommand {
        Objects.requireNonNull(name, "이름은 필수입니다.");
        Objects.requireNonNull(nickname, "닉네임은 필수입니다.");
        Objects.requireNonNull(email, "이메일은 필수입니다.");
        Objects.requireNonNull(password, "비밀번호는 필수입니다.");
    }
}
