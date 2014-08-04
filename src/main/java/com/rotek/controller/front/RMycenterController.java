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
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.constant.SessionParams;
import com.rotek.dao.impl.LoginDao;
import com.rotek.dao.impl.ManagerDao;
import com.rotek.dto.UserDto;

/**
* @ClassName:RMycenterController
* @Description: 我的空间 控制器
* @Author WangJuZhu
* @date 2014年8月4日 下午3:50:55
* @Version:1.1.0
*/
@Controller
@RequestMapping("/front/rmycenter")
public class RMycenterController {

	@Autowired
	private ManagerDao managerDao;
	
	@Autowired
	private LoginDao loginDao;

	/**
	* @MethodName: toMycenter 
	* @Description: 转到登录后首页面
	* @param request
	* @param modelMap
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("toIndex")
	public String toIndex(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {
		return "front/mycenter/index";
	}
	
	/**
	* @MethodName: toMycenter 
	* @Description: 转到我的空间页面
	* @param request
	* @param modelMap
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("toMycenter")
	public String toMycenter(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {
		return "front/mycenter/mycenter";
	}
	
    /**
    * @MethodName: baseInfo 
    * @Description: 查看我的基本信息、工程信息、零件信息等
    * @param flag
    * info 客户基本信息 	resource 客户资源信息 
    * project 客户工程信息 	component 客户零件信息        shenbao 客户申报 
    * @param model
    * @return
    * @author WangJuZhu
    */
    @RequestMapping("/baseInfo")
	public String baseInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "flag", defaultValue = "") String flag,
			ModelMap model) throws Exception {
		
    	model.put("flagStr", flag);
		return "front/mycenter/baseinfo";
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
