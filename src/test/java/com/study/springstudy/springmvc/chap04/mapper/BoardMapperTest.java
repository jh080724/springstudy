package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.dto.request.PageDTO;
import com.study.springstudy.springmvc.chap04.dto.response.BoardDetailResponseDTO;
import com.study.springstudy.springmvc.chap04.entity.Board;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
//@Transactional
//@Rollback
class BoardMapperTest {

    @Autowired
    BoardMapper mapper;

    // 테스트 실행 전에 실행할 코드 @BeforeEach
    @Test
//    @BeforeEach
    void bulkInsert() {
        for (int i = 1; i <= 342; i++) {
            Board b = new Board();
            b.setTitle("테스트 제목" + i);
            b.setWriter("글쓴이" + i);
            b.setContent("내용" + i);

            mapper.save(b);
        }
    }
    @Test
//    @DisplayName("전체 조회 테스트를 진행하면 게시물의 개수가 3개일 것이다.")
    @DisplayName("페이징이 적용된 게시물 조회 테스트.")
    void findAllTest() {
        // given
        PageDTO page = new PageDTO();  // pageNo1, amount 6
        page.setPageNo(16);
        page.setAmount(10);

        // when
        List<BoardDetailResponseDTO> boardList = mapper.findAll(page);

        // then
        boardList.forEach(System.out::println);

        Assertions.assertEquals(10, boardList.size());

    }

    @Test
    @DisplayName("3번글을 상세 조회했을때 작성자가 글쓴이 3일 것이다.")
    void findOneTest() {
        // given
        int boardNo = 10;

        // when
        Board board = mapper.findOne(boardNo);

        // then
        Assertions.assertEquals("글쓴이10", board.getWriter());
    }

    @Test
    @DisplayName("3번 글의 조회수를 2번 증가시킨 후 확인해 보면 조회수가 2일것이다.")
    void updateViewTest() {
        // given
        int boardNo = 3;

        // when
        mapper.updateViewCount(boardNo);
        mapper.updateViewCount(boardNo);

        // then
        Assertions.assertEquals(2, mapper.findOne(3).getViewCount());
    }

    @Test
    @DisplayName("17번 글을 삭제한 후 조회하면 조회되지 않아야 함.")
    void deleteTest() {
        // given
        int boardNo = 17;

        // when
        mapper.delete(boardNo);

        // then
        Assertions.assertNull(mapper.findOne(boardNo));
    }
}