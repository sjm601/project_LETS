package com.vj.lets.domain.common.web;

import lombok.*;

/**
 * 공통 사용 성별 객체
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-14 (목)
 */
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Builder
public class Gender {

    String male;
    String female;
    String others;

}
