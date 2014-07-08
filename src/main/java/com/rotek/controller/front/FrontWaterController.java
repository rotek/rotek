/**
 * @FileName: IndexController.java
 * @Package com.rotek.controller
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013531 上午10:33:55
 * @version V1.0
 */
package com.rotek.controller.front;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.dto.UserDto;
import com.rotek.entity.ComponentDetailEntity;
import com.rotek.entity.ComponentGroupEntity;
import com.rotek.entity.ProjectEntity;
import com.rotek.service.impl.ComponentDetailService;
import com.rotek.service.impl.ComponentGroupService;
import com.rotek.service.impl.ProjectService;
import com.rotek.util.DateTimeUtil;

/**
 * 前台水质控制器
 * 
 * @author chenwenpeng
 * 
 */
@Controller
@RequestMapping("/front/water")
public class FrontWaterController {

	@Resource
	private ProjectService projectService;
 
	@Resource
	private ComponentGroupService componentGroupService;
	
	@Resource
	private  ComponentDetailService componentDetailService;
	
	/**
	 * 返回水质监测页
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toWaterMonitor")
	public String getIndex(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {

		return "front/waterMonitorIndex";
	}

	/**
	 * 返回
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toWaterMonitorStatistic")
	public String toWaterMonitorStatistic(HttpServletRequest request,
			ModelMap modelMap,UserDto user) throws SQLException {

		List<ProjectEntity> projectList = projectService.getProjectListByCustomerId(user.getR_customer_id());

		modelMap.put("dataList", projectList);
		return "front/waterMonitorStatistic";
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("listComponents")
	public String listComponents(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(defaultValue="0",value="projectId") Integer projectId)
			throws SQLException {

		List<ComponentGroupEntity> cgList = componentGroupService.getComGroupListByProjectId(projectId);

		modelMap.put("dataList", cgList);
		
		return "jsonView";
	}
	
	/**
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("listComponentParts")
	public String listComponentParts(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(defaultValue="0",value="componentId") Integer componentId)
			throws SQLException {
		
		List<ComponentDetailEntity> cdList = componentDetailService.getListByComponentGroupId(componentId);
		modelMap.put("dataList", cdList);
		
		return "jsonView";
	}
	
	/**
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("listMonitorItems")
	public String listMonitorItems(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {
		
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>(
				3);
		Map<String, Object> data = new HashMap<String, Object>(2);
		data.put("id", 1);
		data.put("name", "PH值");
		dataList.add(data);
		Map<String, Object> data1 = new HashMap<String, Object>(2);
		data1.put("id", 2);
		data1.put("name", "硬度");
		dataList.add(data1);
		
		modelMap.put("dataList", dataList);
		
		return "jsonView";
	}

	@RequestMapping("listStatistic")
	public String listStatistic(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "projectId", defaultValue = "") Integer projectId)
			throws SQLException {
		
		Date now = new Date();
		List<Map<String,Object>> dataList = new ArrayList<Map<String,Object>>(3);

		List<List<Object>> subList = new ArrayList<List<Object>>();
		List<Object> ssubList = new ArrayList<Object>(2);
		ssubList.add(DateTimeUtil.getDateByDateAmount(now, 1).getTime());
		ssubList.add(100);
		subList.add(ssubList);
		
		List<Object> ssubList1 = new ArrayList<Object>();
		ssubList1.add(DateTimeUtil.getDateByDateAmount(now, 2).getTime());
		ssubList1.add(10);
		subList.add(ssubList1);
		
		List<List<Object>> subList2 = new ArrayList<List<Object>>();
		List<Object> ssubList2 = new ArrayList<Object>();
		ssubList2.add(DateTimeUtil.getDateByDateAmount(now, 3).getTime());
		ssubList2.add(120);
		
		List<Object> ssubList3 = new ArrayList<Object>();
		ssubList3.add(DateTimeUtil.getDateByDateAmount(now, 4).getTime());
		ssubList3.add(150);
		
		List<Object> ssubList4 = new ArrayList<Object>();
		ssubList4.add(DateTimeUtil.getDateByDateAmount(now, 5).getTime());
		ssubList4.add(130);
		
		List<Object> ssubList5 = new ArrayList<Object>();
		ssubList5.add(DateTimeUtil.getDateByDateAmount(now, 6).getTime());
		ssubList5.add(180);
		
		List<Object> ssubList6 = new ArrayList<Object>();
		ssubList6.add(DateTimeUtil.getDateByDateAmount(now, 7).getTime());
		ssubList6.add(200);
		
		List<Object> ssubList7 = new ArrayList<Object>();
		ssubList7.add(DateTimeUtil.getDateByDateAmount(now, 8).getTime());
		ssubList7.add(210);
		
		subList2.add(ssubList2);
		subList2.add(ssubList1);
		subList2.add(ssubList3);
		subList2.add(ssubList4);
		subList2.add(ssubList5);
		subList2.add(ssubList6);
		subList2.add(ssubList7);
		
		
		
		
		Map<String,Object> data = new HashMap<String,Object>(2);
		data.put("id", 1);
		data.put("name", "name");
		data.put("dataList", subList);
		
		Map<String,Object> data1 = new HashMap<String,Object>(2);
		data1.put("id", 2);
		data1.put("name", "name1");
		data1.put("dataList", subList2);
		
		dataList.add(data);
		dataList.add(data1);
		
		modelMap.put("dataList", dataList);
		
		return "jsonView";
	}

	
}
