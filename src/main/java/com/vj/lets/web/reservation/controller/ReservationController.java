package com.vj.lets.web.reservation.controller;

import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.payment.dto.Payment;
import com.vj.lets.domain.payment.service.PaymentService;
import com.vj.lets.domain.reservation.dto.Reservation;
import com.vj.lets.domain.reservation.service.ReservationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 예약, 결제 컨트롤러 
 *
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

    /**
     * 예약 정보 확인 페이지
     * @param id 예약 아이디
     * @param request 리퀘스트
     * @param model 모델 객체
     * @return 일치하는 예약이 없거나 결제가 완료된 상태인 경우 카페 리스트로 리다이렉트
     * @return 데이터 있을지 예약 정보를 확인하고 결제로 넘어가기 위한 페이지 출력
     */
    @GetMapping("/{id}")
    public String resCart(@PathVariable int id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        // 예약 정보 가져오기
        Map<String, Reservation> reservation = reservationService.getResInfo(id, loginMember.getId());
        // 이미 결제 시도된 예약인지 확인
        int count = paymentService.checkAlreadyPayment(id);
        // 예약이 없거나 이미 결제된 경우, 카페 리스트로 리다이렉트
        if (reservation == null || reservation.isEmpty() || count != 0) {
            return "redirect:/cafe/list";
        }
        model.addAttribute("reservation", reservation);
        return "common/cafe/reservation";
    }

    /**
     * 예약 취소
     * @param id 예약아이디
     * @param loginMember 로그인된 회원
     * @param model 모델 객체
     * @return 버튼을 클릭시 예약 취소 후 카페 리스트로 리다이렉트
     */
    @GetMapping("/cancle/{id}")
    public String CancleRes(@PathVariable int id, @SessionAttribute Member loginMember,Model model){
        reservationService.delete(id,loginMember.getId());
        return "redirect:/cafe/list";
    }

    /**
     * 결제 정보 등록 페이지
     * @param id 예약 아이디
     * @param request 리퀘스트
     * @param model 모델 객체
     * @return 결제 등록페이지 출력
     */
    @GetMapping("/{id}/payment")
    public String payPage(@PathVariable int id, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        Map<String, Reservation> reservation = reservationService.getResInfo(id,loginMember.getId());
        model.addAttribute("reservation", reservation);
        return "common/cafe/payment";
    }


    /**
     * 아임포트 API 를 통한 결제 실행
     * @param id 예약 아이디
     * @param loginMember 로그인된 멤버 정보
     * @param payment 결제정보
     * @param paymentType 결제 타입
     * @param payStatus 결제 성공,실패 여부
     * @param payName 결제자명
     * @param payEmail 결제자 이메일
     * @param payPhoneNumber 결제자 전화번호
     * @param model 모델 객체
     * @return 결제 실패시 카페 디테일로 리다이렉트, 성공시 결제 성공 페이지로 이동
     */
    @PostMapping("/{id}/payment")
    public String doPayment(
            @PathVariable("id") int id,
            @SessionAttribute Member loginMember,
            @ModelAttribute Payment payment,
            @RequestParam("paymentType") String paymentType,
            @RequestParam("payStatus") String payStatus,
            @RequestParam("payName") String payName,
            @RequestParam("payEmail") String payEmail,
            @RequestParam("payPhoneNumber") String payPhoneNumber,
            Model model
    ) {
        Map<String, Reservation> reservation = reservationService.getResInfo(id,loginMember.getId());
        model.addAttribute("reservation", reservation);

        // Payment 객체 설정
        payment.setReservationId(id);
        payment.setPaymentType(paymentType);
        payment.setPayStatus(payStatus);
        payment.setPayName(payName);
        payment.setPayEmail(payEmail);
        payment.setPayPhoneNumber(payPhoneNumber);

        // paymentService로 Payment 객체 전달하여 처리
        paymentService.payment(payment);

        return "redirect:/reservation/payment/success";
    }

    /**
     * 아임포트 API에서 결제 실패시 RESERVATION 에서 예약 상태 CANCEL로 변경 됨과 동시에 카페 디테일로 이동
     * @param reservationId 예약 아이디
     * @param model 모델 객체
     * @return 예약 취소 상태 변경 
     */
    @DeleteMapping("/{id}/payment")
    @ResponseBody
    public Object cancelReservation(@RequestBody int reservationId, Model model) {
        reservationService.cancelReservation(reservationId);
        return "success";
    }

    /**
     * 결제 성공시 이동하는 결제 페이지
     * @param model 모델 객체
     * @return 결제 성공페이지
     */
    @GetMapping("/payment/success")
    public String paymentSuccess(Model model) {
        return "common/cafe/payment_success";
    }

}
