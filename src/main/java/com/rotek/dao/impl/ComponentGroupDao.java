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
* @ClassName:ProjectDao
* @Description: 工程信息管理Dao实现
* @Author WangJuZhu
* @date 2014年6月10日 下午4:37:19
* @Version:1.1.0
*/
@Repository
public class ComponentGroupDao extends BaseDaoImpl{

	/**
	* @MethodName: listComGroup 
	* @Description: 根据条件分页查询组信息
	* @param sql
	* @param params
	* @param pager
	* @return List<ComponentGroupDto>
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ComponentGroupDto> listComGroup(String sql,Object[] params, ListPager pager) throws SQLException {
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
	* @MethodName: addComGroup 
	* @Description: 添加组信息
	* @param comgroup
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void addComGroup(ComponentGroupEntity comgroup) throws SQLException {
		this.insert(comgroup);
	}
	
	/**
	* @MethodName: getComGroupById 
	* @Description: 根据ID查询组详情
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ComponentGroupEntity getComGroupById(Integer id) throws SQLException {
		String sql = "select * from r_component_group where id = ?";
		return this.selectOne(sql,new Integer[]{id},ComponentGroupEntity.class);
	}
	
	public ComponentGroupDto getOneComGroup(Integer id) throws SQLException {
		String sql = "select cgro.*,pro.GCMC as PROJECT_NAME from r_component_group cgro "
				+ " left join r_project pro on pro.id = cgro.r_project_id where cgro.id = ?";
		return this.selectOne(sql,new Integer[]{id},ComponentGroupDto.class);
	}
	
	/**
	* @MethodName: modifyComGroup 
	* @Description: 修改工程信息
	* @param project
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void modifyComGroup(ComponentGroupEntity comgroup) throws SQLException {
		this.update(comgroup);
	}

	/**
	* @MethodName: deleteComGroup 
	* @Description: 删除组信息
	* @param sql
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void deleteComGroup(String sql) throws SQLException {
		this.executeUpdate(sql);
	}

	public List<ComponentGroupEntity> selectGroupByPid(Integer projectId,
			Integer componentType, int code)throws SQLException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer() ;
		sql.append(" select t.ID, t.R_PROJECT_ID,t.GROUP_LB,t.GROUP_BH,t.GROUP_MC,t.STATUS from r_component_group t ");
		sql.append(" where 1=1 ");
		if(projectId != null && projectId != 0){
			sql.append(" and  R_PROJECT_ID = " + projectId );
		}
		if(componentType != null && componentType != 0){
			sql.append(" and GROUP_LB = " + componentType );
		}
		sql.append(" and STATUS = " + code );
		
		return selectAll(sql.toString(), new Integer[]{}, ComponentGroupEntity.class);
	}

	public List<ComponentGroupEntity> getComGroupListByProjectId(
			Integer projectId) throws SQLException {
		String sql = "select * From r_component_group where r_project_id = ? and status = ?";
		
		return selectAll(sql, new Integer[]{projectId,ComponentGroupEntity.STATUS_ENABLED}, ComponentGroupEntity.class);
	}
}
