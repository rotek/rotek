/**
 * @FileName: IndexController.java
 * @Package com.rotek.controller
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013531 上午10:33:55
 * @version V1.0
 */
package com.rotek.controller.front;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.dto.UserDto;
import com.rotek.entity.ConsultantEntity;
import com.rotek.service.impl.StewardService;

/**
 * 新闻公告
 * 
 * @author chenwenpeng
 * 
 */
@Controller
@RequestMapping("/front/steward")
public class FrontStewardController {

	@Autowired
	private StewardService stewardService;

	/**
	 * 返回资源下载
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toResource")
	public String toResource(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {

		List<Map<String,Object>> dataList = stewardService.getResourceList();
		modelMap.put("dataList", dataList);
		return "front/resource";
	}
	
	/**
	 * 返回咨询平台
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toConsultants")
	public String toConsultants(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {
		
		List<Map<String,Object>> dataList = stewardService.getConsultantList();
		modelMap.put("dataList", dataList);
		return "front/consultants";
	}
	
	/**
	 * 返回咨询平台
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toAddConsultant")
	public String toAddConsultant(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {
		
		return "front/addConsultant";
	}
	
	/**
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("addConsultant")
	public String addConsultant(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(defaultValue="1",value="type") Integer type,
			@RequestParam(defaultValue="",value="content") String content,
			UserDto user)
			throws SQLException {
		
		ConsultantEntity cte = new ConsultantEntity();
		cte.setConsultant_content(content);
		cte.setSender_id(user.getId());
		cte.setStatus(ConsultantEntity.STATUS_ENABLED);
		cte.setType(type);
		cte.setSend_time(new Date());
		cte.setResponse_id(0);
		
		stewardService.addConsultant(cte);
		
		return this.toConsultants(request, modelMap);
	}
}
