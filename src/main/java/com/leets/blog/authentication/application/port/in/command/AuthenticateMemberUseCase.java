package com.leets.blog.authentication.application.port.in.command;

import com.leets.blog.authentication.application.port.in.command.dto.LoginCommand;
import com.leets.blog.authentication.application.port.in.command.dto.LoginResult;
import com.leets.blog.authentication.application.port.in.command.dto.KakaoLoginCommand;
import com.leets.blog.authentication.application.port.in.command.dto.RefreshTokenCommand;
import com.leets.blog.authentication.application.port.in.command.dto.SignUpCommand;

public interface AuthenticateMemberUseCase {
    LoginResult signUp(SignUpCommand command);

    LoginResult login(LoginCommand command);

    LoginResult loginWithKakao(KakaoLoginCommand command);

    LoginResult refreshTokens(RefreshTokenCommand command);
}
