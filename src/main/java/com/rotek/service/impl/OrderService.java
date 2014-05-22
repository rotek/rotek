/**
 * @FileName: OrderService.java
 * @Package com.rotek.service.impl
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-7-26 下午04:55:35
 * @version V1.0
 */
package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.constant.DataStatus;
import com.rotek.constant.OrderStatus;
import com.rotek.dao.impl.OrderDao;
import com.rotek.dao.impl.RestMenuDao;
import com.rotek.dto.OrderDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.DelivererEntity;
import com.rotek.entity.OrderEntity;
import com.rotek.entity.RestMenuEntity;
import com.rotek.platform.config.SystemGlobals;
import com.rotek.platform.util.ListPager;
import com.rotek.platform.util.ValidateUtil;
import com.rotek.util.DateUtils;

/**
 * @ClassName: OrderService
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-26 下午04:55:35
 *
 */
@Service
public class OrderService {

	@Autowired
	private OrderDao orderDao;
	@Autowired
	private RestMenuDao restMenuDao;

	/**
	 * @throws SQLException
	 * @param end_time
	 * @param start_time
	 * @Title: listOrders
	 * @Description:
	 * @param order
	 * @param pager
	 * @return
	 * @return Object
	 * @throws
	 */
	public List<OrderDto> listOrders(UserDto user,OrderDto order, ListPager pager,
			Date start_time, Date end_time) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();

		sql.append("select o.id,o.phone, o.address, o.remark, o.carriage, o.totalPrice, o.ordered_time, o.user_id, o.type, o.status,r.name rest_name,r.deli_type,u.nick_name user_name from mf_order o");
		sql.append(" left join mf_restaurant r on o.rest_id = r.id ");
		sql.append(" left join mf_user u on o.user_id = u.id");
		sql.append(" where r.id in (select rest_id from mf_dep_restaurant where dep_id in (select id from mf_department where super_dep_id = ? or id = ?))");

		params.add(user.getDep_id());
		params.add(user.getDep_id());

		if(SystemGlobals.getIntPreference("super_dep_id", 0) != user.getDep_id()){
			sql.append(" and o.status <> ?");
			params.add(OrderStatus.INVALID);
		}

		if (null == start_time && null == end_time) {
			sql.append(" and o.ordered_time >= ?");
			params.add(DateUtils.getDateForQuery(new Date()));
		}
		if(null != order.getId()){
			sql.append(" and o.id = ?");
			params.add(order.getId());
		}
		if(StringUtils.isNotEmpty(order.getRest_name())){

			sql.append(" and r.name like '%").append(order.getRest_name()).append("%'");
		}
		if(StringUtils.isNotEmpty(order.getUser_name())){

			sql.append(" and u.nick_name like '%").append(order.getUser_name()).append("%'");
		}
		if(StringUtils.isNotEmpty(order.getPhone())){
			sql.append(" and o.phone like '%").append(order.getPhone()).append("%'");
		}
		if(null != order.getBuilding_id()){
			sql.append(" and o.building_id = ?");
			params.add(order.getBuilding_id());
		}
		if(null != order.getType()){
			sql.append(" and o.type = ?");
			params.add(order.getType());
		}
		if(null != order.getStatus()){
			sql.append(" and o.status = ?");
			params.add(order.getStatus());
		}

		if (null != start_time) {
			sql.append(" and o.ordered_time >=?");
			params.add(DateUtils.getDateForQuery(start_time));
		}
		if (null != end_time) {
			sql.append(" and o.ordered_time <= ?");
			params.add(DateUtils.getDateForQuery(end_time));
		}
		sql.append(" order by o.id desc");

		List<OrderDto> orders = orderDao.listOrders(sql.toString(),
				params.toArray(), pager);
		return orders;
	}
	/**
	* @Title: listOrders
	* @Description:列出要导出的订单
	* @param user
	* @param order
	* @param pager
	* @param start_time
	* @param end_time
	* @param ids
	* @return
	* @throws SQLException
	* @return List<OrderDto>
	* @throws
	*/
	public List<OrderDto> listOrders(UserDto user,OrderDto order, ListPager pager,
			Date start_time, Date end_time,String ids) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();

		sql.append("select o.id,o.rest_id,o.building_id, o.phone, o.address, o.remark, o.carriage, o.totalPrice, o.ordered_time, o.user_id, o.type, o.status,r.name rest_name,r.telephone rest_phone,r.deli_type,u.nick_name user_name from mf_order o");
		sql.append(" left join mf_restaurant r on o.rest_id = r.id ");
		sql.append(" left join mf_user u on o.user_id = u.id");
		sql.append(" where r.id in (select rest_id from mf_dep_restaurant where dep_id in (select id from mf_department where super_dep_id = ? or id = ?))");

		params.add(user.getDep_id());
		params.add(user.getDep_id());

		if(SystemGlobals.getIntPreference("super_dep_id", 0) != user.getDep_id()){
			sql.append(" and o.status <> ?");
			params.add(OrderStatus.INVALID);
		}

		if (null == start_time && null == end_time) {
			sql.append(" and o.ordered_time >= ?");
			params.add(DateUtils.getDateForQuery(new Date()));
		}
		if(null != order.getId()){
			sql.append(" and o.id = ?");
			params.add(order.getId());
		}
		if(StringUtils.isNotEmpty(order.getRest_name())){

			sql.append(" and r.name like '%").append(order.getRest_name()).append("%'");
		}
		if(StringUtils.isNotEmpty(order.getUser_name())){

			sql.append(" and u.nick_name like '%").append(order.getUser_name()).append("%'");
		}
		if(StringUtils.isNotEmpty(order.getPhone())){
			sql.append(" and o.phone like '%").append(order.getPhone()).append("%'");
		}
		if(null != order.getBuilding_id()){
			sql.append(" and o.building_id = ?");
			params.add(order.getBuilding_id());
		}
		if(null != order.getType()){
			sql.append(" and o.type = ?");
			params.add(order.getType());
		}
		if(null != order.getStatus()){
			sql.append(" and o.status = ?");
			params.add(order.getStatus());
		}

		if (null != start_time) {
			sql.append(" and o.ordered_time >=?");
			params.add(DateUtils.getDateForQuery(start_time));
		}
		if (null != end_time) {
			sql.append(" and o.ordered_time <= ?");
			params.add(DateUtils.getDateForQuery(end_time));
		}
		if(StringUtils.isNotBlank(ids)){
			sql.append(" and o.id in (").append(ids).append(")");
		}

		sql.append(" order by o.id desc");

		List<OrderDto> orders = orderDao.listOrders(sql.toString(),
				params.toArray(), pager);
		return orders;
	}

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addOrder
	* @Description:
	* @param order
	* @param menus
	* @param price_spread
	* @param price_total
	* @return void
	* @throws
	*/
	public List<String> addOrder(OrderEntity order, String menus,Double price_total) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {

		List<String> messages = ValidateUtil.validate(order);
		if(messages.size()>0 || null == price_total){
			return messages;
		}

		Integer order_id = orderDao.addOrder(order);

		if(StringUtils.isNotBlank(menus)){
			String[] menu_strs = menus.split(",");
			for(String menu_str : menu_strs){
				Integer menu_id = Integer.parseInt(menu_str.split(";")[0]);
				Integer menu_count = Integer.parseInt(menu_str.split(";")[1]);
				RestMenuEntity menu =restMenuDao.getMenuDetail_s(menu_id);
				if(null != menu && null != order_id){
					orderDao.addOrderMenu(order_id,menu_id,menu.getName(),menu.getPrice(),menu_count);
				}
			}
		}

		return null;
	}

	/**
	 * @throws SQLException
	 * @param status
	* @Title: modifyOrderStatus
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyOrderStatus(String ids, Integer status) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids) || null == status){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}

		StringBuilder sql = new StringBuilder();
		sql.append("update mf_order set status = ").append(status);
		sql.append(" where id in ("+ids.trim()+")");
		orderDao.modifyOrderStatus(sql.toString());
		return messages;
	}

	/**
	* @Title: deleteOrder
	* @Description: 删除订单
	* @param ids
	* @return
	* @throws SQLException
	* @return List<String>
	* @throws
	*/
	public List<String> deleteOrder(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_order set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		orderDao.deleteOrder(sql.toString());
		return messages;
	}

	/**
	 * @throws SQLException
	* @Title: getOrderDetail
	* @Description:
	* @param id
	* @return
	* @return OrderDto
	* @throws
	*/
	public OrderDto getOrderDetail(Integer order_id) throws SQLException {
		if(null == order_id){
			return null;
		}
		OrderDto order = orderDao.getOrderDetail(order_id);
		if(null != order){
			DelivererEntity deliverer = orderDao.getDeliverer(order);
			order.setDeliverer_name(null == deliverer ? SystemGlobals.getPreference("default.deliverer.name") : deliverer.getRealname());
			order.setDeliverer_phone(null == deliverer ? SystemGlobals.getPreference("default.deliverer.telephone") : deliverer.getTelephone());
		}
		 order.setMenus(orderDao.listMenus(order_id));
		return order;
	}

	/**
	 * @throws SQLException
	* @Title: exportOrders
	* @Description:获取导出的订单信息
	* @param user
	* @param order
	* @param pager
	* @param start_time
	* @param end_time
	* @return
	* @return List<OrderDto>
	* @throws
	*/
	public List<OrderDto> exportOrders(UserDto user, OrderDto order,
			ListPager pager, Date start_time, Date end_time,String ids) throws SQLException {

		List<OrderDto> orders = this.listOrders(user, order, pager, start_time, end_time,ids);
		for(OrderDto order_ : orders){
			order_.setMenus(orderDao.listMenus(order_.getId()));

			DelivererEntity deliverer = orderDao.getDeliverer(order_);
			order_.setDeliverer_name(null == deliverer ? SystemGlobals.getPreference("default.deliverer.name") : deliverer.getRealname());
			order_.setDeliverer_phone(null == deliverer ? SystemGlobals.getPreference("default.deliverer.telephone") : deliverer.getTelephone());
		}
		return orders;
	}
}
