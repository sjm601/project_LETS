package com.vj.lets.domain.reservation.service;

import com.vj.lets.domain.reservation.dto.Reservation;
import com.vj.lets.domain.reservation.mapper.ReservationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;
    @Override
    @Transactional
    public void reserve(Reservation reservation) {
        reservationMapper.create(reservation);
    }

    @Override
    public Reservation findById(int id) {
        return reservationMapper.findById(id);
    }


    @Override
    public List<Reservation> getReservationList() {
        return reservationMapper.findByAll();
    }

    @Override
    public List<Reservation> getMemberResList(int memberId) {
        return reservationMapper.findByMember(memberId);
    }

    @Override
    public List<Reservation> getCafeResList(int cafeId) {
        return reservationMapper.findCafeResList(cafeId);
    }

    @Override
    public List<Map<String, Object>> getCountMonthRes(int cafeId) {
        return reservationMapper.findCountMonthRes(cafeId);
    }

    @Override
    public List<Map<String, Reservation>> getTotalData(int cafeId) {
        return reservationMapper.findTotalData(cafeId);
    }

    @Override
    public Map<String, Reservation> getResInfo(int id) {
        return reservationMapper.findResInfo(id);
    }

}
