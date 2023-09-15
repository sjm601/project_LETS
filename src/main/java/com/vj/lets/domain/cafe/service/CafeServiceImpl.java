package com.vj.lets.domain.cafe.service;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.dto.CafeOption;
import com.vj.lets.domain.cafe.dto.CafeOptionList;
import com.vj.lets.domain.cafe.dto.CafeSearch;
import com.vj.lets.domain.cafe.mapper.CafeHistoryMapper;
import com.vj.lets.domain.cafe.mapper.CafeMapper;
import com.vj.lets.domain.cafe.mapper.CafeOptionListMapper;
import com.vj.lets.domain.cafe.mapper.CafeOptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    private final CafeOptionMapper cafeOptionMapper;
    private final CafeOptionListMapper cafeOptionListMapper;

    @Override
    @Transactional
    public void register(Cafe cafe){
        cafeMapper.create(cafe);
        cafeHistoryMapper.create();
    }

    @Override
    @Transactional
    public void cafeOptionRegister(List<CafeOptionList> cafeOptionLists) {
        for (CafeOptionList list : cafeOptionLists) {
            cafeOptionListMapper.create(list);
        }
    }

    @Override
    public void optionRegister(CafeOption cafeOption) {
        cafeOptionMapper.create(cafeOption);
    }

    @Override
    public void optionDelete(int optionId) {
        cafeOptionMapper.delete(optionId);
    }

    @Override
    public List<Map<String, Object>> getCafeList() {
        return cafeMapper.findByAll();
    }

    @Override
    public List<CafeOption> getOptionList() {
        return cafeOptionMapper.readAll();
    }

    @Override
    public Map<String, Object> getCafe(int id) {
        return cafeMapper.findById(id);
    }

    @Override
    public List<Map<String, Object>> getBestCafe(){
        return cafeMapper.findByBest();
    }

    @Override
    public List<Map<String, Object>> getSearchCafe(CafeSearch cafeSearch){
        return cafeMapper.findBySearch(cafeSearch);
    }

    @Override
    public List<Map<String, Object>> getCafeListForAdmin() {
        return cafeMapper.readAllForAdmin();
    }

    @Override
    public List<Map<String, Object>> getCountByRegMonth() {
        return cafeMapper.readCountByRegMonth();
    }

    @Override
    @Transactional
    public void editCafe(Cafe cafe, String comment, List<CafeOptionList> cafeOptionLists) {
        cafeMapper.update(cafe);
        cafeHistoryMapper.update(comment, cafe.getId());
        cafeOptionListMapper.delete(cafe.getId());
        for (CafeOptionList list : cafeOptionLists) {
            cafeOptionListMapper.create(list);
        }
    }

    @Override
    @Transactional
    public void deleteCafe(int id) {
        cafeMapper.delete(id);
        cafeHistoryMapper.delete(id);
    }

    @Override
    public List<CafeOptionList> makeCafeOptionList(int cafeId, List<Integer> optionIds) {
        List<CafeOptionList> cafeOptionLists = new ArrayList<>();
        for (int optionId : optionIds) {
            CafeOptionList makeList = CafeOptionList
                    .builder()
                    .cafeId(cafeId)
                    .optionId(optionId)
                    .build();
            cafeOptionLists.add(makeList);
        }
        return cafeOptionLists;
    }


}
