package com.vj.lets.web.group.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vj.lets.domain.article.dto.Article;
import com.vj.lets.domain.article.service.ArticleService;
import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.common.web.Pagination;
import com.vj.lets.domain.group.dto.*;
import com.vj.lets.domain.group.service.StudyGroupService;
import com.vj.lets.domain.location.dto.SiGunGu;
import com.vj.lets.domain.location.service.SiGunGuService;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.member.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 스터디 그룹 컨트롤러
 *
 * @author VJ특공대 이희영
 * @author VJ특공대 이한솔
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

    private final ArticleService articleService;
    private static final int ELEMENT_SIZE = 5;
    private static final int PAGE_SIZE = 5;

    /**
     * 스터디 전체 리스트 화면 출력
     *
     * @author VJ특공대 이희영
     * @param keyword 검색 키워드
     * @param subject 검색 주제
     * @param model   모델 인터페이스
     * @return 스터디 리스트 화면
     */
    @GetMapping("")
    public String studyGroup(@PathParam("keyword") String keyword, @PathParam("subject") String subject, Model model) {
        Search search = null;

        if (subject != null) {
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

        List<Map<String, Object>> studyGroupList = studyGroupService.getStudyList(search);
        List<StudyGroup> newStudyList = studyGroupService.getNewStudyList();
        model.addAttribute("studyGroupList", studyGroupList);
        model.addAttribute("newStudyList", newStudyList);

        return "common/group/group_list";
    }

    /**
     * 스터디 그룹 상세보기
     *
     * @author VJ특공대 이희영
     * @author VJ특공대 이한솔
     * @param page        게시글 페이지
     * @param keyword     게시글 검색 키워드
     * @param id          스터디 그룹 아이디
     * @param loginMember 로그인 회원 정보
     * @param model       모델 인터페이스
     * @return 스터디 그룹 상세
     */
    @GetMapping("/{id}")
    public String readGroup(@PathParam("page") String page, @PathParam("keyword") String keyword, @PathVariable int id, @SessionAttribute Member loginMember, Model model) {
        // 이희영
        GroupMemberList groupMember = null;
        List<Map<String, Object>> contactList = null;

        Map<String, Object> studyGroup = studyGroupService.viewStudy(id);
        if (studyGroupService.isGroupMember(loginMember.getId(), id) != null) {
            groupMember = studyGroupService.isGroupMember(loginMember.getId(), id);
        }

        if (studyGroupService.isGroupMember(loginMember.getId(), id) != null && studyGroupService.isGroupMember(loginMember.getId(), id).getPosition().equals("팀장")) {
            contactList = studyGroupService.getStudyContactList(id);
        }
        Member member = memberService.getMember(loginMember.getId());
        
        model.addAttribute("member", member);
        model.addAttribute("studyGroup", studyGroup);
        model.addAttribute("groupMember", groupMember);
        model.addAttribute("contactList", contactList);

        // 이한솔
        int count = articleService.getCountAll(keyword);

        if (page == null || page.isEmpty()) {
            page = "1";
        }

        int selectPage = Integer.parseInt(page);
        PageParams pageParams = PageParams.builder()
                .elementSize(ELEMENT_SIZE)
                .pageSize(PAGE_SIZE)
                .requestPage(selectPage)
                .rowCount(count)
                .keyword(keyword)
                .build();

        Pagination pagination = new Pagination(pageParams);
        model.addAttribute(pagination);

        List<Map<String, Object>> articleList = articleService.findByPage(pageParams);
        model.addAttribute("articleList", articleList);

        List<Integer> articleIds = new ArrayList<>();
        for (Map<String, Object> articleMap : articleList) {
            int articleId = Integer.parseInt(articleMap.get("ID").toString());
            articleIds.add(articleId);
        }

        List<Map<String, Object>> articleComments = articleService.findComment(articleIds);
        model.addAttribute("commentList", articleComments);

        return "common/group/mygroup";
    }

    /**
     * 스터디 그룹 멤버 관리 화면 출력
     *
     * @author VJ특공대 이희영
     * @param id 스터디 그룹 아이디
     * @return 스터디 그룹 회원 리스트
     * @throws JsonProcessingException Json 데이터 예외
     */
    @ResponseBody
    @RequestMapping("/groupSetting/{id}")
    public String groupMemberModal(@PathVariable int id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> memberList = studyGroupService.getStudyMemberList(id);

        return objectMapper.writeValueAsString(memberList);
    }

    /**
     * 스터디 그룹 멤버 탈퇴
     *
     * @author VJ특공대 이희영
     * @param id       스터디 그룹 아이디
     * @param memberId 회원 아이디
     * @return 탈퇴 성공 유무
     */
    @ResponseBody
    @DeleteMapping("/{id}/{memberId}")
    public String removeMemberModal(@PathVariable int id, @PathVariable int memberId) {
        studyGroupService.studySubtractMember(memberId, id);
        GroupMemberList groupMemberList = studyGroupService.isGroupMember(memberId, id);

        if (groupMemberList == null) {
            return "delete-success";
        } else {
            return "delete-failure";
        }
    }

    /**
     * 스터디 그룹 신청 내역 화면 출력
     *
     * @author VJ특공대 이희영
     * @param id 스터디 그룹 아이디
     * @return 스터디 그룹 가입 신청 리스트
     * @throws JsonProcessingException Json 데이터 예외
     */
    @ResponseBody
    @RequestMapping("/contactSetting/{id}")
    public String groupContactModal(@PathVariable int id) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Map<String, Object>> contactList = studyGroupService.getStudyContactList(id);

        return objectMapper.writeValueAsString(contactList);
    }

    /**
     * 스터디 그룹 가입 신청 승인
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @param id           회원 아이디
     * @return 가입 승인 성공 / 실패 메세지
     */
    @ResponseBody
    @PutMapping("/memberContact/{studyGroupId}/{id}")
    public String groupContactApprove(@PathVariable int studyGroupId, @PathVariable int id) {
        studyGroupService.approveStudyContact(id, studyGroupId);
        GroupMemberList groupMemberList = studyGroupService.isGroupMember(id, studyGroupId);

        if (groupMemberList != null) {
            return "approve-success";
        } else {
            return "approve-failure";
        }
    }

    /**
     * 스터디 그룹 가입 신청 거절
     *
     * @author VJ특공대 이희영
     * @param studyGroupId 스터디 그룹 아이디
     * @param id           회원 아이디
     * @return 가입 거절 성공 / 실패 메세지
     */
    @ResponseBody
    @DeleteMapping("/memberContact/{studyGroupId}/{id}")
    public String groupContactRefuse(@PathVariable int studyGroupId, @PathVariable int id) {
        studyGroupService.refuseStudyContact(id, studyGroupId);
        GroupMemberList groupMemberList = studyGroupService.isGroupMember(id, studyGroupId);

        if (groupMemberList == null) {
            return "refuse-success";
        } else {
            return "refuse-failure";
        }
    }

    /**
     * 내 스터디 리스트 조회 화면
     *
     * @author VJ특공대 이희영
     * @param model 모델 인터페이스
     * @return 가입한 스터디 그룹 리스트
     */
    @GetMapping("/mygroup")
    public String myGroup(@SessionAttribute Member loginMember, Model model) {
        List<Map<String, Object>> myStudyList = studyGroupService.getMyStudyList(loginMember.getId());

        model.addAttribute("myStudyList", myStudyList);

        return "common/group/mygroup_list";
    }

    /**
     * 스터디 그룹 생성
     *
     * @author VJ특공대 이희영
     * @param loginMember 로그인 회원 정보
     * @return 스터디 그룹 상세
     */
    @PostMapping("/create")
    public String createGroup(@ModelAttribute CreateForm createForm, @SessionAttribute Member loginMember) {
        String selectedSubject = createForm.getSubject();
        String subject = subjectChange(selectedSubject);

        StudyGroup studyGroup = StudyGroup.builder()
                .name(createForm.getName())
                .totalCount(createForm.getTotalCount())
                .imagePath(createForm.getImagePath())
                .subject(subject)
                .build();

        int studyGroupId = studyGroupService.generateStudy(studyGroup, loginMember.getId(), createForm.getSiGunGuName(), createForm.getSiDoName());
        return "redirect:/group/" + studyGroupId;
    }

    /**
     * 스터디 그룹 정보 수정
     *
     * @author VJ특공대 이희영
     * @param createForm 정보 수정 Form에서 입력된 객체
     * @param id         스터디 그룹 아이디
     * @return 스터디 그룹 상세 화면
     */
    @PostMapping("/update/{id}")
    public String updateGroup(@ModelAttribute CreateForm createForm, @PathVariable int id) {
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

        studyGroupService.editStudy(studyGroup);
        return "redirect:/group/{id}";
    }

    /**
     * 스터디 그룹 삭제
     *
     * @author VJ특공대 이희영
     * @param id 스터디 그룹 아이디
     * @return 내 스터디 그룹 리스트 화면
     */
    @PostMapping("/delete/{id}")
    public String deleteGroup(@PathVariable int id) {
        studyGroupService.removeStudy(id);

        return "redirect:/group/mygroup";
    }

    /**
     * 스터디 그룹 가입
     *
     * @author VJ특공대 이희영
     * @param id          스터디 그룹 아이디
     * @param loginMember 로그인 멤버
     * @return 스터디 그룹 상세 화면
     */
    @ResponseBody
    @PostMapping("/join/{id}")
    @Transactional
    public String joinGroup(@PathVariable int id, @SessionAttribute Member loginMember, @RequestBody String contact) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        ContactInfo contactInfo = objectMapper.readValue(contact, ContactInfo.class);
        Member member = memberService.getMember(loginMember.getId());

        if (member.getGender() == null || member.getAge() == 0) {
            member.setGender(contactInfo.getGender());
            member.setAge(contactInfo.getAge());
            memberService.editMember(member);
        }

        GroupContact groupContact = studyGroupService.contactStudy(loginMember.getId(), id);

        if (groupContact == null) {
            return "contact-success";
        } else {
            return "contact-failure";
        }
    }

    /**
     * 스터디 그룹 생성 및 수정 기능에서 사용할 스터디 그룹 주제 변환 기능
     *
     * @author VJ특공대 이희영
     * @param selectedSubject Form에서 선택된 스터디 그룹 주제 옵션
     * @return DB에 입력될 스터디 그룹 주제
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

    /**
     * 게시글 등록
     *
     * @author VJ특공대 이한솔
     * @param article 게시글
     * @param request HttpServletRequest 객체
     * @param model model 인터페이스
     * @return 스터디 그룹 화면
     */
    @PostMapping("/{id}/article")
    public String create(@ModelAttribute Article article, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();

        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember != null) {
            int memberId = loginMember.getId(); // Member 객체에서 member_id를 가져옵니다.

            // 2. ArticleDto에 로그인한 회원의 ID 설정
            article.setMemberId(memberId);

            // 3. ArticleService를 호출하여 article 테이블에 값을 삽입
            articleService.create(article);
        }
        return "redirect:/group/{id}";
    }

    /**
     * 게시글 삭제
     * 
     * @author VJ특공대 이한솔
     * @param id 게시글 아이디
     * @param article 게시글
     * @param model model 인터페이스
     * @return 스터디 그룹 화면
     */
    @PostMapping("/{id}/article/delete")
    public String delete(@PathParam("id") int id, @ModelAttribute Article article, Model model) {
        Article targetArticle = articleService.findById(id);

        model.addAttribute("article", targetArticle);
        articleService.delete(id);

        return "redirect:/group/{id}";
    }

    /**
     * 게시글 수정
     * 
     * @author VJ특공대 이한솔
     * @param article 게시글
     * @param model model 인터페이스
     * @return 스터디 그룹 화면
     */
    @PostMapping("/{id}/article/update")
    public String update(@ModelAttribute Article article, Model model) {
        model.addAttribute("targetArticle", article);
        
        articleService.update(article);
        model.addAttribute("article", article);
        
        return "redirect:/group/{id}";
    }
}