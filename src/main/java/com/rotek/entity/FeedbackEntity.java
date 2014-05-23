package com.rotek.entity;

import java.io.Serializable;
import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.Table;

/**
* @ClassName: FeedbackEntity
* @Description:
* @author chenwenpeng
* @date 2013-8-2 上午10:09:51
*/
@Table(name="MF_FEEDBACK")
public class FeedbackEntity  implements Serializable{

	/** The long serialVersionUID*/
	private static final long serialVersionUID = -4426600304505217116L;

	@Column(name="id")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;//
	@Column(name="content")
	@NotEmpty()
	private String content;//
	@Column(name="user_id")
	@NotEmpty()
	private Integer user_id;//
	@Column(name="time")
	@NotEmpty()
	private Date time;//
	@Column(name="type")
	@NotEmpty()
	private Integer type;//
	@Column(name="status")
	@NotEmpty()
	private Integer status;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
