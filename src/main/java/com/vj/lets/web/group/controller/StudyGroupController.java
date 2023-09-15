package com.vj.lets.web.group.controller;

import com.vj.lets.domain.group.dto.*;
import com.vj.lets.domain.group.service.StudyGroupService;
import com.vj.lets.domain.location.dto.SiGunGu;
import com.vj.lets.domain.location.service.SiGunGuService;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.service.MemberService;
import jakarta.websocket.server.PathParam;
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
    private final SiGunGuService siGunGuService;
    private final MemberService memberService;

    /**
     * 스터디 전체 리스트 화면 출력
     *
     * @param keyword 검색 키워드
     * @param subject 검색 주제
     * @param model
     * @return 스터디 리스트 화면
     * @author VJ특공대 이희영
     */
    @GetMapping("")
    public String studyGroup(@PathParam("keyword") String keyword, @PathParam("subject") String subject, Model model) {
        Search search = null;

        if (subject != null) {
            log.info("전체 확인 : {}", subject);
            String changedSubject = subjectChange(subject);
            search = Search.builder()
                    .keyword(keyword)
                    .subject(changedSubject)
                    .build();
        } else {
            search = Search.builder()
                    .keyword(keyword)
                    .build();
        }

        List<Map<String, Object>> studyGroupList = studyGroupService.getStudyGroupList(search);
        List<StudyGroup> newStudyList = studyGroupService.getNewStudyList();
        model.addAttribute("studyGroupList", studyGroupList);
        model.addAttribute("newStudyList", newStudyList);

        return "common/group/group_list";
    }

    /**
     * 스터디 그룹 상세보기
     *
     * @param id          스터디 그룹 아이디
     * @param loginMember 로그인 회원 정보
     * @param model
     * @return 스터디 그룹 상세
     * @author VJ특공대 이희영
     */
    @GetMapping("/{id}")
    public String readGroup(@PathVariable int id, @SessionAttribute Member loginMember, Model model) {
        GroupMemberList groupMember = null;
        List<Map<String, Object>> memberList = null;
        List<Map<String, Object>> contactList = null;

        Map<String, Object> studyGroup = studyGroupService.viewStudyGroup(id);
        if (studyGroupService.isGroupMember(loginMember.getId(), id) != null) {
            groupMember = studyGroupService.isGroupMember(loginMember.getId(), id);
        }

        if (studyGroupService.isGroupMember(loginMember.getId(), id) != null && studyGroupService.isGroupMember(loginMember.getId(), id).getPosition().equals("팀장")) {
            memberList = studyGroupService.findByAllMember(id);
            contactList = studyGroupService.findByAllRegist(id);

        }
        Member member = memberService.getMember(loginMember.getId());

        model.addAttribute("member", member);
        model.addAttribute("studyGroup", studyGroup);
        model.addAttribute("groupMember", groupMember);
        model.addAttribute("memberList", memberList);
        model.addAttribute("contactList", contactList);

        return "common/group/mygroup";
    }

    /**
     * 내 스터디 리스트 조회 화면
     *
     * @param model
     * @return 가입한 스터디 그룹 리스트
     * @author VJ특공대 이희영
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
     * @author VJ특공대 이희영
     */
    @PostMapping("/create")
    public String createGroup(@ModelAttribute CreateForm createForm, @SessionAttribute Member loginMember, Model model) {
        String selectedSubject = createForm.getSubject();
        String subject = subjectChange(selectedSubject);

        StudyGroup studyGroup = StudyGroup.builder()
                .name(createForm.getName())
                .totalCount(createForm.getTotalCount())
                .imagePath(createForm.getImagePath())
                .subject(subject)
                .build();

        int studyGroupId = studyGroupService.createStudyGroup(studyGroup, loginMember.getId(), createForm.getSiGunGuName());
        return "redirect:/group/" + studyGroupId;
    }

    /**
     * 스터디 그룹 정보 수정
     *
     * @param createForm 정보 수정 Form에서 입력된 객체
     * @param id         스터디 그룹 아이디
     * @param model
     * @return 스터디 그룹 상세 화면
     * @author VJ특공대 이희영
     */
    @PostMapping("/update/{id}")
    public String updateGroup(@ModelAttribute CreateForm createForm, @PathVariable int id, Model model) {
        String siGunGuName = createForm.getSiGunGuName();
        SiGunGu siGunGu = siGunGuService.findById(siGunGuName);

        String selectedSubject = createForm.getSubject();
        String subject = subjectChange(selectedSubject);

        StudyGroup studyGroup = StudyGroup.builder()
                .id(id)
                .name(createForm.getName())
                .totalCount(createForm.getTotalCount())
                .imagePath(createForm.getImagePath())
                .subject(subject)
                .siGunGuId(siGunGu.getId())
                .build();

        studyGroupService.editStudyGroup(studyGroup);
        return "redirect:/group/{id}";
    }

    /**
     * 스터디 그룹 삭제
     *
     * @param id    스터디 그룹 아이디
     * @param model
     * @return 내 스터디 그룹 리스트 화면
     * @author VJ특공대 이희영
     */
    @PostMapping("/delete/{id}")
    public String deleteGroup(@PathVariable int id, Model model) {
        studyGroupService.deleteStudyGroup(id);

        return "redirect:/group/mygroup";
    }

    /**
     * 스터디 그룹 가입
     *
     * @author VJ특공대 이희영
     * @param id 스터디 그룹 아이디
     * @param loginMember 로그인 멤버
     * @param model
     * @return 스터디 그룹 상세 화면
     */
    @PostMapping("/join/{id}")
    public String joinGroup(@PathVariable int id, @SessionAttribute Member loginMember, Model model) {
        studyGroupService.registerStudy(loginMember.getId(), id);

        return "redirect:/group/{id}";
    }

    /**
     * 스터디 그룹 생성 및 수정 기능에서 사용할 스터디 그룹 주제 변환 기능
     *
     * @param selectedSubject Form에서 선택된 스터디 그룹 주제 옵션
     * @return DB에 입력될 스터디 그룹 주제
     * @author VJ특공대 이희영
     */
    private String subjectChange(String selectedSubject) {
        selectedSubject = switch (selectedSubject) {
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

        return selectedSubject;
    }
}