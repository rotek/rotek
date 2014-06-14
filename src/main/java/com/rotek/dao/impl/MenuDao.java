/**
* @FileName: MenuDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 下午01:25:04
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.dto.MenuDto;
import com.rotek.entity.MenuEntity;
import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;

/**
 * @ClassName: MenuDao
 * @Description: 系统菜单dao
 * @author chenwenpeng
 * @date 2013-6-26 下午01:25:04
 *
 */
@Repository
public class MenuDao extends BaseDaoImpl {

	/**
	 * @throws SQLException
	 * @param params
	* @Title: listMenus
	* @Description:
	* @param @param string
	* @param @param pager
	* @param @return
	* @return List<MenuEntity>
	* @throws
	*/
	public List<MenuEntity> listMenus(String sql, Object[] params, ListPager pager) throws SQLException {

		return this.selectPage(sql, params, MenuEntity.class,pager);
	}

	/**
	 * @throws SQLException
	* @Title: listMenus_super
	* @Description:
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listMenus_super(String super_menu_ids) throws SQLException {

		String sql = "select id,name from r_menu where id in (select id from r_menu where super_menu_id in("+super_menu_ids+"))  and status = 1 order by super_menu_id,status,sort desc";
		return this.executeQuery(sql, null);
	}

	/**
	 * @throws SQLException
	* @Title: listMenus_sort
	* @Description:
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listMenus_sort() throws SQLException {

		String sql = "select (sort-1) sort,name from r_menu where status = 1 and id!=1 order by sort desc";
		return this.executeQuery(sql, null);
	}

	/**
	 * @throws SQLException
	* @Title: addMenu
	* @Description:
	* @param @param menu
	* @return void
	* @throws
	*/
	public void addMenu(MenuEntity menu) throws SQLException {
		this.insert(menu);
	}

	/**
	 * @throws SQLException
	* @Title: getMenuDetail
	* @Description:
	* @param @param id
	* @param @return
	* @return MenuEntity
	* @throws
	*/
	public MenuDto getMenuDetail(Integer id) throws SQLException {
		String sql = "select id, name, super_menu_id, url, sort, status from r_menu where id = ?";
		return this.selectOne(sql, new Integer []{id},MenuDto.class);
	}

	/**
	 * @throws SQLException
	* @Title: getMenuButonList
	* @Description:
	* @param @param id
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> getMenuButonList(Integer id) throws SQLException {

		String sql = "select id,name from r_button  where id in (select button_id from r_menu_button where menu_id = ?)";
		return this.executeQuery(sql, new Integer[]{id});
	}

	/**
	* @Title: modifyMenu
	* @Description:
	* @param @param menu
	* @return void
	* @throws
	*/
	public void modifyMenu(MenuEntity menu) throws SQLException {

		this.update(menu);
	}

	/**
	 * @throws SQLException
	* @Title: clearMenu_button
	* @Description:
	* @param @param id
	* @return void
	* @throws
	*/
	public void clearMenu_button(Integer id) throws SQLException {

		String sql = "delete from r_menu_button where menu_id = ?";
		this.executeUpdate(sql, new Integer[]{id});
	}

	/**
	 * @throws SQLException
	* @Title: addMenu_button
	* @Description:
	* @param @param menuId
	* @param @param buttonId
	* @return void
	* @throws
	*/
	public void addMenu_button(Integer menuId, Integer buttonId) throws SQLException {

		String sql = "insert into r_menu_button values(null,?,?)";
		this.executeUpdate(sql,new Integer[]{menuId,buttonId});
	}

	/**
	 * @throws SQLException
	* @Title: deleteMenu
	* @Description:
	* @param @param string
	* @return void
	* @throws
	*/
	public void deleteMenu(String sql) throws SQLException {
		this.executeUpdate(sql);
	}
}
