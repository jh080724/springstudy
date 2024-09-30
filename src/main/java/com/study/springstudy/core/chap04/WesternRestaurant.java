package com.study.springstudy.core.chap04;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

// 스프링이 자동으로 객체를 생성하고, 그 객체의 생명주기 및 기타등등을 관리하게 하겠다는
// 어노테이션
//@Component
public class WesternRestaurant implements Restaurant {
    private Chef chef;
    private Course course;

    @Autowired
    public WesternRestaurant(@Qualifier("jann") Chef chef,
                             @Qualifier("french") Course course) {
        this.chef = chef;
        this.course = course;
    }

    @Override
    public void order(){
        System.out.println("안녕하세요. 서양 레스토랑입니다.");
        chef.cook();
        course.combineMenu();
    }
}
