package com.vj.lets.domain.common.web;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 카페 출력을 위한 페이징 처리 계산 클래스
 * 
 * @author 강소영
 */
@Setter
@Getter
@ToString
public class PaginationForCafe {

	// 페이징 계산 위한 파라메터
	private PageParamsForCafe params;

	// 계산 결과 저장을 위한 필드
	// 전체 페이지 수
	private int totalPages;
	// 목록 번호
	private int listNo;
	// 페이지 시작 번호
	private int startPage;
	// 페이지 끝 번호
	private int endPage;
	// 이전 페이지 시작 번호
	private int previousStartPage;
	// 다음 페이지 시작 번호
	private int nextStartPage;

	public PaginationForCafe(PageParamsForCafe params) {
		this.params = params;
		compute();
	}

	/** 페이징 계산 */
	public void compute(){
		// 테이블로부터 검색된 행의 수에 따른 전체페이지수 계산
		totalPages = (int)Math.ceil((double)params.getRowCount() / params.getElementSize());
		
		// 목록별 번호(1~10):0, (11~20):1, (21~30):2, .....
		listNo = (params.getRequestPage() - 1) / params.getPageSize();
			
		// 현재 페이지의 시작페이지번호와 마지막페이지번호 계산
		startPage = (listNo * params.getPageSize()) + 1;
		endPage = (listNo * params.getPageSize()) + params.getPageSize();
		if (endPage > totalPages){
			endPage = totalPages;
		}
		
		// 이전 그룹의 시작페이지 번호 계산
		previousStartPage = startPage - params.getPageSize();
		// 첫번째 목록인 경우 1페이지로 설정
		if (previousStartPage < 0)  previousStartPage = 1;
		
		// 다음 목록의 시작페이지 번호 계산
		nextStartPage = startPage + params.getPageSize();
	}
	
	/** 현재 목록에서 [처음으로] 출력 여부 반환 */
	public boolean isShowFirst() {
		return listNo > 0;
	}
	
	/** 현재 목록에서 [끝으로] 출력 여부 반환 */
	public boolean isShowLast() {
		return endPage < totalPages;
	}
	
	/** 현재 목록에서 [이전목록] 출력 여부 반환 */
	public boolean isShowPrevious() {
		return listNo > 0;
	}
	
	/** 현재 목록에서 [다음목록] 출력 여부 반환 */
	public boolean isShowNext() {
		return endPage < totalPages;
	}
}