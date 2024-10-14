package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.request.AutoLoginDTO;
import com.study.springstudy.springmvc.chap04.dto.request.LoginRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.request.SignUpRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.response.LoginUserResponseDTO;
import com.study.springstudy.springmvc.chap04.entity.Member;
import com.study.springstudy.springmvc.chap04.mapper.MemberMapper;
import com.study.springstudy.springmvc.util.LoginUtils;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.study.springstudy.springmvc.chap04.service.LoginResult.*;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberMapper memberMapper;
    private final PasswordEncoder encoder;

    // 회원 가입 처리 서비스
    public boolean join(SignUpRequestDTO dto) {
        return memberMapper.save(dto.toEntity(encoder));
    }

    //    public LoginResult authenticate(String account, String password){
    public LoginResult authenticate(LoginRequestDTO dto,
                                    HttpSession session,
                                    HttpServletResponse response) {

        // 회원가입 여부 확인
        Member foundMember = memberMapper.findOne(dto.getAccount());

        if (foundMember == null) {
            return NO_ACC;
        }

        // 비밀번호 일치 검사
        if (!encoder.matches(dto.getPassword(), foundMember.getPassword())) {
            return NO_PW;
        }

        // 자동 로그인 처리
        if (dto.getAutoLogin() == null) {
            System.out.println("[dbg]MemberService: authenticate: dto.getAutoLogin() return null !!!");
        }

        if (dto.getAutoLogin()) {
            // 1. 자동로그인 쿠키 생성 -> 쿠키안에는 중복되지 않는 값을 저장
            // UUID, 브라우서 세션 아이디를 이용.
            Cookie autoCookie = new Cookie("auto", session.getId());

            //2. 쿠키설정 -사용경로, 수명...
            int limitTime = 60 * 60 * 24 * 7; // 자동로그인 유지시간
            autoCookie.setPath("/");
            autoCookie.setMaxAge(limitTime);

            //3 쿠키를 클라이언트에게 전송하기 위해 응답객체에 태우기
            response.addCookie(autoCookie);

            //4. DB에도 쿠키에 관련된 값들 (랜덤한 세션 아이디, 자동로그인 만료 시간)을 갱신
            AutoLoginDTO autoDto = AutoLoginDTO.builder()
                    .sessionId(session.getId())
                    .limitTime(LocalDateTime.now().plusSeconds(limitTime))
                    .account(dto.getAccount())
                    .build();
            memberMapper.saveAutoLogin(autoDto);
        }

        return SUCCESS;

    }


    public boolean checkIdentifier(String type, String keyword) {
        return memberMapper.existsById(type, keyword);
    }

    public void maintainLoginState(HttpSession session, String account) {
        // 세션은 서버에서만 유일하게 보관되는 데이터로서
        // 로그인 유지 등 상태 유지가 필요할 때 사용되는 내장 객체입니다.
        // 세션은 쿠키와 달리 모든 데이터를 저장할 수 있으며 크기도 제한이 없습니다.
        // 세션의 수명은 기본 1800초 -> 원하는 만큼 수명을 설정할 수 있습니다.
        // 브라우저가 종료되면 남은 수명에 상관없이 세션 데이터는 소멸합니다.

        // 현재 로그인한 회원의 모든 정보 조회
        Member foundMember = memberMapper.findOne(account);

        // DB 데이터를 사용할 것 만 정제
        LoginUserResponseDTO dto = LoginUserResponseDTO.builder()
                .account(foundMember.getAccount())
                .name(foundMember.getName())
                .email(foundMember.getEmail())
                .build();

        // 세션에 로그인 한 회원 정보를 저장
        session.setAttribute("login", dto);

        // 세션 수명 설정
        session.setMaxInactiveInterval(60 * 60);    // 1시간(3600초)
    }

    public void autoLoginClear(HttpServletRequest request, HttpServletResponse response) {
        Cookie c = WebUtils.getCookie(request, "auto");

        //쿠키 삭제
        // -> 쿠키의 수명을 0으로 설정하여 다시 클라이언트로 전송 -> 자동 소멸
        if(c!=null) {
            c.setMaxAge(0);
            c.setPath("/");
            response.addCookie(c);
        }

        //데이터베이스에도 세션아이디와 만료시간을 정리.
        memberMapper.saveAutoLogin(
                AutoLoginDTO.builder()
                        .sessionId("none")  // 기존 세션 아이디 지우기
                        .limitTime(LocalDateTime.now())
                        .account(LoginUtils.getCurrentLoginMemberAccount(request.getSession()))
                        .build()
        );



    }
}
