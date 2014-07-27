package com.rotek.constant;

/**
 * @author chenwenpeng
 *
 */
public enum MonitorParams {
	
	EDDL("EDDL","额定电流"),
	EDLL("EDLL","额定流量"),
	EDGHSJ("EDGHSJ","额定维护保养时间"),
	EDDDL("EDDDL","额定电导率"),
	EDPH("EDPH","额定PH值"),
	EDYLV("EDYLV","额定余氯"), 
	EDWD("EDWD","额定温度"),
	EDYD("EDYD","额定硬度"),
	EDTDS("EDTDS","额定TDS值"), 
	EDZDU("EDZDU","额定浊度"), 
	EDSZYL("EDSZYL","额定水质压力"),
	EDYL("EDYL","额定压力"),
	EDSDI("EDSDI","额定SDI值"), 
	EDCOD("EDCOD","额定COD值"), 
	EDBOD("EDBOD","额定BOD值"), 
	EDAD("EDAD","额定氨氮"),
	EDZD("EDZD","额定总氮"),
	EDZL("EDZL","额定总磷"),
	EDXFW("EDXFW","额定悬浮物"), 
	EDYWJ("EDYWJ","额定液位计"), 
	EDWNND("EDWNND","额定污泥浓度"),
	EDHSL("EDHSL","额定回收率"), 
	EDGHYC("EDGHYC","额定更换压差"),
	EDQXYC("EDQXYC","额定清洗压差"),
	EDQXSJ("EDQXSJ","额定清洗时间"),
	EDSZ("EDSZ","额定水质"),
	EDYC("EDYC","额定压差"),
	OTHER_INFO("OTHER_INFO","其他信息");
	
	
	private  String code;
	private  String lable;

	MonitorParams(String code, String lable) {
		this.code = code;
		this.lable = lable;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}

}
