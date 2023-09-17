package com.vj.lets.domain.cafe.service;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.dto.CafeOption;
import com.vj.lets.domain.cafe.dto.CafeOptionList;
import com.vj.lets.domain.cafe.dto.CafeSearch;

import java.util.List;
import java.util.Map;

/**
 * Cafe 관련 비즈니스 로직 처리 및 트랜잭션 관리
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
     * 관리자 용 전체 카페 목록 조회
     *
     * @return 카페 목록
     * @see com.vj.lets.web.dashboard.controller.AdminController
     */
    public List<Map<String, Object>> getCafeListForAdmin();

    /**
     * 최근 1년간 월별 신규 카페 수 조회
     *
     * @return 신규 카페 수 목록
     * @see com.vj.lets.web.dashboard.controller.AdminController
     */
    public List<Map<String, Object>> getCountByRegMonth();

    /**
     * 카페 정보 수정
     *
     * @param cafe            카페
     * @param comment         정보 변경 사유
     * @param cafeOptionLists 카페 옵션 리스트
     */
    public void editCafe(Cafe cafe, String comment, List<CafeOptionList> cafeOptionLists);

    /**
     * 카페 삭제
     *
     * @param id 카페 ID
     */
    public void deleteCafe(int id);

    /**
     * 카페 옵션 리스트 객체 생성
     *
     * @param cafeId    카페 ID
     * @param optionIds 카페 옵션 아이디 리스트
     * @return 카페 옵션 리스트 객체
     */
    public List<CafeOptionList> makeCafeOptionList(int cafeId, List<Integer> optionIds);
}
