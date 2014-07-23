/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.Ltd.
*/
package com.rotek.entity;

import java.io.Serializable;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
* @ClassName:ProjectInfoEntity
* @Description: 工程资料实体类
* @Author WangJuZhu
* @date 2014年6月24日 下午1:37:27
* @Version:1.1.0
*/
@Table(name = "r_project_info")
public class ProjectInfoEntity implements Serializable {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 4628046027257788856L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 所属工程的ID
	
	@Column(name = "GCZLMC")
	private String gczlmc ;   	// 工程资料名称
	
	@Column(name = "GCZLJJ")
	private String gczljj ;   	// 工程资料简介

	@Column(name = "GCZLLX")
	private int gczllx ;    	// 工程资料类型,doc/ppt/jpg/pdf/视频/...
	
	@Column(name = "GCZLLJ")
	private String gczllj ;    	// 工程资料存储路径
	
	// 工程检测图类型,是否作为检测图，作为那个功能的监测图
	// 1、托管服务流量监测图；2、托管服务压力检测图；3、托管服务水质监测图
	// 4、EMC工程流量监测图；5、EMC工程压力检测图；6、EMC工程水质监测图
	@Column(name = "GCJCTLX")
	private String gcjctlx ;    	
	
	@Column(name = "STATUS")
	private Integer status ; 		// 状态

	/** @return id */
	public Integer getId() {
		return id;
	}

	/** @param id id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return r_project_id */
	public Integer getR_project_id() {
		return r_project_id;
	}

	/** @param r_project_id r_project_id to set */
	public void setR_project_id(Integer r_project_id) {
		this.r_project_id = r_project_id;
	}

	/** @return gczlmc */
	public String getGczlmc() {
		return gczlmc;
	}

	/** @param gczlmc gczlmc to set */
	public void setGczlmc(String gczlmc) {
		this.gczlmc = gczlmc;
	}

	/** @return gczljj */
	public String getGczljj() {
		return gczljj;
	}

	/** @param gczljj gczljj to set */
	public void setGczljj(String gczljj) {
		this.gczljj = gczljj;
	}

	/** @return gczllx */
	public int getGczllx() {
		return gczllx;
	}

	/** @param gczllx gczllx to set */
	public void setGczllx(int gczllx) {
		this.gczllx = gczllx;
	}

	/** @return gczllj */
	public String getGczllj() {
		return gczllj;
	}

	/** @param gczllj gczllj to set */
	public void setGczllj(String gczllj) {
		this.gczllj = gczllj;
	}

	/** @return gcjctlx */
	public String getGcjctlx() {
		return gcjctlx;
	}

	/** @param gcjctlx gcjctlx to set */
	public void setGcjctlx(String gcjctlx) {
		this.gcjctlx = gcjctlx;
	}

	/** @return status */
	public Integer getStatus() {
		return status;
	}

	/** @param status status to set */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
