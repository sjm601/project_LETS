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
     * 결제 하기
     */
    public void register(Payment payment);
}
