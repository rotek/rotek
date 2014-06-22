package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;
import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.entity.CustomerDocEntity;
import com.rotek.dto.CustomerDocDto;

/**
* @ClassName:CustomerDocDao
* @Description:
* @Author liusw
* @date 2014年6月22日 上午10:57:30
* @Version:1.1.0
*/
@Repository
public class CustomerDocDao extends BaseDaoImpl {
	
	/**
	* @MethodName: listCustomerDocs 
	* @Description:
	* @param sql
	* @param params
	* @param pager
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<CustomerDocDto> listCustomerDocs(String sql, Object[] params,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, params, CustomerDocDto.class, pager);
	}
	
	/**
	* @MethodName: addCustomerDoc 
	* @Description:
	* @param customerdoc
	* @throws SQLException
	* @author liusw
	*/
	public void addCustomerDoc(CustomerDocEntity customerdoc) throws SQLException {
		this.insert(customerdoc);
	}
	
	/**
	* @MethodName: listAgentsByType 
	* @Description:
	* @param khlb
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<Map<String, Object>> listCustomers() throws SQLException {
		
			String sql = "select id, mc from r_customer where status = 1";
			return this.executeQuery(sql, null);		
	}


}
