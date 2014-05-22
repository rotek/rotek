/**
* @FileName: DepartmentService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-27 上午10:52:52
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
import com.rotek.dao.impl.DepartmentDao;
import com.rotek.dto.DepartmentDto;
import com.rotek.entity.DepartmentEntity;
import com.rotek.platform.config.SystemGlobals;
import com.rotek.platform.util.ListPager;
import com.rotek.platform.util.ValidateUtil;

/**
 * @ClassName: DepartmentService
 * @Description: 部门设置
 * @author chenwenpeng
 * @date 2013-6-27 上午10:52:52
 *
 */
@Service
public class DepartmentService {

	@Autowired
	private DepartmentDao departmentDao;
	/**
	 * @throws SQLException
	* @Title: listDepartments
	* @Description:
	* @param @param department
	* @param @param pager
	* @param @return
	* @return List<ManagerEntity>
	* @throws
	*/
	public List<DepartmentDto> listDepartments(DepartmentEntity department,
			ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select 	d.id, d.dep_name,d.super_dep_id,s.dep_name super_dep_name, d.memo, d.status from mf_department d,mf_department s");
		sql.append(" where s.dep_name = (select dep_name from mf_department where id = d.super_dep_id)");

		if(null != department.getId()){
			sql.append(" and d.id = ?");
			params.add(department.getId());
		}
		if(StringUtils.isNotBlank(department.getDep_name())){
			sql.append(" and d.dep_name like '%"+department.getDep_name()+"%'");
		}
		if(StringUtils.isNotBlank(department.getMemo())){
			sql.append(" and d.memo like '%"+department.getMemo()+"%'");
		}
		if(null != department.getSuper_dep_id()){
			sql.append(" and d.super_dep_id = ?");
			params.add(department.getSuper_dep_id());
		}
		if(null != department.getSort()){
			sql.append(" and d.sort = ?");
			params.add(department.getSort());
		}
		if(null != department.getStatus()){
			sql.append(" and d.status = ?");
			params.add(department.getStatus());
		}

		sql.append(" order by d.status,d.sort desc");

		return departmentDao.listDepartments(sql.toString(),params.toArray(),pager);
	}
	/**
	 * @throws SQLException
	* @Title: listDepartments
	* @Description:
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listDepartments() throws SQLException {

		return departmentDao.listDepartments_s();
	}
	/**
	 * @throws SQLException
	* @Title: listDepartments_super
	* @Description:
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listDepartments_super() throws SQLException {

		Integer super_dep_id = SystemGlobals.getIntPreference("super_dep_id", 1);
		return departmentDao.listDepartments_s(super_dep_id);
	}
	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addDepartment
	* @Description:
	* @param @param department
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> addDepartment(DepartmentEntity department) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {

		List<String> messages = ValidateUtil.validate(department);
		if(messages.size()>0){
			return messages;
		}
		departmentDao.addDepartment(department);
		return null;
	}
	/**
	 * @throws SQLException
	* @Title: getDepartmentDetail
	* @Description:
	* @param @param id
	* @param @return
	* @return DepartmentDto
	* @throws
	*/
	public DepartmentDto getDepartmentDetail(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return departmentDao.getDepartmentDetail(id);
	}
	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyDepartment
	* @Description:
	* @param @param department
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyDepartment(DepartmentEntity department) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(department);
		if(messages.size()>0 || null == department.getId()){
			return messages;
		}
		departmentDao.modifyDepartment(department);
		return null;
	}
	/**
	 * @throws SQLException
	* @Title: deleteDepartment
	* @Description:
	* @param @param ids
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteDepartment(String ids) throws SQLException {
		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_department set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		departmentDao.deleteDepartment(sql.toString());
		return messages;
	}
	/**
	 * @throws SQLException
	* @Title: listDeliverers_dep
	* @Description: 列出部门管理的配送员
	* @param @param id
	* @param @return
	* @return List<Integer>
	* @throws
	*/
	public List<Integer> listDeliverers_dep(Integer dep_id) throws SQLException {
		if(null == dep_id){
			return null;
		}
		//列出部门管理的所有配送员
		String sql = "select deliverer_id id from mf_deliverer_dep where dep_id in (select id from mf_department where super_dep_id = ?) or dep_id = ?";
		return departmentDao.listDeliverers_s(sql,dep_id);
	}
	/**
	 * @throws SQLException
	* @Title: SetDeliverers
	* @Description: 设置配送员
	* @param @param dep_id
	* @param @param deliverer_ids
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> updateDeliverers(Integer dep_id, String deliverer_ids) throws SQLException {

		if(null == dep_id || StringUtils.isBlank(deliverer_ids)){
			return new LinkedList<String>();
		}
		departmentDao.clearDep_Deliverers(dep_id);
		for(String id : deliverer_ids.split(",")){
			Integer deliver_id = Integer.parseInt(id);
			departmentDao.updateDeliverers(dep_id,deliver_id);
		}
		return null;
	}
	/**
	 * @throws SQLException
	* @Title: listBuildings_dep
	* @Description: 列出该部门分配的楼宇信息
	* @param id
	* @return
	* @return List<Integer>
	* @throws
	*/
	public List<Integer> listBuildings_dep(Integer dep_id) throws SQLException {
		if(null == dep_id){
			return null;
		}
		//受部门管理的楼宇
		String sql = "select b_id id from mf_building_dep where dep_id in (select id from mf_department where super_dep_id = ?) or dep_id = ?";
		return departmentDao.listBuildings_dep(sql,dep_id);
	}
	/**
	 * @throws SQLException
	* @Title: updateBuildings
	* @Description:
	* @param dep_id
	* @param building_ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> updateBuildings(Integer dep_id, String building_ids) throws SQLException {
		if(null == dep_id || StringUtils.isBlank(building_ids)){
			return new LinkedList<String>();
		}
		departmentDao.clearDep_Buildings(dep_id);
		for(String id : building_ids.split(",")){
			Integer building_id = Integer.parseInt(id);
			departmentDao.updateBuildings(dep_id,building_id);
		}
		return null;
	}
	/**
	 * @throws SQLException
	* @Title: listRestaurants_dep
	* @Description:
	* @param id
	* @return
	* @return List<Integer>
	* @throws
	*/
	public List<Integer> listRestaurants_dep(Integer dep_id) throws SQLException {

		if(null == dep_id){
			return null;
		}
		//受部门管理的店铺
		String sql = "select rest_id id from mf_dep_restaurant where dep_id in (select id from mf_department where super_dep_id = ?) or dep_id = ?";
		return departmentDao.listRestaurants_dep(sql,dep_id);
	}
	/**
	 * @throws SQLException
	* @Title: updateRestaurants
	* @Description:
	* @param dep_id
	* @param restaurant_ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> updateRestaurants(Integer dep_id, String restaurant_ids) throws SQLException {
		if(null == dep_id || StringUtils.isBlank(restaurant_ids)){
			return new LinkedList<String>();
		}
		departmentDao.clearDep_Restaurants(dep_id);

		for(String id : restaurant_ids.split(",")){
			Integer restaurant_id = Integer.parseInt(id);
			departmentDao.updateRestaurants(dep_id,restaurant_id);
		}
		return null;
	}
}
