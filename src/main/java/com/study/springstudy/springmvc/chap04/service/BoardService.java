package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.response.BoardDetailResponseDTO;
import com.study.springstudy.springmvc.chap04.dto.response.BoardListReponseDTO;
import com.study.springstudy.springmvc.chap04.dto.request.BoardWriteRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.request.PageDTO;
import com.study.springstudy.springmvc.chap04.entity.Board;
import com.study.springstudy.springmvc.chap04.mapper.BoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service // 컨트롤러는 서비스가 필요하니 빈등록
@RequiredArgsConstructor
public class BoardService {
    // MyBatis가 우리가 만든 xml을 클래스로 변환해서 빈등록을 해두기 때문에 주입이 가능하다.
    private final BoardMapper mapper; // @RequiredArgsConstructor 선언해서 주입해주어야 함.

    // mapper로부터 전달받은 entity list를 dto list로 변환해서 컨트롤로에게 리턴
//    public List<BoardListReponseDTO> getList(PageDTO page) {
    public Map<String, Object> getList(PageDTO page) { // SearchDTO가 넘어옴.

        // 전체 게시글을 가지고 오는 것이 아닌, 특정 페이지 부분만 가져와야 함.
        List<BoardDetailResponseDTO> boardList = mapper.findAll(page);
        PageMaker pageMaker = new PageMaker(mapper.getTotalCount(page), page);

//        List<BoardListReponseDTO> dtoListst = new ArrayList<>(); // stream()으로 처리하여 주석처리.

//        return boardList.stream()
//                .map(board -> new BoardListReponseDTO(board))
//                .collect(Collectors.toList()); --> 아래코드 31 라인 메소드 참조로 변환

        // DTO로 변경
//        return boardList.stream()
//                .map(BoardListReponseDTO::new)
//                .collect(Collectors.toList());

        List<BoardListReponseDTO> dtoList = boardList.stream()
                .map(BoardListReponseDTO::new)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("bList", dtoList);
        result.put("pm", pageMaker);

        return result;
    }

    public void register(BoardWriteRequestDTO dto) {
        mapper.save(new Board(dto)); // dto를 entity로 변환해서 mapper에게 전달.
    }

    public BoardDetailResponseDTO getDetail(int bno) {
        mapper.updateViewCount(bno);
        Board board = mapper.findOne(bno);
        return new BoardDetailResponseDTO(board);
    }

    public void delete(int boardNo) {
        mapper.delete(boardNo);
    }
}
