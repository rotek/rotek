/**
* @FileName: BuildingService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-4 上午09:27:53
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
import com.rotek.dao.impl.BuildingDao;
import com.rotek.dto.BuildingDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.BuildingEntity;
import com.rotek.entity.DepartmentEntity;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;

/**
 * @ClassName: BuildingService
 * @Description: 楼宇的service
 * @author chenwenpeng
 * @date 2013-7-4 上午09:27:53
 *
 */
@Service
public class BuildingService {

	@Autowired
	private BuildingDao buildingDao;
	/**
	 * @param name
	 * @throws SQLException
	* @Title: listBuildings_s
	* @Description:
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listBuildings_s(String name, Integer region) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select id,name from mf_building where status = ").append(DataStatus.ENABLED);
		//查询未分配的
		if(RangeType.UNDISTRIBUTED == region){
			sql.append(" and id not in (select distinct b_id from mf_building_dep)");
		}
		//模糊查询
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like '%"+name.trim()+"%'");
		}
		sql.append(" order by id desc");
		return buildingDao.listBuildings_s(sql.toString());
	}

	/**
	 * @param user 
	 * @throws SQLException
	* @Title: listBuildings
	* @Description:
	* @param buildingEntity
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<BuildingDto> listBuildings(BuildingDto building,UserDto user, ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select id, name, alias, address, order_count, status from mf_building where 1 = 1");

		if(null != building.getDep_id()){
			//查询出本部门和下级部门
			sql.append(" and id in (select b_id from mf_building_dep where dep_id in (select id from mf_department where super_dep_id = ? or id = ?))");
			params.add(building.getDep_id());
			params.add(building.getDep_id());
		}

		if(null != building.getId()){
			sql.append(" and id = ?");
			params.add(building.getId());
		}
		if(StringUtils.isNotBlank(building.getAlias())){
			sql.append(" and alias like '%"+building.getAlias().trim()+"%'");
		}
		if(StringUtils.isNotBlank(building.getName())){

			sql.append(" and name like '%"+building.getName().trim()+"%'");
		}
		if(null != building.getRegion_id()){
			sql.append(" and region_id = ?");
			params.add(building.getRegion_id());
		}

		if(null != user && null != user.getDep_id()){
			sql.append(" and id in (select b_id from mf_building_dep where dep_id in (select id from mf_department where id = ? or super_dep_id = ?))");
			params.add(user.getDep_id());
			params.add(user.getDep_id());
		}else {
			return null;
		}
		
		sql.append(" order by status,order_count desc");
		List<BuildingDto> buildings = buildingDao.listBuildings(sql.toString(),params.toArray(),pager);

		for(BuildingDto b : buildings){
			Integer b_id = b.getId();
			List<DepartmentEntity> deps = buildingDao.getDepartments(b_id);
			for(DepartmentEntity d : deps){
				if(deps.size()>1){
					b.setDep_name("<span style=\'color:green;\'>"+(null == b.getDep_name() ? "" : b.getDep_name()) + d.getDep_name()+"  "+"</span>");
				}else {
					b.setDep_name((null == b.getDep_name() ? "" : b.getDep_name()) + d.getDep_name()+"  ");
				}
			}
		}

		return buildings;
	}

	/**
	 * @param user 
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	* @Title: addBuilding
	* @Description:
	* @param buildingEntity
	* @return
	* @return List<BuildingEntity>
	* @throws
	*/
	public List<String> addBuilding(BuildingEntity buildingEntity, UserDto user) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<String> messages = ValidateUtil.validate(buildingEntity);
		if(messages.size()>0){
			return messages;
		}
		//验证是否已经存在这个别名
		BuildingEntity building_db = buildingDao.selectBuilding(buildingEntity.getAlias());
		if(null != building_db){
			messages.add("楼宇别名已经存在!");
			return messages;
		}
		//保存楼宇信息
		Integer buildingId = buildingDao.addBuilding(buildingEntity);
		//给楼宇分配部门
		if(null != buildingId && null != user && null != user.getDep_id()){
			buildingDao.setBuildingDepartment(buildingId,user.getDep_id());
		}else {
			throw new SQLException();		}
		return null;
	}

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyBuilding
	* @Description:
	* @param buildingEntity
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyBuilding(BuildingEntity buildingEntity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(buildingEntity);
		if(messages.size()>0 || null == buildingEntity.getId()){
			return messages;
		}
		//验证是否已经存在这个别名
		BuildingEntity building_db = buildingDao.selectBuilding(buildingEntity.getAlias());
		if(null != building_db && !building_db.getId().equals(buildingEntity.getId())){
			messages.add("楼宇别名已经存在!");
			return messages;
		}
		buildingDao.modifyBuilding(buildingEntity);
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: getBuildingDetail
	* @Description:
	* @param id
	* @return
	* @return BuildingEntity
	* @throws
	*/
	public BuildingEntity getBuildingDetail(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return buildingDao.getBuildingDetail(id);
	}

	/**
	 * @throws SQLException
	* @Title: deleteBuilding
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteBuilding(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_building set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		buildingDao.deleteBuilding(sql.toString());
		return messages;
	}

	/**
	 * @throws SQLException
	* @Title: listBuildings_dep
	* @Description:
	* @param dep_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listBuildings_dep(Integer dep_id) throws SQLException {
		if(null == dep_id){
			return null;
		}

		return buildingDao.listBuildings_dep(dep_id);
	}

	/**
	 * @throws SQLException
	 * @throws NumberFormatException
	* @Title: setBuildingDelivery
	* @Description:
	* @param ids
	* @param restaurant_ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> setBuildingRestaurants(Integer building_id, String restaurant_ids) throws NumberFormatException, SQLException {
		List<String> messages = null;
		if(null == building_id || StringUtils.isBlank(restaurant_ids)){
			messages = new LinkedList<String>();
			messages.add("楼宇ID和送达店铺的ID不能为空！");
			return messages;
		}
		//先清除楼宇的送达店铺信息
		buildingDao.clearBuilding_Restaurants(building_id);
		//设置楼宇的送达店铺信息
		for(String restaurant_id : restaurant_ids.split(",")){
			buildingDao.setBuildingRestaurants(building_id,Integer.parseInt(restaurant_id));
		}
		return null;
	}

}
