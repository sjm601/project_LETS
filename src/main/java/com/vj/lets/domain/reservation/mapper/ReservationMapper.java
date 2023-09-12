package com.vj.lets.domain.reservation.mapper;

import com.vj.lets.domain.reservation.dto.Reservation;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 예약 Mapper 클래스
 * 작성일 (2023-09-07)
 * @author 박상훈
 */
@Mapper
public interface ReservationMapper {
    
    //예약 생성
    public void create(Reservation reservation);

    
    // 예약id 로 예약 조회
    public Reservation findById(int id);

    /**
     * 예약 전체 리스트 조회 
     * @return 예약 리스트
     */
    public List<Reservation> findByAll();

    
    //회원 Id 로 예약 전체 리스트 조회
    public List<Map<String, Object>> findByMember(int memberId);
    
    //카페 id로 예약 전체 리스트 조회
    public List<Reservation> findCafeResList(int cafeId);

    //매달 총 예약 건 수 가져오기
    public List<Map<String, Object>> findCountMonthRes(int cafeId);

    //d
    public List<Map<String,Reservation>> findTotalData(int cafeId);


}
