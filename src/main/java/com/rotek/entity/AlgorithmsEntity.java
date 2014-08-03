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
	
	@Column(name = "ALGORITHM_TYPE")    	// 算法类别，1 --> 算法1 ...... 8 --> 算法8,一期不做维护，直接写对应的ID
	private Integer algorithm_type ;
	
	@Column(name = "ALGORITHM_ALIAS")     	// 提示别名,为每一条记录设置一个别名
	private String algorithm_alias ;
	
	@Column(name = "TIP_CONTENT")     	// 提示内容
	private String tip_content ;

	@Column(name = "R_CUSTOMER_ID")   		// 客户ID外键
	private Integer r_customer_id ;    

	@Column(name = "R_PROJECT_ID")  		// 工程ID外键
	private Integer r_project_id ;    			                 

	@Column(name = "R_COMPONENT_GROUP_ID")  		// 零件（组）ID外键
	private Integer r_component_group_id ;    		   

	@Column(name = "R_COMPONENT_DETAIL_ID")		// 零件部位(零件)ID外键
	private Integer r_component_detail_id ;  		     
	
	@Column(name = "MONITOR_TYPE")  			// 监测类型：1-水质监测，2-流量监测，3-压力监测，4-EMC水质监测，5-EMC流量监测，6-EMC压力监测
	private Integer monitor_type;
	
	@Column(name = "SPECIFICPART_PARAM")  	// 零件部位(零件)的参数名称
	private String	specificpart_param ;    		  

	@Column(name = "SPECIFICPART_EDZ")    	// 零件部位(零件)的参数对应的额定值
	private Double	specificpart_edz ;  		 

	@Column(name = "LJGHSJ")    			// 零件（组）更换时间
	private Date ljghsj ;   	         

	@Column(name = "LJEDYXSJ")    			// 零件（组）额定运行时间标准值（小时）
	private	Integer	ljedyxsj ;      	 	

	@Column(name = "LJYXSJ")  				// 累计运行时间（小时）=目前时间-更换时间(小时)
	private Integer	ljyxsj ;    		  

	@Column(name = "GLQ_CESJ")			 	// 过滤器（组）超额时间设置[N]
	private Integer	glq_cesj ;  		  

	@Column(name = "GLQ_CEBFB")				// 过滤器（组）超额百分比设置[n]
	private Double glq_cebfb ;      		 

	@Column(name = "MXT_CSLLCESJ")			// 膜（组）系统产水流量超额时间设置[N]
	private Integer mxt_csllcesj ;  		      

	@Column(name = "MXT_CSLLBZ")			// 膜（组）系统产水流量标准值[S]
	private Double mxt_csllbz ;   	         

	@Column(name = "MXT_HSLCESJ")       	// 膜（组）系统回收率超额时间[N]
	private	Double	mxt_hslcesj ;      	  

	@Column(name = "MXT_HSLBZ")       		// 膜（组）系统回收率标准值[S]
	private Double	mxt_hslbz ;    	
	
	@Column(name = "MXT_HSLCEBFB")       	// 膜（组）系统回收率超额百分比[S]
	private Double	mxt_hslcebfb ;   
	
	@Column(name = "MXT_CSDDLCESJ") 		// 膜（组）系统产水电导率超额时间设置[N]
	private Integer mxt_csddlcesj ;   	         

	@Column(name = "MXT_CSDDLBZ")     		// 膜（组）系统产水电导率标准值[S]
	private	Double	mxt_csddlbz ; 
 
	@Column(name = "DWSJYLSD")   			// 单位时间用量设定值[S]
	private Integer	dwsjylsd ;  		  

	@Column(name = "DYSYLTJ")      			// 月使用量统计
	private String dysyltj ;      		  
	
	@Column(name = "SITEREAL_TABLE_NAME")		// 现场实时数据表名称
	private String sitereal_table_name ;   	         

	@Column(name = "SITEREAL_FIELD_NAME") 		// 现场实时数据字段名称
	private	String	sitereal_field_name ;
	
	@Column(name = "SITEREAL_FIELD_CODE") 		// 现场实时数据编号
	private	String	sitereal_field_code ;

	@Column(name = "STATUS")     				// 是否可用
	private Integer status ;

	/** @return id */
	public Integer getId() {
		return id;
	}

	/** @param id id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return algorithm_type */
	public Integer getAlgorithm_type() {
		return algorithm_type;
	}

	/** @param algorithm_type algorithm_type to set */
	public void setAlgorithm_type(Integer algorithm_type) {
		this.algorithm_type = algorithm_type;
	}

	/** @return algorithm_alias */
	public String getAlgorithm_alias() {
		return algorithm_alias;
	}

	/** @param algorithm_alias algorithm_alias to set */
	public void setAlgorithm_alias(String algorithm_alias) {
		this.algorithm_alias = algorithm_alias;
	}

	/** @return r_customer_id */
	public Integer getR_customer_id() {
		return r_customer_id;
	}

	/** @param r_customer_id r_customer_id to set */
	public void setR_customer_id(Integer r_customer_id) {
		this.r_customer_id = r_customer_id;
	}

	/** @return r_project_id */
	public Integer getR_project_id() {
		return r_project_id;
	}

	/** @param r_project_id r_project_id to set */
	public void setR_project_id(Integer r_project_id) {
		this.r_project_id = r_project_id;
	}

	/** @return r_component_group_id */
	public Integer getR_component_group_id() {
		return r_component_group_id;
	}

	/** @param r_component_group_id r_component_group_id to set */
	public void setR_component_group_id(Integer r_component_group_id) {
		this.r_component_group_id = r_component_group_id;
	}

	/** @return r_component_detail_id */
	public Integer getR_component_detail_id() {
		return r_component_detail_id;
	}

	/** @param r_component_detail_id r_component_detail_id to set */
	public void setR_component_detail_id(Integer r_component_detail_id) {
		this.r_component_detail_id = r_component_detail_id;
	}

	/** @return monitor_type */
	public Integer getMonitor_type() {
		return monitor_type;
	}

	/** @param monitor_type monitor_type to set */
	public void setMonitor_type(Integer monitor_type) {
		this.monitor_type = monitor_type;
	}

	/** @return specificpart_param */
	public String getSpecificpart_param() {
		return specificpart_param;
	}

	/** @param specificpart_param specificpart_param to set */
	public void setSpecificpart_param(String specificpart_param) {
		this.specificpart_param = specificpart_param;
	}

	/** @return specificpart_edz */
	public Double getSpecificpart_edz() {
		return specificpart_edz;
	}

	/** @param specificpart_edz specificpart_edz to set */
	public void setSpecificpart_edz(Double specificpart_edz) {
		this.specificpart_edz = specificpart_edz;
	}

	/** @return ljghsj */
	public Date getLjghsj() {
		return ljghsj;
	}

	/** @param ljghsj ljghsj to set */
	public void setLjghsj(Date ljghsj) {
		this.ljghsj = ljghsj;
	}

	/** @return ljedyxsj */
	public Integer getLjedyxsj() {
		return ljedyxsj;
	}

	/** @param ljedyxsj ljedyxsj to set */
	public void setLjedyxsj(Integer ljedyxsj) {
		this.ljedyxsj = ljedyxsj;
	}

	/** @return ljyxsj */
	public Integer getLjyxsj() {
		return ljyxsj;
	}

	/** @param ljyxsj ljyxsj to set */
	public void setLjyxsj(Integer ljyxsj) {
		this.ljyxsj = ljyxsj;
	}

	/** @return glq_cesj */
	public Integer getGlq_cesj() {
		return glq_cesj;
	}

	/** @param glq_cesj glq_cesj to set */
	public void setGlq_cesj(Integer glq_cesj) {
		this.glq_cesj = glq_cesj;
	}

	/** @return glq_cebfb */
	public Double getGlq_cebfb() {
		return glq_cebfb;
	}

	/** @param glq_cebfb glq_cebfb to set */
	public void setGlq_cebfb(Double glq_cebfb) {
		this.glq_cebfb = glq_cebfb;
	}

	/** @return mxt_csllcesj */
	public Integer getMxt_csllcesj() {
		return mxt_csllcesj;
	}

	/** @param mxt_csllcesj mxt_csllcesj to set */
	public void setMxt_csllcesj(Integer mxt_csllcesj) {
		this.mxt_csllcesj = mxt_csllcesj;
	}

	/** @return mxt_csllbz */
	public Double getMxt_csllbz() {
		return mxt_csllbz;
	}

	/** @param mxt_csllbz mxt_csllbz to set */
	public void setMxt_csllbz(Double mxt_csllbz) {
		this.mxt_csllbz = mxt_csllbz;
	}

	/** @return mxt_hslcesj */
	public Double getMxt_hslcesj() {
		return mxt_hslcesj;
	}

	/** @param mxt_hslcesj mxt_hslcesj to set */
	public void setMxt_hslcesj(Double mxt_hslcesj) {
		this.mxt_hslcesj = mxt_hslcesj;
	}

	/** @return mxt_hslbz */
	public Double getMxt_hslbz() {
		return mxt_hslbz;
	}

	/** @param mxt_hslbz mxt_hslbz to set */
	public void setMxt_hslbz(Double mxt_hslbz) {
		this.mxt_hslbz = mxt_hslbz;
	}

	/** @return mxt_hslcebfb */
	public Double getMxt_hslcebfb() {
		return mxt_hslcebfb;
	}

	/** @param mxt_hslcebfb mxt_hslcebfb to set */
	public void setMxt_hslcebfb(Double mxt_hslcebfb) {
		this.mxt_hslcebfb = mxt_hslcebfb;
	}

	/** @return mxt_csddlcesj */
	public Integer getMxt_csddlcesj() {
		return mxt_csddlcesj;
	}

	/** @param mxt_csddlcesj mxt_csddlcesj to set */
	public void setMxt_csddlcesj(Integer mxt_csddlcesj) {
		this.mxt_csddlcesj = mxt_csddlcesj;
	}

	/** @return mxt_csddlbz */
	public Double getMxt_csddlbz() {
		return mxt_csddlbz;
	}

	/** @param mxt_csddlbz mxt_csddlbz to set */
	public void setMxt_csddlbz(Double mxt_csddlbz) {
		this.mxt_csddlbz = mxt_csddlbz;
	}

	/** @return dwsjylsd */
	public Integer getDwsjylsd() {
		return dwsjylsd;
	}

	/** @param dwsjylsd dwsjylsd to set */
	public void setDwsjylsd(Integer dwsjylsd) {
		this.dwsjylsd = dwsjylsd;
	}

	/** @return dysyltj */
	public String getDysyltj() {
		return dysyltj;
	}

	/** @param dysyltj dysyltj to set */
	public void setDysyltj(String dysyltj) {
		this.dysyltj = dysyltj;
	}

	/** @return sitereal_table_name */
	public String getSitereal_table_name() {
		return sitereal_table_name;
	}

	/** @param sitereal_table_name sitereal_table_name to set */
	public void setSitereal_table_name(String sitereal_table_name) {
		this.sitereal_table_name = sitereal_table_name;
	}

	/** @return sitereal_field_name */
	public String getSitereal_field_name() {
		return sitereal_field_name;
	}

	/** @param sitereal_field_name sitereal_field_name to set */
	public void setSitereal_field_name(String sitereal_field_name) {
		this.sitereal_field_name = sitereal_field_name;
	}

	/** @return sitereal_field_code */
	public String getSitereal_field_code() {
		return sitereal_field_code;
	}

	/** @param sitereal_field_code sitereal_field_code to set */
	public void setSitereal_field_code(String sitereal_field_code) {
		this.sitereal_field_code = sitereal_field_code;
	}

	/** @return status */
	public Integer getStatus() {
		return status;
	}

	/** @param status status to set */
	public void setStatus(Integer status) {
		this.status = status;
	}

	/** @return tip_content */
	public String getTip_content() {
		return tip_content;
	}

	/** @param tip_content tip_content to set */
	public void setTip_content(String tip_content) {
		this.tip_content = tip_content;
	}

}
