/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.Ltd.
*/
package com.rotek.entity;

import java.io.Serializable;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Table;

/**
* @ClassName: CarbonFilterDetailEntity
* @Description: 碳滤器  详细信息（参数的额定值）实体类
* @Author WangJuZhu
* @date 2014年6月25日 下午9:47:15
* @Version:1.1.0
*/
@Table(name = "r_carbonfilter_detail")
public class CarbonFilterDetailEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = 467569802272805905L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_CARBONFILTER_GROUP_ID")
	private Integer r_carbonfilter_group_id ;   	// 碳滤器组ID
	
	@Column(name = "SPECIFIC_PART")
	private String specific_part ;   	// 具体的零件名称，比如1号碳滤器前，1号碳滤器后
	
	@Column(name = "SPECIFIC_BH")
	private String specific_bh ;   	// 具体的零件编号，比如1号碳滤器前编号  000301，1号碳滤器后   000302 等
	
	@Column(name = "LLSL")
	private Integer llsl ;   	// 滤料数量

	@Column(name = "CARBON_PP")
	private String carbon_pp ;    	// 品牌
	
	@Column(name = "CARBON_XH")
	private String carbon_xh ;    	// 型号
	
	@Column(name = "CKDJ")
	private Double ckdj ;    	// 参考单价
	
	@Column(name = "EDGHSJ")
	private Double edghsj ;    	// 额定更换时间
	
	@Column(name = "STATUS")
	private Integer status ; 		// 状态

}
