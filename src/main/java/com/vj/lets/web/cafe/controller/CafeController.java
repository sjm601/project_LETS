package com.vj.lets.web.cafe.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 카페 관련 요청을 처리하는 세부 컨트롤러 구현 클래스
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023. 9. 8.
 */
@Controller
//@RestController
@RequestMapping("/cafe")
@Slf4j
public class CafeController {

    @GetMapping("")
    public String cafeDetail(Model model) {
        return "common/cafe/cafe_main";
    }
    @GetMapping("/list")
    public String cafeList(Model model) {
        return "common/cafe/cafe_list";
    }
    @GetMapping("/detail/{id}")
    public String cafeDetails(@PathVariable("id") int id, Model model) {
        return "common/cafe/cafe_detail";
    }
}
