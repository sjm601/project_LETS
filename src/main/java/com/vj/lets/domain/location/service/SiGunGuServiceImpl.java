package com.vj.lets.domain.location.service;

import com.vj.lets.domain.location.dto.SiGunGu;
import com.vj.lets.domain.location.mapper.SiGunGuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class SiGunGuServiceImpl implements SiGunGuService {

    private final SiGunGuMapper siGunGuMapper;

    /**
     * 시,군,구 조회
     * @param siGunGuName
     * @return 시,군,구 정보
     */
    @Override
    public SiGunGu findById(String siGunGuName) {
        return siGunGuMapper.getSiGunGu(siGunGuName);
    }
}
