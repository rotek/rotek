/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.rotek.entity;

import java.io.Serializable;
import java.util.Date;

import com.rotek.platform.constant.StrategyType;
import com.rotek.platform.persistence.annotation.Column;
import com.rotek.platform.persistence.annotation.ID;
import com.rotek.platform.persistence.annotation.NotEmpty;
import com.rotek.platform.persistence.annotation.Table;

/**
 * @ClassName: Reply_FeedbackEntity
 * @Description: 
 * @author chenwenpeng
 * @date 2013-8-2 下午12:54:12
 */
@Table(name="MF_REPLY_FEEDBACK")
public class Reply_FeedbackEntity implements Serializable{

	@Column(name="id")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;//
	@Column(name="feedback_id")
	@NotEmpty()
	private Integer feedback_id;//
	@Column(name="replier_id")
	@NotEmpty()
	private Integer replier_id;//
	@Column(name="content")
	@NotEmpty()
	private String content;// 
	@Column(name="responder_id")
	@NotEmpty()
	private Integer responder_id;//
	@Column(name="reply_time")
	@NotEmpty()
	private Date reply_time;// 
	@Column(name="status")
	@NotEmpty()
	private Integer status;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getFeedback_id() {
		return feedback_id;
	}
	public void setFeedback_id(Integer feedback_id) {
		this.feedback_id = feedback_id;
	}
	public Integer getReplier_id() {
		return replier_id;
	}
	public void setReplier_id(Integer replier_id) {
		this.replier_id = replier_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getResponder_id() {
		return responder_id;
	}
	public void setResponder_id(Integer responder_id) {
		this.responder_id = responder_id;
	}
	public Date getReply_time() {
		return reply_time;
	}
	public void setReply_time(Date reply_time) {
		this.reply_time = reply_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
