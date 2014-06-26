/**
* @Copyright:Copyright (c) 2013 - 2100
* @Company:JXWY Co.,Ltd.
*/
package com.rotek.constant;

/**
 * @ClassName:ComponentType
 * @Description: 零件分类（和零件分组是对应的）
 * @Author WangJuZhu
 * @date 2014年6月26日 下午3:55:19
 * @Version:1.1.0
 */
public enum ComponentType {

	PUMP(1,"泵"),
	SAND_FILTER(2,"砂滤器"),
	CARBON_FILTE(3,"碳滤器"),
	SOFTENER(4,"软化器"),
	FILTER_GROUP(5,"过滤器"),
	FILM_GROUP(6,"膜"),
	UVSTERILIZER(7,"紫外杀菌器"),
	TANK_GROUP(8,"水箱"),
	DOSESETTING(9,"加药装置");
	
	private Integer code ;
	private String name ;
	
	ComponentType(Integer code , String name ){
		this.code = code ;
		this.name = name ;
	}

	/** @return code */
	public Integer getCode() {
		return code;
	}

	/** @return name */
	public String getName() {
		return name;
	}
	
}
