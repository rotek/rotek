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
* @ClassName:AlgorithmEntity
* @Description:
* @Author liusw
* @date 2014年6月29日 下午3:11:00
* @Version:1.1.0
*/
@Table(name = "r_algorithm")
public class AlgorithmsEntity extends BaseEntity {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 6144365858889903629L;
	
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="ID")
	private Integer id;
	
	@Column(name = "R_ALGORITHMTYPE_ID")    // 算法ID外键
	private Integer r_algorithmtype_id ;                            

	@Column(name = "R_CUSTOMER_ID")                // 客户ID外键
	private Integer r_customer_id ;    

	@Column(name = "R_PROJECT_ID")                    // 工程ID外键
	private Integer r_project_id ;    			                 

	@Column(name = "R_COMPONET_ID")              // 零件ID外键
	private Integer r_component_id ;    		   

	@Column(name = "R_COMPONET_DETAIL_ID")   // 零件部位ID外键
	private Integer r_component_detail_id ;  		     

	@Column(name = "SPECIFICPARTPARAM")  // 零件部位的参数名称
	private String	specificpartparam ;    		  

	@Column(name = "SPECIFICPARTEDZ")        // 零件部位的参数对应的额定值
	private String	specificpartedz ;  		 

	@Column(name = "LJGHSJ")                                      // 零件更换时间
	private Date ljghsj ;   	         

	@Column(name = "LJEDYXSJ")                                // 零件额定运行时间标准值（小时）
	private	Integer	ljedyxsj ;      	 	

	@Column(name = "LJYXSJ")                                     // 累计运行时间（小时）=目前时间-更换时间(小时)
	private Integer	ljyxsj ;    		  

	@Column(name = "GLQCESJ")							        // 过滤器超额时间设置[N]
	private Integer	glqcesj ;  		  

	@Column(name = "GLQCEBFB")								 // 过滤器超额百分比设置[n]
	private Double glqcebfb ;      		 

	@Column(name = "MXTCSLLCESJ")                      // 膜系统产水流量超额时间设置[N]
	private Integer lxfs ;  		      

	@Column(name = "MXTCSLLBZ")                         // 膜系统产水流量标准值[S]
	private Double mxtcsllbz ;   	         

	@Column(name = "MXTHSLCESJ")                      // 膜系统回收率超额时间[N]
	private	Integer	mxthslcesj ;      	  

	@Column(name = "MXTHSLBZ")                         // 膜系统回收率标准值[S]
	private Double	mxthslbz ;    		  
 
	@Column(name = "DWSJYLSD")                         // 单位时间用量设定值[S]
	private Integer	dwsjylsd ;  		  

	@Column(name = "DYSYLTJ")                           // 月使用量统计
	private Integer dysyltj ;      		  

	@Column(name = "MXTCSDDLCESJ")              // 膜系统产水电导率超额时间设置[N]
	private Integer mxtcsddlcesj ;   	         

	@Column(name = "MXTCSDDLBZ")                  // 膜系统产水电导率标准值[S]
	private	Double	mxtcsddlbz ;      	  	

	@Column(name = "STATUS")                             // 是否可用
	private Integer status ;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getR_algorithmtype_id() {
		return r_algorithmtype_id;
	}

	public void setR_algorithmtype_id(Integer r_algorithmtype_id) {
		this.r_algorithmtype_id = r_algorithmtype_id;
	}

	public Integer getR_customer_id() {
		return r_customer_id;
	}

	public void setR_customer_id(Integer r_customer_id) {
		this.r_customer_id = r_customer_id;
	}

	public Integer getR_project_id() {
		return r_project_id;
	}

	public void setR_project_id(Integer r_project_id) {
		this.r_project_id = r_project_id;
	}

	public Integer getR_component_id() {
		return r_component_id;
	}

	public void setR_component_id(Integer r_component_id) {
		this.r_component_id = r_component_id;
	}

	public Integer getR_component_detail_id() {
		return r_component_detail_id;
	}

	public void setR_component_detail_id(Integer r_component_detail_id) {
		this.r_component_detail_id = r_component_detail_id;
	}

	public String getSpecificpartparam() {
		return specificpartparam;
	}

	public void setSpecificpartparam(String specificpartparam) {
		this.specificpartparam = specificpartparam;
	}

	public String getSpecificpartedz() {
		return specificpartedz;
	}

	public void setSpecificpartedz(String specificpartedz) {
		this.specificpartedz = specificpartedz;
	}

	public Date getLjghsj() {
		return ljghsj;
	}

	public void setLjghsj(Date ljghsj) {
		this.ljghsj = ljghsj;
	}

	public Integer getLjedyxsj() {
		return ljedyxsj;
	}

	public void setLjedyxsj(Integer ljedyxsj) {
		this.ljedyxsj = ljedyxsj;
	}

	public Integer getLjyxsj() {
		return ljyxsj;
	}

	public void setLjyxsj(Integer ljyxsj) {
		this.ljyxsj = ljyxsj;
	}

	public Integer getGlqcesj() {
		return glqcesj;
	}

	public void setGlqcesj(Integer glqcesj) {
		this.glqcesj = glqcesj;
	}

	public Double getGlqcebfb() {
		return glqcebfb;
	}

	public void setGlqcebfb(Double glqcebfb) {
		this.glqcebfb = glqcebfb;
	}

	public Integer getLxfs() {
		return lxfs;
	}

	public void setLxfs(Integer lxfs) {
		this.lxfs = lxfs;
	}

	public Double getMxtcsllbz() {
		return mxtcsllbz;
	}

	public void setMxtcsllbz(Double mxtcsllbz) {
		this.mxtcsllbz = mxtcsllbz;
	}

	public Integer getMxthslcesj() {
		return mxthslcesj;
	}

	public void setMxthslcesj(Integer mxthslcesj) {
		this.mxthslcesj = mxthslcesj;
	}

	public Double getMxthslbz() {
		return mxthslbz;
	}

	public void setMxthslbz(Double mxthslbz) {
		this.mxthslbz = mxthslbz;
	}

	public Integer getDwsjylsd() {
		return dwsjylsd;
	}

	public void setDwsjylsd(Integer dwsjylsd) {
		this.dwsjylsd = dwsjylsd;
	}

	public Integer getDysyltj() {
		return dysyltj;
	}

	public void setDysyltj(Integer dysyltj) {
		this.dysyltj = dysyltj;
	}

	public Integer getMxtcsddlcesj() {
		return mxtcsddlcesj;
	}

	public void setMxtcsddlcesj(Integer mxtcsddlcesj) {
		this.mxtcsddlcesj = mxtcsddlcesj;
	}

	public Double getMxtcsddlbz() {
		return mxtcsddlbz;
	}

	public void setMxtcsddlbz(Double mxtcsddlbz) {
		this.mxtcsddlbz = mxtcsddlbz;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}      		 
	
	
}
