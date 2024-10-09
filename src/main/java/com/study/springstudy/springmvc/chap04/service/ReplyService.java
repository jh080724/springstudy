package com.study.springstudy.springmvc.chap04.service;

import com.study.springstudy.springmvc.chap04.dto.request.ReplyPostRequestDTO;
import com.study.springstudy.springmvc.chap04.dto.response.ReplyDetailResponseDTO;
import com.study.springstudy.springmvc.chap04.entity.Reply;
import com.study.springstudy.springmvc.chap04.mapper.ReplyMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReplyService {

    private final ReplyMapper mapper;

    public void register(ReplyPostRequestDTO dto) {
        mapper.save(dto.toEntity());
    }

    public List<ReplyDetailResponseDTO> getList(int boardNo) {
        List<Reply> replyList = mapper.findAll(boardNo);

        System.out.println("[dbg] ReplyService:getList(): replyList = " + replyList);

        List<ReplyDetailResponseDTO> dtoList  = new ArrayList<>();

//        replyList.forEach(reply -> dtoList.add(new ReplyDetailResponseDTO(reply)));
        for (Reply reply : replyList) {
            dtoList.add(new ReplyDetailResponseDTO(reply));
        }

        System.out.println("[dbg] getList() : dtoList.toString() = " + dtoList.toString());

        return dtoList;
    }
}
