package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.dto.BoardListReponseDTO;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDTO;
import com.study.springstudy.springmvc.chap04.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/*
    요구사항 명세

    1. 목록 조회 요청(/board/list : GET)
      - Req. data: 없음(나중에: 페이지 번호)
      - response: chap04/list.jsp
      - resp. data: 글 목록 리스트 -> Model에 담아서 리턴(bList)
                    제목은 5자를 초과하면 안됨.
                    내용은 30자를 초과하면 안됨.
                    날짜 패턴은 yyyy-MM-dd HH:mm
                    글 번호, 조회수, 작성저는 있는 그대로 운반할 것.

    2. 글쓰기 화면 요청(/board/write : GET)
      - Req. data: 없음
      - response: chap04/write.jsp
      - resp. data: 없음

    3. 글쓰기 등록 요청(/board/write : POST)
      - Req. data: writer, title, content --> 모두 문자열 타입 (BoardWriteRequestDTO)
                    DTO를 board로 바꿔서 mapper에게 전달해야 함. -> Board의 생성자를 이용.
      - response: 글 목록 페이지 요청 다시 들어오게끔(redirect)
      - resp. data: 없음

    4. 글 삭제 요청(/board/delete : POST)
      - Req. data: boardNo --> int 타입
      - response: chap04/write.jsp
      - resp. data: 글 목록 페이지 요청 다시 들어오게끔(redirect)

    5. 글 상세보기 요청(/board/detail : GET)
      - Req. data: boardNo --> int 타입
      - response: chap04/detail.jsp
      - resp. data: Model에 특정 게시글 정보를 담아서 리턴

 */
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor // 의존성 주입(private final BoardService boardService;)
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model) {
        List<BoardListReponseDTO> list = boardService.getList();// 서비스에 맡긴다. 서비스에 메소드 안만들어져 있는 상태라서 alt+enter로 BoardService에 메소드를 생성시킨다.
        model.addAttribute("bList", list);
        return "chap04/list";
    }

    @GetMapping("/write")
    public String write() {
        return "chap04/write";
    }

    @PostMapping("/write")
    public String write(BoardWriteRequestDTO dto) {
        System.out.println("[dbg] dto = " + dto);
        boardService.register(dto);
        return "redirect:/board/list";
    }
}
