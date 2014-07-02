package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.entity.ComplaintInfoEntity;
import com.rotek.dto.ComplaintInfoDto;

/**
* @ClassName:ComplaintInfoEntityDao
* @Description:
* @Author liusw
* @date 2014年6月28日 上午10:01:36
* @Version:1.1.0
*/
@Repository
public class ComplaintInfoDao extends BaseDaoImpl{

	/**
	* @MethodName: listComplaintInfos 
	* @Description:
	* @param sql
	* @param params
	* @param pager
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<ComplaintInfoDto> listComplaintInfos(String sql, Object[] params,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, params, ComplaintInfoDto.class, pager);
	}
	
	/**
	* @MethodName: addComplaintInfo 
	* @Description:
	* @param complaintinfo
	* @throws SQLException
	* @author liusw
	*/
	public void addComplaintInfo(ComplaintInfoEntity complaintinfo) throws SQLException {
		this.insert(complaintinfo);
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
	* @MethodName: modifyComplaintInfo 
	* @Description:
	* @param complaintinfo
	* @throws SQLException
	* @author liusw
	*/
	public void modifyComplaintInfo(ComplaintInfoEntity complaintinfo) throws SQLException {
		this.update(complaintinfo);
	}

	/**
	* @MethodName: deleteComplaintInfo 
	* @Description:
	* @param sql
	* @throws SQLException
	* @author liusw
	*/
	public void deleteComplaintInfo(String sql) throws SQLException {

		this.executeUpdate(sql);
	}
	
	/**
	* @MethodName: getComplaintInfoDetail 
	* @Description:
	* @param id
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public ComplaintInfoDto getComplaintInfoDetail(Integer id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT distinct R.ID, R.R_CUSTOMER_ID, R.TSDW, R.TSSX, R.TSSJ, R.STATUS, RC.MC AS CUSTOMER_MC FROM R_ComplaintInfo R ");
		sql.append(" LEFT JOIN R_CUSTOMER RC ON RC.ID = R.R_CUSTOMER_ID");
		sql.append("  WHERE R.STATUS = 1 ");
		sql.append("  AND R.ID = ? ");
		return this.selectOne(sql.toString(), new Object[] { id }, ComplaintInfoDto.class);
	}


}
