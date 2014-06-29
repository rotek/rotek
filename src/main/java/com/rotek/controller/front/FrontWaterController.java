/**
 * @FileName: IndexController.java
 * @Package com.rotek.controller
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-31 上午10:33:55
 * @version V1.0
 */
package com.rotek.controller.front;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.service.impl.IndexService;
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

	@Autowired
	private IndexService indexservice;

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
			ModelMap modelMap) throws SQLException {

		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>(
				3);
		Map<String, Object> data = new HashMap<String, Object>(2);
		data.put("id", 1);
		data.put("name", "工程");
		dataList.add(data);

		modelMap.put("dataList", dataList);
		return "front/waterMonitorStatistic";
	}

	/**
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("listComponents")
	public String listComponents(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {

		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>(
				3);
		Map<String, Object> data = new HashMap<String, Object>(2);
		data.put("id", 1);
		data.put("name", "泵");
		dataList.add(data);

		modelMap.put("dataList", dataList);
		
		return "jsonView";
	}
	
	/**
	 * 
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("listComponentParts")
	public String listComponentParts(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {
		
		List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>(
				3);
		Map<String, Object> data = new HashMap<String, Object>(2);
		data.put("id", 1);
		data.put("name", "泵前");
		dataList.add(data);
		
		modelMap.put("dataList", dataList);
		
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
		data1.put("id", 1);
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
		ssubList.add(DateTimeUtil.getDateByDateAmount(now, -1).getTime());
		ssubList.add(100);
		subList.add(ssubList);
		
		List<Object> ssubList1 = new ArrayList<Object>();
		ssubList1.add(DateTimeUtil.getDateByDateAmount(now, -2).getTime());
		ssubList1.add(10);
		subList.add(ssubList1);
		
		List<List<Object>> subList2 = new ArrayList<List<Object>>();
		List<Object> ssubList2 = new ArrayList<Object>();
		ssubList2.add(DateTimeUtil.getDateByDateAmount(now, -3).getTime());
		ssubList2.add(120);
		subList2.add(ssubList2);
		subList2.add(ssubList1);
		subList2.add(ssubList);
		
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
