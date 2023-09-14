package com.vj.lets.web.dashboard.controller;

import com.vj.lets.domain.member.dto.EditForm;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.service.MemberService;
import com.vj.lets.domain.reservation.dto.Reservation;
import com.vj.lets.domain.reservation.service.ReservationService;
import com.vj.lets.domain.review.service.ReviewService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

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

    private final MemberService memberService;
    private final ReservationService reservationService;
    private final ReviewService reviewService;

    /**
     * 마이페이지 메인 화면 출력
     *
     * @param model 객체 모델
     * @return 논리적 뷰 이름
     */
    @GetMapping("")
    public String mypageMain(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        Member member =  memberService.getMember(loginMember.getId());

        EditForm editForm = EditForm.builder().build();

        model.addAttribute("member", member);
        model.addAttribute("editForm", editForm);

        return "dashboard/mypage/mypage_dashboard";
    }

    @GetMapping("/reservation")
    public String reservationView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        List<Map<String, Object>> reservationList = reservationService.getMemberResList(loginMember.getId());

        model.addAttribute("reservationList", reservationList);

        return "dashboard/mypage/reservations";
    }

    @GetMapping("/review")
    public String reviewView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        List<Map<String, Object>> reviewList = reviewService.getReviewListByMember(loginMember.getId());
        log.warn("=============={}", reviewList);

        model.addAttribute("reviewList", reviewList);

        return "dashboard/mypage/reviews";
    }

    @GetMapping("/wishlist")
    public String wishlistView(HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        return "dashboard/mypage/wishlists";
    }

}
