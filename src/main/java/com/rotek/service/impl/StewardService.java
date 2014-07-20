/**
* @FileName: ButtonService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 下午03:27:49
* @version V1.0
*/
package com.rotek.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rotek.dao.impl.StewardDao;
import com.rotek.entity.ConsultantEntity;

/**
 * @author chenwenpeng
 *
 */
@Service
public class StewardService {

	@Resource
	private StewardDao stewardDao;
	
	public List<Map<String, Object>> getResourceList() throws SQLException {
		
		
		return stewardDao.getResourceList();
	}

	public List<Map<String, Object>> getConsultantList() throws SQLException {
		
		
		return stewardDao.getConsultantList();
	}

	public void addConsultant(ConsultantEntity cte) throws SQLException {
		
		stewardDao.addConsultant(cte);
	}
}
