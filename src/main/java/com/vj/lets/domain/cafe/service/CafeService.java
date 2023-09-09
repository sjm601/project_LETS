package com.vj.lets.domain.cafe.service;

import com.vj.lets.domain.cafe.dto.Cafe;

import java.util.List;

/**
 * Cafe 관련 비즈니스 로직 처리 및 트랜잭션 관리
 *
 * @author 강소영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
public interface CafeService {

    /** 카페 등록 */
    public void register(Cafe cafe);

    /** 카페 전체 리스트 출력 */
    public List<Cafe> getCafeList();

    /** 카페 id로 검색 */
    public Cafe getCafe(int id);

    /** 예약이 많은 카페 6개 검색 */
    public List<Cafe> getBestCafe();

    /** 카페 키워드 검색 */
    public List<Cafe> getSearchCafe(String name, int countPerson, int price, String option, int minDuaration, int maxDuration, int rating5, int rating4, int rating3, int rating2, int rating1, int rating);


    /** 카페 정보 수정 */
    public void editCafe(Cafe cafe);

    /** 카페 삭제 */
    public void deleteCafe(int id);
}
