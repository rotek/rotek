/**
* @FileName: LoginController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-31 上午11:46:24
* @version V1.0
*/
package com.rotek.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.service.impl.LoginService;

/**
 * @ClassName: LoginController
 * @Description: 登录控制器
 * @author chenwenpeng
 * @date 2013-5-31 上午11:46:24
 *
 */
@Controller
@RequestMapping("/admin/login")
public class LoginController {
	@Autowired
	private LoginService loginService;
	/**跳转到登陆页面
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("toLogin")
	public String toLogin(HttpServletRequest request,HttpServletRequest response){

		return "admin/login/login";
	}

	/**验证用户的登陆
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("login")
	public String Login(HttpServletRequest request,
			ModelMap model,
			@RequestParam(value = "username",defaultValue="") String username,
			@RequestParam(value = "password",defaultValue="") String password) throws Exception{

		String flag = loginService.login(username,password,request);
		model.put("result", flag);
		return "jsonView";
	}


	/**
	* @Title: logout
	* @Description:退出
	* @return
	* @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("logout")
	public String logout(HttpServletRequest request) throws Exception{

		request.getSession().invalidate();
		return "redirect:/admin/login/toLogin";
	}
}
