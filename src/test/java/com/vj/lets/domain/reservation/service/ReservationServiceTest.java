package com.vj.lets.domain.reservation.service;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.reservation.dto.Reservation;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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
        log.info("예약 정보: {}", reserve);
        assertThat(reserve).isNotNull();
    }


    @Test
    @DisplayName("예약 아이디로 예약 정보 출력")
    void findByIdTest() {
        // given
        int resId = 1;
        // when
        Reservation reservation = reservationService.findById(resId);
        // then
        log.info("예약아이디로 예약 정보:{}", reservation);
        assertThat(reservation).isNotNull();
    }

    @Test
    void getReservationListTest() {
        // given
        // when
        List<Reservation> list = reservationService.getReservationList();
        // then
        log.info("예약 전체 리스트:{}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void getMemberResListTest() {
        // given
        int memberId = 21;
        // when
        List<Reservation> list = reservationService.getMemberResList(memberId);
        // then
        log.info("해당 회원의 전체 예약 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void getCafeResListTest() {
        // given
        int cafeId = 3;
        // when
        List<Reservation> list = reservationService.getCafeResList(cafeId);
        // then
        log.info("카페의 전체 예약 리스트 : {}", list);
        assertThat(list).isNotNull();
    }

    @Test
    @DisplayName("달마다 예약 건 수 가져오기")
    void findCountMonthResTest() {
        // given
        int cafeId= 1;
        // when
        List<Map<String, Object>> monthRes = reservationService.getCountMonthRes(cafeId);
        // then
        log.info("달마다 총 예약 수:{}", monthRes);
        assertThat(monthRes).isNotNull();
    }

    @Test
    void findTotalDataTest() {
        // given
        int cafeId = 1;
        // when
        List<Map<String, Reservation>> reserveData = reservationService.getTotalData(cafeId);
        // then
        log.info("호스트의 모든 예약 데이터:{}", reserveData);
        assertThat(reserveData).isNotNull();
    }

    @Test
    void getResInfoTest() {
        // given
        int findId = 2;
        // when
        Map<String ,Reservation> reservation = reservationService.getResInfo(findId);
        // then
        log.info("예약 검색 완료:{}",reservation);
        assertThat(reservation).isNotNull();
    }
}