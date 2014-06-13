/**
 * @FileName: IndexDao.java
 * @Package com.rotek.dao.impl
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-31 上午10:35:24
 * @version V1.0
 */
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.dto.MenuDto;
import com.rotek.entity.MenuEntity;
import com.cta.platform.config.SystemGlobals;
import com.cta.platform.persistence.dao.BaseDaoImpl;

/**
 * @ClassName: IndexDao
 * @Description:首页的dao层
 * @author chenwenpeng
 * @date 2013-5-31 上午10:35:24
 * 
 */
@Repository
public class IndexDao extends BaseDaoImpl {

	/**
	 * @throws SQLException
	 * @Title: listSuperMenu
	 * @Description:
	 * @param @param role_id
	 * @param @return
	 * @return List<MenuEntity>
	 * @throws
	 */
	public List<MenuDto> listSuperMenu(Integer role_id) throws SQLException {
		String sql = "select id,name from r_menu where id in (select distinct super_menu_id from r_menu where id in (select distinct menu_id from r_role_authority where role_id = ?)) and status = ? order by sort desc";
		return this.selectAll(sql, new Object[] { role_id,
				MenuEntity.STATUS_ENABLED }, MenuDto.class);
	}

	/**
	 * @Title: listChildMenu
	 * @Description:
	 * @param @param role_id
	 * @param @return
	 * @return List<MenuEntity>
	 * @throws
	 */
	public List<MenuEntity> listChildMenu(Integer role_id) throws SQLException {

		String sql = "select id,name,super_menu_id,url from r_menu where id in (select distinct menu_id from r_role_authority where role_id = ?) and status = ? order by sort desc";
		return this.selectAll(sql, new Object[] { role_id,
				MenuEntity.STATUS_ENABLED }, MenuEntity.class);
	}

	/**
	 * @throws SQLException
	 * @param date
	 * @Title: getOrderCount
	 * @Description:
	 * @param dep_id
	 * @return
	 * @return Map<String,Object>
	 * @throws
	 */
	public Map<String, Object> getOrderCount(Integer dep_id, Date date)
			throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) count from mf_order where rest_id in");
		sql.append(" (select rest_id from mf_dep_restaurant where dep_id in (select id from mf_department where id = ? or super_dep_id = ?))");
		sql.append(" and ordered_time > ?");

		return this.executeQueryOne(sql.toString(), new Object[] { dep_id,
				dep_id, date });
	}

	/**
	 * @throws SQLException
	 * @Title: getGiftCount
	 * @Description:
	 * @param dateForQuery
	 * @return
	 * @return Map<String,Object>
	 * @throws
	 */
	public Map<String, Object> getGiftCount(Date date) throws SQLException {

		String sql = "select count(1) count from mf_gift_user where time_exchange > ?";
		return this.executeQueryOne(sql, new Date[] { date });
	}
}
