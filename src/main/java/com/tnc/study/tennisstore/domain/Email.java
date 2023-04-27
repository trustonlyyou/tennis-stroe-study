package com.tnc.study.tennisstore.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class Email {
    private String address;

    // 인자로 들어온 address 를 Email Entity 로 반환
    public static Email of(String address) {
        return new Email(address);
    }
}