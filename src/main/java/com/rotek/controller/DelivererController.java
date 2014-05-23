/**
* @FileName: DeliverController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-28 下午01:43:43
* @version V1.0
*/
package com.rotek.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.dto.DelivererDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.DelivererEntity;
import com.cta.platform.util.ListPager;
import com.rotek.service.impl.DelivererService;

/**
 * @ClassName: DeliverController
 * @Description: 配送员管理
 * @author chenwenpeng
 * @date 2013-6-28 下午01:43:43
 *
 */
@Controller
@RequestMapping(value="/admin/deliverer")
public class DelivererController {

	@Autowired
	private DelivererService delivererService;

	/**
	* @Title: toDeliverers
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("toDeliverers")
	public String toDeliverers(){

		return "admin/deliverer/deliverers";
	}
	/**
	 * @throws SQLException
	* @Title: listDeliverers_s
	* @Description: 获取所有配送员的信息
	* @param @param id
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("listDeliverers")
	public String listDeliverers(ModelMap modelMap,UserDto user,
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			DelivererDto deliverer) throws SQLException{
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		List<DelivererDto> deliverers = delivererService.listDeliverers(deliverer,user,pager);
		modelMap.put("dataList", deliverers);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	 * @throws SQLException
	 * @Title: listDeliverers_s
	 * @Description: 获取所有配送员的简单信息
	 * @param @param id
	 * @param @param modelMap
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("listDeliverers_s")
	public String listDeliverers_s(ModelMap modelMap,
			@RequestParam(value = "realname", defaultValue = "") String realname,
			@RequestParam(value = "range", defaultValue = "0") Integer range) throws SQLException{

		List<Map<String,Object>> deliverers = delivererService.listDeliverers_s(realname,range);
		modelMap.put("dataList", deliverers);
		return "jsonView";
	}

	/**
	 * @throws SQLException
	 * @Title: listDeliverers_s
	 * @Description: 获取本部门管理的配送员的简单信息
	 * @param @param id
	 * @param @param modelMap
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("listDeliverers_s_dep")
	public String listDeliverers_s_dep(
			ModelMap modelMap,
			HttpServletRequest request,
			UserDto user) throws SQLException{

		List<Map<String,Object>> deliverers = delivererService.listDeliverers_s_dep(user);
		modelMap.put("dataList", deliverers);
		return "jsonView";
	}


	/**
	 * @throws SQLException
	* @Title: getMenuDetail
	* @Description: 获取配送员详情
	* @param @param modelMap
	* @param @param id
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("getDelivererDetail")
	public String getDelivererDetail(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException{

		modelMap.put("data", delivererService.getDelivererDetail(id));
		return "jsonView";
	}


	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @Title: getMenuDetail
	 * @Description: 获取配送员详情
	 * @param @param modelMap
	 * @param @param id
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("modifyDeliverer")
	public String modifyDeliverer(
			ModelMap modelMap,
			DelivererEntity deliverer)throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		List<String> messages = delivererService.modifyDeliverer(deliverer);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @Title: getMenuDetail
	 * @Description: 获取配送员详情
	 * @param @param modelMap
	 * @param @param id
	 * @param @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("addDeliverer")
	public String addDeliverer(
			ModelMap modelMap,UserDto user,
			DelivererEntity deliverer)throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		List<String> messages = delivererService.addDeliverer(deliverer,user);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}


	/**删除一个或多个配送员信息
	 * @param roleEntity
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("deleteDeliverer")
	public String deleteDeliverer(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = delivererService.deleteDeliverer(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
}
