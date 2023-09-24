package com.vj.lets.domain.payment.dto;

import lombok.*;

/**
 * 결제 DTO
 *
 * @author VJ특공대 박상훈
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Payment {

    private int id;
    private String paymentDate;
    private String payStatus;
    private String payName;
    private String payEmail;
    private String payPhoneNumber;
    private String paymentType;
    private int reservationId;

}