package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.request.LoginRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.request.SignUpRequestDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원가입을 하면 비밀번호가 암호화가 될 것이다.")
    void joinTest() {
        // given
        SignUpRequestDTO dto = SignUpRequestDTO.builder()
                .account("kdhong")
                .email("kdhong@abc.net")
                .name("홍길동")
                .password("1111")
                .build();

        // when
        boolean flag = memberService.join(dto);

        // then
        Assertions.assertTrue(flag);
    }

    @Test
    @DisplayName("id가 존재하지 않는 경우를 테스트 한다.")
    void noAccTest() {
        // given
        String account = "IU";
        String password = "ppp1234";

        // when
//        LoginResult result = memberService.authenticate(account, password);
        LoginResult result = memberService.authenticate(LoginRequestDTO
                .builder()
                .password(password)
                .build());
        // then
        Assertions.assertEquals(LoginResult.NO_ACC, result);

    }

    @Test
    @DisplayName("pw가 틀린 경우를 테스트 한다.")
    void noPwTest() {
        // given
        String account = "kdhong";
        String password = "ppp1234";

        // when
//        LoginResult result = memberService.authenticate(account, password);
        LoginResult result = memberService.authenticate(LoginRequestDTO
                .builder()
                .password(password)
                .build());

        // then
        Assertions.assertEquals(LoginResult.NO_PW, result);

    }

    @Test
    @DisplayName("로그인이 성공하는 경우를 테스트 한다.")
    void loginSuccessTest() {
        // given
        String account = "kdhong";
        String password = "1111";

        // when
//        LoginResult result = memberService.authenticate(account, password);
        LoginResult result = memberService.authenticate(LoginRequestDTO
                .builder()
                .password(password)
                .build());
        // then
        Assertions.assertEquals(LoginResult.SUCCESS, result);

    }
}