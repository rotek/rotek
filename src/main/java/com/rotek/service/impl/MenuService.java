/**
* @FileName: MenuService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 上午11:52:05
* @version V1.0
*/
package com.rotek.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.MenuDao;
import com.rotek.dto.MenuDto;
import com.rotek.entity.MenuEntity;
import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.danga.MemCached.MemCachedClient;

/**
 * @ClassName: MenuService
 * @Description: 系统菜单的业务
 * @author chenwenpeng
 * @date 2013-6-26 上午11:52:05
 *
 */
@Service
public class MenuService {

	@Autowired
	private MenuDao menuDao;
	/**
	 * @throws SQLException
	* @Title: listMenus
	* @Description:
	* @param @param menu
	* @param @param pager
	* @param @return
	* @return List<MenuEntity>
	* @throws
	*/
	public List<MenuEntity> listMenus(MenuEntity menu, ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select 	id, menu_name, url, status from mf_menu where 1 = 1");

		if(null != menu.getId()){
			sql.append(" and id = ?");
			params.add(menu.getId());
		}
		if(StringUtils.isNotEmpty(menu.getMenu_name())){
			sql.append(" and menu_name like '%"+menu.getMenu_name()+"%'");
		}
		if(StringUtils.isNotEmpty(menu.getUrl())){
			sql.append(" and url like '%"+menu.getUrl()+"%'");
		}
		if(null != menu.getSort()){
			sql.append(" and sort = ?");
			params.add(menu.getSort());
		}
		if(null != menu.getStatus()){
			sql.append(" and status = ?");
			params.add(menu.getStatus());
		}
		if(null != menu.getSuper_menu_id()){
			sql.append(" and super_menu_id = ?");
			params.add(menu.getSuper_menu_id());
		}

		sql.append(" order by super_menu_id,status,sort desc");
		return menuDao.listMenus(sql.toString(),params.toArray(),pager);
	}
	/**
	 * @throws SQLException
	* @Title: listMenus_combo
	* @Description: 列出所有上级菜单
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listMenus_super() throws SQLException {

		String super_menu_ids = SystemGlobals.getPreference("super_menu_id", "0");
		return menuDao.listMenus_super(super_menu_ids);
	}
	/**
	 * @throws SQLException
	* @Title: listMenus_sort
	* @Description:  列出所有菜单和排序
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listMenus_sort() throws SQLException {

		return menuDao.listMenus_sort();
	}
	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addMenu
	* @Description:
	* @param @param menu
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> addMenu(MenuEntity menu) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(menu);
		if(messages.size()>0){
			return messages;
		}
		menuDao.addMenu(menu);
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return null;
	}
	/**
	 * @throws SQLException
	* @Title: getMenuDetail
	* @Description:
	* @param @param id
	* @param @return
	* @return MenuEntity
	* @throws
	*/
	public MenuDto getMenuDetail(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		MenuDto menu = menuDao.getMenuDetail(id);
		if(null != menu){
			List<Map<String,Object>> buttonList = menuDao.getMenuButonList(id);
			menu.setButtonList(buttonList);
		}

		return menu;
	}
	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyMenu
	* @Description: 修改菜单信息
	* @param @param menu
	* @param @param buttons
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyMenu(MenuEntity menu, String buttons) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {
		List<String> messages = ValidateUtil.validate(menu);
		Integer menuId= menu.getId();
		if(messages.size() > 0 || null == menuId || null == buttons){
			return messages;
		}
		//修改信息
		menuDao.modifyMenu(menu);
		//清除菜单对应的按钮
		menuDao.clearMenu_button(menuId);
		//添加菜单和按钮的对应关系
		for(String buttonId_str : buttons.split(",")){
			if(StringUtils.isEmpty(buttonId_str)){
				break;
			}
			Integer buttonId = Integer.parseInt(buttonId_str);
			menuDao.addMenu_button(menuId,buttonId);
		}
		
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return null;
	}
	/**
	 * @throws SQLException
	* @Title: deleteMenu
	* @Description:
	* @param @param ids
	* @param @return
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
		sql.append("update mf_menu set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		menuDao.deleteMenu(sql.toString());
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return messages;
	}
}
