/**
* @FileName: IndexController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-31 上午10:33:55
* @version V1.0
*/
package com.rotek.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rotek.dto.MenuDto;
import com.rotek.dto.UserDto;
import com.rotek.service.impl.IndexService;

/**
 * @ClassName: IndexController
 * @Description: 首页的控制器
 * @author chenwenpeng
 * @date 2013-5-31 上午10:33:55
 *
 */
@Controller
@RequestMapping("/admin/index")
public class IndexController {

	@Autowired
	private IndexService indexservice;
	/**返回用户的首页信息
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toIndex")
	public String getIndex(HttpServletRequest request,ModelMap modelMap) throws SQLException{

		return "admin/index/index";
	}

	/**返回用户的首页的menulist
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("getMenuList")
	public @ResponseBody List<MenuDto> getMenuList(
			HttpServletRequest request,
			HttpServletResponse response,
			UserDto user,
			ModelMap modelMap) throws SQLException{

		List<MenuDto> menuList = indexservice.listMenu(user.getR_role_id());
		return menuList;
	}

	/**
	* @Title: listTodos
	* @Description:
	* @param user
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listTodo")
	public String listTodo(UserDto user,ModelMap modelMap) throws SQLException{
//		Map<String,Object> todo = indexservice.listTodos(user.getDep_id());
//		
		modelMap.put("data", null);
		return "jsonView";
	}
}
