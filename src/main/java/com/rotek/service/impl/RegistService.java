package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cta.platform.util.ValidateUtil;
import com.rotek.dao.impl.RegistDao;
import com.rotek.entity.ManagerEntity;

@Service
public class RegistService {
	
	@Resource
	private RegistDao registDao;
	
	public String regist(ManagerEntity manager) throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException, SQLException {
		manager.setR_customer_id(ManagerEntity.DEFAULT_CUSTOMER_ID);
		manager.setR_role_id(ManagerEntity.DEFAULT_ROLE_ID);
		manager.setStatus(ManagerEntity.STATUS_ENABLED);
		
		List<String> msgList = ValidateUtil.validate(manager);
		if(msgList.size()>0){
			StringBuilder sb = new StringBuilder();
			for(String str : msgList){
				sb.append(str).append(";");
			}
			return sb.toString();
		}else {
			
			Integer userId = registDao.insert_pk(manager);
			manager.setId(userId);
		}
		return "success";
	}
}
