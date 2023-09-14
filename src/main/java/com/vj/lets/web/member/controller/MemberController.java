package com.vj.lets.web.member.controller;

import com.vj.lets.domain.member.dto.EditForm;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.dto.LoginForm;
import com.vj.lets.domain.member.dto.RegisterForm;
import com.vj.lets.domain.member.service.MemberService;
import com.vj.lets.domain.member.util.MemberType;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;

/**
 * 멤버 로그인 및 로그아웃 관련 요청 컨트롤러
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;

    /**
     * 회원 가입 화면 출력
     *
     * @param model 객체 모델
     * @return 논리적 뷰 이름
     */
    @GetMapping("/register")
    public String registerView(Model model) {
        RegisterForm registerForm = RegisterForm.builder().build();
        model.addAttribute("registerForm", registerForm);

        return "common/member/register";
    }

    /**
     * 회원 가입 기능
     *
     * @param registerForm  회원가입 폼 객체
     * @param bindingResult 리절트 객체
     * @param response      서블릿 리스폰스 객체
     * @param model         객체 모델
     * @return 논리적 뷰 이름
     */
    @PostMapping("/register")
    public String register(@Valid @ModelAttribute RegisterForm registerForm, BindingResult bindingResult, HttpServletResponse response, Model model) {
        Member member = Member.builder()
                .email(registerForm.getEmail())
                .name(registerForm.getName())
                .password(registerForm.getPassword())
                .type(MemberType.GUEST.getType())
                .build();

        memberService.register(member);

        try {
            response.setContentType("text/html; charset=utf-8");
            PrintWriter w = response.getWriter();
            w.write("<script>alert('회원가입이 완료되었습니다.');location.href='/member/login';</script>");
            w.flush();
            w.close();
        } catch (Exception e) {
            throw new RuntimeException("오류 메세지");
        }

        return "redirect:/member/login";
    }

    /**
     * 로그인 화면 출력
     *
     * @param rememberEmail 쿠키에 저장된 이메일
     * @param model         모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("/login")
    public String loginView(@CookieValue(value = "remember", required = false) String rememberEmail, Model model) {
        LoginForm loginForm = LoginForm.builder().build();

        if (rememberEmail != null) {
            loginForm.setEmail(rememberEmail);
            loginForm.setRemember(true);
        }

        model.addAttribute("loginForm", loginForm);

        return "common/member/login";
    }

    /**
     * 로그인 기능
     *
     * @param loginForm     로그인 폼 객체
     * @param bindingResult 리절트 객체
     * @param request       서블릿 리퀘스트 객체
     * @param response      서블릿 리스폰스 객체
     * @param model         객체 모델
     * @return 논리적 뷰 이름
     */
    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, Model model) {
        if (bindingResult.hasErrors()) {
            return "redirect:/member/login";
        }

        // 데이터 검증 정상 처리 시
        Member loginMember = memberService.isMember(loginForm.getEmail(), loginForm.getPassword());

        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");

            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.write("<script>alert('아이디 또는 비밀번호가 맞지 않습니다.');location.href='/member/login';</script>");
                w.flush();
                w.close();
            } catch (Exception e) {
                throw new RuntimeException("오류 메세지");
            }
            return "redirect:/member/login";
        }

        // 회원인 경우 세션 생성 및 로그인 아이디 설정
        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginMember);

        // 쿠키 생성
        if (loginForm.getRemember() != null && loginForm.getRemember()) {
            Cookie saveCookie;
            saveCookie = new Cookie("remember", loginMember.getEmail());
            saveCookie.setPath("/");
            saveCookie.setMaxAge(60 * 60 * 24 * 100);
            response.addCookie(saveCookie);
        } else if (loginForm.getRemember() == null || !loginForm.getRemember()) {
            Cookie saveCookie;
            saveCookie = new Cookie("remember", loginMember.getEmail());
            saveCookie.setPath("/");
            saveCookie.setMaxAge(0);
            response.addCookie(saveCookie);
        }

        String redirectURI = (String) session.getAttribute("redirectURI");
        log.warn(redirectURI);
        String uri = redirectURI == null ? "/" : redirectURI;
        return "redirect:" + uri;
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute EditForm editForm, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        EditForm checkForm = memberService.checkEdit(loginMember.getId());

        if(!editForm.equals(checkForm)) {
            Member editMember = Member.builder()
                    .id(loginMember.getId())
                    .password(editForm.getPassword())
                    .name(editForm.getName())
                    .gender(editForm.getGender())
                    .age(editForm.getAge())
                    .phoneNumber(editForm.getPhoneNumber())
                    .imagePath(editForm.getImagePath())
                    .build();

            memberService.editMember(editMember);

        } else {
            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.write("<script>alert('수정 정보가 기존 정보와 이미 일치합니다.');location.href='/';</script>");
                w.flush();
                w.close();
            } catch (Exception e) {
                throw new RuntimeException("오류 메세지");
            }
        }

        String redirectURI = (String) session.getAttribute("redirectURI");
        log.warn(redirectURI);
        String uri = redirectURI == null ? "/" : redirectURI;

        return "redirect:" + uri;
    }

    /**
     * 로그아웃 기능
     *
     * @param session 세션 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
