/**
* @FileName: OrderDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-26 下午04:56:01
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.dto.OrderDto;
import com.rotek.entity.DelivererEntity;
import com.rotek.entity.OrderEntity;
import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;

/**
 * @ClassName: OrderDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-26 下午04:56:01
 *
 */
@Repository
public class OrderDao extends BaseDaoImpl{

	/**
	 * @param pager
	 * @throws SQLException
	* @Title: listOrders
	* @Description:
	* @param string
	* @param array
	* @return
	* @return List<OrderDto>
	* @throws
	*/
	public List<OrderDto> listOrders(String sql, Object[] parameters, ListPager pager) throws SQLException {


		return this.selectPage(sql, parameters, OrderDto.class,pager);
	}

	/**
	 * @throws SQLException
	* @Title: addOrder
	* @Description:
	* @param order
	* @return
	* @return Integer
	* @throws
	*/
	public Integer addOrder(OrderEntity order) throws SQLException {

		return this.insert_pk(order);
	}

	/**
	 * @throws SQLException
	 * @param menu_id2
	* @Title: addOrderMenu
	* @Description:
	* @param menu_id
	* @param name
	* @param price
	* @param menu_count
	* @return void
	* @throws
	*/
	public void addOrderMenu(Integer order_id, Integer menu_id, String menu_name, Double menu_price,
			Integer menu_count) throws SQLException {

		String sql = "insert into mf_order_menu values(null,?,?,?,?,?)";
		this.executeUpdate(sql, new Object[]{order_id,menu_id,menu_count,menu_price,menu_name});
	}

	/**
	 * @throws SQLException
	* @Title: modifyOrderStatus
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void modifyOrderStatus(String sql) throws SQLException {

		this.executeUpdate(sql);
	}

	/**
	 * @throws SQLException
	* @Title: deleteOrder
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteOrder(String sql) throws SQLException {

		this.executeUpdate(sql);
	}

	/**
	 * @throws SQLException
	* @Title: getOrderDetail
	* @Description:
	* @param order_id
	* @return
	* @return OrderDto
	* @throws
	*/
	public OrderDto getOrderDetail(Integer order_id) throws SQLException {

		String sql = "select o.id, o.rest_id, o.building_id, o.phone, o.address, o.remark, o.carriage, o.totalPrice, o.ordered_time, o.user_id, o.type, o.status,r.name rest_name,r.telephone rest_phone ,r.deli_type,r.send_type,u.nick_name user_name from mf_order o left join mf_restaurant r on o.rest_id = r.id left join mf_user u on o.user_id = u.id where o.id = ?";

		return this.selectOne(sql, new Integer[]{order_id}, OrderDto.class);
	}

	/**
	 * @throws SQLException
	* @Title: getDelivererByOrderId
	* @Description:
	* @param order_id
	* @return
	* @return DelivererEntity
	* @throws
	*/
	public DelivererEntity getDeliverer(OrderEntity order) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select id,realname,telephone,send_type from mf_deliverer");
		sql.append(" where id = (select deliverer_id from mf_rest_deliverer_building where rest_id = ? or (rest_id = ? and building_id = ?)");
		sql.append(" order by sort desc limit 1)");

		return this.selectOne(sql.toString(), new Integer[]{order.getRest_id(),order.getRest_id(),order.getBuilding_id()}, DelivererEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: listMenus
	* @Description:
	* @param order_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listMenus(Integer order_id) throws SQLException {

		String sql = "select menu_id,count,price,name from mf_order_menu where order_id = ?";
		return this.executeQuery(sql, new Integer[]{order_id});
	}

	/**
	 * @throws SQLException 
	* @Title: updateOrderStatus
	* @Description: 更新订单状态
	* @param @param id
	* @param @param faild 
	* @return void 
	* @throws 
	*/ 
	public void updateOrderStatus(int orderId, int status) throws SQLException {
		String sql = "update mf_order set status = ? where id = ?";
		this.executeUpdate(sql, new Integer[]{status,orderId});
	}
}
