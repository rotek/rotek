package com.rotek.dto;

import com.rotek.entity.CustomerDocEntity;
import com.cta.platform.persistence.annotation.Column;

/**
* @ClassName:CustomerDocDto
* @Description:
* @Author liusw
* @date 2014年6月22日 上午10:14:24
* @Version:1.1.0
*/
public class CustomerDocDto extends CustomerDocEntity {

	@Column(name = "SUPER_MC")
	private String super_mc;               // 资料所属人名称

	public String getSuper_mc() {
		return super_mc;
	}

	public void setSuper_mc(String super_mc) {
		this.super_mc = super_mc;
	}

}
