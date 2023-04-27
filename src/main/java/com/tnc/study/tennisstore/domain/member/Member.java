package com.tnc.study.tennisstore.domain.member;

import com.tnc.study.tennisstore.domain.Address;
import com.tnc.study.tennisstore.domain.BooleanYNConverter;
import com.tnc.study.tennisstore.domain.Email;
import com.tnc.study.tennisstore.domain.Password;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id;

    @Embedded
    @AttributeOverride(name = "address", column = @Column(name = "email", unique = true, nullable = false))
    private Email email;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "password", nullable = false))
    private Password password;

    @Column(name = "name", nullable = false)
    private String name;

    @Embedded
    private Address address;

    @Convert(converter = BooleanYNConverter.class)
    private boolean withdrawal;

    @Enumerated(EnumType.STRING)
    private MemberGrade grade;

    public Member(Email email, Password password, String name, Address address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.grade = MemberGrade.BRONZE;
        this.withdrawal = false;
    }
}