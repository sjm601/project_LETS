package com.vj.lets.domain.payment.service;

import com.vj.lets.domain.payment.dto.Payment;

public interface PaymentService {

    /**
     * 결제 등록
     * @param payment
     */
    public void payment(Payment payment);

}
