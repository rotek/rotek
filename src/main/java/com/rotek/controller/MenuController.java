/**
* @FileName: MenuController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 上午11:42:30
* @version V1.0
*/
package com.rotek.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.dto.MenuDto;
import com.rotek.entity.MenuEntity;
import com.cta.platform.util.ListPager;
import com.rotek.service.impl.MenuService;

/**
 * @ClassName: MenuController
 * @Description: 菜单控制器
 * @author chenwenpeng
 * @date 2013-6-26 上午11:42:30
 *
 */
@Controller
@RequestMapping("/admin/menu")
public class MenuController {

	@Autowired
	private MenuService menuService;
	/**
	* @Title: toMenus
	* @Description: 转到menus
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("toMenus")
	public String toMenus(){

		return "admin/menu/menus";
	}

	/**
	 * @throws SQLException
	* @Title: listMenus
	* @Description: 列出所有的菜单
	* @param @param request
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("listMenus")
	public String listMenus(
		@RequestParam(value = "start", defaultValue = "0") Integer start,
		@RequestParam(value = "limit", defaultValue = "15") Integer limit,

		@RequestParam(value = "id", defaultValue = "") Integer id,
		@RequestParam(value = "name", defaultValue = "") String name,
		@RequestParam(value = "super_menu_id", defaultValue = "") Integer super_menu_id,
		@RequestParam(value = "url", defaultValue = "") String url,
		@RequestParam(value = "sort", defaultValue = "") Integer sort,
		@RequestParam(value = "status", defaultValue = "") Integer status,
		HttpServletRequest request,
		ModelMap modelMap) throws SQLException{

		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		MenuEntity menu = new MenuEntity();
		menu.setId(id);
		menu.setName(name);
		menu.setSuper_menu_id(super_menu_id);
		menu.setUrl(url);
		menu.setSort(sort);
		menu.setStatus(status);

		modelMap.put("dataList", menuService.listMenus(menu,pager));
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}


	/**
	 * 列出所有上级菜单
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listMenus_super")
	public String listMenus_super(ModelMap modelMap) throws Exception {

		List<Map<String,Object>> menus  = menuService.listMenus_super();
		modelMap.put("dataList", menus);
		return "jsonView";
	}

	/**
	 * 列出所有菜单名称和排序
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listMenus_sort")
	public String listMenus_sort(ModelMap modelMap) throws Exception {

		List<Map<String,Object>> menus  = menuService.listMenus_sort();
		modelMap.put("dataList", menus);
		return "jsonView";
	}

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addMenu
	* @Description: 添加菜单
	* @param @param id
	* @param @param name
	* @param @param super_menu_id
	* @param @param url
	* @param @param sort
	* @param @param status
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("addMenu")
	public String addMenu(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "super_menu_id", defaultValue = "") Integer super_menu_id,
			@RequestParam(value = "url", defaultValue = "") String url,
			@RequestParam(value = "sort", defaultValue = "") Integer sort,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			ModelMap modelMap) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException{

		MenuEntity menu = new MenuEntity();
		menu.setName(name);
		menu.setSuper_menu_id(super_menu_id);
		menu.setUrl(url);
		menu.setSort(sort);
		menu.setStatus(status);

		List<String> messages = menuService.addMenu(menu);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

	/**
	 * @throws SQLException
	* @Title: getMenuDetail
	* @Description: 获取菜单详情
	* @param @param modelMap
	* @param @param id
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("getMenuDetail")
	public String getMenuDetail(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException{

		MenuDto role = menuService.getMenuDetail(id);
		modelMap.put("data", role);
		return "jsonView";
	}

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyMenu
	* @Description: 修改menu
	* @param @param name
	* @param @param super_menu_id
	* @param @param url
	* @param @param sort
	* @param @param status
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("modifyMenu")
	public String modifyMenu(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "super_menu_id", defaultValue = "") Integer super_menu_id,
			@RequestParam(value = "url", defaultValue = "") String url,
			@RequestParam(value = "sort", defaultValue = "") Integer sort,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			@RequestParam(value = "buttons", defaultValue = "") String buttons,
			ModelMap modelMap) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException{


			MenuEntity menu = new MenuEntity();
			menu.setId(id);
			menu.setName(name);
			menu.setSuper_menu_id(super_menu_id);
			menu.setUrl(url);
			menu.setSort(sort);
			menu.setStatus(status);

			List<String> messages = menuService.modifyMenu(menu,buttons);
			modelMap.put("success", null == messages ? true : false);
			modelMap.put("messages", messages);
			return "jsonView";
	}
	/**删除一个或多个菜单信息
	 * @param roleEntity
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("deleteMenu")
	public String deleteMenu(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = menuService.deleteMenu(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
}
