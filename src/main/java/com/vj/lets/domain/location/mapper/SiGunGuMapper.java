package com.vj.lets.domain.location.mapper;

import com.vj.lets.domain.location.dto.SiGunGu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 시,군,구 매퍼
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
@Mapper
public interface SiGunGuMapper {

    /**
     * 시,군,구 조회
     *
     * @param siGunGuName
     * @return 조회된 시,군,구 정보
     * @author VJ특공대 이희영
     */
    public SiGunGu getSiGunGu(String siGunGuName);

    /**
     * 이름으로 시군구 ID 조회
     *
     * @param siGunGuName 시군구 이름
     * @param siDoName    시도 이름
     * @return 시군구 ID
     * @author VJ특공대 강소영
     */
    public int getSiGunGuDo(@Param("siGunGuName") String siGunGuName, @Param("siDoName") String siDoName);
}
