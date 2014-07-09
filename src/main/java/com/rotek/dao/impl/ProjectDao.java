package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.dto.ProjectDto;
import com.rotek.entity.ProjectEntity;

/**
* @ClassName:ProjectDao
* @Description: 工程信息管理Dao实现
* @Author WangJuZhu
* @date 2014年6月10日 下午4:37:19
* @Version:1.1.0
*/
@Repository
public class ProjectDao extends BaseDaoImpl{

	/**
	* @MethodName: listProject 
	* @Description: 根据条件分页查询工程信息
	* @param sql
	* @param params
	* @param pager
	* @return List<ProjectDto>
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ProjectDto> listProject(String sql,Object[] params, ListPager pager) throws SQLException {
		return this.selectPage(sql, params, ProjectDto.class, pager);
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
	
	public List<ProjectEntity> selectProjectByType(Integer status,Integer type) throws SQLException{
		String sql = "select * from r_project where status = " + status + " and GCLB = " + type ;
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
	
	public ProjectDto getProjectDtoById(Integer id) throws SQLException {
		String sql = "select r.*,custo.MC AS CUSTOMER_NAME from r_project r "
				+ " left join r_customer custo on custo.ID = r.r_customer_id "
				+ " where r.id = ?";
		return this.selectOne(sql,new Integer[]{id},ProjectDto.class);
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

	/**
	 * @param r_customer_id
	 * @return
	 * @throws SQLException
	 */
	public List<ProjectEntity> getProjectListByCustomerId(Integer r_customer_id) throws SQLException {
		
		String sql = "select * From r_project where R_CUSTOMER_ID = ? and status = ?";
		
		return this.selectAll(sql, new Integer[]{r_customer_id,ProjectEntity.STATUS_ENABLED}, ProjectEntity.class);
	}

	/**
	* @MethodName: updateProject 
	* @Description: 更新操作
	* @param pe
	* @author WangJuZhu
	*/
	public void updateProject(ProjectEntity pe)throws SQLException {
		// TODO Auto-generated method stub
		this.update(pe);
	}
	
}
