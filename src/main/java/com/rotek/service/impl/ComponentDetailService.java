package com.rotek.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.ComponentDetailDao;
import com.rotek.dto.ComponentDetailDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.ComponentDetailEntity;
import com.rotek.entity.ProjectEntity;

/**
* @ClassName:ComponentDetailService
* @Description: 零件详情Service
* @Author WangJuZhu
* @date 2014年7月1日 下午4:45:39
* @Version:1.1.0
*/
@Service
public class ComponentDetailService {

	@Resource
	private ComponentDetailDao comgroupDao;
	
	/**
	* @MethodName: listComDetail 
	* @Description: 组列表查询
	* @param user
	* @param comgroup
	* @param pager
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ComponentDetailDto> listComDetail(UserDto user, ComponentDetailDto comgroup,Integer ptype , ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select cdetail.*,pro.GCMC as PROJECT_NAME,cgrop.GROUP_MC AS GROUP_NAME from r_component_detail cdetail ");
		sql.append(" left join r_project pro on pro.id = cdetail.R_PROJECT_ID ");
		sql.append(" left join r_component_group cgrop on cgrop.id = cdetail.R_COMPONENT_GROUP_ID ");
		sql.append(" where 1 = 1 ");
		
		if(StringUtils.isNotEmpty(comgroup.getProject_name())){
			sql.append(" and pro.GCMC like '%").append(comgroup.getProject_name()).append("%'");
		}
		
		if(StringUtils.isNotEmpty(comgroup.getGroup_name())){
			sql.append(" and cgrop.GROUP_MC like '%").append(comgroup.getGroup_name()).append("%'");
		}
		
		if(StringUtils.isNotEmpty(comgroup.getSpecific_part())){
			sql.append(" and cdetail.SPECIFIC_PART like '%").append(comgroup.getSpecific_part()).append("%'");
		}
		
		if(StringUtils.isNotEmpty(comgroup.getSpecific_bh())){
			sql.append(" and cdetail.SPECIFIC_BH like '%").append(comgroup.getSpecific_bh()).append("%'");
		}
		
		if(comgroup.getR_component_group_type() != null && comgroup.getR_component_group_type() != 0){
			sql.append(" and cdetail.R_COMPONENT_GROUP_TYPE = " + comgroup.getR_component_group_type());
		}
		
		if(ptype != 0){
			sql.append(" and pro.GCLB = " + ptype );
		}

		sql.append(" order by cdetail.ID ");
		return comgroupDao.listComDetail(sql.toString(), params.toArray(), pager);
	}
	
	public List<ProjectEntity> listProjectByStatus(Integer status) throws SQLException{
		return comgroupDao.listProjectByStatus(status);
	}
	
	/**
	* @MethodName: addComDetail 
	* @Description: 新增组信息
	* @param ComDetail
	* @return
	* @throws Exception
	* @author WangJuZhu
	*/
	public List<String> addComDetail(ComponentDetailEntity comgroup) throws Exception {
		
		List<String> messages = ValidateUtil.validate(comgroup);
		if(messages.size() > 0){
			return messages;
		}
		comgroupDao.addComDetail(comgroup);
		
		return null;
	}
	
	/**
	* @MethodName: getComDetailById 
	* @Description: 根据Id查询组信息
	* @param id
	* @return ComponentGroupEntity
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ComponentDetailEntity getComDetailById(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return comgroupDao.getComDetailById(id);
	}
	
	/**
	* @MethodName: getOneComDetail 
	* @Description:
	* @param id
	* @return ComponentGroupDto
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ComponentDetailDto getOneComDetail(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return comgroupDao.getOneComDetail(id);
	}
	
	/**
	* @MethodName: modifyComDetail 
	* @Description: 修改组信息
	* @param project
	* @return
	* @author WangJuZhu
	*/
	public List<String> modifyComDetail(ComponentDetailEntity comgroup) 
			throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		List<String> messages = ValidateUtil.validate(comgroup);
		if(messages.size() > 0 || null == comgroup.getId()){
			messages.add(null == comgroup.getId() ? "修改组的记录ID为空，修改失败!" : "");
			return messages;
		}
		//修改
		comgroupDao.modifyComDetail(comgroup);
		return null;
	}

	/**
	* @MethodName: deleteComDetail 
	* @Description: 批量删除零件组（更改记录的状态为无效(status = -1)）
	* @param ids
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> deleteComDetail(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update r_component_detail set STATUS = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		comgroupDao.deleteComDetail(sql.toString());
		return messages;
	}

	public List<ComponentDetailEntity> getListByComponentGroupId(
			Integer componentId) throws SQLException {
		
		return comgroupDao.getListByComponentGroupId(componentId);
	}
}
