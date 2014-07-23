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

	@Column(name = "rolename")
	private String rolename;// 角色名称

	@Column(name = "customername")
	private String customername;// 客户名称

	public String getCustomername() {
		return customername;
	}

	public void setCustomername(String customername) {
		this.customername = customername;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
