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
	private String project_name;               // 工程名称

	/** @return project_name */
	public String getProject_name() {
		return project_name;
	}

	/** @param project_name project_name to set */
	public void setProject_name(String project_name) {
		this.project_name = project_name;
	}

}
