/**
* @FileName: BroadCastController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-6 上午10:27:49
* @version V1.0
*/
package com.rotek.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rotek.dto.BroadCastDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.BroadCastEntity;
import com.rotek.platform.util.ListPager;
import com.rotek.service.impl.BroadCastService;

/**
 * @ClassName: BroadCastController
 * @Description: 轮播通知
 * @author chenwenpeng
 * @date 2013-8-6 上午10:27:49
 *
 */
@Controller
@RequestMapping("/admin/broadcast")
public class BroadCastController {

	@Autowired
	private BroadCastService broadCastService;

	/**
	* @Title: toBuildings
	* @Description: 转到轮播界面
	* @return
	* @return String
	* @throws
	*/
	@RequestMapping("toBroadcasts")
	public String toBroadcasts(){

		return "admin/broadcast_advice/broadcasts";
	}

	/**
	* @Title: listBroadcasts
	* @Description:
	* @param modelMap
	* @param user
	* @param start
	* @param limit
	* @param id
	* @param alt
	* @param target
	* @param status
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listBroadcasts")
	public String listBroadcasts(ModelMap modelMap,UserDto user,
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,

			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "buildingId", defaultValue = "") Integer buildingId,
			@RequestParam(value = "alt", defaultValue = "") String alt,
			@RequestParam(value = "target", defaultValue = "") String target,
			@RequestParam(value = "status", defaultValue = "") Integer status) throws SQLException{
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		BroadCastEntity broadCast = new BroadCastEntity();
		broadCast.setId(id);
		broadCast.setBuildingId(buildingId);
		broadCast.setAlt(alt);
		broadCast.setTarget(target);
		broadCast.setStatus(status);

		List<BroadCastDto> broadcasts = broadCastService.listBroadcasts(user,broadCast,pager);
		modelMap.put("dataList", broadcasts);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}


	/**
	 * @throws IOException
	 * @throws IllegalStateException
	* @Title: addBroadCast
	* @Description:
	* @param modelMap
	* @param id
	* @param alt
	* @param name
	* @param target
	* @param building_ids
	* @param status
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	@RequestMapping("addBroadcast")
	public void addBroadCast(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "alt", defaultValue = "") String alt,
			@RequestParam(value = "target", defaultValue = "") String target,
			@RequestParam(value = "building_ids", defaultValue = "") String building_ids,
			@RequestParam(value = "status", defaultValue = "") Integer status) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException{

		BroadCastEntity broadCast = new BroadCastEntity();
		broadCast.setAlt(alt);
		broadCast.setTarget(target);
		broadCast.setStatus(status);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;


		List<String> messages = broadCastService.addBroadCast(broadCast,building_ids,multipartRequest);
		JSONObject json = new JSONObject();
		 json.put("success", null == messages ? true : false);
		 json.put("messages", messages);
		 response.setStatus(200);
		 response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}

	/**
	* @Title: getBroadcastDetail
	* @Description: 获取轮播的详情
	* @param modelMap
	* @param id
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getBroadcastDetail")
	public String getBroadcastDetail(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException{

		BroadCastEntity broadcast = broadCastService.getBroadcastDetail(id);
		modelMap.put("data", broadcast);
		return "jsonView";
	}

	/**
	* @Title: modifyBroadcast
	* @Description: 修改轮播
	* @param modelMap
	* @param request
	* @param response
	* @param alt
	* @param target
	* @param buildingId
	* @param status
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @throws IllegalStateException
	* @throws IOException
	* @return void
	* @throws
	*/
	@RequestMapping("modifyBroadcast")
	public void modifyBroadcast(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "alt", defaultValue = "") String alt,
			@RequestParam(value = "target", defaultValue = "") String target,
			@RequestParam(value = "buildingId", defaultValue = "") Integer buildingId,
			@RequestParam(value = "status", defaultValue = "") Integer status) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException{

		BroadCastEntity broadCast = new BroadCastEntity();
		broadCast.setId(id);
		broadCast.setName(name);
		broadCast.setBuildingId(buildingId);
		broadCast.setAlt(alt);
		broadCast.setTarget(target);
		broadCast.setStatus(status);

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;


		List<String> messages = broadCastService.modifyBroadcast(broadCast,multipartRequest);
		JSONObject json = new JSONObject();
		 json.put("success", null == messages ? true : false);
		 json.put("messages", messages);
		 response.setStatus(200);
		 response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}

	/**
	* @Title: deleteBroadcast
	* @Description: 删除轮播
	* @param ids
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteBroadcast")
	public String deleteBroadcast(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap modelMap) throws SQLException{

		List<String> messages = broadCastService.deleteBroadcast(ids);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}
}
