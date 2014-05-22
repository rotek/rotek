/**
* @FileName: RestaurantDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-4 上午11:53:52
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.constant.DataStatus;
import com.rotek.dto.RestaurantDto;
import com.rotek.entity.DepartmentEntity;
import com.rotek.entity.RestaurantDetailEntity;
import com.rotek.entity.RestaurantEntity;
import com.rotek.platform.persistence.dao.BaseDaoImpl;
import com.rotek.platform.util.ListPager;

/**
 * @ClassName: RestaurantDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-4 上午11:53:52
 *
 */
@Repository
public class RestaurantDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listRestaurants_s
	* @Description:
	* @param string
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurants_s(String sql) throws SQLException {

		return this.executeQuery(sql, null);
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurants
	* @Description:
	* @param string
	* @param array
	* @param pager
	* @return
	* @return List<RestaurantDto>
	* @throws
	*/
	public List<RestaurantDto> listRestaurants(String sql, Object[] params,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, params, RestaurantDto.class, pager);
	}

	/**
	 * @throws SQLException
	* @Title: listDeparts
	* @Description:
	* @param rest_id
	* @return
	* @return List<DepartmentEntity>
	* @throws
	*/
	public List<DepartmentEntity> listDeparts(Integer rest_id) throws SQLException {

		String sql = "select id,dep_name from mf_department where id in (select dep_id from mf_dep_restaurant where rest_id = ?)";
		return this.selerotekll(sql, new Integer[]{rest_id}, DepartmentEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurants_sort
	* @Description:
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurants_sort() throws SQLException {
		String sql = "select sort-1 sort,name from mf_restaurant order by sort desc";
		return this.executeQuery(sql, null);
	}

	/**
	 * @return
	 * @throws SQLException
	* @Title: addRestaurant
	* @Description:
	* @param restaurant
	* @return void
	* @throws
	*/
	public Integer addRestaurant(RestaurantEntity restaurant) throws SQLException {

		return this.insert_pk(restaurant);
	}

	/**
	 * @throws SQLException
	* @Title: addRestaurantDetail
	* @Description:
	* @param rest_detail
	* @return void
	* @throws
	*/
	public void addRestaurantDetail(RestaurantDetailEntity rest_detail) throws SQLException {

		this.insert(rest_detail);
	}


	/**
	 * @throws SQLException
	* @Title: updateRestaurantPic
	* @Description:
	* @param pic_name
	* @param restId
	* @return void
	* @throws
	*/
	public void updateRestaurantPic(String pic_name, Integer restId) throws SQLException {

		String sql = "update mf_restaurant set pic = ? where id = ?";
		this.executeUpdate(sql, new Object[]{pic_name,restId});
	}

	/**
	 * @throws SQLException
	* @Title: getRestaurantDetail
	* @Description:
	* @param restaurant
	* @return void
	* @throws
	*/
	public RestaurantDto getRestaurantDetail(Integer id) throws SQLException {

		String sql = "select r.id, r.name, r.alias, r.pic, r.join_date, r.address, r.region_id, r.telephone, r.notice, r.deli_count, r.deli_price, r.carriage,r.deli_type, r.send_type, r.sort, r.status from mf_restaurant r left join mf_rest_detail d on r.id = d.rest_id where r. id = ?";
		return this.selectOne(sql, new Integer[]{id}, RestaurantDto.class);
	}

	/**
	 * @throws SQLException
	 * @Title: modifyRestaurant
	 * @Description:
	 * @param restaurant
	 * @return void
	 * @throws
	 */
	public void modifyRestaurant(RestaurantEntity restaurant) throws SQLException {

		this.update(restaurant);
	}

	/**
	* @Title: listDeliverers
	* @Description:
	* @param rest_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listDeliverers(Integer rest_id) throws SQLException {

		String sql = "select distinct realname from mf_deliverer where id in (select deliverer_id from mf_rest_deliverer_building where rest_id = ?)";
		return this.executeQuery(sql, new Integer[]{rest_id});
	}

	/**
	 * @throws SQLException
	* @Title: setReserveTime
	* @Description:
	* @param sql
	* @param dates
	* @return void
	* @throws
	*/
	public void setReserveTime(String sql, Object[] params) throws SQLException {

		this.executeUpdate(sql, params);
	}

	/**
	 * @throws SQLException
	* @Title: clearRestDeliverer
	* @Description:
	* @param ids
	* @param region
	* @return void
	* @throws
	*/
	public void clearRestDeliverer(String ids) throws SQLException {
		String sql = "delete from mf_rest_deliverer_building where rest_id in ("+ids+")";
		this.executeUpdate(sql);
	}


	/**
	 * @throws SQLException
	* @Title: setReserveDeliverer
	* @Description:
	* @param rest_id
	* @param deliverer_id
	* @param region
	* @return void
	* @throws
	*/
	public void setRestaurantDeliverer(Integer rest_id, Integer deliverer_id) throws SQLException {

		String sql = "insert into mf_rest_deliverer_building values(null,?,0,?,0)";
		this.executeUpdate(sql, new Integer[]{rest_id,deliverer_id});
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurantType
	* @Description:
	* @param id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurantType(Integer id) throws SQLException {

		String sql = "select cate_id id from mf_rest_cate where rest_id = ?";
		return this.executeQuery(sql, new Integer[]{id});
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurantType_all
	* @Description:
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurantType_all() throws SQLException {

		String sql = "select id,cate_name name from mf_category";
		return this.executeQuery(sql, null);
	}

	/**
	 * @throws SQLException
	* @Title: clearRestaurantType
	* @Description:
	* @param id
	* @return void
	* @throws
	*/
	public void clearRestaurantType(Integer id) throws SQLException {

		String sql = "delete from mf_rest_cate where rest_id = ?";
		this.executeUpdate(sql,new Integer[]{id});
	}

	/**
	 * @throws SQLException
	* @Title: setRestaurantType
	* @Description:
	* @param id
	* @param parseInt
	* @return void
	* @throws
	*/
	public void setRestaurantType(Integer id, int cate_id) throws SQLException {

		String sql = "insert into mf_rest_cate values(null,?,?)";
		this.executeUpdate(sql, new Integer[]{id,cate_id});
	}

	/**
	 * @throws SQLException
	* @Title: clearRestaurantSettlement
	* @Description:
	* @param ids
	* @return void
	* @throws
	*/
	public void clearRestaurantSettlement(String ids) throws SQLException {

		String sql = "delete from mf_settlement where rest_id in ("+ids+")";
		this.executeUpdate(sql);
	}

	/**
	 * @throws SQLException
	* @Title: setRestaurantSettlement
	* @Description:
	* @param parseInt
	* @param setttlement_type
	* @param discount
	* @param period
	* @return void
	* @throws
	*/
	public void setRestaurantSettlement(int restId, Integer setttlement_type,
			Double discount, Integer period) throws SQLException {

		String sql = "insert into mf_settlement values(null,?,?,?,?)";
		this.executeUpdate(sql, new Object[]{restId,setttlement_type,discount,period});
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurants_dep
	* @Description:
	* @param dep_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurants_dep(String sql ,Integer[] params) throws SQLException {

		return this.executeQuery(sql, params);
	}

	/**
	 * @throws SQLException
	* @Title: getRestaurantByAlias
	* @Description:
	* @param alias
	* @return
	* @return Object
	* @throws
	*/
	public RestaurantEntity getRestaurantByAlias(String alias) throws SQLException {

		String sql = "select id from mf_restaurant where alias = ?";
		return this.selectOne(sql,new String[]{alias},RestaurantEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: getRestaurantDetail_other
	* @Description:
	* @param id
	* @return
	* @return RestaurantDto
	* @throws
	*/
	public RestaurantDto getRestaurantDetail_other(Integer id) throws SQLException {
		String sql = "select r.id,r.name, d.deli_interval, d.food_count, d.order_count, d.recommend_count, d.taste, d.speed, d.attitude, d.open_time_am, d.open_time_pm, d.close_time_am, d.close_time_pm from mf_rest_detail d,mf_restaurant r  where d.rest_id = r.id and r.id = ?";
		return this.selectOne(sql, new Integer[]{id}, RestaurantDto.class);
	}

	/**
	 * @throws SQLException
	* @Title: setRestaurantDeliverer
	* @Description:添加店铺某一个楼宇下面的送餐人
	* @param rest_id
	* @param building_id
	* @param deliverer_id
	* @param rank
	* @return void
	* @throws
	*/
	public void setRestaurantDeliverer(Integer rest_id, Integer building_id,
			Integer deliverer_id, Integer rank) throws SQLException {
		String sql ="insert into mf_rest_deliverer_building values(null,?,?,?,?)";
		this.executeUpdate(sql,new Integer[]{rest_id,building_id,deliverer_id,rank});
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurant_menus
	* @Description:
	* @param rest_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurant_menus(Integer rest_id) throws SQLException {

		String sql = "select id,name,price from mf_menu_qd where rest_id = ? and status = ?";
		return this.executeQuery(sql, new Integer[]{rest_id,DataStatus.ENABLED});
	}

	/**
	 * @param depId
	 * @throws SQLException
	* @Title: listRestaurants_building
	* @Description:
	* @param buildingId
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurants_building_dep(Integer buildingId, Integer depId) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select r.id,r.name from mf_restaurant r left join mf_build_restaurant br on r.id = br.rest_id");
		sql.append(" where br.build_id = ? and r.id in ");
		sql.append(" (select rest_id from mf_dep_restaurant where dep_id in ");
		sql.append(" (select id from mf_department where super_dep_id = ? or id = ?))");

		return this.executeQuery(sql.toString(), new Integer[]{buildingId,depId,depId});
	}

	/**
	 * @throws SQLException 
	* @Title: modifyStatus
	* @Description: 
	* @param @param sql
	* @param @param params 
	* @return void 
	* @throws 
	*/ 
	public void modifyStatus(String sql, Integer[] params) throws SQLException {
		this.executeUpdate(sql, params);
	}
	/**
	* @Title: modifyStatus
	* @Description: 
	* @param @param rest_id
	* @param @param status
	* @param @throws SQLException 
	* @return void 
	* @throws 
	*/ 
	public void modifyStatus(Integer rest_id, Integer status) throws SQLException {
		String sql = "update mf_restaurant set status = ? where id = ?";
		this.executeUpdate(sql, new Integer[]{status,rest_id});
	}

	/**
	 * @throws SQLException 
	* @Title: getRestIds_dep
	* @Description: 
	* @param @param dep_id
	* @param @return 
	* @return List<Integer> 
	* @throws 
	*/ 
	public List<Integer> getRestIds_dep(Integer dep_id) throws SQLException {
		
		String sql = "select rest_id from mf_dep_restaurant where dep_id in (select id from mf_department where id = ? or super_dep_id = ?)";
		return this.executeQueryForInt(sql, new Integer[]{dep_id,dep_id});
	}

	/**
	 * @throws SQLException 
	* @Title: setRestaurantDepartment
	* @Description: 为店铺设置管理部门
	* @param @param restId
	* @param @param depId 
	* @return void 
	* @throws 
	*/ 
	public void setRestaurantDepartment(Integer restId, Integer depId) throws SQLException {
		
		String sql = "insert into mf_dep_restaurant values(null,?,?)";
		this.executeUpdate(sql, new Integer[]{depId,restId});
	}

	/**
	 * @throws SQLException 
	* @Title: updateRestContent
	* @Description: 
	* @param @param id
	* @param @param content 
	* @return void 
	* @throws 
	*/ 
	public void updateRestContent(Integer id, String content) throws SQLException {
		
		String sql = "update mf_rest_detail set content = ? where rest_id = ?";
		this.executeUpdate(sql, new Object[]{content,id});
	}
}
