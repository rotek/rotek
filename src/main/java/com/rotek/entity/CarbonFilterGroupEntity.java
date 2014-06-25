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
* @ClassName: CarbonFilterGroupEntity
* @Description: 碳滤器  组信息实体类
* @Author WangJuZhu
* @date 2014年6月25日 下午9:46:54
* @Version:1.1.0
*/
@Table(name = "r_carbonfilter_group")
public class CarbonFilterGroupEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = -5307102219600473328L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 工程ID
	
	@Column(name = "CARBON_BH")
	private String carbon_bh ;   	// 碳滤器组的组编号
	
	@Column(name = "CARBON_MC")
	private String carbon_mc ;   	// 碳滤器组的组名称

	@Column(name = "CARBON_GG")
	private String carbon_gg ;    	// 规格
	
	@Column(name = "CARBON_CLL")
	private Integer carbon_cll ;    	// 处理量
	
	@Column(name = "TLGD")
	private Integer tlgd ;    	// 填料高度
	
	@Column(name = "CARBON_CZ")
	private String carbon_cz ;    	// 材质
	
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

	/** @return carbon_bh */
	public String getCarbon_bh() {
		return carbon_bh;
	}

	/** @param carbon_bh carbon_bh to set */
	public void setCarbon_bh(String carbon_bh) {
		this.carbon_bh = carbon_bh;
	}

	/** @return carbon_mc */
	public String getCarbon_mc() {
		return carbon_mc;
	}

	/** @param carbon_mc carbon_mc to set */
	public void setCarbon_mc(String carbon_mc) {
		this.carbon_mc = carbon_mc;
	}

	/** @return carbon_gg */
	public String getCarbon_gg() {
		return carbon_gg;
	}

	/** @param carbon_gg carbon_gg to set */
	public void setCarbon_gg(String carbon_gg) {
		this.carbon_gg = carbon_gg;
	}

	/** @return carbon_cll */
	public Integer getCarbon_cll() {
		return carbon_cll;
	}

	/** @param carbon_cll carbon_cll to set */
	public void setCarbon_cll(Integer carbon_cll) {
		this.carbon_cll = carbon_cll;
	}

	/** @return tlgd */
	public Integer getTlgd() {
		return tlgd;
	}

	/** @param tlgd tlgd to set */
	public void setTlgd(Integer tlgd) {
		this.tlgd = tlgd;
	}

	/** @return carbon_cz */
	public String getCarbon_cz() {
		return carbon_cz;
	}

	/** @param carbon_cz carbon_cz to set */
	public void setCarbon_cz(String carbon_cz) {
		this.carbon_cz = carbon_cz;
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
