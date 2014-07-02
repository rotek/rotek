package com.rotek.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.rotek.service.impl.AlgorithmsService;

/**
* @ClassName:AlgorithmController
* @Description:
* @Author liusw
* @date 2014年6月29日 下午3:12:11
* @Version:1.1.0
*/
@Controller
@RequestMapping("/admin/Algorithms") // controller方法路径，每个Controller最好取不一样的路径名字
public class AlgorithmsController {
	@Autowired
	private AlgorithmsService algorithmsService;

	
	/**
	* @MethodName: toUnProcessInfoList 
	* @Description:
	* @return
	* @author liusw
	*/
	@RequestMapping("toAlgorithmsList")
	public String toAlgorithmsList() {
		// 返回JSP文件目录及对应的JSP文件
		return "admin/algorithms/algorithms";
	}
}
