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
@Table(name="mf_role")
public class RoleEntity implements Serializable{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = 6216523654386139761L;

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//
	@Column(name="role_name")
	@Length(minLength=1,maxLength=50,message="角色名应该为1-50个字符")
	private String role_name;//
	@Column(name="super_role_id")
	@NotEmpty(message="上级角色不能为空")
	private Integer super_role_id;//
	@Column(name="memo")
	@Length(minLength=1,maxLength=200,message="角色描述应该为1-200个字符")
	private String memo;//
	@Column(name="status")
	@NotEmpty()
	private Integer status;//
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public Integer getSuper_role_id() {
		return super_role_id;
	}
	public void setSuper_role_id(Integer super_role_id) {
		this.super_role_id = super_role_id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
