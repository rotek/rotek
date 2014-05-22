/**
* @FileName: OrderDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-26 下午04:47:00
* @version V1.0
*/
package com.rotek.dto;

import java.util.List;
import java.util.Map;

import com.rotek.entity.OrderEntity;
import com.rotek.platform.persistence.annotation.Column;

/**
 * @ClassName: OrderDto
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-26 下午04:47:00
 *
 */
public class OrderDto extends OrderEntity{

	private String deliverer_name;//订单的配送员

	private String deliverer_phone;//配送员电话

	@Column(name="rest_name")
	private String rest_name;

	@Column(name="rest_phone")//店铺的电话
	private String rest_phone;

	@Column(name="deli_type")
	private Integer deli_type;//店铺订单的配送方
	
	@Column(name="send_type")
	private Integer send_type;//店铺订单的通知方式

	@Column(name="user_name")
	private String user_name;

	private List<Map<String,Object>> menus;//订单包含的菜单详情



	public Integer getSend_type() {
		return send_type;
	}

	public void setSend_type(Integer send_type) {
		this.send_type = send_type;
	}

	public Integer getDeli_type() {
		return deli_type;
	}

	public void setDeli_type(Integer deli_type) {
		this.deli_type = deli_type;
	}

	public String getRest_phone() {
		return rest_phone;
	}

	public void setRest_phone(String rest_phone) {
		this.rest_phone = rest_phone;
	}

	public List<Map<String, Object>> getMenus() {
		return menus;
	}

	public void setMenus(List<Map<String, Object>> menus) {
		this.menus = menus;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getRest_name() {
		return rest_name;
	}

	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}

	public String getDeliverer_name() {
		return deliverer_name;
	}

	public void setDeliverer_name(String deliverer_name) {
		this.deliverer_name = deliverer_name;
	}

	public String getDeliverer_phone() {
		return deliverer_phone;
	}

	public void setDeliverer_phone(String deliverer_phone) {
		this.deliverer_phone = deliverer_phone;
	}
}
