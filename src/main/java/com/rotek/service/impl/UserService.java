/**
* @FileName: UserService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-8 上午11:25:27
* @version V1.0
*/
package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.UserDao;
import com.rotek.entity.RegistUserDetail;
import com.rotek.entity.RegistUserEntity;
import com.rotek.platform.util.ListPager;
import com.rotek.platform.util.ValidateUtil;

/**
 * @ClassName: UserService
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-8 上午11:25:27
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;

	/**
	 * @param end_time
	 * @param start_time
	 * @throws SQLException
	* @Title: listUsers
	* @Description:
	* @param registUser
	* @param pager
	* @return
	* @return List<RegistUserEntity>
	* @throws
	*/
	public List<RegistUserEntity> listUsers(RegistUserEntity registUser,
			ListPager pager, Date start_time, Date end_time) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select id,nick_name,telephone,order_count,gold,reg_time,enabled from mf_user where 1=1");

		if(null != registUser.getId()){
			sql.append(" and id = ?");
			params.add(registUser.getId());
		}
		if(null != registUser.getEnabled()){
			sql.append(" and enabled = ?");
			params.add(registUser.getEnabled());
		}

		if(StringUtils.isNotEmpty(registUser.getNick_name())){
			sql.append(" and nick_name like '%").append(registUser.getNick_name()).append("%'");
		}

		if(StringUtils.isNotEmpty(registUser.getTelephone())){
			sql.append(" and telephone like '%").append(registUser.getTelephone()).append("%'");
		}

		if(null != registUser.getGold()){
			sql.append(" and gold >= ?");
			params.add(registUser.getGold());
		}
		if(null != start_time){
			sql.append(" and reg_time >= ?");
			params.add(start_time);
		}
		if(null != end_time){
			sql.append(" and reg_time <= ?");
			params.add(end_time);
		}

		sql.append(" order by id desc");
		return userDao.listUsers(sql.toString(),params.toArray(),pager);
	}

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addUser
	* @Description:
	* @param user
	* @param user_detail
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> addUser(RegistUserEntity user,
			RegistUserDetail user_detail) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {

		List<String> messages = ValidateUtil.validate(user);
		if(messages.size()>0){
			return messages;
		}
		Integer user_id = userDao.addUser(user);
		user_detail.setUser_id(user_id);
		messages = ValidateUtil.validate(user_detail);
		if(messages.size()>0){
			return messages;
		}

		userDao.addUserDetail(user_detail);
		return null;
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

		if(null == id){
			return null;
		}
		return userDao.getUser(id);
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
	public RegistUserDetail getUserDetail(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return userDao.getUserDetail(id);
	}

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyUser
	* @Description:
	* @param user
	* @param user_detail
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyUser(RegistUserEntity user,
			RegistUserDetail user_detail) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {

		List<String> messages = ValidateUtil.validate(user);
		if(messages.size()>0){
			return messages;
		}
		userDao.modifyUser(user);

		messages = ValidateUtil.validate(user_detail);
		if(messages.size()>0){
			return messages;
		}
		userDao.modifyUserDetail(user_detail);
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: deleteUser
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteUser(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_user set enabled = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		userDao.deleteUser(sql.toString());
		return messages;
	}
}
