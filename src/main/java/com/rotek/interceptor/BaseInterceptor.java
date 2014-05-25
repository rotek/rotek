/**
* @FileName: BaseInterceptor.java
* @Package com.cta.platform.interceptor
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-6 下午03:07:15
* @version V1.0
*/
package com.rotek.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @ClassName: BaseInterceptor
 * @Description: 拦截器的父类
 * @author chenwenpeng
 * @date 2013-5-6 下午03:07:15
 *
 */
public class BaseInterceptor implements HandlerInterceptor{

	/* (no Javadoc)
	* Title: preHandle
	* Description:
	* @param request
	* @param response
	* @param handler
	* @return
	* @throws Exception
	* @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
	*/
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {

		return true;
	}

	/* (no Javadoc)
	* Title: postHandle
	* Description:
	* @param request
	* @param response
	* @param handler
	* @param modelAndView
	* @throws Exception
	* @see org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.web.servlet.ModelAndView)
	*/
	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

	}

	/* (no Javadoc)
	* Title: afterCompletion
	* Description:
	* @param request
	* @param response
	* @param handler
	* @param ex
	* @throws Exception
	* @see org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, java.lang.Exception)
	*/
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

	}

}
