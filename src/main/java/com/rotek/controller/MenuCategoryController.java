/**
* @FileName: MenuCategoryController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-22 上午09:48:37
* @version V1.0
*/
package com.rotek.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rotek.service.impl.MenuCategoryService;

/**
 * @ClassName: MenuCategoryController
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-22 上午09:48:37
 *
 */
@Controller
@RequestMapping("/admin/menuCategory")
public class MenuCategoryController {

	@Autowired
	private MenuCategoryService menuCategoryService;

	/**
	* @Title: listCategories
	* @Description: 列出菜单的大类
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listCategories_s")
	public String listCategories_s(ModelMap modelMap) throws SQLException{

		List<Map<String,Object>> categories = menuCategoryService.listCategories();
		modelMap.put("dataList", categories);
		return "jsonView";
	}

}
