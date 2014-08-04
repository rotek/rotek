/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.,Ltd.
*/
package com.rotek.controller.front;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.service.impl.NewsService;

/**
* @ClassName: RNewsController
* @Description: 新闻公告处理类
* @Author WangJuZhu
* @date 2014年8月4日 下午8:23:50
* @Version:1.1.0
*/
@Controller
@RequestMapping("/front/rnews")
public class RNewsController {

	@Autowired
	private NewsService newsService;

	/**
	* @MethodName: toNews 
	* @Description: 转到新闻公告主页面
	* @param request
	* @param modelMap
	* @return
	* @throws SQLException
	* @author WangJuZhu
	*/
	@RequestMapping("toNews")
	public String toNews(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {

		List<Map<String,Object>> dataList = newsService.getNewsList();
		modelMap.put("dataList", dataList);
		return "front/news/news";
	}
	
	/**
	 * 返回水质监测详情
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toNewsDetail")
	public String toNewsDetail(HttpServletRequest request, ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "0") Integer newsId)
			throws SQLException {
		
		Map<String,Object> newsDetail = newsService.getNewsDetailById(newsId);
		
		modelMap.put("data", newsDetail);
		return "front/newsDetail";
	}

}
