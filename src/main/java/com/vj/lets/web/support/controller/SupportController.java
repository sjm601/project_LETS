package com.vj.lets.web.support.controller;

import com.vj.lets.domain.support.dto.Contact;
import com.vj.lets.domain.support.dto.ContactForm;
import com.vj.lets.domain.support.dto.Faq;
import com.vj.lets.domain.support.dto.FaqCategory;
import com.vj.lets.domain.support.service.ContactService;
import com.vj.lets.domain.support.service.FaqService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

/**
 * 고객지원 관련 요청 컨트롤러
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/support")
public class SupportController {

    private final FaqService faqService;
    private final ContactService contactService;

    /**
     * 고객 지원 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("/help")
    public String help(Model model) {
        List<FaqCategory> categoryList = faqService.getFaqCategoryList();

        model.addAttribute("categoryList", categoryList);

        return "common/support/help";
    }

    /**
     * FAQ 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("/faq")
    public String faqList(Model model) {
        List<FaqCategory> categoryList = faqService.getFaqCategoryList();
        Map<String, List<Faq>> faqListMap = faqService.getFaqList();

        model.addAttribute("categoryList", categoryList);
        model.addAttribute("faqListMap", faqListMap);

        return "common/support/faq";
    }

    /**
     * 입점 신청 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("/contact")
    public String contactView(Model model) {
        ContactForm contactForm = ContactForm.builder().build();

        model.addAttribute("contactForm", contactForm);

        return "common/support/contact";
    }

    /**
     * 입점 신청 기능
     *
     * @param contactForm 입점 신청 폼
     * @param response    서블릿 리스폰스 객체
     * @param model       모델 객체
     * @return 논리적 뷰 이름
     */
    @PostMapping("/contact")
    public String contactRegister(@ModelAttribute ContactForm contactForm, HttpServletResponse response, Model model) {
        List<Contact>checkContact = contactService.checkContact(contactForm);

        if (checkContact == null || checkContact.isEmpty()) {
            Contact regiContact = Contact.builder()
                    .email(contactForm.getEmail())
                    .name(contactForm.getName())
                    .cafeName(contactForm.getCafeName())
                    .phoneNumber(contactForm.getPhoneNumber())
                    .address(contactForm.getAddress())
                    .message(contactForm.getMessage())
                    .businessNumber(contactForm.getBusinessNumber())
                    .build();
            contactService.register(regiContact);

            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.write("<script>alert('신청이 완료되었습니다.'); location.href='/';</script>");
                w.flush();
                w.close();
            } catch (Exception e) {
                throw new RuntimeException("오류 메세지");
            }
            return "redirect:/";
        } else {
            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.write("<script>alert('해당 신청 정보로 이미 신청 되었거나 이미 입점된 신청 정보 입니다.'); location.href='/support/contact';</script>");
                w.flush();
                w.close();
            } catch (Exception e) {
                throw new RuntimeException("오류 메세지");
            }
            return "redirect:/support/contact";
        }

    }

}
