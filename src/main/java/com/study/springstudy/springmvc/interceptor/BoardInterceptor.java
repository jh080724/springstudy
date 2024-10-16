package com.study.springstudy.springmvc.interceptor;

import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import com.study.springstudy.springmvc.util.LoginUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.PrintWriter;

import static com.study.springstudy.springmvc.util.LoginUtils.*;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class BoardInterceptor implements HandlerInterceptor {

    // 여기 인터셉터에서 Board DB 조회가 필요해서 주입을 받음.
    private final BoardMapper boardMapper;

    // 컨트롤러로 요청이 들어가기 전에 실행할 내용
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //로그인 안했으면 글쓰기, 글 수정, 글 삭제 요청을 튕겨낼 것.
        HttpSession session = request.getSession();

        if (!isLogin(session)) {
            System.out.println("[dbg] 권한없음.! 요청거부!");
            response.sendRedirect("/members/sign-in");
            return false;
        }

        // 삭제요청이 들어올때 서버에서 다시 한번 글 주인의 요청인지 체크

        // 현재 요청이 삭제 요청인지 확인
        String uri = request.getRequestURI();
        if(uri.contains("delete")){
            // 관리자라면 ?  -> 삭제 수행 통과
            if(isAdmin(session)) return true;

            // 삭제요청이 들어온 글번호를 확인 -> DB에서 조회 -> 작성자와 로그인 회원의 계정명 비교
            String boardNo = request.getParameter("boardNo");

            String writer
                    = boardMapper.findOne(Integer.parseInt(boardNo)).getWriter();

            // 내가 쓴 글이 아니라면 -> 접근 권한이 없는 피드백을 주겠다.
            if(!isMine(session, writer)){
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter w = response.getWriter();
                String htmlCode = "<script>\n" +
                        "    alert('본인이 작성한 게시글만 삭제가 가능합니다.');\n" +
                        "    location.href='/board/list';\n" +
                        "</script>";
                w.write(htmlCode);
                w.flush();
                return false;
            }

        }



        return true;
    }

    // 컨트롤러로 요청이 들어간 후 공통적으로 처리할 코드나 검사할 일들을 실행할 내용
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }
}
