package com.rotek.dto;

import java.util.List;

import com.cta.platform.persistence.annotation.Column;
import com.rotek.entity.ProjectEntity;

/**
* @ClassName:ProjectDto
* @Description: 工程DTO
* @Author WangJuZhu
* @date 2014年7月3日 下午5:25:50
* @Version:1.1.0
*/
public class ProjectDto extends ProjectEntity {
	
	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = -1223342357489672180L;
	
	@Column(name = "CUSTOMER_NAME")
	private String customer_name ;   // 客户名称
	
	private List<String> localCodes ;   // 现场编号

	/** @return customer_name */
	public String getCustomer_name() {
		return customer_name;
	}

	/** @param customer_name customer_name to set */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	/** @return localCodes */
	public List<String> getLocalCodes() {
		return localCodes;
	}

	/** @param localCodes localCodes to set */
	public void setLocalCodes(List<String> localCodes) {
		this.localCodes = localCodes;
	}
	

}
