/**
* @FileName: DepartmentEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 下午05:49:22
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
 * @ClassName: DepartmentEntity
 * @Description: 部门entity
 * @author chenwenpeng
 * @date 2013-6-26 下午05:49:22
 *
 */
@Table(name="MF_DEPARTMENT")
public class DepartmentEntity implements Serializable{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = -2892920636717922425L;


	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//
	@Column(name="dep_name")
	@Length(minLength=1,maxLength=50,message="部门名须为1-50个字符")
	private String dep_name;//
	@Column(name="super_dep_id")
	@NotEmpty
	private Integer super_dep_id;//
	@Column(name="memo")
	@Length(minLength=0,maxLength=50,message="部门描述不能超过200字符")
	private String memo;//
	@Column(name="sort")
	private Integer sort;//
	@Column(name="status")
	@NotEmpty
	private Integer status;//


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDep_name() {
		return dep_name;
	}
	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}
	public Integer getSuper_dep_id() {
		return super_dep_id;
	}
	public void setSuper_dep_id(Integer super_dep_id) {
		this.super_dep_id = super_dep_id;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
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
