package com.vj.lets.domain.support.util;

import lombok.Getter;

/**
 * 입점 신청 상태 enum 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-23 (토)
 */
@Getter
public enum ContactStatus {
    HOLD("hold"), APPROVE("approve"), REFUSE("refuse");

    private final String status;

    private ContactStatus(String status) {
        this.status = status;
    }

}
