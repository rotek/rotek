package com.rotek.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.CustomerDao;
import com.rotek.entity.CustomerEntity;
import com.rotek.util.FileUtils;


/**
* @ClassName:CustomerService
* @Description: 客户信息业务
* @Author Liusw
* @date 2014年6月14日 下午10:28:32
* @Version:1.1.0
*/
@Service
public class CustomerService {
	@Autowired
	private CustomerDao customerDao;
	
	/**
	* @MethodName: listRoles 
	* @Description: 列出所有客户信息
	* @param customer
	* @param pager
	* @return
	* @throws SQLException
	* @author Liusw
	*/
	public List<CustomerEntity> listRoles(CustomerEntity customer, ListPager pager) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("select ID, R_CUSTOMER_ID, KHLB, MC, TXDZ, LXFS, LXR, LXDH, DLQY, JWDDZ, STATUS from r_customer where 1 = 1");
		List<Object> params = new ArrayList<Object>();
		if(null != customer.getId()){
			sql.append(" and id = ?");
			params.add(customer.getId());
		}
		if(StringUtils.isNotEmpty(customer.getMc())){
			sql.append(" and name like '%"+customer.getMc().trim()+"%'");
		}
		if(null != customer.getStatus()){
			sql.append(" and status = ?");
			params.add(customer.getStatus());
		}

		sql.append(" order by status,id asc");
		List<CustomerEntity> customers = customerDao.listCustomers(sql.toString(),params.toArray(),pager);
		return customers;
	}
	
	/**
	* @MethodName: addCustomer 
	* @Description: 添加一条客户信息
	* @param customerEntity
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @author Liusw
	*/
	public List<String> addCustomer(CustomerEntity customerEntity) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<String> messages = ValidateUtil.validate(customerEntity);
		if(messages.size()>0){
			return messages;
		}
		customerDao.addCustomer(customerEntity);
		return null;
	}
	
	/**
	* @MethodName: getCustomerDetail 
	* @Description: 获取一个客户的详细信息
	* @param id
	* @return
	* @throws SQLException
	* @author Liusw
	*/
	public CustomerEntity getCustomerDetail(Integer id) throws SQLException{

		CustomerEntity customer = customerDao.getCustomerDetail_all(id);
		if(null == customer){
			customer = customerDao.getCustomerDetail(id);
		}
		return customer;
	}
	

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyCustomer
	* @Description: 修改客户信息
	* @param @param customerEntity
	* @param @param authority
	* @param @return
	* @return String
	* @throws
	*/
	public List<String> modifyCustomer(CustomerEntity customerEntity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(customerEntity);
		if(messages.size()>0 || null == customerEntity.getId()){
			return messages;
		}
		//修改角色基本信息
		customerDao.modifyCustomer(customerEntity);

		return null;
	}

	/**
	 * @throws SQLException
	* @Title: deleteCustomer
	* @Description: 删除一个或多个角色
	* @param @param ids
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteCustomer(String id_str) throws SQLException {
		List<String> messages = null;
		if(StringUtils.isBlank(id_str)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update r_customer set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+id_str.trim()+")");
		customerDao.deleteCustomer(sql.toString());
		return messages;
	}
	
	/**
	 * @throws SQLException
	* @Title: listAgents
	* @Description:
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listAgents(HttpServletRequest request) throws SQLException {
			return customerDao.listAgents(request);
	}
}
