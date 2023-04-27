package com.tnc.study.tennisstore.application.member;

import com.tnc.study.tennisstore.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FindMemberService {
    private final MemberRepository memberRepository;

    /**
     * 회원 정보 조회
     * @return members
     */
    public List<FindMemberResponse> findMembers() {
        return memberRepository.findAll().stream()
                .map(member -> new FindMemberResponse(
                        member.getId(),
                        member.getEmail().getAddress(),
                        member.getName(),
                        member.getAddress().getAddress1(),
                        member.getAddress().getAddress2(),
                        member.getAddress().getZipcode(),
                        member.getGrade()
                ))
                .toList();
    }
}