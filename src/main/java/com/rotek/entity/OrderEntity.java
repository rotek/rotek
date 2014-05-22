/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.rotek.entity;

import java.io.Serializable;
import java.util.Date;

import com.rotek.platform.constant.StrategyType;
import com.rotek.platform.persistence.annotation.Column;
import com.rotek.platform.persistence.annotation.ID;
import com.rotek.platform.persistence.annotation.Length;
import com.rotek.platform.persistence.annotation.NotEmpty;
import com.rotek.platform.persistence.annotation.Table;

/**
 * @ClassName: OrderEntity
 * @Description: 订单实体
 * @author chenwenpeng
 * @date 2013-7-5 下午9:36:22
 */
@Table(name="MF_ORDER")
public class OrderEntity implements Serializable {
	/** The long serialVersionUID*/
	private static final long serialVersionUID = -2729287783192433782L;

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//
	@Column(name="rest_id")
	@NotEmpty
	private Integer rest_id;//
	@Column(name="building_id")
	@NotEmpty
	private Integer building_id;//
	@Column(name="phone")
	@Length(minLength=11,maxLength=11,message="请正确填写11位收餐电话")
	private String phone;//
	@Column(name="address")
	@Length(minLength=1,maxLength=100,message="配送地址长度在1-100之间")
	private String address;//
	@Column(name="remark")
	@Length(minLength=0,maxLength=30,message="订单备注长度在30字符以内")
	private String remark;//
	@Column(name="carriage")
	@NotEmpty
	private Double carriage;//
	@Column(name="totalPrice")
	@NotEmpty
	private Double totalPrice;//
	@Column(name="ordered_time")
	@NotEmpty
	private Date ordered_time;//
	@Column(name="user_id")
	@NotEmpty
	private Integer user_id;//
	@Column(name="type")
	@NotEmpty
	private Integer type;//
	@Column(name="status")
	@NotEmpty
	private Integer status;//


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
	public Integer getBuilding_id() {
		return building_id;
	}
	public void setBuilding_id(Integer building_id) {
		this.building_id = building_id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Double getCarriage() {
		return carriage;
	}
	public void setCarriage(Double carriage) {
		this.carriage = carriage;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Date getOrdered_time() {
		return ordered_time;
	}
	public void setOrdered_time(Date ordered_time) {
		this.ordered_time = ordered_time;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
