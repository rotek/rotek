/**
 * @FileName: NodeEntity.java
 * @Package com.rotek.entity
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-6-7 上午11:42:47
 * @version V1.0
 */
package com.rotek.entity;

import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;


/**
 * @author chenwenpeng
 *
 */

@Table(name = "r_consultant")
public class ConsultantEntity extends BaseEntity {

	@ID(strategy = StrategyType.IDENTITY)
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "sender_id")
	private Integer sender_id;
	
	@Column(name = "sender_id")
	private Integer response_id;
	
	@Column(name = "consultant_content")
	private String consultant_content;

	@Column(name = "response_content")
	private String response_content;
	
	@Column(name = "send_time")
	private Date send_time;
	
	@Column(name = "response_time")
	private Date response_time;
	
	@Column(name = "type")
	private Integer type;
	
	@Column(name = "status")
	private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSender_id() {
		return sender_id;
	}

	public void setSender_id(Integer sender_id) {
		this.sender_id = sender_id;
	}

	public Integer getResponse_id() {
		return response_id;
	}

	public void setResponse_id(Integer response_id) {
		this.response_id = response_id;
	}

	public String getConsultant_content() {
		return consultant_content;
	}

	public void setConsultant_content(String consultant_content) {
		this.consultant_content = consultant_content;
	}

	public String getResponse_content() {
		return response_content;
	}

	public void setResponse_content(String response_content) {
		this.response_content = response_content;
	}

	public Date getSend_time() {
		return send_time;
	}

	public void setSend_time(Date send_time) {
		this.send_time = send_time;
	}

	public Date getResponse_time() {
		return response_time;
	}

	public void setResponse_time(Date response_time) {
		this.response_time = response_time;
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
