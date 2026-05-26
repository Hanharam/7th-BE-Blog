package com.leets.blog.authentication.adapter.in.web;

import com.leets.blog.authentication.adapter.in.web.dto.request.KakaoLoginRequest;
import com.leets.blog.authentication.adapter.in.web.dto.request.LoginRequest;
import com.leets.blog.authentication.adapter.in.web.dto.request.RefreshTokenRequest;
import com.leets.blog.authentication.adapter.in.web.dto.request.SignUpRequest;
import com.leets.blog.authentication.adapter.in.web.dto.response.AuthResponse;
import com.leets.blog.authentication.application.port.in.command.AuthenticateMemberUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
@Tag(name = "Authentication | 인증", description = "회원가입 및 로그인 API")
@SecurityRequirements
public class AuthenticationController {

    private final AuthenticateMemberUseCase authenticateMemberUseCase;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "이메일과 비밀번호로 회원가입을 진행합니다.")
    public AuthResponse signUp(@Valid @RequestBody SignUpRequest request) {
        return AuthResponse.from(authenticateMemberUseCase.signUp(request.toCommand()));
    }

    @PostMapping("/login")
    @Operation(summary = "로그인", description = "이메일과 비밀번호로 로그인하고 JWT를 발급합니다.")
    public AuthResponse login(@Valid @RequestBody LoginRequest request) {
        return AuthResponse.from(authenticateMemberUseCase.login(request.toCommand()));
    }

    @PostMapping("/login/kakao")
    @Operation(summary = "카카오 로그인", description = "카카오 authorization code를 받아 로그인 또는 자동 회원가입을 진행합니다.")
    public AuthResponse kakaoLogin(@Valid @RequestBody KakaoLoginRequest request) {
        return AuthResponse.from(authenticateMemberUseCase.loginWithKakao(request.toCommand()));
    }

    @PostMapping("/refresh")
    @Operation(summary = "토큰 재발급", description = "리프레시 토큰으로 access token과 refresh token을 모두 재발급합니다.")
    public AuthResponse refresh(@Valid @RequestBody RefreshTokenRequest request) {
        return AuthResponse.from(authenticateMemberUseCase.refreshTokens(request.toCommand()));
    }
}
