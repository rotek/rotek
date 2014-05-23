/**
* @FileName: NewsService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-6 下午04:27:23
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
import com.rotek.dao.impl.NewsDao;
import com.rotek.dto.NewsDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.NewsEntity;
import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;
import com.rotek.util.FileUtils;

/**
 * @ClassName: NewsService
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-6 下午04:27:23
 *
 */
@Service
public class NewsService {

	@Autowired
	private NewsDao newsDao;

	/**
	 * @throws SQLException
	* @Title: listNews
	* @Description:
	* @param news
	* @param pager
	* @return
	* @return Object
	* @throws
	*/
	public List<NewsDto> listNews(NewsEntity news, ListPager pager,UserDto user) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();

		sql.append("select n.id,n.building_id,n.title,n.content,n.send_time,n.type,n.level,n.status,b.name building_name");
		sql.append(" from mf_news n left join mf_building b on n.building_id = b.id ");
		sql.append(" where n.building_id in (select b_id  from mf_building_dep where dep_id in (select id from mf_department where super_dep_id = ? or id = ?))");

		params.add(user.getDep_id());
		params.add(user.getDep_id());

		if(SystemGlobals.getIntPreference("super_dep_id", 0) != user.getDep_id()){
			sql.append(" and n.status <> ?");
			params.add(OrderStatus.INVALID);
		}

		if(null != news.getId()){
			sql.append(" and n.id = ?");
			params.add(news.getId());
		}
		if(null != news.getBuilding_id()){
			sql.append(" and n.building_id = ?");
			params.add(news.getBuilding_id());
		}
		if(null != news.getType()){
			sql.append(" and n.type = ?");
			params.add(news.getType());
		}
		if(null != news.getLevel()){
			sql.append(" and n.level = ?");
			params.add(news.getLevel());
		}
		if(null != news.getStatus()){
			sql.append(" and n.status = ?");
			params.add(news.getStatus());
		}
		if(StringUtils.isNotBlank(news.getTitle())){
			sql.append(" and n.title like '%").append(news.getTitle()).append("%'");
		}

		return newsDao.listNews(sql.toString(),params.toArray(),pager);
	}

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addNews
	* @Description:
	* @param news
	* @param building_ids
	* @return
	* @return Object
	* @throws
	*/
	public List<String> addNews(NewsEntity news, String building_ids) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {

		List<String> messages = ValidateUtil.validate(news);
		if(messages.size()>0 || StringUtils.isBlank(building_ids)){
			messages.add(StringUtils.isBlank(building_ids) ? "楼宇id不能为空！" : "");
			return messages;
		}

		for(String building_id : building_ids.split(",")){

			news.setBuilding_id(Integer.parseInt(building_id));
			newsDao.addNews(news);
		}

		return null;
	}

	/**
	 * @throws IOException
	 * @throws IllegalStateException
	* @Title: saveNewsImg
	* @Description:
	* @param multipartRequest
	* @return
	* @return boolean
	* @throws
	*/
	public String saveNewsImg(MultipartHttpServletRequest multipartRequest) throws IllegalStateException, IOException {

		MultipartFile pic = multipartRequest.getFile("NewFile");
		if(null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("news.pic.path");
			long rest_maxPic = SystemGlobals.getLongPreference("news.pic.maxSize", 102400);
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
	* @Title: getNewsDetail
	* @Description:
	* @param id
	* @return
	* @return NewsEntity
	* @throws
	*/
	public NewsEntity getNewsDetail(Integer id) throws SQLException {

		if(null == id){
			return null;
		}
		return newsDao.getNewsDetail(id);
	}

	/**
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyNews
	* @Description:
	* @param news
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyNews(NewsEntity news) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException {

		List<String> messages = ValidateUtil.validate(news);
		if(messages.size() > 0 || null == news.getId()){

			return messages;
		}

		newsDao.modifyNews(news);
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: deleteNews
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteNews(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_news set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		newsDao.deleteNews(sql.toString());
		return messages;
	}
}
