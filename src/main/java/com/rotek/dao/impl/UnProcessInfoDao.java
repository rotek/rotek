package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.entity.UnProcessInfoEntity;
import com.rotek.dto.UnProcessInfoDto;
import com.rotek.entity.ProjectEntity;

/**
* @ClassName:UnProcessInfoEntityDao
* @Description:
* @Author liusw
* @date 2014年6月28日 上午10:01:43
* @Version:1.1.0
*/
@Repository
public class UnProcessInfoDao extends BaseDaoImpl{

	/**
	* @MethodName: listUnProcessInfos 
	* @Description:
	* @param sql
	* @param params
	* @param pager
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<UnProcessInfoDto> listUnProcessInfos(String sql, Object[] params,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, params, UnProcessInfoDto.class, pager);
	}
	
	/**
	* @MethodName: addUnProcessInfo 
	* @Description:
	* @param unprocessInfo
	* @throws SQLException
	* @author liusw
	*/
	public void addUnProcessInfo(UnProcessInfoEntity unprocessInfo) throws SQLException {
		this.insert(unprocessInfo);
	}
	
	/**
	* @MethodName: listCustomers 
	* @Description:
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<Map<String, Object>> listCustomers() throws SQLException {
		
		String sql = "select id, mc from r_customer where status = 1 and khlb = 3";
		return this.executeQuery(sql, null);		
	}

	/**
	* @MethodName: listProjects 
	* @Description:
	* @param r_customer_id
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public ProjectEntity listProjects(Integer r_customer_id) throws SQLException {
		
		StringBuilder sql = new StringBuilder();
		sql.append(" select id, gcmc from r_project where status = 1");
		sql.append("  and r_project.r_customer_id = ? ");
		return this.selectOne(sql.toString(), new Object[] { r_customer_id }, ProjectEntity.class);
	}
	
	/**
	* @MethodName: modifyUnProcessInfo 
	* @Description:
	* @param unprocessinfo
	* @throws SQLException
	* @author liusw
	*/
	public void modifyUnProcessInfo(UnProcessInfoEntity unprocessinfo) throws SQLException {
		this.update(unprocessinfo);
	}

	/**
	* @MethodName: deleteUnProcessInfo 
	* @Description:
	* @param sql
	* @throws SQLException
	* @author liusw
	*/
	public void deleteUnProcessInfo(String sql) throws SQLException {

		this.executeUpdate(sql);
	}
	
	/**
	* @MethodName: getUnProcessInfoDetail 
	* @Description:
	* @param id
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public UnProcessInfoDto getUnProcessInfoDetail(Integer id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct R.ID, R.R_CUSTOMER_ID, R.R_PROJECT_ID, R.ERRORINFOLB, R.SPECIFIC_PART, R.SPECIFIC_BH, R.JLRQ, R.ISDEALED, R.STATUS, RC.MC AS CUSTOMER_MC, RP.GCMC AS PROJECT_MC FROM R_ProcessInfo R ");
		sql.append(" LEFT JOIN R_CUSTOMER RC ON RC.ID = R.R_CUSTOMER_ID");
		sql.append(" LEFT JOIN R_PROJECT RP ON RP.ID = R.R_PROJECT_ID");
		sql.append("  WHERE R.STATUS = 1 ");
		sql.append("  AND R.ID = ? ");
		return this.selectOne(sql.toString(), new Object[] { id }, UnProcessInfoDto.class);
	}
	
}
