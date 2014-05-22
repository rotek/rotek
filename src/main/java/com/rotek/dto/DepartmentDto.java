/**
* @FileName: DepartmentDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-27 上午11:46:23
* @version V1.0
*/
package com.rotek.dto;

import com.rotek.entity.DepartmentEntity;
import com.rotek.platform.persistence.annotation.Column;

/**
 * @ClassName: DepartmentDto
 * @Description: 部门dto
 * @author chenwenpeng
 * @date 2013-6-27 上午11:46:23
 *
 */
public class DepartmentDto extends DepartmentEntity{

	@Column(name="super_dep_name")
	private String super_dep_name;//上级部门名称

	public String getSuper_dep_name() {
		return super_dep_name;
	}
	public void setSuper_dep_name(String super_dep_name) {
		this.super_dep_name = super_dep_name;
	}
}
