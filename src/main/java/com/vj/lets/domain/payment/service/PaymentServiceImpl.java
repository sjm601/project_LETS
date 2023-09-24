package com.vj.lets.domain.payment.service;

import com.vj.lets.domain.payment.dto.Payment;
import com.vj.lets.domain.payment.mapper.PaymentMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 결제 서비스 구현체
 *
 * @author VJ특공대 박상훈
 * @version 1.0
 * @since 2023-09-21 (목)
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentMapper paymentMapper;

    /**
     * 결제 등록
     *
     * @param payment 결제 정보
     */
    @Override
    @Transactional
    public void payment(Payment payment) {
        paymentMapper.register(payment);
    }

    /**
     * 이미 결제 시도를 했는지 체크
     *
     * @param reservationId 예약 ID
     * @return 시도 횟수 1 or 0 (페이지 접근 막기 위해)
     */
    @Override
    public int checkAlreadyPayment(int reservationId) {
        return paymentMapper.checkAlreadyPayment(reservationId);
    }
}
