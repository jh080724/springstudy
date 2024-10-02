package com.study.springstudy.springmvc.chap03.repository;

import com.study.springstudy.springmvc.chap03.entity.Score;

import java.util.List;

// 역할: 적당한 저장소에 CRUD 담당
// 예가 자체적으로 하는게 아닌, 기능만 명세 --> 인터페이스니깡
public interface ScoreRepository {
    //저장소에 데이터 추가
    void save(Score score);

    //저장소에서 데이터 전체 조회하기
    List<Score> findAll();

    //저장소에서 데이터 개벌 조회하기
    Score findOne(int stuNum);

    // 저장소에서 데이터 삭제하기
    void delete(int stuNum);

}
