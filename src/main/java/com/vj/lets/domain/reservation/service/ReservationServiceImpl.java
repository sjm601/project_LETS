package com.vj.lets.domain.reservation.service;

import com.vj.lets.domain.reservation.dto.Reservation;
import com.vj.lets.domain.reservation.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;
    @Override
    @Transactional
    public void reserve(Reservation reservation) {
        reservationMapper.create(reservation);
    }
}
