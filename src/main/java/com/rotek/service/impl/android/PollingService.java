/**
* @FileName: PollingController.java
* @Package com.rotek.service.impl.android
* @Description: TODO
* @author chenwenpeng
* @date 2013-9-2 上午08:49:39
* @version V1.0
*/
package com.rotek.service.impl.android;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.constant.AndroidStatus;
import com.rotek.dao.impl.android.PollingDao;
import com.rotek.util.DateUtils;


/**
 * @ClassName: PollingController
 * @Description:
 * @author chenwenpeng
 * @date 2013-9-2 上午08:49:39
 *
 */
@Service
public class PollingService {

	@Autowired
	private PollingDao pollingDao;



	/**
	 * @throws SQLException
	 * @throws UnsupportedEncodingException
	* @Title: login_deliver
	* @Description:
	* @param decode
	* @param password
	* @return
	* @return String
	* @throws
	*/
	public Map<String,Object> login_deliver(String username, String password) throws UnsupportedEncodingException, SQLException {

		Map<String,Object> result = new HashMap<String,Object>();

		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			result.put("result", AndroidStatus.FAIL);
			return result;
		}
		username = URLDecoder.decode(username.trim(),"UTF-8");
		password = password.trim();

		Map<String,Object> user_ = pollingDao.login_deliver(username,password);
		if(null != user_){
			result.put("result", AndroidStatus.SUCCESS);
			result.put("deliver_id", user_.get("id"));
		}else {
			result.put("result", AndroidStatus.FAIL);
		}
		return result;
	}
	/**
	 * @throws SQLException
	* @Title: listNewOrders
	* @Description:
	* @param time_last
	* @param deliver_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listNewOrders(Date time_last,Integer manager_id) throws SQLException {

		if(null == time_last){
			time_last = DateUtils.getDateForQuery(new Date());
		}
		List<Integer> restIdList = pollingDao.listRestIdByManagerId(manager_id);
		if(null == restIdList || restIdList.size() <=0){
			return new ArrayList<Map<String,Object>>();
		}
		StringBuilder orderSql = new StringBuilder();
		//找出这个配送员分配的高级接收权限的店铺楼宇和没有被分配高级权限的区域
		orderSql.append("select o.id,o.phone receiver_phone,o.totalPrice menu_price,o.carriage,o.address,o.remark,o.ordered_time,o.status,o.secret,r.name rest_name from mf_order o");
		orderSql.append(" left join mf_restaurant r on o.rest_id = r.id");
		orderSql.append(" where o.ordered_time >= ?");
		orderSql.append(" and o.rest_id in (");
		for(int i= 0;i< restIdList.size();i++){
			orderSql.append(restIdList.get(i));
			if(i < restIdList.size() -1){
				orderSql.append(",");
			}
		}
		
		if(restIdList.size() == 0){
			orderSql.append("0");
		}
		orderSql.append(")");

		List<Map<String,Object>> orderList = pollingDao.listNewOrders(orderSql.toString(),time_last);
		
		for(Map<String,Object> order : orderList){
			Integer orderId = (Integer) order.get("id");
			List<Map<String,Object>> menuList = pollingDao.listMenus(orderId);
			order.put("menu_list", menuList);
		}
		
		return orderList;
	}
	/**
	 * @throws SQLException 
	* @Title: setOrderDelivered
	* @Description: 
	* @param @param order_id 
	* @return void 
	* @throws 
	*/ 
	public String setOrderDelivered(Integer order_id) throws SQLException {
		
		if(null == order_id){
			return AndroidStatus.FAIL;
		}
		pollingDao.setOrderDelivered(order_id);
		return AndroidStatus.SUCCESS_;
	}
}
