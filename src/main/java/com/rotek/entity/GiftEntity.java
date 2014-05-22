/**
* @FileName: GiftEntity.java
* @Package com.rotek.entity
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-12 上午09:39:58
* @version V1.0
*/
package com.rotek.entity;

import java.io.Serializable;

import com.rotek.platform.constant.StrategyType;
import com.rotek.platform.persistence.annotation.Column;
import com.rotek.platform.persistence.annotation.ID;
import com.rotek.platform.persistence.annotation.Length;
import com.rotek.platform.persistence.annotation.NotEmpty;
import com.rotek.platform.persistence.annotation.Table;

/**
 * @ClassName: GiftEntity
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-12 上午09:39:58
 *
 */
@Table(name="MF_GIFT")
public class GiftEntity implements Serializable{

	/**@Field the long serialVersionUID*/
	private static final long serialVersionUID = -5004591302688204441L;


	@Column(name="id")
	@ID(strategy=StrategyType.IDENTITY)
	private Integer id;
	@Column(name="name")
	@Length(minLength=1,maxLength=50,message="礼品名称为1-50字符之间")
	private String name;
	@Column(name="pic")
	@NotEmpty()
	private String pic;
	@Column(name="descr")
	@Length(minLength=1,maxLength=250,message="礼品描述请在1-250字符之间")
	private String descr;
	@Column(name="points")
	@NotEmpty()
	private Integer points;
	@Column(name="status")
	@NotEmpty()
	private Integer status ;



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
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
