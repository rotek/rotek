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
* @ClassName: SoftenerGroupEntity
* @Description: 软化器  组信息实体类
* @Author WangJuZhu
* @date 2014年6月25日 下午10:15:25
* @Version:1.1.0
*/
@Table(name = "r_softener_group")
public class SoftenerGroupEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = 7083932437694991071L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 工程ID
	
	@Column(name = "SOFTENER_BH")
	private String softener_bh ;   	// 软化器组的组编号
	
	@Column(name = "SOFTENER_MC")
	private String softener_mc ;   	// 软化器组的组名称

	@Column(name = "SOFTENER_GG")
	private String softener_gg ;    	// 规格
	
	@Column(name = "SOFTENER_CLL")
	private Integer softener_cll ;    	// 处理量
	
	@Column(name = "TLGD")
	private Integer tlgd ;    	// 填料高度
	
	@Column(name = "SOFTENER_CZ")
	private String softener_cz ;    	// 材质
	
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

	/** @return softener_bh */
	public String getSoftener_bh() {
		return softener_bh;
	}

	/** @param softener_bh softener_bh to set */
	public void setSoftener_bh(String softener_bh) {
		this.softener_bh = softener_bh;
	}

	/** @return softener_mc */
	public String getSoftener_mc() {
		return softener_mc;
	}

	/** @param softener_mc softener_mc to set */
	public void setSoftener_mc(String softener_mc) {
		this.softener_mc = softener_mc;
	}

	/** @return softener_gg */
	public String getSoftener_gg() {
		return softener_gg;
	}

	/** @param softener_gg softener_gg to set */
	public void setSoftener_gg(String softener_gg) {
		this.softener_gg = softener_gg;
	}

	/** @return softener_cll */
	public Integer getSoftener_cll() {
		return softener_cll;
	}

	/** @param softener_cll softener_cll to set */
	public void setSoftener_cll(Integer softener_cll) {
		this.softener_cll = softener_cll;
	}

	/** @return tlgd */
	public Integer getTlgd() {
		return tlgd;
	}

	/** @param tlgd tlgd to set */
	public void setTlgd(Integer tlgd) {
		this.tlgd = tlgd;
	}

	/** @return softener_cz */
	public String getSoftener_cz() {
		return softener_cz;
	}

	/** @param softener_cz softener_cz to set */
	public void setSoftener_cz(String softener_cz) {
		this.softener_cz = softener_cz;
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
