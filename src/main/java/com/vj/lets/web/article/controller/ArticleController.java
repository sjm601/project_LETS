package com.vj.lets.web.article.controller;

import com.vj.lets.domain.article.dto.Article;
import com.vj.lets.domain.article.dto.PageParams;
import com.vj.lets.domain.article.dto.Pagination;
import com.vj.lets.domain.article.service.ArticleService;
import com.vj.lets.domain.member.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
* 클래스 설명 :  게시글 관련 요청을 처리하는 세부 컨트롤러 구현 클래스
* 작성일 : 2023-09-11
* @author : 이한솔
*/
@Controller
@RequiredArgsConstructor
@RequestMapping("/article")
@Slf4j
public class ArticleController {
    private final ArticleService articleService;
    private static final int ELEMENT_SIZE = 5;
    private static final int PAGE_SIZE = 5;


    //게시글 목록 (페이징,검색값 포함)화면 , 수정 , 등록 , 삭제 다 이 페이지에서 실행
    @GetMapping("/list")
    public String articleList(@PathParam("page") String page, @PathParam("keyword")String keyword, Model model) {
        List<Article> list = articleService.findAll();
        model.addAttribute("list", list);
//        log.info("1페이지 전체 리스트{}", list);
        int count = articleService.getCountAll(keyword);
//        log.info("갯수{}",count);
        if (page == null || page.equals("")) {
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
//        log.info("페이징 검색포함{}",pagination);
        model.addAttribute(pagination);
        List<Article> articleList = articleService.findByPage(pageParams);
//        log.info("페이징 된 리스트{}",articleList);
        model.addAttribute("articleList", articleList);
        return "common/group/mygroup";
    }

    //게시글 등록
    @PostMapping("/list")
    public String create (@ModelAttribute Article article, HttpServletRequest request, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember != null) {
            int memberId = loginMember.getId(); // Member 객체에서 member_id를 가져옵니다.

            // 2. ArticleDto에 로그인한 회원의 ID 설정
            article.setMemberId(memberId);

            // 3. ArticleService를 호출하여 article 테이블에 값을 삽입
            articleService.create(article);

            return "redirect:/article/list";
        }
        return "redirect:/artcile/list";
    }
    // 게시글 수정
    @PostMapping("/list/update")
    public String update(@PathParam("articleId") int id ,@ModelAttribute Article article, Model model) {
        Article targetArticle = articleService.findById(id);
        model.addAttribute("article", targetArticle);
        String title = article.getTitle();
        String content = article.getContent();
//        log.info("입력값 : {}", article);
        targetArticle.setTitle(title);
        targetArticle.setContent(content);
//        log.info("업데이트 : {}", targetArticle);
        articleService.update(targetArticle);
        return "redirect:/article/list";

    }
        //게시글 삭제
        @PostMapping("/list/delete")
        public String delete(@PathParam("articleId") int id, @ModelAttribute Article article,
                             Model model) {
            List<Article> articleList = articleService.findAll();
            model.addAttribute("list", articleList);
            Article targetArticle = articleService.findById(id);
            model.addAttribute("article", targetArticle);
                articleService.delete(id);
                return "redirect:/article/list";
        }


}
