/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.Ltd.
*/
package com.rotek.entity;

import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName:ProjectEntity
 * @Description:
 * @Author juzhu.w2013@gmail.com
 * @date 2014年6月9日 下午1:57:15
 * @Version:1.1.0
 */
@Table(name = "r_project")
public class ProjectEntity extends BaseEntity {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 4628046027257788856L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "LOCALE_PROJECT_ID")
	private Integer locale_project_id ;   	// 现场的工程ID
	
	@Column(name = "LOCALE_GCBH")
	private String locale_gcbh ;   	// 现场工程编号
	
	@Column(name = "R_CUSTOMER_ID")
	private Integer r_customer_id ;   	// 客户ID

	@Column(name = "GCMC")
	private String gcmc ;    	// 工程名称
	
	@Column(name = "GCBH")
	private String gcbh ;    	// 工程编号
	
	@Column(name = "GCXH")
	private String gcxh ;    	// 工程型号

	@Column(name = "GCLB")
	private Integer gclb ;  	// 工程类别（1、托管服务；2、EMC工程）

	@Column(name = "GCJS")
	private String gcjs ;   	// 工程介绍

	@Column(name = "GCZP")
	private	String	gczp ;      // 工程照片	

	@Column(name = "JSCSJJ")
	private String	jscsjj ;    // 技术参数简介	

	@Column(name = "JSCSFJ")
	private String	jscsfj ;  	// 技术参数附件	

	@Column(name = "GCLJ")
	private String	gclj ;  	// 工程零件	

	@Column(name = "AZSJ")
	private Date azsj ;      	// 安装时间	

	@Column(name = "TYSJ")
	private Date tysj ;			// 投运时间
	
	@Column(name = "CJR")
	private Integer cjr ; 		// 创建人
	
	@Column(name = "CJSJ")
	private Date cjsj ; 		// 创建时间
	
	@Column(name = "STATUS")
	private Integer status ; 		// 状态
	
	@Column(name = "REMARK")
	private String	remark ;  		// 备注	

	/** @return id */
	public Integer getId() {
		return id;
	}

	/** @param id id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return locale_project_id */
	public Integer getLocale_project_id() {
		return locale_project_id;
	}

	/** @param locale_project_id locale_project_id to set */
	public void setLocale_project_id(Integer locale_project_id) {
		this.locale_project_id = locale_project_id;
	}

	/** @return locale_gcbh */
	public String getLocale_gcbh() {
		return locale_gcbh;
	}

	/** @param locale_gcbh locale_gcbh to set */
	public void setLocale_gcbh(String locale_gcbh) {
		this.locale_gcbh = locale_gcbh;
	}

	/** @return r_customer_id */
	public Integer getR_customer_id() {
		return r_customer_id;
	}

	/** @param r_customer_id r_customer_id to set */
	public void setR_customer_id(Integer r_customer_id) {
		this.r_customer_id = r_customer_id;
	}

	/** @return gcmc */
	public String getGcmc() {
		return gcmc;
	}

	/** @param gcmc gcmc to set */
	public void setGcmc(String gcmc) {
		this.gcmc = gcmc;
	}

	/** @return gcbh */
	public String getGcbh() {
		return gcbh;
	}

	/** @param gcbh gcbh to set */
	public void setGcbh(String gcbh) {
		this.gcbh = gcbh;
	}

	/** @return gcxh */
	public String getGcxh() {
		return gcxh;
	}

	/** @param gcxh gcxh to set */
	public void setGcxh(String gcxh) {
		this.gcxh = gcxh;
	}

	/** @return gclb */
	public Integer getGclb() {
		return gclb;
	}

	/** @param gclb gclb to set */
	public void setGclb(Integer gclb) {
		this.gclb = gclb;
	}

	/** @return gcjs */
	public String getGcjs() {
		return gcjs;
	}

	/** @param gcjs gcjs to set */
	public void setGcjs(String gcjs) {
		this.gcjs = gcjs;
	}

	/** @return gczp */
	public String getGczp() {
		return gczp;
	}

	/** @param gczp gczp to set */
	public void setGczp(String gczp) {
		this.gczp = gczp;
	}

	/** @return jscsjj */
	public String getJscsjj() {
		return jscsjj;
	}

	/** @param jscsjj jscsjj to set */
	public void setJscsjj(String jscsjj) {
		this.jscsjj = jscsjj;
	}

	/** @return jscsfj */
	public String getJscsfj() {
		return jscsfj;
	}

	/** @param jscsfj jscsfj to set */
	public void setJscsfj(String jscsfj) {
		this.jscsfj = jscsfj;
	}

	/** @return gclj */
	public String getGclj() {
		return gclj;
	}

	/** @param gclj gclj to set */
	public void setGclj(String gclj) {
		this.gclj = gclj;
	}

	/** @return azsj */
	public Date getAzsj() {
		return azsj;
	}

	/** @param azsj azsj to set */
	public void setAzsj(Date azsj) {
		this.azsj = azsj;
	}

	/** @return tysj */
	public Date getTysj() {
		return tysj;
	}

	/** @param tysj tysj to set */
	public void setTysj(Date tysj) {
		this.tysj = tysj;
	}

	/** @return cjr */
	public Integer getCjr() {
		return cjr;
	}

	/** @param cjr cjr to set */
	public void setCjr(Integer cjr) {
		this.cjr = cjr;
	}

	/** @return cjsj */
	public Date getCjsj() {
		return cjsj;
	}

	/** @param cjsj cjsj to set */
	public void setCjsj(Date cjsj) {
		this.cjsj = cjsj;
	}

	/** @return status */
	public Integer getStatus() {
		return status;
	}

	/** @param status status to set */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** @return remark */
	public String getRemark() {
		return remark;
	}

	/** @param remark remark to set */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
}
