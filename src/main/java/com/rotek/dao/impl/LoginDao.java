/**
 * @FileName: LoginDao.java
 * @Package com.rotek.dao.impl
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-31 下午01:23:39
 * @version V1.0
 */
package com.rotek.dao.impl;

import java.sql.SQLException;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.rotek.dto.UserDto;

/**
 * @ClassName: LoginDao
 * @Description: 登录
 * @author chenwenpeng
 * @date 2013-5-31 下午01:23:39
 * 
 */
@Repository
public class LoginDao extends BaseDaoImpl {

	/**
	 * @throws SQLException
	 * @Title: getUser
	 * @Description:
	 * @param @param username
	 * @param @param password
	 * @param @return
	 * @return UserEntity
	 * @throws
	 */
	public UserDto getUser(String username, String password)
			throws SQLException {

		String sql = "select  id , r_role_id , r_costomer_id , name , password , email , telephone , realname , companyname , status from r_user where name = ? and password = ? and status = ?";
		return this.selectOne(sql, new Object[] { username, password,
				UserDto.STATUS_ENABLE }, UserDto.class);
	}

}
