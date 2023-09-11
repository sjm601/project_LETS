package com.vj.lets.web.group.controller;

import com.vj.lets.domain.group.dto.StudyGroup;
import com.vj.lets.domain.group.service.StudyGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

/**
 * 스터디 그룹 컨트롤러
 * @author 작성자
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Controller
@RequiredArgsConstructor
@RequestMapping("/group")
@Slf4j
public class StudyGroupController {

    private final StudyGroupService studyGroupService;

    @GetMapping("")
    public String studyGroup(Model model) {
        List<Map<String, Object>> StudyGroupList = studyGroupService.getStudyGroupList();
        log.info("스터디 그룹 리스트 : {}", StudyGroupList);
        model.addAttribute("studyGroupList", StudyGroupList);
        return "common/group/group_list";
    }
}