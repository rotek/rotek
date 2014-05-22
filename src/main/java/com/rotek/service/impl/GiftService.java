/**
* @FileName: GiftService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-9 下午05:26:46
* @version V1.0
*/
package com.rotek.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rotek.constant.DataStatus;
import com.rotek.constant.OrderStatus;
import com.rotek.dao.impl.GiftDao;
import com.rotek.dto.GiftDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.GiftEntity;
import com.rotek.platform.config.SystemGlobals;
import com.rotek.platform.util.ListPager;
import com.rotek.platform.util.ValidateUtil;
import com.rotek.util.FileUtils;

/**
 * @ClassName: GiftService
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-9 下午05:26:46
 *
 */
@Service
public class GiftService {

	@Autowired
	private GiftDao giftDao;

	/**
	 * @param user
	 * @throws SQLException
	* @Title: listUserGifts
	* @Description:
	* @param gift
	* @param pager
	* @param start_time
	* @param end_time
	* @return
	* @return List<RegistUserEntity>
	* @throws
	*/
	public List<GiftDto> listUserGifts(UserDto user, GiftDto gift, ListPager pager,
			Date start_time, Date end_time) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select gu.id exchange_id, gu.time_exchange exchange_time, gu.status exchange_status, u.nick_name user_name,g.name gift_name from mf_gift_user gu");
		sql.append(" left join mf_user u on u.id = gu.user_id");
		sql.append(" left join mf_gift g on gu.gift_id = g.id");
		sql.append(" where 1=1");

		if(SystemGlobals.getIntPreference("super_dep_id", 0) != user.getDep_id()){
			sql.append(" and gu.status <> ?");
			params.add(OrderStatus.INVALID);
		}

		if(null != gift.getExchange_id()){
			sql.append(" and gu.id = ?");
			params.add(gift.getExchange_id());
		}

		if(null != gift.getExchange_status()){
			sql.append(" and gu.status = ?");
			params.add(gift.getExchange_status());
		}
		if(StringUtils.isNotEmpty(gift.getUser_name())){
			sql.append(" and u.nick_name like '%").append(gift.getUser_name()).append("%'");
		}
		if(StringUtils.isNotEmpty(gift.getGift_name())){

			sql.append(" and g.name like '%").append(gift.getGift_name()).append("%'");
		}

		if(null != start_time){
			sql.append(" and gu.time_exchange >= ?");
			params.add(start_time);
		}
		if(null != end_time){
			sql.append(" and gu.time_exchange <= ?");
			params.add(end_time);
		}

		sql.append(" order by gu.id desc");
		return giftDao.listUserGifts(sql.toString(),params.toArray(),pager);
	}

	/**
	 * @throws SQLException
	* @Title: deleteUserGift
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteUserGift(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_gift_user set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		giftDao.modifyUserGiftStatus(sql.toString());
		return messages;
	}

	/**
	 * @param exchange_status
	 * @throws SQLException
	* @Title: modifyUserGiftStatus
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyUserGiftStatus(String ids, Integer exchange_status) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids) || null == exchange_status){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_gift_user set status = ").append(exchange_status);
		sql.append(" where id in ("+ids.trim()+")");
		giftDao.modifyUserGiftStatus(sql.toString());
		return messages;
	}

	/**
	 * @throws SQLException
	* @Title: getUserGiftDetail
	* @Description:
	* @param id
	* @return
	* @return GiftDto
	* @throws
	*/
	public GiftDto getUserGiftDetail(Integer id) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select gu.id exchange_id, gu.time_exchange exchange_time, gu.status exchange_status, u.nick_name user_name,u.telephone user_telephone,g.name gift_name,g.points,g.descr from mf_gift_user gu");
		sql.append(" left join mf_user u on u.id = gu.user_id");
		sql.append(" left join mf_gift g on gu.gift_id = g.id");
		sql.append(" where gu.id = ").append(id);

		return giftDao.getUserGiftDetail(sql.toString());
	}

	/**
	 * @throws SQLException
	 * @param pager
	* @Title: listUserGifts
	* @Description:
	* @param user
	* @param gift
	* @return
	* @return List<GiftDto>
	* @throws
	*/
	public List<GiftDto> listUserGifts(UserDto user, GiftEntity gift, ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select id, name, pic, points, status from mf_gift where 1 = 1");

		if(SystemGlobals.getIntPreference("super_dep_id", 0) != user.getDep_id()){
			sql.append(" and status <> ?");
			params.add(OrderStatus.INVALID);
		}

		if(null != gift.getId()){
			sql.append(" and id = ?");
			params.add(gift.getId());
		}

		if(StringUtils.isNotEmpty(gift.getName())){
			sql.append(" and name like '%").append(gift.getName()).append("%'");
		}

		if(null != gift.getPoints()){
			sql.append(" and points = ?");
			params.add(gift.getPoints());
		}

		if(null != gift.getStatus()){
			sql.append(" and status = ?");
			params.add(gift.getStatus());
		}

		sql.append(" order by id desc");
		return giftDao.listUserGifts(sql.toString(), params.toArray(), pager);
	}

	/**
	 * @throws SQLException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @param request
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addGift
	* @Description:
	* @param gift
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> addGift(GiftEntity gift, MultipartHttpServletRequest multipartRequest) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException, SQLException {
		List<String> messages = ValidateUtil.validate(gift);
		if(messages.size() > 0){
			return messages;
		}

		MultipartFile pic = multipartRequest.getFile("pic");
		if(null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("gift.pic.path");
			long rest_maxPic = SystemGlobals.getLongPreference("gift.pic.maxSize", 102400);
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, rest_maxPic);
			if(null != pic_name){
				//更新礼品图片
				gift.setPic(pic_name);
				giftDao.addGift(gift);
			}else {
				messages.add("店铺图片必须在 "+rest_maxPic/1024 +"k 以内!");
				return messages;
			}
		}
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: getGiftDetail
	* @Description:
	* @param id
	* @return
	* @return GiftEntity
	* @throws
	*/
	public GiftEntity getGiftDetail(Integer id) throws SQLException {
		if(null == id){
			return null;
		}

		return giftDao.getGiftDetail(id);
	}

	/**
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	* @Title: modfiyGift
	* @Description:
	* @param gift
	* @param multipartRequest
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyGift(GiftEntity gift,
			MultipartHttpServletRequest multipartRequest) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IllegalStateException, IOException {

		List<String> messages = ValidateUtil.validate(gift);
		if(messages.size() > 0 || null == gift.getId()){
			messages.add(null == gift.getId() ? "由于，要修改的礼品ID为空，修改失败!" : "");
			return messages;
		}

		MultipartFile pic = multipartRequest.getFile("pic_modify");
		if(null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("gift.pic.path");
			long rest_maxPic = SystemGlobals.getLongPreference("gift.pic.maxSize", 102400);
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, rest_maxPic);
			if(null != pic_name){
				//删除原图片
				FileUtils.clearPic(pic_location, gift.getPic());
				//更新礼品图片
				gift.setPic(pic_name);
			}else {
				messages.add("店铺图片必须在 "+rest_maxPic/1024 +"k 以内!");
				return messages;
			}
		}
		//修改
		giftDao.modifyGift(gift);
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: deleteGift
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteGift(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_gift set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		giftDao.deleteGift(sql.toString());
		return messages;
	}
}
