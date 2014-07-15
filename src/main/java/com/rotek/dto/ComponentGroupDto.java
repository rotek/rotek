package com.rotek.dto;

import com.cta.platform.persistence.annotation.Column;
import com.rotek.entity.ComponentGroupEntity;

/**
* @ClassName: ComponentGroupDto
* @Description: 零件组
* @Author WangJuZhu
* @date 2014年6月30日 下午9:31:09
* @Version:1.1.0
*/
public class ComponentGroupDto extends ComponentGroupEntity {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = -1234096955270329798L;
	
	@Column(name = "PROJECT_NAME")
	private String project_name;   // 工程名称
	
	@Column(name = "CUSTOMER_ID")
	private Integer customer_id;   // 客户ID
	
	@Column(name = "CUSTOMER_NAME")
	private String customer_name;  // 客户名称
	
	/** @return project_name */
	public String getProject_name() {
		return project_name;
	}

	/** @param project_name project_name to set */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/** @return customer_id */
	public Integer getCustomer_id() {
		return customer_id;
	}

	/** @param customer_id customer_id to set */
	public void setCustomer_id(Integer customer_id) {
		this.customer_id = customer_id;
	}

	/** @return customer_name */
	public String getCustomer_name() {
		return customer_name;
	}

	/** @param customer_name customer_name to set */
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

}
