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
 * @ClassName: RegionEntity
 * @Description: 区域对应的实体类
 * @author chenwenpeng
 * @date 2013-5-22 下午10:03:45
 */

@Table(name="MF_REGION")
public class RegionEntity implements Serializable{

	/** The long serialVersionUID*/
	private static final long serialVersionUID = 100000222L;
	
	//
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;
	//
	@Column(name="name")
	private String  name;
	//
	@Column(name="city_id")
	private Integer city_id;
	//
	@Column(name="type")
	private Integer type;
	//
	@Column(name="sort")
	private Integer sort;
	//
	@Column(name="status")
	private Integer status;
	
	
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
	public Integer getCity_id() {
		return city_id;
	}
	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	} 
}
