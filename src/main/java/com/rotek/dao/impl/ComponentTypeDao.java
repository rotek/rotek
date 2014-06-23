package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.entity.ComponentTypeEntity;

/**
* @ClassName:ComponentTypeDao
* @Description: 零件类别信息管理Dao实现
* @Author WangJuZhu
* @date 2014年6月23日 下午2:52:44
* @Version:1.1.0
*/
@Repository
public class ComponentTypeDao extends BaseDaoImpl{

	/**
	* @MethodName: listComponentType 
	* @Description: 根据条件分页查询零件类别信息
	* @param sql
	* @param params
	* @param pager
	* @return List<ComponentTypeEntity>
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ComponentTypeEntity> listComponentType(String sql,Object[] params, ListPager pager) throws SQLException {
		return this.selectPage(sql, params, ComponentTypeEntity.class, pager);
	}
	
	/**
	* @MethodName: addComponentType 
	* @Description: 添加零件类别信息
	* @param componentType
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void addComponentType(ComponentTypeEntity componentType) throws SQLException {
		this.insert(componentType);
	}
	
	/**
	* @MethodName: getComponentTypeById 
	* @Description: 根据ID查询零件类别详情
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ComponentTypeEntity getComponentTypeById(Integer id) throws SQLException {
		String sql = "select * from r_component_type where id = ?";
		return this.selectOne(sql,new Integer[]{id},ComponentTypeEntity.class);
	}
	
	/**
	* @MethodName: modifyComponentType 
	* @Description: 修改零件类别信息
	* @param componentType
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void modifyComponentType(ComponentTypeEntity componentType) throws SQLException {
		this.update(componentType);
	}

	/**
	* @MethodName: deleteComponentType 
	* @Description: 删除零件类别信息
	* @param sql
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void deleteComponentType(String sql) throws SQLException {
		this.executeUpdate(sql);
	}
	
	
	
}
