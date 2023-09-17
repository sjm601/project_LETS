package com.vj.lets.domain.cafe.mapper;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.dto.CafeSearch;
import com.vj.lets.domain.common.web.PageParams;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * Cafe 테이블 관련 명세
 *
 * @author VJ특공대 강소영
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023. 9. 7.
 */
@Mapper
public interface CafeMapper {

    /**
     * 카페 생성
     *
     * @param cafe 카페 정보
     * @see com.vj.lets.web.dashboard.controller.AdminController
     */
    public void create(Cafe cafe);

    /**
     * 카페정보 업데이트
     */
    public void update(Cafe cafe);

    /**
     * id로 카페 조회
     */
    public Map<String, Object> findById(int id);

    /**
     * 멤버 id로 카페 검색
     *
     * @param id 멤버 아이디
     * @return cafe
     */
    public Map<String, Object> findByMemberId(int id);

    /** 카페 전체리스트 출력 */
    public List<Map<String, Object>> findByAll();

    /** 누적 예약이 가장 많은 카페 6개 출력 */
    public List<Map<String, Object>> findByBest();


    /**
     * 카패 검색 결과 출력
     */
    public List<Map<String, Object>> findBySearch(CafeSearch cafeSearch);

    /**
     * 전체 입점 카페 수 조회
     *
     * @return 전체 입점 카페 수
     * @see com.vj.lets.web.dashboard.controller.AdminController
     */
    public int readCountAllForAdmin(String type);

    /**
     * 전체 카페 목록 조회 (관리자용)
     *
     * @return 카페 목록
     * @see com.vj.lets.web.dashboard.controller.AdminController
     */
    public List<Map<String, Object>> readAllForAdmin(PageParams pageParams);

    /**
     * 최근 1년간 월별 신규 카페 수 조회
     *
     * @return 신규 카페 수 목록
     * @see com.vj.lets.web.dashboard.controller.AdminController
     */
    public List<Map<String, Object>> readCountByRegMonth();

    /**
     * 카페삭제
     */
    public void delete(int id);
}
