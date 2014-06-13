/**
* @FileName: DeliverEntity.java
* @Package com.cta.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-28 下午01:44:03
* @version V1.0
*/
package com.cta.entity;

import java.io.Serializable;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: DelivererEntity
 * @Description: 配送员
 * @author chenwenpeng
 * @date 2013-6-28 下午01:44:03
 *
 */
@Table(name="MF_DELIVERER")
public class DelivererEntity implements Serializable{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = -5144500502395456979L;
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//
	@Column(name="realname")
	private String realname;//
	@Column(name="telephone")
	private String telephone;//
	@Column(name="gender")
	private Integer gender;//
	@Column(name="send_type")
	private Integer send_type;
	@Column(name="status")
	private Integer status;//

	
	public Integer getSend_type() {
		return send_type;
	}
	public void setSend_type(Integer send_type) {
		this.send_type = send_type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
