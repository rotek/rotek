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
* @ClassName: UvsterilizerGroupEntity
* @Description: 紫外杀菌器  组信息实体类
* @Author WangJuZhu
* @date 2014年6月26日 上午7:00:05
* @Version:1.1.0
*/
@Table(name = "r_uvsterilizer_group")
public class UvsterilizerGroupEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = -5307102219600473328L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 工程ID
	
	@Column(name = "UVSTER_BH")
	private String uvster_bh ;   	// 紫外杀菌器组的组编号
	
	@Column(name = "UVSTER_MC")
	private String uvster_mc ;   	// 紫外杀菌器组的组名称

	@Column(name = "CLL")
	private String cll ;    	// 处理量
	
	@Column(name = "DGSM")
	private Integer dgsm ;    	// 灯管寿命（N小时）
	
	@Column(name = "DGSL")
	private Integer dgsl ;    	// 灯管数量
	
	@Column(name = "DGPP")
	private String dgpp ;    	// 灯管品牌
	
	@Column(name = "DGXH")
	private String dgxh ;    	// 灯管型号
	
	@Column(name = "DGGL")
	private String dggl ;    	// 灯管功率
	
	@Column(name = "DGCKDJ")
	private Double dgckdj ;    	// 灯管参考单价
	
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

	/** @return uvster_bh */
	public String getUvster_bh() {
		return uvster_bh;
	}

	/** @param uvster_bh uvster_bh to set */
	public void setUvster_bh(String uvster_bh) {
		this.uvster_bh = uvster_bh;
	}

	/** @return uvster_mc */
	public String getUvster_mc() {
		return uvster_mc;
	}

	/** @param uvster_mc uvster_mc to set */
	public void setUvster_mc(String uvster_mc) {
		this.uvster_mc = uvster_mc;
	}

	/** @return cll */
	public String getCll() {
		return cll;
	}

	/** @param cll cll to set */
	public void setCll(String cll) {
		this.cll = cll;
	}

	/** @return dgsm */
	public Integer getDgsm() {
		return dgsm;
	}

	/** @param dgsm dgsm to set */
	public void setDgsm(Integer dgsm) {
		this.dgsm = dgsm;
	}

	/** @return dgsl */
	public Integer getDgsl() {
		return dgsl;
	}

	/** @param dgsl dgsl to set */
	public void setDgsl(Integer dgsl) {
		this.dgsl = dgsl;
	}

	/** @return dgpp */
	public String getDgpp() {
		return dgpp;
	}

	/** @param dgpp dgpp to set */
	public void setDgpp(String dgpp) {
		this.dgpp = dgpp;
	}

	/** @return dgxh */
	public String getDgxh() {
		return dgxh;
	}

	/** @param dgxh dgxh to set */
	public void setDgxh(String dgxh) {
		this.dgxh = dgxh;
	}

	/** @return dggl */
	public String getDggl() {
		return dggl;
	}

	/** @param dggl dggl to set */
	public void setDggl(String dggl) {
		this.dggl = dggl;
	}

	/** @return dgckdj */
	public Double getDgckdj() {
		return dgckdj;
	}

	/** @param dgckdj dgckdj to set */
	public void setDgckdj(Double dgckdj) {
		this.dgckdj = dgckdj;
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
