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
import com.rotek.service.impl.ComplaintService;
import com.rotek.entity.ComplaintInfoEntity;
import com.rotek.dto.ComplaintInfoDto;

/**
* @ClassName:ComplaintInfoController
* @Description:
* @Author liusw
* @date 2014年6月28日 下午1:34:17
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/ComplaintInfo") // controller方法路径，每个Controller最好取不一样的路径名字
public class ComplaintInfoController {

	@Autowired
	private ComplaintService complaintinfoService;

	
	/**
	* @MethodName: toComplaintList 
	* @Description:
	* @return
	* @author liusw
	*/
	@RequestMapping("toComplaintInfoList")
	public String toComplaintInfoList() {
		// 返回JSP文件目录及对应的JSP文件
		return "admin/customer/complaintinfo";
	}
	
	/**
	* @MethodName: listComplaintInfos 
	* @Description:
	* @param start
	* @param limit
	* @param complaintinfo
	* @param request
	* @param user
	* @param modelMap
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("listComplaintInfos")
	public String listComplaintInfos(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			ComplaintInfoEntity complaintinfo,
			HttpServletRequest request,
			UserDto user,
			ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		List<ComplaintInfoDto> complaintinfoList = complaintinfoService.listComplaintInfos(complaintinfo, pager);
		modelMap.put("dataList", complaintinfoList);
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
		
		List<Map<String,Object>> customerList = complaintinfoService.listCustomers();
		
		modelMap.put("dataList", customerList);
		return "jsonView";
	}
	
	/**
	* @MethodName: getComplaintInfoDetail 
	* @Description:
	* @param modelMap
	* @param id
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("getComplaintInfoDetail")
	public String getComplaintInfoDetail(ModelMap modelMap,
			@RequestParam(value="id", defaultValue = "") Integer id) throws Exception{

		ComplaintInfoDto complaintinfodto = complaintinfoService.getComplaintInfoDetail(id);
		modelMap.put("data", complaintinfodto);
		return "jsonView";
	}
	
	/**
	* @MethodName: deleteComplaintInfo 
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author liusw
	*/
	@RequestMapping("deleteComplaintInfo")
	public String deleteComplaintInfo(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = complaintinfoService.deleteComplaintInfo(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	/**
	* @MethodName: addComplaintInfo 
	* @Description:
	* @param request
	* @param response
	* @param TSDW
	* @param TSSX
	* @param R_CUSTOMER_ID
	* @param TSSJ
	* @param STATUS
	* @param model
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("addComplaintInfo")
	public String addComplaintInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "tsdw", defaultValue = "") String TSDW,
			@RequestParam(value = "tssx", defaultValue = "0") String TSSX,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer R_CUSTOMER_ID,
			@RequestParam(value = "tssj", defaultValue = "") Date TSSJ,  
			@RequestParam(value="status", defaultValue="1") Integer STATUS,
			ModelMap model ) throws Exception {
		
		ComplaintInfoEntity complaintinfo = new ComplaintInfoEntity();
		complaintinfo.setTsdw(TSDW);
		complaintinfo.setTssx(TSSX);
		complaintinfo.setR_customer_id(R_CUSTOMER_ID);
		complaintinfo.setTssj(TSSJ);
		complaintinfo.setStatus(STATUS);

		List<String> messages = complaintinfoService.addComplaintInfo(complaintinfo);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		return "jsonView";
	}
	
	/**
	* @MethodName: modifyComplaintInfo 
	* @Description:
	* @param request
	* @param response
	* @param ID
	* @param TSDW
	* @param TSSX
	* @param R_CUSTOMER_ID
	* @param TSSJ
	* @param STATUS
	* @param model
	* @return
	* @throws Exception
	* @author liusw
	*/
	@RequestMapping("modifyComplaintInfo")
	public String modifyComplaintInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "id", defaultValue = "0") Integer ID,
			@RequestParam(value = "tsdw", defaultValue = "") String TSDW,
			@RequestParam(value = "tssx", defaultValue = "0") String TSSX,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer R_CUSTOMER_ID,
			@RequestParam(value = "tssj", defaultValue = "") Date TSSJ,  
			@RequestParam(value="status", defaultValue="1") Integer STATUS,
			ModelMap model ) throws Exception {
		
		ComplaintInfoEntity complaintinfo = new ComplaintInfoEntity();
		complaintinfo.setId(ID);
		complaintinfo.setTsdw(TSDW);
		complaintinfo.setTssx(TSSX);
		complaintinfo.setR_customer_id(R_CUSTOMER_ID);
		complaintinfo.setTssj(TSSJ);
		complaintinfo.setStatus(STATUS);

		List<String> messages = complaintinfoService.modifyComplaintInfo(complaintinfo);
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
