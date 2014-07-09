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
import com.rotek.entity.AlgorithmTypeEntity;
import com.rotek.service.impl.AlgorithmTypeService;

/**
* @ClassName:AlgorithmTypeController
* @Description: 算法控制器
* @Author WangJuZhu
* @date 2014年7月9日 下午4:31:43
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/algorithmtype")
public class AlgorithmTypeController {

	@Autowired
	private AlgorithmTypeService ctypeService;

	/**
	 * @MethodName: toAlgorithmType
	 * @Description: 转到算法管理页面
	 * @return
	 * @author WangJuZhu
	 */
	@RequestMapping("toAlgorithmTypes")
	public String toAlgorithmType() {
		return "admin/algorithms/algorithmtype";
	}
	
	/**
	* @MethodName: componentTypeList 
	* @Description: 算法信息列表
	* @param start
	* @param limit
	* @return
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("listAlgorithmTypes")
	public String componentTypeList(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,  // 名称
			@RequestParam(value = "description", defaultValue = "") String description,  // 描述说明
			@RequestParam(value = "status", defaultValue = "") Integer status,  // 状态
			HttpServletRequest request, ModelMap modelMap)throws Exception {
		
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		AlgorithmTypeEntity ctype = new AlgorithmTypeEntity();
		ctype.setId(id);
		ctype.setName(name);
		ctype.setDescription(description);

		List<AlgorithmTypeEntity> types = ctypeService.listAlgorithmType(ctype, pager);
		modelMap.put("dataList", types);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @MethodName: addAlgorithmType 
	* @Description: 添加算法信息
	* @param request
	* @param response
	* @param model
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("addAlgorithmType")
	public void addAlgorithmType(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "description", defaultValue = "") String description,ModelMap model ) throws Exception {

		AlgorithmTypeEntity ctype = new AlgorithmTypeEntity();
		ctype.setName(name);
		ctype.setDescription(description);

		List<String> messages = ctypeService.addAlgorithmType(ctype);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: getAlgorithmTypeDetail 
	* @Description: 根据算法ID查询算法详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("getAlgorithmTypeDetail")
	public String getAlgorithmTypeDetail(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap model) throws SQLException {

		AlgorithmTypeEntity ctype = ctypeService.getAlgorithmTypeById(id);
		model.put("data", ctype);
		return "jsonView";
	}

	/**
	* @MethodName: modifyAlgorithmType 
	* @Description: 修改算法信息
	* @param id
	* @throws IOException
	* @author WangJuZhu
	*/
	@RequestMapping("modifyAlgorithmType")
	public void modifyAlgorithmType(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "description", defaultValue = "") String description,
			ModelMap model, HttpServletRequest request,HttpServletResponse response) throws SQLException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		AlgorithmTypeEntity ctype = new AlgorithmTypeEntity();
		ctype.setId(id);
		ctype.setName(name);
		ctype.setDescription(description);

		List<String> messages = ctypeService.modifyAlgorithmType(ctype);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: deleteAlgorithmType 
	* @Description: 批量删除算法信息
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("deleteAlgorithmType")
	public String deleteAlgorithmType(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap model) throws SQLException {

		List<String> messages = ctypeService.deleteAlgorithmType(ids);
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
