package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.dto.CustomerDto;
import com.rotek.entity.ButtonEntity;
import com.rotek.entity.CustomerEntity;


/**
* @ClassName:CustomerDao
* @Description: 客户信息关系DAO实现
* @Author Liusw
* @date 2014年6月14日 下午10:29:52
* @Version:1.1.0
*/
@Repository
public class CustomerDao extends BaseDaoImpl{
	
	/**
	* @MethodName: listCustomers 
	* @Description:
	* @param sql
	* @param params
	* @param pager
	* @return
	* @throws SQLException
	* @author Liusw
	*/
	public List<CustomerDto> listCustomers(String sql, Object[] params,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, params, CustomerDto.class, pager);
	}
	
	/**
	* @MethodName: addCustomer 
	* @Description: 添加客户信息
	* @param customer
	* @throws SQLException
	* @author Liusw
	*/
	public void addCustomer(CustomerEntity customer) throws SQLException {
		this.insert(customer);
	}
	
	/**
	* @MethodName: getCustomerDetail_all 
	* @Description: 获取客户的所有信息
	* @param id
	* @return
	* @throws SQLException
	* @author Liusw
	*/
	public CustomerEntity getCustomerDetail_all(Integer id) throws SQLException {
		String sql = "select ID, KHLB, MC, TXDZ, LXFS, LXR, LXDH, DLQY, JWDDZ, STATUS, R_CUSTOMER_ID from r_customer where id = ?";

		return this.selectOne(sql, new Object[] { id }, CustomerEntity.class);
	}
	
	/**
	* @MethodName: getCustomerDetail 
	* @Description: 获取客户的部分信息
	* @param id
	* @return
	* @throws SQLException
	* @author Liusw
	*/
	public CustomerEntity getCustomerDetail(Integer id) throws SQLException {
		String sql = "select distinct ID, KHLB, MC, TXDZ, LXFS, LXR, LXDH, DLQY, JWDDZ, STATUS from r_customer where id = ?";

		return this.selectOne(sql, new Object[] { id }, CustomerEntity.class);
	}

	
	public List<ButtonEntity> listAuthority_button(Integer nodeId)
			throws SQLException {
		String sql = "select id,name from r_button where id in (select button_id from r_menu_button where menu_id = ?)";
		return this.selectAll(sql, new Object[] { nodeId }, ButtonEntity.class);
	}
	
	public Boolean testButtonAuthority(Integer roleId, Integer menuId,
			Integer buttonId) throws SQLException {
		String sql = "select id from r_role_authority where role_id = ? and menu_id = ? and button_id = ?";
		return this
				.executeQuery(sql, new Object[] { roleId, menuId, buttonId })
				.size() > 0 ? true : false;
	}
	
	public Boolean testMenuAuthority(Integer roleId, Integer menuId)
			throws SQLException {
		String sql = "select id from r_role_authority where role_id = ? and menu_id = ?";
		return this.executeQuery(sql, new Object[] { roleId, menuId }).size() > 0 ? true
				: false;
	}
	
	
	/**
	* @MethodName: modifyCustomer 
	* @Description: 修改客户信息
	* @param customerEntity
	* @throws SQLException
	* @author Liusw
	*/
	public void modifyCustomer(CustomerEntity customerEntity) throws SQLException {
		this.update(customerEntity);
	}

	/**
	* @MethodName: deleteCustomer 
	* @Description: 删除客户信息
	* @param sql
	* @throws SQLException
	* @author Liusw
	*/
	public void deleteCustomer(String sql) throws SQLException {

		this.executeUpdate(sql);
	}
	
	/**
	 * @throws SQLException
	 * @Title: listAgents
	 * @Description:
	 * @param @return
	 * @return List<Map<String,Object>>
	 * @throws
	 */
	public List<Map<String, Object>> listAgentsByType(Integer khlb) throws SQLException {
		
		if (khlb == 1) {
			String sql = "select id, mc from r_customer where status = ? and khlb= ?";
			return this.executeQuery(sql, new Integer[]{CustomerEntity.STATUS_ENABLED,khlb});		
		}
		else if (khlb == 2) {
			String sql = "select id, mc from r_customer where (status = ?) and ((khlb= 1) or (khlb=?)) ";
			return this.executeQuery(sql, new Integer[]{CustomerEntity.STATUS_ENABLED,khlb});					
		}
		else {
			return null;
		}
	}
	
	/**
	* @MethodName: selectCustomers 
	* @Description: 查询客户信息
	* @param status
	* @param khlb
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<CustomerEntity> selectCustomers(Integer status , Integer khlb)throws SQLException{
		StringBuffer sql = new StringBuffer();
		sql.append(" select * from r_customer where 1=1 ");
		if(status != null){
			sql.append(" and status = " + status);
		}
		if(khlb != 0 && khlb != null ){
			sql.append(" and khlb = " + khlb );
		}
		return selectAll(sql.toString(), CustomerEntity.class) ;
	}
	
	

}
