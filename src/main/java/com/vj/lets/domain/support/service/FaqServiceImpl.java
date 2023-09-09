package com.vj.lets.domain.support.service;

import com.vj.lets.domain.support.dto.Faq;
import com.vj.lets.domain.support.mapper.FaqMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

/**
* 클래스 설명 : FAQ 서비스 구현체
* 작성일 : 2023-09-08
* @author : 이한솔
*/
@RequiredArgsConstructor
@Service
public class FaqServiceImpl implements FaqService {

	private final FaqMapper faqMapper;


	@Override
	public Faq findById(int id) {
		return faqMapper.findById(id);
	}
}