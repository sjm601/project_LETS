package com.vj.lets.domain.payment.dto;

import lombok.*;

/**
 * 결제 DTO
 * 작성일 (2023-09-10)
 * @author 박상훈
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