/**
 * @Copyright:Copyright (c) 2013 - 2100
 * @Company:JXWY Co.,Ltd.
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
import com.rotek.constant.Status;
import com.rotek.dto.ProjectDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.CustomerEntity;
import com.rotek.entity.ProjectEntity;
import com.rotek.service.impl.CustomerService;
import com.rotek.service.impl.ProjectService;
import com.rotek.util.ProjectUtils;

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
	
	@Autowired
	private CustomerService customerService ;

	/**
	 * @MethodName: toProjectList
	 * @Description: 转到工程信息管理页面
	 * @return
	 * @author WangJuZhu
	 */
	@RequestMapping("toProjects")
	public String toProject() {
		return "admin/project/project";
	}
	
	/**
	* @MethodName: projectList 
	* @Description: 工程信息列表
	* @param start
	* @param limit
	* @return
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("listProjects")
	public String projectList(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,  // 工程名称
			@RequestParam(value = "gcbh", defaultValue = "") String gcbh,  // 工程编号
			@RequestParam(value = "gcxh", defaultValue = "") String gcxh,  // 工程型号
			@RequestParam(value = "gclb", defaultValue = "") Integer gclb,  // 工程类别
			@RequestParam(value = "start_azsj", defaultValue = "") Date start_azsj,  // 安装时间
			@RequestParam(value = "end_azsj", defaultValue = "") Date end_azsj,
			@RequestParam(value = "start_tysj", defaultValue = "") Date start_tysj, // 投运时间
			@RequestParam(value = "end_tysj", defaultValue = "") Date end_tysj,
			@RequestParam(value = "status", defaultValue = "") Integer status,  // 状态
			HttpServletRequest request, UserDto user, ModelMap modelMap)throws Exception {
		
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);
		
		ProjectEntity project = new ProjectEntity();
		project.setId(id);
		project.setGcmc(gcmc);
		project.setGcbh(gcbh);
		project.setGcxh(gcxh);
		project.setGclb(gclb);
		project.setStatus(status);

		List<ProjectDto> projects = projectService.listProeject(user, project,start_azsj,end_azsj,start_tysj,end_tysj, pager);
		modelMap.put("dataList", projects);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @MethodName: addProject 
	* @Description: 添加工程信息
	* @param request
	* @param response
	* @param model
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("addProject")
	public void addProject(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer r_customer_id,
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,
			@RequestParam(value = "gcbh", defaultValue = "") String gcbh,
			@RequestParam(value = "gcxh", defaultValue = "") String gcxh,
			@RequestParam(value = "gclb", defaultValue = "1") Integer gclb,
			@RequestParam(value = "gcjs", defaultValue = "") String gcjs,
			@RequestParam(value = "jscsjj", defaultValue = "") String jscsjj,
			//@RequestParam(value = "gclj", defaultValue = "") String gclj,
			@RequestParam(value = "azsj", defaultValue = "") Date azsj,
			@RequestParam(value = "tysj", defaultValue = "") Date tysj,ModelMap model ) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		ProjectEntity project = new ProjectEntity();
		project.setR_customer_id(r_customer_id);
		project.setGcmc(gcmc);
		project.setGcbh(gcbh);
		project.setGcxh(gcxh);
		project.setJscsjj(jscsjj);
		project.setGclb(gclb);
		project.setGcjs(gcjs);
		//project.setGclj(gclj);
		project.setAzsj(azsj);
		project.setTysj(tysj);
		project.setStatus(Status.VALID.getCode());
		project.setCjsj(new Date());
		project.setCjr(0);

		List<String> messages = projectService.addProject(project, multipartRequest);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: getProjectDetail 
	* @Description: 根据工程ID查询工程详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("getProjectDetail")
	public String getProjectDetail(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap model) throws SQLException {

		ProjectEntity project = projectService.getProjectById(id);
		model.put("data", project);
		return "jsonView";
	}
	
	/**
	* @MethodName: goDuiweiProject 
	* @Description: 转到工程对位页面
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("/goDuiweiUrl")
	public String goDuiweiProject(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap model) throws SQLException {

		ProjectDto project = projectService.getProjectDtoById(id);
		//获取所有现场编号
		List<String> tempList =  ProjectUtils.getProjectCode() ;
		project.setLocalCodes(tempList);
		model.put("data", project);
		return "jsonView";
	}
	
	/**
	* @MethodName: duiweiProject 
	* @Description: 工程对位，把现场工程的编号和库中工程信息进行对位
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("/duiweiProject")
	public void duiweiProject(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "locale_gcbh", defaultValue = "") String locale_gcbh,
			HttpServletRequest request,HttpServletResponse response, ModelMap model) throws Exception {

		ProjectEntity project = projectService.getProjectById(id);
		project.setLocale_gcbh(locale_gcbh);
		
		projectService.updateProject(project);
		JSONObject json = new JSONObject();
		json.put("success", true);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}

	/**
	* @MethodName: modifyProject 
	* @Description: 修改工程信息
	* @param id
	* @throws IOException
	* @author WangJuZhu
	*/
	@RequestMapping("modifyProject")
	public void modifyProject(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "r_customer_id", defaultValue = "0") Integer r_customer_id,
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,
			@RequestParam(value = "gcbh", defaultValue = "") String gcbh,
			@RequestParam(value = "gcxh", defaultValue = "") String gcxh,
			@RequestParam(value = "gclb", defaultValue = "1") int gclb,
			@RequestParam(value = "gcjs", defaultValue = "") String gcjs,
			@RequestParam(value = "gczp", defaultValue = "") String gczp,
			@RequestParam(value = "jscsjj", defaultValue = "") String jscsjj,
			@RequestParam(value = "jscsfj", defaultValue = "") String jscsfj,
			@RequestParam(value = "gclj", defaultValue = "") String gclj,
			@RequestParam(value = "azsj", defaultValue = "") Date azsj,
			@RequestParam(value = "tysj", defaultValue = "") Date tysj,
			ModelMap model, HttpServletRequest request,HttpServletResponse response) throws SQLException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		ProjectEntity project = new ProjectEntity();
		project.setId(id);
		project.setR_customer_id(r_customer_id);
		project.setGcmc(gcmc);
		project.setGcbh(gcbh);
		project.setGcxh(gcxh);
		project.setJscsjj(jscsjj);
		project.setGclb(gclb);
		project.setGcjs(gcjs);
		project.setGczp(gczp);
		project.setJscsfj(jscsfj);
		project.setGclj(gclj);
		project.setAzsj(azsj);
		project.setTysj(tysj);
		project.setStatus(Status.VALID.getCode());

		List<String> messages = projectService.modifyProject(project,multipartRequest);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: deleteProject 
	* @Description: 批量删除工程信息
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("deleteProject")
	public String deleteProject(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap model) throws SQLException {

		List<String> messages = projectService.deleteProject(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	@RequestMapping("/selectCustomers")
	public String selectCustomers(ModelMap modelMap) throws SQLException{
		List<CustomerEntity> customerList = customerService.selectCustomers(Status.VALID.getCode(), 0);
		modelMap.put("customerList", customerList);
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
