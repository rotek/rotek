/**
* @FileName: DelivererService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-28 下午02:04:56
* @version V1.0
*/
package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.constant.DataStatus;
import com.rotek.constant.RangeType;
import com.rotek.dao.impl.DelivererDao;
import com.rotek.dto.DelivererDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.DelivererEntity;
import com.rotek.entity.DepartmentEntity;
import com.rotek.platform.util.ListPager;
import com.rotek.platform.util.ValidateUtil;

/**
 * @ClassName: DelivererService
 * @Description: 配送员
 * @author chenwenpeng
 * @date 2013-6-28 下午02:04:56
 *
 */
@Service
public class DelivererService {

	@Autowired
	private DelivererDao delivererDao;
	/**
	 * @param range
	 * @throws SQLException
	* @Title: listDeliverers_s
	* @Description:
	* @param @param dep_id
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listDeliverers_s(String realname, Integer range) throws SQLException {
		StringBuilder sql = new StringBuilder();
		if(StringUtils.isNotBlank(realname)){
			sql.append("select id,realname from mf_deliverer where status = 1 and realname like '%"+realname.trim()+"%'");
		}else {
			sql.append("select id,realname from mf_deliverer where status = 1");
		}
		//只查询已经未分配的数据
		if(RangeType.UNDISTRIBUTED == range){
			sql.append(" and id not in (select deliverer_id from mf_deliverer_dep)");
		}
		sql.append(" order by id desc");
		return delivererDao.listDeliverers_s(sql.toString());
	}
	/**
	 * @param user 
	 * @throws SQLException
	* @Title: listDeliverers
	* @Description:
	* @param deliverer
	* @param pager
	* @return
	* @return List<DelivererEntity>
	* @throws
	*/
	public List<DelivererDto> listDeliverers(DelivererDto deliverer,
			UserDto user, ListPager pager) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select id, realname, telephone, gender,send_type, status from mf_deliverer where 1 = 1");
		if(null != deliverer.getId()){
			sql.append(" and id = ?");
			params.add(deliverer.getId());
		}

		if(StringUtils.isNotBlank(deliverer.getRealname())){
			sql.append(" and realname like '%"+deliverer.getRealname()+"%'");
		}
		if(StringUtils.isNotBlank(deliverer.getTelephone())){
			sql.append(" and telephone like '%"+deliverer.getTelephone()+"%'");
		}
		if(null != deliverer.getGender()){
			sql.append(" and gender = ?");
			params.add(deliverer.getGender());
		}
		if(null != deliverer.getStatus()){
			sql.append(" and status = ?");
			params.add(deliverer.getStatus());
		}
		if(null != deliverer.getDep_id()){
			sql.append(" and id in (select deliverer_id from mf_deliverer_dep where dep_id in (select id from mf_department where super_dep_id = ? or id = ?))");
			params.add(deliverer.getDep_id());
			params.add(deliverer.getDep_id());
		}
		
		if(null != user && null != user.getDep_id()){
			sql.append(" and id in (select deliverer_id from mf_deliverer_dep where dep_id in (select id from mf_department where id = ? or super_dep_id = ?))");
			params.add(user.getDep_id());
			params.add(user.getDep_id());
		}else {
			return null;
		}
		
		sql.append(" order by id desc");
		List<DelivererDto> deliverers = delivererDao.listDeliverers(sql.toString(),params.toArray(),pager);
		for(DelivererDto d : deliverers){
			Integer d_id = d.getId();
			List<DepartmentEntity> deps = delivererDao.listDeliverers(d_id);
			for(DepartmentEntity dep : deps){
				if(deps.size()>1){
					d.setDep_name("<span style=\'color:green;\'>"+(null == d.getDep_name() ? "" : d.getDep_name()) + dep.getDep_name()+"  "+"</span>");
				}else {
					d.setDep_name((null == d.getDep_name() ? "" : d.getDep_name()) + dep.getDep_name()+"  ");
				}
			}
		}
		return deliverers;
	}


	/**
	 * @param user 
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	* @Title: addDeliverer
	* @Description:
	* @param deliverer
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> addDeliverer(DelivererEntity deliverer, UserDto user) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<String> messages = ValidateUtil.validate(deliverer);
		if(messages.size() > 0){
			return messages;
		}
		//保存配送员信息
		Integer delivererId = delivererDao.addDeliverer(deliverer);
		//给配送员分配管理部门
		if(null != delivererId && null != user && null != user.getDep_id()){
			
			delivererDao.addDelivererDepartment(delivererId,user.getDep_id());
		}else {
			throw new SQLException();
		}
		return null;
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
		if(null == id){
			return null;
		}
		return delivererDao.getDelivererDetail(id);
	}
	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyDeliverer
	* @Description:
	* @param deliverer
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyDeliverer(DelivererEntity deliverer) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {

		List<String> messages = ValidateUtil.validate(deliverer);
		if(messages.size() > 0 || null == deliverer.getId()){
			return messages;
		}

		delivererDao.modifyDeliverer(deliverer);
		return null;
	}
	/**
	 * @throws SQLException
	* @Title: deleteDeliverer
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteDeliverer(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_deliverer set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		delivererDao.deleteDeliverer(sql.toString());
		return messages;
	}
	/**
	 * @throws SQLException
	* @Title: listDeliverers_s_dep
	* @Description:
	* @param attribute
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listDeliverers_s_dep(UserDto user) throws SQLException {

		if(null ==user || null == user.getDep_id()){
			return null;
		}
		return delivererDao.listDeliverers_s_dep(user.getDep_id());
	}
}
