package com.study.springstudy.core.chap04.config;

import com.study.springstudy.core.chap04.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

// 스프링에게 객체 생성 등의 제어를 맡길때 사용하는 어노테이션.
// 수동으로 Bean(객체)를 등록할때 클래스에 선언하는 문법.
@Configuration
//지정한 패키지 내에 있는 @Component 붙은 객체들을 전부 스캔해서 스프링 컨텐너에
// 등록 시키겠다.
@ComponentScan(basePackages = "com.study.springstudy.core")
public class AppConfig {


}
