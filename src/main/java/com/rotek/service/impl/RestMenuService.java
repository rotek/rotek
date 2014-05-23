/**
* @FileName: RestMenuService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-19 下午04:52:13
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
import com.rotek.dao.impl.RestMenuDao;
import com.rotek.dto.RestMenuDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.RestMenuEntity;
import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.util.FileUtils;

/**
 * @ClassName: RestMenuService
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-19 下午04:52:13
 *
 */
@Service
public class RestMenuService {

	@Autowired
	private RestMenuDao restMenuDao;
	/**@Field the int MENU_GENERAL 普通菜品*/
	public static final int MENU_GENERAL = 2;
	/**@Field the int MENU_RECOMMENDED 推荐菜品*/
	public static final int MENU_RECOMMENDED = 1;
	/**
	 * @param user
	 * @throws SQLException
	* @Title: listMenus
	* @Description:
	* @param menu
	* @param pager
	* @return
	* @return Object
	* @throws
	*/
	public List<RestMenuDto> listMenus(RestMenuEntity menu, ListPager pager, UserDto user) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select m.id, m.name, m.price, m.pic, m.recommend,m.status,r.name rest_name from mf_menu_qd m left join mf_restaurant r on m.rest_id = r.id");
		sql.append(" where m.rest_id in (select distinct rest_id from mf_dep_restaurant where dep_id in (select id from mf_department where super_dep_id = ? or id = ?))");
		params.add(user.getDep_id());
		params.add(user.getDep_id());

		if(null != menu.getId()){
			sql.append(" and m.id = ?");
			params.add(menu.getId());
		}
		if(StringUtils.isNotBlank(menu.getName())){
			sql.append(" and m.name like '%").append(menu.getName()).append("%'");
		}
		if(null != menu.getCate_id()){
			sql.append(" and m.cate_id = ?");
			params.add(menu.getCate_id());
		}
		if(null != menu.getRest_id()){
			sql.append(" and m.rest_id = ?");
			params.add(menu.getRest_id());
		}
		if(null != menu.getRecommend()){
			sql.append(" and m.recommend = ?");
			params.add(menu.getRecommend());
		}
		if(null != menu.getStatus()){
			sql.append(" and m.status = ?");
			params.add(menu.getStatus());
		}

		sql.append(" order by m.id desc");

		return restMenuDao.listMenus(sql.toString(),params.toArray(),pager);
	}
	/**
	 * @throws IOException
	 * @throws IllegalStateException
	 * @param multipartRequest
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addMenu
	* @Description:
	* @param menu
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> addMenu(RestMenuEntity menu, MultipartHttpServletRequest multipartRequest) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException, IllegalStateException, IOException {

		List<String> messages = ValidateUtil.validate(menu);
		if(messages.size() > 0){
			return messages;
		}

		Integer menu_id = restMenuDao.addMenu(menu);
		MultipartFile pic = multipartRequest.getFile("menu_pic");
		if(MENU_RECOMMENDED == menu.getRecommend() && null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("restaurant.pic.path")+"/"+menu.getRest_id();
			long rest_maxPic = SystemGlobals.getLongPreference("restaurant.pic.maxSize", 102400);
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, rest_maxPic);
			if(null != pic_name){
				//更新菜单的图片
				restMenuDao.updateMenuPic(pic_name,menu_id);
			}else {
				messages.add("店铺图片必须在 "+rest_maxPic/1024 +"k 以内!");
				return messages;
			}
		}
		return null;
	}
	/**
	 * @throws SQLException
	* @Title: getMenuDetail
	* @Description:
	* @param id
	* @return
	* @return MenuDto
	* @throws
	*/
	public RestMenuEntity getMenuDetail(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return restMenuDao.getMenuDetail(id);
	}

	/**
	 * @throws SQLException
	 * @Title: getMenuDetail
	 * @Description:获取菜单的简单信息
	 * @param id
	 * @return
	 * @return MenuDto
	 * @throws
	 */
	public RestMenuEntity getMenuDetail_s(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return restMenuDao.getMenuDetail_s(id);
	}
	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @param multipartRequest
	* @Title: modifyMenu
	* @Description:
	* @param menu
	* @param buttons
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyMenu(RestMenuEntity menu, MultipartHttpServletRequest multipartRequest) throws IllegalStateException, IOException, SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		List<String> messages = ValidateUtil.validate(menu);
		if(messages.size()>0 || null == menu.getId()){
			return messages;
		}
		restMenuDao.modifyMenu(menu);

		MultipartFile pic = multipartRequest.getFile("menu_pic");
		if(MENU_RECOMMENDED == menu.getRecommend()&& null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("restaurant.pic.path")+"/"+menu.getRest_id();
			long rest_maxPic = SystemGlobals.getLongPreference("restaurant.pic.maxSize", 102400);
			//删除菜品已经存在的图片
			FileUtils.clearPic(pic_location, menu.getPic());
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, rest_maxPic);
			if(null != pic_name){
				//更新菜品的数据库中图片
				restMenuDao.updateMenuPic(pic_name,menu.getId());
			}else {
				messages.add("店铺图片必须在 "+rest_maxPic/1024 +"k 以内!");
				return messages;
			}
		}
		return null;
	}
	/**
	 * @throws SQLException
	* @Title: deleteMenu
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteMenu(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_menu_qd set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		restMenuDao.deleteMenu(sql.toString());
		return messages;
	}
}
