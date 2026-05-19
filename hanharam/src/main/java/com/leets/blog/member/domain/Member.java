package com.leets.blog.member.domain;

import com.leets.blog.common.enums.Role;
import com.leets.blog.member.domain.enums.Gender;
import java.time.LocalDateTime;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Member {

    private final Long id;
    private final String name;
    private final String nickname;
    private final String email;
    private final String password;
    private final Role role;
    private final Gender gender;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public static Member create(String name, String nickname, String email, String password) {
        return Member.builder()
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .role(Role.USER)
                .gender(Gender.NONE)
                .build();
    }

    public static Member reconstruct(
            Long id,
            String name,
            String nickname,
            String email,
            String password,
            Role role,
            Gender gender,
            LocalDateTime createdAt,
            LocalDateTime updatedAt
    ) {
        return Member.builder()
                .id(id)
                .name(name)
                .nickname(nickname)
                .email(email)
                .password(password)
                .role(role)
                .gender(gender)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }
}
