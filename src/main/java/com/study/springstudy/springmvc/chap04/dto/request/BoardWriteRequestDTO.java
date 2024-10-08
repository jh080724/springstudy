package com.study.springstudy.springmvc.chap04.dto.request;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@ToString
@EqualsAndHashCode
public class BoardWriteRequestDTO {
//    private final int boardNo;
    private String writer;
    private String title;
    private String content;

//    public BoardWriteRequestDTO(Board board) {
//        this.boardNo = board.getBoardNo();
//        this.writer = board.getWriter();
//        this.title = board.getTitle();
//        this.content = board.getContent();
//    }
}
