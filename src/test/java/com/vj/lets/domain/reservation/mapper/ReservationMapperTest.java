package com.vj.lets.domain.reservation.mapper;

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
 * 예약 Mapper 테스트
 *
 * @author 박상훈
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@SpringBootTest
@Slf4j
class ReservationMapperTest {

    @Autowired
    private ReservationMapper reservationMapper;


    @Test
    @DisplayName("예약하기 - 결제 전")
    @Transactional
    void createTest() {
        // given
        Reservation newReservation = Reservation.builder()
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
        reservationMapper.create(newReservation);
        // then
        log.info("예약 완료 : {}",newReservation);
        assertThat(newReservation).isNotNull();
    }

    @Test
    @DisplayName("예약ID로 예약 찾기")
     void findByIdTest() {
        // given
        int findId = 2;
        // when
        Reservation reservation = reservationMapper.findById(findId);
        // then
        log.info("예약 검색 완료:{}",reservation);
        assertThat(reservation).isNotNull();
    }

    @Test
    @DisplayName("모든 예약 리스트 가져오기")
    void findByAllTest() {
        // given
        // when
        List<Reservation> list = reservationMapper.findByAll();
        // then
        log.info("예약 리스트 출력:{}", list);
        assertThat(list).isNotNull();
    }


    @Test
    @DisplayName("멤버ID로 예약 찾기")
    void findByMemberTest() {
        // given
        int memberId = 21;
        // when
        List<Map<String, Object>> memberReservation = reservationMapper.findByMember(memberId);
        // then
        log.info("회원의 예약 목록:{}", memberReservation);
        assertThat(memberReservation).isNotNull();
    }

    @Test
    void findCafeResListTest() {
        // given
        int cafeId = 1;
        // when
        List<Reservation> cafeReservationList = reservationMapper.findCafeResList(cafeId);
        // then
        log.info("카페 예약 목록:{}", cafeReservationList);
        assertThat(cafeReservationList).isNotNull();
    }

    @Test
    @DisplayName("달마다 예약 건 수 가져오기")
    void findCountMonthResTest() {
        // given
        int cafeId = 1;
        // when
        List<Map<String, Object>> monthRes = reservationMapper.findCountMonthRes(cafeId);
        // then
        log.info("달마다 총 예약 수:{}", monthRes);
        assertThat(monthRes).isNotNull();
    }

    @Test
    @DisplayName("호스트의 모든 예약 데이터 가져오기")
    void findTotalDataTest() {
        // given
        int cafeId = 1;
        // when
        List<Map<String, Reservation>> reserveData = reservationMapper.findTotalData(cafeId);
        // then
        log.info("호스트의 모든 예약 데이터:{}", reserveData);
        assertThat(reserveData).isNotNull();
    }

    @Test
    void findResInfoTest() {
        // given
        int findId = 2;
        // when
        Map<String ,Reservation> reservation = reservationMapper.findResInfo(findId);
        // then
        log.info("예약 검색 완료:{}",reservation);
        assertThat(reservation).isNotNull();
    }

    @Test
    void findNowResTest() {
        // given
        int memberId=21;
        // when
        int resId = reservationMapper.findNowRes(memberId);
        // then
        log.info("지금 예약하려는 번호:{}", resId);
        assertThat(resId).isNotNull();
    }


    @Test
    void checkDuplicateReservationTest() {
        // given
        int roomId = 26;
        String bookDate="23/09/26";
        int startTime = 11;
        int endTime = 16;
        // when
        int result = reservationMapper.checkDuplicateReservation(roomId,bookDate,startTime,endTime);
        // then
        log.info("결과:{}",result);
        assertThat(result).isNotNull();
    }

    @Test
    void checkDuplicateResTimeTest() {
        // given
        int roomId = 26;
        String bookDate="23/09/26";
        // when
        List<Map<String, Integer>>  result = reservationMapper.checkDuplicateResTime(roomId,bookDate);
        // then
        log.info("결과:{}",result);
        assertThat(result).isNotNull();
    }
}