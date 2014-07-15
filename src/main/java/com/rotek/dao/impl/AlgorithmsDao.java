package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.rotek.entity.AlgorithmsEntity;

/**
* @ClassName:AlgorithmsDao
* @Description:
* @Author liusw
* @date 2014年6月29日 下午3:17:56
* @Version:1.1.0
*/
@Repository
public class AlgorithmsDao  extends BaseDaoImpl{

	/**
	* @MethodName: selectAlgorithmByIds 
	* @Description：查询算法
	* @param algorithmType  		算法类别
	* @param r_coustromer_id  		客户ID
	* @param r_project_id 			工程ID
	* @param r_component_group_id  	零件组ID
	* @param r_component_detail_id 	具体零件ID
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<AlgorithmsEntity> selectAlgorithmByIds(Integer algorithmType,Integer r_coustromer_id, 
			Integer r_project_id,Integer r_component_group_id, Integer r_component_detail_id)throws SQLException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.* from r_algorithm t where 1=1 ");
		if(algorithmType > 0){
			sql.append(" and t.ALGORITHM_TYPE = ? ");
		}
		if(r_coustromer_id > 0){
			sql.append(" and t.R_CUSTOMER_ID = ? ");
		}
		if(r_project_id > 0){
			sql.append(" and t.R_PROJECT_ID = ? ");
		}
		if(r_component_group_id > 0){
			sql.append(" and t.R_COMPONET_GROUP_ID = ? ");
		}
		if(r_component_detail_id > 0){
			sql.append(" and t.R_COMPONET_DETAIL_ID = ? ");
		}
		sql.append(" order by t.ID desc ");
		
		return selectAll(sql.toString(), new Object[]{algorithmType,r_coustromer_id,r_project_id,r_component_group_id,r_component_detail_id}, AlgorithmsEntity.class);
	}

	public void addAlgorithms(AlgorithmsEntity algorithm) throws SQLException {
		// TODO Auto-generated method stub
		this.insert(algorithm);
	}

	public AlgorithmsEntity getAlgorithmsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from r_algorithm where id = ? ";
		return this.selectOne(sql,new Integer[]{id},AlgorithmsEntity.class);
	}

	public void modifyAlgorithms(AlgorithmsEntity algorithm) throws SQLException {
		// TODO Auto-generated method stub
		this.update(algorithm);
	}

	public void deleteAlgorithms(String sql) throws SQLException {
		// TODO Auto-generated method stub
		this.executeUpdate(sql);
	}
	
	
	
	
	

}
