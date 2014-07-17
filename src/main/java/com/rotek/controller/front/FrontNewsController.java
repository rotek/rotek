/**
 * @FileName: IndexController.java
 * @Package com.rotek.controller
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013531 上午10:33:55
 * @version V1.0
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
 * 新闻公告
 * 
 * @author chenwenpeng
 * 
 */
@Controller
@RequestMapping("/front/news")
public class FrontNewsController {

	@Autowired
	private NewsService newsService;

	/**
	 * 返回水质监测页
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toNews")
	public String getIndex(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {

		List<Map<String,Object>> dataList = newsService.getNewsList();
		modelMap.put("dataList", dataList);
		return "front/news";
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
