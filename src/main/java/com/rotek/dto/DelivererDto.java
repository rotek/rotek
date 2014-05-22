/**
* @FileName: DelivererDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-6 下午05:10:50
* @version V1.0
*/
package com.rotek.dto;

import com.rotek.entity.DelivererEntity;

/**
 * @ClassName: DelivererDto
 * @Description: 配送员
 * @author chenwenpeng
 * @date 2013-7-6 下午05:10:50
 *
 */
public class DelivererDto extends DelivererEntity{

	private Integer dep_id;//配送员所属部门的id
	private String dep_name;//配送员所属部门的name


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
