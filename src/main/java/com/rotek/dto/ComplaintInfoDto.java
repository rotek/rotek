package com.rotek.dto;

import com.cta.platform.persistence.annotation.Column;
import com.rotek.entity.ComplaintInfoEntity;

public class ComplaintInfoDto extends ComplaintInfoEntity  {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 214874819549404136L;

	@Column(name = "CUSTOMER_MC")
	private String customer_mc;                            // 客户名称

	public String getCustomer_mc() {
		return customer_mc;
	}

	public void setCustomer_mc(String customer_mc) {
		this.customer_mc = customer_mc;
	}

}
