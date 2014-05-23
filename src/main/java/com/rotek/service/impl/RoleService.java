/**
* @FileName: RoleService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-3 上午08:59:55
* @version V1.0
*/
package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.RoleDao;
import com.rotek.entity.ButtonEntity;
import com.rotek.entity.MenuEntity;
import com.rotek.entity.NodeEntity;
import com.rotek.entity.RoleEntity;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;

/**
 * @ClassName: RoleService
 * @Description: 角色的业务
 * @author chenwenpeng
 * @date 2013-6-3 上午08:59:55
 *
 */
@Service
public class RoleService {
	@Autowired
	private RoleDao roleDao;

	/**
	 * @param pager
	 * @param role
	* @Title: listRoles
	* @Description: 列出所有角色信息
	* @param @return
	* @return List<UserDto>
	* @throws
	*/
	public List<RoleEntity> listRoles(RoleEntity role, ListPager pager) throws SQLException{
		StringBuilder sql = new StringBuilder();

		sql.append("select id,role_name,memo,status from mf_role where 1 = 1 and super_role_id = ?");
		List<Object> params = new LinkedList<Object>();
		params.add(role.getSuper_role_id());
		if(null != role.getId()){
			sql.append(" and id = ?");
			params.add(role.getId());
		}
		if(StringUtils.isNotEmpty(role.getRole_name())){
			sql.append(" and role_name like '%"+role.getRole_name().trim()+"%'");
		}
		if(StringUtils.isNotEmpty(role.getMemo())){
			sql.append(" and memo like '%"+role.getMemo().trim()+"%'");
		}
		if(null != role.getStatus()){
			sql.append(" and status = ?");
			params.add(role.getStatus());
		}

		sql.append(" order by status,id desc");
		List<RoleEntity> roles = roleDao.listRoles(sql.toString(),params.toArray(),pager);
		return roles;
	}

	/**
	 * @return
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addRole
	* @Description:添加一个角色信息
	* @param @param roleEntity
	* @return void
	* @throws
	*/
	public List<String> addRole(RoleEntity roleEntity) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<String> messages = ValidateUtil.validate(roleEntity);
		if(messages.size()>0){
			return messages;
		}
		roleDao.addRole(roleEntity);
		return null;
	}

	/**
	* @Title: getRoleDetail
	* @Description: 获取一个角色的详情
	* @param @param id
	* @param @return
	* @return RoleEntity
	* @throws
	*/
	public RoleEntity getRoleDetail(Integer id) throws SQLException{

		RoleEntity role = roleDao.getRoleDetail_all(id);
		if(null == role){
			role = roleDao.getRoleDetai(id);
		}
		return role;
	}

	/**
	 * @throws SQLException
	* @Title: listAuthority
	* @Description:
	* @param @param roleId
	* @param @param node
	* @param @return
	* @return List<NodeEntity>
	* @throws
	*/
	public List<NodeEntity> listAuthority(Integer roleId, String nodeId_str) throws SQLException {
		if(null == roleId || StringUtils.isBlank(nodeId_str) || nodeId_str.length()<=5){
			return new LinkedList<NodeEntity>();
		}
		Integer nodeId = Integer.parseInt(nodeId_str.substring(5,nodeId_str.length()));
		List<NodeEntity> nodeList = new ArrayList<NodeEntity>();
		//根据上级菜单的id 获取下级菜单的列表
		List<MenuEntity> menuList = roleDao.listAuthority_menu(nodeId);
		//如果没有下级元素了，那么就是最后一级菜单，去查找所有的按钮信息
		if(menuList.size() <=0){
			List<ButtonEntity> buttonList = roleDao.listAuthority_button(nodeId);
			for(ButtonEntity button : buttonList){
				NodeEntity node = new NodeEntity();
				node.setId("node_"+button.getId()+"_"+Math.random());
				node.setText(button.getButton_name());
				node.setLeaf(true);
				node.setChecked(roleDao.testButtonAuthority(roleId,nodeId,button.getId()));
				nodeList.add(node);
			}
			return nodeList;
		}
		//如果有下级菜单那么就返回以后继续查找
		for(MenuEntity menu : menuList){
			NodeEntity node = new NodeEntity();
			node.setId("menu_"+menu.getId());
			node.setText(menu.getMenu_name());
			node.setLeaf(false);
			node.setChecked(roleDao.testMenuAuthority(roleId,menu.getId()));
			nodeList.add(node);
		}

		return nodeList;
	}

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyRole
	* @Description: 修改角色信息
	* @param @param roleEntity
	* @param @param authority
	* @param @return
	* @return String
	* @throws
	*/
	public List<String> modifyRole(RoleEntity roleEntity, String authority) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(roleEntity);
		if(messages.size()>0 || null == roleEntity.getId()){
			return messages;
		}
		//修改角色基本信息
		roleDao.modifyRole(roleEntity);

		//修改权限信息
		Integer roleId = roleEntity.getId();
		roleDao.clearRoleAuthority(roleId);

		if(StringUtils.isNotBlank(authority)){
			String[] items = authority.split(",");
			for(String item : items){
				Integer menuId = Integer.parseInt(item.split(";")[0].split("_")[1].trim());
				Integer buttonId = Integer.parseInt(item.split(";")[1].split("_")[1].trim());
				roleDao.addAuthority(roleId,menuId,buttonId);
			}
		}
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: deleteRole
	* @Description: 删除一个或多个角色
	* @param @param ids
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteRole(String id_str) throws SQLException {
		List<String> messages = null;
		if(StringUtils.isBlank(id_str)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_role set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+id_str.trim()+")");
		roleDao.deleteRole(sql.toString());
		return messages;
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
			return roleDao.listRoles_combo();
	}
}
