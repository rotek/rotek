/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rotek.entity.SEOEntity;
import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;

/**
 * @ClassName: SEODao
 * @Description: 
 * @author chenwenpeng
 * @date 2013-8-16 上午10:45:58
 */
@Repository
public class SEODao extends BaseDaoImpl{

	/**
	 * @throws SQLException 
	 * @param params 
	* @Title: listSEOs
	* @Description: 
	* @param @param sql
	* @param @param pager
	* @param @return 
	* @return List<SEOEntity> 
	* @throws 
	*/ 
	public List<SEOEntity> listSEOs(String sql, Object[] params, ListPager pager) throws SQLException {
		
		
		return this.selectPage(sql, params, SEOEntity.class,pager);
	}
	/**
	 * @throws SQLException 
	* @Title: addSEO
	* @Description: 
	* @param @param seo 
	* @return void 
	* @throws 
	*/ 
	public void addSEO(SEOEntity seo) throws SQLException {
		
		this.insert(seo);
	}

	/**
	 * @throws SQLException 
	* @Title: getSEOByType
	* @Description: 
	* @param @param type
	* @param @return 
	* @return List<Integer> 
	* @throws 
	*/ 
	public List<Integer> getSEOByType(Integer type) throws SQLException {
		
		String sql = "select id from mf_seo where type = ?";
		return this.executeQueryForInt(sql, new Integer[]{type});
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
		
		String sql = "select id, title, keywords, description, type from mf_seo where id = ?";
		return this.selectOne(sql, new Integer[]{id}, SEOEntity.class);
	}
	
	/**
	 * @throws SQLException 
	* @Title: modifySEO
	* @Description: 
	* @param @param seo 
	* @return void 
	* @throws 
	*/ 
	public void modifySEO(SEOEntity seo) throws SQLException {
		
		this.update(seo);
	}
	/**
	 * @throws SQLException 
	* @Title: deleteSEO
	* @Description: 
	* @param @param string 
	* @return void 
	* @throws 
	*/ 
	public void deleteSEO(String sql) throws SQLException {
		
		this.executeUpdate(sql);
	}
}
