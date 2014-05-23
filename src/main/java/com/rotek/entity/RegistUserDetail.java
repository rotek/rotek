/**
* @FileName: RegistUserDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-8 上午11:37:57
* @version V1.0
*/
package com.rotek.entity;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: RegistUserDto
 * @Description: 用户详情
 * @author chenwenpeng
 * @date 2013-8-8 上午11:37:57
 *
 */
@Table(name="MF_USER_DETAIL")
public class RegistUserDetail{

		@ID(strategy=StrategyType.IDENTITY)
		@Column(name="id")
		private Integer id;
		@Column(name="user_id")
		@NotEmpty()
		private Integer user_id;
		@Column(name="gender")
		@NotEmpty()
		private Integer gender;
		@Column(name="icon")
		@NotEmpty()
		private String icon;
		@Column(name="email")
		@NotEmpty()
		private String email;
		@Column(name="qq")
		@NotEmpty()
		private String qq;



		public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public Integer getUser_id() {
			return user_id;
		}
		public void setUser_id(Integer user_id) {
			this.user_id = user_id;
		}
		public Integer getGender() {
			return gender;
		}
		public void setGender(Integer gender) {
			this.gender = gender;
		}
		public String getIcon() {
			return icon;
		}
		public void setIcon(String icon) {
			this.icon = icon;
		}
		public String getEmail() {
			return email;
		}
		public void setEmail(String email) {
			this.email = email;
		}
		public String getQq() {
			return qq;
		}
		public void setQq(String qq) {
			this.qq = qq;
		}
	}
