package com.vj.lets.domain.location.mapper;

import com.vj.lets.domain.location.dto.SiGunGu;
import org.apache.ibatis.annotations.Mapper;

/**
 * 시,군,구 매퍼
 * 
 * @author 이희영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface SiGunGuMapper {

    /**
     * 시,군,구 조회
     * @param siGunGuName
     * @return 시,군,구 정보
     */
    public SiGunGu getSiGunGu(String siGunGuName);
}
