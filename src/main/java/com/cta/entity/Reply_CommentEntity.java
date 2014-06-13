/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.cta.entity;

import java.io.Serializable;
import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: Reply_CommentEntity
 * @Description: 订单评论的回复
 * @author chenwenpeng
 * @date 2013-6-11 下午3:39:48
 */
@Table(name="MF_REPLY_COMMENT")
public class Reply_CommentEntity implements Serializable{
	/** The long serialVersionUID*/
	private static final long serialVersionUID = 2273673135501731L;
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;// 
	@Column(name="comment_id")
	@NotEmpty()
	private Integer comment_id;// 
	@Column(name="replier_id")
	@NotEmpty()
	private Integer replier_id;// 
	@Column(name="content")
	@NotEmpty()
	private String content;// 
	@Column(name="commenter_id")
	@NotEmpty()
	private Integer commenter_id;// 
	@Column(name="reply_time")
	@NotEmpty()
	private Date reply_time;// 
	@Column(name="status")
	@NotEmpty()
	private Integer status;//
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getComment_id() {
		return comment_id;
	}
	public void setComment_id(Integer comment_id) {
		this.comment_id = comment_id;
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
	public Integer getCommenter_id() {
		return commenter_id;
	}
	public void setCommenter_id(Integer commenter_id) {
		this.commenter_id = commenter_id;
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
