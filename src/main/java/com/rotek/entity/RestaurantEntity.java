/**
* @FileName: RestaurantEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-20 下午01:43:51
* @version V1.0
*/
package com.rotek.entity;

import java.io.Serializable;
import java.util.Date;

import com.cta.platform.constant.StrategyType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.ID;
import com.cta.platform.persistence.annotation.Length;
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.Table;

/**
 * @ClassName: RestaurantEntity
 * @Description: MF_RESTAURANT 对应实体类
 * @author chenwenpeng
 * @date 2013-5-20 下午01:43:51
 *
 */
@Table(name="MF_RESTAURANT")
public class RestaurantEntity implements Serializable{
	/** The long serialVersionUID*/
	private static final long serialVersionUID = 130643537371L;
	//
	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;
	//
	@Column(name="name")
	@Length(minLength=1,maxLength=100,message="店铺名称为1-100个字符")
	private String name;
	//
	@Column(name="alias")
	@Length(minLength=1,maxLength=100,message="店铺别名为1-100个字符")
	private String alias;
	//
	@Column(name="pic")
	@NotEmpty(message="店铺门头照不能为空")
	private String pic;
	//
	@Column(name="join_date")
	@NotEmpty()
	private Date join_date;
	//
	@Column(name="address")
	@Length(minLength=1,maxLength=100,message="店铺地址为1-100个字符")
	private String address;
	//
	@Column(name="region_id")
	@NotEmpty()
	private Integer region_id;
	//
	@Column(name="telephone")
	private String telephone;
	//
	@Column(name="notice")
	@NotEmpty()
	private String notice;
	//起送份数 如果按照起送分数来算(现在没用)
	@Column(name="deli_count")
	@NotEmpty()
	private Integer deli_count;
	//起送金额
	@Column(name="deli_price")
	@NotEmpty()
	private Double deli_price;
	//
	@Column(name="carriage")
	@NotEmpty()
	private Double carriage;
	// 配送方式 1酷客送 2店铺送
	@Column(name="deli_type")
	@NotEmpty()
	private Integer deli_type;

	//订单发送方式
	@Column(name="send_type")
	@NotEmpty()
	private Integer send_type;
	//
	@Column(name="sort")
	@NotEmpty()
	private Integer sort;
	//
	@Column(name="status")
	@NotEmpty()
	private Integer status;



	public Integer getSend_type() {
		return send_type;
	}
	public void setSend_type(Integer send_type) {
		this.send_type = send_type;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}

	public Date getJoin_date() {
		return join_date;
	}
	public void setJoin_date(Date join_date) {
		this.join_date = join_date;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Integer getRegion_id() {
		return region_id;
	}
	public void setRegion_id(Integer region_id) {
		this.region_id = region_id;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
	public Integer getDeli_count() {
		return deli_count;
	}
	public void setDeli_count(Integer deli_count) {
		this.deli_count = deli_count;
	}
	public Double getDeli_price() {
		return deli_price;
	}
	public void setDeli_price(Double deli_price) {
		this.deli_price = deli_price;
	}
	public Double getCarriage() {
		return carriage;
	}
	public void setCarriage(Double carriage) {
		this.carriage = carriage;
	}
	public Integer getDeli_type() {
		return deli_type;
	}
	public void setDeli_type(Integer deli_type) {
		this.deli_type = deli_type;
	}
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
}
