package com.rotek.constant;

/**
* @ClassName:MonitorType
* @Description: 监测类型
* @Author WangJuZhu
* @date 2014年7月4日 上午9:28:07
* @Version:1.1.0
*/
public enum MonitorType {
	
	PTSZ(1, "普通工程-水质监测"),
	PTLL(2, "普通工程-流量监测"),
	PTYL(3, "普通工程-压力监测"),
	EMCSZ(4, "EMC工程-水质监测"),
	EMCLL(5, "EMC工程-流量监测"),
	EMCYL(6, "EMC工程-压力监测");

	private  int code;
	private  String lable;

	MonitorType(int code, String lable) {
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
