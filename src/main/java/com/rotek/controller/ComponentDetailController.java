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
import com.rotek.dto.ComponentDetailDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.ComponentDetailEntity;
import com.rotek.entity.ComponentGroupEntity;
import com.rotek.entity.ProjectEntity;
import com.rotek.service.impl.ComponentDetailService;
import com.rotek.service.impl.ComponentGroupService;
import com.rotek.service.impl.ProjectService;

/**
* @ClassName:ComponentGroupController
* @Description: 零件明细信息管理控制器
* @Author WangJuZhu
* @date 2014年6月30日 下午3:39:13
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/componentdetail")
public class ComponentDetailController {

	@Autowired
	private ComponentDetailService detailService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ComponentGroupService groupService;

	/**
	* @MethodName: toComponentDetails 
	* @Description: 转到零件 明细信息管理页面
	* 1,泵 ;     2,砂滤器 ; 3,碳滤器 ;    4,软化器 ;
	* 5,过滤器;  6,膜 ;    7,紫外杀菌器 ; 8,水箱 ;   9,加药装置 ;
	* @param request
	* @param response
	* @param modelMap
	* @return
	* @author WangJuZhu
	*/
	@RequestMapping("/toComponentDetails/{groupType}")
	public String toComponentDetails( HttpServletRequest request,HttpServletResponse response,
			@PathVariable(value = "groupType") Integer groupType,ModelMap modelMap)throws Exception {
		modelMap.put("groupType", groupType);
		return "admin/componentdetail/componentdetail";
	}
	
	/**
	* @MethodName: listComDetail 
	* @Description: 零件明细信息列表
	* @param start
	* @param limit
	* @return
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("listComDetail/{groupType}")
	public String listComDetail(
			@PathVariable(value = "groupType") Integer groupType,  // 组分类
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "project_name", defaultValue = "") String project_name,  // 工程名称
			@RequestParam(value = "group_name", defaultValue = "") String group_name, //组名称
			@RequestParam(value = "specific_part", defaultValue = "") String specific_part,  // 零件名称
			@RequestParam(value = "specific_bh", defaultValue = "") String specific_bh, // 零件编号
			
			@RequestParam(value = "status", defaultValue = "1") Integer status,  // 状态
			HttpServletRequest request, UserDto user, ModelMap modelMap)throws Exception {
		
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		ComponentDetailDto comDetail = new ComponentDetailDto();
		comDetail.setProject_name(project_name);   // 工程名称
		comDetail.setGroup_name(group_name);  // 组名称
		comDetail.setSpecific_part(specific_part);  // 零件名称
		comDetail.setSpecific_bh(specific_bh);  // 零件编号
		comDetail.setStatus(status);

		List<ComponentDetailDto> cgroup = detailService.listComDetail(user, comDetail, pager);
		modelMap.put("dataList", cgroup);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @MethodName: addComDetail 
	* @Description: 添加零件明细信息
	* @param request
	* @param response
	* @param model
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("addComDetail/{groupType}")
	public void addComDetail(HttpServletRequest request,HttpServletResponse response,
			@PathVariable(value = "groupType") Integer groupType,
			ComponentDetailEntity comDetail , ModelMap model ) throws Exception {

		ComponentDetailEntity addDetail = new ComponentDetailEntity();
		addDetail.setR_project_id(comDetail.getR_project_id());
		addDetail.setR_component_group_id(comDetail.getR_component_group_id());
		addDetail.setSpecific_part(comDetail.getSpecific_part());
		addDetail.setSpecific_bh(comDetail.getSpecific_bh());
		addDetail.setStatus(Status.VALID.getCode());
		
		//保存 泵组零件明细信息
		if(groupType == ComponentType.PUMP.getCode()){
			addDetail.setEdll(comDetail.getEdll());
			addDetail.setEdghsj(comDetail.getEdghsj());
			addDetail.setEdddl(comDetail.getEdddl());
			addDetail.setEdph(comDetail.getEdph());
			addDetail.setEdylv(comDetail.getEdylv());
			addDetail.setEdwd(comDetail.getEdwd());
			addDetail.setEdyd(comDetail.getEdyd());
			addDetail.setEdywj(comDetail.getEdywj());
			addDetail.setEdtds(comDetail.getEdtds());
			addDetail.setEdzdu(comDetail.getEdzdu());
			addDetail.setEdyl(comDetail.getEdyl());
			addDetail.setEdsdi(comDetail.getEdsdi());
			addDetail.setEdcod(comDetail.getEdcod());
			addDetail.setEdbod(comDetail.getEdbod());
			addDetail.setEdad(comDetail.getEdad());
			addDetail.setEdzd(comDetail.getEdzd());
			addDetail.setEdzl(comDetail.getEdzl());
			addDetail.setEdxfw(comDetail.getEdxfw());
			addDetail.setEdwnnd(comDetail.getEdwnnd());
			addDetail.setOther_info(comDetail.getOther_info());
		}
		
		//保存 砂滤器 和 软化器 明细信息
		if(groupType == ComponentType.SAND_FILTER.getCode() || groupType == ComponentType.SOFTENER.getCode()){
			
		}
		
		//保存 碳滤器 明细信息
		if(groupType == ComponentType.CARBON_FILTE.getCode()){
			
		}
		
		//保存 过滤器 明细信息
		if(groupType == ComponentType.FILTER_GROUP.getCode()){
			
		}
		
		//保存 膜 明细信息
		if(groupType == ComponentType.FILM_GROUP.getCode()){
			
		}
		
		//保存 紫外杀菌器 明细信息
		if(groupType == ComponentType.UVSTERILIZER.getCode()){
			
		}
		
		// 保存 水箱 明细信息
		if(groupType == ComponentType.TANK_GROUP.getCode()){
			
		}
		
		//保存 加药装置器 明细信息
		if(groupType == ComponentType.DOSESETTING.getCode()){
			
		}
		
		List<String> messages = detailService.addComDetail(addDetail);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}

	/**
	* @MethodName: modifyComDetail 
	* @Description: 修改零件明细信息
	* @param id
	* @throws IOException
	* @author WangJuZhu
	*/
	@RequestMapping("modifyComDetail/{groupType}")
	public void modifyComDetail(
			@PathVariable(value = "groupType") Integer groupType,
			ComponentDetailEntity comDetail,
			ModelMap model, HttpServletRequest request,HttpServletResponse response) throws SQLException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		ComponentDetailEntity editDetail = detailService.getComDetailById(comDetail.getId());
		
		editDetail.setId(comDetail.getId());
		editDetail.setR_project_id(comDetail.getR_project_id());
		editDetail.setR_component_group_id(comDetail.getR_component_group_id());
		editDetail.setSpecific_part(comDetail.getSpecific_part());
		editDetail.setSpecific_bh(comDetail.getSpecific_bh());
		editDetail.setStatus(Status.VALID.getCode());
		
		//保存 泵组零件明细信息
		if(groupType == ComponentType.PUMP.getCode()){
			editDetail.setEdll(comDetail.getEdll());
			editDetail.setEdghsj(comDetail.getEdghsj());
			editDetail.setEdddl(comDetail.getEdddl());
			editDetail.setEdph(comDetail.getEdph());
			editDetail.setEdylv(comDetail.getEdylv());
			editDetail.setEdwd(comDetail.getEdwd());
			editDetail.setEdyd(comDetail.getEdyd());
			editDetail.setEdywj(comDetail.getEdywj());
			editDetail.setEdtds(comDetail.getEdtds());
			editDetail.setEdzdu(comDetail.getEdzdu());
			editDetail.setEdyl(comDetail.getEdyl());
			editDetail.setEdsdi(comDetail.getEdsdi());
			editDetail.setEdcod(comDetail.getEdcod());
			editDetail.setEdbod(comDetail.getEdbod());
			editDetail.setEdad(comDetail.getEdad());
			editDetail.setEdzd(comDetail.getEdzd());
			editDetail.setEdzl(comDetail.getEdzl());
			editDetail.setEdxfw(comDetail.getEdxfw());
			editDetail.setEdwnnd(comDetail.getEdwnnd());
			editDetail.setOther_info(comDetail.getOther_info());
		}

		//保存 砂滤器 和 软化器 明细信息
		if(groupType == ComponentType.SAND_FILTER.getCode() || groupType == ComponentType.SOFTENER.getCode()){
			
		}

		//保存 碳滤器 明细信息
		if(groupType == ComponentType.CARBON_FILTE.getCode()){
			
		}
		
		//保存 过滤器 明细信息
		if(groupType == ComponentType.FILTER_GROUP.getCode()){
			
		}
		
		//保存 膜 明细信息
		if(groupType == ComponentType.FILM_GROUP.getCode()){
			
		}
		
		//保存 紫外杀菌器 明细信息
		if(groupType == ComponentType.UVSTERILIZER.getCode()){
			
		}
		
		// 保存 水箱 明细信息
		if(groupType == ComponentType.TANK_GROUP.getCode()){
			
		}
		
		//保存 加药装置器 明细信息
		if(groupType == ComponentType.DOSESETTING.getCode()){
			
		}
		
		List<String> messages = detailService.modifyComDetail(editDetail);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: deleteComDetail 
	* @Description: 删除零件明细信息
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("deleteComDetail")
	public String deleteComDetail(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap model) throws SQLException {

		List<String> messages = detailService.deleteComDetail(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	/**
	* @MethodName: getComDetail
	* @Description: 根据组ID查询零件组详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("getComDetail")
	public String getComDetail(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap model) throws SQLException {

		ComponentDetailDto cgroup = detailService.getOneComDetail(id);
		model.put("data", cgroup);
		return "jsonView";
	}
	
	/**
	* @MethodName: selectProjectByType 
	* @Description: 查询有效的工程信息
	* @param modelMap
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("selectProjectByType")
	public String selectProjectByType(ModelMap modelMap) throws SQLException{
		//工程类别（1、普通工程；2、EMC工程）
		List<ProjectEntity> projectList = projectService.selectProjectByType(Status.VALID.getCode(),1);
		modelMap.put("projectList", projectList);
		return "jsonView";
	}
	
	/**
	* @MethodName: selectGroupByPid 
	* @Description: 由 工程ID和零件分组查询组信息
	* @param projectId
	* @param componentType
	* @param modelMap
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("selectGroupByPid/{projectId}/{componentType}")
	public String selectGroupByPid(
			@PathVariable(value="projectId") Integer projectId,
			@PathVariable(value="componentType") Integer componentType,
			ModelMap modelMap) throws SQLException{
		
		List<ComponentGroupEntity> grouptList = groupService.selectGroupByPid(projectId, componentType, Status.VALID.getCode());
		modelMap.put("grouptList", grouptList);
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
