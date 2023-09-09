package com.vj.lets.domain.support.dto;

/**
* 클래스 설명 : FAQ DTO
* 작성일 : 2023-09-08
* @author : 이한솔
*/

import lombok.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Faq {
    /** FAQ 번호*/
    private int id;
    /** FAQ 제목*/
    private String title;
    /** FAQ 내용*/
    private String content;

}
