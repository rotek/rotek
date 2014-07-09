/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.Ltd.
*/
package com.rotek.entity;

import java.io.Serializable;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
* @ClassName:AlgorithmTypeEntity
* @Description: 算法基本信息实体类
* @Author WangJuZhu
* @date 2014年7月9日 下午4:25:45
* @Version:1.1.0
*/
@Table(name = "r_algorithm_type")
public class AlgorithmTypeEntity implements Serializable {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 4628046027257788856L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "NAME")
	private String name ;   	// 算法名称
	
	@Column(name = "DESCRIPTION")
	private String description ;   	// 算法的说明
	
	/** @return id */
	public Integer getId() {
		return id;
	}

	/** @param id id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return name */
	public String getName() {
		return name;
	}

	/** @param name name to set */
	public void setName(String name) {
		this.name = name;
	}

	/** @return description */
	public String getDescription() {
		return description;
	}

	/** @param description description to set */
	public void setDescription(String description) {
		this.description = description;
	}

}
