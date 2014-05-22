/**
* @FileName: RestMenuDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-19 下午05:24:13
* @version V1.0
*/
package com.rotek.dto;

import com.rotek.entity.RestMenuEntity;

/**
 * @ClassName: RestMenuDto
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-19 下午05:24:13
 *
 */

public class RestMenuDto extends RestMenuEntity{

	private String rest_name;//菜单所属店铺

	public String getRest_name() {
		return rest_name;
	}

	public void setRest_name(String rest_name) {
		this.rest_name = rest_name;
	}
}
