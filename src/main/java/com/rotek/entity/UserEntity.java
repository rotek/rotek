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

import com.rotek.platform.constant.StrategyType;
import com.rotek.platform.persistence.annotation.Column;
import com.rotek.platform.persistence.annotation.ID;
import com.rotek.platform.persistence.annotation.Table;

/**
 * @ClassName: UserEntity
 * @Description: mf_manager对应实体类
 * @author chenwenpeng
 * @date 2013-5-18 下午03:33:11
 *
 */
@Table(name="MF_MANAGER")
public class UserEntity implements Serializable{

	//
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;
	//
	@Column(name="name")
	private String name;
	//
	@Column(name="real_name")
	private String real_name;
	//
	@Column(name="password")
	private String password;
	//
	@Column(name="phone")
	private String phone;
	//
	@Column(name="question_secu")
	private String question_secu;
	//
	@Column(name="answer")
	private String answer;
	//用户的类型状态 -1 或 -2 不可用 1代表管理员 2代表店铺
	@Column(name="status")
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

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQuestion_secu() {
		return question_secu;
	}

	public void setQuestion_secu(String question_secu) {
		this.question_secu = question_secu;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
