/**
* @FileName: ButtonDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 下午03:29:30
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;

/**
 * @ClassName: ButtonDao
 * @Description: 按钮
 * @author chenwenpeng
 * @date 2013-6-26 下午03:29:30
 *
 */
@Repository
public class NewsDao extends BaseDaoImpl{

	public List<Map<String, Object>> getNewsList() throws SQLException {
		
		String sql = "select n.*,m.realname from r_news n,r_manager m where n.sender_id = m.id";
		
		return this.executeQuery(sql, null);
	}

	public Map<String, Object> getNewsDetailById(Integer newsId) throws SQLException {
		
		String sql = "select * from r_news where id = ?";
		return this.executeQueryOne(sql, new Integer[]{newsId});
	}
	
}
