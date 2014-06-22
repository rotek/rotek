package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.dao.impl.CustomerDocDao;
import com.rotek.dto.CustomerDocDto;
import com.rotek.entity.CustomerDocEntity;

/**
* @ClassName:CustomerDocService
* @Description:
* @Author liusw
* @date 2014年6月22日 上午10:21:17
* @Version:1.1.0
*/
@Service
public class CustomerDocService {
	
	@Autowired
	private CustomerDocDao customerDocDao;
	
	public List<CustomerDocDto> listCustomerDocs(CustomerDocEntity customerdoc, ListPager pager) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT R.ID, R.R_CUSTOMER_ID, R.KHZLLB, R.KHZLFJ, R.KHZLMC, R.DLSZJYXQ, RC.MC AS SUPER_MC, R.STATUS FROM R_CUSTOMERDOCINFO R ");
		sql.append(" LEFT JOIN R_CUSTOMER RC ON RC.ID = R.R_CUSTOMER_ID");
		sql.append("  WHERE R.STATUS = 1 ");

		List<Object> params = new ArrayList<Object>();
		if(null != customerdoc.getId()){
			sql.append(" and r.id = ?");
			params.add(customerdoc.getId());
		}
		if(StringUtils.isNotEmpty(customerdoc.getKhzlmc())){
			sql.append(" and r.khzlmc like '%"+customerdoc.getKhzlmc().trim()+"%'");
		}
		if(null != customerdoc.getStatus()){
			sql.append(" and r.status = ?");
			params.add(customerdoc.getStatus());
		}

		sql.append(" order by r.status,r.id asc");
		List<CustomerDocDto> customerdocs = customerDocDao.listCustomerDocs(sql.toString(),params.toArray(),pager);
		return customerdocs;
	}

	/**
	* @MethodName: addCustomerDoc 
	* @Description:
	* @param customerdocEntity
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @author liusw
	*/
	public List<String> addCustomerDoc(CustomerDocEntity customerdocEntity) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<String> messages = ValidateUtil.validate(customerdocEntity);
		if(messages.size()>0){
			return messages;
		}
		customerDocDao.addCustomerDoc(customerdocEntity);
		return null;
	}
	
	/**
	* @MethodName: listCustomers 
	* @Description:
	* @return
	* @throws SQLException
	* @author liusw
	*/
	public List<Map<String, Object>> listCustomers() throws SQLException {
		return customerDocDao.listCustomers();
}

}
