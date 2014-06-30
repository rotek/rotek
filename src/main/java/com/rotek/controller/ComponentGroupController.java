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
import com.rotek.constant.ComponentType;
import com.rotek.constant.Status;
import com.rotek.dto.UserDto;
import com.rotek.entity.ProjectEntity;
import com.rotek.service.impl.ComponentGroupService;

/**
* @ClassName:ComponentGroupController
* @Description: 零件组信息管理控制器
* @Author WangJuZhu
* @date 2014年6月30日 下午3:39:13
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/componentgroup")
public class ComponentGroupController {

	@Autowired
	private ComponentGroupService groupService;

	/**
	* @MethodName: toPumps 
	* @Description: 转到泵组信息管理页面
	* @param request
	* @param response
	* @param modelMap
	* @return
	* @author WangJuZhu
	*/
	@RequestMapping("toPumps")
	public String toPumps( HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) {
		modelMap.put("groupType", ComponentType.PUMP.getCode());
		return "admin/componentgroup/componentgroup";
	}
	
	/**
	* @MethodName: toSandFilters 
	* @Description: 转到砂滤器组信息管理页面
	* @param request
	* @param response
	* @param modelMap
	* @return
	* @author WangJuZhu
	*/
	@RequestMapping("toSandFilters")
	public String toSandFilters( HttpServletRequest request,HttpServletResponse response,ModelMap modelMap) {
		modelMap.put("groupType", ComponentType.SAND_FILTER.getCode());
		return "admin/componentgroup/componentgroup";
	}
	
	
	
	/**
	* @MethodName: projectList 
	* @Description: 零件组信息列表
	* @param start
	* @param limit
	* @return
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("listComGroup")
	public String projectList(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,  // 零件组名称
			@RequestParam(value = "gcbh", defaultValue = "") String gcbh,  // 零件组编号
			@RequestParam(value = "gcxh", defaultValue = "") String gcxh,  // 零件组型号
			@RequestParam(value = "gclb", defaultValue = "") Integer gclb,  // 零件组类别
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

		List<ProjectEntity> projects = groupService.listProeject(user, project,start_azsj,end_azsj,start_tysj,end_tysj, pager);
		modelMap.put("dataList", projects);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @MethodName: addProject 
	* @Description: 添加零件组信息
	* @param request
	* @param response
	* @param model
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("addComGroup")
	public void addProject(HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,
			@RequestParam(value = "gcbh", defaultValue = "") String gcbh,
			@RequestParam(value = "gcxh", defaultValue = "") String gcxh,
			@RequestParam(value = "gclb", defaultValue = "1") Integer gclb,
			@RequestParam(value = "gcjs", defaultValue = "") String gcjs,
			@RequestParam(value = "jscsjj", defaultValue = "") String jscsjj,
			@RequestParam(value = "gclj", defaultValue = "") String gclj,
			@RequestParam(value = "azsj", defaultValue = "") Date azsj,
			@RequestParam(value = "tysj", defaultValue = "") Date tysj,ModelMap model ) throws Exception {

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		ProjectEntity project = new ProjectEntity();
		project.setGcmc(gcmc);
		project.setGcbh(gcbh);
		project.setGcxh(gcxh);
		project.setJscsjj(jscsjj);
		project.setGclb(gclb);
		project.setGcjs(gcjs);
		project.setGclj(gclj);
		project.setAzsj(azsj);
		project.setTysj(tysj);
		project.setStatus(Status.NEW.getCode());
		project.setCjsj(new Date());
		project.setCjr(0);

		List<String> messages = groupService.addProject(project, multipartRequest);
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
	* @Description: 根据零件组ID查询零件组详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("getComGroupDetail")
	public String getProjectDetail(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap model) throws SQLException {

		ProjectEntity project = groupService.getProjectById(id);
		model.put("data", project);
		return "jsonView";
	}

	/**
	* @MethodName: modifyProject 
	* @Description: 修改零件组信息
	* @param id
	* @throws IOException
	* @author WangJuZhu
	*/
	@RequestMapping("modifyComGroup")
	public void modifyProject(
			@RequestParam(value = "id", defaultValue = "") Integer id,
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
		project.setStatus(Status.NEW.getCode());

		List<String> messages = groupService.modifyProject(project,multipartRequest);
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
	* @Description: 批量删除零件组信息
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("deleteComGroup")
	public String deleteProject(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap model) throws SQLException {

		List<String> messages = groupService.deleteProject(ids);
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
