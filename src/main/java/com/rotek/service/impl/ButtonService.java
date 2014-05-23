/**
* @FileName: ButtonService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-26 下午03:27:49
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
import com.rotek.dao.impl.ButtonDao;
import com.rotek.entity.ButtonEntity;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;

/**
 * @ClassName: ButtonService
 * @Description: 按钮
 * @author chenwenpeng
 * @date 2013-6-26 下午03:27:49
 *
 */
@Service
public class ButtonService {

	@Autowired
	private ButtonDao buttonDao;
	/**
	 * @throws SQLException
	* @Title: listButtons_s
	* @Description: 列出按钮id name
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listButtons_s() throws SQLException {

		return buttonDao.listButtons_s();
	}
	/**
	 * @throws SQLException
	 * @param pager
	 * @param button
	* @Title: listButtons
	* @Description: 列出展示的button信息
	* @param @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<ButtonEntity> listButtons(ButtonEntity button, ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select id,button_name,action,memo,status,sort from mf_button");

		sql.append(" order by sort desc");
		return buttonDao.listButtons(sql.toString(),params.toArray(),pager);
	}
	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addButton
	* @Description:
	* @param @param button
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> addButton(ButtonEntity button) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {

		List<String> messages = ValidateUtil.validate(button);
		if(messages.size()>0){
			return null;
		}

		buttonDao.addButton(button);
		return null;
	}
	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	* @Title: modifyButton
	* @Description:
	* @param @param button
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyButton(ButtonEntity button) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<String> messages = ValidateUtil.validate(button);
		if(messages.size()>0 || null == button.getId()){
			return null;
		}
		buttonDao.modifyButton(button);
		return null;
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
	public ButtonEntity getButtonDetail(Integer id) throws SQLException{
		if(null == id){
			return null;
		}
		return buttonDao.getButtonDetail(id);
	}
	/**
	 * @throws SQLException
	* @Title: deleteButton
	* @Description:
	* @param @param ids
	* @param @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteButton(String ids) throws SQLException {
		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_button set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		buttonDao.deleteButton(sql.toString());
		return messages;
	}
}
