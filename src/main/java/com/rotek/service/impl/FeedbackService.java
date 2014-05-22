/**
* @FileName: FeedbackService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-9 下午01:23:35
* @version V1.0
*/
package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.FeedbackDao;
import com.rotek.dto.FeedbackDto;
import com.rotek.entity.Reply_FeedbackEntity;
import com.rotek.platform.config.SystemGlobals;
import com.rotek.platform.util.ListPager;
import com.rotek.platform.util.ValidateUtil;

/**
 * @ClassName: FeedbackService
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-9 下午01:23:35
 *
 */
@Service
public class FeedbackService {

	@Autowired
	private FeedbackDao feedbackDao;
	/**
	 * @throws SQLException
	* @Title: listFeedbacks
	* @Description:
	* @param feedback
	* @param start_time
	* @param end_time
	* @return
	* @return Object
	* @throws
	*/
	public List<FeedbackDto> listFeedbacks(FeedbackDto feedback, Date start_time,
			Date end_time,ListPager pager) throws SQLException {
		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();

		sql.append("select f.id, f.user_id, f.time, f.type, f.status,u.nick_name user_name from mf_feedback f");
        sql.append(" left join mf_user u on f.user_id = u.id where 1=1");

        if(null != feedback.getId()){
        	sql.append(" and f.id = ?");
        	params.add(feedback.getId());
        }

        if(null != feedback.getUser_id()){
        	sql.append(" and f.user_id= ?");
        	params.add(feedback.getUser_id());
        }
        if(StringUtils.isNotEmpty(feedback.getUser_name())){
        	sql.append(" and u.nick_name like '%").append(feedback.getUser_name()).append("%'");
        }
        if(null != feedback.getStatus()){
        	sql.append(" and f.status = ?");
        	params.add(feedback.getStatus());
        }
        if(null != start_time){
        	sql.append(" and f.time >=?");
        	params.add(start_time);
        }
        if(null != end_time){
        	sql.append(" and f.time <= ?");
        	params.add(end_time);

        }

        sql.append(" order by f.id desc");
		return feedbackDao.listFeedbacks(sql.toString(),params.toArray(),pager);
	}
	/**
	 * @throws SQLException
	* @Title: deleteFeedback
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteFeedback(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_feedback set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		feedbackDao.deleteFeedback(sql.toString());
		return messages;
	}
	/**
	 * @throws SQLException
	* @Title: getFeedbackDetail
	* @Description:
	* @param id
	* @return
	* @return FeedbackDto
	* @throws
	*/
	public FeedbackDto getFeedbackDetail(Integer feedback_id) throws SQLException {

		if(null == feedback_id){
			return null;
		}
		FeedbackDto feedback = feedbackDao.getFeedbackDetail(feedback_id);
		List<Map<String,Object>> replyList = feedbackDao.listFeedbackReply(feedback_id);
		feedback.setReplyList(replyList);
		return feedback;
	}
	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: replyFeedback
	* @Description:
	* @param id
	* @param reply_content
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> replyFeedback(Integer id,Integer responder_id, String reply_content) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {

		Reply_FeedbackEntity reply = new Reply_FeedbackEntity();
		reply.setContent(reply_content);
		reply.setFeedback_id(id);
		reply.setReplier_id(SystemGlobals.getIntPreference("site.adminUser.id",0));
		reply.setReply_time(new Date());
		reply.setResponder_id(responder_id);
		reply.setStatus(DataStatus.ENABLED);

		List<String> messages = ValidateUtil.validate(reply);
		if(messages.size() > 0){
			return messages;
		}

		feedbackDao.replyFeedback(reply);
		return null;
	}
}
