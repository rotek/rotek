package com.rotek.service.impl;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.dao.impl.AlgorithmsDao;
import com.rotek.dto.AlgorithmsDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.AlgorithmsEntity;

/**
* @ClassName:AlgorithmsService
* @Description:
* @Author liusw
* @date 2014年6月29日 下午3:16:24
* @Version:1.1.0
*/
@Service
public class AlgorithmsService {
	
	@Autowired
	private AlgorithmsDao algorithmsDao;
	
	/**
	* @MethodName: listAlgorithms 
	* @Description: 查询算法设置信息
	* @param user
	* @param algor
	* @param pager
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<AlgorithmsDto> listAlgorithms(UserDto user, 
			AlgorithmsDto algor,ListPager pager) throws SQLException {
		return algorithmsDao.listAlgorithms(user, algor, pager);
	}
	
	/**
	* @MethodName: addAlgorithms 
	* @Description: 新增算法设置
	* @param algorithm
	* @return
	* @throws Exception
	* @author WangJuZhu
	*/
	public List<String> addAlgorithms(AlgorithmsEntity algorithm) throws Exception {
		List<String> messages = ValidateUtil.validate(algorithm);
		if(messages.size() > 0){
			return messages;
		}
		algorithmsDao.addAlgorithms(algorithm);
		return null;
	}
	
	/**
	* @MethodName: getAlgorithmsById 
	* @Description: 根据Id查询算法设置信息
	* @param id
	* @return AlgorithmsEntity
	* @throws SQLException
	* @author WangJuZhu
	*/
	public AlgorithmsEntity getAlgorithmsById(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return algorithmsDao.getAlgorithmsById(id);
	}
	
	/**
	* @MethodName: modifyAlgorithms 
	* @Description: 修改算法设置信息
	* @param project
	* @return
	* @author WangJuZhu
	*/
	public List<String> modifyAlgorithms(AlgorithmsEntity algorithm) throws Exception{
		List<String> messages = ValidateUtil.validate(algorithm);
		if(messages.size() > 0 || null == algorithm.getId()){
			messages.add(null == algorithm.getId() ? "修改的记录ID为空，修改失败!" : "");
			return messages;
		}
		//修改
		algorithmsDao.modifyAlgorithms(algorithm);
		return null;
	}
	
	/**
	* @MethodName: deleteAlgorithms 
	* @Description: 批量删除（更改记录的状态为无效(status = -1)）
	* @param ids
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> deleteAlgorithms(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update r_algorithm set STATUS = -1");
		sql.append(" where id in ("+ids.trim()+")");
		algorithmsDao.deleteAlgorithms(sql.toString());
		return messages;
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
	public List<AlgorithmsEntity> selectAlgorithmByIds(Integer algorithmType , 
			Integer r_coustromer_id,Integer r_project_id,Integer r_component_group_id,Integer r_component_detail_id) throws SQLException {
		return algorithmsDao.selectAlgorithmByIds(algorithmType,r_coustromer_id,r_project_id,r_component_group_id,r_component_detail_id);
	}

	public AlgorithmsDto getOneAlgorithm(Integer id) throws SQLException {
		return algorithmsDao.getOneAlgorithm(id);
	}
	
}
