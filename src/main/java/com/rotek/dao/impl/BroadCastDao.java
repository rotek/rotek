/**
* @FileName: BroadCastDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-6 上午10:56:46
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rotek.dto.BroadCastDto;
import com.rotek.entity.BroadCastEntity;
import com.rotek.platform.persistence.dao.BaseDaoImpl;
import com.rotek.platform.util.ListPager;

/**
 * @ClassName: BroadCastDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-6 上午10:56:46
 *
 */
@Repository
public class BroadCastDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	 * @param pager
	* @Title: listBroadcasts
	* @Description:
	* @param string
	* @param array
	* @return
	* @return List<BroadCastDto>
	* @throws
	*/
	public List<BroadCastDto> listBroadcasts(String sql, Object[] params, ListPager pager) throws SQLException {

		return this.selectPage(sql, params, BroadCastDto.class, pager);
	}

	/**
	 * @throws SQLException
	* @Title: addBroadCast
	* @Description:
	* @param broadCast
	* @return void
	* @throws
	*/
	public void addBroadCast(BroadCastEntity broadCast) throws SQLException {

		this.insert(broadCast);
	}

	/**
	 * @throws SQLException
	* @Title: braodCastDao
	* @Description:
	* @param id
	* @return
	* @return BroadCastEntity
	* @throws
	*/
	public BroadCastEntity getBroadcastDetail(Integer id) throws SQLException {

		String sql = "select id, name, alt, target, buildingId, status from mf_broadcast where id = ?";
		return this.selectOne(sql, new Integer[]{id},BroadCastEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: modifyBroadcast
	* @Description:
	* @param broadCast
	* @return
	* @return List<String>
	* @throws
	*/
	public void modifyBroadcast(BroadCastEntity broadCast) throws SQLException {

		this.update(broadCast);
	}

	/**
	 * @throws SQLException
	* @Title: deleteBroadcast
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteBroadcast(String sql) throws SQLException {

		this.executeUpdate(sql);
	}
}
