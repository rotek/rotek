/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.cta.entity;

import java.io.Serializable;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: GiftEntity
 * @Description: 礼品
 * @author chenwenpeng
 * @date 2013-8-8 上午12:10:49
 */
@Table(name="MF_GIFT")
public class GiftEntity implements Serializable {
	/** The long serialVersionUID*/
	private static final long serialVersionUID = 5678476572042216536L;
	
	
	@Column(name="id")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	@Column(name="name")
	private String name;//
	@Column(name="pic")
	private String pic;//
	@Column(name="descr")
	private String descr;//
	@Column(name="points")
	private Integer points;//
	@Column(name="status")
	private Integer status;//
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
