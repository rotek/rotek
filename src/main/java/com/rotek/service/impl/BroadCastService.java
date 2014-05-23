/**
* @FileName: BroadCastService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-6 上午10:33:09
* @version V1.0
*/
package com.rotek.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rotek.constant.DataStatus;
import com.rotek.constant.OrderStatus;
import com.rotek.dao.impl.BroadCastDao;
import com.rotek.dto.BroadCastDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.BroadCastEntity;
import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.util.FileUtils;

/**
 * @ClassName: BroadCastService
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-6 上午10:33:09
 *
 */
@Service
public class BroadCastService {

	@Autowired
	private BroadCastDao broadcastDao;
	/**
	 * @throws SQLException
	* @Title: listBroadcasts
	* @Description:
	* @param broadCast
	* @param pager
	* @return
	* @return List<BroadCastDto>
	* @throws
	*/
	public List<BroadCastDto> listBroadcasts(UserDto user,BroadCastEntity broadCast,
			ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select 	br.id, br.name, br.alt, br.target, br.buildingId, br.status,b.name building_name");
		sql.append(" from mf_broadcast br left join mf_building b on br.buildingId = b.id");
		sql.append(" where br.buildingId in (select b_id from mf_building_dep where dep_id in(select id from mf_department where super_dep_id = ? or id = ?))");
		params.add(user.getDep_id());
		params.add(user.getDep_id());

		if(SystemGlobals.getIntPreference("super_dep_id", 0) != user.getDep_id()){
			sql.append(" and br.status <> ?");
			params.add(OrderStatus.INVALID);
		}
		//轮播的ID
		if(null != broadCast.getId()){
			sql.append(" and br.id = ?");
			params.add(broadCast.getId());
		}
		if(null != broadCast.getBuildingId()){
			sql.append(" and br.buildingId = ?");
			params.add(broadCast.getBuildingId());
		}
		if(StringUtils.isNotEmpty(broadCast.getAlt())){
			sql.append(" and br.alt like '%").append(broadCast.getAlt()).append("%'");
		}
		if(StringUtils.isNotEmpty(broadCast.getTarget())){
			sql.append(" and br.target like '%").append(broadCast.getTarget()).append("%'");
		}
		if(null != broadCast.getStatus()){
			sql.append(" and br.status = ?");
			params.add(broadCast.getStatus());
		}

		sql.append(" order by br.id desc");
		return broadcastDao.listBroadcasts(sql.toString(),params.toArray(),pager);
	}
	/**
	 * @throws IOException
	 * @throws IllegalStateException
	 * @param multipartRequest
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @param building_ids
	* @Title: addBroadCast
	* @Description:
	* @param broadCast
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> addBroadCast(BroadCastEntity broadCast, String building_ids, MultipartHttpServletRequest multipartRequest) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException, IllegalStateException, IOException {
		List<String> messages =new LinkedList<String>();
		if(StringUtils.isBlank(building_ids)){
			messages.add("添加失败，如果错误继续出现，请联系工程师解决！");
			return messages;
		}

		MultipartFile pic = multipartRequest.getFile("name");
		if(null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("broadcast.pic.path");
			long broad_maxSize = SystemGlobals.getLongPreference("broadcast.pic.maxSize", 102400);
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, broad_maxSize);
			if(null != pic_name){

				broadCast.setName(pic_name);
			}else {
				messages.add("店铺图片必须在 "+broad_maxSize/1024 +"k 以内!");
				return messages;
			}
		}

		for(String id_str : building_ids.split(",")){
			broadCast.setBuildingId(Integer.parseInt(id_str));
			messages = ValidateUtil.validate(broadCast);
			if(messages.size()>0){
				return messages;
			}
			broadcastDao.addBroadCast(broadCast);
		}

		return null;
	}
	/**
	 * @throws SQLException
	* @Title: getBroadcastDetail
	* @Description:
	* @param id
	* @return
	* @return BroadCastEntity
	* @throws
	*/
	public BroadCastEntity getBroadcastDetail(Integer id) throws SQLException {
		if(null == id){
			return null;
		}

		return broadcastDao.getBroadcastDetail(id);
	}
	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws SQLException
	* @Title: modifyBroadcast
	* @Description:
	* @param broadCast
	* @param multipartRequest
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyBroadcast(BroadCastEntity broadCast,
			MultipartHttpServletRequest multipartRequest) throws SQLException, IllegalStateException, IOException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<String> messages = ValidateUtil.validate(broadCast);

		if(messages.size()>00 || null == broadCast.getId()){
			messages.add(null == broadCast.getId() ? "修改数据失败，如果继续出现请联系工程师解决！" : "");
			return messages;
		}

		MultipartFile pic = multipartRequest.getFile("name_");
		if(null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("broadcast.pic.path");
			long broad_maxSize = SystemGlobals.getLongPreference("broadcast.pic.maxSize", 102400);
			//清空原图片
			FileUtils.clearPic(pic_location, broadCast.getName());
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, broad_maxSize);
			if(null != pic_name){
				broadCast.setName(pic_name);
			}else {
				messages.add("店铺图片必须在 "+broad_maxSize/1024 +"k 以内!");
				return messages;
			}
		}

		broadcastDao.modifyBroadcast(broadCast);
		return null;
	}
	/**
	 * @throws SQLException
	* @Title: deleteBroadcast
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteBroadcast(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_broadcast set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		broadcastDao.deleteBroadcast(sql.toString());
		return messages;
	}
}
