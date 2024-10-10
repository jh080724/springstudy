package com.study.springstudy.springmvc.chap04.controller;

import com.study.springstudy.springmvc.chap04.dto.request.ReplyModifyRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.request.ReplyPostRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.response.ReplyListResponseDTO;
import com.study.springstudy.springmvc.chap04.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController //메서드마다 @ResponseBody를 붙일 필요가 없다.
@RequestMapping("/api/v1/replies")
@RequiredArgsConstructor
public class ReplyApiController {
    private final ReplyService replyService;

    //fetch(URL, requestInfo);
    @PostMapping
    public ResponseEntity<?> create(@Validated @RequestBody ReplyPostRequestDTO dto,
                                    BindingResult result) { // 검증결과 메세지를 가진 객체

        if (result.hasErrors()) {
            // 입력값 검증에 걸리면 400번 status와 함께 메시지를 클라이언트로 전송
            return ResponseEntity.badRequest()
                    .body(result.toString());
        }

        System.out.println("[dbg] /api/v1/replies: POST!!");
        System.out.println("[dbg] dto = " + dto);

        replyService.register(dto);

        return ResponseEntity.ok().body("Success");
    }

    // 댓글 목록 조회 요청
    // URL: "/api/v1/replies/{boardNo}/page/페이지 번호"
    @GetMapping("/{boardNo}/page/{pageNo}")
    public ResponseEntity<?> list(@PathVariable int boardNo,
                                  @PathVariable int pageNo) {

        System.out.println("[dbg] 댓글목록 조회: boardNo: " + boardNo + ", pageNo: " + pageNo);

        ReplyListResponseDTO replies = replyService.getList(boardNo, pageNo);

//        System.out.println("[dbg] 댓글목록조회, ReplyApiController:list():dtoList = " + dtoList.toString());

        return ResponseEntity.ok().body(replies);
    }

    @PatchMapping
    public ResponseEntity<?> update(@Validated @RequestBody ReplyModifyRequestDTO dto, BindingResult result) {
        if (result.hasErrors()) {
            return ResponseEntity.badRequest().body(result.toString());
//                    getFieldError().getDefaultMessage());
        }

        replyService.modify(dto);
        return ResponseEntity.ok().body("modSuccess");
    }

    @DeleteMapping("/{replyNo}")
    public ResponseEntity<?> remove(@PathVariable int replyNo)  {
        try {
            replyService.delete(replyNo);
            return ResponseEntity.ok().body("delSuccess");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }
}
