/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.Ltd.
*/
package com.rotek.entity;

import java.io.Serializable;
import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
* @ClassName:CustomerEntity
* @Description:
* @Author Liusw
* @date 2014年6月14日 下午9:56:56
* @Version:1.1.0
*/
@Table(name = "r_customer")
public class CustomerEntity implements Serializable {

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name = "R_CUSTOMER_ID")
	private Integer r_customer_id ;     // 客户ID外键

	@Column(name = "KHLB")             // 客户类别：1-一级代理商，2-二级代理商，3-客户
	private Integer khlb ;    

	@Column(name = "MC")
	private String mc ;    			   // 客户名称

	@Column(name = "TXDZ")
	private String txdz ;    		   // 通信地址

	@Column(name = "LXFS")
	private String lxfs ;  		      // 联系方式

	@Column(name = "LXR")
	private String lxr ;   	          // 联系人

	@Column(name = "LXDH")
	private	String	lxdh ;      	  // 联系电话	

	@Column(name = "DLQY")
	private String	dlqy ;    		  // 代理区域	


	@Column(name = "JWDDZ")
	private String	jwddz ;  		  // 经纬度地址	

	@Column(name = "STATUS")
	private Integer status ;      		  // 是否可用	
	
	public Integer getR_customer_id() {
		return r_customer_id;
	}

	public void setR_customer_id(Integer r_customer_id) {
		this.r_customer_id = r_customer_id;
	}

	public Integer getKhlb() {
		return khlb;
	}

	public void setKhlb(Integer khlb) {
		this.khlb = khlb;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public String getTxdz() {
		return txdz;
	}

	public void setTxdz(String txdz) {
		this.txdz = txdz;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getLxr() {
		return lxr;
	}

	public void setLxr(String lxr) {
		this.lxr = lxr;
	}

	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	public String getDlqy() {
		return dlqy;
	}

	public void setDlqy(String dlqy) {
		this.dlqy = dlqy;
	}

	public String getJwddz() {
		return jwddz;
	}

	public void setJwddz(String jwddz) {
		this.jwddz = jwddz;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
