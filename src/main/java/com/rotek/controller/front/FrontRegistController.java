/**
 * @FileName: IndexController.java
 * @Package com.rotek.controller
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-31 上午10:33:55
 * @version V1.0
 */
package com.rotek.controller.front;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rotek.constant.SessionParams;
import com.rotek.entity.ManagerEntity;
import com.rotek.service.impl.RegistService;

/**
 * @ClassName: IndexController
 * @Description: 注册的控制器
 * @author chenwenpeng
 * @date 2013-5-31 上午10:33:55
 * 
 */
@Controller
@RequestMapping("/front/regist")
public class FrontRegistController {

	@Resource
	private RegistService registService;

	/**
	 * 返回注册页面
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toRegist")
	public String toRegist(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {

		return "front/regist";
	}

	/**
	 * @param request
	 * @param modelMap
	 * @param manager
	 * @return
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("regist")
	public String regist(HttpServletRequest request, ModelMap modelMap,
			ManagerEntity manager) throws SQLException, IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {

		String msg = registService.regist(manager);
		if ("success".equals(msg)) {
			request.getSession().setAttribute(SessionParams.USER, manager);
			return "redirect:/front/mycenter/toMycenter";
		} else {
			modelMap.put("msg", msg);
			return "front/regist";
		}
	}
}
