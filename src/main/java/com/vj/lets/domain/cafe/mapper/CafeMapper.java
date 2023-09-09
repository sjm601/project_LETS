package com.vj.lets.domain.cafe.mapper;

import com.vj.lets.domain.cafe.dto.Cafe;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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
    public Cafe update(Cafe cafe);

    /** id로 카페 조회 */
    public Cafe findById(int id);

    /** 카페 전체리스트 출력 */
    public List<Cafe> findByAll();

    /** 누적 예약이 가장 많은 카페 6개 출력 */
    public List<Cafe> findByBest();

    /** 카패 검색 결과 출력 */
    public List<Cafe> findBySearch(@Param("searchName") String name,
                                   @Param("searchCountPerson") int countPerson,
                                   @Param("searchPrice") int price,
                                   @Param("searchOption") String option,
                                   @Param("searchMinDuration") int minDuration,
                                   @Param("searchMaxDuration") int maxDuration,
                                   @Param("rating5") int rating5,
                                   @Param("rating4") int rating4,
                                   @Param("rating3") int rating3,
                                   @Param("rating2") int rating2,
                                   @Param("rating1") int rating1,
                                   @Param("rating") int rating);

    /** 카페삭제 */
    public void delete(int id);
}
