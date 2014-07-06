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

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rotek.service.impl.IndexService;

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
	private IndexService indexservice;

	/**
	 * 返回水质监测页
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toNews")
	public String getIndex(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {

		return "front/news";
	}
	
	/**
	 * 返回水质监测详情
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toNewsDetail")
	public String toNewsDetail(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {
		
		return "front/newsDetail";
	}

}
