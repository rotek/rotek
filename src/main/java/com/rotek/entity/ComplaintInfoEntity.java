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
* @ClassName:ComplaintInfoEntity
* @Description: 投诉信息统计实体
* @Author liusw
* @date 2014年6月28日 上午8:48:53
* @Version:1.1.0
*/
@Table(name = "r_complaintinfo")
public class ComplaintInfoEntity  extends BaseEntity {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 7178939025471619901L;

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name = "R_CUSTOMER_ID")
	private Integer r_customer_id ;                   // 客户ID外键

	@Column(name = "TSDW")                           // 投诉单位
	private String tsdw ;    

	@Column(name = "TSSX")
	private String tssx ;    			                        // 投诉事项

	@Column(name = "TSSJ")
	private Date tssj ;    			                           // 投诉时间

	@Column(name = "STATUS")
	private Integer status ;      		                   // 是否可用

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

	public String getTsdw() {
		return tsdw;
	}

	public void setTsdw(String tsdw) {
		this.tsdw = tsdw;
	}

	public String getTssx() {
		return tssx;
	}

	public void setTssx(String tssx) {
		this.tssx = tssx;
	}

	public Date getTssj() {
		return tssj;
	}

	public void setTssj(Date tssj) {
		this.tssj = tssj;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	
}
