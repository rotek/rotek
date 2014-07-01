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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cta.platform.util.ListPager;
import com.rotek.constant.ComponentType;
import com.rotek.constant.Status;
import com.rotek.dto.ComponentGroupDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.ComponentGroupEntity;
import com.rotek.entity.ProjectEntity;
import com.rotek.service.impl.ComponentGroupService;
import com.rotek.service.impl.ProjectService;

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
	
	@Autowired
	private ProjectService projectService;

	/**
	* @MethodName: toComponentGroups 
	* @Description: 转到泵组信息管理页面
	* 1,泵 ;     2,砂滤器 ; 3,碳滤器 ;    4,软化器 ;
	* 5,过滤器;  6,膜 ;    7,紫外杀菌器 ; 8,水箱 ;   9,加药装置 ;
	* @param request
	* @param response
	* @param modelMap
	* @return
	* @author WangJuZhu
	*/
	@RequestMapping("/toComponentGroups/{groupType}")
	public String toComponentGroups( HttpServletRequest request,HttpServletResponse response,
			@PathVariable(value = "groupType") Integer groupType,ModelMap modelMap)throws Exception {
		modelMap.put("groupType", groupType);
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
	@RequestMapping("listComGroup/{groupType}")
	public String listComGroup(
			@PathVariable(value = "groupType") Integer groupType,
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "r_project_id", defaultValue = "") Integer project_id,
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,
			@RequestParam(value = "group_bh", defaultValue = "") String group_bh,
			@RequestParam(value = "group_mc", defaultValue = "") String group_mc,
			@RequestParam(value = "pp", defaultValue = "") String pp,
			@RequestParam(value = "xh", defaultValue = "") String xh,
			@RequestParam(value = "gl", defaultValue = "") String gl,
			@RequestParam(value = "status", defaultValue = "1") Integer status,  // 状态
			HttpServletRequest request, UserDto user, ModelMap modelMap)throws Exception {
		
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		ComponentGroupDto comGroup = new ComponentGroupDto();
		comGroup.setId(id);
		comGroup.setR_project_id(project_id);  //工程ID
		comGroup.setProject_name(gcmc);   // 工程名称
		comGroup.setGroup_lb(groupType);  // 组类别
		comGroup.setGroup_bh(group_bh);   // 组编号
		comGroup.setGroup_mc(group_mc);   // 组名称
		comGroup.setStatus(status);
		
		comGroup.setPp(pp);
		comGroup.setXh(xh);
		comGroup.setGl(gl);

		List<ComponentGroupDto> cgroup = groupService.listComGroup(user, comGroup, pager);
		modelMap.put("dataList", cgroup);
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
	@RequestMapping("addComGroup/{groupType}")
	public void addProject(HttpServletRequest request,HttpServletResponse response,
			@PathVariable(value = "groupType") Integer groupType,
			@RequestParam(value = "id", defaultValue = "") Integer project_id,
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,
			@RequestParam(value = "group_bh", defaultValue = "") String group_bh,
			@RequestParam(value = "group_mc", defaultValue = "") String group_mc,
			@RequestParam(value = "pp", defaultValue = "") String pp,
			@RequestParam(value = "xh", defaultValue = "") String xh,
			@RequestParam(value = "gl", defaultValue = "") String gl,
			ModelMap model ) throws Exception {

		ComponentGroupEntity comGroup = new ComponentGroupEntity();
		comGroup.setR_project_id(project_id);  //工程ID
		comGroup.setGroup_lb(groupType);  // 组类别
		comGroup.setGroup_bh(group_bh);   // 组编号
		comGroup.setGroup_mc(group_mc);   // 组名称
		comGroup.setStatus(Status.VALID.getCode());
		
		// 保存 泵组信息
		if(groupType == ComponentType.PUMP.getCode()){
			comGroup.setPp(pp);
			comGroup.setXh(xh);
			comGroup.setGl(gl);
		}
		
		List<String> messages = groupService.addComGroup(comGroup);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: getComGroupDetail 
	* @Description: 根据组ID查询零件组详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("getComGroupDetail")
	public String getComGroupDetail(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap model) throws SQLException {

		ComponentGroupDto cgroup = groupService.getOneComGroup(id);
		model.put("data", cgroup);
		return "jsonView";
	}

	/**
	* @MethodName: modifyComGroup 
	* @Description: 修改零件组信息
	* @param id
	* @throws IOException
	* @author WangJuZhu
	*/
	@RequestMapping("modifyComGroup/{groupType}")
	public void modifyComGroup(
			@PathVariable(value = "groupType") Integer groupType,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "r_project_id", defaultValue = "") Integer project_id,
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,
			@RequestParam(value = "group_bh", defaultValue = "") String group_bh,
			@RequestParam(value = "group_mc", defaultValue = "") String group_mc,
			@RequestParam(value = "pp", defaultValue = "") String pp,
			@RequestParam(value = "xh", defaultValue = "") String xh,
			@RequestParam(value = "gl", defaultValue = "") String gl,
			ModelMap model, HttpServletRequest request,HttpServletResponse response) throws SQLException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		ComponentGroupEntity comGroup = groupService.getComGroupById(id);
		comGroup.setR_project_id(project_id);  //工程ID
		comGroup.setGroup_lb(comGroup.getGroup_lb());  // 组类别
		comGroup.setGroup_bh(group_bh);   // 组编号
		comGroup.setGroup_mc(group_mc);   // 组名称
		comGroup.setStatus(Status.VALID.getCode());
		
		// 保存 泵组信息
		if(groupType == ComponentType.PUMP.getCode()){
			comGroup.setPp(pp);
			comGroup.setXh(xh);
			comGroup.setGl(gl);
		}

		List<String> messages = groupService.modifyComGroup(comGroup);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: deleteComGroup 
	* @Description: 批量删除零件组信息
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("deleteComGroup")
	public String deleteComGroup(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap model) throws SQLException {

		List<String> messages = groupService.deleteComGroup(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
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
