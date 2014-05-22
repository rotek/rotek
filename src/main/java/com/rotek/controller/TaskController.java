/**
* @FileName: TaskController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-13 上午11:34:51
* @version V1.0
*/
package com.rotek.controller;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.dto.UserDto;
import com.rotek.entity.TaskEntity;
import com.rotek.platform.util.ListPager;
import com.rotek.service.impl.TaskService;

/**
 * @ClassName: TaskController
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-13 上午11:34:51
 *
 */
@Controller
@RequestMapping("/admin/task")
public class TaskController {

	@Autowired
	private TaskService taskService;

	/**
	* @Title: toTasks
	* @Description:
	* @param modelMap
	* @return
	* @return String
	* @throws
	*/
	@RequestMapping("/toTasks")
	public String toTasks(ModelMap modelMap){

		return "admin/task/tasks";
	}

	/**
	* @Title: listTasks
	* @Description:
	* @param user
	* @param start
	* @param limit
	* @param target_id
	* @param type
	* @param start_time
	* @param end_time
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listTasks")
	public String listTasks(UserDto user,
		@RequestParam(value = "start", defaultValue = "0") Integer start,
		@RequestParam(value = "limit", defaultValue = "15") Integer limit,

		@RequestParam(value = "target_id", defaultValue = "") Integer target_id,
		@RequestParam(value = "type", defaultValue = "") Integer type,
		@RequestParam(value = "start_time", defaultValue = "") Date start_time,
		@RequestParam(value = "end_time", defaultValue = "") Date end_time,

		ModelMap modelMap) throws SQLException{

		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		TaskEntity task = new TaskEntity();
		task.setTarget_id(target_id);
		task.setType(type);

		modelMap.put("dataList", taskService.listTasks(task,start_time,end_time,pager));
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @Title: deleteTask
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteTask")
	public String deleteTask(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = taskService.deleteTask(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}
	
	
	

	@InitBinder
    protected void initBinder(HttpServletRequest request,
    	    ServletRequestDataBinder binder) throws Exception {
    	    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    	    CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
    	    binder.registerCustomEditor(Date.class, dateEditor);
    }
}
