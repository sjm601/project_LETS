package com.vj.lets.domain.cafe.service;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.dto.CafeOption;
import com.vj.lets.domain.cafe.dto.CafeOptionList;
import com.vj.lets.domain.cafe.dto.CafeSearch;
import com.vj.lets.domain.common.web.PageParams;

import java.util.List;
import java.util.Map;

/**
 * Cafe 관련 비즈니스 로직 처리 및 트랜잭션 관리 서비스
 *
 * @author VJ특공대 강소영
 * @author VJ특공대 김종원
 * @version 1.0
 * @since 2023-09-08 (금)
 */
public interface CafeService {

    /**
     * 카페 옵션 등록
     *
     * @param cafeOptionLists 카페 옵션 리스트
     */
    public void cafeOptionRegister(List<CafeOptionList> cafeOptionLists);

    /**
     * 카페 옵션 등록
     *
     * @param cafeOption 카페 옵션
     */
    public void optionRegister(CafeOption cafeOption);

    /**
     * 카페 옵션 삭제
     *
     * @param optionId 카페옵션 ID
     */
    public void optionDelete(int optionId);

    /**
     * 카페 전체 리스트 출력
     *
     * @return 카페 전체 리스트
     */
    public List<Map<String, Object>> getCafeList();

    /**
     * 카페 옵션 리스트 출력
     *
     * @return 카페 옵션 전체 리스트
     */
    public List<CafeOption> getOptionList();

    /**
     * id로 카페 검색
     *
     * @param id 카페 ID
     * @return 카페 검색 결과
     */
    public Map<String, Object> getCafe(int id);

    /**
     * memberID로 카페 검색
     *
     * @param id memberId
     * @return 카페 검색결과
     */
    public Map<String, Object> getCafeMemberId(int id);

    /**
     * 카페 id로 옵션리스트 검색
     *
     * @param id 카페 id
     * @return 옵션리스트
     */
    public List<CafeOption> getCafeOptionCafeId(int id);
    /**
     * 예약이 많은 카페 6개 검색
     *
     * @return 카페 리스트 6개
     */
    public List<Map<String, Object>> getBestCafe();

    /**
     * 카페 검색
     *
     * @param cafeSearch 카페 검색값
     * @return 카페 검색 결과
     */
    public List<Map<String, Object>> getSearchCafe(CafeSearch cafeSearch);

    /**
     * 전체 입점 카페 수 조회
     *
     * @return 전체 입점 카페 수
     * @see com.vj.lets.web.dashboard.controller.AdminController
     */
    public int getCountCafeForAdmin(String type);

    /**
     * 관리자 용 전체 카페 목록 조회
     *
     * @return 카페 목록
     * @see com.vj.lets.web.dashboard.controller.AdminController
     */
    public List<Map<String, Object>> getCafeListForAdmin(PageParams pageParams);

    /**
     * 최근 1년간 월별 신규 카페 수 조회
     *
     * @return 신규 카페 수 목록
     * @see com.vj.lets.web.dashboard.controller.AdminController
     */
    public List<Map<String, Object>> getCountByRegMonth();

    /**
     * 카페 정보 수정
     * @param cafeId 카페 아이디
     * @param cafe 카페 정보
     * @param comment 수정 사유
     * @param optionIds 수정한 옵션 리스트
     */
    public void editCafe(int cafeId, String siGunGu, String siDo, Cafe cafe, String comment, List<Integer> optionIds);


    /**
     * 카페 옵션 여부 체크
     *
     * @param cafeId 카페아이디
     * @param optionId 옵션아이디
     * @return 옵션여부체크
     */
    public boolean cafeOptionCheck(int cafeId, int optionId);
}
