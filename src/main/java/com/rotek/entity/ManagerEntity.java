/**
* @FileName: ManagerEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-22 下午06:13:46
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
 * @ClassName: ManagerEntity
 * @Description:后台管理员的实体类
 * @author chenwenpeng
 * @date 2013-6-22 下午06:13:46
 *
 */
@Table(name="MF_MANAGER")
public class ManagerEntity implements Serializable{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = -692961467321766317L;

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//
	@Column(name="name")
	@Length(maxLength=30,minLength=6,message="用户名长度必须在6-30之间")
	private String name;//
	@Column(name="real_name")
	@Length(maxLength=30,minLength=1,message="真实姓名必须在1-30之间")
	private String real_name;//
	@Column(name="password")
	@Length(maxLength=30,minLength=6,message="密码长度必须在6-30之间")
	private String password;//
	@Column(name="phone")
	@TelePhone(message="请输入11位电话号码")
	private String phone;//
	@Column(name="question_secu")
	@Length(maxLength=30,minLength=1,message="密保问题长度必须在1-30之间")
	private String question_secu;//
	@Column(name="answer")
	@Length(maxLength=30,minLength=1,message="密保答案长度必须在1-30之间")
	private String answer;//
	@Column(name="status")
	@NotEmpty
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
