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
import com.rotek.entity.ConsultantEntity;

@Repository
public class StewardDao extends BaseDaoImpl{

	public List<Map<String, Object>> getResourceList() throws SQLException {
		
		String sql = "select n.*,m.realname from r_resources n,r_manager m where n.sender_id = m.id";
		return this.executeQuery(sql,null);
	}

	public List<Map<String, Object>> getConsultantList() throws SQLException {
		
		String sql = "select n.*,m1.realname as sender_name,m2.realname as responser_name from r_consultant n join r_manager m1 on n.sender_id = m1.id left join r_manager m2 on  n.response_id = m2.id";
		return this.executeQuery(sql,null);
	}

	public void addConsultant(ConsultantEntity cte) throws SQLException {
		
		this.insert(cte);
	}
}
