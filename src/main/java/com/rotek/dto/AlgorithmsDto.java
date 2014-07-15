package com.rotek.dto;

import com.cta.platform.persistence.annotation.Column;
import com.rotek.entity.AlgorithmsEntity;

/**
* @ClassName:AlgorithmsDto
* @Description: 算法设置
* @Author WangJuZhu
* @date 2014年7月16日 下午2:18:03
* @Version:1.1.0
*/
public class AlgorithmsDto extends AlgorithmsEntity {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = -1234096955270329798L;
	
	@Column(name = "CUSTOMER_NAME")
	private String customer_name;  			// 客户名称
	
	@Column(name = "PROJECT_NAME")
	private String project_name;   			// 工程名称
	
	@Column(name = "COMPONENT_GROUP_NAME")
	private String component_group_name;   	// 零件组名称
	
	@Column(name = "COMPONENT_NAME")
	private String component_name;   		// 零件名称

	/** @return customer_name */
	public String getCustomer_name() {
		return customer_name;
	}

	/** @param customer_name customer_name to set */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	/** @return project_name */
	public String getProject_name() {
		return project_name;
	}

	/** @param project_name project_name to set */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/** @return component_group_name */
	public String getComponent_group_name() {
		return component_group_name;
	}

	/** @param component_group_name component_group_name to set */
	public void setComponent_group_name(String component_group_name) {
		this.component_group_name = component_group_name;
	}

	/** @return component_name */
	public String getComponent_name() {
		return component_name;
	}

	/** @param component_name component_name to set */
	public void setComponent_name(String component_name) {
		this.component_name = component_name;
	}
	
}
