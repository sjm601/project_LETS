package com.vj.lets.domain.payment.service;

import com.vj.lets.domain.payment.dto.Payment;
import com.vj.lets.domain.payment.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;

    @Override
    @Transactional
    public void payment(Payment payment) {
        paymentMapper.register(payment);
    }
}
