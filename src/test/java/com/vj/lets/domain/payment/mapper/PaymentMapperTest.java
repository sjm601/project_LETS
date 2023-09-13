package com.vj.lets.domain.payment.mapper;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.payment.dto.Payment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 결제 Mapper 테스트
 *
 * @author 박상훈
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@SpringBootTest
@Slf4j
class PaymentMapperTest {

    @Autowired
    private PaymentMapper paymentMapper;

    @Test
    @Transactional
    void registerTest() {
        // given
        Payment payment = Payment.builder()
                .id(1)
                .payStatus("success")
                .payName("신짱구")
                .payEmail("shin@gmail.com")
                .payPhoneNumber("01082828282")
                .reservationId(1)
                .build();
        // when
        paymentMapper.register(payment);
        // then
        log.info("결제 정보 :{}",payment);
        assertThat(payment).isNotNull();
    }
}