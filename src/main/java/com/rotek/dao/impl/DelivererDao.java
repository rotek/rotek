/**
* @FileName: DelivererDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-28 下午02:14:30
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.dto.DelivererDto;
import com.rotek.entity.DelivererEntity;
import com.rotek.entity.DepartmentEntity;
import com.rotek.platform.persistence.dao.BaseDaoImpl;
import com.rotek.platform.util.ListPager;

/**
 * @ClassName: DelivererDao
 * @Description: 配送员
 * @author chenwenpeng
 * @date 2013-6-28 下午02:14:30
 *
 */
@Repository
public class DelivererDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listDeliverers_s
	* @Description: 列出所有可分配的配送员 简单信息
	* @param @param dep_id
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listDeliverers_s(String sql) throws SQLException {

		return this.executeQuery(sql,null);
	}

	/**
	 * @throws SQLException
	* @Title: listDeliverers
	* @Description:
	* @param sql
	* @param array
	* @param pager
	* @return
	* @return List<DelivererDto>
	* @throws
	*/
	public List<DelivererDto> listDeliverers(String sql, Object[] parameters,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, parameters, DelivererDto.class, pager);
	}

	/**
	 * @throws SQLException
	* @Title: listDeliverers
	* @Description:
	* @param d_id
	* @return
	* @return List<DepartmentEntity>
	* @throws
	*/
	public List<DepartmentEntity> listDeliverers(Integer d_id) throws SQLException {

		String sql = "select id,dep_name from mf_department where id in (select dep_id from mf_deliverer_dep where deliverer_id = ?)";
		return this.selerotekll(sql, new Integer []{d_id},DepartmentEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: getDelivererDetail
	* @Description:
	* @param id
	* @return
	* @return DelivererEntity
	* @throws
	*/
	public DelivererEntity getDelivererDetail(Integer id) throws SQLException {

		String sql = "select id, realname, telephone, gender,send_type, status from mf_deliverer where id = ?";
		return this.selectOne(sql, new Integer[]{id},DelivererEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: modifyDeliverer
	* @Description:
	* @param deliverer
	* @return void
	* @throws
	*/
	public void modifyDeliverer(DelivererEntity deliverer) throws SQLException {

		this.update(deliverer);
	}

	/**
	 * @throws SQLException
	* @Title: deleteDeliverer
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteDeliverer(String sql) throws SQLException {

		this.executeUpdate(sql);
	}

	/**
	 * @return 
	 * @throws SQLException
	* @Title: addDeliverer
	* @Description:
	* @param deliverer
	* @return void
	* @throws
	*/
	public Integer addDeliverer(DelivererEntity deliverer) throws SQLException {

		return this.insert_pk(deliverer);
	}

	/**
	 * @throws SQLException
	* @Title: listDeliverers_s_dep
	* @Description:
	* @param dep_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listDeliverers_s_dep(Integer dep_id) throws SQLException {

		String sql = "select id,realname from mf_deliverer where id in (select deliverer_id from mf_deliverer_dep where dep_id in (select id from mf_department where id = ? or super_dep_id = ?)) and status = 1";
		return this.executeQuery(sql, new Integer[]{dep_id,dep_id});
	}

	/**
	 * @throws SQLException 
	* @Title: addDelivererDepartment
	* @Description: 
	* @param @param delivererId
	* @param @param dep_id 
	* @return void 
	* @throws 
	*/ 
	public void addDelivererDepartment(Integer delivererId, Integer dep_id) throws SQLException {
		
		String sql = "insert into mf_deliverer_dep values(null,?,?)";
		this.executeUpdate(sql, new Integer[]{delivererId,dep_id});
	}
}
