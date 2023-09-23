package com.vj.lets.domain.reservation.service;

import com.vj.lets.domain.common.web.PageParams;
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
     * 특정 회원이 예약한 횟수 조회
     *
     * @param memberId 회원 ID
     * @param type     검색 조건
     * @return 예약 횟수
     * @see com.vj.lets.web.dashboard.controller.MypageController
     */
    public int getCountResByMember(int memberId, String type);

    /**
     * 회원 아이디로 예약 전체 리스트 조회
     *
     * @param memberId 회원 ID
     * @return 예약 목록
     * @see com.vj.lets.web.dashboard.controller.MypageController
     */
    public List<Map<String, Object>> getMemberResList(int memberId, PageParams pageParams);

    /**
     * 카페 id로 예약 전체 리스트 조회
     * @param cafeId 카페 아이디
     * @return 호스트 카페에 대한 전체 예약 리스트
     */
    public List<Reservation> getCafeResList(int cafeId);

    /**
     * 매달 총 예약 목록 수 및 정보 가져오기 (호스트 대시보드 사용)
     * @param cafeId 카페 아이디
     * @return 호스트 대시보드 그래프에서 사용
     */
    public List<Map<String, Object>> getCountMonthRes(int cafeId);

    /**
     * 예약에 관한 총 데이터 출력 (호스트 대시보드 테이블 페이지에서 사용)
     * @param cafeId
     * @return 예약 상태가 cancel 이 아닌 데이터 리스트 출력
     */
    public List<Map<String, Reservation>> getTotalData(int cafeId);

    /**
     * 예약에 필요한 예약 정보 가져오기
     * @param id 예약 아이디
     * @param memberId 멤버 아이디
     * @return 예약 정보 확인 페이지에서 보여줄 목록들 출력
     */
    public Map<String, Reservation> getResInfo(int id, int memberId);


    /**
     * 예약 페이지에서 결제로 이동하기 위해 필요함
     * @param memberId 멤버아이디
     * @return 가장 최근 예약 시도된 예약 아이디
     */
    public int getNowRes(int memberId);

    /**
     * 예약 중복 방지를 위한 카운트
     * @param roomId 룸 ID
     * @param bookingDate 예약일자
     * @param startTime 시작 시간
     * @param endTime 종료 시간
     * @return 카운트가 1보다 클 시 중복
     */
    int checkDuplicateReservation(@Param("roomId") int roomId, @Param("bookingDate") String bookingDate, @Param("startTime") int startTime, @Param("endTime") int endTime);

    /**
     * 예약 중복을 막기 위해 카페 디테일에서 사용
     * @param roomId 룸 ID
     * @param bookingDate 예약일자
     * @return 예약 중복 방지를 위한 시작시간, 종료 시간 출력
     */
    public List<Reservation> checkDuplicateResTime(int roomId, String  bookingDate);

    /**
     * 예약 삭제 (예약 정보 확인  페이지 - 취소 버튼)
     * @param id 예약 ID
     * @param memberId 회원 ID
     */
    public void delete(int id, int memberId);

    /**
     * 호스트의 예약 건수 달마다 출력 ( 호스트 대시보드 그래프에서 사용)
     * @param cafeId 카페 ID
     * @return 호스트의 매달 예약 건순
     */
    public List<Map<String,Object>> getCountByResMonth(int cafeId);

    /**
     * 호스트의 월 매출 (호스트 대시보드 그래프에서 사용)
     * @param cafeId 카페 ID
     * @return 호스트의 월 매출
     */
    public List<Map<String, Object>> getMonthlySales(int cafeId);

    /**
     * cafe 아이디로 카페의 총 예약 수 조회
     * @param cafeId 카페 ID
     * @param type 페이지 요청 타입
     * @return 카페의 총 예약 수
     */
    public int getCountResByHost(int cafeId, String type);

    /**
     * 회원 ID로 예약 목록 조회
     *
     * @param cafeId   카페 ID
     * @param pageParams 페이징 객체
     * @return 예약 목록
     */
    public List<Map<String, Object>> getHostResList(int cafeId, PageParams pageParams);

    /**
     * 호스트 카페의 총 예약 건 수를 가져옴
     * @param cafeId 카페 ID
     * @return 호스트 카페의 총 예약 건 수
     */
    public int getTotalRes(int cafeId);



}
