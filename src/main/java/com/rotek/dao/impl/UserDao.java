/**
* @FileName: UserDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-8 上午11:26:01
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rotek.entity.RegistUserDetail;
import com.rotek.entity.RegistUserEntity;
import com.rotek.platform.persistence.dao.BaseDaoImpl;
import com.rotek.platform.util.ListPager;

/**
 * @ClassName: UserDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-8 上午11:26:01
 *
 */
@Repository
public class UserDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listUsers
	* @Description:
	* @param string
	* @param array
	* @param pager
	* @return
	* @return List<RegistUserEntity>
	* @throws
	*/
	public List<RegistUserEntity> listUsers(String sql, Object[] params,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, params, RegistUserEntity.class, pager);
	}

	/**
	 * @throws SQLException
	* @Title: addUser
	* @Description:
	* @param user
	* @return
	* @return Integer
	* @throws
	*/
	public Integer addUser(RegistUserEntity user) throws SQLException {

		return this.insert_pk(user);
	}

	/**
	 * @throws SQLException
	* @Title: addUserDetail
	* @Description:
	* @param user_detail
	* @return void
	* @throws
	*/
	public void addUserDetail(RegistUserDetail user_detail) throws SQLException {

		this.insert(user_detail);
	}

	/**
	 * @throws SQLException
	* @Title: getUser
	* @Description:
	* @param id
	* @return
	* @return RegistUserEntity
	* @throws
	*/
	public RegistUserEntity getUser(Integer id) throws SQLException {

		String sql = "select id, nick_name, real_name, password, telephone, order_count, un_comment, gold, reg_time, enabled from mf_user where id = ?";
		return this.selectOne(sql, new Integer[]{id},RegistUserEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: getUserDetail
	* @Description:
	* @param id
	* @return
	* @return RegistUserDetail
	* @throws
	*/
	public RegistUserDetail getUserDetail(Integer user_id) throws SQLException {

		String sql = "select id, user_id, gender, icon, email, qq from mf_user_detail where user_id = ?";
		return this.selectOne(sql, new Integer[]{user_id},RegistUserDetail.class);
	}

	/**
	 * @throws SQLException
	* @Title: modifyUser
	* @Description:
	* @param user
	* @return void
	* @throws
	*/
	public void modifyUser(RegistUserEntity user) throws SQLException {

		this.update(user);
	}

	/**
	 * @throws SQLException
	* @Title: modifyUserDetail
	* @Description:
	* @param user_detail
	* @return void
	* @throws
	*/
	public void modifyUserDetail(RegistUserDetail user_detail) throws SQLException {

		this.update(user_detail);
	}

	/**
	 * @throws SQLException
	* @Title: deleteUser
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteUser(String sql) throws SQLException {

		this.executeUpdate(sql);
	}
}
