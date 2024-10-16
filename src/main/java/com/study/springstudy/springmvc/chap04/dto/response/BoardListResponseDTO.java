package com.study.springstudy.springmvc.chap04.dto.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@ToString
@EqualsAndHashCode
public class BoardListResponseDTO {  // BoardList의 응답용 DTO
    private final int boardNo;
    private final String shortTitle;    // 5자 넘어가면 잘라내기
    private final String shortContent;  // 30자 넘어가면 잘라내기
    private final String regDate;   // yyyy-MM-dd HH:mm
    private final int viewCount;
    private final String writer;
    private final int replyCount;

    // 생성자
    public BoardListResponseDTO(BoardDetailResponseDTO board) {
        this.boardNo = board.getBoardNo();
        this.shortTitle = makeShortTitle(board.getTitle());
        this.shortContent = makeShortContent(board.getContent());
        this.regDate = board.getRegDate();
//                makePrettierDateString(board.getRegDate());
        this.viewCount = board.getViewCount();
        this.writer = board.getWriter();
        this.replyCount = board.getReplyCount();
    }

    public static String makePrettierDateString(LocalDateTime regDate) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return dtf.format(regDate);
    }

    private String makeShortContent(String content) {
        return sliceString(content, 30);
    }

    private String makeShortTitle(String title) {
        return sliceString(title, 5);

    }

    // target: 줄이고 싶은 문자열 원본
    // wishlength: 자르고 싶은 글자 수
    // target의 길이가 wishLength보다 길면 wishLength 만큼 잘라서 뒤에 점점정(...)을 붙여서 리턴
    private String sliceString(String target, int wishLength) {
        return (target.length() > wishLength)
                ? target.substring(0, wishLength)
                + "...":target;
    }
}
