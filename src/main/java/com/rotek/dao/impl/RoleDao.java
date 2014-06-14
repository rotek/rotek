/**
 * @FileName: RoleDao.java
 * @Package com.rotek.dao.impl
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-6-3 上午09:01:04
 * @version V1.0
 */
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.entity.ButtonEntity;
import com.rotek.entity.MenuEntity;
import com.rotek.entity.RoleEntity;
import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;

/**
 * @ClassName: RoleDao
 * @Description: 角色
 * @author chenwenpeng
 * @date 2013-6-3 上午09:01:04
 * 
 */
@Repository
public class RoleDao extends BaseDaoImpl {

	/**
	 * @throws SQLException
	 * @Title: listRoles
	 * @Description:
	 * @param @param sql
	 * @param @return
	 * @return List<RoleEntity>
	 * @throws
	 */
	public List<RoleEntity> listRoles(String sql, Object[] params,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, params, RoleEntity.class, pager);
	}

	/**
	 * @Title: addRole
	 * @Description:
	 * @param @param roleEntity
	 * @return void
	 * @throws
	 */
	public void addRole(RoleEntity roleEntity) throws SQLException {
		this.insert(roleEntity);
	}

	/**
	 * @throws SQLException
	 * @Title: getRoleDetail
	 * @Description: 获取角色的所有信息
	 * @param @param id
	 * @param @return
	 * @return RoleEntity
	 * @throws
	 */
	public RoleEntity getRoleDetail_all(Integer id) throws SQLException {
		String sql = "select id, name, status from r_role where id = ?";

		return this.selectOne(sql, new Object[] { id }, RoleEntity.class);
	}

	/**
	 * @throws SQLException
	 * @Title: getRoleDetail
	 * @Description:获取角色的部分信息
	 * @param @param id
	 * @param @return
	 * @return RoleEntity
	 * @throws
	 */
	public RoleEntity getRoleDetai(Integer id) throws SQLException {
		String sql = "select distinct id, name,status from r_role where id = ?";

		return this.selectOne(sql, new Object[] { id }, RoleEntity.class);
	}

	/**
	 * @throws SQLException
	 * @Title: listAuthority_menu
	 * @Description:
	 * @param @param nodeId
	 * @param @return
	 * @return List<MenuEntity>
	 * @throws
	 */
	public List<MenuEntity> listAuthority_menu(Integer nodeId)
			throws SQLException {

		String sql = "select id,name from r_menu where super_menu_id = ?";
		return this.selectAll(sql, new Object[] { nodeId }, MenuEntity.class);
	}

	/**
	 * @Title: listAuthority_button
	 * @Description: 列出菜单对应的按钮
	 * @param @param nodeId
	 * @param @return
	 * @return List<ButtonEntity>
	 * @throws
	 */
	public List<ButtonEntity> listAuthority_button(Integer nodeId)
			throws SQLException {
		String sql = "select id,name from r_button where id in (select button_id from r_menu_button where menu_id = ?)";
		return this.selectAll(sql, new Object[] { nodeId }, ButtonEntity.class);
	}

	/**
	 * @Title: testButtonAuthority
	 * @Description: 验证用户是否具有这个按钮权限
	 * @param @param roleId
	 * @param @param nodeId
	 * @param @param id
	 * @param @return
	 * @return Boolean
	 * @throws
	 */
	public Boolean testButtonAuthority(Integer roleId, Integer menuId,
			Integer buttonId) throws SQLException {
		String sql = "select id from r_role_authority where role_id = ? and menu_id = ? and button_id = ?";
		return this
				.executeQuery(sql, new Object[] { roleId, menuId, buttonId })
				.size() > 0 ? true : false;
	}

	/**
	 * @throws SQLException
	 * @Title: testMenuAuthority
	 * @Description: 验证用户是否具有这个菜单权限
	 * @param @param roleId
	 * @param @param id
	 * @param @return
	 * @return Boolean
	 * @throws
	 */
	public Boolean testMenuAuthority(Integer roleId, Integer menuId)
			throws SQLException {
		String sql = "select id from r_role_authority where role_id = ? and menu_id = ?";
		return this.executeQuery(sql, new Object[] { roleId, menuId }).size() > 0 ? true
				: false;
	}

	/**
	 * @throws SQLException
	 * @Title: modifyRole
	 * @Description: 修改角色
	 * @param @param roleEntity
	 * @return void
	 * @throws
	 */
	public void modifyRole(RoleEntity roleEntity) throws SQLException {
		this.update(roleEntity);
	}

	/**
	 * @throws SQLException
	 * @Title: clearRoleAuthority
	 * @Description: 清除角色已经具有的权限
	 * @param @param id
	 * @return void
	 * @throws
	 */
	public void clearRoleAuthority(Integer id) throws SQLException {

		String sql = "delete from r_role_authority where role_id = ?";
		this.executeUpdate(sql, new Integer[] { id });
	}

	/**
	 * @throws SQLException
	 * @Title: addAuthority
	 * @Description:
	 * @param @param roleId
	 * @param @param menuId
	 * @param @param buttonId
	 * @return void
	 * @throws
	 */
	public void addAuthority(Integer roleId, Integer menuId, Integer buttonId)
			throws SQLException {

		String sql = "insert into r_role_authority values(null,?,?,?)";

		this.executeUpdate(sql, new Integer[] { roleId, menuId, buttonId });
	}

	/**
	 * @throws SQLException
	 * @Title: deleteRole
	 * @Description:
	 * @param @param sql
	 * @param @param array
	 * @return void
	 * @throws
	 */
	public void deleteRole(String sql) throws SQLException {

		this.executeUpdate(sql);
	}

	/**
	 * @throws SQLException
	 * @Title: listRoles_combo
	 * @Description:
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @throws
	 */
	public List<Map<String, Object>> listRoles_combo() throws SQLException {
		String sql = "select id,name from r_role where status = 1";
		return this.executeQuery(sql, null);
	}

	/**
	 * @throws SQLException
	 * @Title: clearRoleDepartment
	 * @Description:
	 * @param id
	 * @return void
	 * @throws
	 */
	public void clearRoleDepartment(Integer id) throws SQLException {

		String sql = "delete from r_role_dep where role_id = ?";
		this.executeUpdate(sql, new Integer[] { id });
	}

	/**
	 * @throws SQLException
	 * @Title: updateRoleDepartment
	 * @Description:
	 * @param id
	 * @param dep_id
	 * @return void
	 * @throws
	 */
	public void updateRoleDepartment(Integer role_id, Integer dep_id)
			throws SQLException {

		String sql = "insert into r_role_dep values(null,?,?)";
		this.executeUpdate(sql, new Integer[] { role_id, dep_id });
	}

	/**
	 * 如果库中存在这个role 的这个权限菜单的 上级菜单id，信息，那么就返回0，否则返回这个menu的上级菜单id
	 * 
	 * @param roleId
	 * @param menuId
	 * @return
	 * @throws SQLException
	 */
	public Integer getSuperMenuIdByRoleIdAndMenuId(Integer roleId,
			Integer menuId) throws SQLException {

		String sql = "select rm.super_menu_id from r_role_authority rra,r_menu rm where rra.menu_id = rm.super_menu_id and rm.id = ? and rra.role_id = ?";
		List<Integer> superMenuIdList = this.executeQueryForInt(sql,
				new Integer[] { menuId , roleId});

		return superMenuIdList.size() >0 ? 0 : superMenuIdList.get(0);
	}
}
