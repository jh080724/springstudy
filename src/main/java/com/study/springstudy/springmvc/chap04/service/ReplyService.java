package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.request.PageDTO;
import com.study.springstudy.springmvc.chap04.dto.request.ReplyModifyRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.request.ReplyPostRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.response.ReplyDetailResponseDTO;
import com.study.springstudy.springmvc.chap04.dto.response.ReplyListResponseDTO;
import com.study.springstudy.springmvc.chap04.entity.Reply;
import com.study.springstudy.springmvc.chap04.mapper.ReplyMapper;
import com.study.springstudy.springmvc.util.LoginUtils;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper mapper;

    public void register(ReplyPostRequestDTO dto, HttpSession session) {
        Reply reply = dto.toEntity();

        // 세션 데이터에서 현재 로그인 중인 사용자의 아이디를 따로 세팅
        reply.setAccount(LoginUtils.getCurrentLoginMemberAccount(session));
        mapper.save(reply);

    }

    public ReplyListResponseDTO getList(int boardNo, int pageNo) {
        PageDTO page = new PageDTO();
        page.setAmount(5);
        page.setPageNo(pageNo);

        // DB 쿼리
        List<Reply> replyList = mapper.findAll(boardNo, page);

        System.out.println("[dbg] 서비스: ReplyService:getList(): replyList = " + replyList);

        List<ReplyDetailResponseDTO> dtoList  = new ArrayList<>();

        replyList.forEach(reply -> dtoList.add(new ReplyDetailResponseDTO(reply)));
//        for (Reply reply : replyList) {
//            dtoList.add(new ReplyDetailResponseDTO(reply));
//        }

//        System.out.println("[dbg] getList() : dtoList.toString() = " + dtoList.toString());

        return ReplyListResponseDTO.builder()
                .count(dtoList.size())
                .pageInfo(new PageMaker(mapper.count(boardNo), page))
                .replies(dtoList)
                .build();

//        return dtoList;
    }

    public void modify(ReplyModifyRequestDTO dto) {
        mapper.modify(dto.toEntity());
    }

    public void delete(int replyNo) throws Exception {
        mapper.delete(replyNo);
    }
}
