/**
 * @FileName: LoginInterceptor.java
 * @Package com.cta.platform.interceptor
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-6-3 下午04:09:49
 * @version V1.0
 */
package com.rotek.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.rotek.constant.SessionParams;
import com.rotek.dao.impl.LoginDao;
import com.rotek.dto.UserDto;

/**
 * @ClassName: LoginInterceptor
 * @Description: 登录的拦截器
 * @author chenwenpeng
 * @date 2013-6-3 下午04:09:49
 * 
 */
public class FrontLoginInterceptor extends BaseInterceptor {

	@Autowired
	private LoginDao loginDao;

	/*
	 * (no Javadoc) Title: preHandle Description:
	 * 
	 * @param request
	 * 
	 * @param response
	 * 
	 * @param handler
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see
	 * com.cta.platform.interceptor.BaseInterceptor#preHandle(javax.servlet.
	 * http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		if (null != session && null != session.getAttribute("user")) {

			return true;
		} else {

			String url = request.getRequestURI();
			if (null != url && url.length() > 5) {
				String url_last = url.substring(url.length() - 5, url.length());
				if ("login".equals(url_last) || "Login".equals(url_last)) {
					return true;
				}
				
				if ("egist".equals(url_last) || "Egist".equals(url_last)) {
					return true;
				}
			}
			request.getRequestDispatcher("/front/login/toLogin").forward(request, response);
			return false;
		}
	}
}
