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
import com.rotek.dto.ProjectInfoDto;
import com.rotek.entity.ProjectEntity;
import com.rotek.entity.ProjectInfoEntity;
import com.rotek.service.impl.ProjectInfoService;
import com.rotek.service.impl.ProjectService;

/**
* @ClassName:ProjectInfoController
* @Description: 工程资料管理控制器
* @Author WangJuZhu
* @date 2014年6月24日 下午1:58:14
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/projectdatainfo")
public class ProjectInfoController {

	@Autowired
	private ProjectInfoService projectInfoService;
	
	@Autowired
	private ProjectService projectService;

	/**
	 * @MethodName: toProjectInfo
	 * @Description: 转到工程资料管理页面
	 * @return
	 * @author WangJuZhu
	 */
	@RequestMapping("toProjectInfos")
	public String toProjectInfo() {
		return "admin/project/projectinfo";
	}
	
	/**
	* @MethodName: projectInfoList 
	* @Description: 工程资料列表
	* @param start
	* @param limit
	* @return
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("listProjectInfo")
	public String projectInfoList(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "proejctId", defaultValue = "") Integer proejctId,
			@RequestParam(value = "gczlmc", defaultValue = "") String gczlmc,  // 工程名称
			@RequestParam(value = "status", defaultValue = "") Integer status,
			HttpServletRequest request, ModelMap modelMap)throws Exception {
		
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		ProjectInfoEntity projectInfo = new ProjectInfoEntity();
		projectInfo.setId(id);
		projectInfo.setR_project_id(proejctId);
		projectInfo.setGczlmc(gczlmc);
		projectInfo.setStatus(status);

		List<ProjectInfoDto> projects = projectInfoService.listProejectInfo( projectInfo,pager);
		modelMap.put("dataList", projects);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}
	
	/**
	* @MethodName: listProjectByStatus 
	* @Description: 查询所有有效的工程信息
	* @param modelMap
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("listProjectByStatus")
	public String listProjectByStatus(ModelMap modelMap) throws SQLException{
		List<ProjectEntity> regions = projectService.listProjectByStatus(Status.VALID.getCode());
		modelMap.put("dataList", regions);
		return "jsonView";
	}

	/**
	* @MethodName: addProjectInfo 
	* @Description: 添加工程资料
	* @param request
	* @param response
	* @param model
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("addProjectInfo")
	public void addProjectInfo(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "projectId", defaultValue = "") Integer projectId,
			@RequestParam(value = "gczlmc", defaultValue = "") String gczlmc,
			@RequestParam(value = "gczljj", defaultValue = "") String gczljj,  // 资料简介
			@RequestParam(value = "gczllx", defaultValue = "") Integer gczllx,  // 上传附件类型
			@RequestParam(value = "gcjctlx", defaultValue = "") String gcjctlx,  // 监测图类型
			ModelMap model ) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		ProjectInfoEntity projectInfo = new ProjectInfoEntity();
		
		projectInfo.setR_project_id(projectId);
		projectInfo.setGczlmc(gczlmc);
		projectInfo.setGczljj(gczljj);
		projectInfo.setGczllx(gczllx);
		projectInfo.setGcjctlx(gcjctlx);
		projectInfo.setStatus(Status.NEW.getCode());

		List<String> messages = projectInfoService.addProjectInfo(projectInfo, multipartRequest);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: getProjectInfoDetail 
	* @Description: 根据工程ID查询工程详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("getProjectInfoDetail")
	public String getProjectInfoDetail(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap model) throws SQLException {

		ProjectInfoEntity project = projectInfoService.getProjectInfoById(id);
		model.put("data", project);
		return "jsonView";
	}

	/**
	* @MethodName: modifyProjectInfo 
	* @Description: 修改工程资料
	* @param id
	* @throws IOException
	* @author WangJuZhu
	*/
	@RequestMapping("modifyProjectInfo")
	public void modifyProjectInfo(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "projectId", defaultValue = "") Integer projectId,
			@RequestParam(value = "gczlmc", defaultValue = "") String gczlmc,
			@RequestParam(value = "gczljj", defaultValue = "") String gczljj,  // 资料简介
			@RequestParam(value = "gczllx", defaultValue = "") Integer gczllx,  // 上传附件类型
			@RequestParam(value = "gcjctlx", defaultValue = "") String gcjctlx,  // 监测图类型
			ModelMap model, HttpServletRequest request,HttpServletResponse response) throws SQLException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		ProjectInfoEntity projectInfo = new ProjectInfoEntity();
		
		projectInfo.setR_project_id(projectId);
		projectInfo.setGczlmc(gczlmc);
		projectInfo.setGczljj(gczljj);
		projectInfo.setGczllx(gczllx);
		projectInfo.setGcjctlx(gcjctlx);
		projectInfo.setStatus(Status.NEW.getCode());

		List<String> messages = projectInfoService.modifyProjectInfo(projectInfo,multipartRequest);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: deleteProjectInfo 
	* @Description: 批量删除工程资料
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("deleteProjectInfo")
	public String deleteProjectInfo(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap model) throws SQLException {

		List<String> messages = projectInfoService.deleteProjectInfo(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
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
