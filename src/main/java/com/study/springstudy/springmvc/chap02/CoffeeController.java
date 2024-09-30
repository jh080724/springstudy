package com.study.springstudy.springmvc.chap02;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/coffee")
public class CoffeeController {

    //    @RequestMapping(value = "/order", method = RequestMethod.GET)
    @GetMapping("/order")
    public String order() {
        return "mvc/coffee-form";
    }

    // POST 요청으로 들어오는 /coffee/result 요청을 받아서 처리하겠다.
    // 파라미터: 커피종류(menu), 커피가격(price)
//    @RequestMapping(value="/result", mathod= RequestMethod.POST)
    @PostMapping("/result")
//    public String result(@RequestParam("menu") String menu,
//                         @RequestParam("price") int price) { // 아래와 같이 ("name")생략가능
    public String result(@RequestParam String menu,
                         @RequestParam int price,
                         Model model) {

        model.addAttribute("mmm", menu);
        model.addAttribute("ppp", price);

        return "mvc/coffee-result";
    }
}
