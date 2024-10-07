package com.study.springstudy.springmvc.chap04.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class SearchDTO extends PageDTO{
// DTO:요청과 함께 넘어오는 파라미터 넘기는 역할

    // 검색 조건, 검색어
    private String type, keyword;

    // 조회 기능에 검색기능을 붙이려는 시도를 하는 것이기 때문에
    // 항상 검색이 들어오지는 않는다. -> 빈 문자열로 초기화.
    public SearchDTO() {
        this.type = "";
        this.keyword = "";
    }
}
