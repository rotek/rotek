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
	@NotEmpty()
	private Integer building_id;//
	@Column(name="title")
	@Length(minLength=1,maxLength=50,message="新闻标题不能超过50字符")
	private String title;//
	@Column(name="content")
	@Length(minLength=1,maxLength=20000,message="新闻的内容不能超过2万字符")
	private String content;//
	@Column(name="send_time")
	@NotEmpty()
	private Date send_time;//
	@Column(name="type")
	@NotEmpty()
	private Integer type;//
	@Column(name="level")
	@NotEmpty()
	private Integer level;//
	@Column(name="status")
	@NotEmpty()
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
