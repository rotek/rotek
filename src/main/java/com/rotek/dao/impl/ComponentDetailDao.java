package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.dto.ComponentDetailDto;
import com.rotek.entity.ComponentDetailEntity;
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
	public List<ComponentDetailDto> listComDetail(String sql,Object[] params, ListPager pager) throws SQLException {
		return this.selectPage(sql, params, ComponentDetailDto.class, pager);
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
	public void addComDetail(ComponentDetailEntity comgroup) throws SQLException {
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
	public ComponentDetailEntity getComDetailById(Integer id) throws SQLException {
		String sql = "select * from r_component_detail where id = ?";
		return this.selectOne(sql,new Integer[]{id},ComponentDetailEntity.class);
	}
	
	public ComponentDetailDto getOneComDetail(Integer id) throws SQLException {
		String sql = "select cgro.*,pro.GCMC as PROJECT_NAME,cgrop.GROUP_MC AS GROUP_NAME from r_component_detail cgro "
				+ " left join r_project pro on pro.id = cgro.r_project_id"
				+ " left join r_component_group cgrop on cgrop.id = cgro.R_COMPONENT_GROUP_ID "
				+ " where cgro.id = ?";
		return this.selectOne(sql,new Integer[]{id},ComponentDetailDto.class);
	}
	
	/**
	* @MethodName: modifyComDetail 
	* @Description: 修改工程信息
	* @param project
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void modifyComDetail(ComponentDetailEntity comgroup) throws SQLException {
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
