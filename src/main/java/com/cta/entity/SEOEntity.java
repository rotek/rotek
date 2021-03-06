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
 * @ClassName: SEOEntity
 * @Description: 
 * @author chenwenpeng
 * @date 2013-8-15 下午9:02:21
 */
@Table(name="MF_SEO")
public class SEOEntity implements Serializable{

	/** The long serialVersionUID*/
	private static final long serialVersionUID = -6719840516148861764L;
	
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//
	
	@Column(name="title")
	private String title;//
	
	@Column(name="keywords")
	private String keywords;//
	
	@Column(name="description")
	private String description;
	
	@Column(name="type")
	private Integer type;//

	
	
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

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
