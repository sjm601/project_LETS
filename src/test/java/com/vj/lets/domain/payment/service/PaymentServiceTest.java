package com.vj.lets.domain.payment.service;//import static org.junit.jupiter.api.Assertions.*;

import com.vj.lets.domain.payment.dto.Payment;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Slf4j
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Test
    @Transactional
    void registerTest() {
        //given
        Payment payment = Payment.builder()
                .id(1)
                .payStatus("success")
                .payName("신짱구")
                .payEmail("shin@gmail.com")
                .payPhoneNumber("010-8282-8282")
                .reservationId(1)
                .build();
        // when
        paymentService.payment(payment);
        // then
        log.info("결제 정보 :{}",payment);
        assertThat(payment).isNotNull();
    }
}