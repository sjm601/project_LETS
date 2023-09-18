package com.vj.lets.web.article.controller;

import com.vj.lets.domain.article.dto.Article;
import com.vj.lets.domain.article.dto.ArticleComment;
import com.vj.lets.domain.article.dto.PageParams;
import com.vj.lets.domain.article.dto.Pagination;
import com.vj.lets.domain.article.service.ArticleCommentService;
import com.vj.lets.domain.article.service.ArticleService;
import com.vj.lets.domain.member.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * 클래스 설명 :  게시글 관련 요청을 처리하는 세부 컨트롤러 구현 클래스
 * 작성일 : 2023-09-11
 *
 * @author : 이한솔
 */



@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    private final ArticleCommentService articleCommentService;
    private static final int ELEMENT_SIZE = 5;
    private static final int PAGE_SIZE = 5;


    //게시글 목록 (페이징,검색값,게시글)화면 , 수정 , 등록 , 삭제 다 이 페이지에서 실행
    @GetMapping("/list/{page}")
    public String articleList(@PathVariable String page, @PathParam("keyword") String keyword,@PathParam("articleId") Integer articleId ,Model model) {
        int count = articleService.getCountAll(keyword);
//        log.info("갯수{}",count);
        if (page == null || page.isEmpty()) {
            page = "1";
        }
        int selectPage = Integer.parseInt(page);
//        log.info("페이지 : {}" , selectPage);
        PageParams pageParams = PageParams.builder()
                .elementSize(ELEMENT_SIZE)
                .pageSize(PAGE_SIZE)
                .requestPage(selectPage)
                .rowCount(count)
                .keyword(keyword)
                .build();
        Pagination pagination = new Pagination(pageParams);
//        log.info("페이징 검색포함{}",pagination);
        model.addAttribute(pagination);
        List<Map<String, Object>> articleList = articleService.findByPage(pageParams);
        log.info("페이징 된 리스트{}",articleList);
        model.addAttribute("articleList", articleList);
        log.info("모델에 저장된 리스트{}",articleList);
        log.info("제발 아티클 Id{}", articleId);

//        List<Map<String, Object>> articleComment = articleService.findComment(articleId);

        return "common/group/mygroup";
    }

    //게시글 등록
    @PostMapping("/list/{page}")
    public String create(@ModelAttribute Article article,@PathVariable String page, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember != null) {
            int memberId = loginMember.getId(); // Member 객체에서 member_id를 가져옵니다.

            // 2. ArticleDto에 로그인한 회원의 ID 설정
            article.setMemberId(memberId);

            // 3. ArticleService를 호출하여 article 테이블에 값을 삽입
            articleService.create(article);
            log.info("아티클 {}",article);

            return "redirect:/article/list/"+page;
        }
        return "redirect:/article/list/{page}";
    }

    // 게시글 수정
    @PostMapping("/list/{page}/update")
    public String update(@PathVariable String page, @ModelAttribute Article article, Model model) {
        model.addAttribute("targetArticle", article);
        log.info("입력값 : {}", article);
        articleService.update(article);
        model.addAttribute("article", article);
        return "redirect:/article/list/"+page;

    }

    //게시글 삭제
    @PostMapping("/list/delete")
    public String delete(@PathParam("ID") int id, @ModelAttribute Article article,
                         Model model) {
        Article targetArticle = articleService.findById(id);
        model.addAttribute("article", targetArticle);
        articleService.delete(id);
        return "redirect:/article/list/1";
    }


    //댓글 등록
    @PostMapping("/list/{page}/{articleId}/commentCreate")
    public String commentCreate(@ModelAttribute ArticleComment articleComment,@PathVariable String page, @PathVariable int articleId ,HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
//        log.info("받은 정보들{}",articleId);
        if (loginMember != null) {
            int memberId = loginMember.getId();
//            log.info("멤버아이디 -------------{}",memberId);
            articleComment.setMemberId(memberId);
            articleComment.setArticleId(articleId);
            articleCommentService.create(articleComment);
//            log.info("댓글 만들어졌냐{}",articleComment);

            return "redirect:/article/list/"+page;
        }
        return "redirect:/article/list/"+page;
    }

    //댓글 삭제
    @PostMapping("/list/comment/delete")
    public String delete(@PathParam("commentId") int id, @ModelAttribute ArticleComment articleComment, Model model) {
        log.info("코멘트 아이디ㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣㅣ{}", id);
        ArticleComment targetComment = articleCommentService.findById(id);
        model.addAttribute("articleComment", targetComment);
        articleCommentService.delete(id);
        return "redirect:/article/list/1";
    }

}
