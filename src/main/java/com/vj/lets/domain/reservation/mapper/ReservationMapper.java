package com.vj.lets.domain.reservation.mapper;

import com.vj.lets.domain.reservation.dto.Reservation;
import org.apache.ibatis.annotations.Mapper;
/**
 * 예약 Mapper 클래스
 * 작성일 (2023-09-07)
 * @author 박상훈
 */
@Mapper
public interface ReservationMapper {
    /**
     * 예약 하기
     * @param reservation
     */
    public void create(Reservation reservation);



}
