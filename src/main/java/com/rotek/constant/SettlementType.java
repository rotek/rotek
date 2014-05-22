/**
* @FileName: SettlementType.java
* @Package com.rotek.constant
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-20 下午03:35:22
* @version V1.0
*/
package com.rotek.constant;


/**
 * @ClassName: SettlementType
 * @Description: 店铺结算方式
 * @author chenwenpeng
 * @date 2013-7-20 下午03:35:22
 *
 */
public enum SettlementType{

	BYORDERPRICE(1,"按订单金额"),
	BYORDERMENUCOUNT(2,"按订单菜品数");

	private int type;
	private String name;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	SettlementType(int type,String name){
		this.type = type;
		this.name = name;
	}
}
