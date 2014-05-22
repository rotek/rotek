/**
* @FileName: AboutDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-12 下午03:47:55
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.constant.DataStatus;
import com.rotek.platform.persistence.dao.BaseDaoImpl;
import com.rotek.platform.util.ListPager;

/**
 * @ClassName: AboutDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-12 下午03:47:55
 *
 */
@Repository
public class AboutDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	 * @param pager
	* @Title: listAbouts
	* @Description:
	* @param string
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listAbouts(String sql, ListPager pager) throws SQLException {

		return this.executeQueryPage(sql, new Integer[]{}, pager);
	}

	/**
	 * @throws SQLException
	* @Title: getAboutByType
	* @Description:
	* @param type
	* @return
	* @return Map<String,Object>
	* @throws
	*/
	public Map<String, Object> getAboutByType(Integer type) throws SQLException {

		String sql= "select type from mf_about where type = ? and status = ? limit 1";
		return this.executeQueryOne(sql, new Integer[]{type,DataStatus.ENABLED});
	}

	/**
	 * @throws SQLException
	* @Title: addAbout
	* @Description:
	* @param type
	* @param content
	* @return void
	* @throws
	*/
	public void addAbout(Integer type,Integer status, String content) throws SQLException {

		String sql = "insert into mf_about values (null,?,?,?)";
		this.executeUpdate(sql, new Object[]{content,type,status});
	}

	/**
	 * @throws SQLException
	* @Title: getAboutDetail
	* @Description:
	* @param id
	* @return
	* @return Map<String,Object>
	* @throws
	*/
	public Map<String, Object> getAboutDetail(Integer id) throws SQLException {

		String sql ="select id, content, type,status from mf_about where id = ?";
		return this.executeQueryOne(sql,new Integer[]{id});
	}

	/**
	 * @param status
	 * @throws SQLException
	* @Title: modifyAbout
	* @Description:
	* @param id
	* @param type
	* @param content
	* @return void
	* @throws
	*/
	public void modifyAbout(Integer id, Integer type, Integer status, String content) throws SQLException {

		String sql = "update mf_about set content=?,type=?,status=? where id=?";
		this.executeUpdate(sql, new Object[]{content,type,status,id});
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
