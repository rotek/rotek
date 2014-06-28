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

import com.rotek.constant.SessionParams;
import com.rotek.dao.impl.LoginDao;
import com.rotek.dao.impl.ManagerDao;
import com.rotek.dto.UserDto;

/**
 * @ClassName: IndexController
 * @Description: 首页的控制器
 * @author chenwenpeng
 * @date 2013-5-31 上午10:33:55
 * 
 */
@Controller
@RequestMapping("/front/mycenter")
public class FrontMycenterController {

	@Autowired
	private ManagerDao managerDao;
	@Autowired
	private LoginDao loginDao;

	/**
	 * 返回用户的首页信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toMycenter")
	public String getIndex(HttpServletRequest request, ModelMap modelMap,UserDto user)
			throws SQLException {
		
		UserDto userInDb = loginDao.getUserById(user.getId());
		request.getSession().setAttribute(SessionParams.USER, userInDb);
		return "front/mycenter";
	}
	
	/**修改
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("modify")
	public String modifyMyInfo(HttpServletRequest request, ModelMap modelMap,UserDto frontUser)
			throws SQLException {
		
		managerDao.modifyManager(frontUser);
		
		return "redirect:/front/mycenter/toMycenter";
	}
}
