package com.vj.lets.web.dashboard.controller;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.dto.CafeEditForm;
import com.vj.lets.domain.cafe.dto.CafeOption;
import com.vj.lets.domain.cafe.dto.OptionListForm;
import com.vj.lets.domain.cafe.service.CafeService;
import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.common.web.Pagination;
import com.vj.lets.domain.member.dto.Member;
import com.vj.lets.domain.reservation.dto.Reservation;
import com.vj.lets.domain.reservation.service.ReservationService;
import com.vj.lets.domain.review.service.ReviewService;
import com.vj.lets.domain.room.dto.Room;
import com.vj.lets.domain.room.service.RoomService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 호스트 대시보드 관련 요청 컨트롤러
 *
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@Controller
@RequestMapping("/host")
@RequiredArgsConstructor
@Slf4j
public class HostController {

    private final CafeService cafeService;
    private final RoomService roomService;
    private final ReservationService reservationService;
    private final ReviewService reviewService;

    private static final int ELEMENT_SIZE = 5;
    private static final int PAGE_SIZE = 5;

    /**
     * 호스트 대시보드 메인 화면 출력
     *
     * @param model 모델 객체
     * @return 논리적 뷰 이름
     */
    @GetMapping("")
    public String hostMain(HttpServletRequest request,Model model) {
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        Map<String, Object> cafe = cafeService.getCafeMemberId(loginMember.getId());
        int cafeId = Integer.parseInt(cafe.get("id").toString());
        List<Map<String, Object>> countRes = reservationService.getCountByResMonth(cafeId);
        model.addAttribute("countRes", countRes);
        List<Map<String, Object>> monthlySales = reservationService.getMonthlySales(cafeId);
        model.addAttribute("monthlySales", monthlySales);
        return "dashboard/host/host_dashboard";
    }

    @GetMapping("/cafe")
    public String cafeRegister(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");

        if (loginMember != null){
            Map<String, Object> cafe = cafeService.getCafeMemberId(loginMember.getId());
            model.addAttribute("cafe", cafe);
            int cafeId = Integer.parseInt(cafe.get("id").toString());
            List<CafeOption> cafeOptions = cafeService.getCafeOptionCafeId(cafeId);
            model.addAttribute("cafeOptions", cafeOptions);

            List<CafeOption> allOption = cafeService.getOptionList();
            List<OptionListForm> optionListForms = new ArrayList<>();
            for(CafeOption option : allOption) {
                OptionListForm optionListForm = OptionListForm.builder()
                        .optionId(option.getId())
                        .optionName(option.getName())
                        .imagePath(option.getImagePath())
                        .optionCheck(cafeService.cafeOptionCheck(cafeId, option.getId()))
                        .build();
                optionListForms.add(optionListForm);
            }
            model.addAttribute("optionListForms", optionListForms);
        }
        return "dashboard/host/cafe_register";
    }

    @PostMapping("/cafe/edit")
    public String cafeUpdate(@ModelAttribute CafeEditForm cafeEditForm,
                             MultipartFile imagePath,
                             HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        if(loginMember != null){
            Map<String, Object> cafes = cafeService.getCafeMemberId(loginMember.getId());
            int cafeId = Integer.parseInt(cafes.get("id").toString());
            log.info("cafeEditForm:{}", cafeEditForm);
            Cafe cafeRe = Cafe.builder()
                    .id(cafeId)
//                    .imagePath(imagePath)
                    .businessNumber(cafeEditForm.getBusinessNumber())
                    .name(cafeEditForm.getName())
                    .phoneNumber(cafeEditForm.getPhoneNumber())
                    .roadAddress(cafeEditForm.getRoadAddress())
                    .detailAddress(cafeEditForm.getDetailAddress())
                    .latitude(cafeEditForm.getLatitude())
                    .longitude(cafeEditForm.getLongitude())
                    .startTime(cafeEditForm.getStartTime())
                    .endTime(cafeEditForm.getEndTime())
                    .description(cafeEditForm.getDescription())
                    .build();
            String comment = "host";
            String siGunGu = cafeEditForm.getSiGunGuName();
            String siDo = cafeEditForm.getSiDoName();
            cafeService.editCafe(cafeId, siGunGu, siDo, cafeRe, comment, cafeEditForm.getOptions());
        }
        return "redirect:/host/cafe";
    }

    @GetMapping("/room")
    public String roomList(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        if(loginMember != null){
            Map<String, Object> cafe = cafeService.getCafeMemberId(loginMember.getId());
            int cafeId = Integer.parseInt(cafe.get("id").toString());
            List<Room> rooms = roomService.getSearchCafeRoom(cafeId);
            model.addAttribute("rooms", rooms);
        }
        return "dashboard/host/room_table";
    }

    @GetMapping("/room/{id}")
    public String roomDetail(@PathVariable int id, Model model){
        Room room = roomService.getSearchRoom(id);
        Room roomForm = Room.builder().build();
        model.addAttribute("room", room);
        model.addAttribute("roomForm", roomForm);
        return "dashboard/host/room_register";
    }

    @PostMapping("/room/{id}/edit")
    public String roomUpdate(@PathVariable String id,
                             @ModelAttribute Room roomForm, Model model){
        log.info("id값 : {}", id);
        Room editRoom = Room.builder()
//                .imagePath(roomForm.getImagePath())
                .id(Integer.parseInt(id))
                .name(roomForm.getName())
                .headCount(roomForm.getHeadCount())
                .price(roomForm.getPrice())
                .description(roomForm.getDescription())
                .build();
        roomService.editRoom(editRoom);
        return "redirect:/host/room/{id}";
    }

    @PostMapping("/room/regist")
    public String roomRegist(@ModelAttribute Room roomRegist,
                             HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        if(loginMember != null) {
            Map<String, Object> cafe = cafeService.getCafeMemberId(loginMember.getId());
            int cafeId = Integer.parseInt(cafe.get("id").toString());
            Room roomNew = Room.builder()
                    .name(roomRegist.getName())
//                    .imagePath(roomRegist.getImagePath())
                    .imagePath("/image/roompicture")
                    .headCount(roomRegist.getHeadCount())
                    .price(roomRegist.getPrice())
                    .description(roomRegist.getDescription())
                    .cafeId(cafeId)
                    .build();
            log.info("등록될 룸 : {}", roomNew);
            roomService.register(roomNew);
        }
        return "redirect:/host/room";
    }

    @GetMapping("/bookings")
    public String hostBookingList(@RequestParam(value = "page", required = false) String page,
                                  @RequestParam(value = "type", required = false) String type,
                                  HttpServletRequest request,
                                  Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        Map<String, Object> cafe = cafeService.getCafeMemberId(loginMember.getId());
        int cafeId = Integer.parseInt(cafe.get("id").toString());
        if (page == null || page.isBlank()) {
            page = "1";
        }
        if (type == null || type.isBlank()) {
            type = "all";
        }
        int selectPage = Integer.parseInt(page);
        int count = reservationService.getCountResByHost(cafeId, type);
        PageParams pageParams = PageParams.builder()
                .elementSize(ELEMENT_SIZE)
                .pageSize(PAGE_SIZE)
                .requestPage(selectPage)
                .rowCount(count)
                .type(type)
                .build();

        Pagination pagination = new Pagination(pageParams);

        List<Map<String, Object>> hostReservationList = reservationService.getHostResList(cafeId, pageParams);

        model.addAttribute("hostReservationList", hostReservationList);
        model.addAttribute("pagination", pagination);

        return "dashboard/host/bookings";
    }


    @GetMapping("/reviews")
    public String hostreviewList(@RequestParam(value = "page", required = false) String page,
                                 @RequestParam(value = "type", required = false) String type,
                                 HttpServletRequest request,
                                 Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        Map<String, Object> cafe = cafeService.getCafeMemberId(loginMember.getId());
        int cafeId = Integer.parseInt(cafe.get("id").toString());
        if (page == null || page.isBlank()) {
            page = "1";
        }
        if (type == null || type.isBlank()) {
            type = "latest";
        }
        int selectPage = Integer.parseInt(page);
        int count = reviewService.getCountByHost(cafeId);
        log.info("카운트:{}",count);
        PageParams pageParams = PageParams.builder()
                .elementSize(ELEMENT_SIZE)
                .pageSize(PAGE_SIZE)
                .requestPage(selectPage)
                .rowCount(count)
                .type(type)
                .build();
        Pagination pagination = new Pagination(pageParams);

        List<Map<String, Object>> hostReviewList = reviewService.getByHost(cafeId, pageParams);
        log.info("리뷰리스트:{}",hostReviewList);
        model.addAttribute("hostReviewList", hostReviewList);
        model.addAttribute("pagination", pagination);

        return "dashboard/host/reviews";
    }

    @GetMapping("/stats")
    public String hostTotalData(HttpServletRequest request, Model model){
        HttpSession session = request.getSession();
        Member loginMember = (Member) session.getAttribute("loginMember");
        if(loginMember != null){
            Map<String, Object> cafe = cafeService.getCafeMemberId(loginMember.getId());
            int cafeId = Integer.parseInt(cafe.get("id").toString());
            List<Map<String,Reservation>>  reserveList = reservationService.getTotalData(cafeId);
            model.addAttribute("reserveList", reserveList);
        }
        return "dashboard/host/tables";
    }
}
