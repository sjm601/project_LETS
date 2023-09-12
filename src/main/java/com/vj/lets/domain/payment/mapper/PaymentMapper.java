package com.vj.lets.domain.payment.mapper;

import com.vj.lets.domain.payment.dto.Payment;
import org.apache.ibatis.annotations.Mapper;

/**
 * 결제 Mapper 클래스
 * @author 박상훈
 * @version 1.0
 * @since 2023-09-10 (일)
 */
@Mapper
public interface PaymentMapper {

    /**
     * 결제 하기(등록)
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
