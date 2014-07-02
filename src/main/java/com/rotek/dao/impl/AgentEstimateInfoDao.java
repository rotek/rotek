package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.entity.AgentEstimateInfoEntity;
import com.rotek.dto.AgentEstimateInfoDto;

/**
* @ClassName:AgentEstimateInfoEntityDao
* @Description:
* @Author liusw
* @date 2014年6月28日 上午10:01:46
* @Version:1.1.0
*/
@Repository
public class AgentEstimateInfoDao extends BaseDaoImpl{

	/**
	* @MethodName: listAgentEstimateInfos 
	* @Description:
	* @param sql
	* @param params
	* @param pager
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<AgentEstimateInfoDto> listAgentEstimateInfos(String sql, Object[] params,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, params, AgentEstimateInfoDto.class, pager);
	}
	
	/**
	* @MethodName: addAgentEstimateInfo 
	* @Description:
	* @param agentestimateinfo
	* @throws SQLException
	* @author liusw
	*/
	public void addAgentEstimateInfo(AgentEstimateInfoEntity agentestimateinfo) throws SQLException {
		this.insert(agentestimateinfo);
	}
	
	/**
	* @MethodName: listCustomers 
	* @Description:
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<Map<String, Object>> listCustomers() throws SQLException {
		
		String sql = "select id, mc from r_customer where status = 1 and khlb <> 3";
		return this.executeQuery(sql, null);		
	}
	
	/**
	* @MethodName: modifyAgentEstimateInfo 
	* @Description:
	* @param agentestimateinfo
	* @throws SQLException
	* @author liusw
	*/
	public void modifyAgentEstimateInfo(AgentEstimateInfoEntity agentestimateinfo) throws SQLException {
		this.update(agentestimateinfo);
	}

	/**
	* @MethodName: deleteAgentEstimateInfo 
	* @Description:
	* @param sql
	* @throws SQLException
	* @author liusw
	*/
	public void deleteAgentEstimateInfo(String sql) throws SQLException {

		this.executeUpdate(sql);
	}
	
	/**
	* @MethodName: getAgentEstimateInfoDetail 
	* @Description:
	* @param id
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public AgentEstimateInfoDto getAgentEstimateInfoDetail(Integer id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct R.ID, R.R_CUSTOMER_ID, R.DLSXJPJ, R.DLSGLXZ, R.STATUS, RC.MC AS CUSTOMER_MC FROM R_AgentEstimateInfo R ");
		sql.append(" LEFT JOIN R_CUSTOMER RC ON RC.ID = R.R_CUSTOMER_ID");
		sql.append("  WHERE R.STATUS = 1 ");
		sql.append("  AND R.ID = ? ");
		return this.selectOne(sql.toString(), new Object[] { id }, AgentEstimateInfoDto.class);
	}

}
