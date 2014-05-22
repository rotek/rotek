/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.rotek.entity;

import java.io.Serializable;

import com.rotek.platform.constant.StrategyType;
import com.rotek.platform.persistence.annotation.Column;
import com.rotek.platform.persistence.annotation.ID;

/**
 * @ClassName: NoticeEntity
 * @Description: 紧急通知
 * @author chenwenpeng
 * @date 2013-5-26 下午4:34:14
 */
public class NoticeEntity implements Serializable{

	/** The long serialVersionUID*/
	private static final long serialVersionUID = 100203394948L;

	//
	@ID(strategy = StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;
	//
	@Column(name="title")
	private String title;
	//
	@Column(name="content")
	private String content;
	//
	@Column(name="target")
	private String target;
	//
	@Column(name="status")
	private Integer status;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
