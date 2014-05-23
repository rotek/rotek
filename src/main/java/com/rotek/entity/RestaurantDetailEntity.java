/**
* @FileName: RestaurantDetailEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-17 下午04:08:59
* @version V1.0
*/
package com.rotek.entity;

import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: RestaurantDetailEntity
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-17 下午04:08:59
 *
 */
@Table(name="MF_REST_DETAIL")
public class RestaurantDetailEntity {

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//
	@Column(name="rest_id")
	@NotEmpty
	private Integer rest_id;//
	@Column(name="deli_interval")

	private Integer deli_interval = 0;//
	@Column(name="food_count")

	private Integer food_count = 0;//
	@Column(name="order_count")

	private Integer order_count = 0;//
	@Column(name="recommend_count")

	private Integer recommend_count = 0;//

	@Column(name="taste")
	private Double taste = 0.0;//

	@Column(name="speed")
	private Double speed = 0.0;//

	@Column(name="attitude")
	private Double attitude = 0.0;//

	@Column(name="open_time_am")
	private Date open_time_am;//

	@Column(name="open_time_pm")
	private Date open_time_pm;//

	@Column(name="close_time_am")
	private Date close_time_am;//

	@Column(name="close_time_pm")
	private Date close_time_pm;
	
	@Column(name="content")
	private String content;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getRest_id() {
		return rest_id;
	}
	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
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
	public Date getOpen_time_am() {
		return open_time_am;
	}
	public void setOpen_time_am(Date open_time_am) {
		this.open_time_am = open_time_am;
	}
	public Date getOpen_time_pm() {
		return open_time_pm;
	}
	public void setOpen_time_pm(Date open_time_pm) {
		this.open_time_pm = open_time_pm;
	}
	public Date getClose_time_am() {
		return close_time_am;
	}
	public void setClose_time_am(Date close_time_am) {
		this.close_time_am = close_time_am;
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
