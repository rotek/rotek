/**
* @FileName: IndexService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-31 上午10:34:47
* @version V1.0
*/
package com.rotek.service.impl;

import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.dao.impl.IndexDao;
import com.rotek.dto.MenuDto;
import com.rotek.entity.MenuEntity;
import com.rotek.util.DateUtils;

/**
 * @ClassName: IndexService
 * @Description: 首页的业务
 * @author chenwenpeng
 * @date 2013-5-31 上午10:34:47
 *
 */
@Service
public class IndexService {

	@Autowired
	private IndexDao indexDao;
	/**
	 * @throws SQLException
	* @Title: listMenu
	* @Description:
	* @param @param role_id
	* @param @return
	* @return List<MenuEntity>
	* @throws
	*/
	public List<MenuDto> listMenu(Integer role_id) throws SQLException {
		 if(null == role_id){
			return null;
		 }
		 //获取父级菜单
		 List<MenuDto> super_menuList = indexDao.listSuperMenu(role_id);
		 //子级菜单
		 List<MenuEntity> child_menuList = indexDao.listChildMenu(role_id);
		 for(MenuDto super_menu : super_menuList){
			 for(MenuEntity child_menu : child_menuList){
				 if(super_menu.getId().equals(child_menu.getSuper_menu_id())){
					 super_menu.getSubMenuList().add(child_menu);
				 }
			 }
		 }
		return super_menuList;
	}
	/**
	 * @throws SQLException
	* @Title: listTodos
	* @Description:
	* @param dep_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public Map<String, Object> listTodos(Integer dep_id) throws SQLException {

		Map<String,Object> count = new HashMap<String,Object>();
		Map<String,Object> count_order = indexDao.getOrderCount(dep_id,DateUtils.getDateForQuery(new Date()));
		Map<String,Object> count_gift = indexDao.getGiftCount(DateUtils.getDateForQuery(new Date()));

		count.put("count_order", null ==count_order || null == count_order.get("count") ? 0 : count_order.get("count"));
		count.put("count_gift", null ==count_gift || null == count_gift.get("count") ? 0 : count_gift.get("count"));

		return count;
	}
}
