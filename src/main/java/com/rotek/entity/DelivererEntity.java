/**
* @FileName: DeliverEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-28 下午01:44:03
* @version V1.0
*/
package com.rotek.entity;

import java.io.Serializable;

import com.rotek.platform.constant.StrategyType;
import com.rotek.platform.persistence.annotation.Column;
import com.rotek.platform.persistence.annotation.ID;
import com.rotek.platform.persistence.annotation.Length;
import com.rotek.platform.persistence.annotation.NotEmpty;
import com.rotek.platform.persistence.annotation.Table;
import com.rotek.platform.persistence.annotation.TelePhone;

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
	@Length(minLength=1,maxLength=10,message="配送员名称须在1-10之间")
	private String realname;//
	@Column(name="telephone")
	@TelePhone(message="配送员电话须为11位")
	private String telephone;//
	@Column(name="send_type")
	@NotEmpty
	private Integer send_type;//订单通知方式
	@Column(name="gender")
	@NotEmpty
	private Integer gender;//
	@Column(name="status")
	@NotEmpty
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
