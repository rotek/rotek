/**
* @FileName: NewsDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-6 下午04:21:24
* @version V1.0
*/
package com.rotek.dto;

import com.rotek.entity.NewsEntity;
import com.rotek.platform.persistence.annotation.Column;

/**
 * @ClassName: NewsDto
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-6 下午04:21:24
 *
 */

public class NewsDto extends NewsEntity{

	@Column(name="building_name")
	private String building_name;

	public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}

}
