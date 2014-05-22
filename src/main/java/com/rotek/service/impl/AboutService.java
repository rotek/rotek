/**
* @FileName: AboutService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-12 下午03:47:27
* @version V1.0
*/
package com.rotek.service.impl;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rotek.constant.DataStatus;
import com.rotek.dao.impl.AboutDao;
import com.rotek.platform.config.SystemGlobals;
import com.rotek.platform.util.ListPager;
import com.rotek.util.FileUtils;

/**
 * @ClassName: AboutService
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-12 下午03:47:27
 *
 */
@Service
public class AboutService {

	@Autowired
	private AboutDao aboutDao;

	/**
	 * @throws SQLException
	 * @param pager
	* @Title: listNews
	* @Description:
	* @param pager
	* @return
	* @return Object
	* @throws
	*/
	public List<Map<String,Object>> listAbouts(ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select id,type,status from mf_about");

		return aboutDao.listAbouts(sql.toString(),pager);
	}

	/**
	 * @throws SQLException
	* @Title: addAbout
	* @Description:
	* @param type
	* @param content
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> addAbout(Integer type,Integer status, String content) throws SQLException {

		List<String> messages = new LinkedList<String>();
		int maxSize = SystemGlobals.getIntPreference("about.page.maxSize", 40000);
		if(null == type || null == status || StringUtils.isEmpty(content) || content.length()>maxSize){
			messages.add("页面请在1-"+maxSize+"字符以内！");
			return messages;
		}
		Map<String,Object> about = aboutDao.getAboutByType(type);
		if(null != about){
			messages.add("本页面已经存在，如果您要修改请点击修改按钮");
			return messages;
		}
		aboutDao.addAbout(type,status,content);
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: getAboutDetail
	* @Description:
	* @param id
	* @return
	* @return Map<String,Object>
	* @throws
	*/
	public Map<String, Object> getAboutDetail(Integer id) throws SQLException {

		if(null == id){
			return null;
		}
		return aboutDao.getAboutDetail(id);
	}

	/**
	 * @throws SQLException
	* @Title: modifyAbout
	* @Description:
	* @param id
	* @param type
	* @param content
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyAbout(Integer id, Integer type,Integer status, String content) throws SQLException {

		List<String> messages = new LinkedList<String>();
		int maxSize = SystemGlobals.getIntPreference("about.page.maxSize", 40000);
		if(null == id || null == type || null == status || StringUtils.isEmpty(content) || content.length()>maxSize){
			messages.add("页面请在1-"+maxSize+"字符以内！");
			return messages;
		}
		aboutDao.modifyAbout(id,type,status,content);
		return null;
	}

	/**
	 * @throws IOException
	 * @throws IllegalStateException
	* @Title: saveAboutImg
	* @Description:
	* @param multipartRequest
	* @return
	* @return String
	* @throws
	*/
	public String saveAboutImg(MultipartHttpServletRequest multipartRequest) throws IllegalStateException, IOException {

		MultipartFile pic = multipartRequest.getFile("NewFile");
		if(null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("about.pic.path");
			long rest_maxPic = SystemGlobals.getLongPreference("about.pic.maxSize", 102400);
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, rest_maxPic);
			if(null != pic_name){
				return pic_name;
			}
		}
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: deleteAbout
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteAbout(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_about set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		aboutDao.deleteNews(sql.toString());
		return messages;
	}
}
