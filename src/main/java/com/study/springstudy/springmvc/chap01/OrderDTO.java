package com.study.springstudy.springmvc.chap01;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

// 커맨드 객체 생성.
// 요청과 함께 전달되는 데이터가 많을경우
// 스프링에게 객체 형태로 파라미터를 전달해 달라고 요구할 수 있다.
@Getter
@Setter
@ToString

public class OrderDTO {
    // 필드 선언 -> 필드명이 쿼리 파라미터 변수명과 동일해야 한다.
    // order?orderNum=22&goods=구두&amount=3&price=10000&.....
    // getter, setter를 구현하기 위해 정보은닉(private 선언하고 setter/getter 사용해야함.)을 구현해야 한다.
    private int orderNum;
    private String goods;
    private int amount;
    private int price;


}
