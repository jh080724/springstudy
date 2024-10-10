package com.study.springstudy.springmvc.chap04.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.security.PrivateKey;

@Getter @ToString
@AllArgsConstructor

public enum Auth {

    COMMON("일반회원", 1),
    ADMIN("관리자회원", 2);

    private String desc;
    private int authNumber;

}
