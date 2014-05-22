/**
* @FileName: BroadCastDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-6 上午10:42:05
* @version V1.0
*/
package com.rotek.dto;

import com.rotek.entity.BroadCastEntity;
import com.rotek.platform.persistence.annotation.Column;

/**
 * @ClassName: BroadCastDto
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-6 上午10:42:05
 *
 */
public class BroadCastDto extends BroadCastEntity{

	@Column(name="building_name")
	private String building_name;//轮播在哪个楼宇下显示

	public String getBuilding_name() {
		return building_name;
	}

	public void setBuilding_name(String building_name) {
		this.building_name = building_name;
	}
}
