/**
* @FileName: ButtonDao.java
* @Package com.rotek.dao.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 下午03:29:30
* @version V1.0
*/
package com.rotek.dao.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.rotek.entity.ButtonEntity;
import com.rotek.platform.persistence.dao.BaseDaoImpl;
import com.rotek.platform.util.ListPager;

/**
 * @ClassName: ButtonDao
 * @Description: 按钮
 * @author chenwenpeng
 * @date 2013-6-26 下午03:29:30
 *
 */
@Repository
public class ButtonDao extends BaseDaoImpl{

	/**
	 * @throws SQLException
	* @Title: listButtons_s
	* @Description:
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listButtons_s() throws SQLException {

		String sql = "select id,button_name,sort from mf_button where status = 1";
		return this.executeQuery(sql, null);
	}

	/**
	 * @throws SQLException
	 * @param pager
	 * @param objects
	 * @param string
	* @Title: listButtons
	* @Description:
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<ButtonEntity> listButtons(String sql, Object[] params, ListPager pager) throws SQLException {

		return this.selectPage(sql, params,ButtonEntity.class,pager);
	}

	/**
	 * @throws SQLException
	* @Title: addButton
	* @Description:
	* @param @param button
	* @return void
	* @throws
	*/
	public void addButton(ButtonEntity button) throws SQLException {

		this.insert(button);
	}

	/**
	 * @throws SQLException
	* @Title: modifyButton
	* @Description:
	* @param @param button
	* @return void
	* @throws
	*/
	public void modifyButton(ButtonEntity button) throws SQLException {

		this.update(button);
	}

	/**
	 * @throws SQLException
	* @Title: getButtonDetail
	* @Description:
	* @param @param id
	* @param @return
	* @return ButtonEntity
	* @throws
	*/
	public ButtonEntity getButtonDetail(Integer id) throws SQLException {

		String sql = "select id, button_name, action, memo, icon, sort, status from mf_button where id = ?";
		return this.selectOne(sql, new Integer[]{id},ButtonEntity.class);
	}

	/**
	 * @throws SQLException
	* @Title: deleteButton
	* @Description:
	* @param @param string
	* @return void
	* @throws
	*/
	public void deleteButton(String sql) throws SQLException {

		this.executeUpdate(sql);
	}
}
