/**
* @FileName: RestMenuDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-19 下午05:05:59
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rotek.dto.RestMenuDto;
import com.rotek.entity.RestMenuEntity;
import com.rotek.platform.persistence.dao.BaseDaoImpl;
import com.rotek.platform.util.ListPager;

/**
 * @ClassName: RestMenuDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-19 下午05:05:59
 *
 */
@Repository
public class RestMenuDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listMenus
	* @Description:
	* @param string
	* @param array
	* @param pager
	* @return
	* @return Object
	* @throws
	*/
	public List<RestMenuDto> listMenus(String sql, Object[] parameters, ListPager pager) throws SQLException {

		return this.selectPage(sql, parameters, RestMenuDto.class,pager);
	}

	/**
	 * @throws SQLException
	* @Title: addMenu
	* @Description:
	* @param menu
	* @return
	* @return Integer
	* @throws
	*/
	public Integer addMenu(RestMenuEntity menu) throws SQLException {

		return this.insert_pk(menu);
	}

	/**
	 * @throws SQLException
	* @Title: updateMenuPic
	* @Description:
	* @param pic_name
	* @param menu_id
	* @return void
	* @throws
	*/
	public void updateMenuPic(String pic_name, Integer menu_id) throws SQLException {

		String sql = "update mf_menu_qd set pic = ? where id = ?";
		this.executeUpdate(sql, new Object[]{pic_name,menu_id});
	}

	/**
	 * @throws SQLException
	* @Title: getMenuDetail
	* @Description:
	* @param id
	* @return
	* @return RestMenuEntity
	* @throws
	*/
	public RestMenuEntity getMenuDetail(Integer id) throws SQLException {

		String sql = "select id, name, price, pic, mix, recommend, descr, cate_id, rest_id, status from mf_menu_qd where id = ?";
		return this.selectOne(sql, new Integer[]{id}, RestMenuEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: modifyMenu
	* @Description:
	* @param menu
	* @return void
	* @throws
	*/
	public void modifyMenu(RestMenuEntity menu) throws SQLException {

		this.update(menu);
	}

	/**
	 * @throws SQLException
	* @Title: deleteMenu
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteMenu(String sql) throws SQLException {

		this.executeUpdate(sql);
	}

	/**
	 * @throws SQLException
	* @Title: getMenuDetail_s
	* @Description:
	* @param id
	* @return
	* @return RestMenuEntity
	* @throws
	*/
	public RestMenuEntity getMenuDetail_s(Integer id) throws SQLException {
		String sql = "select id, name, price from mf_menu_qd where id = ?";
		return this.selectOne(sql, new Integer[]{id}, RestMenuEntity.class);
	}
}
