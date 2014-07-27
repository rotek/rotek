package com.rotek.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.dao.impl.MonitorsettingDao;

@Service
public class MonitorsettingService {

	@Autowired
	private MonitorsettingDao monitorsettingDao;

	
	public void clearParamsByCustomerIdAndProjectId(Integer customerId,
			Integer projectId) throws SQLException {
		
		monitorsettingDao.clearParamsByCustomerIdAndProjectId(customerId,projectId);
	}
	
	public void save(Object[] dbParams) throws SQLException {
		
		
		monitorsettingDao.save(dbParams);
	}

	public List<Map<String, Object>> getParamListByProjectIdAndCustomerId(
			Integer projectId, Integer customerId) throws SQLException {
		
		
		return monitorsettingDao.getParamListByProjectIdAndCustomerId(projectId,customerId);
	}
}
