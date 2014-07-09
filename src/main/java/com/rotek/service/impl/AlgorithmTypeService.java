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
import com.rotek.dao.impl.AlgorithmTypeDao;
import com.rotek.entity.AlgorithmTypeEntity;

/**
* @ClassName: AlgorithmTypeService
* @Description: 零件类别信息Service
* @Author WangJuZhu
* @date 2014年6月10日 下午4:21:22
* @Version:1.1.0
*/
@Service
public class AlgorithmTypeService {

	@Autowired
	private AlgorithmTypeDao typeDao;

	/**
	* @MethodName: AlgorithmTypeEntity 
	* @Description: 零件类别列表查询
	* @param user
	* @param ctype
	* @param pager
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<AlgorithmTypeEntity> listAlgorithmType( AlgorithmTypeEntity ctype, ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select * from r_component_type where 1 = 1");
		
		if(ctype.getId() != null){
			sql.append(" and ID = " + ctype.getId() );
		}
		
		if(StringUtils.isNotEmpty(ctype.getName())){
			sql.append(" and NAME like '%").append(ctype.getName()).append("%'");
		}
		if(StringUtils.isNotEmpty(ctype.getDescription())){
			sql.append(" and DESCRIPTION like '%").append(ctype.getDescription()).append("%'");
		}

		sql.append(" order by ID desc");
		return typeDao.listAlgorithmType(sql.toString(), params.toArray(), pager);
	}
	
	/**
	* @MethodName: addAlgorithmType 
	* @Description: 新增零件类别信息
	* @param comtype
	* @param multipartRequest
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> addAlgorithmType(AlgorithmTypeEntity comtype) throws Exception {
		List<String> messages = ValidateUtil.validate(comtype);
		if(messages.size() > 0){
			return messages;
		}
		
		typeDao.addAlgorithmType(comtype);
		
		return null;
	}
	
	/**
	* @MethodName: getAlgorithmTypeById 
	* @Description: 根据Id查询零件类别信息
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public AlgorithmTypeEntity getAlgorithmTypeById(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return typeDao.getAlgorithmTypeById(id);
	}
	
	public List<String> modifyAlgorithmType(AlgorithmTypeEntity cmtype) 
			throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		List<String> messages = ValidateUtil.validate(cmtype);
		if(messages.size() > 0 || null == cmtype.getId()){
			messages.add(null == cmtype.getId() ? "由于，要修改的零件类别记录ID为空，修改失败!" : "");
			return messages;
		}
		//修改
		typeDao.modifyAlgorithmType(cmtype);
		return null;
	}

	/**
	* @MethodName: deleteAlgorithmType 
	* @Description: 批量删除零件类别（更改记录的状态为无效(status = -1)）
	* @param ids
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> deleteAlgorithmType(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update r_component_type set STATUS = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		typeDao.deleteAlgorithmType(sql.toString());
		return messages;
	}
	
	
	

	
}
