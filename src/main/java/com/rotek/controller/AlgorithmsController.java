package com.rotek.controller;

import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.constant.Status;
import com.rotek.dto.ComponentGroupDto;
import com.rotek.entity.AlgorithmsEntity;
import com.rotek.service.impl.AlgorithmsService;
import com.rotek.service.impl.ComponentGroupService;

/**
* @ClassName:AlgorithmController
* @Description: 设置报警算法
* @Author liusw
* @date 2014年6月29日 下午3:12:11
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/algorithms")
public class AlgorithmsController {
	
	@Autowired
	private AlgorithmsService algorithmsService;
	
	@Autowired
	private ComponentGroupService groupService;
	
	/**
	* @MethodName: toUnProcessInfoList 
	* @Description:
	* @return
	* @author liusw
	*/
/*	@RequestMapping("toAlgorithmsList")
	public String toAlgorithmsList() {
		// 返回JSP文件目录及对应的JSP文件
		return "admin/algorithms/algorithms";
	}*/
	
	/**
	* @MethodName: getComGroupDetail 
	* @Description: 根据组ID查询零件组详情
	* 设置算法1，算法1设置额定更换时间，额定更换时间只是针对组
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
		ComponentGroupDto cgroup = groupService.selectOneComGroupById(id);
		model.put("data", cgroup);
		return "jsonView";
	}
	
	/**
	* @MethodName: setAlgorithm 
	* @Description: 算法设置实现
	* @param request
	* @param response
	* @param algorithmType  算法类别，1 --> 算法1；2 --> 算法2； ... ...  8 --> 算法8；
	* @param algorithmEntity
	* @param model
	* @throws Exception
	* @author WangJuZhu
	*/
	@RequestMapping("setAlgorithm/{algorithmType}")
	public void setAlgorithm(HttpServletRequest request,HttpServletResponse response,
			@PathVariable(value = "algorithmType") Integer algorithmType,
			AlgorithmsEntity algorithmEntity,ModelMap model ) throws Exception {

		algorithmEntity.setAlgorithm_type(algorithmType);
		algorithmEntity.setStatus(Status.VALID.getCode());
		
		List<String> messages = algorithmsService.modifyAlgorithms(algorithmEntity);
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}
	
	
	
	
	
	
	
}
