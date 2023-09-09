package com.vj.lets.web.support.controller;

import com.vj.lets.domain.support.dto.Faq;
import com.vj.lets.domain.support.service.FaqService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * faq 요청을 처리하는 세부 컨트롤러 구현 클래스
 *
 * @author 이한솔
 * @version 1.0
 * @since 2023. 9. 4.
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/support")
public class FaqController {

    private final FaqService faqService;

    @GetMapping("/help")
    public String help(Model model) {
        return "common/support/help";
    }

    @GetMapping("/faq")
    public String faqList(Model model) {

        Faq faq = faqService.findById(49);
        Faq faq2 = faqService.findById(50);
        Faq faq3 = faqService.findById(51);
        Faq faq4 = faqService.findById(52);
        Faq faq5 = faqService.findById(53);
        Faq faq6 = faqService.findById(54);
        Faq faq7 = faqService.findById(55);
        Faq faq8 = faqService.findById(62);
        Faq faq9 = faqService.findById(56);
        Faq faq10 = faqService.findById(57);
        Faq faq11 = faqService.findById(58);
        Faq faq12 = faqService.findById(59);
        Faq faq13 = faqService.findById(60);
        Faq faq14 = faqService.findById(61);
        Faq faq15 = faqService.findById(63);
        Faq faq16 = faqService.findById(64);
        Faq faq17 = faqService.findById(65);
        Faq faq18 = faqService.findById(66);

        model.addAttribute("study1",faq);
        model.addAttribute("study2",faq2);
        model.addAttribute("study3",faq3);
        model.addAttribute("board1",faq4);
        model.addAttribute("board2",faq5);
        model.addAttribute("board3",faq6);
        model.addAttribute("register1",faq7);
        model.addAttribute("register2",faq8);
        model.addAttribute("reservation1",faq9);
        model.addAttribute("reservation2",faq10);
        model.addAttribute("payment1",faq11);
        model.addAttribute("payment2",faq12);
        model.addAttribute("cancel1",faq13);
        model.addAttribute("cancel2",faq14);
        model.addAttribute("member1",faq15);
        model.addAttribute("member2",faq16);
        model.addAttribute("member3",faq17);
        model.addAttribute("member4",faq18);


        return "common/support/faq";
    }

}
