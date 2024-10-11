package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.dto.request.LoginRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.request.SignUpRequestDTO;
import com.study.springstudy.springmvc.chap04.service.LoginResult;
import com.study.springstudy.springmvc.chap04.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입 양식 열기
    @GetMapping("/sign-up")
    public void signUp() {

    }

    // 아이디, 이메일 중복검사 비동기 요청 처리
    @GetMapping("/check")
    @ResponseBody   // Get 방식의 Fetch 요청을 받았으면 JSON 파싱을 위해서 @ResponseBody 붙여야 함
    public ResponseEntity<?> check(@RequestParam String type,
                                   @RequestParam String keyword) {
        boolean flag = memberService.checkIdentifier(type, keyword);

        System.out.println("[dbg] 가입자 존재여부 결과 flag = " + flag);
        return ResponseEntity.ok().body(flag);

    }

    @PostMapping("/sign-up")
    public String signUp(@Validated SignUpRequestDTO dto) {
        System.out.println("[dbg] Controller:signUp() dto = " + dto.toString());
        boolean flag = memberService.join(dto);

        return flag ? "redirect:/members/sign-in" : "redirect:/members/sign-up";

    }

    // 로그인 화면 요청
    @GetMapping("sign-in")
    public void signIn(){

    }

    // 로그인 검증 요청
    @PostMapping("/sign-in")
    public String signIn(LoginRequestDTO dto,
                         // Model에 담긴 데이터는 리다이렉트 시 jsp로 전달되지 못함.
                         // 리다이렉트는 응답이 나갔다가 재요청이 들어오는데, 그 과정에서
                         // 응답이 나가는 순간 모델이 소멸함. 그래서, RedirectAttributes를 사용함.
                         RedirectAttributes ra){

        LoginResult result = memberService.authenticate(dto);

        // redirect에서 데이터를 일회성으로 사용하는 메서드
        ra.addFlashAttribute("result", result);

        if(result == LoginResult.SUCCESS){
            //로그인 성공
            return "redirect:/board/list";
        }

        return "redirect:/members/sign-in"; // 로그인 실패 시
    }

}

