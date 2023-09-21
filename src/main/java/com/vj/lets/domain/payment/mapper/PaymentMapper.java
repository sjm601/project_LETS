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
     * 이미 결제 시도를 했는지 체크
     * @param reservationId
     * @return 시도 횟수 1 or 0 (페이지 접근 막기 위해)
     */
    public int checkAlreadyPayment(int reservationId);


}
