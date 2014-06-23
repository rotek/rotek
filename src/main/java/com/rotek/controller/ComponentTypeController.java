/**
 * @Copyright:Copyright (c) 2013 - 2100
 * @Company:JXWY Co.,Ltd.
 */
package com.rotek.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cta.platform.util.ListPager;
import com.rotek.constant.Status;
import com.rotek.entity.ComponentTypeEntity;
import com.rotek.service.impl.ComponentTypeService;

/**
* @ClassName:ComponentTypeController
* @Description: 零件类别控制器
* @Author WangJuZhu
* @date 2014年6月23日 下午2:39:29
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/componenttype")
public class ComponentTypeController {

	@Autowired
	private ComponentTypeService ctypeService;

	/**
	 * @MethodName: toComponentType
	 * @Description: 转到零件类别管理页面
	 * @return
	 * @author WangJuZhu
	 */
	@RequestMapping("toComponentTypes")
	public String toComponentType() {
		return "admin/project/componenttype_index";
	}
	
	/**
	* @MethodName: componentTypeList 
	* @Description: 零件信息列表
	* @param start
	* @param limit
	* @return
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("listComponentTypes")
	public String componentTypeList(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,  // 名称
			@RequestParam(value = "describe", defaultValue = "") String describe,  // 描述说明
			@RequestParam(value = "status", defaultValue = "") Integer status,  // 状态
			HttpServletRequest request, ModelMap modelMap)throws Exception {
		
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		ComponentTypeEntity ctype = new ComponentTypeEntity();
		ctype.setId(id);
		ctype.setName(name);
		ctype.setDescribe(describe);
		ctype.setStatus(status);

		List<ComponentTypeEntity> types = ctypeService.listComponentType(ctype, pager);
		modelMap.put("dataList", types);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @MethodName: addComponentType 
	* @Description: 添加零件类别信息
	* @param request
	* @param response
	* @param model
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("addComponentType")
	public void addComponentType(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "describe", defaultValue = "") String describe,ModelMap model ) throws Exception {

		ComponentTypeEntity ctype = new ComponentTypeEntity();
		ctype.setName(name);
		ctype.setDescribe(describe);
		ctype.setStatus(Status.NEW.getCode());

		List<String> messages = ctypeService.addComponentType(ctype);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: getComponentTypeDetail 
	* @Description: 根据零件类别ID查询零件类别详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("getComponentTypeDetail")
	public String getComponentTypeDetail(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap model) throws SQLException {

		ComponentTypeEntity ctype = ctypeService.getComponentTypeById(id);
		model.put("data", ctype);
		return "jsonView";
	}

	/**
	* @MethodName: modifyComponentType 
	* @Description: 修改零件类别信息
	* @param id
	* @throws IOException
	* @author WangJuZhu
	*/
	@RequestMapping("modifyComponentType")
	public void modifyComponentType(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "describe", defaultValue = "") String describe,
			ModelMap model, HttpServletRequest request,HttpServletResponse response) throws SQLException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		ComponentTypeEntity ctype = new ComponentTypeEntity();
		ctype.setId(id);
		ctype.setName(name);
		ctype.setDescribe(describe);
		ctype.setStatus(Status.NEW.getCode());

		List<String> messages = ctypeService.modifyComponentType(ctype);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: deleteComponentType 
	* @Description: 批量删除零件类别信息
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("deleteComponentType")
	public String deleteComponentType(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap model) throws SQLException {

		List<String> messages = ctypeService.deleteComponentType(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**
	* @MethodName: initBinder 
	* @Description: 对绑定的时间进行格式化处理
	* @param request
	* @param binder
	* @throws Exception
	* @author WangJuZhu
	*/
	@InitBinder
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	

}
