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
* @ClassName: DosesettingGroupEntity
* @Description: 加药装置  组信息实体类
* @Author WangJuZhu
* @date 2014年6月26日 上午6:44:14
* @Version:1.1.0
*/
@Table(name = "r_dosesetting_group")
public class DosesettingGroupEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = -5307102219600473328L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 工程ID
	
	@Column(name = "DOSE_BH")
	private String dose_bh ;   	// 加药装置组的组编号
	
	@Column(name = "DOSE_MC")
	private String dose_mc ;   	// 加药装置组的组名称

	@Column(name = "YJPP")
	private String yjpp ;    	// 药剂品牌
	
	@Column(name = "YJXH")
	private String yjxh ;    	// 药剂型号
	
	@Column(name = "DOSE_GL")
	private String dose_gl ;    	// 功率
	
	@Column(name = "YJND")
	private String yjnd ;    	// 药剂浓度
	
	@Column(name = "YJEDTJL")
	private String yjedtjl ;    	// 药剂额定添加量
	
	@Column(name = "YJCKDJ")
	private String yjckdj ;    	// 药剂参考单价
	
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

	/** @return dose_bh */
	public String getDose_bh() {
		return dose_bh;
	}

	/** @param dose_bh dose_bh to set */
	public void setDose_bh(String dose_bh) {
		this.dose_bh = dose_bh;
	}

	/** @return dose_mc */
	public String getDose_mc() {
		return dose_mc;
	}

	/** @param dose_mc dose_mc to set */
	public void setDose_mc(String dose_mc) {
		this.dose_mc = dose_mc;
	}

	/** @return yjpp */
	public String getYjpp() {
		return yjpp;
	}

	/** @param yjpp yjpp to set */
	public void setYjpp(String yjpp) {
		this.yjpp = yjpp;
	}

	/** @return yjxh */
	public String getYjxh() {
		return yjxh;
	}

	/** @param yjxh yjxh to set */
	public void setYjxh(String yjxh) {
		this.yjxh = yjxh;
	}

	/** @return dose_gl */
	public String getDose_gl() {
		return dose_gl;
	}

	/** @param dose_gl dose_gl to set */
	public void setDose_gl(String dose_gl) {
		this.dose_gl = dose_gl;
	}

	/** @return yjnd */
	public String getYjnd() {
		return yjnd;
	}

	/** @param yjnd yjnd to set */
	public void setYjnd(String yjnd) {
		this.yjnd = yjnd;
	}

	/** @return yjedtjl */
	public String getYjedtjl() {
		return yjedtjl;
	}

	/** @param yjedtjl yjedtjl to set */
	public void setYjedtjl(String yjedtjl) {
		this.yjedtjl = yjedtjl;
	}

	/** @return yjckdj */
	public String getYjckdj() {
		return yjckdj;
	}

	/** @param yjckdj yjckdj to set */
	public void setYjckdj(String yjckdj) {
		this.yjckdj = yjckdj;
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
