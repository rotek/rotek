/**
* @FileName: ButtonEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-3 上午09:25:15
* @version V1.0
*/
package com.rotek.entity;

import java.io.Serializable;

import com.rotek.platform.constant.StrategyType;
import com.rotek.platform.persistence.annotation.Column;
import com.rotek.platform.persistence.annotation.ID;
import com.rotek.platform.persistence.annotation.Length;
import com.rotek.platform.persistence.annotation.NotEmpty;
import com.rotek.platform.persistence.annotation.Table;

/**
 * @ClassName: ButtonEntity
 * @Description: 按钮的实体类
 * @author chenwenpeng
 * @date 2013-6-3 上午09:25:15
 *
 */
@Table(name="MF_BUTTON")
public class ButtonEntity implements Serializable{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = 103654789244563236L;
	//
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;
	//
	@Column(name="button_name")
	@Length(maxLength=50,minLength=0,message="按钮名称须为0-50字符")
	private String button_name;
	//
	@Column(name="action")
	@Length(maxLength=50,minLength=1,message="按钮action须为1-50字符")
	private String action;
	//
	@Column(name="memo")
	@Length(maxLength=50,minLength=0,message="按钮描述须为1-50字符")
	private String memo;
	//
	@Column(name="icon")
	@Length(maxLength=50,minLength=1,message="按钮icon须为0-50字符")
	private String icon;
	//
	@Column(name="sort")
	@NotEmpty
	private Integer sort;
	//
	@Column(name="status")
	@NotEmpty
	private Integer status;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getButton_name() {
		return button_name;
	}
	public void setButton_name(String button_name) {
		this.button_name = button_name;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
