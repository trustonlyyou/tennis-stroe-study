package com.tnc.study.tennisstore.application.member;

import com.tnc.study.tennisstore.domain.Address;
import com.tnc.study.tennisstore.domain.Email;
import com.tnc.study.tennisstore.domain.Password;
import com.tnc.study.tennisstore.domain.member.Member;
import com.tnc.study.tennisstore.domain.member.MemberGrade;
import com.tnc.study.tennisstore.domain.member.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
@Rollback
class MemberServiceTest {

    @Autowired
    CreateMemberService createMemberService;

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    FindMemberService findMemberService;

    @BeforeEach
    void setup() {
        Email email = Email.of("kjpmj@tnctec.co.kr");
        Password password = Password.of("1234");
        String name = "김명진";
        Address address = new Address("서울시 관악구 은천로 37길 21", "501호", "05123");
        Member member = new Member(email, password, name, address);
        memberRepository.save(member);
    }

    @Test
    @DisplayName("회원 등록 서비스")
    void testCreateMember() throws Exception {
        // given
        String email = "kjpmj@naver.com";
        String password = "1234";
        String name = "김명진";
        String address1 = "서울시 관악구 은천로 37길 21";
        String address2 = "501호";
        String zipcode = "05123";

        CreateMemberRequest createMemberRequest = new CreateMemberRequest(
                email,
                password,
                name,
                address1,
                address2,
                zipcode
        );

        // when
        Long memberId = createMemberService.signUp(createMemberRequest);
        Member findMember = memberRepository.findById(memberId).get();

        // then
        assertThat(findMember.getEmail().getAddress()).isEqualTo(email);
        assertThat(findMember.getName()).isEqualTo(name);
        assertThat(findMember.getPassword().getValue()).isEqualTo(password);
        assertThat(findMember.getAddress().getAddress1()).isEqualTo(address1);
        assertThat(findMember.getAddress().getAddress2()).isEqualTo(address2);
        assertThat(findMember.getAddress().getZipcode()).isEqualTo(zipcode);
        assertThat(findMember.isWithdrawal()).isFalse();
        assertThat(findMember.getGrade()).isEqualTo(MemberGrade.BRONZE);
    }

    @Test
    @DisplayName("회원 이메일 중복 체크")
    void testCheckDuplicateEmail() throws Exception {
        // given
        String email = "kjpmj@tnctec.co.kr";
        String password = "1234";
        String name = "김명진";
        String address1 = "서울시 관악구 은천르 37길 21";
        String address2 = "501호";
        String zipcode = "05123";

        CreateMemberRequest createMemberRequest = new CreateMemberRequest(
                email,
                password,
                name,
                address1,
                address2,
                zipcode
        );

        // when
        DuplicateMemberException exception = assertThrows(DuplicateMemberException.class,
                () -> createMemberService.signUp(createMemberRequest));

        // then
        assertThat(exception.getMessage()).isEqualTo("Email 중복");
    }


    @Test
    @DisplayName("회원 조회 서비스")
    void testFindMembers() throws Exception {
        // given
        Email email = Email.of("kjpmj@kakao.com");
        Password password = Password.of("1234");
        String name = "MJ";
        Address address = new Address("서울시 관악구 은천르 37길 21", "501호", "05123");
        Member member = new Member(email, password, name, address);
        memberRepository.save(member);

        // when
        List<FindMemberResponse> members = findMemberService.findMembers();

        // then
        assertThat(members.size()).isEqualTo(2);
        assertThat(members).extracting("email").containsExactly("kjpmj@tnctec.co.kr", "kjpmj@kakao.com");
        assertThat(members).extracting("name").containsExactly("김명진", "MJ");
        assertThat(members).extracting("grade").containsExactly(MemberGrade.BRONZE, MemberGrade.BRONZE);
    }

}