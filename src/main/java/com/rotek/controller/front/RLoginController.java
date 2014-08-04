package com.rotek.controller.front;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rotek.entity.ManagerEntity;
import com.rotek.service.impl.LoginService;
import com.rotek.service.impl.RegistService;

/**
* @ClassName:RLoginController
* @Description: 登录控制器
* @Author WangJuZhu
* @date 2014年8月4日 下午3:32:51
* @Version:1.1.0
*/
@Controller
@RequestMapping("/front/rlogin")
public class RLoginController {

	@Resource
	private RegistService registService;
	
	@Resource
	private LoginService loginService;

	/**
	* @MethodName: getIndex 
	* @Description: 转到登录页面
	* @param request
	* @param modelMap
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("toLogin")
	public String getIndex(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {
		return "front/login/login";
	}

	/**
	* @MethodName: login 
	* @Description: 登录系统
	* @param request
	* @param modelMap
	* @param manager
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("login")
	public String login(HttpServletRequest request, ModelMap modelMap,
			ManagerEntity manager) throws SQLException {

		String msg = loginService.login(manager.getName(),manager.getPassword(), request);
		
		if ("success".equals(msg)) {
			return "redirect:/front/rmycenter/toIndex";
		} else {
			modelMap.put("msg", msg);
			return "front/login/login";
		}
	}
}
