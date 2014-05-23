/**
* @FileName: PollingController.java
* @Package com.rotek.dao.impl.android
* @Description: TODO
* @author chenwenpeng
* @date 2013-9-2 上午08:50:08
* @version V1.0
*/
package com.rotek.dao.impl.android;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.constant.OrderStatus;
import com.rotek.constant.RankType;
import com.cta.platform.persistence.dao.BaseDaoImpl;

/**
 * @ClassName: PollingController
 * @Description:
 * @author chenwenpeng
 * @date 2013-9-2 上午08:50:08
 *
 */
@Repository
public class PollingDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: login_deliver
	* @Description:
	* @param username
	* @param password
	* @return
	* @return Map<String,Object>
	* @throws
	*/
	public Map<String, Object> login_deliver(String username, String password) throws SQLException {

		String sql = "select id from mf_deliverer where realname = ? and telephone = ?";
		return this.executeQueryOne(sql, new String[]{username,password});
	}

	/**
	 * @throws SQLException
	* @Title: listDeliverInfo
	* @Description:
	* @param deliver_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listDeliverInfo(Integer deliver_id) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select rest_id,building_id from mf_rest_deliverer_building where deliverer_id = ? and sort = ?");
		sql.append(" or (deliverer_id = ? and rest_id not in (select rest_id from mf_rest_deliverer_building where sort = ?))");

		return this.executeQuery(sql.toString(), new Integer[]{deliver_id,RankType.HEIGHT,deliver_id,RankType.HEIGHT});
	}

	/**
	 * @throws SQLException
	* @Title: listNewOrders
	* @Description:
	* @param string
	* @param time_last
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listNewOrders(String sql, Date time_last) throws SQLException {

		return this.executeQuery(sql, new Date[]{time_last});
	}

	/**
	 * @throws SQLException 
	* @Title: listMenus
	* @Description: 
	* @param @param orderId
	* @param @return 
	* @return List<Map<String,Object>> 
	* @throws 
	*/ 
	public List<Map<String, Object>> listMenus(Integer orderId) throws SQLException {
		String sql = "select m.name,o.count,m.price from mf_order_menu o left join mf_menu_qd m on o.menu_id = m.id where o.order_id = ?";
		return this.executeQuery(sql, new Integer[]{orderId});
	}

	/**
	 * @throws SQLException 
	* @Title: setOrderDelivered
	* @Description: 
	* @param @param order_id 
	* @return void 
	* @throws 
	*/ 
	public void setOrderDelivered(Integer order_id) throws SQLException {
		
		String sql = "update mf_order set status = ? where id = ?";
		this.executeUpdate(sql, new Integer[]{OrderStatus.DELIVERED,order_id});
	}
}
