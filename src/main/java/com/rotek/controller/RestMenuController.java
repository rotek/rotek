/**
* @FileName: RestMenuController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-19 下午04:34:23
* @version V1.0
*/
package com.rotek.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rotek.constant.DataStatus;
import com.rotek.dto.UserDto;
import com.rotek.entity.RestMenuEntity;
import com.cta.platform.util.ListPager;
import com.rotek.service.impl.RestMenuService;

/**
 * @ClassName: RestMenuController
 * @Description: 店铺菜单设置
 * @author chenwenpeng
 * @date 2013-7-19 下午04:34:23
 *
 */
@Controller
@RequestMapping("/admin/restMenu")
public class RestMenuController {

	@Autowired
	private RestMenuService restMenuService;
	/**
	* @Title: toRestaurants
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("toMenus")
	public String toMenus(){

		return "admin/restaurant/menus";
	}

	/**
	 * @throws SQLException
	* @Title: listMenus
	* @Description: 收我管理的店铺的所有菜单
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
		@RequestParam(value = "recommend", defaultValue = "") Integer recommend,
		@RequestParam(value = "cate_id", defaultValue = "") Integer cate_id,
		@RequestParam(value = "rest_id", defaultValue = "") Integer rest_id,
		@RequestParam(value = "status", defaultValue = "") Integer status,
		UserDto user,HttpServletRequest request,ModelMap modelMap) throws SQLException{

		RestMenuEntity menu = new RestMenuEntity();
		menu.setId(id);
		menu.setName(name);
		menu.setRecommend(recommend);
		menu.setCate_id(cate_id);
		menu.setRest_id(rest_id);
		menu.setStatus(status);

		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);


		modelMap.put("dataList", restMenuService.listMenus(menu,pager,user));
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}


	@RequestMapping("addMenu")
	public void addMenu(
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "price", defaultValue = "") Double price,
			@RequestParam(value = "mix", defaultValue = "") String mix,
			@RequestParam(value = "recommend", defaultValue = "") Integer recommend,
			@RequestParam(value = "descr", defaultValue = "") String descr,
			@RequestParam(value = "cate_id", defaultValue = "") Integer cate_id,
			@RequestParam(value = "rest_id", defaultValue = "") Integer rest_id,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException, IllegalStateException, IOException{

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		RestMenuEntity menu = new RestMenuEntity();
		menu.setName(name);
		menu.setPrice(price);
		menu.setMix(StringUtils.isBlank(mix)?DataStatus.NONE_STRING:mix);
		menu.setDescr(StringUtils.isBlank(descr)?DataStatus.NONE_STRING:descr);
		menu.setRecommend(recommend);
		menu.setCate_id(cate_id);
		menu.setRest_id(rest_id);
		menu.setStatus(status);
		menu.setPic(DataStatus.NONE_STRING);

		List<String> messages = null;
		try{
			messages = restMenuService.addMenu(menu,multipartRequest);
		}catch (Exception e) {
			messages = new ArrayList<String>();
			messages.add("保存出错，如果错误继续出现，请联系工程师解决!");
		}
		JSONObject json = new JSONObject();
		 json.put("success", null == messages ? true : false);
		 json.put("messages", messages);
		 response.setStatus(200);
		 response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
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

		RestMenuEntity menu = restMenuService.getMenuDetail(id);
		modelMap.put("data", menu);
		return "jsonView";
	}

	/**
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyMenu
	* @Description: 修改menu
	* @param @param menu_name
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
	public void modifyMenu(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "price", defaultValue = "") Double price,
			@RequestParam(value = "pic", defaultValue = "") String pic,
			@RequestParam(value = "mix", defaultValue = "") String mix,
			@RequestParam(value = "recommend", defaultValue = "") Integer recommend,
			@RequestParam(value = "descr", defaultValue = "") String descr,
			@RequestParam(value = "cate_id", defaultValue = "") Integer cate_id,
			@RequestParam(value = "rest_id", defaultValue = "") Integer rest_id,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException, IllegalStateException, IOException{

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			RestMenuEntity menu = new RestMenuEntity();
			menu.setId(id);
			menu.setName(name);
			menu.setPrice(price);
			menu.setMix(StringUtils.isBlank(mix)?DataStatus.NONE_STRING:mix);
			menu.setDescr(StringUtils.isBlank(descr)?DataStatus.NONE_STRING:descr);
			menu.setRecommend(recommend);
			menu.setCate_id(cate_id);
			menu.setRest_id(rest_id);
			menu.setStatus(status);
			menu.setPic(StringUtils.isBlank(pic)?DataStatus.NONE_STRING:pic);

			List<String> messages = restMenuService.modifyMenu(menu,multipartRequest);
			JSONObject json = new JSONObject();
			 json.put("success", null == messages ? true : false);
			 json.put("messages", messages);
			 response.setStatus(200);
			 response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
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

		List<String> messages = restMenuService.deleteMenu(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
}
