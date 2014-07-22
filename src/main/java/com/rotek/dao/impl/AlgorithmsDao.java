package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;

import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;
import com.rotek.dto.AlgorithmsDto;
import com.rotek.dto.UserDto;
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
	* @MethodName: listAlgorithms 
	* @Description: 根据条件分页查询算法设置信息
	* @param user
	* @param algor
	* @param pager
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<AlgorithmsDto> listAlgorithms(UserDto user, 
					AlgorithmsDto algor,ListPager pager) throws SQLException {
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,comgroup.GROUP_MC AS COMPONENT_GROUP_NAME,pro.GCMC AS PROJECT_NAME ");
		sql.append(" ,custom.MC AS CUSTOMER_NAME, comdeta.SPECIFIC_PART AS COMPONENT_NAME ");
		sql.append(" from r_algorithm t ");
		sql.append(" left join r_component_group comgroup on comgroup.ID = t.R_COMPONENT_GROUP_ID ");
		sql.append(" left join r_project pro on pro.ID = comgroup.R_PROJECT_ID ");
		sql.append(" left join r_customer custom on custom.ID = pro.R_CUSTOMER_ID ");
		sql.append(" left join r_component_detail comdeta on comdeta.ID = t.R_COMPONENT_DETAIL_ID ");
		sql.append(" where 1=1 ");
		if(StringUtils.isNotBlank(algor.getComponent_name())){
			sql.append(" and comdeta.SPECIFIC_PART like '%").append(algor.getComponent_name()).append("%'");
		}
		if(StringUtils.isNotBlank(algor.getComponent_group_name())){
			sql.append(" and comgroup.GROUP_MC like '%").append(algor.getComponent_group_name()).append("%'");
		}
		if(StringUtils.isNotBlank(algor.getProject_name())){
			sql.append(" and pro.GCMC like '%").append(algor.getProject_name()).append("%'");
		}
		if(StringUtils.isNotBlank(algor.getCustomer_name())){
			sql.append(" and custom.MC like '%").append(algor.getCustomer_name()).append("%'");
		}
		if(StringUtils.isNotBlank(algor.getAlgorithm_alias())){
			sql.append(" and t.ALGORITHM_ALIAS like '%").append(algor.getAlgorithm_alias()).append("%'");
		}
		if(algor.getAlgorithm_type() != 0 && algor.getAlgorithm_type() != null){
			sql.append(" and t.ALGORITHM_TYPE = ").append(algor.getAlgorithm_type());
		}
		sql.append(" order by t.ID desc ");
		
		return selectPage(sql.toString(), new Object[]{}, AlgorithmsDto.class, pager);
		
	}
	
	public void addAlgorithms(AlgorithmsEntity algorithm) throws SQLException {
		this.insert(algorithm);
	}
	
	public AlgorithmsEntity getAlgorithmsById(Integer id) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "select * from r_algorithm where id = ? ";
		return this.selectOne(sql,new Integer[]{id},AlgorithmsEntity.class);
	}
	
	public void modifyAlgorithms(AlgorithmsEntity algorithm) throws SQLException {
		this.update(algorithm);
	}
	
	public void deleteAlgorithms(String sql) throws SQLException {
		this.executeUpdate(sql);
	}
	
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
			sql.append(" and t.R_COMPONENT_GROUP_ID = ? ");
		}
		if(r_component_detail_id > 0){
			sql.append(" and t.R_COMPONENT_DETAIL_ID = ? ");
		}
		sql.append(" order by t.ID desc ");
		
		return selectAll(sql.toString(), new Object[]{algorithmType,r_coustromer_id,r_project_id,r_component_group_id,r_component_detail_id}, AlgorithmsEntity.class);
	}

	public AlgorithmsDto getOneAlgorithm(Integer id)throws SQLException {
		// TODO Auto-generated method stub
		StringBuffer sql = new StringBuffer();
		sql.append(" select t.*,comgroup.GROUP_MC AS COMPONENT_GROUP_NAME,pro.GCMC AS PROJECT_NAME ");
		sql.append(" ,custom.MC AS CUSTOMER_NAME, comdeta.SPECIFIC_PART AS COMPONENT_NAME, comdeta.SPECIFIC_BH AS COMPONENT_BH ");
		sql.append(" from r_algorithm t ");
		sql.append(" left join r_component_group comgroup on comgroup.ID = t.R_COMPONENT_GROUP_ID ");
		sql.append(" left join r_project pro on pro.ID = comgroup.R_PROJECT_ID ");
		sql.append(" left join r_customer custom on custom.ID = pro.R_CUSTOMER_ID ");
		sql.append(" left join r_component_detail comdeta on comdeta.ID = t.R_COMPONENT_DETAIL_ID ");
		sql.append(" where 1=1 ");
		sql.append(" and t.id = " + id);
		
		return selectOne(sql.toString(), AlgorithmsDto.class);
	}


}
