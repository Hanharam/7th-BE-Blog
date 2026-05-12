package com.leets.blog.authentication.application.service;

import com.leets.blog.authentication.application.port.in.command.AuthenticateMemberUseCase;
import com.leets.blog.authentication.application.port.in.command.dto.LoginCommand;
import com.leets.blog.authentication.application.port.in.command.dto.LoginResult;
import com.leets.blog.authentication.application.port.in.command.dto.SignUpCommand;
import com.leets.blog.authentication.domain.exception.AuthenticationDomainException;
import com.leets.blog.authentication.domain.exception.AuthenticationErrorCode;
import com.leets.blog.global.security.JwtTokenProvider;
import com.leets.blog.member.application.port.out.LoadMemberAuthPort;
import com.leets.blog.member.application.port.out.SaveMemberAuthPort;
import com.leets.blog.member.domain.Member;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AuthenticationService implements AuthenticateMemberUseCase {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");
    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[A-Za-z])(?=.*\\d).{8,64}$");

    private final LoadMemberAuthPort loadMemberAuthPort;
    private final SaveMemberAuthPort saveMemberAuthPort;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public LoginResult signUp(SignUpCommand command) {
        String email = normalizeEmail(command.email());
        validateSignUpInput(command.name(), command.nickname(), email, command.password());

        if (loadMemberAuthPort.existsByEmail(email)) {
            throw new AuthenticationDomainException(AuthenticationErrorCode.DUPLICATE_EMAIL);
        }

        Member savedMember = saveMemberAuthPort.save(
                Member.create(
                        command.name().trim(),
                        command.nickname().trim(),
                        email,
                        passwordEncoder.encode(command.password())
                )
        );

        return issueTokens(savedMember);
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResult login(LoginCommand command) {
        String email = normalizeEmail(command.email());
        validateLoginInput(email, command.password());

        Member member = loadMemberAuthPort.findByEmail(email)
                .orElseThrow(() -> new AuthenticationDomainException(AuthenticationErrorCode.INVALID_CREDENTIALS));

        if (!passwordEncoder.matches(command.password(), member.getPassword())) {
            throw new AuthenticationDomainException(AuthenticationErrorCode.INVALID_CREDENTIALS);
        }

        return issueTokens(member);
    }

    private LoginResult issueTokens(Member member) {
        List<String> roles = List.of(member.getRole().name());

        return LoginResult.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .nickname(member.getNickname())
                .accessToken(jwtTokenProvider.createAccessToken(member.getId(), roles))
                .refreshToken(jwtTokenProvider.createRefreshToken(member.getId()))
                .build();
    }

    private void validateSignUpInput(String name, String nickname, String email, String password) {
        if (name.isBlank() || nickname.isBlank()) {
            throw new AuthenticationDomainException(AuthenticationErrorCode.INVALID_PROFILE_INPUT);
        }
        validateEmail(email);
        validatePassword(password);
    }

    private void validateLoginInput(String email, String password) {
        validateEmail(email);
        if (password == null || password.isBlank()) {
            throw new AuthenticationDomainException(AuthenticationErrorCode.INVALID_CREDENTIALS);
        }
    }

    private void validateEmail(String email) {
        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new AuthenticationDomainException(AuthenticationErrorCode.INVALID_EMAIL_FORMAT);
        }
    }

    private void validatePassword(String password) {
        if (!PASSWORD_PATTERN.matcher(password).matches()) {
            throw new AuthenticationDomainException(AuthenticationErrorCode.INVALID_PASSWORD_FORMAT);
        }
    }

    private String normalizeEmail(String email) {
        return email.trim().toLowerCase(Locale.ROOT);
    }
}
