package com.vj.lets.domain.payment.service;

import com.vj.lets.domain.payment.dto.Payment;

public interface PaymentService {

    /**
     * 결제 등록
     * @param payment
     */
    public void register(Payment payment);

    /**
     * 예약 번호에 따른 결제 내역 조회
     * @param reservationId
     * @return 결제 정보
     */
    public Payment read(int reservationId);
}
