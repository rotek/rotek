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
* @ClassName: TankGroupEntity
* @Description: 水箱  组信息实体类
* @Author WangJuZhu
* @date 2014年6月26日 上午6:54:12
* @Version:1.1.0
*/
@Table(name = "r_tank_group")
public class TankGroupEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = -5307102219600473328L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 工程ID
	
	@Column(name = "TANK_BH")
	private String tank_bh ;   	// 水箱组的组编号
	
	@Column(name = "TANK_MC")
	private String tank_mc ;   	// 水箱组的组名称

	@Column(name = "TANK_GG")
	private String tank_gg ;    	// 规格
	
	@Column(name = "TANK_RJ")
	private String tank_rj ;    	// 容积
	
	@Column(name = "OTHERS")
	private String others ;    	// 其它
	
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

	/** @return tank_bh */
	public String getTank_bh() {
		return tank_bh;
	}

	/** @param tank_bh tank_bh to set */
	public void setTank_bh(String tank_bh) {
		this.tank_bh = tank_bh;
	}

	/** @return tank_mc */
	public String getTank_mc() {
		return tank_mc;
	}

	/** @param tank_mc tank_mc to set */
	public void setTank_mc(String tank_mc) {
		this.tank_mc = tank_mc;
	}

	/** @return tank_gg */
	public String getTank_gg() {
		return tank_gg;
	}

	/** @param tank_gg tank_gg to set */
	public void setTank_gg(String tank_gg) {
		this.tank_gg = tank_gg;
	}

	/** @return tank_rj */
	public String getTank_rj() {
		return tank_rj;
	}

	/** @param tank_rj tank_rj to set */
	public void setTank_rj(String tank_rj) {
		this.tank_rj = tank_rj;
	}

	/** @return others */
	public String getOthers() {
		return others;
	}

	/** @param others others to set */
	public void setOthers(String others) {
		this.others = others;
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
