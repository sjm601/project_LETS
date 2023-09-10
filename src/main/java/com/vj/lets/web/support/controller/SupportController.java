package com.vj.lets.web.support.controller;

import com.vj.lets.domain.support.dto.Faq;
import com.vj.lets.domain.support.dto.FaqCategory;
import com.vj.lets.domain.support.service.FaqService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
@Slf4j
public class SupportController {

    private final FaqService faqService;

    @GetMapping("/help")
    public String help(Model model) {
        List<FaqCategory> categoryList = faqService.getFaqCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "common/support/help";
    }

    @GetMapping("/faq")
    public String faqList(Model model) {
        List<FaqCategory> categoryList = faqService.getFaqCategoryList();
        model.addAttribute("categoryList", categoryList);

        Map<String, List<Faq>> faqListMap = faqService.getFaqList();
        model.addAttribute("faqListMap", faqListMap);

        return "common/support/faq";
    }

    @GetMapping("/contact")
    public String contactView(Model model) {

        return "common/support/contact";
    }

}
