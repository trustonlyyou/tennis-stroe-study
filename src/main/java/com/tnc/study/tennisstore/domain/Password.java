package com.tnc.study.tennisstore.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Password {
    private String value;

    // 인자로 들어온 password 를 Password Entity 로 반환
    public static Password of(String password) {
        return new Password(password);
    }
}