package com.study.springstudy.springmvc.chap03.mapper;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ScoreMapperTest {

    @Autowired
    ScoreMapper mapper;

    @Test
    @DisplayName("마이바티스 인서트 테스트")
    void insertTest() {
        // given
        Score score = new Score(new ScorePostDTO("김마이바티스", 60, 60, 60));

        // when
        mapper.save(score);

        // then
    }

    @Test
    @DisplayName("셀렉트 테스트")
    void selectTest() {
        // given

        // when
        List<Score> scoreList = mapper.findAll("avg");

        // then
//        scoreList.forEach(score -> System.out.println(score));
        scoreList.forEach(System.out::println);
    }

    @Test
    @DisplayName("셀렉트 원 테스트")
    void selectOne() {
        // given

        // when
        Score score = mapper.findOne(9);


        // then
        System.out.println("score = " + score);
    }
}
