/**
* @FileName: FeedbackDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-9 下午01:38:58
* @version V1.0
*/
package com.rotek.dto;

import java.util.List;
import java.util.Map;

import com.rotek.entity.FeedbackEntity;
import com.cta.platform.persistence.annotation.Column;

/**
 * @ClassName: FeedbackDto
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-9 下午01:38:58
 *
 */
public class FeedbackDto extends FeedbackEntity{

	@Column(name="user_name")
	private String user_name;

	@Column(name="replyList")
	private List<Map<String,Object>> replyList;


	public List<Map<String, Object>> getReplyList() {
		return replyList;
	}

	public void setReplyList(List<Map<String, Object>> replyList) {
		this.replyList = replyList;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
}
