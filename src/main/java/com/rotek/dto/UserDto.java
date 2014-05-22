/**
* @FileName: UserDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-1 下午12:56:26
* @version V1.0
*/
package com.rotek.dto;

import java.io.Serializable;

import com.rotek.entity.UserEntity;
import com.rotek.platform.persistence.annotation.Column;

/**
 * @ClassName: UserDto
 * @Description:
 * @author chenwenpeng
 * @date 2013-6-1 下午12:56:26
 *
 */
public class UserDto extends UserEntity implements Serializable{
	//用户部门
	@Column(name="dep_id")
	private Integer dep_id = 0;
	//用户权限
	@Column(name="role_id")
	private Integer role_id = 0;


	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
	public Integer getDep_id() {
		return dep_id;
	}
	public void setDep_id(Integer dep_id) {
		this.dep_id = dep_id;
	}
}
