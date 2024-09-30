package com.study.springstudy.core.chap04;

import org.springframework.stereotype.Component;

// 스프링이 자동으로 객체를 생성하고, 그 객체의 생명주기 및 기타등등을 관리하게 하겠다는
// 어노테이션
@Component("kim")
public class KimChef implements Chef {
    @Override
    public void cook() {
        System.out.println("안녕하세요. 김쉐프입니다.");
        System.out.println("요리를 시작합니다");
    }
}
