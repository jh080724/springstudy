package com.study.springstudy.springmvc.chap03.mapper;

import com.study.springstudy.springmvc.chap03.entity.Score;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

// 마이바티스의 SQL실행을 위한 인터페이스임을 명시
@Mapper
public interface ScoreMapper {
    //저장소에 데이터 추가
    void save(Score score);

    //저장소에서 데이터 전체 조회하기
    List<Score> findAll(String sort);

    //저장소에서 데이터 개벌 조회하기
    Score findOne(int stuNum);

    // 저장소에서 데이터 삭제하기
    void delete(int stuNum);

    // 저장소에서 데이터 수정(업데이트)하기
    void update(Score score);
}
