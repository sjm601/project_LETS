package com.vj.lets.web.cafe.controller;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.service.CafeService;
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
    public String cafeDetails(@PathVariable int id, Model model) {
        Map<String, Object> cafe = cafeService.getCafe(id);
        model.addAttribute("cafe", cafe);
        List<Room> room = roomService.getSearchCafeRoom(id);
        model.addAttribute("room", room);

        return "common/cafe/cafe_detail";
    }

    @PostMapping("/{id}")
    public String reserve(@PathVariable int id, @ModelAttribute Reservation reservation, Model model){
        Map<String, Object> cafe = cafeService.getCafe(id);
        model.addAttribute("cafe",cafe);
        List<Room> room =roomService.getSearchCafeRoom(id);
        model.addAttribute("room",room);
        reservationService.reserve(reservation);
        return "common/cafe/reservation";
    }


}
