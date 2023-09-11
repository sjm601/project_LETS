package com.vj.lets.domain.reservation.service;

import com.vj.lets.domain.reservation.dto.Reservation;

/**
 * 예약 서비스 클래스
 * @author 박상훈
 * @version 1.0
 * @since 2023-09-10 (일)
 */
public interface ReservationService {

    public void reserve(Reservation reservation);
}
