package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.dto.request.SignUpRequestDTO;
import com.study.springstudy.springmvc.chap04.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 양식 열기
    @GetMapping("/sign-up")
    public void signUp(){

    }

    // 아이디, 이메일 중복검사 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody   // Get 방식의 Fetch 요청을 받았으면 JSON 파싱을 위해서 @ResponseBody 붙여야 함
    public ResponseEntity<?> check(@RequestParam String type,
                                   @RequestParam String keyword){
        boolean flag = memberService.checkIdentifier(type, keyword);

        System.out.println("[dbg] 가입자 존재여부 결과 flag = " + flag);
        return ResponseEntity.ok().body(flag);

    }

    @PostMapping("/sign-up")
    public String signUp(@Validated SignUpRequestDTO dto){
        System.out.println("[dbg] Controller:signUp() dto = " + dto.toString());
        boolean flag = memberService.join(dto);

        return flag ? "redirect:/members/sign-in" : "redirect:/members/sign-up";

    }

}

