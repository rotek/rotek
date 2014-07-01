package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.dto.ComponentGroupDto;
import com.rotek.entity.ComponentGroupEntity;
import com.rotek.entity.ProjectEntity;

/**
* @ClassName:ComponentDetailDao
* @Description:管理Dao实现
* @Author WangJuZhu
* @date 2014年7月1日 下午4:46:20
* @Version:1.1.0
*/
@Repository
public class ComponentDetailDao extends BaseDaoImpl{

	/**
	* @MethodName: listComDetail 
	* @Description: 根据条件分页查询组信息
	* @param sql
	* @param params
	* @param pager
	* @return List<ComponentGroupDto>
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ComponentGroupDto> listComDetail(String sql,Object[] params, ListPager pager) throws SQLException {
		return this.selectPage(sql, params, ComponentGroupDto.class, pager);
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
	* @MethodName: addComDetail 
	* @Description: 添加组信息
	* @param ComDetail
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void addComDetail(ComponentGroupEntity comgroup) throws SQLException {
		this.insert(comgroup);
	}
	
	/**
	* @MethodName: getComDetailById 
	* @Description: 根据ID查询组详情
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ComponentGroupEntity getComDetailById(Integer id) throws SQLException {
		String sql = "select * from r_component_group where id = ?";
		return this.selectOne(sql,new Integer[]{id},ComponentGroupEntity.class);
	}
	
	public ComponentGroupDto getOneComDetail(Integer id) throws SQLException {
		String sql = "select cgro.*,pro.GCMC as PROJECT_NAME from r_component_group cgro "
				+ " left join r_project pro on pro.id = cgro.r_project_id where cgro.id = ?";
		return this.selectOne(sql,new Integer[]{id},ComponentGroupDto.class);
	}
	
	/**
	* @MethodName: modifyComDetail 
	* @Description: 修改工程信息
	* @param project
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void modifyComDetail(ComponentGroupEntity comgroup) throws SQLException {
		this.update(comgroup);
	}

	/**
	* @MethodName: deleteComDetail 
	* @Description: 删除组信息
	* @param sql
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void deleteComDetail(String sql) throws SQLException {
		this.executeUpdate(sql);
	}
	
	
	
}
