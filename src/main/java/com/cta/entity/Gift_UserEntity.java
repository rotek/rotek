/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.cta.entity;

import java.io.Serializable;
import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: Gift_UserEntity
 * @Description: 
 * @author chenwenpeng
 * @date 2013-8-8 下午5:24:23
 */
@Table(name="MF_GIFT_USER")
public class Gift_UserEntity implements Serializable{
	
	/** The long serialVersionUID*/
	private static final long serialVersionUID = 3823234078568220538L;
	
	
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Column(name="gift_id")
	private Integer gift_id;
	@Column(name="user_id")
	private Integer user_id; 
	@Column(name="time_exchange")
	private Date time_exchange; 
	@Column(name="status")
	private Integer status ;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getGift_id() {
		return gift_id;
	}
	public void setGift_id(Integer gift_id) {
		this.gift_id = gift_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Date getTime_exchange() {
		return time_exchange;
	}
	public void setTime_exchange(Date time_exchange) {
		this.time_exchange = time_exchange;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
