package com.vj.lets.domain.support.mapper;

import com.vj.lets.domain.support.dto.Faq;
import org.apache.ibatis.annotations.Mapper;

/**
 * 클래스 설명 : FAQ Mapper 파일
 * 작성일 : 2023-09-08
 * @author : 이한솔
 */
@Mapper
public interface FaqMapper {
    /** FAQ ID로 찾기*/
    public Faq findById(int id);
}
