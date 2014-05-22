/**
* @FileName: GiftController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-9 下午05:22:37
* @version V1.0
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

import com.rotek.constant.DataStatus;
import com.rotek.dto.GiftDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.GiftEntity;
import com.rotek.platform.util.ListPager;
import com.rotek.service.impl.GiftService;

/**
 * @ClassName: GiftController
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-9 下午05:22:37
 *
 */
@Controller
@RequestMapping("/admin/gift")
public class GiftController {

	@Autowired
	private GiftService giftService;

	/**
	* @Title: toUserGifts
	* @Description: 转到所有用户兑换礼品
	* @return
	* @return String
	* @throws
	*/
	@RequestMapping("toUserGifts")
	public String toUserGifts(){

		return "admin/gift/userGifts";
	}


	/**
	* @Title: listUserGifts
	* @Description:
	* @param start
	* @param limit
	* @param id
	* @param user_name
	* @param telephone
	* @param start_time
	* @param end_time
	* @param status
	* @param request
	* @param user
	* @param modelMap
	* @return
	* @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("listUserGifts")
	public String listUserGifts(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,

			@RequestParam(value="exchange_id", defaultValue = "") Integer exchange_id,
			@RequestParam(value="gift_name", defaultValue = "") String gift_name,
			@RequestParam(value="user_name", defaultValue = "") String user_name,
			@RequestParam(value="exchange_status", defaultValue = "") Integer exchange_status,
			@RequestParam(value = "start_time", defaultValue = "") Date start_time,
			@RequestParam(value = "end_time", defaultValue = "") Date end_time,
			HttpServletRequest request,UserDto user,ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		GiftDto gift = new GiftDto();
		gift.setExchange_id(exchange_id);
		gift.setGift_name(gift_name);
		gift.setUser_name(user_name);
		gift.setExchange_status(exchange_status);

		List<GiftDto> giftUsers = giftService.listUserGifts(user,gift,pager,start_time,end_time);
		modelMap.put("dataList", giftUsers);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @Title: deleteUserGift
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteUserGift")
	public String deleteUserGift(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = giftService.deleteUserGift(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: modifyUserGiftStatus
	* @Description:
	* @param ids
	* @param exchange_status
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("modifyUserGiftStatus")
	public String modifyUserGiftStatus(
			@RequestParam(value="ids", defaultValue="") String ids,
			@RequestParam(value="exchange_status", defaultValue="") Integer exchange_status,
			ModelMap model) throws SQLException{

		List<String> messages = giftService.modifyUserGiftStatus(ids,exchange_status);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: getUserGiftDetail
	* @Description:
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getUserGiftDetail")
	public String getUserGiftDetail(
			@RequestParam(value="id", defaultValue="") Integer id,ModelMap model) throws SQLException{

		GiftDto gift = giftService.getUserGiftDetail(id);
		model.put("data", gift);
		return "jsonView";
	}


	/**
	 * @Title: toGifts
	 * @Description: 转到所有礼物
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("toGifts")
	public String toGifts(){

		return "admin/gift/gifts";
	}

	/**
	* @Title: listGifts
	* @Description: 列出所有的礼品
	* @param start
	* @param limit
	* @param id
	* @param name
	* @param points
	* @param status
	* @param request
	* @param user
	* @param modelMap
	* @return
	* @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("listGifts")
	public String listGifts(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,

			@RequestParam(value="id", defaultValue = "") Integer id,
			@RequestParam(value="name", defaultValue = "") String name,
			@RequestParam(value="points", defaultValue = "") Integer points,
			@RequestParam(value="status", defaultValue = "") Integer status,
			HttpServletRequest request,UserDto user,ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		GiftEntity gift = new GiftEntity();
		gift.setId(id);
		gift.setName(name);
		gift.setPoints(points);
		gift.setStatus(status);

		List<GiftDto> gifts = giftService.listUserGifts(user,gift,pager);
		modelMap.put("dataList", gifts);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}


	/**
	* @Title: addGift
	* @Description: 添加礼品信息
	* @param name
	* @param descr
	* @param points
	* @param status
	* @param model
	* @param request
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @throws IllegalStateException
	* @throws IOException
	* @return String
	* @throws
	*/
	@RequestMapping("addGift")
	public void addGift(
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="descr", defaultValue="") String descr,
			@RequestParam(value="points", defaultValue="") Integer points,
			@RequestParam(value="status", defaultValue="") Integer status,
			ModelMap model,HttpServletRequest request,HttpServletResponse response) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException{

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		GiftEntity gift = new GiftEntity();
		gift.setName(name);
		gift.setDescr(descr);
		gift.setPoints(points);
		gift.setStatus(status);
		gift.setPic(DataStatus.NONE_STRING);

		List<String> messages = giftService.addGift(gift,multipartRequest);
		JSONObject json = new JSONObject();
		 json.put("success", null == messages ? true : false);
		 json.put("messages", messages);
		 response.setStatus(200);
		 response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}

	/**
	* @Title: getGiftDetail
	* @Description: 获取礼品详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getGiftDetail")
	public String getGiftDetail(
			@RequestParam(value="id", defaultValue="") Integer id,ModelMap model) throws SQLException{

		GiftEntity gift = giftService.getGiftDetail(id);
		model.put("data", gift);
		return "jsonView";
	}


	/**
	* @Title: modfiyGift
	* @Description:
	* @param id
	* @param name
	* @param pic
	* @param descr
	* @param points
	* @param status
	* @param model
	* @param request
	* @param response
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @throws IllegalStateException
	* @throws IOException
	* @return void
	* @throws
	*/
	@RequestMapping("modifyGift")
	public void modifyGift(
			@RequestParam(value="id", defaultValue="") Integer id,
			@RequestParam(value="name", defaultValue="") String name,
			@RequestParam(value="pic", defaultValue="") String pic,
			@RequestParam(value="descr", defaultValue="") String descr,
			@RequestParam(value="points", defaultValue="") Integer points,
			@RequestParam(value="status", defaultValue="") Integer status,
			ModelMap model,HttpServletRequest request,HttpServletResponse response) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException{

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		GiftEntity gift = new GiftEntity();
		gift.setId(id);
		gift.setName(name);
		gift.setDescr(descr);
		gift.setPoints(points);
		gift.setStatus(status);
		gift.setPic(pic);

		List<String> messages = giftService.modifyGift(gift,multipartRequest);
		JSONObject json = new JSONObject();
		 json.put("success", null == messages ? true : false);
		 json.put("messages", messages);
		 response.setStatus(200);
		 response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}



	@InitBinder
    protected void initBinder(HttpServletRequest request,
    	    ServletRequestDataBinder binder) throws Exception {
    	    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    	    CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
    	    binder.registerCustomEditor(Date.class, dateEditor);
    }

	/**
	* @Title: deleteGift
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteGift")
	public String deleteGift(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = giftService.deleteGift(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

}
