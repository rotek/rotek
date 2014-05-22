/**
* @FileName: RestMenuEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-19 下午04:37:52
* @version V1.0
*/
package com.rotek.entity;

import java.io.Serializable;

import com.rotek.platform.constant.StrategyType;
import com.rotek.platform.persistence.annotation.Column;
import com.rotek.platform.persistence.annotation.ID;
import com.rotek.platform.persistence.annotation.Length;
import com.rotek.platform.persistence.annotation.NotEmpty;
import com.rotek.platform.persistence.annotation.Size;
import com.rotek.platform.persistence.annotation.Table;

/**
 * @ClassName: RestMenuEntity
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-19 下午04:37:52
 *
 */
@Table(name="MF_MENU_QD")
public class RestMenuEntity implements Serializable{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = -4387207681192727899L;

	@ID(strategy=StrategyType.IDENTITY)
	@Column(name="id")
	private Integer id;//
	@Column(name="name")
	@Length(minLength=1,maxLength=50,message="菜品名称在(1-50)字符之间")
	private String name;//
	@Column(name="price")
	@Size(minDoubleSize=0,maxDoubleSize=100000,message="菜品的价格在(0-100000)之间")
	private Double price;//
	@Column(name="pic")
	@NotEmpty()
	private String pic;//
	@Column(name="mix")
	@NotEmpty()
	private String mix;//
	@Column(name="recommend")
	@NotEmpty()
	private Integer recommend;//
	@Column(name="descr")
	private String descr;//
	@Column(name="cate_id")
	@NotEmpty()
	private Integer cate_id;//
	@Column(name="rest_id")
	@NotEmpty()
	private Integer rest_id;//
	@Column(name="status")
	@NotEmpty()
	private Integer status;//


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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getMix() {
		return mix;
	}
	public void setMix(String mix) {
		this.mix = mix;
	}
	public Integer getRecommend() {
		return recommend;
	}
	public void setRecommend(Integer recommend) {
		this.recommend = recommend;
	}

	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getCate_id() {
		return cate_id;
	}
	public void setCate_id(Integer cate_id) {
		this.cate_id = cate_id;
	}
	public Integer getRest_id() {
		return rest_id;
	}
	public void setRest_id(Integer rest_id) {
		this.rest_id = rest_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}

}
