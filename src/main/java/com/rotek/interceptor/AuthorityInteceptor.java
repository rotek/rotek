/**
* @FileName: AuthorityInteceptor.java
* @Package com.cta.platform.interceptor
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-3 下午03:32:36
* @version V1.0
*/
package com.rotek.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.rotek.constant.SessionParams;
import com.rotek.dto.UserDto;
import com.rotek.service.impl.AuthorityService;

/**
 * @ClassName: AuthorityInteceptor
 * @Description:用户权限的控制器
 * @author chenwenpeng
 * @date 2013-5-3 下午03:32:36
 *
 */
public class AuthorityInteceptor extends BaseInterceptor{

	@Autowired
	private AuthorityService authorityService;
	
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		String requestURI = request.getRequestURI();
		//如果这个请求的链接是打开一个菜单的，那么访问库，找出这个用户在这个模块下面的 按钮权限信息
		if(StringUtils.isNotEmpty(requestURI) && requestURI.matches("^[\\S]*/to.*")){
			//用户信息
			UserDto user = (UserDto) request.getSession().getAttribute(SessionParams.USER);
			//菜单在库中的url
			String url_inDB = null;
			String basePath = request.getContextPath();
			if(requestURI.length()>1){
				url_inDB = requestURI.substring(basePath.length(),requestURI.length());
			}
			if(user != null){
				//加载用户权限 
				JSONArray buttonList = authorityService.getButtonList(user.getR_role_id(), url_inDB);
				request.setAttribute("buttonInfoList", buttonList);
			}
		}
		return true;
	}
}
