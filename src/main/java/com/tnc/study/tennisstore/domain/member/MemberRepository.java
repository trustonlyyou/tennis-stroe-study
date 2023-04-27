package com.tnc.study.tennisstore.domain.member;

import com.tnc.study.tennisstore.domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
    // Email이 일치하는 회원이 존재하는지 체크
    boolean existsByEmail(Email email);
}