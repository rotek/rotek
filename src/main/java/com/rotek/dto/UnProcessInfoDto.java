package com.rotek.dto;

import com.cta.platform.persistence.annotation.Column;
import com.rotek.entity.UnProcessInfoEntity;

public class UnProcessInfoDto extends UnProcessInfoEntity {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = -7379970360209668460L;

	@Column(name = "CUSTOMER_MC")
	private String customer_mc;                        // 客户名称

	@Column(name = "PROJECT_MC")
	private String project_mc;                            // 工程名称

	public String getProject_mc() {
		return project_mc;
	}

	public void setProject_mc(String project_mc) {
		this.project_mc = project_mc;
	}

	public String getCustomer_mc() {
		return customer_mc;
	}

	public void setCustomer_mc(String customer_mc) {
		this.customer_mc = customer_mc;
	}

}
