package com.vj.lets.web.dashboard.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 호스트 대시보드 관련 요청 컨트롤러
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@Controller
@RequestMapping("/host")
@RequiredArgsConstructor
@Slf4j
public class HostController {

    /**
     * 호스트 대시보드 메인 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("")
    public String hostMain(Model model) {
        return "dashboard/host/host_dashboard";
    }
    @GetMapping("/cafe")
    public String cafeRegister(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();

        return "dashboard/host/cafe_register";
    }
    @GetMapping("/room")
    public String roomList(HttpServletRequest request, Model model){
        return "dashboard/host/room_table";
    }
    @GetMapping("/room/{id}")
    public String roomDetail(HttpServletRequest request, Model model){
        return "dashboard/host/room_register";
    }

}
