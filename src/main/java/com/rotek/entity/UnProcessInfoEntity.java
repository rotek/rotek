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
* @ClassName:UnProcessInfoEntity
* @Description: 未处理信息统计表
* @Author liusw
* @date 2014年6月28日 上午8:48:14
* @Version:1.1.0
*/
@Table(name = "r_processinfo")
public class UnProcessInfoEntity  extends BaseEntity {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 893163806843869212L;

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name = "R_CUSTOMER_ID")
	private Integer r_customer_id ;                   // 客户ID外键

	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;                       // 工程ID外键

	@Column(name = "ERRORINFOLB")           // 未处理信息类别：1-报警未处理；2-提醒未处理；3-工程师未及时回复
	private Integer errorinfolb ;    

	@Column(name = "SPECIFIC_BH")
	private String specific_bh ;    			               // 零件部位编号
	
	@Column(name = "SPECIFIC_PART")
	private String specific_part ;    			            // 零件部位名称
	
	@Column(name = "JLRQ")
	private Date jlrq ;    			                               // 记录日期

	@Column(name = "ISDEALED")
	private Integer isdealed ;      		                   // 是否处理	

	@Column(name = "STATUS")
	private Integer status ;      		                       // 是否有效

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getR_customer_id() {
		return r_customer_id;
	}

	public void setR_customer_id(Integer r_customer_id) {
		this.r_customer_id = r_customer_id;
	}

	public Integer getR_project_id() {
		return r_project_id;
	}

	public void setR_project_id(Integer r_project_id) {
		this.r_project_id = r_project_id;
	}

	public Integer getErrorinfolb() {
		return errorinfolb;
	}

	public void setErrorinfolb(Integer errorinfolb) {
		this.errorinfolb = errorinfolb;
	}

	public String getSpecific_bh() {
		return specific_bh;
	}

	public void setSpecific_bh(String specific_bh) {
		this.specific_bh = specific_bh;
	}

	public String getSpecific_part() {
		return specific_part;
	}

	public void setSpecific_part(String specific_part) {
		this.specific_part = specific_part;
	}

	public Date getJlrq() {
		return jlrq;
	}

	public void setJlrq(Date jlrq) {
		this.jlrq = jlrq;
	}

	public Integer getIsdealed() {
		return isdealed;
	}

	public void setIsdealed(Integer isdealed) {
		this.isdealed = isdealed;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
