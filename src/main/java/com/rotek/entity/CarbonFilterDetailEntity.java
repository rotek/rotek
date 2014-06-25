/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.Ltd.
*/
package com.rotek.entity;

import java.io.Serializable;
import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
* @ClassName: CarbonFilterDetailEntity
* @Description: 碳滤器  详细信息（参数的额定值）实体类
* @Author WangJuZhu
* @date 2014年6月25日 下午9:47:15
* @Version:1.1.0
*/
@Table(name = "r_carbonfilter_detail")
public class CarbonFilterDetailEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = 467569802272805905L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_CARBONFILTER_GROUP_ID")
	private Integer r_carbonfilter_group_id ;   	// 碳滤器组ID
	
	@Column(name = "SPECIFIC_PART")
	private String specific_part ;   	// 具体的零件名称，比如1号碳滤器前，1号碳滤器后
	
	@Column(name = "SPECIFIC_BH")
	private String specific_bh ;   	// 具体的零件编号，比如1号碳滤器前编号  000301，1号碳滤器后   000302 等
	
	@Column(name = "LLSL")
	private Integer llsl ;   	// 滤料数量

	@Column(name = "CARBON_PP")
	private String carbon_pp ;    	// 品牌
	
	@Column(name = "CARBON_XH")
	private String carbon_xh ;    	// 型号
	
	@Column(name = "CKDJ")
	private Double ckdj ;    	// 参考单价
	
	@Column(name = "EDGHSJ")
	private Date edghsj ;    	// 额定更换时间
	
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

	/** @return r_carbonfilter_group_id */
	public Integer getR_carbonfilter_group_id() {
		return r_carbonfilter_group_id;
	}

	/** @param r_carbonfilter_group_id r_carbonfilter_group_id to set */
	public void setR_carbonfilter_group_id(Integer r_carbonfilter_group_id) {
		this.r_carbonfilter_group_id = r_carbonfilter_group_id;
	}

	/** @return specific_part */
	public String getSpecific_part() {
		return specific_part;
	}

	/** @param specific_part specific_part to set */
	public void setSpecific_part(String specific_part) {
		this.specific_part = specific_part;
	}

	/** @return specific_bh */
	public String getSpecific_bh() {
		return specific_bh;
	}

	/** @param specific_bh specific_bh to set */
	public void setSpecific_bh(String specific_bh) {
		this.specific_bh = specific_bh;
	}

	/** @return llsl */
	public Integer getLlsl() {
		return llsl;
	}

	/** @param llsl llsl to set */
	public void setLlsl(Integer llsl) {
		this.llsl = llsl;
	}

	/** @return carbon_pp */
	public String getCarbon_pp() {
		return carbon_pp;
	}

	/** @param carbon_pp carbon_pp to set */
	public void setCarbon_pp(String carbon_pp) {
		this.carbon_pp = carbon_pp;
	}

	/** @return carbon_xh */
	public String getCarbon_xh() {
		return carbon_xh;
	}

	/** @param carbon_xh carbon_xh to set */
	public void setCarbon_xh(String carbon_xh) {
		this.carbon_xh = carbon_xh;
	}

	/** @return ckdj */
	public Double getCkdj() {
		return ckdj;
	}

	/** @param ckdj ckdj to set */
	public void setCkdj(Double ckdj) {
		this.ckdj = ckdj;
	}

	/** @return edghsj */
	public Date getEdghsj() {
		return edghsj;
	}

	/** @param edghsj edghsj to set */
	public void setEdghsj(Date edghsj) {
		this.edghsj = edghsj;
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
