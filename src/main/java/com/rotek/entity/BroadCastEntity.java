/**
* @FileName: BroadCastEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-6 上午10:35:30
* @version V1.0
*/
package com.rotek.entity;

import java.io.Serializable;

import com.rotek.platform.constant.StrategyType;
import com.rotek.platform.persistence.annotation.Column;
import com.rotek.platform.persistence.annotation.ID;
import com.rotek.platform.persistence.annotation.NotEmpty;
import com.rotek.platform.persistence.annotation.Table;

/**
 * @ClassName: BroadCastEntity
 * @Description: 轮播的实体类
 * @author chenwenpeng
 * @date 2013-8-6 上午10:35:30
 *
 */
@Table(name="MF_BROADCAST")
public class BroadCastEntity implements Serializable{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = 4438334781913748979L;

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//
	@Column(name="name")
	@NotEmpty()
	private String name;//
	@Column(name="alt")
	@NotEmpty()
	private String alt;//
	@Column(name="buildingId")
	@NotEmpty()
	private String target;//
	@Column(name="buildingId")
	@NotEmpty()
	private Integer buildingId;//
	@Column(name="status")
	@NotEmpty()
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
	public String getAlt() {
		return alt;
	}
	public void setAlt(String alt) {
		this.alt = alt;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public Integer getBuildingId() {
		return buildingId;
	}
	public void setBuildingId(Integer buildingId) {
		this.buildingId = buildingId;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
