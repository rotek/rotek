/**
* @FileName: TaskEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-13 上午11:19:58
* @version V1.0
*/
package com.rotek.entity;

import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: TaskEntity
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-13 上午11:19:58
 *
 */
@Table(name="MF_TASK")
public class TaskEntity {

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//

	@Column(name="target_id")
	private Integer target_id;//

	@Column(name="time")
	private Date time;//

	@Column(name="type")
	private Integer type;//


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTarget_id() {
		return target_id;
	}
	public void setTarget_id(Integer target_id) {
		this.target_id = target_id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
}
