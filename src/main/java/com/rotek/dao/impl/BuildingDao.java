/**
* @FileName: BuildingDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-4 上午09:35:27
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.dto.BuildingDto;
import com.rotek.entity.BuildingEntity;
import com.rotek.entity.DepartmentEntity;
import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;

@Repository
public class BuildingDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listBuildings_s
	* @Description:
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listBuildings_s(String sql) throws SQLException {

		return this.executeQuery(sql, null);
	}

	/**
	 * @throws SQLException
	* @Title: listBuildings
	* @Description:
	* @param string
	* @param array
	* @param pager
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<BuildingDto> listBuildings(String sql,
			Object[] parameters, ListPager pager) throws SQLException {

		return this.selectPage(sql, parameters, BuildingDto.class, pager);
	}

	/**
	 * @throws SQLException
	* @Title: addBuilding
	* @Description:
	* @param buildingEntity
	* @return void
	* @throws
	*/
	public Integer addBuilding(BuildingEntity buildingEntity) throws SQLException {

		return this.insert_pk(buildingEntity);
	}

	/**
	 * @throws SQLException
	* @Title: selectBuilding
	* @Description:
	* @param alias
	* @return
	* @return BuildingEntity
	* @throws
	*/
	public BuildingEntity selectBuilding(String alias) throws SQLException {
		String sql = "select id from mf_building where alias = ?";
		return this.selectOne(sql, new String[]{alias},BuildingEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: modifyBuilding
	* @Description:
	* @param buildingEntity
	* @return void
	* @throws
	*/
	public void modifyBuilding(BuildingEntity buildingEntity) throws SQLException {

		this.update(buildingEntity);
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
		String sql = "select id, name, alias, address, order_count, region_id, status from mf_building where id = ?";
		return this.selectOne(sql, new Integer[]{id}, BuildingEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: deleteBuilding
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteBuilding(String sql) throws SQLException{

		this.executeUpdate(sql) ;
	}

	/**
	 * @throws SQLException
	* @Title: getDepartments
	* @Description:
	* @param b_id
	* @return
	* @return List<DepartmentEntity>
	* @throws
	*/
	public List<DepartmentEntity> getDepartments(Integer b_id) throws SQLException {

		String sql = "select id,dep_name from mf_department where id in (select dep_id from mf_building_dep where b_id = ?)";
		return this.selectAll(sql, new Integer[]{b_id}, DepartmentEntity.class);
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

		String sql = "select id,name from mf_building where id in (select b_id from mf_building_dep where dep_id in (select id from mf_department where super_dep_id = ? or id = ?))";
		return this.executeQuery(sql, new Integer[]{dep_id,dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: clearBuildingDelivery
	* @Description:
	* @param parseInt
	* @return void
	* @throws
	*/
	public void clearBuilding_Restaurants(int building_id) throws SQLException {

		String sql = "delete from mf_build_restaurant where build_id = ?";
		this.executeUpdate(sql, new Integer[]{building_id});
	}

	/**
	 * @throws SQLException
	* @Title: setRestaurantsDelivery
	* @Description:
	* @param building_id
	* @param parseInt
	* @return void
	* @throws
	*/
	public void setBuildingRestaurants(Integer building_id, int restaurant_id) throws SQLException {

		String sql = "insert into mf_build_restaurant values(null,?,?)";
		this.executeUpdate(sql, new Integer[]{building_id,restaurant_id});
	}

	/**
	 * @throws SQLException 
	* @Title: setBuildingDepartment
	* @Description: 
	* @param @param buildingId
	* @param @param dep_id 
	* @return void 
	* @throws 
	*/ 
	public void setBuildingDepartment(Integer buildingId, Integer dep_id) throws SQLException {
		
		String sql = "insert into mf_building_dep values(null,?,?)";
		this.executeUpdate(sql, new Integer[]{buildingId,dep_id});
	}
}
