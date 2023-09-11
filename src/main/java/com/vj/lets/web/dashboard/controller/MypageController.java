package com.vj.lets.web.dashboard.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 회원 마이페이지 관련 요청 컨트롤러
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Controller
@RequestMapping("/mypage")
@RequiredArgsConstructor
@Slf4j
public class MypageController {

    /**
     * 마이페이지 메인 화면 출력
     *
     * @param model 객체 모델
     * @return 논리적 뷰 이름
     */
    @GetMapping("")
    public String mypageMain(Model model) {
        return "dashboard/mypage/mypage_dashboard";
    }

    @GetMapping("/reservation")
    public String reservationView(Model model) {
        return "dashboard/mypage/reservations";
    }

    @GetMapping("/review")
    public String reviewView(Model model) {
        return "dashboard/mypage/reviews";
    }

    @GetMapping("/wishlist")
    public String wishlistView(Model model) {
        return "dashboard/mypage/wishlists";
    }

}
