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
 * @ClassName: MenuEntity
 * @Description: 店铺菜单
 * @author chenwenpeng
 * @date 2013-6-5 下午11:13:02
 */
@Table(name="MF_MENU_QD")
public class MenuEntity implements Serializable{
	/** The long serialVersionUID*/
	private static final long serialVersionUID = 2561879663366718695L;
	
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;// 
	@Column(name="name")
	private String name;// 
	@Column(name="price")
	private Double price;// 
	@Column(name="pic")
	private String pic;// 
	@Column(name="mix")
	private String mix;// 
	@Column(name="recommend")
	private Integer recommend;// 
	@Column(name="descr")
	private String descr;// 
	@Column(name="cate_id")
	private Integer cate_id;// 
	@Column(name="rest_id")
	private Integer rest_id;// 
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getMix() {
		return mix;
	}
	public void setMix(String mix) {
		this.mix = mix;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getCate_id() {
		return cate_id;
	}
	public void setCate_id(Integer cate_id) {
		this.cate_id = cate_id;
	}
	public Integer getRest_id() {
		return rest_id;
	}
	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
