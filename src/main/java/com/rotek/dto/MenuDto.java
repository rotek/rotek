/**
* @FileName: MenuDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-1 下午02:51:54
* @version V1.0
*/
package com.rotek.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.rotek.entity.MenuEntity;

/**
 * @ClassName: MenuDto
 * @Description:
 * @author chenwenpeng
 * @date 2013-6-1 下午02:51:54
 *
 */
public class MenuDto extends MenuEntity{
	//子级菜单
	private List<MenuEntity> subMenuList = new ArrayList<MenuEntity>();
	//对应的button
	private List<Map<String,Object>> buttonList;


	public List<Map<String, Object>> getButtonList() {
		return buttonList;
	}

	public void setButtonList(List<Map<String, Object>> buttonList) {
		this.buttonList = buttonList;
	}

	public List<MenuEntity> getSubMenuList() {
		return subMenuList;
	}

	public void setSubMenuList(List<MenuEntity> subMenuList) {
		this.subMenuList = subMenuList;
	}

}
