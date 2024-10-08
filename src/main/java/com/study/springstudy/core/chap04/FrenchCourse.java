package com.study.springstudy.core.chap04;

import org.springframework.stereotype.Component;

// 스프링이 자동으로 객체를 생성하고, 그 객체의 생명주기 및 기타등등을 관리하게 하겠다는
// 어노테이션
@Component("french")
public class FrenchCourse implements Course {
    @Override
    public void combineMenu() {
        System.out.println("====== 프렌치 코스 구성 ======");
        System.out.println("1. 제철 채소, 퀴노아");
        System.out.println("2. 트러플 크림 스프");
        System.out.println("3. 참돔 포와레");
        System.out.println("4. 한우 살치살 스테이크");
        System.out.println("5. 프렌치 랙");
        System.out.println("6. 조리장 특제 마카롱");
    }
}
