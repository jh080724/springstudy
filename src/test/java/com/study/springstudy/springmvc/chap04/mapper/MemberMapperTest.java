package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.entity.Member;
import jakarta.validation.constraints.AssertTrue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberMapperTest {
    @Autowired
    MemberMapper memberMapper;

    @Test
    @DisplayName("회원 가입에 성공해야 한다.")
    void saveTest() {
        // given
        Member m = Member.builder()
                .account("abcd1234")
                .password("111")
                .name("홍길동")
                .email("abc@abc.net")
                .build();
        // when
        boolean flag = memberMapper.save(m);

        // then
        Assertions.assertTrue(flag);
    }

    @Test
    @DisplayName("계정명이 abcd1234인 회원은 중복확인 결과가 true이어야 한다.")
    void existsAccountTest() {
        // given
        String acc="abcd1234";

        // when
        boolean flag = memberMapper.existsById("account", acc);

        // then
        Assertions.assertTrue(flag);
    }

    @Test
    @DisplayName("이메일이 abc@abc.net인 회원은 중복확인 결과가 true이어야 한다.")
    void existsEmailTest() {
        // given
        String email = "abc@abc.net";
        // when
        boolean flag = memberMapper.existsById("email", email);

        // then
        Assertions.assertTrue(flag);

    }
}
