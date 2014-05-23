/**
* @FileName: TaskService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-13 上午11:37:40
* @version V1.0
*/
package com.rotek.service.impl;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.constant.OrderDeliverType;
import com.rotek.constant.OrderMessageSendType;
import com.rotek.constant.OrderStatus;
import com.rotek.constant.RestaurantStatus;
import com.rotek.dao.impl.OrderDao;
import com.rotek.dao.impl.RestaurantDao;
import com.rotek.dao.impl.TaskDao;
import com.rotek.dto.OrderDto;
import com.rotek.entity.DelivererEntity;
import com.rotek.entity.TaskEntity;
import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.rotek.util.ShortMessageUtils;

/**
 * @ClassName: TaskService
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-13 上午11:37:40
 *
 */
@Service
public class TaskService {

	@Autowired
	private TaskDao taskDao;
	@Autowired
	private OrderDao orderDao;
	@Autowired
	private RestaurantDao restaurantDao;

	/**
	 * @throws SQLException
	 * @param pager
	* @Title: listTasks
	* @Description:
	* @param task
	* @param start_time
	* @param end_time
	* @return
	* @return Object
	* @throws
	*/
	public List<TaskEntity> listTasks(TaskEntity task, Date start_time, Date end_time, ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();

		sql.append("select id, target_id, time, type from mf_task where 1 =1");

		if(null != task.getTarget_id()){
			sql.append(" and target_id = ?");
			params.add(task.getTarget_id());
		}

		if(null != task.getType()){
				sql.append(" and type = ?");
				params.add(task.getType());
		}

		if(null != start_time){
			sql.append(" and time >= ?");
			params.add(start_time);
		}
		if(null != end_time){
			sql.append(" and time <= ?");
			params.add(end_time);
		}

		sql.append(" order by id desc");

		return taskDao.listTasks(sql.toString(),params.toArray(),pager);
	}

	/**
	 * @throws SQLException
	* @Title: deleteTask
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteTask(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("delete from mf_task");
		sql.append(" where id in ("+ids.trim()+")");
		taskDao.deleteTask(sql.toString());
		return messages;
	}

	/**
	* @Title: getTask
	* @Description: 获取需要执行的task
	* @param @param time
	* @param @return
	* @param @throws SQLException 
	* @return List<TaskEntity> 
	* @throws 
	*/ 
	public List<TaskEntity> getTask(Date time) throws SQLException {
		
		return taskDao.getTask(time);
	}

	/**
	 * @throws UnsupportedEncodingException 
	 * @throws SQLException 
	* @Title: sendOrder
	* @Description: 发送预定的订单
	* @param @param order_id 
	* @return void 
	* @throws 
	*/ 
	public void sendOrder(Integer order_id) throws SQLException, UnsupportedEncodingException {
		OrderDto order = orderDao.getOrderDetail(order_id);
		//如果订单状态还是预定中，那么就执行订单下发操作
		if(null != order && null != order.getStatus() && OrderStatus.RESERVE == order.getStatus()){
			//要发送的订单内容(只有菜单)
			StringBuilder menu_message = new StringBuilder();
			//要发送的订单内容(菜单和收餐用户信息)
			StringBuilder order_message = new StringBuilder();
			
			List<Map<String,Object>> menus = orderDao.listMenus(order_id);
			DelivererEntity deliverer = orderDao.getDeliverer(order);
			
			//获取菜单和订单信息
			this.generateMessage(menu_message,order_message,order,menus);
			//发送菜单和订单信息
			boolean flag = this.sendOrderMessage(order, menu_message, order_message,deliverer);
			//更新订单状态
			if(false == flag){
				//失败
				orderDao.updateOrderStatus(order.getId(),OrderStatus.FAILD_ADVICE);
			}else {
				//成功，修改订单为烹饪中
				orderDao.updateOrderStatus(order.getId(),OrderStatus.COOKING);
			}
		}
	}

	/**
	* @Title: generateMessage
	* @Description: 获取要发送的短信信息
	* @param @param menu_message
	* @param @param order_message
	* @param @param order
	* @param @param menus 
	* @return void 
	* @throws 
	*/ 
	private void generateMessage(StringBuilder menu_message,StringBuilder order_message, OrderDto order,List<Map<String, Object>> menus) {
		menu_message.append("菜单:");
		int i = 1;
		//保存菜单
		for(Map<String,Object> menu : menus){
			Integer count = (Integer) menu.get("count");
			Double price = (Double) menu.get("price");
			count = (null == count) ? 0 : count;
			price = (null == price) ? 1 : price;
			
			menu_message.append("["+i+"]");
			menu_message.append(menu.get("name")); 
			menu_message.append("*"); 
			menu_message.append(menu.get("count")); 
			menu_message.append("="); 
			menu_message.append(count * price);
			i++;
		}
		menu_message.append("|订单总计:").append("￥").append(order.getTotalPrice()).append("+").append(order.getCarriage());
		menu_message.append("|备注:");
		menu_message.append(order.getRemark());
		menu_message.append("|烹饪方:");
		menu_message.append(order.getRest_name());
		
		//由谁配送
		Integer deli_type = order.getDeli_type();
		
		order_message.append(menu_message);
		order_message.append("|配送方:");
		order_message.append(OrderDeliverType.ORDER_DELIVER_OUR == deli_type ? SystemGlobals.getPreference("company.name") : order.getRest_name());
		order_message.append("|配送地址:");
		order_message.append(order.getAddress());
		order_message.append("|收餐电话：");
		order_message.append(order.getPhone());
		}
	
	
	/**
	* @Title: sendOrderMessage
	* @Description: 
	* @param @param order
	* @param @param menu_message
	* @param @param order_message
	* @param @param deliverer
	* @param @return
	* @param @throws UnsupportedEncodingException
	* @param @throws SQLException 
	* @return boolean 
	* @throws 
	*/ 
	private boolean sendOrderMessage(OrderDto order,StringBuilder menu_message,StringBuilder order_message, DelivererEntity deliverer) throws UnsupportedEncodingException, SQLException {
		Integer deli_type = order.getDeli_type();
		//由店铺配送
		if(OrderDeliverType.ORDER_DELIVER_RESTAURANT == deli_type){
			//短信发送全部信息
			return this.sendToRestaurant(order,order_message);
		//由我们配送
		}else {
			//短信发送店铺简要信息
			if(false == this.sendToRestaurant(order,menu_message)){
				return false;
			}
			//店铺的配送员确定通知方式然后发送订单
			if(null != deliverer && null != deliverer.getSend_type() && deliverer.getSend_type() == OrderMessageSendType.SHORT_MESSAGE){
				String phone_deliverer = deliverer.getTelephone();
				//订单发送失败
				if(false == ShortMessageUtils.sendMessage(phone_deliverer, order_message.toString())){
					return false;
				}
			}else if(null == deliverer || null == deliverer.getSend_type()) {
				//如果没有找到送餐员查找默认的收餐电话
				 String phone_deliverer = SystemGlobals.getPreference("default.deliverer.telephone","15801377079");
				//订单发送失败
				if(false == ShortMessageUtils.sendMessage(phone_deliverer, order_message.toString())){
					return false;
				}
			}
			
			return true;
		}
	}
	
	/**
	* @Title: sendToRestaurant
	* @Description: 发送订单给店铺
	* @param @param restaurant
	* @param @param message
	* @param @return
	* @param @throws UnsupportedEncodingException 
	* @return boolean 
	* @throws 
	*/ 
	private boolean sendToRestaurant(OrderDto order,StringBuilder message) throws UnsupportedEncodingException {
		String phone_rest = order.getRest_phone();
			//短信发送
			if(null != order.getSend_type() && OrderMessageSendType.SHORT_MESSAGE == order.getSend_type()){
				if(StringUtils.isNotEmpty(phone_rest)){
					//给店铺发送信息
					return ShortMessageUtils.sendMessage(phone_rest, message.toString());
				}
			}
			return false;
	}

	/**
	 * @throws SQLException 
	* @Title: closeRest
	* @Description: 
	* @param @param target_id 
	* @return void 
	* @throws 
	*/ 
	public void closeRest(Integer restaurant_id) throws SQLException {
		
		restaurantDao.modifyStatus(restaurant_id,RestaurantStatus.CLOSED);
	}
	
	/**
	 * @throws SQLException 
	* @Title: openRest
	* @Description: 
	* @param @param target_id 
	* @return void 
	* @throws 
	*/ 
	public void openRest(Integer restaurant_id) throws SQLException {
		
		restaurantDao.modifyStatus(restaurant_id,RestaurantStatus.OPENED);
	}

	/**
	 * @throws SQLException 
	* @Title: putoffTask
	* @Description: 推迟任务
	* @param @param task_id 
	* @return void 
	* @throws 
	*/ 
	public void putoffTask(TaskEntity task) throws SQLException {
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(task.getTime());
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		task.setTime(calendar.getTime());
		
		taskDao.putoffTask(task);
	}
}
