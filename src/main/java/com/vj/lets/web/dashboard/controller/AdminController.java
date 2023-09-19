package com.vj.lets.web.dashboard.controller;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.service.CafeService;
import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.common.web.Pagination;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.service.MemberService;
import com.vj.lets.domain.member.util.DefaultPassword;
import com.vj.lets.domain.member.util.MemberType;
import com.vj.lets.domain.support.dto.*;
import com.vj.lets.domain.support.service.ContactService;
import com.vj.lets.domain.support.service.FaqService;
import com.vj.lets.domain.support.util.ContactStatus;
import lombok.RequiredArgsConstructor;
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
public class AdminController {

    private final MemberService memberService;
    private final ContactService contactService;
    private final CafeService cafeService;
    private final FaqService faqService;

    private static final int ELEMENT_SIZE = 3;
    private static final int PAGE_SIZE = 3;

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

    /**
     * 입점 신청 목록 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("/contact")
    public String contactView(@RequestParam(value = "page", required = false) String page,
                              @RequestParam(value = "type", required = false) String type,
                              Model model) {
        if (page == null || page.isBlank()) {
            page = "1";
        }
        if (type == null || type.isBlank()) {
            type = ContactStatus.HOLD.getStatus();
        }
        int selectPage = Integer.parseInt(page);
        int count = contactService.getCountContact(type);
        PageParams pageParams = PageParams.builder()
                .elementSize(ELEMENT_SIZE)
                .pageSize(PAGE_SIZE)
                .requestPage(selectPage)
                .rowCount(count)
                .type(type)
                .build();
        Pagination pagination = new Pagination(pageParams);

        List<Contact> contactList = contactService.getContactList(pageParams);

        ContactForm contactForm = ContactForm.builder().build();

        model.addAttribute("contactList", contactList);
        model.addAttribute("contactForm", contactForm);
        model.addAttribute("pagination", pagination);

        return "dashboard/admin/contacts";
    }

    /**
     * 입점 신청 승인 및 거부 기능
     *
     * @param contactRequest 입점 신청 승인 여부 요청
     * @param contactForm    입점 신청 정보 폼 객체
     * @param model          모델 객체
     * @return 논리적 뷰 이름
     */
    @PostMapping("/contact")
    public String contactApprove(@RequestParam("contactRequest") String contactRequest,
                                 @ModelAttribute ContactForm contactForm,
                                 Model model) {
        List<Contact> checkContactList = contactService.checkContact(contactForm);
        int contactId = 0;
        for (Contact refuseContact : checkContactList) {
            contactId = refuseContact.getId();
        }

        if (contactRequest.equals(ContactStatus.APPROVE.getStatus())) {
            Member member = Member.builder()
                    .email(contactForm.getEmail())
                    .name(contactForm.getName())
                    .password(DefaultPassword.DEFAULT.getPassword())
                    .type(MemberType.HOST.getType())
                    .build();
            Cafe cafe = Cafe.builder()
                    .email(contactForm.getEmail())
                    .name(contactForm.getCafeName())
                    .businessNumber(contactForm.getBusinessNumber())
                    .build();

            contactService.approveContact(contactId, member, cafe);
        } else if (contactRequest.equals(ContactStatus.REFUSE.getStatus())) {
            contactService.refuseContact(contactId);
        }

        return "redirect:/admin/contact";
    }

    /**
     * FAQ 신규 등록 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("/faq/register")
    public String faqRegisterView(Model model) {
        FaqForm faqForm = FaqForm.builder().build();
        List<FaqCategory> categoryList = faqService.getFaqCategoryList();

        model.addAttribute("faqForm", faqForm);
        model.addAttribute("categoryList", categoryList);

        return "dashboard/admin/faq_add";
    }

    /**
     * FAQ 신규 등록 기능
     *
     * @param faqForm FAQ 등록 폼 객체
     * @param model   모델 객체
     * @return 논리적 뷰 이름
     */
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

    /**
     * FAQ 목록 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("/faq")
    public String faqListView(@RequestParam(value = "page", required = false) String page,
                              @RequestParam(value = "type", required = false) String type,
                              Model model) {
        List<FaqCategory> categoryList = faqService.getFaqCategoryList();

        if (page == null || page.isBlank()) {
            page = "1";
        }
        if (type == null || type.isBlank()) {
            type = "all";
        }
        int selectPage = Integer.parseInt(page);
        int count = faqService.getCountFaq(type);
        PageParams pageParams = PageParams.builder()
                .elementSize(ELEMENT_SIZE)
                .pageSize(PAGE_SIZE)
                .requestPage(selectPage)
                .rowCount(count)
                .type(type)
                .build();
        Pagination pagination = new Pagination(pageParams);

        List<Map<String, Object>> faqList = faqService.getFaqListForAdmin(pageParams);

        FaqForm faqForm = FaqForm.builder().build();

        model.addAttribute("faqForm", faqForm);
        model.addAttribute("faqList", faqList);
        model.addAttribute("categoryList", categoryList);
        model.addAttribute("pagination", pagination);


        return "dashboard/admin/faq_list";
    }

    /**
     * FAQ 수정 및 삭제 기능
     *
     * @param faqRequest FAQ 수정 및 삭제 요청
     * @param faqForm    FAQ 폼
     * @param model      모델 객체
     * @return 논리적 뷰 이름
     */
    @PostMapping("/faq")
    public String faqEditAndRemove(@RequestParam("faqRequest") String faqRequest,
                                   @ModelAttribute FaqForm faqForm,
                                   Model model) {
        if (faqRequest.equals("edit")) {
            Faq faq = Faq.builder()
                    .id(faqForm.getFaqId())
                    .title(faqForm.getTitle())
                    .content(faqForm.getContent())
                    .categoryId(faqForm.getCategory())
                    .build();

            faqService.edit(faq);
        } else if (faqRequest.equals("remove")) {
            faqService.remove(faqForm.getFaqId());
        }

        return "redirect:/admin/faq";
    }

    /**
     * 차트 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
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

    /**
     * 현재 입점 목록 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("/host")
    public String hostView(@RequestParam(value = "page", required = false) String page,
                           @RequestParam(value = "type", required = false) String type,
                           Model model) {
        if (page == null || page.isBlank()) {
            page = "1";
        }
        if (type == null || type.isBlank()) {
            type = "latest";
        }
        int selectPage = Integer.parseInt(page);
        int count = cafeService.getCountCafeForAdmin(type);
        PageParams pageParams = PageParams.builder()
                .elementSize(ELEMENT_SIZE)
                .pageSize(PAGE_SIZE)
                .requestPage(selectPage)
                .rowCount(count)
                .type(type)
                .build();
        Pagination pagination = new Pagination(pageParams);

        List<Map<String, Object>> cafeList = cafeService.getCafeListForAdmin(pageParams);

        model.addAttribute("cafeList", cafeList);
        model.addAttribute("pagination", pagination);

        return "dashboard/admin/hosts";
    }

    /**
     * 현재 회원 목록 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("/member")
    public String memberView(Model model) {
        List<Member> memberList = memberService.getMemberList();

        model.addAttribute("memberList", memberList);

        return "dashboard/admin/members";
    }

}
