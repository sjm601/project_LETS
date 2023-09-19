package com.vj.lets.domain.article.service;

import com.vj.lets.domain.article.dto.ArticleComment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * 스터디 그룹 서비스 테스트
 *
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-18 (월)
 */
@SpringBootTest
@Slf4j
class ArticleCommentServiceTest {

    @Autowired
    private ArticleCommentService articleCommentService;


    @Test
    @Transactional
    void createTest() {
        // given
        int articleId = 1;

        ArticleComment articleComment = ArticleComment.builder()
                .id(1)
                .content("댓글내용테스트")
                .articleId(articleId)
                .memberId(27)
                .build();

        // when
        articleCommentService.create(articleComment);

        // then
        log.info("나는 안될거야");
    }


}