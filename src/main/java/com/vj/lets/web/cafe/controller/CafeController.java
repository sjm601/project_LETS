package com.vj.lets.web.cafe.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vj.lets.domain.cafe.dto.CafeOption;
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

import java.text.ParseException;
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
        List<Map<String, Object>> bestCafe = cafeService.getBestCafe();
        model.addAttribute("bestCafe", bestCafe);
        return "common/cafe/cafe_main";
    }

    @GetMapping("/list")
    public String cafeList(Model model) {
        List<CafeOption> options = cafeService.getOptionList();
        model.addAttribute("options", options);
        List<Map<String, Object>> allCafe = cafeService.getCafeList();
        model.addAttribute("allCafe", allCafe);
        return "common/cafe/cafe_list";
    }


    @GetMapping("/{id}")
    public String viewDetail(@PathVariable int id,
                             @ModelAttribute Reservation reservation,
                             Model model) {
        Map<String, Object> cafe = cafeService.getCafe(id);
        Object cafeRating = cafe.get("cafeRating");
        if (cafeRating == null) {
            cafeRating = 5;
        }

        model.addAttribute("Cafe", cafe);
        log.info("카페 내용:{}",cafe);
        model.addAttribute("cafeRating", cafeRating);

        List<Room> roomList = roomService.getSearchCafeRoom(id);
        model.addAttribute("roomList", roomList);

        model.addAttribute("errorMessage", "");

        return "common/cafe/cafe_detail";
    }

    
    /**
     * 방 정보 및 가격, 예약 가능 시간 동적 출력
     * @param id
     * @param model
     * @author VJ특공대 박상훈
     * @return json으로 변환된 룸 리스트
     * @throws JsonProcessingException
     */
    @RequestMapping("/selectDate/{id}")
    @ResponseBody
    public  String findRoom(@PathVariable int id, Model model) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();

        List<Room> roomList = roomService.getSearchCafeRoom(id);
        log.info("룸 목록:{}",roomList);
        return  objectMapper.writeValueAsString(roomList);
    }


    @PostMapping("/selectRoom/{roomId}")
    @ResponseBody
    public  String findReservedTime(@PathVariable int roomId, Model model, @RequestBody Object bookingDate) throws JsonProcessingException, ParseException {
        ObjectMapper objectMapper = new ObjectMapper();
        log.info("asdasdasdasd:{}", roomId);
        log.info("bookingDate:{}", bookingDate);
        String bookDate = bookingDate.toString();
        log.info("bookingDate:{}", bookDate);
        List<Reservation> reservedTime =reservationService.checkDuplicateResTime(roomId, bookDate);
        log.info("reservedTime:{}", reservedTime);
        return objectMapper.writeValueAsString(reservedTime);
    }

    /**
     * 예약 생성하기
     * @param id
     * @param bookingDate
     * @param headCount
     * @param startTime
     * @param endTime
     * @param roomId
     * @param reservation
     * @param loginMember
     * @param model
     * @author VJ 특공대 박상훈
     * @return 예약 ~결제 페이지
     */
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

        int count = reservationService.checkDuplicateReservation(roomId, bookingDate, startTime, endTime);
        if (count > 0) {
            model.addAttribute("errorMessage", "이미 예약된 시간대 입니다. 다른 시간을 선택하세요.");
            return "redirect:/cafe/{id}?error=true";
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