package com.vj.lets.web.home.controller;


import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.util.MemberType;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * 메인 인덱스 페이지 요청 컨트롤러
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {


    /**
     * 현재 로그인 상태 및 로그인 회원 타입에 따른 홈 화면 출력 및 리다이렉트
     *
     * @param request 서블릿 리퀘스트 객체
     * @param model   모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("")
    public String home(@CookieValue(value = "remember", required = false) String rememberEmail,
                       HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        if (loginMember == null) {
            return "index";
        }

        if (loginMember.getType().equals(MemberType.ADMIN.getType())) {
            return "redirect:/admin";
        } else if (loginMember.getType().equals(MemberType.HOST.getType())) {
            return "redirect:/host";
        } else {
            return "redirect:/cafe";
        }

    }

    @PostMapping("")
    public String home2 (@RequestBody String google) {
        log.info(google);
        return "/index";
    }
}
