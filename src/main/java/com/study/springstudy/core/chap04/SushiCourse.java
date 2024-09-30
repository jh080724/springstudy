package com.study.springstudy.core.chap04;

import org.springframework.stereotype.Component;

// 스프링이 자동으로 객체를 생성하고, 그 객체의 생명주기 및 기타등등을 관리하게 하겠다는
// 어노테이션
@Component("sushi")
public class SushiCourse implements Course {
    @Override
    public void combineMenu() {
        System.out.println("====== 스시 코스 구성 ======");
        System.out.println("1. 대합 맑은국");
        System.out.println("2. 전어, 고등어, 도미 스시");
        System.out.println("3. 구이 요리");
        System.out.println("4. 오도로, 우니군함, 복어");
        System.out.println("5. 튀김 요리");
        System.out.println("6. 아나고덮밥");
        System.out.println("7. 조리장 특선 디저트");
    }
}
