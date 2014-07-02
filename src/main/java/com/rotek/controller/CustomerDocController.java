/**
 * @Copyright:Copyright (c) 2013 - 2100
 * @Company:JXWY Co.Ltd.
 */
package com.rotek.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.io.*;

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
import com.rotek.dto.UserDto;
import com.rotek.entity.CustomerDocEntity;
import com.rotek.service.impl.CustomerDocService;
import com.rotek.dto.CustomerDocDto;

/**
* @ClassName:CustomerDocController
* @Description: 客户资料管理控制器
* @Author liusw
* @date 2014年6月22日 上午9:27:00
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/customerdoc") // controller方法路径，每个Controller最好取不一样的路径名字
public class CustomerDocController {
	
	@Autowired
	private CustomerDocService customerdocService;

	
	/**
	* @MethodName: toProjectList 
	* @Description: 转到客户资料信息管理列表页面
	* @return
	* @author Liusw
	*/
	@RequestMapping("toCustomerDocList")
	public String toCustomerDocList() {
		// 返回JSP文件目录及对应的JSP文件
		return "admin/customer/customerdoc";
	}
	
	/**
	* @MethodName: listCustomerDocs 
	* @Description:
	* @param start
	* @param limit
	* @param customer
	* @param request
	* @param user
	* @param modelMap
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("listCustomerDocs")
	public String listCustomerDocs(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			CustomerDocEntity customerdoc,
			HttpServletRequest request,
			UserDto user,
			ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		List<CustomerDocDto> customerdocList = customerdocService.listCustomerDocs(customerdoc, pager);
		modelMap.put("dataList", customerdocList);
		modelMap.put("totalCount", pager.getTotalRows());

		return "jsonView";
	}
	
	/**
	* @MethodName: addCustomerDoc 
	* @Description:
	* @param request
	* @param response
	* @param KHZLMC
	* @param KHZLLB
	* @param R_CUSTOMER_ID
	* @param KHZLFJ
	* @param DLSZJYXQ
	* @param STATUS
	* @param model
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("addCustomerDoc")
	public void addCustomerDoc(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "khzlmc", defaultValue = "") String KHZLMC,
			@RequestParam(value = "khzllb", defaultValue = "0") Integer KHZLLB,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer R_CUSTOMER_ID,
			@RequestParam(value = "dlszjyxq", defaultValue = "") Date DLSZJYXQ,  
			@RequestParam(value="status", defaultValue="1") Integer STATUS,
			ModelMap model ) throws Exception {
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CustomerDocEntity customerdoc = new CustomerDocEntity();
		customerdoc.setKhzlmc(KHZLMC);
		customerdoc.setKhzllb(KHZLLB);
		customerdoc.setR_customer_id(R_CUSTOMER_ID);
		customerdoc.setDlszjyxq(DLSZJYXQ);
		customerdoc.setStatus(STATUS);

		List<String> messages = customerdocService.addCustomerDoc(customerdoc, multipartRequest);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
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
		
		List<Map<String,Object>> customerList = customerdocService.listCustomers();
		
		modelMap.put("dataList", customerList);
		return "jsonView";
	}
	
	/**
	* @MethodName: getCustomerDocDetail 
	* @Description:
	* @param modelMap
	* @param id
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("getCustomerDocDetail")
	public String getCustomerDocDetail(ModelMap modelMap,
			@RequestParam(value="id", defaultValue = "") Integer id) throws Exception{

		CustomerDocDto customerdocdto = customerdocService.getCustomerDocDetail(id);
		modelMap.put("data", customerdocdto);
		return "jsonView";
	}
	
	/**
	* @MethodName: deleteCustomerDoc 
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author liusw
	*/
	@RequestMapping("deleteCustomerDoc")
	public String deleteCustomerDoc(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = customerdocService.deleteCustomerDoc(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	/**
	* @MethodName: modifyCustomerDoc 
	* @Description:
	* @param ID
	* @param KHZLMC
	* @param KHZLLB
	* @param R_CUSTOMER_ID
	* @param KHZLFJ
	* @param DLSZJYXQ
	* @param STATUS
	* @param model
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("modifyCustomerDoc")
	public void modifyCustomerDoc(
			@RequestParam(value = "id", defaultValue = "0") Integer ID,
			@RequestParam(value = "khzlmc", defaultValue = "") String KHZLMC,
			@RequestParam(value = "khzllb", defaultValue = "0") Integer KHZLLB,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer R_CUSTOMER_ID,
			@RequestParam(value = "khzlfj", defaultValue = "") String KHZLFJ,
			@RequestParam(value = "dlszjyxq", defaultValue = "") Date DLSZJYXQ,  
			@RequestParam(value="status", defaultValue="1") Integer STATUS,
			ModelMap model, HttpServletRequest request,HttpServletResponse response) throws Exception{
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		CustomerDocEntity customerdoc = new CustomerDocEntity();
		customerdoc.setId(ID);
		customerdoc.setKhzlmc(KHZLMC);
		customerdoc.setKhzllb(KHZLLB);
		customerdoc.setR_customer_id(R_CUSTOMER_ID);
		customerdoc.setKhzlfj(KHZLFJ);
		customerdoc.setDlszjyxq(DLSZJYXQ);
		customerdoc.setStatus(STATUS);
		List<String> messages = customerdocService.modifyCustomerDoc(customerdoc, multipartRequest);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
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
