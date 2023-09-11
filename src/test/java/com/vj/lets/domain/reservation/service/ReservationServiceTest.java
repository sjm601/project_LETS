package com.vj.lets.domain.reservation.service;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.reservation.dto.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 예약 서비스 테스트
 * @author 박상훈
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@SpringBootTest
@Slf4j
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;
    @Test
    @Transactional
    @DisplayName("예약 하기 테스트")
    void reserveTest() {
        // given
        Reservation reserve = Reservation.builder()
                .id(1)
                .bookingDate("2023-09-11")
                .startTime(11)
                .endTime(13)
                .headCount(4)
                .memberId(1)
                .cafeId(1)
                .roomId(3)
                .build();
        // when
        reservationService.reserve(reserve);
        // then
        log.info("예약 정보: {}",reserve);
        assertThat(reserve).isNotNull();
    }
}