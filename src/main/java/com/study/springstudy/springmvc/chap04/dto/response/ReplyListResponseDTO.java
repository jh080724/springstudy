package com.study.springstudy.springmvc.chap04.dto.response;

import com.study.springstudy.springmvc.chap04.service.PageMaker;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter @ToString @Builder
public class ReplyListResponseDTO {
    // 댓글 목록 페이징 해야 하기 때문에 좀 더 여러개의 정보를 화면단으로 넘겨야함.
    // DTO 새롭게 생성해서 댓글 수와 페이징 정보도 함께 넘기자.
    private int count; // 댓극 수
    private PageMaker pageInfo; // 페이징 정보

    private List<ReplyDetailResponseDTO> replies;
}
