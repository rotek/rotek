/**
* @FileName: RegistUserEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-8 上午11:37:26
* @version V1.0
*/
package com.rotek.entity;

import java.io.Serializable;
import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Length;
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.Table;
import com.cta.platform.persistence.annotation.TelePhone;

/**
 * @ClassName: RegistUserEntity
 * @Description:注册用户
 * @author chenwenpeng
 * @date 2013-8-8 上午11:37:26
 *
 */
@Table(name="MF_USER")
public class RegistUserEntity implements Serializable{

		/**@Field the long serialVersionUID*/
		private static final long serialVersionUID = 10005L;
		@ID(strategy=StrategyType.IDENTITY)
		@Column(name="id")
		private Integer id;
		@Column(name="nick_name")
		@Length(minLength=3,maxLength=20,message="用户名长度请在3-20位之间")
		private String nick_name;
		@Column(name="real_name")
		@NotEmpty()
		private String real_name;
		@Column(name="password")
		@Length(minLength=3,maxLength=30,message="密码长度请在3-30位之间")
		private String password;

		@Column(name="telephone")
		@TelePhone(message="请正确填写11位电话号码")
		private String telephone;

		@Column(name="order_count")
		@NotEmpty()
		private Integer order_count;
		@Column(name="un_comment")
		@NotEmpty()
		private Integer un_comment;
		@Column(name="gold")
		@NotEmpty()
		private Integer gold;
		@Column(name="reg_time")
		@NotEmpty()
		private Date reg_time;
		@Column(name="enabled")
		@NotEmpty()
		private Integer enabled;


		public String getTelephone() {
			return telephone;
		}
		public void setTelephone(String telephone) {
			this.telephone = telephone;
		}
		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNick_name() {
			return nick_name;
		}
		public void setNick_name(String nick_name) {
			this.nick_name = nick_name;
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

		public Integer getOrder_count() {
			return order_count;
		}
		public void setOrder_count(Integer order_count) {
			this.order_count = order_count;
		}
		public Integer getUn_comment() {
			return un_comment;
		}
		public void setUn_comment(Integer un_comment) {
			this.un_comment = un_comment;
		}
		public Integer getGold() {
			return gold;
		}
		public void setGold(Integer gold) {
			this.gold = gold;
		}

		public Date getReg_time() {
			return reg_time;
		}
		public void setReg_time(Date reg_time) {
			this.reg_time = reg_time;
		}
		public Integer getEnabled() {
			return enabled;
		}
		public void setEnabled(Integer enabled) {
			this.enabled = enabled;
		}
	}
