package com.vj.lets.domain.article.mapper;

import com.vj.lets.domain.article.dto.ArticleComment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

/**
 * 그룹 신청 매퍼 테스트
 * 
 * @author VJ특공대 이한솔
 * @version 1.0
 * @since 2023-09-18 (월)
 */
@SpringBootTest
@Slf4j
class ArticleCommentMapperTest {

    @Autowired
    private ArticleCommentMapper articleCommentMapper;

    @Test
    @Transactional
    void CreateTest() {
        // given
        int articleId = 1;
        ArticleComment articleComment = ArticleComment.builder()
                                                        .id(1)
                                                        .articleId(articleId)
                                                        .content("생성테스트")
                                                        .memberId(27)
                                                        .build();

        // when
        articleCommentMapper.create(articleComment);

        // then
        log.info("성공");
    }



}