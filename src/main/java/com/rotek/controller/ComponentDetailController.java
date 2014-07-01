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
import com.rotek.service.impl.ComponentDetailService;
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
	private ComponentDetailService groupService;
	
	@Autowired
	private ProjectService projectService;

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
			@RequestParam(value = "id", defaultValue = "") Integer id, // 组Id
			@RequestParam(value = "r_project_id", defaultValue = "") Integer project_id, // 工程ID
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,  // 工程名称
			@RequestParam(value = "group_bh", defaultValue = "") String group_bh, //组编号
			@RequestParam(value = "group_mc", defaultValue = "") String group_mc,  // 组名称
			@RequestParam(value = "pp", defaultValue = "") String pp, // 品牌
			@RequestParam(value = "xh", defaultValue = "") String xh,  // 型号
			@RequestParam(value = "gl", defaultValue = "") String gl,  // 功率
			@RequestParam(value = "gg", defaultValue = "") String gg,  // 规格
			@RequestParam(value = "cll", defaultValue = "") String cll,  // 处理量
			
			@RequestParam(value = "status", defaultValue = "1") Integer status,  // 状态
			HttpServletRequest request, UserDto user, ModelMap modelMap)throws Exception {
		
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		ComponentGroupDto comDetail = new ComponentGroupDto();
		comDetail.setId(id);
		comDetail.setR_project_id(project_id);  //工程ID
		comDetail.setProject_name(gcmc);   // 工程名称
		comDetail.setGroup_lb(groupType);  // 组类别
		comDetail.setGroup_bh(group_bh);   // 组编号
		comDetail.setGroup_mc(group_mc);   // 组名称
		comDetail.setStatus(status);
		
		comDetail.setPp(pp);
		comDetail.setXh(xh);
		comDetail.setGl(gl);
		comDetail.setCll(cll);
		comDetail.setGg(gg);

		List<ComponentGroupDto> cgroup = groupService.listComDetail(user, comDetail, pager);
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
			@RequestParam(value = "id", defaultValue = "") Integer project_id,
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,
			@RequestParam(value = "group_bh", defaultValue = "") String group_bh,
			@RequestParam(value = "group_mc", defaultValue = "") String group_mc,
			@RequestParam(value = "pp", defaultValue = "") String pp,
			@RequestParam(value = "xh", defaultValue = "") String xh,
			@RequestParam(value = "gl", defaultValue = "") String gl,
			@RequestParam(value = "gg", defaultValue = "") String gg,
			@RequestParam(value = "cll", defaultValue = "") String cll,
			@RequestParam(value = "tlgd", defaultValue = "") Integer tlgd,
			@RequestParam(value = "cz", defaultValue = "") String cz,
			@RequestParam(value = "sl", defaultValue = "") Integer sl,
			@RequestParam(value = "ckdj", defaultValue = "") Double ckdj,
			@RequestParam(value = "gljd", defaultValue = "") Double gljd,
			@RequestParam(value = "lxcc", defaultValue = "") Double lxcc,
			@RequestParam(value = "lxjkxs", defaultValue = "") String lxjkxs,
			@RequestParam(value = "others", defaultValue = "") String others,
			@RequestParam(value = "csl", defaultValue = "") String csl,
			@RequestParam(value = "hsl", defaultValue = "") Double hsl,
			@RequestParam(value = "plfs", defaultValue = "") String plfs,
			@RequestParam(value = "dgsm", defaultValue = "") Integer dgsm,
			@RequestParam(value = "rj", defaultValue = "") String rj,
			@RequestParam(value = "yjnd", defaultValue = "") String yjnd,
			@RequestParam(value = "yjedtjl", defaultValue = "") String yjedtjl,
			
			ModelMap model ) throws Exception {

		ComponentGroupEntity comDetail = new ComponentGroupEntity();
		comDetail.setR_project_id(project_id);  //工程ID
		comDetail.setGroup_lb(groupType);  // 组类别
		comDetail.setGroup_bh(group_bh);   // 组编号
		comDetail.setGroup_mc(group_mc);   // 组名称
		comDetail.setStatus(Status.VALID.getCode());
		
		// 保存 泵明细信息
		if(groupType == ComponentType.PUMP.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setGl(gl);
		}
		
		//保存 砂滤器 和 软化器 明细信息
		if(groupType == ComponentType.SAND_FILTER.getCode() || groupType == ComponentType.SOFTENER.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setCll(cll);
			comDetail.setGg(gg);
			comDetail.setSl(sl);
			comDetail.setTlgd(tlgd);
			comDetail.setCkdj(ckdj);
			comDetail.setCz(cz);
		}
		
		//保存 碳滤器 明细信息
		if(groupType == ComponentType.CARBON_FILTE.getCode()){
			comDetail.setCll(cll);
			comDetail.setGg(gg);
			comDetail.setTlgd(tlgd);
			comDetail.setCz(cz);
		}
		
		//保存 过滤器 明细信息
		if(groupType == ComponentType.FILTER_GROUP.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setCll(cll);
			comDetail.setGg(gg);
			comDetail.setSl(sl);
			comDetail.setCkdj(ckdj);
			comDetail.setCz(cz);
			comDetail.setGljd(gljd);
			comDetail.setLxcc(lxcc);
			comDetail.setLxjkxs(lxjkxs);
			comDetail.setOthers(others);
		}
		
		//保存 膜 明细信息
		if(groupType == ComponentType.FILM_GROUP.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setCsl(csl);
			comDetail.setGg(gg);
			comDetail.setSl(sl);
			comDetail.setCkdj(ckdj);
			comDetail.setHsl(hsl);
			comDetail.setPlfs(plfs);
		}
		
		//保存 紫外杀菌器 明细信息
		if(groupType == ComponentType.UVSTERILIZER.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setCll(cll);
			comDetail.setGl(gl);
			comDetail.setSl(sl);
			comDetail.setDgsm(dgsm);
			comDetail.setHsl(hsl);
			comDetail.setPlfs(plfs);
		}
		
		// 保存 水箱 明细信息
		if(groupType == ComponentType.TANK_GROUP.getCode()){
			comDetail.setGg(gg);
			comDetail.setRj(rj);
			comDetail.setOthers(others);
		}
		
		//保存 加药装置器 明细信息
		if(groupType == ComponentType.DOSESETTING.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setYjnd(yjnd);
			comDetail.setGl(gl);
			comDetail.setYjedtjl(yjedtjl);
			comDetail.setCkdj(ckdj);
		}
		
		List<String> messages = groupService.addComDetail(comDetail);
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
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "r_project_id", defaultValue = "") Integer project_id,
			@RequestParam(value = "gcmc", defaultValue = "") String gcmc,
			@RequestParam(value = "group_bh", defaultValue = "") String group_bh,
			@RequestParam(value = "group_mc", defaultValue = "") String group_mc,
			@RequestParam(value = "pp", defaultValue = "") String pp,
			@RequestParam(value = "xh", defaultValue = "") String xh,
			@RequestParam(value = "gl", defaultValue = "") String gl,
			@RequestParam(value = "gg", defaultValue = "") String gg,
			@RequestParam(value = "cll", defaultValue = "") String cll,
			@RequestParam(value = "tlgd", defaultValue = "") Integer tlgd,
			@RequestParam(value = "cz", defaultValue = "") String cz,
			@RequestParam(value = "sl", defaultValue = "") Integer sl,
			@RequestParam(value = "ckdj", defaultValue = "") Double ckdj,
			@RequestParam(value = "gljd", defaultValue = "") Double gljd,
			@RequestParam(value = "lxcc", defaultValue = "") Double lxcc,
			@RequestParam(value = "lxjkxs", defaultValue = "") String lxjkxs,
			@RequestParam(value = "others", defaultValue = "") String others,
			@RequestParam(value = "csl", defaultValue = "") String csl,
			@RequestParam(value = "hsl", defaultValue = "") Double hsl,
			@RequestParam(value = "plfs", defaultValue = "") String plfs,
			@RequestParam(value = "dgsm", defaultValue = "") Integer dgsm,
			@RequestParam(value = "rj", defaultValue = "") String rj,
			@RequestParam(value = "yjnd", defaultValue = "") String yjnd,
			@RequestParam(value = "yjedtjl", defaultValue = "") String yjedtjl,
			
			ModelMap model, HttpServletRequest request,HttpServletResponse response) throws SQLException,
			IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		ComponentGroupEntity comDetail = groupService.getComDetailById(id);
		comDetail.setR_project_id(project_id);  //工程ID
		comDetail.setGroup_lb(comDetail.getGroup_lb());  // 组类别
		comDetail.setGroup_bh(group_bh);   // 组编号
		comDetail.setGroup_mc(group_mc);   // 组名称
		comDetail.setStatus(Status.VALID.getCode());
		
		// 保存 泵明细信息
		if(groupType == ComponentType.PUMP.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setGl(gl);
		}

		//保存 砂滤器 和 软化器 明细信息
		if(groupType == ComponentType.SAND_FILTER.getCode() || groupType == ComponentType.SOFTENER.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setCll(cll);
			comDetail.setGg(gg);
			comDetail.setSl(sl);
			comDetail.setTlgd(tlgd);
			comDetail.setCkdj(ckdj);
			comDetail.setCz(cz);
		}

		//保存 碳滤器 明细信息
		if(groupType == ComponentType.CARBON_FILTE.getCode()){
			comDetail.setCll(cll);
			comDetail.setGg(gg);
			comDetail.setTlgd(tlgd);
			comDetail.setCz(cz);
		}
		
		//保存 过滤器 明细信息
		if(groupType == ComponentType.FILTER_GROUP.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setCll(cll);
			comDetail.setGg(gg);
			comDetail.setSl(sl);
			comDetail.setCkdj(ckdj);
			comDetail.setCz(cz);
			comDetail.setGljd(gljd);
			comDetail.setLxcc(lxcc);
			comDetail.setLxjkxs(lxjkxs);
			comDetail.setOthers(others);
		}
		
		//保存 膜 明细信息
		if(groupType == ComponentType.FILM_GROUP.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setCsl(csl);
			comDetail.setGg(gg);
			comDetail.setSl(sl);
			comDetail.setCkdj(ckdj);
			comDetail.setHsl(hsl);
			comDetail.setPlfs(plfs);
		}
		
		//保存 紫外杀菌器 明细信息
		if(groupType == ComponentType.UVSTERILIZER.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setCll(cll);
			comDetail.setGl(gl);
			comDetail.setSl(sl);
			comDetail.setDgsm(dgsm);
			comDetail.setCkdj(ckdj);
			comDetail.setOthers(others);
		}
		
		// 保存 水箱 明细信息
		if(groupType == ComponentType.TANK_GROUP.getCode()){
			comDetail.setGg(gg);
			comDetail.setRj(rj);
			comDetail.setOthers(others);
		}
		
		//保存 加药装置器 明细信息
		if(groupType == ComponentType.DOSESETTING.getCode()){
			comDetail.setPp(pp);
			comDetail.setXh(xh);
			comDetail.setYjnd(yjnd);
			comDetail.setGl(gl);
			comDetail.setYjedtjl(yjedtjl);
			comDetail.setCkdj(ckdj);
		}
		
		List<String> messages = groupService.modifyComDetail(comDetail);
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

		List<String> messages = groupService.deleteComDetail(ids);
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

		ComponentGroupDto cgroup = groupService.getOneComDetail(id);
		model.put("data", cgroup);
		return "jsonView";
	}
	
	/**
	* @MethodName: listProjectByStatus 
	* @Description: 查询有效的工程信息
	* @param modelMap
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("listProjectByStatus")
	public String listProjectByStatus(ModelMap modelMap) throws SQLException{
		List<ProjectEntity> projectList = projectService.listProjectByStatus(Status.VALID.getCode());
		modelMap.put("dataList", projectList);
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
