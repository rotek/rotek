/**
* @FileName: NewsDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-7 上午08:27:18
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rotek.dto.NewsDto;
import com.rotek.entity.NewsEntity;
import com.rotek.platform.persistence.dao.BaseDaoImpl;
import com.rotek.platform.util.ListPager;

/**
 * @ClassName: NewsDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-7 上午08:27:18
 *
 */
@Repository
public class NewsDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listNews
	* @Description:
	* @param string
	* @param array
	* @param pager
	* @return void
	* @throws
	*/
	public List<NewsDto> listNews(String sql, Object[] params, ListPager pager) throws SQLException {

		return this.selectPage(sql, params, NewsDto.class, pager);
	}

	/**
	 * @throws SQLException
	* @Title: addNews
	* @Description:
	* @param news
	* @return void
	* @throws
	*/
	public void addNews(NewsEntity news) throws SQLException {

		this.insert(news);
	}

	/**
	 * @throws SQLException
	* @Title: getNewsDetail
	* @Description:
	* @param id
	* @return
	* @return NewsEntity
	* @throws
	*/
	public NewsEntity getNewsDetail(Integer id) throws SQLException {

		String sql = "select id, building_id, title, content, send_time, type, level, status from mf_news where id = ?";
		return this.selectOne(sql, new Integer[]{id},NewsEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: modifyNews
	* @Description:
	* @param news
	* @return void
	* @throws
	*/
	public void modifyNews(NewsEntity news) throws SQLException {

		this.update(news);
	}

	/**
	 * @throws SQLException
	* @Title: deleteNews
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteNews(String sql) throws SQLException {

		this.executeUpdate(sql);
	}
}
