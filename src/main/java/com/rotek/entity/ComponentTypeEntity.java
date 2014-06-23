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
* @ClassName:ComponentTypeEntity
* @Description:
* @Author WangJuZhu
* @date 2014年6月23日 下午2:30:48
* @Version:1.1.0
*/
@Table(name = "r_component_type")
public class ComponentTypeEntity implements Serializable {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 4628046027257788856L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "NAME")
	private String name ;   	// 类别名称
	
	@Column(name = "DESCRIPTION")
	private String description ;   	// 类别的说明
	
	@Column(name = "STATUS")
	private Integer status ; 		// 状态

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

	/** @return status */
	public Integer getStatus() {
		return status;
	}

	/** @param status status to set */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
