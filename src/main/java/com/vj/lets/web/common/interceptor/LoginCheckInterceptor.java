package com.vj.lets.web.common.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/**
 * 로그인 체크 Interceptor 구현
 *
 * @author 에너자이조 김기정
 * @since 2023. 9. 4.
 * @version 1.0
 */
@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String requestURI = request.getRequestURI();
		HttpSession session = request.getSession();
		if (session.getAttribute("loginMember") == null) {
			String queryString = request.getQueryString();
			String redirectURI = queryString == null ? requestURI : requestURI + "?" + queryString;
			session.setAttribute("redirectURI", redirectURI);
			response.sendRedirect("/member/login");
			return false;
		}
		return true;
	}
}

