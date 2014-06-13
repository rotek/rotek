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
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: MenuEntity
 * @Description: 店铺菜单
 * @author chenwenpeng
 * @date 2013-6-5 下午11:13:02
 */
@Table(name = "MF_ORDER_MENU")
public class OrderMenuEntity implements Serializable {
	/** The long serialVersionUID */
	private static final long serialVersionUID = -2729287783192433782L;

	@ID(strategy = StrategyType.IDENTITY)
	@Column(name = "id")
	private Integer id;//
	@Column(name = "order_id")
	@NotEmpty
	private Integer order_id;//
	@Column(name = "menu_id")
	@NotEmpty
	private Integer menu_id;//

	@Column(name = "price")
	@NotEmpty
	private Double price;//

	@Column(name = "count")
	@NotEmpty
	private Integer count;//

	@Column(name = "name")
	@NotEmpty
	private String name;//

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}

	public Integer getMenu_id() {
		return menu_id;
	}

	public void setMenu_id(Integer menu_id) {
		this.menu_id = menu_id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
