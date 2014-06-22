/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.Ltd.
*/
package com.rotek.entity;

import java.util.Date;
import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
* @ClassName:CustomerDocEntity
* @Description:
* @Author liusw
* @date 2014年6月22日 上午10:02:21
* @Version:1.1.0
*/
@Table(name = "r_customerdocinfo")
public class CustomerDocEntity {
	
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name = "R_CUSTOMER_ID")
	private Integer r_customer_id ;     // 客户ID外键

	@Column(name = "KHZLLB")             // 客户资料类别：1-文档，2-演示稿，3-图片，4-视频，5-监测图，6-证件
	private Integer khzllb ;    

	@Column(name = "KHZLMC")
	private String khzlmc ;    			   // 客户资料名称

	@Column(name = "KHZLFJ")
	private String khzlfj ;    		          // 客户资料附件

	@Column(name = "DLSZJYXQ")
	private Date dlszjyxq ;   	          // 有效期

	@Column(name = "STATUS")
	private Integer status ;      		  // 是否可用	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getR_customer_id() {
		return r_customer_id;
	}

	public void setR_customer_id(Integer r_customer_id) {
		this.r_customer_id = r_customer_id;
	}

	public Integer getKhzllb() {
		return khzllb;
	}

	public void setKhzllb(Integer khzllb) {
		this.khzllb = khzllb;
	}

	public String getKhzlmc() {
		return khzlmc;
	}

	public void setKhzlmc(String khzlmc) {
		this.khzlmc = khzlmc;
	}

	public String getKhzlfj() {
		return khzlfj;
	}

	public void setKhzlfj(String khzlfj) {
		this.khzlfj = khzlfj;
	}

	public Date getDlszjyxq() {
		return dlszjyxq;
	}

	public void setDlszjyxq(Date dlszjyxq) {
		this.dlszjyxq = dlszjyxq;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}


}
