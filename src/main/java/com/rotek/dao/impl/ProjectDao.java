/**
* @FileName: GiftDao.java
* @Package com.cta.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-12 上午09:40:42
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.dto.GiftDto;
import com.rotek.entity.GiftEntity;

@Repository
public class ProjectDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listUserGifts
	* @Description:
	* @param string
	* @param params
	* @param pager
	* @return
	* @return List<RegistUserEntity>
	* @throws
	*/
	public List<GiftDto> listUserGifts(String sql,Object[] params, ListPager pager) throws SQLException {

		return this.selectPage(sql, params, GiftDto.class, pager);
	}

	/**
	 * @throws SQLException
	* @Title: deleteUserGift
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void modifyUserGiftStatus(String sql) throws SQLException {

		this.executeUpdate(sql);
	}

	/**
	 * @throws SQLException
	* @Title: getUserGiftDetail
	* @Description:
	* @param string
	* @return
	* @return GiftDto
	* @throws
	*/
	public GiftDto getUserGiftDetail(String sql) throws SQLException {

		return this.selectOne(sql, GiftDto.class);
	}

	/**
	 * @throws SQLException
	* @Title: addGift
	* @Description:
	* @param gift
	* @return void
	* @throws
	*/
	public void addGift(GiftEntity gift) throws SQLException {

		this.insert(gift);
	}

	/**
	 * @throws SQLException
	* @Title: getGiftDetail
	* @Description:
	* @param id
	* @return
	* @return GiftEntity
	* @throws
	*/
	public GiftEntity getGiftDetail(Integer id) throws SQLException {

		String sql = "select id, name, pic, descr, points, status from mf_gift where id = ?";
		return this.selectOne(sql,new Integer[]{id},GiftEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: modfiyGift
	* @Description:
	* @param gift
	* @return void
	* @throws
	*/
	public void modifyGift(GiftEntity gift) throws SQLException {

		this.update(gift);
	}

	/**
	 * @throws SQLException
	* @Title: deleteGift
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteGift(String sql) throws SQLException {

		this.executeUpdate(sql);
	}
}
