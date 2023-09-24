package com.vj.lets.domain.reservation.util;

import lombok.Getter;

/**
 * 예약 히스토리 enum 클래스
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-15 (금)
 */
@Getter
public enum ReservationStatus {
    COMPLETE("complete"), CANCEL("update"), USE("use");

    private final String status;

    private ReservationStatus(String status) {
        this.status = status;
    }
}
