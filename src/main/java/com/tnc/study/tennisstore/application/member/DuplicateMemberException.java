package com.tnc.study.tennisstore.application.member;

import java.io.Serial;

public class DuplicateMemberException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6664739201022484650L;

    public DuplicateMemberException() {
        super("Email 중복");
    }
}