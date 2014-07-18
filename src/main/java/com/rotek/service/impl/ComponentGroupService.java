package com.rotek.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.ComponentGroupDao;
import com.rotek.dto.ComponentGroupDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.ComponentGroupEntity;
import com.rotek.entity.ProjectEntity;

/**
* @ClassName:ProjectService
* @Description: 工程信息Service
* @Author WangJuZhu
* @date 2014年6月10日 下午4:21:22
* @Version:1.1.0
*/
@Service
public class ComponentGroupService {

	@Autowired
	private ComponentGroupDao comgroupDao;
	
	/**
	* @MethodName: listComGroup 
	* @Description: 组列表查询
	* @param user
	* @param comgroup
	* @param pager
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ComponentGroupDto> listComGroup(UserDto user, ComponentGroupDto comgroup,ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select cgroup.*,pro.GCMC as PROJECT_NAME from r_component_group cgroup ");
		sql.append(" left join r_project pro on pro.id = cgroup.R_PROJECT_ID ");
		sql.append(" where 1 = 1 ");
		
		if(StringUtils.isNotEmpty(comgroup.getProject_name())){
			sql.append(" and pro.GCMC like '%").append(comgroup.getProject_name()).append("%'");
		}
		
		if(null != comgroup.getGroup_lb()){
			sql.append(" and cgroup.GROUP_LB = ?");
			params.add(comgroup.getGroup_lb());
		}
		
		if(null != comgroup.getId()){
			sql.append(" and cgroup.ID = ?");
			params.add(comgroup.getId());
		}
		
		if(StringUtils.isNotEmpty(comgroup.getGroup_bh())){
			sql.append(" and cgroup.GROUP_BH like '%").append(comgroup.getGroup_bh()).append("%'");
		}
		
		if(StringUtils.isNotEmpty(comgroup.getGroup_mc())){
			sql.append(" and cgroup.GROUP_MC like '%").append(comgroup.getGroup_mc()).append("%'");
		}
		
		if(StringUtils.isNotEmpty(comgroup.getPp())){
			sql.append(" and cgroup.PP like '%").append(comgroup.getPp()).append("%'");
		}
		
		if(StringUtils.isNotEmpty(comgroup.getXh())){
			sql.append(" and cgroup.XH like '%").append(comgroup.getXh()).append("%'");
		}
		
		if(StringUtils.isNotEmpty(comgroup.getGl())){
			sql.append(" and cgroup.GL like '%").append(comgroup.getGl()).append("%'");
		}

		
		
		
		sql.append(" order by cgroup.ID ");
		return comgroupDao.listComGroup(sql.toString(), params.toArray(), pager);
	}
	
	public List<ProjectEntity> listProjectByStatus(Integer status) throws SQLException{
		return comgroupDao.listProjectByStatus(status);
	}
	
	/**
	* @MethodName: addComGroup 
	* @Description: 新增组信息
	* @param comgroup
	* @return
	* @throws Exception
	* @author WangJuZhu
	*/
	public List<String> addComGroup(ComponentGroupEntity comgroup) throws Exception {
		
		List<String> messages = ValidateUtil.validate(comgroup);
		if(messages.size() > 0){
			return messages;
		}
		comgroupDao.addComGroup(comgroup);
		
		return null;
	}
	
	/**
	* @MethodName: getComGroupById 
	* @Description: 根据Id查询组信息
	* @param id
	* @return ComponentGroupEntity
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ComponentGroupEntity getComGroupById(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return comgroupDao.getComGroupById(id);
	}
	
	/**
	* @MethodName: getOneComGroup 
	* @Description:
	* @param id
	* @return ComponentGroupDto
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ComponentGroupDto getOneComGroup(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return comgroupDao.getOneComGroup(id);
	}
	
	/**
	* @MethodName: selectOneComGroupById 
	* @Description: 根据组Id获取组的详细信息，查询出对应的工程ID和客户ID
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ComponentGroupDto selectOneComGroupById(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return comgroupDao.selectOneComGroupById(id);
	}
	
	/**
	* @MethodName: modifyComGroup 
	* @Description: 修改组信息
	* @param project
	* @return
	* @author WangJuZhu
	*/
	public List<String> modifyComGroup(ComponentGroupEntity comgroup) 
			throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		List<String> messages = ValidateUtil.validate(comgroup);
		if(messages.size() > 0 || null == comgroup.getId()){
			messages.add(null == comgroup.getId() ? "修改组的记录ID为空，修改失败!" : "");
			return messages;
		}
		//修改
		comgroupDao.modifyComGroup(comgroup);
		return null;
	}

	/**
	* @MethodName: deleteComGroup 
	* @Description: 批量删除零件组（更改记录的状态为无效(status = -1)）
	* @param ids
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> deleteComGroup(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update r_component_group set STATUS = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		comgroupDao.deleteComGroup(sql.toString());
		return messages;
	}

	public List<ComponentGroupEntity> selectGroupByPid(Integer projectId,
			Integer componentType, int code)throws SQLException {
		return comgroupDao.selectGroupByPid(projectId, componentType, code);
	}

	public List<ComponentGroupEntity> getComGroupListByProjectId(
			Integer projectId) throws SQLException {
		
		return comgroupDao.getComGroupListByProjectId(projectId);
	}
}
