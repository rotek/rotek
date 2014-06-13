/**
* @FileName: AuthorityDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-3 上午09:14:02
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rotek.entity.ButtonEntity;
import com.rotek.entity.MenuEntity;
import com.cta.platform.persistence.dao.BaseDaoImpl;

/**
 * @ClassName: AuthorityDao
 * @Description: 权限
 * @author chenwenpeng
 * @date 2013-6-3 上午09:14:02
 *
 */
@Repository
public class AuthorityDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: getMenu
	* @Description:获取menuid
	* @param @param url_inDB
	* @param @return
	* @return MenuEntity
	* @throws
	*/
	public MenuEntity getMenu(String url_inDB) throws SQLException {
		String sql = "select id from r_menu where url=?";
		return this.selectOne(sql, new Object[]{url_inDB}, MenuEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: getListButton
	* @Description: 获取权限信息
	* @param @param role_id
	* @param @param menuId
	* @param @return
	* @return List<ButtonEntity>
	* @throws
	*/
	public List<ButtonEntity> getListButton(Integer role_id, Integer menuId) throws SQLException {

		String sql = "select name,action from r_button where id in (select button_id from r_role_authority where role_id = ? and menu_id = ?)";
		return this.selectAll(sql, new Object[]{role_id,menuId}, ButtonEntity.class);
	}
}
