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
* @ClassName: FilmDetailEntity
* @Description: 膜  详细信息（参数的额定值）实体类
* @Author WangJuZhu
* @date 2014年6月26日 上午5:59:07
* @Version:1.1.0
*/
@Table(name = "r_film_detail")
public class FilmDetailEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = 467569802272805905L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_FILM_GROUP_ID")
	private Integer r_film_group_id ;   	// 膜组ID
	
	@Column(name = "SPECIFIC_PART")
	private String specific_part ;   	// 具体的零件名称，比如1号膜前，1号膜后
	
	@Column(name = "SPECIFIC_BH")
	private String specific_bh ;   	// 具体的零件编号，比如1号膜前编号  000301，1号膜后   000302 等
	
	@Column(name = "EDGHSJ")
	private Date edghsj ;   	// 额定更换时间

	@Column(name = "EDQXSJ")
	private Date edqxsj ;    	// 额定清洗时间
	
	@Column(name = "EDQXYC")
	private Double edqxyc ;    	// 额定清洗压差
	
	@Column(name = "EDGHYC")
	private Double edghyc ;    	// 额定更换压差
	
	@Column(name = "EDLL")
	private Double edll ;    	// 额定流量
	
	@Column(name = "EDDDL")
	private Double edddl ;    	// 额定电导率
	
	@Column(name = "EDHSL")
	private Double edhsl ;    	// 额定回收率
	
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

	/** @return r_film_group_id */
	public Integer getR_film_group_id() {
		return r_film_group_id;
	}

	/** @param r_film_group_id r_film_group_id to set */
	public void setR_film_group_id(Integer r_film_group_id) {
		this.r_film_group_id = r_film_group_id;
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

	/** @return edghsj */
	public Date getEdghsj() {
		return edghsj;
	}

	/** @param edghsj edghsj to set */
	public void setEdghsj(Date edghsj) {
		this.edghsj = edghsj;
	}

	/** @return edqxsj */
	public Date getEdqxsj() {
		return edqxsj;
	}

	/** @param edqxsj edqxsj to set */
	public void setEdqxsj(Date edqxsj) {
		this.edqxsj = edqxsj;
	}

	/** @return edqxyc */
	public Double getEdqxyc() {
		return edqxyc;
	}

	/** @param edqxyc edqxyc to set */
	public void setEdqxyc(Double edqxyc) {
		this.edqxyc = edqxyc;
	}

	/** @return edghyc */
	public Double getEdghyc() {
		return edghyc;
	}

	/** @param edghyc edghyc to set */
	public void setEdghyc(Double edghyc) {
		this.edghyc = edghyc;
	}

	/** @return edll */
	public Double getEdll() {
		return edll;
	}

	/** @param edll edll to set */
	public void setEdll(Double edll) {
		this.edll = edll;
	}

	/** @return edddl */
	public Double getEdddl() {
		return edddl;
	}

	/** @param edddl edddl to set */
	public void setEdddl(Double edddl) {
		this.edddl = edddl;
	}

	/** @return edhsl */
	public Double getEdhsl() {
		return edhsl;
	}

	/** @param edhsl edhsl to set */
	public void setEdhsl(Double edhsl) {
		this.edhsl = edhsl;
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
