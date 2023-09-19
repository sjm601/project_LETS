package com.vj.lets.domain.reservation.service;

import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.reservation.dto.Reservation;
import com.vj.lets.domain.reservation.mapper.ReservationMapper;
import com.vj.lets.domain.review.mapper.ReviewMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {

    private final ReservationMapper reservationMapper;
    private final ReviewMapper reviewMapper;

    @Override
    @Transactional
    public void reserve(Reservation reservation) {
        reservationMapper.create(reservation);
    }

    @Override
    @Transactional
    public void cancelReservation(int id) {
        reservationMapper.cancel(id);
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
    public int getCountResByMember(int memberId, String type) {
        return reservationMapper.readCountByMember(memberId, type);
    }

    @Override
    public List<Map<String, Object>> getMemberResList(int memberId, PageParams pageParams) {
        List<Map<String, Object>> resList = new ArrayList<>();
        List<Map<String, Object>> list = reservationMapper.findByMember(memberId, pageParams);
        for (Map<String, Object> map : list) {
            int reservationId = Integer.parseInt(map.get("id").toString());
            boolean reviewBoolean = reviewMapper.readCountByReservationId(reservationId);
            map.put("reviewBoolean", reviewBoolean);
            resList.add(map);
        }
        return resList;
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

    @Override
    public int getNowRes(int memberId) {
        return reservationMapper.findNowRes(memberId);
    }

    @Override
    public int checkDuplicateReservation(int roomId, String bookingDate, int startTime, int endTime) {
        return reservationMapper.checkDuplicateReservation(roomId,bookingDate,startTime,endTime);
    }

    @Override
    public List<Reservation> checkDuplicateResTime(int roomId, String bookingDate) {
        return reservationMapper.checkDuplicateResTime(roomId, bookingDate);
    }

    @Override
    @Transactional
    public void delete(int id, int memberId) {
        reservationMapper.delete(id, memberId);
    }


}
