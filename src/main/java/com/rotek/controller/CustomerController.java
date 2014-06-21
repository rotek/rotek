/**
 * @Copyright:Copyright (c) 2013 - 2100
 * @Company:JXWY Co.Ltd.
 */
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
import com.rotek.entity.CustomerEntity;
import com.rotek.service.impl.CustomerService;
import com.rotek.dto.CustomerDto;


/**
* @ClassName:CustomerController
* @Description: 客户管理控制器
* @Author Liusw
* @date 2014年6月14日 下午10:25:52
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/customer")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;


	/**
	* @MethodName: toProjectList 
	* @Description: 转到客户信息管理列表页面
	* @return
	* @author Liusw
	*/
	@RequestMapping("toCustomerList")
	public String toCustomerList() {
		return "admin/customer/customer";
	}
	
	/**
	* @MethodName: listCustomers 
	* @Description: 列出所有的客户信息
	* @param start
	* @param limit
	* @param customer
	* @param request
	* @param user
	* @param modelMap
	* @return
	* @throws Exception
	* @author Liusw
	*/
	@RequestMapping("listCustomers")
	public String listCustomers(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			CustomerEntity customer,
			HttpServletRequest request,
			UserDto user,
			ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		List<CustomerDto> customerList = customerService.listCustomers(customer, pager);
		modelMap.put("dataList", customerList);
		modelMap.put("totalCount", pager.getTotalRows());

		return "jsonView";
	}
	
	/**
	* @MethodName: addCustomer 
	* @Description: 增加一个客户信息
	* @param request
	* @param response
	* @param KHLB
	* @param R_CUSTOMER_ID
	* @param MC
	* @param TXDZ
	* @param LXFS
	* @param LXR
	* @param LXDH
	* @param DLQY
	* @param JWDDZ
	* @param STATUS
	* @param model
	* @throws Exception
	* @author Liusw
	*/
	@RequestMapping("addCustomer")
	public String addCustomer(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "khlb", defaultValue = "0") Integer KHLB,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer R_CUSTOMER_ID,
			@RequestParam(value = "mc", defaultValue = "") String MC,
			@RequestParam(value = "txdz", defaultValue = "") String TXDZ,
			@RequestParam(value = "lxfs", defaultValue = "") String LXFS,
			@RequestParam(value = "lxr", defaultValue = "") String LXR,
			@RequestParam(value = "lxdh", defaultValue = "") String LXDH,
			@RequestParam(value = "dlqy", defaultValue = "") String DLQY,
			@RequestParam(value = "jwddz", defaultValue = "") String JWDDZ,
			@RequestParam(value="status", defaultValue="1") Integer STATUS,
			ModelMap model ) throws Exception {

		CustomerEntity customer = new CustomerEntity();
		customer.setKhlb(KHLB);
		if (KHLB == 1)
		{
			customer.setR_customer_id(0);
		}
		else
		{
			customer.setR_customer_id(R_CUSTOMER_ID);
		}
		customer.setMc(MC);
		customer.setTxdz(TXDZ);
		customer.setLxfs(LXFS);
		customer.setLxr(LXR);
		customer.setLxdh(LXDH);
		customer.setDlqy(DLQY);
		customer.setJwddz(JWDDZ);
		customer.setStatus(STATUS);

		List<String> messages = customerService.addCustomer(customer);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		return "jsonView";
	}

	
	/**
	* @MethodName: modifyCustomer 
	* @Description: 修改客户信息
	* @param ID
	* @param KHLB
	* @param MC
	* @param TXDZ
	* @param LXFS
	* @param LXR
	* @param LXDH
	* @param DLQY
	* @param JWDDZ
	* @param STATUS
	* @param model
	* @return
	* @throws Exception
	* @author Liusw
	*/
	@RequestMapping("modifyCustomer")
	public String modifyCustomer(
			@RequestParam(value = "id", defaultValue = "0") Integer ID,
			@RequestParam(value = "khlb", defaultValue = "0") Integer KHLB,
			@RequestParam(value = "mc", defaultValue = "") String MC,
			@RequestParam(value = "txdz", defaultValue = "") String TXDZ,
			@RequestParam(value = "lxfs", defaultValue = "") String LXFS,
			@RequestParam(value = "lxr", defaultValue = "") String LXR,
			@RequestParam(value = "lxdh", defaultValue = "") String LXDH,
			@RequestParam(value = "dlqy", defaultValue = "") String DLQY,
			@RequestParam(value = "jwddz", defaultValue = "") String JWDDZ,
			@RequestParam(value="status", defaultValue="1") Integer STATUS,
			ModelMap model) throws Exception{
		
		CustomerEntity customer = new CustomerEntity();
		customer.setId(ID);
		customer.setKhlb(KHLB);
		customer.setMc(MC);
		customer.setTxdz(TXDZ);
		customer.setLxfs(LXFS);
		customer.setLxr(LXR);
		customer.setLxdh(LXDH);
		if (KHLB == 3)
			customer.setDlqy("");
		customer.setJwddz(JWDDZ);
		customer.setStatus(STATUS);
		List<String> messages = customerService.modifyCustomer(customer);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}


	/**
	* @MethodName: deleteCustomer 
	* @Description: 删除一个或多个客户信息
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author Liusw
	*/
	@RequestMapping("deleteCustomer")
	public String deleteCustomer(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = customerService.deleteCustomer(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	
	/**
	* @MethodName: listAgents 
	* @Description: 列出所有的代理商信息
	* @param modelMap
	* @return
	* @throws Exception
	* @author Liusw
	*/
	@RequestMapping("listAgents")
	public String listAgents (ModelMap modelMap, HttpServletRequest request,
			@RequestParam(value="khlb", defaultValue="") Integer khlb) throws Exception {
		
		List<Map<String,Object>> firstAgentList = customerService.listAgentsByType(CustomerEntity.KHLB_FIRSTAGENT);
		List<Map<String,Object>> secondAgentList = customerService.listAgentsByType(CustomerEntity.KHLB_SECONDAGENT);
		
		modelMap.put("firstAgentList", firstAgentList);
		modelMap.put("secondAgentList", secondAgentList);
		return "jsonView";
	}
	
	/**
	* @Title: getCustomerDetail
	* @Description: 根据id 获取客户信息详情
	* @param @param modelMap
	* @param @param id
	* @param @return
	* @param @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("getCustomerDetail")
	public String getCustomerDetail(ModelMap modelMap,
			@RequestParam(value="id", defaultValue = "") Integer id) throws Exception{

		CustomerEntity customerEntity = customerService.getCustomerDetail(id);
		modelMap.put("data", customerEntity);
		return "jsonView";
	}
}
