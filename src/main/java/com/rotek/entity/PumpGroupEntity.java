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
* @ClassName:GroupPumpEntity
* @Description: 泵  组信息实体类
* @Author WangJuZhu
* @date 2014年6月25日 上午5:45:02
* @Version:1.1.0
*/
@Table(name = "r_pump_group")
public class PumpGroupEntity implements Serializable {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 4628046027257788856L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 工程ID
	
	@Column(name = "LJBH")
	private String ljbh ;   	// 零件编号
	
	@Column(name = "LJBM")
	private String ljbm ;   	// 零件别名

	@Column(name = "PUMP_PP")
	private String pump_pp ;    	// 泵品牌
	
	@Column(name = "PUMP_XH")
	private String pump_xh ;    	// 泵型号
	
	@Column(name = "PUMP_GL")
	private String pump_gl ;    	// 泵功率
	
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

	/** @return ljbh */
	public String getLjbh() {
		return ljbh;
	}

	/** @param ljbh ljbh to set */
	public void setLjbh(String ljbh) {
		this.ljbh = ljbh;
	}

	/** @return ljbm */
	public String getLjbm() {
		return ljbm;
	}

	/** @param ljbm ljbm to set */
	public void setLjbm(String ljbm) {
		this.ljbm = ljbm;
	}

	/** @return pump_pp */
	public String getPump_pp() {
		return pump_pp;
	}

	/** @param pump_pp pump_pp to set */
	public void setPump_pp(String pump_pp) {
		this.pump_pp = pump_pp;
	}

	/** @return pump_xh */
	public String getPump_xh() {
		return pump_xh;
	}

	/** @param pump_xh pump_xh to set */
	public void setPump_xh(String pump_xh) {
		this.pump_xh = pump_xh;
	}

	/** @return pump_gl */
	public String getPump_gl() {
		return pump_gl;
	}

	/** @param pump_gl pump_gl to set */
	public void setPump_gl(String pump_gl) {
		this.pump_gl = pump_gl;
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
