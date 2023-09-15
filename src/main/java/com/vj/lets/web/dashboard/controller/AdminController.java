package com.vj.lets.web.dashboard.controller;


import com.vj.lets.domain.cafe.service.CafeService;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.service.MemberService;
import com.vj.lets.domain.support.dto.*;
import com.vj.lets.domain.support.service.ContactService;
import com.vj.lets.domain.support.service.FaqService;
import com.vj.lets.domain.support.util.ContactStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 관리자 대시보드 관련 요청 컨트롤러
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class AdminController {

    private final MemberService memberService;
    private final ContactService contactService;
    private final CafeService cafeService;
    private final FaqService faqService;

    /**
     * 관리자 대시보드 메인 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("")
    public String adminMain(Model model) {
        List<Map<String, Object>> countMember = memberService.getCountByRegMonth();
        model.addAttribute("countMember", countMember);

        return "dashboard/admin/admin_dashboard";
    }

    @GetMapping("/contact")
    public String contactView(Model model) {
        List<Contact> contactList = contactService.getContactList();
        model.addAttribute("contactList", contactList);

        return "dashboard/admin/contacts";
    }

    @PostMapping("/contact")
    public String contactApprove(@RequestParam("contactRequest") String contactRequest, @ModelAttribute ContactForm contactForm, Model model) {
        List<Contact> checkContactList = contactService.checkContact(contactForm);
        int contactId = 0;
        for (Contact refuseContact : checkContactList) {
            contactId = refuseContact.getId();
        }

        if (contactRequest.equals(ContactStatus.APPROVE.getStatus())) {
            log.warn("==============={}", contactId);
        } else if (contactRequest.equals(ContactStatus.REFUSE.getStatus())) {
            contactService.editContactRefuse(contactId);
        }

        return "redirect:/admin/contact";
    }

    @GetMapping("/faq/register")
    public String faqRegisterView(Model model) {
        FaqForm faqForm = FaqForm.builder().build();
        List<FaqCategory> categoryList = faqService.getFaqCategoryList();

        model.addAttribute("faqForm", faqForm);
        model.addAttribute("categoryList", categoryList);

        return "dashboard/admin/faq_add";
    }

    @PostMapping("/faq/register")
    public String faqRegister(@ModelAttribute FaqForm faqForm, Model model) {
        Faq faq = Faq.builder()
                .title(faqForm.getTitle())
                .content(faqForm.getContent())
                .categoryId(faqForm.getCategory())
                .build();

        faqService.register(faq);

        return "redirect:/admin/faq";
    }

    @GetMapping("/faq")
    public String faqListView(Model model) {
        List<Map<String, Object>> faqList = faqService.getFaqListForAdmin();
        List<FaqCategory> categoryList = faqService.getFaqCategoryList();
        FaqForm faqForm = FaqForm.builder().build();

        model.addAttribute("faqForm", faqForm);
        model.addAttribute("faqList", faqList);
        model.addAttribute("categoryList", categoryList);

        return "dashboard/admin/faq_list";
    }

    @PostMapping("/faq")
    public String faqEditAndRemove(@ModelAttribute FaqForm faqForm, Model model) {
        log.warn("============{}", faqForm);

        if (faqForm.getType().equals("edit")) {
            Faq faq = Faq.builder()
                    .id(faqForm.getFaqId())
                    .title(faqForm.getTitle())
                    .content(faqForm.getContent())
                    .categoryId(faqForm.getCategory())
                    .build();
            log.warn("============{}", faq);

            faqService.edit(faq);
        } else if (faqForm.getType().equals("remove")) {
            faqService.remove(faqForm.getFaqId());
        }

        return "redirect:/admin/faq";
    }

    @GetMapping("/chart")
    public String chartView(Model model) {
        List<Map<String, Object>> countMember = memberService.getCountByRegMonth();
        List<Map<String, Object>> countCafe = cafeService.getCountByRegMonth();
        List<Map<String, Object>> countGender = memberService.getCountByGender();

        model.addAttribute("countMember", countMember);
        model.addAttribute("countCafe", countCafe);
        model.addAttribute("countGender", countGender);

        return "dashboard/admin/charts";
    }

    @GetMapping("/host")
    public String hostView(Model model) {
        List<Map<String, Object>> cafeList = cafeService.getCafeListForAdmin();

        model.addAttribute("cafeList", cafeList);

        return "dashboard/admin/hosts";
    }

    @GetMapping("/member")
    public String memberView(Model model) {
        List<Member> memberList = memberService.getMemberList();

        model.addAttribute("memberList", memberList);

        return "dashboard/admin/members";
    }

}
