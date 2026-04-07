package com.leets.blog.member.adapter.out.persistence;

import com.leets.blog.member.domain.MemberJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface MemberRepository extends JpaRepository<MemberJpaEntity, Long> {
    // SELECT * FROM members WHERE id IN (...)
    List<MemberJpaEntity> findAllByIdIn(Set<Long> ids);
}
