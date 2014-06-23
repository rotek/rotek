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
import com.rotek.dao.impl.ComponentTypeDao;
import com.rotek.entity.ComponentTypeEntity;

/**
* @ClassName: ComponentTypeService
* @Description: 零件类别信息Service
* @Author WangJuZhu
* @date 2014年6月10日 下午4:21:22
* @Version:1.1.0
*/
@Service
public class ComponentTypeService {

	@Autowired
	private ComponentTypeDao typeDao;

	/**
	* @MethodName: ComponentTypeEntity 
	* @Description: 零件类别列表查询
	* @param user
	* @param ctype
	* @param pager
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<ComponentTypeEntity> listComponentType( ComponentTypeEntity ctype, ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select * from r_component_type where 1 = 1");
		
		if(StringUtils.isNotEmpty(ctype.getName())){
			sql.append(" and NAME like '%").append(ctype.getName()).append("%'");
		}
		if(StringUtils.isNotEmpty(ctype.getDescribe())){
			sql.append(" and DESCRIBE like '%").append(ctype.getDescribe()).append("%'");
		}

		if(null != ctype.getStatus()){
			sql.append(" and STATUS = ?");
			params.add(ctype.getStatus());
		}

		sql.append(" order by ID desc");
		return typeDao.listComponentType(sql.toString(), params.toArray(), pager);
	}
	
	/**
	* @MethodName: addComponentType 
	* @Description: 新增零件类别信息
	* @param comtype
	* @param multipartRequest
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> addComponentType(ComponentTypeEntity comtype) throws Exception {
		List<String> messages = ValidateUtil.validate(comtype);
		if(messages.size() > 0){
			return messages;
		}
		
		typeDao.addComponentType(comtype);
		
		return null;
	}
	
	/**
	* @MethodName: getComponentTypeById 
	* @Description: 根据Id查询零件类别信息
	* @param id
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public ComponentTypeEntity getComponentTypeById(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return typeDao.getComponentTypeById(id);
	}
	
	public List<String> modifyComponentType(ComponentTypeEntity cmtype) 
			throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		List<String> messages = ValidateUtil.validate(cmtype);
		if(messages.size() > 0 || null == cmtype.getId()){
			messages.add(null == cmtype.getId() ? "由于，要修改的零件类别记录ID为空，修改失败!" : "");
			return messages;
		}
		//修改
		typeDao.modifyComponentType(cmtype);
		return null;
	}

	/**
	* @MethodName: deleteComponentType 
	* @Description: 批量删除零件类别（更改记录的状态为无效(status = -1)）
	* @param ids
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	public List<String> deleteComponentType(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update r_component_type set STATUS = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		typeDao.deleteComponentType(sql.toString());
		return messages;
	}
	
	
	

	
}
