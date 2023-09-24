package com.vj.lets.domain.reservation.dto;

import lombok.*;

/**
 * 예약 DTO
 *
 * @author VJ특공대 박상훈
 * @version 1.0
 * @since 2023-09-07 (목)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Reservation {

    private int id;
    private String bookingDate;
    private int startTime;
    private int endTime;
    private int headCount;
    private int memberId;
    private int cafeId;
    private int roomId;
    private String status;

}
