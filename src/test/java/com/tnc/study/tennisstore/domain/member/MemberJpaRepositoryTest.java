package com.tnc.study.tennisstore.domain.member;

import com.tnc.study.tennisstore.domain.Address;
import com.tnc.study.tennisstore.domain.Email;
import com.tnc.study.tennisstore.domain.Password;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@Rollback
class MemberJpaRepositoryTest {

//    @Autowired
//    MemberJpaRepository repository;

    @Autowired
    MemberRepository repository;

    @Test
    @DisplayName("회원 테스트")
    void testMember() throws Exception {
        // given
        Email email = Email.of("kjpmj@tnctec.co.kr");
        Address address = new Address("서울시 관악구", "은천로", "05123");
        Password password = Password.of("1234");
        Member member = new Member(email, password, "김명진", address);

        // when
        Member savedMember = repository.save(member);
//        Member findMember = repository.findById(savedMember.getId());
        Member findMember = repository.findById(savedMember.getId()).get();

        // then
        assertThat(findMember.getEmail()).isEqualTo(email);
        assertThat(findMember.getName()).isEqualTo(member.getName());
        assertThat(findMember.getAddress()).isEqualTo(address);
        assertThat(findMember.getGrade()).isEqualTo(MemberGrade.BRONZE);
        assertThat(findMember.isWithdrawal()).isFalse();
        assertThat(findMember).isEqualTo(member); // JPA 엔티티 동일성 보장
    }

}