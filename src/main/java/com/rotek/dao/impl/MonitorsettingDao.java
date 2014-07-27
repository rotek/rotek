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

@Repository
public class MonitorsettingDao extends BaseDaoImpl{

	public void clearParamsByCustomerIdAndProjectId(Integer customerId,
			Integer projectId) throws SQLException {
		
		String sql = "delete from r_monitorsetting where R_CUSTOMER_ID = ? and R_PROJECT_ID = ?";
		this.executeUpdate(sql, new Object[]{customerId,projectId});
	}

	public void save(Object[] dbParams) throws SQLException {
		
		String sql = "insert into r_monitorsetting(R_CUSTOMER_ID,R_PROJECT_ID,R_COMPONENT_GROUP_ID,R_COMPONENT_DETAIL_ID,SPECIFICPART_PARAM,X,Y,STATUS) values(?,?,?,?,?,?,?,?)";
		
		this.executeUpdate(sql, dbParams);
	}

	public List<Map<String, Object>> getParamListByProjectIdAndCustomerId(
			Integer projectId, Integer customerId) throws SQLException {
		
		String sql = "select * from r_monitorsetting where R_CUSTOMER_ID = ? and R_PROJECT_ID = ?";
		return this.executeQuery(sql, new Integer[]{customerId,projectId});
	}
}
