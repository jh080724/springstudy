package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.dto.request.PageDTO;
import com.study.springstudy.springmvc.chap04.dto.response.BoardDetailResponseDTO;
import com.study.springstudy.springmvc.chap04.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardMapper {
    // 목록 조회
    List<BoardDetailResponseDTO> findAll(PageDTO page);

    // 게시글 상세 조회
    Board findOne(int boardNo);

    // 게시글 등록
    void save(Board board);

    // 게시글 삭제
    void delete(int boardNo);

    // 게시글 수정

    // 조회수 처리
    void updateViewCount(int boardNo);
//    void updateViewCount(int bno);

    // 검색 게시물 개수 카운트
    int getTotalCount(PageDTO page);

}
