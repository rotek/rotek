/**
* @FileName: RegionDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-5 下午04:48:18
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.constant.DataStatus;
import com.rotek.entity.RegionEntity;
import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;

/**
 * @ClassName: RegionDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-5 下午04:48:18
 *
 */
@Repository
public class RegionDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listRegions_s
	* @Description:
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRegions_s() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name from mf_region where status = ").append(DataStatus.ENABLED).append(" order by sort desc");
		return this.executeQuery(sql.toString(), null);
	}

	/**
	* @Title: listRegions_sort
	* @Description:
	* @return
	* @throws SQLException
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRegions_sort() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select sort,name from mf_region order by sort desc");
		return this.executeQuery(sql.toString(), null);
	}

	/**
	* @Title: listRegions
	* @Description:
	* @param pager
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<RegionEntity> listRegions(ListPager pager) throws SQLException {

		String sql = "select id, name, type, sort, status from mf_region order by sort desc";
		return this.selectPage(sql, null, RegionEntity.class,pager);
	}

	/**
	 * @throws SQLException
	* @Title: addBuilding
	* @Description:
	* @param region
	* @return void
	* @throws
	*/
	public void addBuilding(RegionEntity region) throws SQLException {


		this.insert(region);
	}

	/**
	 * @throws SQLException
	* @Title: getRegionDetail
	* @Description:
	* @param id
	* @return
	* @return RegionEntity
	* @throws
	*/
	public RegionEntity getRegionDetail(Integer id) throws SQLException {


		String sql = "select id, name, city_id, type, sort, status from mf_region where id = ?";
		return this.selectOne(sql, new Integer[]{id},RegionEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: modifyRegion
	* @Description:
	* @param region
	* @return void
	* @throws
	*/
	public void modifyRegion(RegionEntity region) throws SQLException {

		this.update(region);
	}

	/**
	 * @throws SQLException
	* @Title: deleteRegion
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteRegion(String sql) throws SQLException {

		this.executeUpdate(sql);
	}
}
