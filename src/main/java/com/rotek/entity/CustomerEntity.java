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
	public static final int KHLB = 0;  // 客户类别：1-代理商，2-客户
	public static int getKhlb() {
		return KHLB;
	}

	public static int getSsjb() {
		return SSJB;
	}

	private static final int SSJB = 0; // 所属级别:1,2,3:一二三级代理商	

	@Column(name="id")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "r_customer_id")
	private Integer r_customer_Id ;     // 客户ID外键

	@Column(name = "MC")
	private String MC ;    			   // 客户名称
	
	@Column(name = "TXDZ")
	private String TXDZ ;    		   // 通信地址

	@Column(name = "LXFS")
	private String LXFS ;  		  // 联系方式

	@Column(name = "LXR")
	private String LXR ;   	          // 联系人

	@Column(name = "LXDH")
	private	String	LXDH ;      	  // 联系电话	

	@Column(name = "DLQY")
	private String	DLQY ;    		 // 代理区域	

	@Column(name = "JWDDZ")
	private String	JWDDZ ;  		// 经纬度地址	

	@Column(name = "STATUS")
	private Date STATUS ;      		// 是否可用	
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getR_customer_Id() {
		return r_customer_Id;
	}

	public void setR_customer_Id(Integer r_customer_Id) {
		this.r_customer_Id = r_customer_Id;
	}

	public String getMC() {
		return MC;
	}

	public void setMC(String mC) {
		MC = mC;
	}

	public String getTXDZ() {
		return TXDZ;
	}

	public void setTXDZ(String tXDZ) {
		TXDZ = tXDZ;
	}

	public String getLXFS() {
		return LXFS;
	}

	public void setLXFS(String lXFS) {
		LXFS = lXFS;
	}

	public String getLXR() {
		return LXR;
	}

	public void setLXR(String lXR) {
		LXR = lXR;
	}

	public String getLXDH() {
		return LXDH;
	}

	public void setLXDH(String lXDH) {
		LXDH = lXDH;
	}

	public String getDLQY() {
		return DLQY;
	}

	public void setDLQY(String dLQY) {
		DLQY = dLQY;
	}

	public String getJWDDZ() {
		return JWDDZ;
	}

	public void setJWDDZ(String jWDDZ) {
		JWDDZ = jWDDZ;
	}

	public Date getSTATUS() {
		return STATUS;
	}

	public void setSTATUS(Date sTATUS) {
		STATUS = sTATUS;
	}

}
