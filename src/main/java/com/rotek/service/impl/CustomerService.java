package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.CustomerDao;
import com.rotek.dto.CustomerDto;
import com.rotek.entity.CustomerEntity;


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
	* @MethodName: listCustomers 
	* @Description: 列出所有客户信息
	* @param customer
	* @param pager
	* @return
	* @throws SQLException
	* @author Liusw
	*/
	public List<CustomerDto> listCustomers(CustomerEntity customer, Integer customerType, ListPager pager) throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		//sql.append("select ID, R_CUSTOMER_ID, KHLB, MC, TXDZ, LXFS, LXR, LXDH, DLQY, JWDDZ, STATUS from r_customer where STATUS = 1");
		sql.append("SELECT R.ID, R.R_CUSTOMER_ID, R.KHLB, R.MC,RC.MC AS SUPER_MC, R.TXDZ, R.LXFS, R.LXR, R.LXDH, R.DLQY, R.JWDDZ, R.STATUS FROM R_CUSTOMER R ");
		sql.append(" LEFT JOIN R_CUSTOMER RC ON RC.ID = R.R_CUSTOMER_ID");
		sql.append("  WHERE R.STATUS = 1 ");
		if (customerType == 3){
			sql.append(" and r.khlb = 3");
		}
		else{
			sql.append(" and r.khlb != 3");
		}

		List<Object> params = new ArrayList<Object>();
		if(null != customer.getId()){
			sql.append(" and r.id = ?");
			params.add(customer.getId());
		}
		if(StringUtils.isNotEmpty(customer.getMc())){
			sql.append(" and r.mc like '%"+customer.getMc().trim()+"%'");
		}
		if(null != customer.getStatus()){
			sql.append(" and r.status = ?");
			params.add(customer.getStatus());
		}

		sql.append(" order by r.status,r.id asc");
		List<CustomerDto> customers = customerDao.listCustomers(sql.toString(),params.toArray(),pager);
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
//		List<String> messages = ValidateUtil.validate(customerEntity);
//		if(messages.size()>0){
//			return messages;
//		}
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
	public List<Map<String, Object>> listAgentsByType(Integer type) throws SQLException {
			return customerDao.listAgentsByType(type);
	}
	
	/**
	* @MethodName: selectCustomers 
	* @Description: 查询客户信息
	* @param status 状态
	* @param khlb 客户类别
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<CustomerEntity> selectCustomers(Integer status , Integer khlb)throws SQLException{
		return customerDao.selectCustomers(status , khlb ) ;
	}
	

}
