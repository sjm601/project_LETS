package com.vj.lets.web.reservation.controller;

import com.vj.lets.domain.payment.dto.Payment;
import com.vj.lets.domain.payment.service.PaymentService;
import com.vj.lets.domain.reservation.dto.Reservation;
import com.vj.lets.domain.reservation.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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

    private final ReservationService reservationService;
    private final PaymentService paymentService;

    @GetMapping("/{id}")
    public String resCart(@PathVariable int id, HttpServletRequest request,Model model){
        Map<String,Reservation> reservation = reservationService.getResInfo(id);
       model.addAttribute("reservation", reservation);
        return "common/cafe/reservation";
    }

    @GetMapping("/{id}/payment")
    public String payPage(@PathVariable int id, HttpServletRequest request,Model model){
        Map<String,Reservation> reservation = reservationService.getResInfo(id);
        model.addAttribute("reservation", reservation);
        return "common/cafe/payment";
    }


    @PostMapping("/{id}/paymet")
    public String doPay(@PathVariable int id, @ModelAttribute Payment payment, Model model){
        Map<String,Reservation> reservation = reservationService.getResInfo(id);
        model.addAttribute("reservation", reservation);
        payment.setReservationId(id);
        paymentService.payment(payment);
        return "redirect:article/1";
    }





}
