/**
 * @FileName: IndexDao.java
 * @Package com.cta.dao
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-18 下午04:11:09
 * @version V1.0
 */
package com.cta.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.entity.BuildingEntity;
import com.cta.entity.OrderEntity;
import com.cta.entity.RegionEntity;
import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;

/**
 * @ClassName: IndexDao
 * @Description: 首页dao层
 * @author chenwenpeng
 * @date 2013-5-18 下午04:11:09
 * 
 */
@Repository
public class IndexDao extends BaseDaoImpl {

	/**
	 * @throws SQLException
	 * @Title: listBuildings
	 * @Description: 列出所有楼宇
	 * @param @param sql
	 * @param @return
	 * @return List<BuildingEntity>
	 * @throws
	 */
	public List<BuildingEntity> listBuildings(String sql) throws SQLException {

		return this.selectAll(sql, BuildingEntity.class);
	}

	/**
	 * @throws SQLException
	 * @Title: listRegions
	 * @Description: 列出所有区域
	 * @param @param sql
	 * @param @return
	 * @return List<RegionEntity>
	 * @throws
	 */
	public List<RegionEntity> listRegions(String sql) throws SQLException {
		return this.selectAll(sql, RegionEntity.class);
	}

	/**
	 * @param id
	 * @param pager
	 * @return
	 * @throws SQLException
	 */
	public List<OrderEntity> listOrder_history(Integer id, ListPager pager)
			throws SQLException {

		String sql = "select o.id,o.user_id,o.rest_id,o.phone,o.address,o.remark,o.carriage,o.totalPrice,o.ordered_time,o.status from mf_order o left join mf_restaurant r on o.rest_id = r.id where o.user_id = ? and o.status <> -1 order by id desc";
		return this.selectPage(sql, new Integer[] { id }, OrderEntity.class,
				pager);
	}
}
