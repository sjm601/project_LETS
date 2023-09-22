package com.vj.lets.domain.reservation.service;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.reservation.dto.Reservation;
import com.vj.lets.domain.review.service.ReviewService;
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
 *
 * @author 박상훈
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@SpringBootTest
@Slf4j
class ReservationServiceTest {

    @Autowired
    private ReservationService reservationService;
    @Autowired
    private ReviewService reviewService;

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
        List<Map<String, Object>> list = reservationService.getMemberResList(memberId, PageParams.builder().build());
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
        int cafeId = 1;
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
        int memberId = 22;
        // when
        Map<String, Reservation> reservation = reservationService.getResInfo(findId, memberId);
        // then
        log.info("예약 검색 완료:{}", reservation);
        assertThat(reservation).isNotNull();
    }

    @Test
    void checkDuplicateReservationTest() {
        // given
        int roomId = 26;
        String bookDate = "23/09/26";
        int startTime = 11;
        int endTime = 16;
        // when
        int result = reservationService.checkDuplicateReservation(roomId, bookDate, startTime, endTime);
        // then
        log.info("결과:{}", result);
        assertThat(result).isNotNull();
    }

    @Test
    void getCountByResMonthTest() {
        // given
        int cafeId = 2;
        // when
        List<Map<String, Object>> list = reservationService.getCountByResMonth(cafeId);
        // then
        log.info("리스트값:{}", list);
        assertThat(list).isNotNull();
    }


    @Test
    @Transactional
    void cancelReservationTest() {
        // given
        int id = 2;
        // when
        reservationService.cancelReservation(id);
        // then
        log.info("예약 취소된 예약 아이디:{}", id);
        assertThat(id).isNotZero();
    }

    @Test
    void getCountResByMemberTest() {
        // given
        int memberId =2;
        String type = "all";
        // when
        int count = reservationService.getCountResByMember(memberId,type);
        // then
        log.info("카운트:{}",count);
    }


    @Test
    void getCountMonthResTest() {
        // given
        int cafeId = 2;
        // when
        List<Map<String, Object>> list = reservationService.getCountMonthRes(cafeId);
        // then
        log.info("호스트 대시보드 막대 그래프의 Y축과 X축:{}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void getTotalDataTest() {
        // given
        int cafeId = 2;
        // when
        List<Map<String, Reservation>> reserveData = reservationService.getTotalData(cafeId);
        // then
        log.info("호스트 카페에 대한 모든 예약 데이터:{}", reserveData);
        assertThat(reserveData).isNotNull();
    }


    @Test
    void getNowResTest() {
        // given
        int memberId = 21;
        // when
        int resId = reservationService.getNowRes(memberId);
        // then
        log.info("지금 예약하려는 예약 번호:{}", resId);
        assertThat(resId).isNotZero();
    }

    @Test
    void checkDuplicateResTimeTest() {
        // given
        int roomId = 7;
        String bookDate = "2023-09-26";
        // when
        List<Reservation> result = reservationService.checkDuplicateResTime(roomId, bookDate);
        // then
        log.info("9월 26일에 예약되어 있는 시간대 결과:{}", result);
        assertThat(result).isNotNull();
    }

    @Test
    @Transactional
    void deleteTest() {
        // given
        int id = 2;
        int memberId =22;
        // when
        reservationService.delete(id,memberId);
        // then
        log.info("삭제된 예약 아이디:{}", id);
        assertThat(id).isNotZero();
    }

    @Test
    void getMonthlySalesTest() {
        // given
        int cafeId =2;
        // when
        List<Map<String,Object>> result = reservationService.getMonthlySales(cafeId);
        // then
        log.info("결과:{}",result);
        assertThat(result).isNotNull();
    }

    @Test
    void getCountResByHostTest() {
        // given
        int cafeId =2;
        String type = "all";
        // when
        int count = reservationService.getCountResByHost(cafeId,type);
        // then
        log.info("카운트:{}",count);
        assertThat(count).isNotZero();
    }

    @Test
    void getHostResListTest() {
        // given
        int cafeId =2;
        int count = reviewService.getCountByHost(cafeId);
        PageParams pageParams = PageParams.builder()
                .elementSize(2)
                .pageSize(2)
                .requestPage(1)
                .rowCount(count)
                .build();
        // when
        List<Map<String,Object>> list = reservationService.getHostResList(cafeId,pageParams);
        // then
        log.info("카페 아이디에 따른 예약 목록:{}", list);
        assertThat(list).isNotNull();
    }

    @Test
    void getTotalResTest() {
        // given
        int cafeId = 2;
        // when
        int count = reservationService.getTotalRes(cafeId);
        // then
        log.info("총 예약 횟수:{}", count);
        assertThat(count).isNotZero();
    }
}