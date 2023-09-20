package com.vj.lets.web.article.controller;

import com.vj.lets.domain.article.dto.Article;
import com.vj.lets.domain.article.dto.ArticleComment;
import com.vj.lets.domain.article.service.ArticleCommentService;
import com.vj.lets.domain.article.service.ArticleService;
import com.vj.lets.domain.group.util.PageParams;
import com.vj.lets.domain.group.util.Pagination;
import com.vj.lets.domain.member.dto.Member;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
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

    /**
     * 실제 게시글 이미지 경로
     */
    @Value("${article.imageLocation}")
    private String imageLocation;

    /**
     * DB에 입력할 게시글 이미지 경로
     */
    @Value("${article.imageDBPath}")
    private String imageDBPath;


    //게시글 목록 (페이징,검색값,게시글)화면 , 수정 , 등록 , 삭제 다 이 페이지에서 실행
    @GetMapping("/list/{page}")
    public String articleList(@PathVariable String page, @PathParam("keyword") String keyword,Model model) {
        // 페이징 처리 된 게시글 목록들 출력하는 코드
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

        // 댓글 목록들 출력하는 코드
        List<Integer> articleIds = new ArrayList<>();
        for (Map<String, Object> articleMap : articleList) {
            Integer articleId = ((BigDecimal) articleMap.get("ID")).intValue();
            articleIds.add(articleId);
        }
        List<Map<String, Object>> articleComments = articleService.findComment(articleIds);
        model.addAttribute("commentList", articleComments);

        // 최근 게시글 목록 반환하는 코드
        List<Article> recentArticles = articleService.getRecentArticles();
        log.info("최근게시물 = ============================{}",recentArticles);
        model.addAttribute("recentArticleList",recentArticles);
        log.info("담긴 게시물 ------------------{}", recentArticles);
        return "common/group/mygroup";
    }

    //게시글 등록
    @PostMapping("/list/{page}")
    public String create(@ModelAttribute Article article, @PathVariable String page, MultipartFile imagePath, HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        if (loginMember != null) {
            int memberId = loginMember.getId(); // Member 객체에서 member_id를 가져옵니다.

            article.setMemberId(memberId);
            log.info(" 담으려는 아티클 객체{}", article);
            log.info("이미지 패스ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ{}" , imagePath);
            if (!imagePath.isEmpty()) {
                // 이미지 폴더에 저장
                // 업로드 이미지 확장자 가져오기
                String imageExtension = StringUtils.getFilenameExtension(imagePath.getOriginalFilename());
                // 업로드 한 이미지 다운로드 받을 위치 설정
                StringBuilder imageDir = new StringBuilder();
                imageDir.append(imageLocation).append(loginMember.getId()).append(".").append(imageExtension);
                File uploadDir = new File(imageDir.toString());
                // 폴더 없으면 생성
                if (!uploadDir.exists()) {
                    uploadDir.mkdirs();
                }
                imagePath.transferTo(uploadDir);

                StringBuilder imagePathDB = new StringBuilder();
                imagePathDB.append(imageDBPath).append(loginMember.getId()).append(".").append(imageExtension);
                article.setImagePath(imagePathDB.toString());
            }

            log.info("-----------create 전 아티클 {}", article);
            articleService.create(article);
            log.info("생성 후 아티클 {}",article);

        }
        return "redirect:/article/list/1";
    }

    // 게시글 수정
    @PostMapping("/list/{page}/update")
    public String update(@PathVariable String page, @ModelAttribute Article article, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        Article targetArticle = articleService.findById(article.getId());
        int articleMemberId = targetArticle.getMemberId();

        if (loginMember.getId() == articleMemberId) {
        model.addAttribute("targetArticle", article);
        log.info("입력값 : {}", article);
        articleService.update(article);
        model.addAttribute("article", article);
        }else {

            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.write("<script>alert('글쓴이만 수정할 수 있습니다..');location.href='/article/list/1';</script>");
                w.flush();
                w.close();
            } catch (Exception e) {
                throw new RuntimeException("오류 메세지");
            }
        }
        return "redirect:/article/list/"+page;
    }

    //게시글 삭제
    @PostMapping("/list/delete/{articleId}")
    public String delete(@PathVariable("articleId") int articleId, @ModelAttribute Article article, HttpServletRequest request,
                         HttpServletResponse response, Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        Article targetArticle = articleService.findById(articleId);
        int articleMemberId = targetArticle.getMemberId();

        if (loginMember.getId() == articleMemberId) {
        articleService.delete(articleId);
        }else {

            try {
                response.setContentType("text/html; charset=utf-8");
                PrintWriter w = response.getWriter();
                w.write("<script>alert('글쓴이만 삭제할 수 있습니다..');location.href='/article/list/1';</script>");
                w.flush();
                w.close();
            } catch (Exception e) {
                throw new RuntimeException("오류 메세지");
            }
        }
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
