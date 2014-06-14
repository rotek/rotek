/**
 * @FileName: RoleEntity.java
 * @Package com.rotek.entity
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-6-3 下午01:31:19
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
 * @ClassName: RoleEntity
 * @Description: 角色信息
 * @author chenwenpeng
 * @date 2013-6-3 下午01:31:19
 * 
 */
@Table(name = "r_role")
public class RoleEntity implements Serializable {

	/** @Field the long serialVersionUID */
	private static final long serialVersionUID = 6216523654386139761L;

	@ID(strategy = StrategyType.IDENTITY)
	@Column(name = "id")
	private Integer id;//
	@Column(name = "name")
	@Length(minLength = 1, maxLength = 50, message = "角色名应该为1-50个字符")
	private String name;//
	@Column(name = "status")
	@NotEmpty()
	private Integer status;//

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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
