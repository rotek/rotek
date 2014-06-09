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
 * @ClassName:ProjectEntity
 * @Description:
 * @Author juzhu.w2013@gmail.com
 * @date 2014年6月9日 下午1:57:15
 * @Version:1.1.0
 */
@Table(name = "r_project")
public class ProjectEntity implements Serializable {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 4628046027257788856L;

	@Column(name="id")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "customer_id")
	private Integer customerId ;   		// 客户ID

	@Column(name = "pro_name")
	private String proName ;    			// 工程名称
	
	@Column(name = "pro_num")
	private String proNum ;    			// 工程编号
	
	@Column(name = "pro_model")
	private String proModel ;    		// 工程型号

	@Column(name = "pro_type")
	private Integer proType ;  			// 工程类别（1、普通工程；2、EMC工程）

	@Column(name = "pro_introduce")
	private String proIntroduce ;   	// 工程介绍

	@Column(name = "pro_pic")
	private	String	proPic ;      		// 工程照片	

	@Column(name = "pro_param")
	private String	proParam ;    		// 技术参数简介	

	@Column(name = "pro_param_affix")
	private String	proParamAffix ;  	// 技术参数附件	

	@Column(name = "pro_part")
	private String	proPart ;  			// 工程零件	

	@Column(name = "setup_time")
	private Date setUpTime ;      		// 安装时间	

	@Column(name = "start_use_time")
	private Date startUseTime ;			// 投运时间
	
	@Column(name = "create_user")
	private Integer createUser ; 		// 创建人
	
	@Column(name = "create_time")
	private Date createTime ; 			// 创建时间
	
	@Column(name = "status")
	private Integer status ; 			// 状态
	
	@Column(name = "remark")
	private String	remark ;  			// 备注	

	/** @return id */
	public Integer getId() {
		return id;
	}

	/** @param id id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return customerId */
	public Integer getCustomerId() {
		return customerId;
	}

	/** @param customerId customerId to set */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	/** @return proNum */
	public String getProNum() {
		return proNum;
	}

	/** @param proNum proNum to set */
	public void setProNum(String proNum) {
		this.proNum = proNum;
	}

	/** @return proModel */
	public String getProModel() {
		return proModel;
	}

	/** @param proModel proModel to set */
	public void setProModel(String proModel) {
		this.proModel = proModel;
	}

	/** @return proType */
	public Integer getProType() {
		return proType;
	}

	/** @param proType proType to set */
	public void setProType(Integer proType) {
		this.proType = proType;
	}

	/** @return proIntroduce */
	public String getProIntroduce() {
		return proIntroduce;
	}

	/** @param proIntroduce proIntroduce to set */
	public void setProIntroduce(String proIntroduce) {
		this.proIntroduce = proIntroduce;
	}

	/** @return proPic */
	public String getProPic() {
		return proPic;
	}

	/** @param proPic proPic to set */
	public void setProPic(String proPic) {
		this.proPic = proPic;
	}

	/** @return proParam */
	public String getProParam() {
		return proParam;
	}

	/** @param proParam proParam to set */
	public void setProParam(String proParam) {
		this.proParam = proParam;
	}

	/** @return proParamAffix */
	public String getProParamAffix() {
		return proParamAffix;
	}

	/** @param proParamAffix proParamAffix to set */
	public void setProParamAffix(String proParamAffix) {
		this.proParamAffix = proParamAffix;
	}

	/** @return proPart */
	public String getProPart() {
		return proPart;
	}

	/** @param proPart proPart to set */
	public void setProPart(String proPart) {
		this.proPart = proPart;
	}

	/** @return setUpTime */
	public Date getSetUpTime() {
		return setUpTime;
	}

	/** @param setUpTime setUpTime to set */
	public void setSetUpTime(Date setUpTime) {
		this.setUpTime = setUpTime;
	}

	/** @return startUseTime */
	public Date getStartUseTime() {
		return startUseTime;
	}

	/** @param startUseTime startUseTime to set */
	public void setStartUseTime(Date startUseTime) {
		this.startUseTime = startUseTime;
	}

	/** @return createUser */
	public Integer getCreateUser() {
		return createUser;
	}

	/** @param createUser createUser to set */
	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	/** @return createTime */
	public Date getCreateTime() {
		return createTime;
	}

	/** @param createTime createTime to set */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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

	/** @return proName */
	public String getProName() {
		return proName;
	}

	/** @param proName proName to set */
	public void setProName(String proName) {
		this.proName = proName;
	}
	
}
