package com.vj.lets.web.group.controller;

import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.group.dto.StudyGroup;
import com.vj.lets.domain.group.service.StudyGroupService;
import com.vj.lets.domain.member.dto.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 스터디 그룹 컨트롤러
 *
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

    /**
     * 스터디 전체 리스트 화면 출력
     * @param model
     * @return 스터디 리스트 화면
     */
    @GetMapping("")
    public String studyGroup(Model model) {
        List<Map<String, Object>> StudyGroupList = studyGroupService.getStudyGroupList();

        model.addAttribute("studyGroupList", StudyGroupList);

        return "common/group/group_list";
    }

    /**
     * 스터디 상세보기
     * @param id 스터디 그룹 아이디
     * @param loginMember 로그인 회원 정보
     * @param model
     * @return 스터디 그룹 상세
     */
    @GetMapping("/{id}")
    public String readGroup(@PathVariable int id, @SessionAttribute Member loginMember, Model model) {
        GroupMemberList groupMemberList = null;

        Map<String, Object> studyGroup = studyGroupService.findStudyGroup(id);
        if (studyGroupService.isMember(loginMember.getId(), id) != null) {
            groupMemberList = studyGroupService.isMember(loginMember.getId(), id);
        }

        model.addAttribute("studyMember", groupMemberList);
        model.addAttribute("studyGroup", studyGroup);

        return "common/group/mygroup";
    }

    /**
     * 내 스터디 리스트 조회 화면
     * @param model
     * @return 내 스터디 리스트
     */
    @GetMapping("/mygroup")
    public String myGroup(@SessionAttribute Member loginMember, Model model) {
        List<Map<String, Object>> myStudyList= studyGroupService.myGroupList(loginMember.getId());

        model.addAttribute("myStudyList", myStudyList);

        return "common/group/mygroup_list";
    }

    /**
     * 스터디 그룹 생성
     * @param loginMember 로그인 회원 정보
     * @param model
     * @return 스터디 그룹 상세
     */
    @PostMapping("/create")
    public String createGroup(@ModelAttribute StudyGroup studyGroup, @SessionAttribute Member loginMember, Model model) {
//        studyGroupService.createStudy();
        log.info("123123123123 : {}", studyGroup);
        return "redirect:/group/mygroup/";
    }
}