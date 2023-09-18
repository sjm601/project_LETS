package com.vj.lets.domain.article.mapper;

import com.vj.lets.domain.article.dto.Article;
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
class ArticleMapperTest {

    @Autowired
    private ArticleMapper articleMapper;
    private static final int ELEMENT_SIZE = 5;
    private static final int PAGE_SIZE = 5;

    @Test
    @Transactional
    void CreateTest() {
        // given
        int articleId = 1;
        Article article = Article.builder()
                                .id(5)
                                .title("제목")
                                .content("내용")
                                .build();

        // when
        articleMapper.create(article);

        // then
        log.info("성공");
    }

}