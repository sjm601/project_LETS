package com.vj.lets.web.group.controller;

import com.vj.lets.domain.group.dto.CreateForm;
import com.vj.lets.domain.group.dto.GroupMemberList;
import com.vj.lets.domain.group.dto.Search;
import com.vj.lets.domain.group.dto.StudyGroup;
import com.vj.lets.domain.group.service.StudyGroupService;
import com.vj.lets.domain.member.dto.Member;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.AbstractDocument;
import java.util.List;
import java.util.Map;

/**
 * 스터디 그룹 컨트롤러
 *
 * @author VJ특공대 이희영
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
     *
     * @param model
     * @return 스터디 리스트 화면
     */
    @GetMapping("")
    public String studyGroup(@PathParam("keyword") String keyword, Model model) {

        Search search = Search.builder()
                .keyword(keyword)
                .build();

        List<Map<String, Object>> studyGroupList = studyGroupService.getStudyGroupList(search);
        log.info("스터디 리스트 : {}", studyGroupList);
        model.addAttribute("studyGroupList", studyGroupList);

        return "common/group/group_list";
    }

    /**
     * 스터디 그룹 상세보기
     *
     * @param id          스터디 그룹 아이디
     * @param loginMember 로그인 회원 정보
     * @param model
     * @return 스터디 그룹 상세
     */
    @GetMapping("/{id}")
    public String readGroup(@PathVariable int id, @SessionAttribute Member loginMember, Model model) {
        GroupMemberList groupMemberList = null;

        Map<String, Object> studyGroup = studyGroupService.viewStudyGroup(id);
        if (studyGroupService.isGroupMember(loginMember.getId(), id) != null) {
            groupMemberList = studyGroupService.isGroupMember(loginMember.getId(), id);
        }

        model.addAttribute("studyMember", groupMemberList);
        model.addAttribute("studyGroup", studyGroup);

        return "common/group/mygroup";
    }

    /**
     * 내 스터디 리스트 조회 화면
     *
     * @param model
     * @return 가입한 스터디 그룹 리스트
     */
    @GetMapping("/mygroup")
    public String myGroup(@SessionAttribute Member loginMember, Model model) {
        List<Map<String, Object>> myStudyList = studyGroupService.myGroupList(loginMember.getId());

        model.addAttribute("myStudyList", myStudyList);

        return "common/group/mygroup_list";
    }

    /**
     * 스터디 그룹 생성
     *
     * @param loginMember 로그인 회원 정보
     * @param model
     * @return 스터디 그룹 상세
     */
    @PostMapping("/create")
    public String createGroup(@ModelAttribute CreateForm createForm, @SessionAttribute Member loginMember, Model model) {
        String subjectFormOption = createForm.getSubject();
        subjectFormOption = switch (subjectFormOption) {
            case "stock" -> "주식 / 코인";
            case "econo" -> "경제 / 부동산";
            case "emplo" -> "취업";
            case "inter" -> "면접";
            case "essay" -> "논술";
            case "langu" -> "언어";
            case "it" -> "IT";
            case "certi" -> "자격증";
            case "study" -> "공부";
            default -> "기타";
        };

        StudyGroup studyGroup = StudyGroup.builder()
                .name(createForm.getName())
                .totalCount(createForm.getTotalCount())
                .imagePath(createForm.getImagePath())
                .subject(subjectFormOption)
                .build();

        int studyGroupId = studyGroupService.createStudyGroup(studyGroup, loginMember.getId(), createForm.getSiGunGuName());
        return "redirect:/group/" + studyGroupId;
    }
}