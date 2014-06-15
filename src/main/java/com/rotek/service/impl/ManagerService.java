/**
 * @FileName: ManagerService.java
 * @Package com.rotek.service.impl
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-6-22 下午05:28:36
 * @version V1.0
 */
package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.ManagerDao;
import com.rotek.dto.ManagerDto;
import com.rotek.entity.ManagerEntity;

/**
 * @ClassName: ManagerService
 * @Description: 后台管理者
 * @author chenwenpeng
 * @date 2013-6-22 下午05:28:36
 * 
 */
@Service
public class ManagerService {

	@Autowired
	private ManagerDao managerDao;

	/**
	 * @throws SQLException
	 * @Title: listManagers
	 * @Description:
	 * @param @param manager
	 * @param @param pager
	 * @param @return
	 * @return List<ManagerEntity>
	 * @throws
	 */
	public List<ManagerDto> listManagers(ManagerEntity manager, ListPager pager)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select rm.id,rm.r_role_id,rm.r_customer_id,rm.name,rm.password,rm.email,rm.telephone,rm.realname,rm.companyname,rm.status,rr.name rolename from r_manager rm,r_role rr where rm.r_role_id = rr.id ");
		List<Object> params = new LinkedList<Object>();
		if (null != manager.getId()) {
			sql.append(" and rm.id = ?");
			params.add(manager.getId());
		}
		if (StringUtils.isNotEmpty(manager.getName())) {
			sql.append(" and rm.name like '%" + manager.getName() + "%'");
		}

		if (StringUtils.isNotEmpty(manager.getRealname())) {
			sql.append(" and rm.realname like '%" + manager.getRealname() + "%'");
		}

		if (StringUtils.isNotEmpty(manager.getTelephone())) {
			sql.append(" and rm.telephone like '%" + manager.getTelephone() + "%'");
		}
		if (null != manager.getStatus()) {
			sql.append(" and rm.status = ?");
			params.add(manager.getStatus());
		}

		sql.append(" order by rm.status,rm.id desc");
		return managerDao.listManagers(sql.toString(), params.toArray(), pager);
	}

	/**
	 * @param dep_id
	 * @param role_id
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Title: addManager
	 * @Description: 添加管理员信息
	 * @param @param manager
	 * @param @return
	 * @return List<String>
	 * @throws
	 */
	public List<String> addManager(ManagerEntity manager)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(manager);
		if (messages.size() > 0) {
			return messages;
		}
		// 添加用户
		managerDao.addManager(manager);
		return null;
	}

	/**
	 * @throws SQLException
	 * @Title: getManagerDetail
	 * @Description:
	 * @param @param id
	 * @param @return
	 * @return ManagerEntity
	 * @throws
	 */
	public ManagerDto getManagerDetail(Integer id) throws SQLException {

		if (null == id) {
			return null;
		}
		return managerDao.getManagerDetail(id);
	}

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Title: modifyManager
	 * @Description: 修改
	 * @param @param manager
	 * @param @return
	 * @return List<String>
	 * @throws
	 */
	public List<String> modifyManager(ManagerEntity manager, Integer role_id,
			Integer dep_id) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(manager);
		if (messages.size() > 0 || null == manager.getId() || null == role_id
				|| null == dep_id) {
			return messages;
		}
		managerDao.modifyManager(manager);
		// 清除用户对应的角色信息
		managerDao.clearManager_role(manager.getId());
		managerDao.addManager_role(manager.getId(), role_id);
		// 清除用户对应的部门信息
		managerDao.clearManagerDepartment(manager.getId());
		managerDao.addManager_dep(manager.getId(), dep_id);

		return null;
	}

	/**
	 * @throws SQLException
	 * @Title: managerService
	 * @Description: 删除
	 * @param @param ids
	 * @param @return
	 * @return List<String>
	 * @throws
	 */
	public List<String> deleteManager(String ids) throws SQLException {
		List<String> messages = null;
		if (StringUtils.isBlank(ids)) {
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_manager set status = ").append(
				DataStatus.DISABLED);
		sql.append(" where id in (" + ids.trim() + ")");
		managerDao.deleteManager(sql.toString());
		return messages;
	}
}
