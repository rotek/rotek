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
 * @ClassName: NewsEntity
 * @Description: 新闻的实体类
 * @author chenwenpeng
 * @date 2013-5-26 下午4:39:23
 */
@Table(name="MF_NEWS")
public class NewsEntity implements Serializable{

	/** The long serialVersionUID*/
	private static final long serialVersionUID = 1737525366773L;

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//
	@Column(name="building_id")
	private Integer building_id;//
	@Column(name="title")
	private String title;//
	@Column(name="content")
	private String content;//
	@Column(name="send_time")
	private Date send_time;//
	@Column(name="type")
	private Integer type;//
	@Column(name="level")
	private Integer level;//
	@Column(name="status")
	private Integer status;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getBuilding_id() {
		return building_id;
	}
	public void setBuilding_id(Integer building_id) {
		this.building_id = building_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getSend_time() {
		return send_time;
	}
	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
