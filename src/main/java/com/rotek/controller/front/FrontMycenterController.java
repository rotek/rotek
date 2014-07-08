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
	public String toMycenter(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {
		
		return "front/mycenter";
	}
	
	/**修改
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 */
	@RequestMapping("modify")
	public String modifyMyInfo(HttpServletRequest request, ModelMap modelMap,UserDto frontUser)
			throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		
		frontUser.setStatus(UserDto.STATUS_ENABLED);
		String msg = managerDao.modifyManager(frontUser);
		if ("success".equals(msg)) {
			
			UserDto userInSession = (UserDto) request.getSession().getAttribute(SessionParams.USER);
			frontUser.setR_customer_id(userInSession.getR_customer_id());
			frontUser.setR_role_id(frontUser.getR_role_id());
			
			request.getSession().setAttribute(SessionParams.USER, frontUser);
		}else{
			
			modelMap.put("msg", msg);
		}
		
		return this.toMycenter(request, modelMap);
	}
}
