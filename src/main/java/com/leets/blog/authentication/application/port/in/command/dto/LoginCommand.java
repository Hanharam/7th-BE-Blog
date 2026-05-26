package com.leets.blog.authentication.application.port.in.command.dto;

import java.util.Objects;

public record LoginCommand(
        String email,
        String password
) {
    public LoginCommand {
        Objects.requireNonNull(email, "이메일은 필수입니다.");
        Objects.requireNonNull(password, "비밀번호는 필수입니다.");
    }
}
