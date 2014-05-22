/**
* @FileName: MenuCategoryDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-22 上午09:54:20
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.platform.persistence.dao.BaseDaoImpl;

/**
 * @ClassName: MenuCategoryDao
 * @Description:
 * @author chenwenpeng
 * @date 2013-7-22 上午09:54:20
 *
 */
@Repository
public class MenuCategoryDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listCategories
	* @Description:
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listCategories() throws SQLException {

		String sql = "select id,name from mf_cate_menu";
		return this.executeQuery(sql, null);
	}
}
