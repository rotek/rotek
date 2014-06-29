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
* @ClassName: ComponentGroupEntity
* @Description: 零件 组信息实体类
* @Author WangJuZhu
* @date 2014年6月29日 上午6:54:36
* @Version:1.1.0
*/
@Table(name = "r_component_group")
public class ComponentGroupEntity implements Serializable {

	/**  @Fields serialVersionUID   @Description:  */
	private static final long serialVersionUID = 2539437904714378111L;

	@Column(name="ID")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	
	@Column(name = "R_PROJECT_ID")
	private Integer r_project_id ;   	// 工程ID
	
	@Column(name = "GROUP_LB")
	private Integer group_lb ;   	// 组类别
	
	@Column(name = "GROUP_BH")
	private String group_bh ;   	// 组的组编号，如1号泵组的编号是  001
	
	@Column(name = "GROUP_MC")
	private String group_mc ;   	// 组的组名称，如 1号泵

	@Column(name = "PP")
	private String pp ;    	// 品牌
	
	@Column(name = "XH")
	private String xh ;    	// 型号
	
	@Column(name = "GL")
	private String gl ;    	// 功率
	
	@Column(name = "GG")
	private String gg ;    	// 规格
	
	@Column(name = "CLL")
	private String cll ;    	// 处理量
	
	@Column(name = "TLGD")
	private Integer tlgd ;    	// 填料高度
	
	@Column(name = "CZ")
	private String cz ;    	// 材质
	
	@Column(name = "SL")
	private Integer sl ;    	// 数量

	@Column(name = "CKDJ")
	private Double ckdj ;    	// 参考单价
	
	@Column(name = "GLJD")
	private Double gljd ;    	// 过滤精度
	
	@Column(name = "LXCC")
	private Double lxcc ;    	// 滤芯尺寸
	
	@Column(name = "LXJKXS")
	private String lxjkxs ;    	// 滤芯接口形式
	
	@Column(name = "CSL")
	private String csl ;    	// 产水量
	
	@Column(name = "PLFS")
	private String plfs ;    	// 排列方式
	
	@Column(name = "HSL")
	private Double hsl ;    	// 回收率
	
	@Column(name = "DGSM")
	private Integer dgsm ;    	// 灯管寿命(小时)
	
	@Column(name = "rj")
	private String rj ;    	// 容积
	
	@Column(name = "YJND")
	private String yjnd ;    	// 药剂浓度
	
	@Column(name = "YJEDTJl")
	private String yjedtjl ;    	// 药剂额定添加量
	
	@Column(name = "OTHERS")
	private String others ;    	// 其它
		
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

	/** @return r_project_id */
	public Integer getR_project_id() {
		return r_project_id;
	}

	/** @param r_project_id r_project_id to set */
	public void setR_project_id(Integer r_project_id) {
		this.r_project_id = r_project_id;
	}

	/** @return group_lb */
	public Integer getGroup_lb() {
		return group_lb;
	}

	/** @param group_lb group_lb to set */
	public void setGroup_lb(Integer group_lb) {
		this.group_lb = group_lb;
	}

	/** @return group_bh */
	public String getGroup_bh() {
		return group_bh;
	}

	/** @param group_bh group_bh to set */
	public void setGroup_bh(String group_bh) {
		this.group_bh = group_bh;
	}

	/** @return group_mc */
	public String getGroup_mc() {
		return group_mc;
	}

	/** @param group_mc group_mc to set */
	public void setGroup_mc(String group_mc) {
		this.group_mc = group_mc;
	}

	/** @return pp */
	public String getPp() {
		return pp;
	}

	/** @param pp pp to set */
	public void setPp(String pp) {
		this.pp = pp;
	}

	/** @return xh */
	public String getXh() {
		return xh;
	}

	/** @param xh xh to set */
	public void setXh(String xh) {
		this.xh = xh;
	}

	/** @return gl */
	public String getGl() {
		return gl;
	}

	/** @param gl gl to set */
	public void setGl(String gl) {
		this.gl = gl;
	}

	/** @return gg */
	public String getGg() {
		return gg;
	}

	/** @param gg gg to set */
	public void setGg(String gg) {
		this.gg = gg;
	}

	/** @return cll */
	public String getCll() {
		return cll;
	}

	/** @param cll cll to set */
	public void setCll(String cll) {
		this.cll = cll;
	}

	/** @return tlgd */
	public Integer getTlgd() {
		return tlgd;
	}

	/** @param tlgd tlgd to set */
	public void setTlgd(Integer tlgd) {
		this.tlgd = tlgd;
	}

	/** @return cz */
	public String getCz() {
		return cz;
	}

	/** @param cz cz to set */
	public void setCz(String cz) {
		this.cz = cz;
	}

	/** @return sl */
	public Integer getSl() {
		return sl;
	}

	/** @param sl sl to set */
	public void setSl(Integer sl) {
		this.sl = sl;
	}

	/** @return ckdj */
	public Double getCkdj() {
		return ckdj;
	}

	/** @param ckdj ckdj to set */
	public void setCkdj(Double ckdj) {
		this.ckdj = ckdj;
	}

	/** @return gljd */
	public Double getGljd() {
		return gljd;
	}

	/** @param gljd gljd to set */
	public void setGljd(Double gljd) {
		this.gljd = gljd;
	}

	/** @return lxcc */
	public Double getLxcc() {
		return lxcc;
	}

	/** @param lxcc lxcc to set */
	public void setLxcc(Double lxcc) {
		this.lxcc = lxcc;
	}

	/** @return lxjkxs */
	public String getLxjkxs() {
		return lxjkxs;
	}

	/** @param lxjkxs lxjkxs to set */
	public void setLxjkxs(String lxjkxs) {
		this.lxjkxs = lxjkxs;
	}

	/** @return csl */
	public String getCsl() {
		return csl;
	}

	/** @param csl csl to set */
	public void setCsl(String csl) {
		this.csl = csl;
	}

	/** @return plfs */
	public String getPlfs() {
		return plfs;
	}

	/** @param plfs plfs to set */
	public void setPlfs(String plfs) {
		this.plfs = plfs;
	}

	/** @return hsl */
	public Double getHsl() {
		return hsl;
	}

	/** @param hsl hsl to set */
	public void setHsl(Double hsl) {
		this.hsl = hsl;
	}

	/** @return dgsm */
	public Integer getDgsm() {
		return dgsm;
	}

	/** @param dgsm dgsm to set */
	public void setDgsm(Integer dgsm) {
		this.dgsm = dgsm;
	}

	/** @return rj */
	public String getRj() {
		return rj;
	}

	/** @param rj rj to set */
	public void setRj(String rj) {
		this.rj = rj;
	}

	/** @return yjnd */
	public String getYjnd() {
		return yjnd;
	}

	/** @param yjnd yjnd to set */
	public void setYjnd(String yjnd) {
		this.yjnd = yjnd;
	}

	/** @return yjedtjl */
	public String getYjedtjl() {
		return yjedtjl;
	}

	/** @param yjedtjl yjedtjl to set */
	public void setYjedtjl(String yjedtjl) {
		this.yjedtjl = yjedtjl;
	}

	/** @return others */
	public String getOthers() {
		return others;
	}

	/** @param others others to set */
	public void setOthers(String others) {
		this.others = others;
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
