/**
* @FileName: AuthorityService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-3 上午09:10:14
* @version V1.0
*/
package com.rotek.service.impl;

import java.sql.SQLException;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.dao.impl.AuthorityDao;
import com.rotek.entity.ButtonEntity;
import com.rotek.entity.MenuEntity;
import com.rotek.entity.User;

/**
 * @ClassName: AuthorityService
 * @Description: 权限
 * @author chenwenpeng
 * @date 2013-6-3 上午09:10:14
 *
 */
@Service
public class AuthorityService {

	@Autowired
	private AuthorityDao authorityDao;
	/**
	 * @throws SQLException
	* @Title: listPower
	* @Description: 根据url 列出用户对应的模块的权限信息{add:true,drop:true}
	* @param @param user
	* @param @param url_inDB
	* @param @return
	* @return JSONObject
	* @throws
	*/
	public JSONObject listAuthority(Integer roleId, String url_inDB) throws SQLException {
		JSONObject authority = new JSONObject();
		if(StringUtils.isBlank(url_inDB)){
			return authority;
		}
		MenuEntity menu = authorityDao.getMenu(url_inDB);
		if(null == menu){
			return authority;
		}

		Integer menuId = menu.getId();
		List<ButtonEntity> buttonList = authorityDao.getListButton(roleId,menuId);
		for(ButtonEntity button : buttonList){
			authority.put(button.getAction(),true);
		}
		authority.put("add",true);
		authority.put("drop",true);
		authority.put("modify",true);
		authority.put("query",true);
		authority.put("viewDetail",true);
		authority.put("setDepartmentDeliverer",true);
		authority.put("setRestaurantDeliverer",true);
		authority.put("setDepartment",true);
		authority.put("setBuilding",true);
		authority.put("setReserveTime",true);
		authority.put("setRestaurantType",true);
		return authority;
	}
}
