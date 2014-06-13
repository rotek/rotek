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
import com.cta.platform.persistence.annotation.Length;
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: CommentEntity
 * @Description: 评论表对应的实体类
 * @author chenwenpeng
 * @date 2013-6-11 下午2:58:33
 */
@Table(name="MF_COMMENT")
public class CommentEntity implements Serializable{

	/** The long serialVersionUID*/
	private static final long serialVersionUID = 2137718352684486L;
	@ID(strategy= StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;// 
	@Column(name="order_id")
	@NotEmpty()
	private Integer order_id;// 
	@Column(name="rest_id")
	@NotEmpty()
	private Integer rest_id;// 
	@Column(name="comment_time")
	@NotEmpty()
	private Date comment_time;// 
	@Column(name="content")
	@Length(minLength=1,maxLength=200,message="评论内容的长度必须在1-200字符之间")
	private String content;// 
	@Column(name="taste")
	@NotEmpty()
	private Integer taste;// 
	@Column(name="speed")
	@NotEmpty()
	private Integer speed;// 
	@Column(name="attitude")
	@NotEmpty()
	private Integer attitude;// 
	@Column(name="user_id")
	@NotEmpty()
	private Integer user_id;// 
	@Column(name="status")
	@NotEmpty()
	private Integer status;
	
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrder_id() {
		return order_id;
	}
	public void setOrder_id(Integer order_id) {
		this.order_id = order_id;
	}
	public Integer getRest_id() {
		return rest_id;
	}
	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
	}
	public Date getComment_time() {
		return comment_time;
	}
	public void setComment_time(Date comment_time) {
		this.comment_time = comment_time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getTaste() {
		return taste;
	}
	public void setTaste(Integer taste) {
		this.taste = taste;
	}
	public Integer getSpeed() {
		return speed;
	}
	public void setSpeed(Integer speed) {
		this.speed = speed;
	}
	public Integer getAttitude() {
		return attitude;
	}
	public void setAttitude(Integer attitude) {
		this.attitude = attitude;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
