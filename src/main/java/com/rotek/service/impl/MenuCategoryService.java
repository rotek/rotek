/**
* @FileName: MenuCategoryService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-22 上午09:53:31
* @version V1.0
*/
package com.rotek.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rotek.dao.impl.MenuCategoryDao;

/**
 * @ClassName: MenuCategoryService
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-22 上午09:53:31
 *
 */
@Service
public class MenuCategoryService {

	@Autowired
	private MenuCategoryDao menuCategoryDao;

	/**
	 * @throws SQLException
	* @Title: listCategories
	* @Description:
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listCategories() throws SQLException {

		return menuCategoryDao.listCategories();
	}
}
