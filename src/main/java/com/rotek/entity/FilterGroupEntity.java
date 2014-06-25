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
* @ClassName: FilterGroupEntity
* @Description: 过滤器  组信息实体类
* @Author WangJuZhu
* @date 2014年6月26日 上午5:30:29
* @Version:1.1.0
*/
@Table(name = "r_filter_group")
public class FilterGroupEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = 7083932437694991071L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 工程ID
	
	@Column(name = "FILTER_BH")
	private String filter_bh ;   	// 过滤器组的组编号
	
	@Column(name = "FILTER_MC")
	private String filter_mc ;   	// 过滤器组的组名称

	@Column(name = "FILTER_GG")
	private String filter_gg ;    	// 规格
	
	@Column(name = "FILTER_CLL")
	private Integer filter_cll ;    	// 处理量
	
	@Column(name = "GLJD")
	private Double gljd ;    	// 过滤精度
	
	@Column(name = "LXCC")
	private Double lxcc ;    	// 滤芯尺寸
	
	@Column(name = "LXSL")
	private Integer lxsl ;    	// 滤芯数量
	
	@Column(name = "LXCZ")
	private String lxcz ;    	// 滤芯材质
	
	@Column(name = "LXPP")
	private String lcpp ;    	// 滤芯品牌
	
	@Column(name = "LXXH")
	private String lxxh ;    	// 滤芯型号
	
	@Column(name = "LXJKXS")
	private String lxjkxs ;    	// 滤芯接口形式
	
	@Column(name = "LXCKDJ")
	private Double lxckdj ;    	// 滤芯参考单价
	
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

	/** @return filter_bh */
	public String getFilter_bh() {
		return filter_bh;
	}

	/** @param filter_bh filter_bh to set */
	public void setFilter_bh(String filter_bh) {
		this.filter_bh = filter_bh;
	}

	/** @return filter_mc */
	public String getFilter_mc() {
		return filter_mc;
	}

	/** @param filter_mc filter_mc to set */
	public void setFilter_mc(String filter_mc) {
		this.filter_mc = filter_mc;
	}

	/** @return filter_gg */
	public String getFilter_gg() {
		return filter_gg;
	}

	/** @param filter_gg filter_gg to set */
	public void setFilter_gg(String filter_gg) {
		this.filter_gg = filter_gg;
	}

	/** @return filter_cll */
	public Integer getFilter_cll() {
		return filter_cll;
	}

	/** @param filter_cll filter_cll to set */
	public void setFilter_cll(Integer filter_cll) {
		this.filter_cll = filter_cll;
	}

	/** @return gljd */
	public Double getGljd() {
		return gljd;
	}

	/** @param gljd gljd to set */
	public void setGljd(Double gljd) {
		this.gljd = gljd;
	}

	/** @return lxcc */
	public Double getLxcc() {
		return lxcc;
	}

	/** @param lxcc lxcc to set */
	public void setLxcc(Double lxcc) {
		this.lxcc = lxcc;
	}

	/** @return lxsl */
	public Integer getLxsl() {
		return lxsl;
	}

	/** @param lxsl lxsl to set */
	public void setLxsl(Integer lxsl) {
		this.lxsl = lxsl;
	}

	/** @return lxcz */
	public String getLxcz() {
		return lxcz;
	}

	/** @param lxcz lxcz to set */
	public void setLxcz(String lxcz) {
		this.lxcz = lxcz;
	}

	/** @return lcpp */
	public String getLcpp() {
		return lcpp;
	}

	/** @param lcpp lcpp to set */
	public void setLcpp(String lcpp) {
		this.lcpp = lcpp;
	}

	/** @return lxxh */
	public String getLxxh() {
		return lxxh;
	}

	/** @param lxxh lxxh to set */
	public void setLxxh(String lxxh) {
		this.lxxh = lxxh;
	}

	/** @return lxjkxs */
	public String getLxjkxs() {
		return lxjkxs;
	}

	/** @param lxjkxs lxjkxs to set */
	public void setLxjkxs(String lxjkxs) {
		this.lxjkxs = lxjkxs;
	}

	/** @return lxckdj */
	public Double getLxckdj() {
		return lxckdj;
	}

	/** @param lxckdj lxckdj to set */
	public void setLxckdj(Double lxckdj) {
		this.lxckdj = lxckdj;
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
