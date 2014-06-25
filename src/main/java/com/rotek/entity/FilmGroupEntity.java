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
* @ClassName: FilmGroupEntity
* @Description: 膜  组信息实体类
* @Author WangJuZhu
* @date 2014年6月26日 上午5:51:06
* @Version:1.1.0
*/
@Table(name = "r_film_group")
public class FilmGroupEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = -5307102219600473328L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 工程ID
	
	@Column(name = "FILM_BH")
	private String film_bh ;   	// 膜组的组编号
	
	@Column(name = "FILM_MC")
	private String film_mc ;   	// 膜组的组名称

	@Column(name = "PLFS")
	private String plfs ;    	// 排列方式
	
	@Column(name = "MKGG")
	private Integer mkgg ;    	// 膜壳规格（N芯）
	
	@Column(name = "MZSL")
	private Integer mzsl ;    	// 膜总数量
	
	@Column(name = "CSL")
	private Integer csl ;    	// 产水量
	
	@Column(name = "MPP")
	private String mpp ;    	// 膜品牌
	
	@Column(name = "MXH")
	private String mxh ;    	// 膜型号
	
	@Column(name = "MHSL")
	private Double mhsl ;    	// 膜回收率
	
	@Column(name = "MCKDJ")
	private Double mckdj ;    	// 膜参考单价
	
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

	/** @return film_bh */
	public String getFilm_bh() {
		return film_bh;
	}

	/** @param film_bh film_bh to set */
	public void setFilm_bh(String film_bh) {
		this.film_bh = film_bh;
	}

	/** @return film_mc */
	public String getFilm_mc() {
		return film_mc;
	}

	/** @param film_mc film_mc to set */
	public void setFilm_mc(String film_mc) {
		this.film_mc = film_mc;
	}

	/** @return plfs */
	public String getPlfs() {
		return plfs;
	}

	/** @param plfs plfs to set */
	public void setPlfs(String plfs) {
		this.plfs = plfs;
	}

	/** @return mkgg */
	public Integer getMkgg() {
		return mkgg;
	}

	/** @param mkgg mkgg to set */
	public void setMkgg(Integer mkgg) {
		this.mkgg = mkgg;
	}

	/** @return mzsl */
	public Integer getMzsl() {
		return mzsl;
	}

	/** @param mzsl mzsl to set */
	public void setMzsl(Integer mzsl) {
		this.mzsl = mzsl;
	}

	/** @return csl */
	public Integer getCsl() {
		return csl;
	}

	/** @param csl csl to set */
	public void setCsl(Integer csl) {
		this.csl = csl;
	}

	/** @return mpp */
	public String getMpp() {
		return mpp;
	}

	/** @param mpp mpp to set */
	public void setMpp(String mpp) {
		this.mpp = mpp;
	}

	/** @return mxh */
	public String getMxh() {
		return mxh;
	}

	/** @param mxh mxh to set */
	public void setMxh(String mxh) {
		this.mxh = mxh;
	}

	/** @return mhsl */
	public Double getMhsl() {
		return mhsl;
	}

	/** @param mhsl mhsl to set */
	public void setMhsl(Double mhsl) {
		this.mhsl = mhsl;
	}

	/** @return mckdj */
	public Double getMckdj() {
		return mckdj;
	}

	/** @param mckdj mckdj to set */
	public void setMckdj(Double mckdj) {
		this.mckdj = mckdj;
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
