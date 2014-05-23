/**
* @FileName: BuildingEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-18 下午03:59:05
* @version V1.0
*/
package com.rotek.entity;

import java.io.Serializable;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Length;
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: BuildingEntity
 * @Description: mf_building 对应的实体类
 * @author chenwenpeng
 * @date 2013-5-18 下午03:59:05
 *
 */
@Table(name="MF_BUILDING")
public class BuildingEntity implements Serializable{
	/** The long serialVersionUID*/
	private static final long serialVersionUID = 1957683377429361866L;
	//
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;
	//
	@Column(name="name")
	@Length(minLength=1,maxLength=50,message="楼宇名称为1-50个字符")
	private String name;
	//别名
	@Column(name="alias")
	@Length(minLength=1,maxLength=50,message="楼宇别名为1-50个字符")
	private String alias;
	//
	@Column(name="address")
	@Length(minLength=1,maxLength=50,message="楼宇地址为1-100个字符")
	private String address;
	//
	@Column(name="order_count")
	@NotEmpty
	private Integer order_count;
	//
	@Column(name="region_id")
	@NotEmpty
	private Integer region_id;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getOrder_count() {
		return order_count;
	}
	public void setOrder_count(Integer order_count) {
		this.order_count = order_count;
	}
	public Integer getRegion_id() {
		return region_id;
	}
	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}

}
