package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.entity.AlgorithmTypeEntity;

/**
* @ClassName:AlgorithmTypeDao
* @Description: 零件类别信息管理Dao实现
* @Author WangJuZhu
* @date 2014年6月23日 下午2:52:44
* @Version:1.1.0
*/
@Repository
public class AlgorithmTypeDao extends BaseDaoImpl{

	/**
	* @MethodName: listAlgorithmType 
	* @Description: 根据条件分页查询零件类别信息
	* @param sql
	* @param params
	* @param pager
	* @return List<AlgorithmTypeEntity>
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<AlgorithmTypeEntity> listAlgorithmType(String sql,Object[] params, ListPager pager) throws SQLException {
		return this.selectPage(sql, params, AlgorithmTypeEntity.class, pager);
	}
	
	/**
	* @MethodName: addAlgorithmType 
	* @Description: 添加零件类别信息
	* @param componentType
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void addAlgorithmType(AlgorithmTypeEntity componentType) throws SQLException {
		this.insert(componentType);
	}
	
	/**
	* @MethodName: getAlgorithmTypeById 
	* @Description: 根据ID查询零件类别详情
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public AlgorithmTypeEntity getAlgorithmTypeById(Integer id) throws SQLException {
		String sql = "select * from r_component_type where id = ?";
		return this.selectOne(sql,new Integer[]{id},AlgorithmTypeEntity.class);
	}
	
	/**
	* @MethodName: modifyAlgorithmType 
	* @Description: 修改零件类别信息
	* @param componentType
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void modifyAlgorithmType(AlgorithmTypeEntity componentType) throws SQLException {
		this.update(componentType);
	}

	/**
	* @MethodName: deleteAlgorithmType 
	* @Description: 删除零件类别信息
	* @param sql
	* @throws SQLException
	* @author WangJuZhu
	*/
	public void deleteAlgorithmType(String sql) throws SQLException {
		this.executeUpdate(sql);
	}
	
	
	
}
