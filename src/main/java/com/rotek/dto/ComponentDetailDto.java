package com.rotek.dto;

import com.cta.platform.persistence.annotation.Column;
import com.rotek.entity.ComponentGroupEntity;

/**
* @ClassName: ComponentDetailDto
* @Description: 零件详情
* @Author WangJuZhu
* @date 2014年7月1日 下午8:57:44
* @Version:1.1.0
*/
public class ComponentDetailDto extends ComponentGroupEntity {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = -1234096955270329798L;
	
	@Column(name = "PROJECT_NAME")
	private String project_name;               // 工程名称
	
	@Column(name = "GROUP_NAME")
	private String group_name;               // 组名称

	/** @return project_name */
	public String getProject_name() {
		return project_name;
	}

	/** @param project_name project_name to set */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

	/** @return group_name */
	public String getGroup_name() {
		return group_name;
	}

	/** @param group_name group_name to set */
	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

}
