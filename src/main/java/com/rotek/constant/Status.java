package com.rotek.constant;

/**
* @ClassName:Status
* @Description: 状态分类
* @Author WangJuZhu
* @date 2014年6月10日 下午4:09:46
* @Version:1.1.0
*/
public enum Status {
	
	ALL(-2, "全部"),
	INVALID(-1, "无效"),
	VALID(1, "有效"),
	NEW(2, "待审核");

	private  int code;
	private  String lable;

	Status(int code, String lable) {
		this.code = code;
		this.lable = lable;
	}

	/** @return code */
	public int getCode() {
		return code;
	}

	/** @return lable */
	public String getLable() {
		return lable;
	}

}
