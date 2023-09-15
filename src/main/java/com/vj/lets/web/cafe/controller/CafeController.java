package com.vj.lets.web.cafe.controller;

import com.vj.lets.domain.cafe.service.CafeService;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.reservation.dto.Reservation;
import com.vj.lets.domain.reservation.service.ReservationService;
import com.vj.lets.domain.room.dto.Room;
import com.vj.lets.domain.room.service.RoomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 카페 관련 요청을 처리하는 세부 컨트롤러 구현 클래스
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023. 9. 8.
 */
@Controller
//@RestController
@RequestMapping("/cafe")
@RequiredArgsConstructor
@Slf4j
public class CafeController {

    private final ReservationService reservationService;
    private final CafeService cafeService;
    private final RoomService roomService;

    @GetMapping("")
    public String cafeDetail(Model model) {
        return "common/cafe/cafe_main";
    }
    
    @GetMapping("/list")
    public String cafeList(Model model) {
        return "common/cafe/cafe_list";
    }


    @GetMapping("/{id}")
    public String viewDetail(@PathVariable int id, Model model){
        Map<String,Object> cafe = cafeService.getCafe(id);
        Object cafeRating = cafe.get("cafeRating");
        if (cafeRating == null) {
            cafeRating = 5;
        }
        model.addAttribute("Cafe", cafe);
        model.addAttribute("cafeRating", cafeRating);

        List<Room> roomList = roomService.getSearchCafeRoom(id);
        model.addAttribute("roomList", roomList);

        return "common/cafe/cafe_detail";
    }







    @PostMapping("/{id}")
    public String bookCafe(@PathVariable("id") int id,
                           @RequestParam("bookingDate") String bookingDate,
                           @RequestParam("headCount") int headCount,
                           @RequestParam("startTime") int startTime,
                           @RequestParam("endTime") int endTime,
                           @RequestParam("roomId") int roomId,
                           @ModelAttribute Reservation reservation,
                           @SessionAttribute Member loginMember,
                           Model model) {

        // 예약 중복 체크
        List<Map<String, Integer>> nonReservationTime = reservationService.checkDuplicateResTime(roomId, bookingDate, startTime, endTime);
        model.addAttribute("nonReservationTime",nonReservationTime);
        if (!nonReservationTime.isEmpty()) {
            return "common/cafe/cafe_detail"; // 예약 실패 페이지 또는 에러 페이지로 리다이렉트
        }

        // 예약 객체 설정
        reservation.setCafeId(id);
        reservation.setBookingDate(bookingDate);
        reservation.setStartTime(startTime);
        reservation.setEndTime(endTime);
        reservation.setMemberId(loginMember.getId());
        reservation.setHeadCount(headCount);
        reservation.setRoomId(roomId);
        reservationService.reserve(reservation);
        int resId = reservationService.getNowRes(loginMember.getId());

        return "redirect:/reservation/" + resId;
    }
}