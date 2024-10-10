package com.study.springstudy.springmvc.chap04.mapper;

import com.study.springstudy.springmvc.chap04.entity.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper {
    // 중복확인(아이디, 이메일)
    // 어떤걸 중복검사할 것인지 type으로 받기.(account or email)
    // 값을 keyword로 받자
    boolean existsById(String type, String keyword);

    // 회원가입
    boolean save(Member member);

    // 회원정보 개별 조회
    Member findOne(String account);

}
