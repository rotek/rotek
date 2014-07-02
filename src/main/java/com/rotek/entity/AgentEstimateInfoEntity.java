/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.Ltd.
*/
package com.rotek.entity;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
* @ClassName:AgentEstimateInfoEntity
* @Description: 代理商评价信息实体
* @Author liusw
* @date 2014年6月28日 上午8:47:19
* @Version:1.1.0
*/
@Table(name = "r_agentestimateinfo")
public class AgentEstimateInfoEntity  extends BaseEntity {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 5853625596281635557L;

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name = "R_CUSTOMER_ID")
	private Integer r_customer_id ;                   // 客户ID外键

	@Column(name = "DLSXJPJ")                       // 五星（10分）、四星（8--9分）三星（6--7分）
	private Integer dlsxjpj ;    

	@Column(name = "DLSGLXZ")
	private String dlsglxz ;    			                    // 代理商管理细则

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

	public Integer getDlsxjpj() {
		return dlsxjpj;
	}

	public void setDlsxjpj(Integer dlsxjpj) {
		this.dlsxjpj = dlsxjpj;
	}

	public String getDlsglxz() {
		return dlsglxz;
	}

	public void setDlsglxz(String dlsglxz) {
		this.dlsglxz = dlsglxz;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
