/**
* @FileName: FeedbackDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-9 下午01:42:26
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.dto.FeedbackDto;
import com.rotek.entity.Reply_FeedbackEntity;
import com.cta.platform.persistence.dao.BaseDaoImpl;
import com.cta.platform.util.ListPager;

/**
 * @ClassName: FeedbackDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-9 下午01:42:26
 */
@Repository
public class FeedbackDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listFeedbacks
	* @Description:
	* @param string
	* @param array
	* @param pager
	* @return
	* @return List<FeedbackDto>
	* @throws
	*/
	public List<FeedbackDto> listFeedbacks(String sql, Object[] params,
			ListPager pager) throws SQLException {

		return this.selectPage(sql, params, FeedbackDto.class, pager);
	}

	/**
	 * @throws SQLException
	* @Title: deleteFeedback
	* @Description:
	* @param string
	* @return void
	* @throws
	*/
	public void deleteFeedback(String sql) throws SQLException {

		this.executeUpdate(sql);
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
	public FeedbackDto getFeedbackDetail(Integer id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select f.id, f.content, f.user_id, f.time, f.type, f.status,u.nick_name user_name from mf_feedback f");
        sql.append(" left join mf_user u on f.user_id = u.id where f.id = ?");

        return this.selectOne(sql.toString(), new Integer[]{id}, FeedbackDto.class);
	}

	/**
	 * @throws SQLException
	* @Title: replyFeedback
	* @Description:
	* @param reply
	* @return void
	* @throws
	*/
	public void replyFeedback(Reply_FeedbackEntity reply) throws SQLException {

		this.insert(reply);
	}

	/**
	 * @throws SQLException
	* @Title: listFeedbackReply
	* @Description:
	* @param feedback_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listFeedbackReply(Integer feedback_id) throws SQLException {

		String sql = "select u.nick_name replier_name,rf.content from mf_reply_feedback rf left join mf_user u on rf.replier_id = u.id where rf.feedback_id = ?";
		return this.executeQuery(sql, new Integer[]{feedback_id});
	}
}
