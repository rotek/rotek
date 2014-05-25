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

import com.rotek.dto.UserDto;
import com.cta.platform.persistence.dao.BaseDaoImpl;

/**
 * @ClassName: LoginDao
 * @Description: 登录
 * @author chenwenpeng
 * @date 2013-5-31 下午01:23:39
 *
 */
@Repository
public class LoginDao extends BaseDaoImpl{

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
	public UserDto getUser(String username, String password) throws SQLException {

		String sql = "select r.role_id,m.id,m.name,m.real_name,m.status,d.dep_id from mf_manager m inner join mf_manager_role r on m.id = r.manager_id inner join mf_manager_dep d on m.id=d.manager_id where name = ? and password = ? limit 1";
		return this.selectOne(sql, new Object[]{username,password}, UserDto.class);
	}

}
