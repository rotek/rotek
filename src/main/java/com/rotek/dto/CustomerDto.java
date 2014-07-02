/**
 * @FileName: CustomerDto.java
 * @Package com.rotek.dto
 * @Description: TODO
 * @author liusw
 * @date 2014-6-21 上午09:46:23
 * @version V1.0
 */
package com.rotek.dto;

import com.rotek.entity.CustomerEntity;
import com.cta.platform.persistence.annotation.Column;

public class CustomerDto extends CustomerEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8555827003338251372L;
	
	@Column(name = "SUPER_MC")
	private String super_mc;// 上级名称

	public String getSuper_mc() {
		return super_mc;
	}

	public void setSuper_mc(String super_mc) {
		this.super_mc = super_mc;
	}

}
