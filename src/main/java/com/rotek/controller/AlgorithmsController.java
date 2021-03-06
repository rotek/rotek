/**
 * @Copyright:Copyright (c) 2013 - 2100
 * @Company:JXWY Co.,Ltd.
 */
package com.rotek.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
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
import com.rotek.dto.AlgorithmsDto;
import com.rotek.dto.ProjectDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.AlgorithmsEntity;
import com.rotek.entity.BaseEntity;
import com.rotek.entity.ComponentDetailEntity;
import com.rotek.entity.ComponentGroupEntity;
import com.rotek.entity.TableDescEntity;
import com.rotek.service.impl.AlgorithmsService;
import com.rotek.service.impl.ComponentDetailService;
import com.rotek.service.impl.ComponentGroupService;
import com.rotek.service.impl.ProjectService;
import com.rotek.util.ProjectUtils;

/**
* @ClassName:AlgorithmsController
* @Description: 算法设置管理控制器
* @Author WangJuZhu
* @date 2014年7月16日 上午10:54:24
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/algorithms")
public class AlgorithmsController {

	@Autowired
	private AlgorithmsService algorithmsService;
	
	@Autowired
	private ProjectService projectService;
	
	@Autowired
	private ComponentGroupService groupService;
	
	@Autowired
	private ComponentDetailService detailService;

	/**
	* @MethodName: toAlgorithms 
	* @Description: 转到算法设置管理页面
	* 1,算法1;  2,算法2 ;  3,算法3;   4,算法4 ;
	* 5,算法5;  6,算法6 ;  7,算法7 ;  8,算法8 ;
	* @param request
	* @param response
	* @param modelMap
	* @return
	* @author WangJuZhu
	*/
	@RequestMapping("/toAlgorithms/{algorithmsType}")
	public String toAlgorithms( HttpServletRequest request,HttpServletResponse response,
			@PathVariable(value = "algorithmsType") Integer algorithmsType,ModelMap modelMap )throws Exception {
		modelMap.put("algorithmsType", algorithmsType);
		return "admin/algorithms/algorithms";
	}
	
	/**
	* @MethodName: listAlgorithms 
	* @Description: 算法设置列表
	* @param start
	* @param limit
	* @return
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("/listAlgorithms/{algorithmsType}")
	public String listAlgorithms(
			@PathVariable(value = "algorithmsType") Integer algorithmsType,  // 算法分类
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "10") Integer limit,
			@RequestParam(value = "customer_name", defaultValue = "") String customer_name,  // 用户名称
			@RequestParam(value = "project_name", defaultValue = "") String project_name,  // 工程名称
			@RequestParam(value = "component_group_name", defaultValue = "") String component_group_name,  // 零件（组）名称
			@RequestParam(value = "component_name", defaultValue = "") String component_name,  // 零件名称
			@RequestParam(value = "algorithm_alias", defaultValue = "") String algorithm_alias,  // 提示别名
			@RequestParam(value = "tip_content", defaultValue = "") String tip_content,  // 提示内容
			@RequestParam(value = "status", defaultValue = "1") Integer status,  // 状态
			HttpServletRequest request, UserDto user, ModelMap modelMap)throws Exception {
		
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		AlgorithmsDto algoriGroup = new AlgorithmsDto();
		algoriGroup.setCustomer_name(customer_name);
		algoriGroup.setProject_name(project_name);
		algoriGroup.setComponent_group_name(component_group_name);
		algoriGroup.setComponent_name(component_name);
		algoriGroup.setAlgorithm_alias(algorithm_alias);
		algoriGroup.setAlgorithm_type(algorithmsType);
		algoriGroup.setStatus(status);

		List<AlgorithmsDto> algor = algorithmsService.listAlgorithms(user, algoriGroup, pager);
		modelMap.put("dataList", algor);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @MethodName: addAlgorithms 
	* @Description: 添加算法设置
	* @param request
	* @param response
	* @param model
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("addAlgorithms/{algorithmsType}")
	public void addAlgorithms(HttpServletRequest request,HttpServletResponse response,
			@PathVariable(value = "algorithmsType") Integer algorithmsType,
			AlgorithmsEntity algorithm, ModelMap model ) throws Exception {

		algorithm.setAlgorithm_type(algorithmsType);
		algorithm.setStatus(BaseEntity.STATUS_ENABLED);
		List<String> messages = algorithmsService.addAlgorithms(algorithm);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}

	/**
	* @MethodName: modifyAlgorithms
	* @Description: 修改算法设置
	* @param id
	* @throws IOException
	* @author WangJuZhu
	*/
	@RequestMapping("modifyAlgorithms/{algorithmsType}")
	public void modifyAlgorithms( HttpServletRequest request,HttpServletResponse response,
			@PathVariable(value = "algorithmsType") Integer algorithmsType,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			AlgorithmsEntity algorithm, ModelMap model) throws Exception {

		/*AlgorithmsEntity oldAlgorithm = algorithmsService.getAlgorithmsById(id);
		oldAlgorithm.setR_customer_id(algorithm.getR_customer_id());
		oldAlgorithm.setR_project_id(algorithm.getR_project_id());
		oldAlgorithm.setR_component_group_id(algorithm.getR_component_group_id());
		oldAlgorithm.setR_component_detail_id(algorithm.getR_component_detail_id());
		oldAlgorithm.setAlgorithm_alias(algorithm.getAlgorithm_alias());
		oldAlgorithm.setLjghsj(algorithm.getLjghsj());  	// 零件更换时间
		oldAlgorithm.setLjedyxsj(algorithm.getLjedyxsj()); 	// 零件额定运行时间        */	
		
		algorithm.setId(id);
		algorithm.setAlgorithm_type(algorithmsType);
		algorithm.setStatus(BaseEntity.STATUS_ENABLED);
		
		List<String> messages = algorithmsService.modifyAlgorithms(algorithm);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	/**
	* @MethodName: deleteAlgorithms 
	* @Description: 删除算法设置
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("deleteAlgorithms")
	public String deleteAlgorithms(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap model) throws SQLException {

		List<String> messages = algorithmsService.deleteAlgorithms(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	/**
	* @MethodName: getAlgorithmDetail 
	* @Description: 根据算法ID查询算法设置详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("getAlgorithmDetail")
	public String getAlgorithmDetail( @RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap model) throws SQLException {
		AlgorithmsDto algor = algorithmsService.getOneAlgorithm(id);
		model.put("data", algor);
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
	@RequestMapping("/selectProjectByType/{projectType}")
	public String selectProjectByType(
			@PathVariable(value="projectType") Integer projectType,
			ModelMap modelMap) throws SQLException{
		//工程类别（1、托管服务；2、EMC工程）
		List<ProjectDto> projectList = projectService.selectProjectByType(BaseEntity.STATUS_ENABLED,projectType);
		modelMap.put("projectList", projectList);
		return "jsonView";
	}
	
	/**
	* @MethodName: selectGroupByPid 
	* @Description: 由 工程ID和  查询分组信息
	* @param projectId 工程ID
	* @param componentType  零件分组类型 (9个分组)
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
		if(projectId != 0 && projectId != null){
			List<ComponentGroupEntity> grouptList = groupService.selectGroupByPid(projectId, componentType, BaseEntity.STATUS_ENABLED);
			modelMap.put("grouptList", grouptList);
		}
		return "jsonView";
	}
	
	/**
	* @MethodName: selectGroupDetailByIds 
	* @Description: 由 工程ID、零件组ID和   查询零件信息
	* @param projectId 工程ID
	* @param groupId 零件组ID
	* @param componentType 零件分组类型
	* @param modelMap
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("selectGroupDetailByIds/{projectId}/{groupId}/{componentType}")
	public String selectGroupDetailByIds(
			@PathVariable(value="projectId") Integer projectId,
			@PathVariable(value="groupId") Integer groupId,
			@PathVariable(value="componentType") Integer componentType,
			ModelMap modelMap) throws SQLException{
		if(projectId != 0 && projectId != null){
			List<ComponentDetailEntity> detailList = detailService.selectGroupDetailByIds(projectId,groupId,componentType,BaseEntity.STATUS_ENABLED);
			modelMap.put("groupDetailList", detailList);
		}
		return "jsonView";
	}
	
	@RequestMapping("getParamsByGroupId/{componentType}")
	public String getParamsByGroupId(
			@PathVariable(value="componentType") Integer componentType, 
			ModelMap modelMap) throws SQLException{
		
		List<TableDescEntity> tempDesc = new ArrayList<>();
		
		// 所有字段信息
		List<TableDescEntity> allDesc = ProjectUtils.getColumnDesc("r_component_detail");
		// 找出以 “ED”开头的参数，column_comment 字段组合成   字段名 + "-" + 注释
		for(TableDescEntity temp : allDesc){
			if(temp.getColumnName().startsWith("ED")){
				temp.setColumnComment(temp.getColumnName() + "-" + temp.getColumnComment());
				tempDesc.add(temp);
			}
		}
		modelMap.put("paramList", tempDesc);
		
		return "jsonView";
	}
	
	/**
	* @MethodName: getParamsValue 
	* @Description: 根据参数字段名和零件ID，查询参数的额定值
	* @param paramName 参数的字段名
	* @param componentId 零件ID
	* @param modelMap
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("/getParamsValue")
	public String getParamsValue(
			@RequestParam(value = "paramName" , defaultValue = "" ) String paramName ,
			@RequestParam(value = "componentId" , defaultValue = "" ) Integer componentId ,
			ModelMap modelMap) throws SQLException{
		
		if(StringUtils.isNotBlank(paramName)){
			/*String[] temp = paramName.split("-");
			//String tempStr = temp[0]
			ComponentDetailEntity paramValue = detailService.getComDetailById(componentId );*/
		}
		
		List<ProjectDto> projectList = null ;
		modelMap.put("projectList", projectList);
		return "jsonView";
	}
	
	@RequestMapping("getLocalTables")
	public String getLocalTables(ModelMap modelMap) throws SQLException{
		List<TableDescEntity> tables = ProjectUtils.getTableNames("tb%");
		modelMap.put("locTableList", tables);
		
		return "jsonView";
	}
	
	@RequestMapping("selectLocalParams/{loalTableName}")
	public String selectLocalParams(
			@PathVariable(value="loalTableName") String loalTableName, 
			ModelMap modelMap) throws SQLException{
		
		// 所有字段信息
		List<TableDescEntity> allDesc = ProjectUtils.getColumnDesc(loalTableName);
		modelMap.put("localParamList", allDesc);
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
