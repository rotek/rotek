/**
 * @FileName: ManagerDao.java
 * @Package com.rotek.dao.impl
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-6-22 下午06:01:16
 * @version V1.0
 */
package com.rotek.dao.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.dto.ManagerDto;
import com.rotek.entity.ManagerEntity;

/**
 * @ClassName: ManagerDao
 * @Description: 管理者dao
 * @author chenwenpeng
 * @date 2013-6-22 下午06:01:16
 * 
 */
@Repository
public class ManagerDao extends BaseDaoImpl {

	/**
	 * @throws SQLException
	 * @return
	 * @Title: listManagers
	 * @Description:
	 * @param @param string
	 * @param @param array
	 * @param @param pager
	 * @return void
	 * @throws
	 */
	public List<ManagerDto> listManagers(String sql, Object[] parameters,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, parameters, ManagerDto.class, pager);
	}

	/**
	 * @return
	 * @throws SQLException
	 * @Title: addManager
	 * @Description:
	 * @param @param manager
	 * @return void
	 * @throws
	 */
	public Integer addManager(ManagerEntity manager) throws SQLException {

		return this.insert_pk(manager);
	}

	/**
	 * @throws SQLException
	 * @Title: getManagerDetail
	 * @Description: 获取管理员详情
	 * @param @param id
	 * @param @return
	 * @return ManagerEntity
	 * @throws
	 */
	public ManagerDto getManagerDetail(Integer id) throws SQLException {

		String sql = "select m.id, m.name, m.real_name, m.password, m.phone, m.question_secu, m.answer, m.status,mr.role_id,md.dep_id from mf_manager m left join mf_manager_role mr on m.id = mr.manager_id left join mf_manager_dep md on m.id=md.manager_id where m.id = ?";
		ManagerDto manager = this.selectOne(sql, new Integer[] { id },
				ManagerDto.class);

		return manager;
	}

	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SQLException
	 * @Title: modifyManager
	 * @Description:
	 * @param @param manager
	 * @return void
	 * @throws
	 */
	public String modifyManager(ManagerEntity manager) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		
		List<String> msgList = ValidateUtil.validate(manager);
		if (msgList.size() > 0) {
			StringBuilder sb = new StringBuilder();
			for (String str : msgList) {
				sb.append(str).append(";");
			}
			return sb.toString();
		} else {

			this.update(manager);
		}
		return "success";
	}

	/**
	 * @throws SQLException
	 * @Title: deleteManager
	 * @Description:
	 * @param @param string
	 * @return void
	 * @throws
	 */
	public void deleteManager(String sql) throws SQLException {

		this.executeUpdate(sql);
	}

	/**
	 * @throws SQLException
	 * @Title: addManager_role
	 * @Description: 给用户添加角色权限
	 * @param @param id
	 * @param @param role_id
	 * @return void
	 * @throws
	 */
	public void addManager_role(Integer id, Integer role_id)
			throws SQLException {
		String sql = "insert into mf_manager_role values(null,?,?)";
		this.executeUpdate(sql, new Integer[] { id, role_id });
	}

	/**
	 * @throws SQLException
	 * @Title: clearManager_role
	 * @Description:
	 * @param @param id
	 * @return void
	 * @throws
	 */
	public void clearManager_role(Integer id) throws SQLException {
		String sql = "delete from mf_manager_role where manager_id = ?";
		this.executeUpdate(sql, new Integer[] { id });
	}

	/**
	 * @throws SQLException
	 * @param dep_id
	 * @Title: addManager_dep
	 * @Description:
	 * @param id
	 * @return void
	 * @throws
	 */
	public void addManager_dep(Integer id, Integer dep_id) throws SQLException {

		String sql = "insert into mf_manager_dep values(null,?,?)";
		this.executeUpdate(sql, new Integer[] { id, dep_id });
	}

	/**
	 * @throws SQLException
	 * @Title: clearRoleDepartment
	 * @Description:
	 * @param id
	 * @return void
	 * @throws
	 */
	public void clearManagerDepartment(Integer id) throws SQLException {

		String sql = "delete from mf_manager_dep where manager_id = ?";
		this.executeUpdate(sql, new Integer[] { id });
	}
}
