package com.vj.lets.domain.cafe.service;

import com.vj.lets.domain.cafe.dto.Cafe;
import com.vj.lets.domain.cafe.dto.CafeOption;
import com.vj.lets.domain.cafe.dto.CafeOptionList;
import com.vj.lets.domain.cafe.dto.CafeSearch;
import com.vj.lets.domain.cafe.mapper.CafeHistoryMapper;
import com.vj.lets.domain.cafe.mapper.CafeMapper;
import com.vj.lets.domain.cafe.mapper.CafeOptionListMapper;
import com.vj.lets.domain.cafe.mapper.CafeOptionMapper;
import com.vj.lets.domain.common.web.PageParams;
import com.vj.lets.domain.location.mapper.SiGunGuMapper;
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
 * @author VJ특공대 김종원
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
    private final SiGunGuMapper siGunGuMapper;
    
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
    public List<Map<String, Object>> getCafeList(PageParams pageParams) {
        return cafeMapper.findByAll(pageParams);
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
    public Map<String, Object> getCafeMemberId(int id) {
        return cafeMapper.findByMemberId(id);
    }

    @Override
    public List<CafeOption> getCafeOptionCafeId(int id) {
        return cafeOptionListMapper.findByOptionCafeId(id);
    }

    @Override
    public List<Map<String, Object>> getBestCafe(){
        return cafeMapper.findByBest();
    }

    @Override
    public List<Map<String, Object>> getSearchCafe(CafeSearch cafeSearch, PageParams pageParams,
                                                   double currentY, double currentX){
        return cafeMapper.findBySearch(cafeSearch, pageParams, currentY, currentX);
    }

    @Override
    public int getCountCafeForAdmin() {
        return cafeMapper.readCountAllForAdmin();
    }

    @Override
    public List<Map<String, Object>> getCafeListForAdmin(PageParams pageParams) {
        return cafeMapper.readAllForAdmin(pageParams);
    }

    @Override
    public List<Map<String, Object>> getCountByRegMonth() {
        return cafeMapper.readCountByRegMonth();
    }

    @Override
    public int getCountByLastMonth() {
        return cafeMapper.readCountByLastMonth();
    }

    @Override
    @Transactional
    public void editCafe(int cafeId, String siGunGu, String siDo, Cafe cafe, String comment, List<Integer> optionIds) {
        List<CafeOptionList> cafeOptionLists = new ArrayList<>();
        for (int optionId : optionIds) {
            CafeOptionList makeList = CafeOptionList
                    .builder()
                    .cafeId(cafeId)
                    .optionId(optionId)
                    .build();
            cafeOptionLists.add(makeList);
        }
        if (siGunGu != null){
            int sggId = siGunGuMapper.getSiGunGuDo(siGunGu, siDo);
            cafe.setSiGunGuId(sggId);
        }
        cafeMapper.update(cafe);
        cafeHistoryMapper.update(comment, cafe.getId());
        cafeOptionListMapper.delete(cafe.getId());
        for (CafeOptionList list : cafeOptionLists) {
            cafeOptionListMapper.create(list);
        }
    }

    @Override
    public boolean cafeOptionCheck(int cafeId, int optionId) {
        return cafeOptionListMapper.findByOptionCheckCafeId(cafeId, optionId);
    }


}
