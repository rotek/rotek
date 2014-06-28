package com.rotek.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.rotek.dto.UserDto;
import com.rotek.service.impl.UnProcessInfoService;
import com.rotek.dto.UnProcessInfoDto;
import com.rotek.entity.UnProcessInfoEntity;

/**
* @ClassName:UnProcessInfoController
* @Description:
* @Author liusw
* @date 2014年6月28日 下午1:34:55
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/UnProcessInfo") // controller方法路径，每个Controller最好取不一样的路径名字
public class UnProcessInfoController {

	@Autowired
	private UnProcessInfoService unprocessinfoService;

	
	/**
	* @MethodName: toUnProcessInfoList 
	* @Description:
	* @return
	* @author liusw
	*/
	@RequestMapping("toUnProcessInfoList")
	public String toUnProcessInfoList() {
		// 返回JSP文件目录及对应的JSP文件
		return "admin/customer/unprocessinfo";
	}
	
	/**
	* @MethodName: listUnProcessInfos 
	* @Description:
	* @param start
	* @param limit
	* @param unprocessinfo
	* @param request
	* @param user
	* @param modelMap
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("listUnProcessInfos")
	public String listUnProcessInfos(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			UnProcessInfoEntity unprocessinfo,
			HttpServletRequest request,
			UserDto user,
			ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		List<UnProcessInfoDto> unprocessinfoList = unprocessinfoService.listUnProcessInfos(unprocessinfo, pager);
		modelMap.put("dataList", unprocessinfoList);
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
		
		List<Map<String,Object>> customerList = unprocessinfoService.listCustomers();
		
		modelMap.put("dataList", customerList);
		return "jsonView";
	}
	
	/**
	* @MethodName: getUnProcessInfoDetail 
	* @Description:
	* @param modelMap
	* @param id
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("getUnProcessInfoDetail")
	public String getUnProcessInfoDetail(ModelMap modelMap,
			@RequestParam(value="id", defaultValue = "") Integer id) throws Exception{

		UnProcessInfoDto unprocessinfodto = unprocessinfoService.getUnProcessInfoDetail(id);
		modelMap.put("data", unprocessinfodto);
		return "jsonView";
	}
	
	/**
	* @MethodName: deleteUnProcessInfo 
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author liusw
	*/
	@RequestMapping("deleteUnProcessInfo")
	public String deleteUnProcessInfo(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = unprocessinfoService.deleteUnProcessInfo(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	/**
	* @MethodName: addUnProcessInfo 
	* @Description:
	* @param request
	* @param response
	* @param SPECIFIC_PART
	* @param SPECIFIC_BH
	* @param ERRORINFOLB
	* @param R_CUSTOMER_ID
	* @param R_PROJECT_ID
	* @param JLRQ
	* @param ISDEALED
	* @param STATUS
	* @param model
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("addUnProcessInfo")
	public String addUnProcessInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "specific_part", defaultValue = "") String SPECIFIC_PART,
			@RequestParam(value = "specific_bh", defaultValue = "0") String SPECIFIC_BH,
			@RequestParam(value = "errorinfolb", defaultValue = "0") Integer ERRORINFOLB,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer R_CUSTOMER_ID,
			@RequestParam(value = "r_project_id", defaultValue = "0") Integer R_PROJECT_ID,
			@RequestParam(value = "jlrq", defaultValue = "") Date JLRQ,  
			@RequestParam(value="isdealed", defaultValue="1") Integer ISDEALED,
			@RequestParam(value="status", defaultValue="1") Integer STATUS,
			ModelMap model ) throws Exception {
		
		UnProcessInfoEntity unprocessInfo = new UnProcessInfoEntity();
		unprocessInfo.setSpecific_part(SPECIFIC_PART);
		unprocessInfo.setSpecific_bh(SPECIFIC_BH);
		unprocessInfo.setErrorinfolb(ERRORINFOLB);
		unprocessInfo.setR_customer_id(R_CUSTOMER_ID);
		unprocessInfo.setR_project_id(R_PROJECT_ID);
		unprocessInfo.setJlrq(JLRQ);
		unprocessInfo.setIsdealed(ISDEALED);
		unprocessInfo.setStatus(STATUS);

		List<String> messages = unprocessinfoService.addUnProcessInfo(unprocessInfo);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		return "jsonView";
	}
	
	/**
	* @MethodName: modifyUnProcessInfo 
	* @Description:
	* @param request
	* @param response
	* @param ID
	* @param SPECIFIC_PART
	* @param SPECIFIC_BH
	* @param ERRORINFOLB
	* @param R_CUSTOMER_ID
	* @param R_PROJECT_ID
	* @param JLRQ
	* @param ISDEALED
	* @param STATUS
	* @param model
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("modifyUnProcessInfo")
	public String modifyUnProcessInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "id", defaultValue = "0") Integer ID,
			@RequestParam(value = "specific_part", defaultValue = "") String SPECIFIC_PART,
			@RequestParam(value = "specific_bh", defaultValue = "0") String SPECIFIC_BH,
			@RequestParam(value = "errorinfolb", defaultValue = "0") Integer ERRORINFOLB,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer R_CUSTOMER_ID,
			@RequestParam(value = "r_project_id", defaultValue = "0") Integer R_PROJECT_ID,
			@RequestParam(value = "jlrq", defaultValue = "") Date JLRQ,  
			@RequestParam(value="isdealed", defaultValue="1") Integer ISDEALED,
			@RequestParam(value="status", defaultValue="1") Integer STATUS,
			ModelMap model ) throws Exception {
		
		UnProcessInfoEntity unprocessInfo = new UnProcessInfoEntity();
		unprocessInfo.setId(ID);
		unprocessInfo.setSpecific_part(SPECIFIC_PART);
		unprocessInfo.setSpecific_bh(SPECIFIC_BH);
		unprocessInfo.setErrorinfolb(ERRORINFOLB);
		unprocessInfo.setR_customer_id(R_CUSTOMER_ID);
		unprocessInfo.setR_project_id(R_PROJECT_ID);
		unprocessInfo.setJlrq(JLRQ);
		unprocessInfo.setIsdealed(ISDEALED);
		unprocessInfo.setStatus(STATUS);

		List<String> messages = unprocessinfoService.modifyUnProcessInfo(unprocessInfo);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
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
