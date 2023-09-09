package com.vj.lets.domain.cafe.service;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.mapper.CafeHistoryMapper;
import com.vj.lets.domain.cafe.mapper.CafeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Cafe Service 구현체
 *
 * @author 강소영
 * @version 1.0
 * @since 2023-09-08 (금)
 */
@RequiredArgsConstructor
@Service
public class CafeServiceImpl implements CafeService{

    private final CafeMapper cafeMapper;
    private final CafeHistoryMapper cafeHistoryMapper;

    @Override
    @Transactional
    public void register(Cafe cafe) {
        cafeMapper.create(cafe);
        cafeHistoryMapper.create();
    }

    @Override
    public List<Cafe> getCafeList() {
        return cafeMapper.findByAll();
    }

    @Override
    public Cafe getCafe(int id) {
        return cafeMapper.findById(id);
    }

    @Override
    public List<Cafe> getBestCafe(){
        return cafeMapper.findByBest();
    }

    @Override
    public List<Cafe> getSearchCafe(String name, int countPerson, int price, String option,
                                    int minDuaration, int maxDuration, int rating5, int rating4,
                                    int rating3, int rating2, int rating1, int rating){
        return cafeMapper.findBySearch(name, countPerson, price, option, minDuaration, maxDuration,
                rating5, rating4, rating3, rating2, rating1, rating);
    }

    @Override
    @Transactional
    public void editCafe(Cafe cafe) {
        cafeMapper.update(cafe);
        cafeHistoryMapper.update(cafe.getId());
    }

    @Override
    @Transactional
    public void deleteCafe(int id) {
        cafeMapper.delete(id);
        cafeHistoryMapper.delete(id);
    }
}
