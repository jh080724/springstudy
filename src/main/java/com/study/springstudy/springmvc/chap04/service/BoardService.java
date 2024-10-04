package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.BoardListReponseDTO;
import com.study.springstudy.springmvc.chap04.dto.BoardWriteRequestDTO;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service // 컨트롤러는 서비스가 필요하니 빈등록
@RequiredArgsConstructor
public class BoardService {
    // MyBatis가 우리가 만든 xml을 클래스로 변환해서 빈등록을 해두기 때문에 주입이 가능하다.
    private final BoardMapper mapper; // @RequiredArgsConstructor 선언해서 주입해주어야 함.

    // mapper로부터 전달받은 entity list를 dto list로 변환해서 컨트롤로에게 리턴
    public List<BoardListReponseDTO> getList() {

        List<Board> boardList = mapper.findAll();
//        List<BoardListReponseDTO> dtoListst = new ArrayList<>(); // stream()으로 처리하여 주석처리.

//        return boardList.stream()
//                .map(board -> new BoardListReponseDTO(board))
//                .collect(Collectors.toList()); --> 아래코드 31 라인 메소드 참조로 변환

        return boardList.stream()
                .map(BoardListReponseDTO::new)
                .collect(Collectors.toList());
    }

    public void register(BoardWriteRequestDTO dto) {
        mapper.save(new Board(dto)); // dto를 entity로 변환해서 mapper에게 전달.
    }
}
