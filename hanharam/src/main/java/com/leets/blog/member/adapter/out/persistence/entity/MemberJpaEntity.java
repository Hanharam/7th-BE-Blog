package com.leets.blog.member.adapter.out.persistence.entity;

import com.leets.blog.common.BaseEntity;
import com.leets.blog.common.enums.Role;
import com.leets.blog.member.domain.Member;
import com.leets.blog.member.domain.enums.Gender;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "member")
public class MemberJpaEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender = Gender.NONE;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role = Role.USER;

    @Builder
    private MemberJpaEntity(String name, String nickname, String email, String password, Role role, Gender gender) {
        this.name = name;
        this.nickname = nickname;
        this.email = email;
        this.password = password;
        this.role = role;
        this.gender = gender;
    }

    public static MemberJpaEntity from(Member member) {
        return MemberJpaEntity.builder()
                .name(member.getName())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .password(member.getPassword())
                .role(member.getRole())
                .gender(member.getGender())
                .build();
    }

    public Member toDomain() {
        return Member.reconstruct(
                this.id,
                this.name,
                this.nickname,
                this.email,
                this.password,
                this.role,
                this.gender,
                this.getCreatedAt(),
                this.getUpdatedAt()
        );
    }
}
