package com.tnc.study.tennisstore.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable // JPA Entity 안의 Column 을 하나의 객체로써 사용 (값 타입)
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@EqualsAndHashCode
public class Address {
    private String address1;
    private String address2;
    private String zipcode;
}
