package com.rotek.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cta.platform.util.ListPager;
import com.rotek.dto.UserDto;
import com.rotek.service.impl.AgentEstimateInfoService;
import com.rotek.entity.AgentEstimateInfoEntity;
import com.rotek.dto.AgentEstimateInfoDto;

/**
* @ClassName:AgentEstimateController
* @Description:
* @Author liusw
* @date 2014年6月28日 下午1:35:14
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/AgentEstimateInfo") // controller方法路径，每个Controller最好取不一样的路径名字
public class AgentEstimateController {

	@Autowired
	private AgentEstimateInfoService agentestimateinfoService;

	
	/**
	* @MethodName: toAgentEstimateInfoList 
	* @Description:
	* @return
	* @author liusw
	*/
	@RequestMapping("toAgentEstimateInfoList")
	public String toAgentEstimateInfoList() {
		// 返回JSP文件目录及对应的JSP文件
		return "admin/customer/agentestimate";
	}
	
	/**
	* @MethodName: listAgentEstimateInfos 
	* @Description:
	* @param start
	* @param limit
	* @param agentestimateinfo
	* @param request
	* @param user
	* @param modelMap
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("listAgentEstimateInfos")
	public String listAgentEstimateInfos(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			AgentEstimateInfoEntity agentestimateinfo,
			HttpServletRequest request,
			UserDto user,
			ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		List<AgentEstimateInfoDto> agentestimateinfoList = agentestimateinfoService.listAgentEstimateInfos(agentestimateinfo, pager);
		modelMap.put("dataList", agentestimateinfoList);
		modelMap.put("totalCount", pager.getTotalRows());

		return "jsonView";
	}
	
	/**
	* @MethodName: listCustomers 
	* @Description:
	* @param modelMap
	* @param request
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("listCustomers")
	public String listCustomers (ModelMap modelMap, HttpServletRequest request) throws Exception {
		
		List<Map<String,Object>> customerList = agentestimateinfoService.listCustomers();
		
		modelMap.put("dataList", customerList);
		return "jsonView";
	}
	
	/**
	* @MethodName: getAgentEstimateInfoDetail 
	* @Description:
	* @param modelMap
	* @param id
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("getAgentEstimateInfoDetail")
	public String getAgentEstimateInfoDetail(ModelMap modelMap,
			@RequestParam(value="id", defaultValue = "") Integer id) throws Exception{

		AgentEstimateInfoDto agentestimateinfodto = agentestimateinfoService.getAgentEstimateInfoDetail(id);
		modelMap.put("data", agentestimateinfodto);
		return "jsonView";
	}
	
	/**
	* @MethodName: deleteAgentEstimateInfo 
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author liusw
	*/
	@RequestMapping("deleteAgentEstimateInfo")
	public String deleteAgentEstimateInfo(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = agentestimateinfoService.deleteComplaintInfo(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	/**
	* @MethodName: addAgentEstimateInfo 
	* @Description:
	* @param request
	* @param response
	* @param DLSXJPJ
	* @param DLSGLXZ
	* @param R_CUSTOMER_ID
	* @param STATUS
	* @param model
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("addAgentEstimateInfo")
	public String addAgentEstimateInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "dlsxjpj", defaultValue = "") Integer DLSXJPJ,
			@RequestParam(value = "dlsglxz", defaultValue = "0") String DLSGLXZ,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer R_CUSTOMER_ID,
			@RequestParam(value="status", defaultValue="1") Integer STATUS,
			ModelMap model ) throws Exception {
		
		AgentEstimateInfoEntity agentestimateinfo = new AgentEstimateInfoEntity();
		agentestimateinfo.setDlsxjpj(DLSXJPJ);
		agentestimateinfo.setDlsglxz(DLSGLXZ);
		agentestimateinfo.setR_customer_id(R_CUSTOMER_ID);
		agentestimateinfo.setStatus(STATUS);

		List<String> messages = agentestimateinfoService.addAgentEstimateInfo(agentestimateinfo);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		return "jsonView";
	}
	
	/**
	* @MethodName: modifyAgentEstimateInfo 
	* @Description:
	* @param request
	* @param response
	* @param ID
	* @param DLSXJPJ
	* @param DLSGLXZ
	* @param R_CUSTOMER_ID
	* @param STATUS
	* @param model
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("modifyAgentEstimateInfo")
	public String modifyAgentEstimateInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "id", defaultValue = "0") Integer ID,
			@RequestParam(value = "dlsxjpj", defaultValue = "") Integer DLSXJPJ,
			@RequestParam(value = "dlsglxz", defaultValue = "0") String DLSGLXZ,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer R_CUSTOMER_ID,
			@RequestParam(value="status", defaultValue="1") Integer STATUS,
			ModelMap model ) throws Exception {
		
		AgentEstimateInfoEntity agentestimateinfo = new AgentEstimateInfoEntity();
		agentestimateinfo.setId(ID);
		agentestimateinfo.setDlsxjpj(DLSXJPJ);
		agentestimateinfo.setDlsglxz(DLSGLXZ);
		agentestimateinfo.setR_customer_id(R_CUSTOMER_ID);
		agentestimateinfo.setStatus(STATUS);

		List<String> messages = agentestimateinfoService.modifyAgentEstimateInfo(agentestimateinfo);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		return "jsonView";
	}

}
