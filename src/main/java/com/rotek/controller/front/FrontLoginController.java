/**
 * @FileName: IndexController.java
 * @Package com.rotek.controller
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-31 上午10:33:55
 * @version V1.0
 */
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
 * @ClassName: IndexController
 * @Description: 登录的控制器
 * @author chenwenpeng
 * @date 2013-5-31 上午10:33:55
 * 
 */
@Controller
@RequestMapping("/front/login")
public class FrontLoginController {

	@Resource
	private RegistService registService;
	@Resource
	private LoginService loginService;

	/**
	 * 返回登录页面
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toLogin")
	public String getIndex(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {

		return "front/login";
	}

	/**
	 * 登录
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("login")
	public String login(HttpServletRequest request, ModelMap modelMap,
			ManagerEntity manager) throws SQLException {

		String msg = loginService.login(manager.getName(),manager.getPassword(), request);
		
		if ("success".equals(msg)) {
			return "redirect:/front/mycenter/toMycenter";
		} else {
			modelMap.put("msg", msg);
			return "front/login";
		}
	}
}
