package com.study.springstudy.springmvc.chap04.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.study.springstudy.springmvc.chap04.entity.Reply;

import java.time.LocalDateTime;

public class ReplyDetailResponseDTO {

    private int rno;
    private String text;
    private String writer;

    // 나중에 dTO가 JSON으로 변환될 때 원하는 Format형식으로 자동 변환.
    @JsonFormat(pattern = "yyyy년 MM월 dd일 HH:mm")
    private LocalDateTime regDate;

    public ReplyDetailResponseDTO(Reply reply) {
        this.rno = reply.getReplyNo();
        this.text = reply.getReplyText();
        this.writer = reply.getReplyWriter();
        this.regDate = reply.getReplyDate();
    }
}
