/**
* @FileName: BuildingDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-5 下午04:22:36
* @version V1.0
*/
package com.rotek.dto;

import com.rotek.entity.BuildingEntity;
import com.rotek.platform.persistence.annotation.Column;

/**
 * @ClassName: BuildingDto
 * @Description: 楼宇
 * @author chenwenpeng
 * @date 2013-7-5 下午04:22:36
 *
 */
public class BuildingDto extends BuildingEntity{

	private Integer dep_id;//楼宇所属管理部门的id，查询的时候使用
	@Column(name="dep_name")
	private String dep_name;
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
}
