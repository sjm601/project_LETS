package com.vj.lets.domain.support.service;

import com.vj.lets.domain.support.dto.Faq;

/**
* 클래스 설명 : FAQ 관련 로직 처리 및 관리
* 작성일 : 2023-09-08
* @author : 이한솔
*/


public interface FaqService {
	/** FAQ ID로 찾기*/
	public Faq findById(int id);
}
