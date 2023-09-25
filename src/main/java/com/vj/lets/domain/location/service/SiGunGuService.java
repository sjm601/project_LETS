package com.vj.lets.domain.location.service;

import com.vj.lets.domain.location.dto.SiGunGu;

/**
 * 시,군,구 서비스 규약
 *
 * @author VJ특공대 이희영
 * @version 1.0
 * @since 2023-09-11 (월)
 */
public interface SiGunGuService {

    /**
     * 시,군,구 조회
     *
     * @param siGunGuName 시,군,구 이름
     * @return 조회된 시,군,구 정보
     * @author VJ특공대 이희영
     */
    public SiGunGu findById(String siGunGuName);

    /**
     * 이름으로 시군구 ID 조회
     *
     * @param siGunGuName 시군구 이름
     * @param siDoName    시도 이름
     * @return 시군구 ID
     * @author VJ특공대 강소영
     */
    public int getSiGunGuDo(String siGunGuName, String siDoName);
}
