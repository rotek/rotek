/**
* @FileName: ManagerController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-22 下午05:26:03
* @version V1.0
*/
package com.rotek.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cta.platform.util.ListPager;
import com.rotek.dto.ManagerDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.ManagerEntity;
import com.rotek.service.impl.ManagerService;

/**
 * @ClassName: ManagerController
 * @Description: 后台管理员模块
 * @author chenwenpeng
 * @date 2013-6-22 下午05:26:03
 *
 */
@Controller
@RequestMapping("/admin/manager")
public class ManagerController {

	@Autowired
	private ManagerService managerService;

	/**
	 * 转到查看所有管理者
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("toManagers")
	public String toManagers() {
		return "admin/manager/managers";
	}


	/**
	 * 列出所有的管理者信息
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listManagers")
	public String listManagers(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			@RequestParam(value="id", defaultValue = "") Integer id,
			@RequestParam(value="name", defaultValue = "") String name,
			@RequestParam(value="password", defaultValue = "") String password,
			@RequestParam(value="email", defaultValue = "") String email,
			@RequestParam(value="telephone", defaultValue = "") String telephone,
			@RequestParam(value="realname", defaultValue = "") String realname,
			@RequestParam(value="companyname", defaultValue = "") String companyname,
			@RequestParam(value="status", defaultValue = "") Integer status,
			HttpServletRequest request,
			UserDto user,
			ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		ManagerEntity manager = new ManagerEntity();
		manager.setId(id);
		manager.setName(name);
		manager.setPassword(password);
		manager.setRealname(realname);
		manager.setTelephone(telephone);
		manager.setCompanyname(companyname);
		manager.setEmail(email);
		manager.setStatus(status);

		List<ManagerDto> managerList = managerService.listManagers(manager,pager);
		modelMap.put("dataList", managerList);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	 * 添加
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("addManager")
	public String listManagers(
			HttpServletRequest request,
			ManagerEntity manager,
			ModelMap model) throws Exception {


		List<String> messages = managerService.addManager(manager);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: getManagerDetail
	* @Description: 根据id 获取管理员信息详情
	* @param @param modelMap
	* @param @param id
	* @param @return
	* @param @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("getManagerDetail")
	public String getManagerDetail(ModelMap modelMap,
			@RequestParam(value="id", defaultValue = "") Integer id) throws Exception{

		ManagerDto manager = managerService.getManagerDetail(id);
		modelMap.put("data",manager);
		return "jsonView";
	}

	/**
	 * 修改管理员信息
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("modifyManager")
	public String listManagers(
			@RequestParam(value="id", defaultValue = "") Integer id,
			@RequestParam(value="name", defaultValue = "") String name,
			@RequestParam(value="password", defaultValue = "") String password,
			@RequestParam(value="email", defaultValue = "") String email,
			@RequestParam(value="telephone", defaultValue = "") String telephone,
			@RequestParam(value="realname", defaultValue = "") String realname,
			@RequestParam(value="companyname", defaultValue = "") String companyname,
			@RequestParam(value="question", defaultValue = "") String question,
			@RequestParam(value="answer", defaultValue = "") String answer,
			@RequestParam(value="status", defaultValue = "") Integer status,
			@RequestParam(value="r_role_id", defaultValue = "") Integer r_role_id,
			@RequestParam(value="r_customer_id", defaultValue = "") Integer r_customer_id,
			HttpServletRequest request,
			UserDto user,
			ModelMap model) throws Exception {

		ManagerEntity manager = new ManagerEntity();
		manager.setId(id);
		manager.setName(name);
		manager.setPassword(password);
		manager.setEmail(email);
		manager.setTelephone(telephone);
		manager.setRealname(realname);
		manager.setCompanyname(companyname);
		manager.setQuestion(question);
		manager.setAnswer(answer);
		manager.setStatus(status);
		manager.setR_customer_id(r_customer_id);
		manager.setR_role_id(r_role_id);

		List<String> messages = managerService.modifyManager(manager);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: deleteManager
	* @Description: 删除一个或多个管理员信息
	* @param @param ids
	* @param @param model
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteManager")
	public String deleteManager(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = managerService.deleteManager(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	@RequestMapping("listCustomers")
	public String listCustomers (ModelMap modelMap, HttpServletRequest request) throws Exception {
		
		List<Map<String,Object>> customerList = managerService.listCustomers();
		
		modelMap.put("dataList", customerList);
		return "jsonView";
	}
}
