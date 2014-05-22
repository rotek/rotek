/**
* @FileName: FeedbackController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-9 下午01:22:35
* @version V1.0
*/
package com.rotek.controller;

import java.lang.reflect.InvocationTargetException;
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

import com.rotek.dto.FeedbackDto;
import com.rotek.platform.util.ListPager;
import com.rotek.service.impl.FeedbackService;
import com.rotek.util.DateUtils;

/**
 * @ClassName: FeedbackController
 * @Description: 反馈
 * @author chenwenpeng
 * @date 2013-8-9 下午01:22:35
 *
 */
@Controller
@RequestMapping("/admin/feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;

	/**
	* @Title: toFeedbacks
	* @Description:
	* @return
	* @return String
	* @throws
	*/
	@RequestMapping("toFeedbacks")
	public String toFeedbacks(){

		return "admin/feedback/feedbacks";
	}

	/**
	* @Title: listFeedbacks
	* @Description:列出反馈信息
	* @param start
	* @param limit
	* @param id
	* @param user_id
	* @param user_name
	* @param start_time
	* @param end_time
	* @param type
	* @param status
	* @param request
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listFeedbacks")
	public String listFeedbacks(
		@RequestParam(value = "start", defaultValue = "0") Integer start,
		@RequestParam(value = "limit", defaultValue = "15") Integer limit,

		@RequestParam(value = "id", defaultValue = "") Integer id,
		@RequestParam(value = "user_id", defaultValue = "") Integer user_id,
		@RequestParam(value = "user_name", defaultValue = "") String user_name,
		@RequestParam(value = "start_time", defaultValue = "") Date start_time,
		@RequestParam(value = "end_time", defaultValue = "") Date end_time,
		@RequestParam(value = "type", defaultValue = "") Integer type,
		@RequestParam(value = "status", defaultValue = "") Integer status,
		HttpServletRequest request,
		ModelMap modelMap) throws SQLException{

		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		FeedbackDto feedback = new FeedbackDto();
		feedback.setId(id);
		feedback.setUser_id(user_id);
		feedback.setUser_name(user_name);
		feedback.setType(type);
		feedback.setStatus(status);


		modelMap.put("dataList", feedbackService.listFeedbacks(feedback,start_time,end_time,pager));
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}


	/**
	* @Title: deleteFeedback
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteFeedback")
	public String deleteFeedback(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = feedbackService.deleteFeedback(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: getFeedbackDetail
	* @Description:
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getFeedbackDetail")
	public String getFeedbackDetail(
			@RequestParam(value="id", defaultValue="") Integer id,
			ModelMap model) throws SQLException{

		FeedbackDto feedback = feedbackService.getFeedbackDetail(id);
		if(null != feedback){
			model.put("time", DateUtils.formatDateTime(feedback.getTime()));
		}
		model.put("data", feedback);
		return "jsonView";
	}


	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: replyFeedback
	* @Description:
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("replyFeedback")
	public String replyFeedback(
			@RequestParam(value="id", defaultValue="") Integer id,
			@RequestParam(value="user_id", defaultValue="") Integer user_id,
			@RequestParam(value="reply_content", defaultValue="") String reply_content,
			ModelMap model) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		List<String> messages = feedbackService.replyFeedback(id,user_id,reply_content);
		model.put("success", null == messages ? true:false);
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
