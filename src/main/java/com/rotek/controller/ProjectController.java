/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.Ltd.
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
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cta.platform.util.ListPager;
import com.rotek.constant.DataStatus;
import com.rotek.dto.GiftDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.GiftEntity;
import com.rotek.service.impl.ProjectService;

/**
* @ClassName:ProjectController
* @Description: 工程信息管理控制器
* @Author WangJuZhu
* @date 2014年6月9日 下午1:48:26
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/projectinfo")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	
	/**
	* @MethodName: toProjectList 
	* @Description: 转到工程信息管理列表页面
	* @return
	* @author WangJuZhu
	*/
	@RequestMapping("toProjectList")
	public String toProjectList(){
		return "admin/project/project";
	}

	/**
	* @Title: listGifts
	* @Description: 列出所有的礼品
	* @param start
	* @param limit
	* @param id
	* @param name
	* @param points
	* @param status
	* @param request
	* @param user
	* @param modelMap
	* @return
	* @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("projectList")
	public String listGifts(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,

			@RequestParam(value="id", defaultValue = "") Integer id,
			@RequestParam(value="name", defaultValue = "") String name,
			@RequestParam(value="points", defaultValue = "") Integer points,
			@RequestParam(value="status", defaultValue = "") Integer status,
			HttpServletRequest request,UserDto user,ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		GiftEntity gift = new GiftEntity();
		gift.setId(id);
		gift.setName(name);
		gift.setPoints(points);
		gift.setStatus(status);

		List<GiftDto> gifts = projectService.listUserGifts(user,gift,pager);
		modelMap.put("dataList", gifts);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}


	/**
	* @Title: addGift
	* @Description: 添加礼品信息
	* @param name
	* @param descr
	* @param points
	* @param status
	* @param model
	* @param request
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @throws IllegalStateException
	* @throws IOException
	* @return String
	* @throws
	*/
	@RequestMapping("addProject")
	public void addProject(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="descr", defaultValue="") String descr,
			@RequestParam(value="points", defaultValue="") Integer points,
			@RequestParam(value="status", defaultValue="") Integer status,
			ModelMap model,HttpServletRequest request,HttpServletResponse response) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException{

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		GiftEntity gift = new GiftEntity();
		gift.setName(name);
		gift.setDescr(descr);
		gift.setPoints(points);
		gift.setStatus(status);
		gift.setPic(DataStatus.NONE_STRING);

		List<String> messages = projectService.addGift(gift,multipartRequest);
		JSONObject json = new JSONObject();
		 json.put("success", null == messages ? true : false);
		 json.put("messages", messages);
		 response.setStatus(200);
		 response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}

	/**
	* @Title: getProjectDetail
	* @Description: 获取礼品详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getProjectDetail")
	public String getProjectDetail(
			@RequestParam(value="id", defaultValue="") Integer id,ModelMap model) throws SQLException{

		GiftEntity gift = projectService.getGiftDetail(id);
		model.put("data", gift);
		return "jsonView";
	}


	/**
	* @Title: modfiyProject
	* @Description:
	* @param id
	* @param name
	* @param pic
	* @param descr
	* @param points
	* @param status
	* @param model
	* @param request
	* @param response
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @throws IllegalStateException
	* @throws IOException
	* @return void
	* @throws
	*/
	@RequestMapping("modifyProject")
	public void modifyProject(
			@RequestParam(value="id", defaultValue="") Integer id,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="pic", defaultValue="") String pic,
			@RequestParam(value="descr", defaultValue="") String descr,
			@RequestParam(value="points", defaultValue="") Integer points,
			@RequestParam(value="status", defaultValue="") Integer status,
			ModelMap model,HttpServletRequest request,HttpServletResponse response) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException{

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		GiftEntity gift = new GiftEntity();
		gift.setId(id);
		gift.setName(name);
		gift.setDescr(descr);
		gift.setPoints(points);
		gift.setStatus(status);
		gift.setPic(pic);

		List<String> messages = projectService.modifyGift(gift,multipartRequest);
		JSONObject json = new JSONObject();
		 json.put("success", null == messages ? true : false);
		 json.put("messages", messages);
		 response.setStatus(200);
		 response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}



	@InitBinder
    protected void initBinder(HttpServletRequest request,
    	    ServletRequestDataBinder binder) throws Exception {
    	    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    	    CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
    	    binder.registerCustomEditor(Date.class, dateEditor);
    }

	/**
	* @Title: deleteProject
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteProject")
	public String deleteProject(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = projectService.deleteGift(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

}