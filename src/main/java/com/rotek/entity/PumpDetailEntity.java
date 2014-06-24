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
* @ClassName:PumpDetailEntity
* @Description: 泵  详细信息（参数的额定值）实体类
* @Author WangJuZhu
* @date 2014年6月25日 上午6:02:02
* @Version:1.1.0
*/
@Table(name = "r_pump_detail")
public class PumpDetailEntity implements Serializable {

	/** 
	 * @Fields serialVersionUID 
	 * @Description: 
	 */
	private static final long serialVersionUID = 4628046027257788856L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PUMP_GROUP_ID")
	private Integer r_pump_group_id ;   	// 泵组ID
	
	@Column(name = "SPECIFIC_PART")
	private String specific_part ;   	// 具体的零件名称，比如1号泵前，1号泵后
	
	@Column(name = "EDYCLL")
	private Double edycll ;   	// 额定扬程流量

	@Column(name = "EDWHBYSJ")
	private Double edwhbysj ;    	// 额定维护保养时间
	
	@Column(name = "EDDDL")
	private Double edddl ;    	// 额定电导率
	
	@Column(name = "EDPH")
	private Double edph ;    	// 额定PH值
	
	@Column(name = "EDYLV")
	private Double edylv ;    	// 额定余氯
	
	@Column(name = "EDWD")
	private Double edwd ;    	// 额定温度
	
	@Column(name = "EDYD")
	private Double edyd ;    	// 额定硬度
	
	@Column(name = "EDTDS")
	private Double edtds ;    	// 额定TDS值
	
	@Column(name = "EDZDU")
	private Double edzdu ;    	// 额定浊度
	
	@Column(name = "EDYL")
	private Double edyl ;    	// 额定压力
	
	@Column(name = "EDSDI")
	private Double edsdi ;    	// 额定SDI值
	
	@Column(name = "EDCOD")
	private Double edcod ;    	// 额定COD值
	
	@Column(name = "EDBOD")
	private Double edbod ;    	// 额定BOD值
	
	@Column(name = "EDAD")
	private Double edad ;    	// 额定氨氮
	
	@Column(name = "EDZD")
	private Double edzd ;    	// 额定总氮
	
	@Column(name = "EDZL")
	private Double edzl ;    	// 额定总磷
	
	@Column(name = "EDXFW")
	private Double edxfw ;    	// 额定悬浮物
	
	@Column(name = "EDYWJ")
	private Double edywj ;    	// 额定液位计
	
	@Column(name = "EDWNND")
	private Double edwnnd ;    	// 额定污泥浓度
	
	@Column(name = "OTHER_INFO")
	private String other_info ;    	// 其他信息
	
	@Column(name = "STATUS")
	private Integer status ; 		// 状态

	/** @return id */
	public Integer getId() {
		return id;
	}

	/** @param id id to set */
	public void setId(Integer id) {
		this.id = id;
	}

	/** @return r_pump_group_id */
	public Integer getR_pump_group_id() {
		return r_pump_group_id;
	}

	/** @param r_pump_group_id r_pump_group_id to set */
	public void setR_pump_group_id(Integer r_pump_group_id) {
		this.r_pump_group_id = r_pump_group_id;
	}

	/** @return specific_part */
	public String getSpecific_part() {
		return specific_part;
	}

	/** @param specific_part specific_part to set */
	public void setSpecific_part(String specific_part) {
		this.specific_part = specific_part;
	}

	/** @return edycll */
	public Double getEdycll() {
		return edycll;
	}

	/** @param edycll edycll to set */
	public void setEdycll(Double edycll) {
		this.edycll = edycll;
	}

	/** @return edwhbysj */
	public Double getEdwhbysj() {
		return edwhbysj;
	}

	/** @param edwhbysj edwhbysj to set */
	public void setEdwhbysj(Double edwhbysj) {
		this.edwhbysj = edwhbysj;
	}

	/** @return edddl */
	public Double getEdddl() {
		return edddl;
	}

	/** @param edddl edddl to set */
	public void setEdddl(Double edddl) {
		this.edddl = edddl;
	}

	/** @return edph */
	public Double getEdph() {
		return edph;
	}

	/** @param edph edph to set */
	public void setEdph(Double edph) {
		this.edph = edph;
	}

	/** @return edylv */
	public Double getEdylv() {
		return edylv;
	}

	/** @param edylv edylv to set */
	public void setEdylv(Double edylv) {
		this.edylv = edylv;
	}

	/** @return edwd */
	public Double getEdwd() {
		return edwd;
	}

	/** @param edwd edwd to set */
	public void setEdwd(Double edwd) {
		this.edwd = edwd;
	}

	/** @return edyd */
	public Double getEdyd() {
		return edyd;
	}

	/** @param edyd edyd to set */
	public void setEdyd(Double edyd) {
		this.edyd = edyd;
	}

	/** @return edtds */
	public Double getEdtds() {
		return edtds;
	}

	/** @param edtds edtds to set */
	public void setEdtds(Double edtds) {
		this.edtds = edtds;
	}

	/** @return edzdu */
	public Double getEdzdu() {
		return edzdu;
	}

	/** @param edzdu edzdu to set */
	public void setEdzdu(Double edzdu) {
		this.edzdu = edzdu;
	}

	/** @return edyl */
	public Double getEdyl() {
		return edyl;
	}

	/** @param edyl edyl to set */
	public void setEdyl(Double edyl) {
		this.edyl = edyl;
	}

	/** @return edsdi */
	public Double getEdsdi() {
		return edsdi;
	}

	/** @param edsdi edsdi to set */
	public void setEdsdi(Double edsdi) {
		this.edsdi = edsdi;
	}

	/** @return edcod */
	public Double getEdcod() {
		return edcod;
	}

	/** @param edcod edcod to set */
	public void setEdcod(Double edcod) {
		this.edcod = edcod;
	}

	/** @return edbod */
	public Double getEdbod() {
		return edbod;
	}

	/** @param edbod edbod to set */
	public void setEdbod(Double edbod) {
		this.edbod = edbod;
	}

	/** @return edad */
	public Double getEdad() {
		return edad;
	}

	/** @param edad edad to set */
	public void setEdad(Double edad) {
		this.edad = edad;
	}

	/** @return edzd */
	public Double getEdzd() {
		return edzd;
	}

	/** @param edzd edzd to set */
	public void setEdzd(Double edzd) {
		this.edzd = edzd;
	}

	/** @return edzl */
	public Double getEdzl() {
		return edzl;
	}

	/** @param edzl edzl to set */
	public void setEdzl(Double edzl) {
		this.edzl = edzl;
	}

	/** @return edxfw */
	public Double getEdxfw() {
		return edxfw;
	}

	/** @param edxfw edxfw to set */
	public void setEdxfw(Double edxfw) {
		this.edxfw = edxfw;
	}

	/** @return edywj */
	public Double getEdywj() {
		return edywj;
	}

	/** @param edywj edywj to set */
	public void setEdywj(Double edywj) {
		this.edywj = edywj;
	}

	/** @return edwnnd */
	public Double getEdwnnd() {
		return edwnnd;
	}

	/** @param edwnnd edwnnd to set */
	public void setEdwnnd(Double edwnnd) {
		this.edwnnd = edwnnd;
	}

	/** @return other_info */
	public String getOther_info() {
		return other_info;
	}

	/** @param other_info other_info to set */
	public void setOther_info(String other_info) {
		this.other_info = other_info;
	}

	/** @return status */
	public Integer getStatus() {
		return status;
	}

	/** @param status status to set */
	public void setStatus(Integer status) {
		this.status = status;
	}

}
