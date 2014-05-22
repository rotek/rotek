/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.dao.impl.SEODao;
import com.rotek.entity.SEOEntity;
import com.rotek.platform.util.ListPager;
import com.rotek.platform.util.ValidateUtil;

/**
 * @ClassName: SEOService
 * @Description: 
 * @author chenwenpeng
 * @date 2013-8-16 上午10:44:00
 */
@Service
public class SEOService {

	@Autowired
	private SEODao SEODao;
	
	/**
	* @Title: listSEOs
	* @Description: 
	* @param @param seo
	* @param @param pager
	* @param @return
	* @param @throws SQLException 
	* @return List<SEOEntity> 
	* @throws 
	*/ 
	public List<SEOEntity> listSEOs(SEOEntity seo, ListPager pager) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select id, title, keywords, description, type from mf_seo where 1=1");
		
		if(null != seo.getId()){
			sql.append(" and id = ?");
			params.add(seo.getId());
		}
		
		if(null != seo.getType()){
			sql.append(" and type = ?");
			params.add(seo.getType());
		}
		
		sql.append(" order by id desc");
		return SEODao.listSEOs(sql.toString(),params.toArray(),pager);
	}

	/**
	 * @throws SQLException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	* @Title: addSEO
	* @Description: 
	* @param @param seo
	* @param @return 
	* @return List<String> 
	* @throws 
	*/ 
	public List<String> addSEO(SEOEntity seo) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(seo);
		if(messages.size()>0){
			return messages;
		}
		//检查有没有已经存在的seo类型
		List<Integer> seoType = SEODao.getSEOByType(seo.getType());
		if(null != seoType && seoType.size()>0){
			messages.add("添加失败，已经存在的本SEO信息！");
			return messages;
		}
		SEODao.addSEO(seo);
		return null;
	}
	
	/**
	 * @throws SQLException 
	* @Title: getSEODetail
	* @Description: 
	* @param @param id
	* @param @return 
	* @return SEOEntity 
	* @throws 
	*/ 
	public SEOEntity getSEODetail(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		
		return SEODao.getSEODetail(id);
	}

	/**
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalAccessException 
	 * @throws SQLException 
	* @Title: modifySEO
	* @Description: 
	* @param @param seo
	* @param @return 
	* @return List<String> 
	* @throws 
	*/ 
	public List<String> modifySEO(SEOEntity seo) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<String> messages = ValidateUtil.validate(seo);
		if(messages.size()>0 || null == seo.getId()){
			messages.add(null == messages ? "请选择要修改的seo信息" : "");
			return messages;
		}
		
		//检查有没有已经存在的seo类型
		List<Integer> seoType = SEODao.getSEOByType(seo.getType());
		if((null != seoType && seoType.size()==1 && seoType.get(0) == seo.getId()) || (null != seoType && seoType.size()<=0)){
			SEODao.modifySEO(seo);
		}else {
			messages.add("修改失败，已经存在要修改的seo类型");
			return messages;
		}
		return null;
	}

	/**
	 * @throws SQLException 
	* @Title: deleteSEO
	* @Description: 
	* @param @param ids
	* @param @return 
	* @return List<String> 
	* @throws 
	*/ 
	public List<String> deleteSEO(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("delete from mf_seo ");
		sql.append(" where id in ("+ids.trim()+")");
		SEODao.deleteSEO(sql.toString());
		return messages;
	}
}
