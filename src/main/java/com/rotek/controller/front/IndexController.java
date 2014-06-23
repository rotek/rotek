/**
 * @FileName: IndexController.java
 * @Package com.rotek.controller
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-31 上午10:33:55
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
 * @ClassName: IndexController
 * @Description: 首页的控制器
 * @author chenwenpeng
 * @date 2013-5-31 上午10:33:55
 * 
 */
@Controller
@RequestMapping("/front/index")
public class IndexController {

	@Autowired
	private IndexService indexservice;

	/**
	 * 返回用户的首页信息
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toIndex")
	public String getIndex(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {

		return "front/index/index";
	}

}
