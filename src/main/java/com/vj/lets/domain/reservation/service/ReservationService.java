package com.vj.lets.domain.reservation.service;

import com.vj.lets.domain.reservation.dto.Reservation;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 예약 서비스 클래스
 *
 * @author 박상훈
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
public interface ReservationService {

    /**
     * 예약하기 create
     *
     * @param reservation 예약
     */
    public void reserve(Reservation reservation);

    /**
     * 예약 취소
     *
     * @param id 예약 ID
     * @see com.vj.lets.web.dashboard.controller.MypageController
     */
    public void cancelReservation(int id);

    /**
     * 예약아이디로 예약 찾기
     *
     * @param id
     * @return 해당 예약
     */
    public Reservation findById(int id);

    /**
     * 예약 전체 리스트 조회
     *
     * @return 예약 리스트
     */
    public List<Reservation> getReservationList();

    /**
     * 회원 아이디로 예약 전체 리스트 조회
     *
     * @param memberId 회원 ID
     * @return 예약 목록
     * @see com.vj.lets.web.dashboard.controller.MypageController
     */
    public List<Map<String, Object>> getMemberResList(int memberId);

    //카페 id로 예약 전체 리스트 조회
    public List<Reservation> getCafeResList(int cafeId);

    //매달 총 예약 건수 가져오기
    public List<Map<String, Object>> getCountMonthRes(int cafeId);

    //호스트의 모든 예약 데이터 가져오기
    public List<Map<String, Reservation>> getTotalData(int cafeId);

    //예약에 필요한 예약 정보 가져오기
    public Map<String, Reservation> getResInfo(int id);

    //지금 예약하려는 예약 번호 가져오기

    //지금 예약하려는 페이지 불러오기
    public int getNowRes(int memberId);

    //조작으로 중복예약도 불가
    int checkDuplicateReservation(@Param("roomId") int roomId, @Param("bookingDate") String bookingDate, @Param("startTime") int startTime, @Param("endTime") int endTime);

    //예약 완료된 시간 불러오기
    public List<Reservation> checkDuplicateResTime(int roomId, String  bookingDate);

    //예약 삭제
    public void delete(int id, int memberId);

}
