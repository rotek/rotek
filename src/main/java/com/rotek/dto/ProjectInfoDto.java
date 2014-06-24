package com.rotek.dto;

import com.cta.platform.persistence.annotation.Column;
import com.rotek.entity.ProjectInfoEntity;

/**
* @ClassName: ProjectInfoDto
* @Description:
* @Author WangJuZhu
* @date 2014年6月24日 下午3:07:08
* @Version:1.1.0
*/
public class ProjectInfoDto extends ProjectInfoEntity {

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
