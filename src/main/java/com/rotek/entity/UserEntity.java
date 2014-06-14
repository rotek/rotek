/**
 * @FileName: UserEntity.java
 * @Package com.rotek.entity
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-18 下午03:33:11
 * @version V1.0
 */
package com.rotek.entity;

import java.io.Serializable;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: UserEntity
 * @Description: mf_manager对应实体类
 * @author chenwenpeng
 * @date 2013-5-18 下午03:33:11
 * 
 */
@Table(name = "r_user")
public class UserEntity implements Serializable {

	/**启用
	 */
	public static final int STATUS_ENABLE = 1;
	/** 禁用
	 */
	public static final int STATUS_UNENABLE = 2;

	private static final long serialVersionUID = 3591939639360046783L;
	//
	@ID(strategy = StrategyType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	//
	@Column(name = "name")
	private String name;
	//
	@Column(name = "r_role_id")
	private Integer r_role_id;
	//
	@Column(name = "r_customer_id")
	private Integer r_customer_id;
	//
	@Column(name = "r_agent_id")
	private Integer r_agent_id;
	//
	@Column(name = "password")
	private String password;
	//
	@Column(name = "email")
	private String email;
	//
	@Column(name = "telephone")
	private String telephone;
	//
	@Column(name = "realname")
	private String realname;
	//
	@Column(name = "companyname")
	private String companyname;
	//
	@Column(name = "logintime")
	private String logintime;
	// 用户的类型状态 -1 或 -2 不可用 1代表管理员 2代表店铺
	@Column(name = "status")
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

	public Integer getR_role_id() {
		return r_role_id;
	}

	public void setR_role_id(Integer r_role_id) {
		this.r_role_id = r_role_id;
	}

	
	public Integer getR_customer_id() {
		return r_customer_id;
	}

	public void setR_customer_id(Integer r_customer_id) {
		this.r_customer_id = r_customer_id;
	}

	public Integer getR_agent_id() {
		return r_agent_id;
	}

	public void setR_agent_id(Integer r_agent_id) {
		this.r_agent_id = r_agent_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getRealname() {
		return realname;
	}

	public void setRealname(String realname) {
		this.realname = realname;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public String getLogintime() {
		return logintime;
	}

	public void setLogintime(String logintime) {
		this.logintime = logintime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
