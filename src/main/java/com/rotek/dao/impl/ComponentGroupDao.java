package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.entity.ProjectEntity;

/**
* @ClassName:ProjectDao
* @Description: 工程信息管理Dao实现
* @Author WangJuZhu
* @date 2014年6月10日 下午4:37:19
* @Version:1.1.0
*/
@Repository
public class ComponentGroupDao extends BaseDaoImpl{

	/**
	* @MethodName: listProject 
	* @Description: 根据条件分页查询工程信息
	* @param sql
	* @param params
	* @param pager
	* @return List<ProjectEntity>
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ProjectEntity> listProject(String sql,Object[] params, ListPager pager) throws SQLException {
		return this.selectPage(sql, params, ProjectEntity.class, pager);
	}
	
	/**
	* @MethodName: listProjectByStatus 
	* @Description: 根据状态查询工程信息
	* @param status
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ProjectEntity> listProjectByStatus(Integer status) throws SQLException{
		String sql = "select * from r_project where status = " + status;
		return selectAll(sql, ProjectEntity.class);
	}
	
	/**
	* @MethodName: addProject 
	* @Description: 添加工程信息
	* @param project
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void addProject(ProjectEntity project) throws SQLException {
		this.insert(project);
	}
	
	/**
	* @MethodName: getProjectById 
	* @Description: 根据ID查询工程详情
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ProjectEntity getProjectById(Integer id) throws SQLException {
		String sql = "select * from r_project where id = ?";
		return this.selectOne(sql,new Integer[]{id},ProjectEntity.class);
	}
	
	/**
	* @MethodName: modifyProject 
	* @Description: 修改工程信息
	* @param project
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void modifyProject(ProjectEntity project) throws SQLException {
		this.update(project);
	}

	/**
	* @MethodName: deleteProject 
	* @Description: 删除工程信息
	* @param sql
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void deleteProject(String sql) throws SQLException {
		this.executeUpdate(sql);
	}
	
	
	
}
