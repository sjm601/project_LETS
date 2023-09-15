package com.vj.lets.domain.cafe.mapper;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.dto.CafeSearch;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Cafe 테이블 관련 명세
 *
 * @author VJ특공대 강소영
 * @version 1.0
 * @since 2023. 9. 7.
 */
@Mapper
public interface CafeMapper {

    /** 카페 생성 */
    public void create(Cafe cafe);

    /** 카페정보 업데이트 */
    public void update(Cafe cafe);

    /** id로 카페 조회 */
    public Map<String, Object> findById(int id);

    /** 카페 전체리스트 출력 */
    public List<Map<String, Object>> findByAll();

    /** 누적 예약이 가장 많은 카페 6개 출력 */
    public List<Integer> findByBest();

    /** 카패 검색 결과 출력 */
    public List<Map<String, Object>> findBySearch(CafeSearch cafeSearch);

    /** 카페삭제 */
    public void delete(int id);
}
