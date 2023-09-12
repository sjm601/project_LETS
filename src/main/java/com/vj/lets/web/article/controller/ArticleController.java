package com.vj.lets.web.article.controller;

import com.vj.lets.domain.article.dto.Article;
import com.vj.lets.domain.article.dto.PageParams;
import com.vj.lets.domain.article.dto.Pagination;
import com.vj.lets.domain.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/{page}")
    public String articleList(@PathVariable String page, Model model) {
        List<Article> list = articleService.findAll();
        model.addAttribute("list", list);
        int count = articleService.getCountAll(null);
        if (page == null || page.equals("")) {
            page = "1";
        }
        int selectPage = Integer.parseInt(page);
        PageParams pageParams = PageParams.builder()
                .elementSize(ELEMENT_SIZE)
                .pageSize(PAGE_SIZE)
                .requestPage(selectPage)
                .rowCount(count)
                .keyword(null)
                .build();
        Pagination pagination = new Pagination(pageParams);
        model.addAttribute(pagination);
        List<Article> articleList = articleService.findByPage(pageParams);
        model.addAttribute("articleList", articleList);
        return "common/group/mygroup";
    }

    //게시글 등록
    @PostMapping("/create")
    public String create (@ModelAttribute Article article, Model model) {
        articleService.create(article);
        return "redirect:article/1";
    }

    // 게시글 수정
    @PostMapping("/update/{id}")
    public String update(@PathVariable int id ,@ModelAttribute Article article, Model model) {
        Article targetArticle = articleService.findById(id);
        model.addAttribute("article", targetArticle);
        String title = targetArticle.getTitle();
        String content = targetArticle.getContent();
//        log.info("입력값 : {}", article);
        article.setTitle(title);
        article.setContent(content);
//        log.info("업데이트 : {}", article);
        articleService.update(article);
        return "redirect:/article/{id}";

    }
        //게시글 삭제
        @PostMapping("")
        public String delete(@PathVariable int id, @ModelAttribute Article article,
                             Model model) {
            List<Article> articleList = articleService.findAll();
            model.addAttribute("list", articleList);
            Article targetArticle = articleService.findById(id);
            model.addAttribute("article", targetArticle);
                articleService.delete(id);
                return "redirect:";
        }


}
