/**
* @FileName: RestaurantDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-9 上午09:50:54
* @version V1.0
*/
package com.rotek.dto;

import java.util.Date;

import com.rotek.entity.RestaurantEntity;
import com.rotek.platform.persistence.annotation.Column;

/**
 * @ClassName: RestaurantDto
 * @Description: 店铺dto
 * @author chenwenpeng
 * @date 2013-7-9 上午09:50:54
 *
 */
public class RestaurantDto extends RestaurantEntity{

	private Integer dep_id;
	private String dep_name;//店铺所属部门名称
	private String deliverer_name;//店铺的配送员的名称
	private String settlement_display;//用于在前台展示

	@Column(name="settlement_type")
	private Integer settlement_type;//结算类型
	@Column(name="discount")
	private Double discount;//折扣或者利润
	@Column(name="period")
	private Integer period;//结算周期
	//
	@Column(name="deli_interval")
	private Integer deli_interval;
	//
	@Column(name="food_count")
	private Integer food_count;
	//
	@Column(name="order_count")
	private Integer order_count;
	//
	@Column(name="recommend_count")
	private Integer recommend_count;
	//
	@Column(name="taste")
	private Double taste;
	//
	@Column(name="speed")
	private Double speed;
	//
	@Column(name="attitude")
	private Double attitude;

	@Column(name="open_time_am")
	private Date open_time_am;

	@Column(name="close_time_am")
	private Date close_time_am;

	@Column(name="open_time_pm")
	private Date open_time_pm;

	@Column(name="close_time_pm")
	private Date close_time_pm;
	@Column(name="content")
	private String content;



	public String getSettlement_display() {
		return settlement_display;
	}
	public void setSettlement_display(String settlement_display) {
		this.settlement_display = settlement_display;
	}
	public Integer getSettlement_type() {
		return settlement_type;
	}
	public void setSettlement_type(Integer settlement_type) {
		this.settlement_type = settlement_type;
	}
	public Double getDiscount() {
		return discount;
	}
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	public Integer getPeriod() {
		return period;
	}
	public void setPeriod(Integer period) {
		this.period = period;
	}
	public Integer getDep_id() {
		return dep_id;
	}
	public void setDep_id(Integer dep_id) {
		this.dep_id = dep_id;
	}
	public String getDeliverer_name() {
		return deliverer_name;
	}
	public void setDeliverer_name(String deliverer_name) {
		this.deliverer_name = deliverer_name;
	}
	public Integer getDeli_interval() {
		return deli_interval;
	}
	public void setDeli_interval(Integer deli_interval) {
		this.deli_interval = deli_interval;
	}
	public Integer getFood_count() {
		return food_count;
	}
	public void setFood_count(Integer food_count) {
		this.food_count = food_count;
	}
	public Integer getOrder_count() {
		return order_count;
	}
	public void setOrder_count(Integer order_count) {
		this.order_count = order_count;
	}
	public Integer getRecommend_count() {
		return recommend_count;
	}
	public void setRecommend_count(Integer recommend_count) {
		this.recommend_count = recommend_count;
	}
	public Double getTaste() {
		return taste;
	}
	public void setTaste(Double taste) {
		this.taste = taste;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Double getAttitude() {
		return attitude;
	}
	public void setAttitude(Double attitude) {
		this.attitude = attitude;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public Date getOpen_time_am() {
		return open_time_am;
	}
	public void setOpen_time_am(Date open_time_am) {
		this.open_time_am = open_time_am;
	}
	public Date getClose_time_am() {
		return close_time_am;
	}
	public void setClose_time_am(Date close_time_am) {
		this.close_time_am = close_time_am;
	}
	public Date getOpen_time_pm() {
		return open_time_pm;
	}
	public void setOpen_time_pm(Date open_time_pm) {
		this.open_time_pm = open_time_pm;
	}
	public Date getClose_time_pm() {
		return close_time_pm;
	}
	public void setClose_time_pm(Date close_time_pm) {
		this.close_time_pm = close_time_pm;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
}
