/**
* @FileName: ManagerEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-22 下午06:13:46
* @version V1.0
*/
package com.rotek.entity;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: ManagerEntity
 * @Description:后台管理员的实体类
 * @author chenwenpeng
 * @date 2013-6-22 下午06:13:46
 *
 */
@Table(name="R_MANAGER")
public class ManagerEntity extends BaseEntity{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = -692961467321766317L;

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
	// 用户的类型状态 -1 不可用 1代表管理员
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
