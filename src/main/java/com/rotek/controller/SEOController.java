/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.rotek.controller;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.dto.UserDto;
import com.rotek.entity.SEOEntity;
import com.rotek.platform.util.ListPager;
import com.rotek.service.impl.SEOService;

/**
 * @ClassName: SEOController
 * @Description: 
 * @author chenwenpeng
 * @date 2013-8-16 上午10:34:54
 */
@Controller
@RequestMapping("/admin/seo")
public class SEOController {

	@Autowired
	private SEOService seoService;
	
	@RequestMapping("toSEOs")
	public String toSEOs() {
		return "admin/seo/seos";
	}
	
	
	/**
	* @Title: listSEOs
	* @Description: 列出所有的seo信息
	* @param @param start
	* @param @param limit
	* @param @param id
	* @param @param type
	* @param @param request
	* @param @param user
	* @param @param modelMap
	* @param @return
	* @param @throws Exception 
	* @return String 
	* @throws 
	*/ 
	@RequestMapping("listSEOs")
	public String listSEOs(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			
			@RequestParam(value="id", defaultValue = "") Integer id,
			@RequestParam(value="type", defaultValue = "") Integer type,
			HttpServletRequest request,UserDto user,ModelMap modelMap) throws Exception {
		
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		SEOEntity seo = new SEOEntity();
		seo.setId(id);
		seo.setType(type);

		List<SEOEntity> SEOList = seoService.listSEOs(seo,pager);
		modelMap.put("dataList", SEOList);
		modelMap.put("totalCount", SEOList.size());
		return "jsonView";
	}
	
	/**
	* @Title: addSEO
	* @Description: 
	* @param @param type
	* @param @param title
	* @param @param keywords
	* @param @param description
	* @param @param request
	* @param @param user
	* @param @param model
	* @param @return
	* @param @throws Exception 
	* @return String 
	* @throws 
	*/ 
	@RequestMapping("addSEO")
	public String addSEO(
			@RequestParam(value="type", defaultValue = "") Integer type,
			@RequestParam(value="title", defaultValue = "") String title,
			@RequestParam(value="keywords", defaultValue = "") String keywords,
			@RequestParam(value="description", defaultValue = "") String description,
			HttpServletRequest request,UserDto user,ModelMap model) throws Exception {
		
		SEOEntity seo = new SEOEntity();
		seo.setTitle(title);
		seo.setKeywords(keywords);
		seo.setDescription(description);
		seo.setType(type);

		List<String> messages = seoService.addSEO(seo);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	/**
	* @Title: getSEODetail
	* @Description: 
	* @param @param id
	* @param @param request
	* @param @param user
	* @param @param model
	* @param @return
	* @param @throws Exception 
	* @return String 
	* @throws 
	*/ 
	@RequestMapping("getSEODetail")
	public String getSEODetail(
			@RequestParam(value="id", defaultValue = "") Integer id,
			HttpServletRequest request,UserDto user,ModelMap model) throws Exception {
		
		SEOEntity seo = seoService.getSEODetail(id);
		model.put("data", seo);
		return "jsonView";
	}
	
	/**
	* @Title: modifySEO
	* @Description: 
	* @param @param id
	* @param @param type
	* @param @param title
	* @param @param keywords
	* @param @param description
	* @param @param request
	* @param @param user
	* @param @param model
	* @param @return
	* @param @throws Exception 
	* @return String 
	* @throws 
	*/ 
	@RequestMapping("modifySEO")
	public String modifySEO(
			@RequestParam(value="id", defaultValue = "") Integer id,
			@RequestParam(value="type", defaultValue = "") Integer type,
			@RequestParam(value="title", defaultValue = "") String title,
			@RequestParam(value="keywords", defaultValue = "") String keywords,
			@RequestParam(value="description", defaultValue = "") String description,
			HttpServletRequest request,UserDto user,ModelMap model) throws Exception {
		
		SEOEntity seo = new SEOEntity();
		seo.setId(id);
		seo.setTitle(title);
		seo.setKeywords(keywords);
		seo.setDescription(description);
		seo.setType(type);

		List<String> messages = seoService.modifySEO(seo);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	/**
	* @Title: deleteSEO
	* @Description: 
	* @param @param ids
	* @param @param model
	* @param @return
	* @param @throws SQLException 
	* @return String 
	* @throws 
	*/ 
	@RequestMapping("deleteSEO")
	public String deleteSEO(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = seoService.deleteSEO(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

}
