/**
* @FileName: ManagerDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 上午09:46:23
* @version V1.0
*/
package com.rotek.dto;

import com.rotek.entity.ManagerEntity;
import com.cta.platform.persistence.annotation.Column;

/**
 * @ClassName: ManagerDto
 * @Description:
 * @author chenwenpeng
 * @date 2013-6-26 上午09:46:23
 *
 */
public class ManagerDto extends ManagerEntity {

	@Column(name="manager_id")
	private Integer manager_id;//部门id

	@Column(name="manager_name")
	private String manager_name;//部门name


	@Column(name="role_id")
	private Integer role_id;//角色id

	@Column(name="role_name")
	private String role_name;//角色name

	@Column(name="dep_id")
	private Integer dep_id;//部门id

	@Column(name="dep_name")
	private String dep_name;//部门名称



	public Integer getDep_id() {
		return dep_id;
	}
	public void setDep_id(Integer dep_id) {
		this.dep_id = dep_id;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public Integer getManager_id() {
		return manager_id;
	}
	public void setManager_id(Integer manager_id) {
		this.manager_id = manager_id;
	}
	public String getManager_name() {
		return manager_name;
	}
	public void setManager_name(String manager_name) {
		this.manager_name = manager_name;
	}
	public String getRole_name() {
		return role_name;
	}
	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}
	public Integer getRole_id() {
		return role_id;
	}
	public void setRole_id(Integer role_id) {
		this.role_id = role_id;
	}
}
