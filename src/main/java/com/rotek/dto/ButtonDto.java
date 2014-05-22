/**
* @FileName: ButtonDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 下午02:53:07
* @version V1.0
*/
package com.rotek.dto;

import java.util.List;
import java.util.Map;

import com.rotek.entity.ButtonEntity;

/**
 * @ClassName: ButtonDto
 * @Description:
 * @author chenwenpeng
 * @date 2013-6-26 下午02:53:07
 *
 */
public class ButtonDto extends ButtonEntity{
	private List<Map<String,Object>> buttonList;//菜单对应的按钮

	public List<Map<String, Object>> getButtonList() {
		return buttonList;
	}

	public void setButtonList(List<Map<String, Object>> buttonList) {
		this.buttonList = buttonList;
	}
}
