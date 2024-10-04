package com.study.springstudy.springmvc.chap04.dto;

import com.study.springstudy.springmvc.chap04.entity.Board;

import java.time.LocalDateTime;

public class BoardDetailResponseDTO {

    private final int boardNo;
    private final String title;
    private final String content;
    private final String regDate;
    private final String writer;

    public BoardDetailResponseDTO(Board board) {
        this.boardNo = board.getBoardNo();
        this.title = board.getTitle();
        this.content = board.getContent();
//        this.regDate = board.getRegDate();
        this.regDate = makePrettierDateString(board.getRegDate());
        this.writer = board.getWriter();

    }
}
