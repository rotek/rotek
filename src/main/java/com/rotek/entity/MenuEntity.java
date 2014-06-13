/**
 * @FileName: MenuEntity.java
 * @Package com.rotek.entity
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-6-1 上午11:05:16
 * @version V1.0
 */
package com.rotek.entity;

import java.io.Serializable;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Length;
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: MenuEntity
 * @Description: 菜单权限 的实体类
 * @author chenwenpeng
 * @date 2013-6-1 上午11:05:16
 * 
 */
@Table(name = "R_MENU")
public class MenuEntity extends BaseEntity {

	/** @Field the long serialVersionUID */
	private static final long serialVersionUID = 112365545L;
	//
	@ID(strategy = StrategyType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	//
	@Column(name = "name")
	@Length(maxLength = 50, minLength = 1, message = "菜单长度必须在50以内")
	private String name;
	//
	@Column(name = "super_menu_id")
	@NotEmpty()
	private Integer super_menu_id;
	//
	@Column(name = "url")
	@Length(maxLength = 250, minLength = 1, message = "菜单URL长度必须在250以内")
	private String url;
	//
	@Column(name = "sort")
	@NotEmpty()
	private Integer sort;
	//
	@Column(name = "status")
	@NotEmpty()
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

	public Integer getSuper_menu_id() {
		return super_menu_id;
	}

	public void setSuper_menu_id(Integer super_menu_id) {
		this.super_menu_id = super_menu_id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
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
