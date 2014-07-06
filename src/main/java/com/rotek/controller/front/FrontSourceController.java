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
 * 资源下载
 * 
 * @author chenwenpeng
 * 
 */
@Controller
@RequestMapping("/front/resource")
public class FrontSourceController {

	@Autowired
	private IndexService indexservice;

	/**
	 * 返回资源下载
	 * 
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping("toResource")
	public String toResource(HttpServletRequest request, ModelMap modelMap)
			throws SQLException {

		return "front/resource";
	}

}
