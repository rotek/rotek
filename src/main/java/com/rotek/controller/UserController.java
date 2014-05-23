/**
* @FileName: UserController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-8 上午11:24:08
* @version V1.0
*/
package com.rotek.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.constant.DataStatus;
import com.rotek.dto.UserDto;
import com.rotek.entity.RegistUserDetail;
import com.rotek.entity.RegistUserEntity;
import com.cta.platform.util.ListPager;
import com.rotek.service.impl.UserService;
import com.rotek.util.DateUtils;

/**
 * @ClassName: UserController
 * @Description: 注册用户
 * @author chenwenpeng
 * @date 2013-8-8 上午11:24:08
 *
 */

@Controller
@RequestMapping(value="/admin/user")
public class UserController {

	@Autowired
	private UserService userService;

	/**
	 * 转到查看所有注册用户的界面
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("toUsers")
	public String toUsers() {

		return "admin/user/users";
	}


	/**
	 * 列出所有的用户信息
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("listUsers")
	public String listRoles(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,

			@RequestParam(value="id", defaultValue = "") Integer id,
			@RequestParam(value="nick_name", defaultValue = "") String nick_name,
			@RequestParam(value="telephone", defaultValue = "") String telephone,
			@RequestParam(value="enabled", defaultValue = "") Integer enabled,

			@RequestParam(value = "start_time", defaultValue = "") Date start_time,
			@RequestParam(value = "end_time", defaultValue = "") Date end_time,
			HttpServletRequest request,
			UserDto user,
			ModelMap modelMap) throws Exception {
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		RegistUserEntity registUser = new RegistUserEntity();
		registUser.setId(id);
		registUser.setNick_name(nick_name);
		registUser.setEnabled(enabled);

		List<RegistUserEntity> registUserList = userService.listUsers(registUser,pager,start_time,end_time);
		modelMap.put("dataList", registUserList);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}


	/**
	* @Title: addUser
	* @Description: 添加用户
	* @param nick_name
	* @param password
	* @param telephone
	* @param gold
	* @param enabled
	* @param real_name
	* @param qq
	* @param email
	* @param gender
	* @param model
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	@RequestMapping("addUser")
	public String addUser(
			@RequestParam(value="nick_name", defaultValue="") String nick_name,
			@RequestParam(value="password", defaultValue="") String password,
			@RequestParam(value="telephone", defaultValue="") String telephone,
			@RequestParam(value="gold", defaultValue="") Integer gold,
			@RequestParam(value="enabled", defaultValue="") Integer enabled,
			@RequestParam(value="real_name", defaultValue="") String real_name,
			@RequestParam(value="qq", defaultValue="") String qq,
			@RequestParam(value="email", defaultValue="") String email,
			@RequestParam(value="gender", defaultValue="") Integer gender,
			ModelMap model) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		RegistUserEntity user = new RegistUserEntity();
		user.setNick_name(nick_name);
		user.setPassword(password);
		user.setTelephone(telephone);
		user.setGold(gold);
		user.setEnabled(enabled);
		user.setReal_name(StringUtils.isBlank(real_name) ? DataStatus.NONE_STRING : real_name);
		user.setUn_comment(DataStatus.NONE_INT);
		user.setOrder_count(DataStatus.NONE_INT);
		user.setReg_time(new Date());

		RegistUserDetail user_detail = new RegistUserDetail();
		user_detail.setGender(null == gender? DataStatus.NONE_INT : gender);
		user_detail.setEmail(StringUtils.isBlank(email) ? DataStatus.NONE_STRING : email);
		user_detail.setIcon(DataStatus.NONE_STRING);
		user_detail.setQq(StringUtils.isBlank(qq) ? DataStatus.NONE_STRING : qq);


		List<String> messages = userService.addUser(user,user_detail);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: getUserDetail
	* @Description:
	* @param id
	* @param modelMap
	* @return
	* @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("getUserDetail")
	public String getUserDetail(
			@RequestParam(value="id", defaultValue = "") Integer id,
			ModelMap modelMap) throws Exception {

		RegistUserEntity user = userService.getUser(id);
		RegistUserDetail user_detail = userService.getUserDetail(id);

		if(null != user && null != user.getReg_time()){
			modelMap.put("reg_time", DateUtils.formatDate(user.getReg_time()));
		}
		modelMap.put("user", user);
		modelMap.put("user_detail", user_detail);
		return "jsonView";
	}

	/**
	* @Title: modifyUser
	* @Description:
	* @param id 修改用户信息
	* @param nick_name
	* @param password
	* @param telephone
	* @param gold
	* @param enabled
	* @param real_name
	* @param qq
	* @param email
	* @param gender
	* @param model
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	@RequestMapping("modifyUser")
	public String modifyUser(
			@RequestParam(value="id", defaultValue="") Integer id,
			@RequestParam(value="detail_id", defaultValue="") Integer detail_id,
			@RequestParam(value="nick_name", defaultValue="") String nick_name,
			@RequestParam(value="password", defaultValue="") String password,
			@RequestParam(value="telephone", defaultValue="") String telephone,
			@RequestParam(value="gold", defaultValue="") Integer gold,
			@RequestParam(value="enabled", defaultValue="") Integer enabled,
			@RequestParam(value="real_name", defaultValue="") String real_name,
			@RequestParam(value="qq", defaultValue="") String qq,
			@RequestParam(value="email", defaultValue="") String email,
			@RequestParam(value="gender", defaultValue="") Integer gender,
			@RequestParam(value="un_comment", defaultValue="") Integer un_comment,
			@RequestParam(value="order_count", defaultValue="") Integer order_count,
			@RequestParam(value="reg_time", defaultValue="") Date reg_time,
			ModelMap model) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		RegistUserEntity user = new RegistUserEntity();
		user.setId(id);
		user.setNick_name(nick_name);
		user.setPassword(password);
		user.setTelephone(telephone);
		user.setGold(gold);
		user.setEnabled(enabled);
		user.setReal_name(StringUtils.isBlank(real_name) ? DataStatus.NONE_STRING : real_name);
		user.setUn_comment(un_comment);
		user.setOrder_count(DataStatus.NONE_INT);
		user.setReg_time(reg_time);

		RegistUserDetail user_detail = new RegistUserDetail();
		user_detail.setId(detail_id);
		user_detail.setUser_id(id);
		user_detail.setGender(null == gender? DataStatus.NONE_INT : gender);
		user_detail.setEmail(email);
		user_detail.setIcon(DataStatus.NONE_STRING);
		user_detail.setQq(qq);


		List<String> messages = userService.modifyUser(user,user_detail);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: deleteUser
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteUser")
	public String deleteUser(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = userService.deleteUser(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	@InitBinder
    protected void initBinder(HttpServletRequest request,
    	    ServletRequestDataBinder binder) throws Exception {
    	    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    	    CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
    	    binder.registerCustomEditor(Date.class, dateEditor);
    }
}
