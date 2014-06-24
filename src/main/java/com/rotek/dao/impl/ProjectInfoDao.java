package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.dto.ProjectInfoDto;
import com.rotek.entity.ProjectInfoEntity;

/**
* @ClassName: ProjectInfoDao
* @Description: 工程资料信息管理Dao实现
* @Author WangJuZhu
* @date 2014年6月10日 下午4:37:19
* @Version:1.1.0
*/
@Repository
public class ProjectInfoDao extends BaseDaoImpl{

	/**
	* @MethodName: listProjectInfo 
	* @Description: 根据条件分页查询工程资料信息
	* @param sql
	* @param params
	* @param pager
	* @return List<listProjectInfo>
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ProjectInfoDto> listProjectInfo(String sql,Object[] params, ListPager pager) throws SQLException {
		return this.selectPage(sql, params, ProjectInfoDto.class, pager);
	}
	
	/**
	* @MethodName: addProjectInfo 
	* @Description: 添加工程资料信息
	* @param project
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void addProjectInfo(ProjectInfoEntity project) throws SQLException {
		this.insert(project);
	}
	
	/**
	* @MethodName: getProjectById 
	* @Description: 根据ID查询工程资料详情
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ProjectInfoEntity getProjectInfoById(Integer id) throws SQLException {
		String sql = "select * from r_project_info where id = ?";
		return this.selectOne(sql,new Integer[]{id},ProjectInfoEntity.class);
	}
	
	/**
	* @MethodName: modifyProjectInfo 
	* @Description: 修改工程资料信息
	* @param project
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void modifyProjectInfo(ProjectInfoEntity project) throws SQLException {
		this.update(project);
	}

	/**
	* @MethodName: deleteProjectInfo 
	* @Description: 删除工程资料信息
	* @param sql
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void deleteProjectInfo(String sql) throws SQLException {
		this.executeUpdate(sql);
	}
	
	
	
}
