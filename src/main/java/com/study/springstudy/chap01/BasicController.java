package com.study.springstudy.chap01;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.lang.model.SourceVersion;

@Controller
@RequestMapping("/spring/chap01")
public class BasicController {

    // URL: /spring/chap01/hello 요청수신
    @RequestMapping("/hello")
    public String hello() {

        System.out.println("/hello 요청 수신!!");

        return "hello";
    }

    // === 요청 파라미터(Query String) 읽기 ===
    // URL: /spring/chap01/person
    // 1. HttpServletRequest 사용 (잘 사용 않함.)
    @RequestMapping("/person")
    public String person(HttpServletRequest request) {

        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));

        System.out.println("name = " + name);
        System.out.println("age = " + age);

        return "";
    }

    // 2. @RequestParameter 사용
    // URL: /spring/chap01/major?stu=에일리&major=경영학&grade=3
    @RequestMapping("/major")
    public String major(@RequestParam String stu,  // 매개변수이름과 @RequestParam("name")의 "name"이 같으면 어노테이션 괄호 또는 어노테이션 생략 가능
                        @RequestParam("major") String mj,
                        int grade) {

        System.out.println("stu = " + stu);
        System.out.println("mj = " + mj);
        System.out.println("grade = " + grade);

        return ""; // 화면으로 볼게 아니라서 빈문자열로 리턴함.
    }

    // 3. 커맨드 객체(request DTO:Data Transfer Object) 사용
    // URL: /spring/chap01/order?orderNum=22&goods=구두&amount=3&price=10000&.....
    @RequestMapping("/order")
    public String order(OrderDTO dto) {
        System.out.println("[dbg] dto = " + dto);
        return "";
    }

}
