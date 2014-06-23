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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rotek.service.impl.IndexService;

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

	@Autowired
	private IndexService indexservice;

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

}
