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
* @ClassName: ComponentDetailEntity
* @Description: 组  详细信息（参数的额定值）实体类
* @Author WangJuZhu
* @date 2014年6月30日 上午6:17:46
* @Version:1.1.0
*/
@Table(name = "r_component_detail")
public class ComponentDetailEntity extends BaseEntity{

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = 2843425794297136575L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 工程ID
	
	@Column(name = "R_COMPONENT_GROUP_ID")
	private Integer r_component_group_id ;   	// 组ID
	
	@Column(name = "R_COMPONENT_GROUP_TYPE")
	private Integer r_component_group_type ;   	// 零件类别（属于哪个组）
	
	@Column(name = "SPECIFIC_PART")
	private String specific_part ;   	// 具体的零件名称，比如1号泵前，1号泵后
	
	@Column(name = "SPECIFIC_BH")
	private String specific_bh ;   	// 具体的零件编号，比如1号泵前的编号   000101，1号泵后  000102
	
	@Column(name = "EDLL")
	private Double edll ;   	// 额定流量(额定扬程流量)

	@Column(name = "EDDL")
	private Double eddl ;   	// 额定电流
	
	@Column(name = "EDGHSJ")
	private Date edghsj ;    	// 额定维护保养时间(额定更换时间)
	
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
	
	@Column(name = "EDSZYL")
	private Double edszyl ;    	// 额定水质压力
	
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
	
	@Column(name = "EDYC")
	private Double edyc ;    	// 额定压差
	
	@Column(name = "EDSZ")
	private Double edsz ;    	// 额定水质
	
	@Column(name = "EDQXSJ")
	private Date edqxsj ;    	// 额定清洗时间
	
	@Column(name = "EDQXYC")
	private Double edqxyc ;    	// 额定清洗压差
	
	@Column(name = "EDGHYC")
	private Double edghyc ;    	// 额定更换压差
	
	@Column(name = "EDHSL")
	private Double edhsl ;    	// 额定回收率
		
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

	/** @return r_component_group_id */
	public Integer getR_component_group_id() {
		return r_component_group_id;
	}

	/** @param r_component_group_id r_component_group_id to set */
	public void setR_component_group_id(Integer r_component_group_id) {
		this.r_component_group_id = r_component_group_id;
	}

	/** @return r_component_group_type */
	public Integer getR_component_group_type() {
		return r_component_group_type;
	}

	/** @param r_component_group_type r_component_group_type to set */
	public void setR_component_group_type(Integer r_component_group_type) {
		this.r_component_group_type = r_component_group_type;
	}

	/** @return r_project_id */
	public Integer getR_project_id() {
		return r_project_id;
	}

	/** @param r_project_id r_project_id to set */
	public void setR_project_id(Integer r_project_id) {
		this.r_project_id = r_project_id;
	}

	/** @return specific_part */
	public String getSpecific_part() {
		return specific_part;
	}

	/** @param specific_part specific_part to set */
	public void setSpecific_part(String specific_part) {
		this.specific_part = specific_part;
	}

	/** @return specific_bh */
	public String getSpecific_bh() {
		return specific_bh;
	}

	/** @param specific_bh specific_bh to set */
	public void setSpecific_bh(String specific_bh) {
		this.specific_bh = specific_bh;
	}

	/** @return edll */
	public Double getEdll() {
		return edll;
	}

	/** @param edll edll to set */
	public void setEdll(Double edll) {
		this.edll = edll;
	}

	/** @return edghsj */
	public Date getEdghsj() {
		return edghsj;
	}

	/** @param edghsj edghsj to set */
	public void setEdghsj(Date edghsj) {
		this.edghsj = edghsj;
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

	/** @return eddl */
	public Double getEddl() {
		return eddl;
	}

	/** @param eddl eddl to set */
	public void setEddl(Double eddl) {
		this.eddl = eddl;
	}

	/** @return edyl */
	public Double getEdyl() {
		return edyl;
	}

	/** @param edyl edyl to set */
	public void setEdyl(Double edyl) {
		this.edyl = edyl;
	}

	/** @return edszyl */
	public Double getEdszyl() {
		return edszyl;
	}

	/** @param edszyl edszyl to set */
	public void setEdszyl(Double edszyl) {
		this.edszyl = edszyl;
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

	/** @return edyc */
	public Double getEdyc() {
		return edyc;
	}

	/** @param edyc edyc to set */
	public void setEdyc(Double edyc) {
		this.edyc = edyc;
	}

	/** @return edsz */
	public Double getEdsz() {
		return edsz;
	}

	/** @param edsz edsz to set */
	public void setEdsz(Double edsz) {
		this.edsz = edsz;
	}

	/** @return edqxsj */
	public Date getEdqxsj() {
		return edqxsj;
	}

	/** @param edqxsj edqxsj to set */
	public void setEdqxsj(Date edqxsj) {
		this.edqxsj = edqxsj;
	}

	/** @return edqxyc */
	public Double getEdqxyc() {
		return edqxyc;
	}

	/** @param edqxyc edqxyc to set */
	public void setEdqxyc(Double edqxyc) {
		this.edqxyc = edqxyc;
	}

	/** @return edghyc */
	public Double getEdghyc() {
		return edghyc;
	}

	/** @param edghyc edghyc to set */
	public void setEdghyc(Double edghyc) {
		this.edghyc = edghyc;
	}

	/** @return edhsl */
	public Double getEdhsl() {
		return edhsl;
	}

	/** @param edhsl edhsl to set */
	public void setEdhsl(Double edhsl) {
		this.edhsl = edhsl;
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
