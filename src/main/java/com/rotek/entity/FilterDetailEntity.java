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
* @ClassName: FilterDetailEntity
* @Description: 过滤器  详细信息（参数的额定值）实体类
* @Author WangJuZhu
* @date 2014年6月26日 上午5:41:41
* @Version:1.1.0
*/
@Table(name = "r_filter_detail")
public class FilterDetailEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = 467569802272805905L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_FILTER_GROUP_ID")
	private Integer r_filter_group_id ;   	//  过滤器组ID
	
	@Column(name = "SPECIFIC_PART")
	private String specific_part ;   	// 具体的零件名称，比如1号过滤器前，1号过滤器后
	
	@Column(name = "SPECIFIC_BH")
	private String specific_bh ;   	// 具体的零件编号，比如1号过滤器前编号  000301，1号过滤器后   000302 等
	
	@Column(name = "EDYC")
	private Double edyc ;    	// 额定压差
	
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

	/** @return r_filter_group_id */
	public Integer getR_filter_group_id() {
		return r_filter_group_id;
	}

	/** @param r_filter_group_id r_filter_group_id to set */
	public void setR_filter_group_id(Integer r_filter_group_id) {
		this.r_filter_group_id = r_filter_group_id;
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

	/** @return edyc */
	public Double getEdyc() {
		return edyc;
	}

	/** @param edyc edyc to set */
	public void setEdyc(Double edyc) {
		this.edyc = edyc;
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
