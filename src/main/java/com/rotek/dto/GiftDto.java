/**
* @FileName: GiftDto.java
* @Package com.rotek.dto
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-12 上午09:42:58
* @version V1.0
*/
package com.rotek.dto;

import java.util.Date;

import com.rotek.entity.GiftEntity;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.NotEmpty;

/**
 * @ClassName: GiftDto
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-12 上午09:42:58
 *
 */
public class GiftDto extends GiftEntity{


	@Column(name="gift_name")
	@NotEmpty()
	private String gift_name;

	@Column(name="user_name")
	@NotEmpty()
	private String user_name;

	@Column(name="user_telephone")
	@NotEmpty()
	private String user_telephone;

	@Column(name="exchange_id")
	@NotEmpty()
	private Integer exchange_id;

	@Column(name="exchange_time")
	@NotEmpty()
	private Date exchange_time;

	@Column(name="exchange_status")
	@NotEmpty()
	private Integer exchange_status;



	public String getUser_telephone() {
		return user_telephone;
	}

	public void setUser_telephone(String user_telephone) {
		this.user_telephone = user_telephone;
	}

	public String getGift_name() {
		return gift_name;
	}

	public void setGift_name(String gift_name) {
		this.gift_name = gift_name;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public Integer getExchange_id() {
		return exchange_id;
	}

	public void setExchange_id(Integer exchange_id) {
		this.exchange_id = exchange_id;
	}

	public Date getExchange_time() {
		return exchange_time;
	}

	public void setExchange_time(Date exchange_time) {
		this.exchange_time = exchange_time;
	}

	public Integer getExchange_status() {
		return exchange_status;
	}

	public void setExchange_status(Integer exchange_status) {
		this.exchange_status = exchange_status;
	}
}
