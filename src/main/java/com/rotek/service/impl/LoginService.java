/**
* @FileName: LoginService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-31 上午11:47:50
* @version V1.0
*/
package com.rotek.service.impl;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.constant.SessionParams;
import com.rotek.dao.impl.LoginDao;
import com.rotek.dto.UserDto;

/**
 * @ClassName: LoginService
 * @Description:登录的业务
 * @author chenwenpeng
 * @date 2013-5-31 上午11:47:50
 *
 */
@Service
public class LoginService {

	@Autowired
	private LoginDao loginDao;
	/**
	 * @throws SQLException
	* @Title: login
	* @Description:
	* @param @param username
	* @param @param password
	* @param @param request
	* @param @return
	* @return String
	* @throws
	*/
	public String login(String username, String password,
			HttpServletRequest request) throws SQLException {

		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			return "fail_blank";
		}
		UserDto user = loginDao.getUser(username,password);
		if(null == user){
			return "fail";
		}
		request.getSession().setAttribute(SessionParams.USER, user);
		return "success";
	}

}
