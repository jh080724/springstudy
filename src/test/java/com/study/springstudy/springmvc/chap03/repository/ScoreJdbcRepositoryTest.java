package com.study.springstudy.springmvc.chap03.repository;

import com.study.springstudy.springmvc.chap03.dto.ScorePostDTO;
import com.study.springstudy.springmvc.chap03.entity.Score;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Spring 컨테이너의 빈을 활용한 테스트 클래스를 활용.
@SpringBootTest
class ScoreJdbcRepositoryTest {

    @Autowired
    ScoreJdbcRepository repository;

    @Test
    @DisplayName("새로운 성적 정보를 save를 통해 추가한다.")
        // 스프링에 테스트 설명 추가
    void saveTest() {

        ScorePostDTO dto = new ScorePostDTO("홍길동", 100, 100, 100);
        Score score = new Score(dto);
        repository.save(score);
    }

    @Test
    @DisplayName("Score 테이블의 모든 학생 목록을 조회하면 학생의 수가 2명일 것이다.")
    void findAllTest() {

        // given - 테스트에 필요한 값을 세팅

        // when - 테스트 주요 코드 실행(테스트 실행 목표)
        List<Score> scoreList = repository.findAll();
        System.out.println("scoreList = " + scoreList); // 단언기법 결과에 영향을 줄수 있으므로 실제 검증에서는 출력문 확인 지양.

        // then - 테스트 결과를 검증(테스트기법: Assertion: 단언기법(<- 단언컨데의 단언))
        assertEquals(scoreList.size(), 4);

    }

    @Test
    @DisplayName("2번 학생의 이름은 홍길동일 것이다.")
    void findOneTest() {
        // given
        int stuNum = 25;

        // when
        Score score = repository.findOne(stuNum);

        // then
        if(score != null){
            assertEquals("홍길동", score.getStuName());
        } else{
            assertNull(score);
        }
    }

    @Test
    @DisplayName("1번 학생을 삭제한 후에는 더이상 1번 학생이 조회되지 않아야 한다.")
    void deleteTest() {
        // given
        int stuNum = 1;

        // when
        repository.delete(stuNum);
        Score score = repository.findOne(stuNum);

        // then
        assertNull(score);

    }
}