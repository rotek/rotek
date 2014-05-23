/**
* @FileName: DepartmentDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-27 上午10:57:52
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.dto.DepartmentDto;
import com.rotek.entity.DepartmentEntity;
import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;

/**
 * @ClassName: DepartmentDao
 * @Description: 部门dao
 * @author chenwenpeng
 * @date 2013-6-27 上午10:57:52
 *
 */
@Repository
public class DepartmentDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listDepartments
	* @Description:
	* @param @param department
	* @param @param array
	* @param @param pager
	* @param @return
	* @return List<ManagerEntity>
	* @throws
	*/
	public List<DepartmentDto> listDepartments(String sql,Object[] parameters, ListPager pager) throws SQLException {

		return this.selectPage(sql, parameters, DepartmentDto.class, pager);
	}

	/**
	 * @throws SQLException
	* @Title: listDepartments_s
	* @Description:
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listDepartments_s() throws SQLException {

		String sql = "select id,dep_name from mf_department order by sort desc";
		return this.executeQuery(sql, null);
	}

	/**
	 * @throws SQLException
	* @Title: listDepartments_s
	* @Description:
	* @param @param super_dep_id
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listDepartments_s(Integer super_dep_id) throws SQLException {

		String sql = "select id,dep_name from mf_department where status = 1 and super_dep_id = ? order by sort desc";
		return this.executeQuery(sql, new Integer[]{super_dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: addDepartment
	* @Description:
	* @param @param department
	* @return void
	* @throws
	*/
	public void addDepartment(DepartmentEntity department) throws SQLException {

		this.insert(department);
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

		String sql = "select id, dep_name, super_dep_id, memo, sort, status from mf_department where id = ?";

		return this.selectOne(sql,new Integer[]{id},DepartmentDto.class);
	}

	/**
	 * @throws SQLException
	* @Title: modifyDepartment
	* @Description:
	* @param @param department
	* @return void
	* @throws
	*/
	public void modifyDepartment(DepartmentEntity department) throws SQLException {

		this.update(department);
	}

	/**
	 * @throws SQLException
	* @Title: deleteDepartment
	* @Description:
	* @param @param string
	* @return void
	* @throws
	*/
	public void deleteDepartment(String sql) throws SQLException {

		this.executeUpdate(sql);
	}


	/**
	 * @throws SQLException
	* @Title: getSuperDepId
	* @Description: 根据部门id 获取上级部门的id
	* @param @param dep_id
	* @param @return
	* @return Integer
	* @throws
	*/
	public Integer getSuperDepId(Integer dep_id) throws SQLException {
		String sql = "select super_dep_id from mf_department where id = ?";
		return this.executeQueryForInt(sql, new Integer[]{dep_id}).get(0);
	}

	/**
	 * @throws SQLException
	* @Title: listDeliverers_s
	* @Description:
	* @param @param sql
	* @param @param object
	* @param @return
	* @return List<Integer>
	* @throws
	*/
	public List<Integer> listDeliverers_s(String sql, Integer dep_id) throws SQLException {

		return this.executeQueryForInt(sql, new Integer[]{dep_id,dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: updateDeliverers
	* @Description:
	* @param @param dep_id
	* @param @param deliver_id
	* @return void
	* @throws
	*/
	public void updateDeliverers(Integer dep_id, Integer deliverer_id) throws SQLException {

		String sql = "insert into mf_deliverer_dep values(null,?,?)";
		this.executeUpdate(sql, new Integer[]{deliverer_id,dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: clearDep_Deliverers
	* @Description:
	* @param @param dep_id
	* @return void
	* @throws
	*/
	public void clearDep_Deliverers(Integer dep_id) throws SQLException {

		String sql = "delete from mf_deliverer_dep where dep_id = ?";
		this.executeUpdate(sql, new Integer[]{dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: listBuildings_dep
	* @Description:
	* @param sql
	* @param object
	* @return
	* @return List<Integer>
	* @throws
	*/
	public List<Integer> listBuildings_dep(String sql, Integer dep_id) throws SQLException {

		return this.executeQueryForInt(sql, new Integer[]{dep_id,dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: clearDep_Buildings
	* @Description:
	* @param dep_id
	* @return void
	* @throws
	*/
	public void clearDep_Buildings(Integer dep_id) throws SQLException {

		String sql = "delete from mf_building_dep where dep_id = ?";
		this.executeUpdate(sql, new Integer[]{dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: updateBuildings
	* @Description:
	* @param dep_id
	* @param building_id
	* @return void
	* @throws
	*/
	public void updateBuildings(Integer dep_id, Integer building_id) throws SQLException {

		String sql = "insert into mf_building_dep values(null,?,?)";
		this.executeUpdate(sql,new Integer[]{building_id,dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurants_dep
	* @Description:
	* @param sql
	* @param dep_id
	* @return
	* @return List<Integer>
	* @throws
	*/
	public List<Integer> listRestaurants_dep(String sql, Integer dep_id) throws SQLException {

		return this.executeQueryForInt(sql, new Integer[]{dep_id,dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: clearDep_Restaurants
	* @Description:
	* @param dep_id
	* @return void
	* @throws
	*/
	public void clearDep_Restaurants(Integer dep_id) throws SQLException {

		String sql = "delete from mf_dep_restaurant where dep_id = ?";
		this.executeUpdate(sql, new Integer[]{dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: updateRestaurants
	* @Description:
	* @param dep_id
	* @param restaurant_id
	* @return void
	* @throws
	*/
	public void updateRestaurants(Integer dep_id, Integer restaurant_id) throws SQLException {

		String sql = "insert into mf_dep_restaurant values(null,?,?)";
		this.executeUpdate(sql,new Integer[]{dep_id,restaurant_id});
	}
}
