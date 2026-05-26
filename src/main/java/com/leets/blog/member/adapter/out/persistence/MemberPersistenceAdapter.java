package com.leets.blog.member.adapter.out.persistence;

import com.leets.blog.member.adapter.out.persistence.entity.MemberJpaEntity;
import com.leets.blog.member.application.port.out.LoadMemberAuthPort;
import com.leets.blog.member.application.port.out.SaveMemberAuthPort;
import com.leets.blog.member.application.port.out.out.LoadMemberPort;
import com.leets.blog.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter implements LoadMemberPort, LoadMemberAuthPort, SaveMemberAuthPort {

    private final MemberRepository memberRepository;

    @Override
    public String findNicknameById(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberJpaEntity::getNickname)
                .orElse("알 수 없음"); //todo: 멤버 도메인 커스텀 예외로 변경
    }

    @Override
    public Map<Long, String> findNicknamesByIds(Set<Long> memberIds) {
        // 1. DB에서 해당 ID들을 가진 멤버들을 한 번에 조회
        // 2. Map<ID, Nickname> 형태로 변환
        return memberRepository.findAllByIdIn(memberIds).stream()
                .collect(Collectors.toMap(
                        MemberJpaEntity::getId,
                        MemberJpaEntity::getNickname
                ));
    }

    @Override
    public boolean existsById(Long memberId) {
        return memberRepository.existsById(memberId);
    }

    @Override
    public Optional<Member> findById(Long memberId) {
        return memberRepository.findById(memberId)
                .map(MemberJpaEntity::toDomain);
    }

    @Override
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email)
                .map(MemberJpaEntity::toDomain);
    }

    @Override
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }

    @Override
    public Member save(Member member) {
        MemberJpaEntity saved = memberRepository.save(MemberJpaEntity.from(member));
        return saved.toDomain();
    }
}
