/**
* @FileName: ButtonService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 下午03:27:49
* @version V1.0
*/
package com.rotek.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.rotek.dao.impl.NewsDao;

@Service
public class NewsService {
	
	@Resource
	private NewsDao newsDao;

	public List<Map<String, Object>> getNewsList() throws SQLException {

		return newsDao.getNewsList();
	}

	public Map<String, Object> getNewsDetailById(Integer newsId) throws SQLException {
		
		return newsDao.getNewsDetailById(newsId);
	}
}
