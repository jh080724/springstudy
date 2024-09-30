package com.study.springstudy.core.chap01;

/**
 * @problem -   호텔 클래스에서직접 객체를 생성하면
 *              나중에 의존객체(레스토랑, 헤드쉐프)를 변경해야할 때
 *              직젖 호텔클래스를 수정해야 하므로 OCP를 위반하게 됨.
 *              그리고, headChef가 변경되면 레스토랑 안에 쉐프도
 *              같이 변경되어야할때 2군데 수정해야 함.
 */


public class Hotel {

    // 레스토랑
    private WesternRestaurant restaurant = new WesternRestaurant();


    // 헤드쉐프
    private JannChef headChef = new JannChef();


    // 호텔을 소개하는 기능
    public void inform(){
        System.out.printf("우리 호텔의 레스토랑은 %s입니다. 그리고, 헤드쉐프는 %s 입니다. \n",
                restaurant.getClass().getSimpleName(), headChef.getClass().getSimpleName());

        restaurant.order();

    }

}
