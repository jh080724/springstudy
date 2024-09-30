package com.study.springstudy.springmvc.chap01;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class ResponseController {

    // JSP 파일로 포워딩할때 데이터 전달하기
    // 1. Model 객체 사용하기
    @RequestMapping("/hobbies")
    public String hobbies(Model model) {

        model.addAttribute("name", "에일리");
        model.addAttribute("hobbies", List.of("골프", "수영", "영화감상"));

        return "mvc/hobbies";   // # jsp view resolver setting: spring.mvc.view.prefix=/WEB-INF/views/
        // spring.mvc.view.suffix=.jsp
    }

    //2. ModelAndView 객체 사용하기
    @RequestMapping("/hobbies2")
    public ModelAndView hobbies2(){
        ModelAndView mv = new ModelAndView("mvc/hobbies");

        System.out.println("[dbg] hobbies2 호출!!");
        mv.addObject("name", "아이유");
        mv.addObject("hobbies", List.of("카페가기", "맛집가기"));

        return mv;
    }
}
