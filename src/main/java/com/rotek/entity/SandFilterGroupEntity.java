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
* @ClassName: SandFilterGroupEntity
* @Description: 砂滤器  组信息实体类
* @Author WangJuZhu
* @date 2014年6月25日 上午5:45:02
* @Version:1.1.0
*/
@Table(name = "r_sandfilter_group")
public class SandFilterGroupEntity implements Serializable {

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

	@Column(name = "SAND_GG")
	private String sand_gg ;    	// 规格
	
	@Column(name = "SAND_CLL")
	private Integer sand_cll ;    	// 处理量
	
	@Column(name = "TLGD")
	private Integer tlgd ;    	// 填料高度
	
	@Column(name = "SAND_CZ")
	private String sand_cz ;    	// 材质
	
	@Column(name = "LLSL")
	private Integer llsl ;    	// 滤料数量
	
	@Column(name = "LLPP")
	private String llpp ;    	// 滤料品牌
	
	@Column(name = "LLXH")
	private String llxh ;    	// 滤料型号
	
	@Column(name = "LLCKDJ")
	private Double llckdj ;    	// 滤料参考单价
	
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

	/** @return sand_gg */
	public String getSand_gg() {
		return sand_gg;
	}

	/** @param sand_gg sand_gg to set */
	public void setSand_gg(String sand_gg) {
		this.sand_gg = sand_gg;
	}

	/** @return sand_cll */
	public Integer getSand_cll() {
		return sand_cll;
	}

	/** @param sand_cll sand_cll to set */
	public void setSand_cll(Integer sand_cll) {
		this.sand_cll = sand_cll;
	}

	/** @return tlgd */
	public Integer getTlgd() {
		return tlgd;
	}

	/** @param tlgd tlgd to set */
	public void setTlgd(Integer tlgd) {
		this.tlgd = tlgd;
	}

	/** @return sand_cz */
	public String getSand_cz() {
		return sand_cz;
	}

	/** @param sand_cz sand_cz to set */
	public void setSand_cz(String sand_cz) {
		this.sand_cz = sand_cz;
	}

	/** @return llsl */
	public Integer getLlsl() {
		return llsl;
	}

	/** @param llsl llsl to set */
	public void setLlsl(Integer llsl) {
		this.llsl = llsl;
	}

	/** @return llpp */
	public String getLlpp() {
		return llpp;
	}

	/** @param llpp llpp to set */
	public void setLlpp(String llpp) {
		this.llpp = llpp;
	}

	/** @return llxh */
	public String getLlxh() {
		return llxh;
	}

	/** @param llxh llxh to set */
	public void setLlxh(String llxh) {
		this.llxh = llxh;
	}

	/** @return llckdj */
	public Double getLlckdj() {
		return llckdj;
	}

	/** @param llckdj llckdj to set */
	public void setLlckdj(Double llckdj) {
		this.llckdj = llckdj;
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
