package com.rotek.dto;

import com.cta.platform.persistence.annotation.Column;
import com.rotek.entity.AgentEstimateInfoEntity;

public class AgentEstimateInfoDto extends AgentEstimateInfoEntity {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 4046788732311058487L;

	@Column(name = "CUSTOMER_MC")
	private String customer_mc;                         // 上级名称

	public String getCustomer_mc() {
		return customer_mc;
	}

	public void setCustomer_mc(String customer_mc) {
		this.customer_mc = customer_mc;
	}

}
