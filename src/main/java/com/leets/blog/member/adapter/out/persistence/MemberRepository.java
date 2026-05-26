package com.leets.blog.member.adapter.out.persistence;

import com.leets.blog.member.adapter.out.persistence.entity.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {
    // SELECT * FROM members WHERE id IN (...)
    List<MemberJpaEntity> findAllByIdIn(Set<Long> ids);

    Optional<MemberJpaEntity> findByEmail(String email);

    boolean existsByEmail(String email);
}
