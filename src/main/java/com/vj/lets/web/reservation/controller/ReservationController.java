package com.vj.lets.web.reservation.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 예약 컨트롤러 (임시)
 * @author 박상훈
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Controller
@RequestMapping("/reservation")
@RequiredArgsConstructor
@Slf4j
public class ReservationController {

    @GetMapping("")
    public String resCart(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        String name = "A1";
        String bookDate = "2023-09-27";
        int headCount = 3;
        int price = 20000;
        session.setAttribute("name",name);
        session.setAttribute("bookDate",bookDate);
        session.setAttribute("headCount",headCount);
        session.setAttribute("price",price);

        return "common/cafe/reservation";
    }

}
