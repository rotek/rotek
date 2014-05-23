/**
* @FileName: ButtonController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 下午03:20:21
* @version V1.0
*/
package com.rotek.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.entity.ButtonEntity;
import com.cta.platform.util.ListPager;
import com.rotek.service.impl.ButtonService;

/**
 * @ClassName: ButtonController
 * @Description: 按钮控制器
 * @author chenwenpeng
 * @date 2013-6-26 下午03:20:21
 *
 */
@Controller
@RequestMapping("/admin/button")
public class ButtonController {

	@Autowired
	private ButtonService buttonService;

	/**
	* @Title: toButtons
	* @Description: 转到按钮模块
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("/toButtons")
	public String toButtons(){

		return "admin/button/buttons";
	}

	/**
	 * @throws SQLException
	* @Title: listButtons_s
	* @Description: 列出button的简单信息
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("listButtons")
	public String listButtons(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			@RequestParam(value="id", defaultValue = "") Integer id,
			@RequestParam(value="button_name", defaultValue = "") String button_name,
			@RequestParam(value="action", defaultValue = "") String action,
			@RequestParam(value="memo", defaultValue = "") String memo,
			@RequestParam(value="icon", defaultValue = "") String icon,
			@RequestParam(value="sort", defaultValue = "") Integer sort,
			@RequestParam(value="status", defaultValue = "") Integer status,
			ModelMap modelMap) throws SQLException{
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		ButtonEntity button = new ButtonEntity();
		button.setId(id);
		button.setButton_name(button_name);
		button.setAction(action);
		button.setMemo(memo);
		button.setIcon(icon);
		button.setSort(sort);
		button.setStatus(status);

		List<ButtonEntity> buttonList = buttonService.listButtons(button,pager);
		modelMap.put("dataList", buttonList);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	 * @throws SQLException
	* @Title: listButtons_s
	* @Description: 列出button的简单信息(id name 或者sort)
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("listButtons_s")
	public String listButtons_s(ModelMap modelMap) throws SQLException{

		List<Map<String,Object>> menuList = buttonService.listButtons_s();
		modelMap.put("dataList", menuList);
		return "jsonView";
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addButton
	* @Description:
	* @param @param button_name
	* @param @param action
	* @param @param memo
	* @param @param icon
	* @param @param sort
	* @param @param status
	* @param @param modelMap
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("addButton")
	public String addButton(
			@RequestParam(value="button_name", defaultValue = "") String button_name,
			@RequestParam(value="action", defaultValue = "") String action,
			@RequestParam(value="memo", defaultValue = "") String memo,
			@RequestParam(value="icon", defaultValue = "") String icon,
			@RequestParam(value="sort", defaultValue = "") Integer sort,
			@RequestParam(value="status", defaultValue = "") Integer status,
			ModelMap modelMap) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		ButtonEntity button = new ButtonEntity();
		button.setButton_name(button_name);
		button.setAction(action);
		button.setMemo(memo);
		button.setIcon(icon);
		button.setSort(sort);
		button.setStatus(status);

		List<String> messages = buttonService.addButton(button);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: getButtonDetail
	* @Description: 详情
	* @param @param id
	* @param @param modelMap
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getButtonDetail")
	public String getButtonDetail(
			@RequestParam(value="id", defaultValue = "") Integer id,
			ModelMap modelMap) throws SQLException{

		ButtonEntity manager = buttonService.getButtonDetail(id);
		modelMap.put("data",manager);
		return "jsonView";
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Title: addButton
	 * @Description:
	 * @param @param button_name
	 * @param @param action
	 * @param @param memo
	 * @param @param icon
	 * @param @param sort
	 * @param @param status
	 * @param @param modelMap
	 * @param @return
	 * @param @throws SQLException
	 * @return String
	 * @throws
	 */
	@RequestMapping("modifyButton")
	public String modifyButton(
			@RequestParam(value="id", defaultValue = "") Integer id,
			@RequestParam(value="button_name", defaultValue = "") String button_name,
			@RequestParam(value="action", defaultValue = "") String action,
			@RequestParam(value="memo", defaultValue = "") String memo,
			@RequestParam(value="icon", defaultValue = "") String icon,
			@RequestParam(value="sort", defaultValue = "") Integer sort,
			@RequestParam(value="status", defaultValue = "") Integer status,
			ModelMap modelMap) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		ButtonEntity button = new ButtonEntity();
		button.setId(id);
		button.setButton_name(button_name);
		button.setAction(action);
		button.setMemo(memo);
		button.setIcon(icon);
		button.setSort(sort);
		button.setStatus(status);

		List<String> messages = buttonService.modifyButton(button);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: deleteButton
	* @Description: 删除
	* @param @param id
	* @param @param modelMap
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteButton")
	public String deleteButton(
			@RequestParam(value="ids", defaultValue = "") String ids,
			ModelMap modelMap) throws SQLException{

		List<String> messages = buttonService.deleteButton(ids);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}
}
