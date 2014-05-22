/**
* @FileName: NewsController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-6 下午04:07:58
* @version V1.0
*/
package com.rotek.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rotek.constant.DataStatus;
import com.rotek.dto.UserDto;
import com.rotek.entity.NewsEntity;
import com.rotek.platform.config.SystemGlobals;
import com.rotek.platform.util.ListPager;
import com.rotek.service.impl.NewsService;
import com.danga.MemCached.MemCachedClient;

/**
 * @ClassName: NewsController
 * @Description:新闻
 * @author chenwenpeng
 * @date 2013-8-6 下午04:07:58
 *
 */
@Controller
@RequestMapping("/admin/news")
public class NewsController {


	@Autowired
	private NewsService newsService;

	/**
	* @Title: toButtons
	* @Description: 转到按钮模块
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("/toNews")
	public String toButtons(ModelMap modelMap){
		//上传的新闻图片路径
		String news_pic_upload_url = SystemGlobals.getPreference("news.pic.upload.url");
		modelMap.put("news_pic_upload_url", news_pic_upload_url);
		return "admin/news/news";
	}
	/**
	* @Title: listNews
	* @Description: 列出用户能管理的新闻
	* @param user
	* @param start
	* @param limit
	* @param id
	* @param title
	* @param status
	* @param request
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listNews")
	public String listNews(UserDto user,
		@RequestParam(value = "start", defaultValue = "0") Integer start,
		@RequestParam(value = "limit", defaultValue = "15") Integer limit,

		@RequestParam(value = "id", defaultValue = "") Integer id,
		@RequestParam(value = "building_id", defaultValue = "") Integer building_id,
		@RequestParam(value = "type", defaultValue = "") Integer type,
		@RequestParam(value = "title", defaultValue = "") String title,
		@RequestParam(value = "status", defaultValue = "") Integer status,
		@RequestParam(value = "level", defaultValue = "") Integer level,
		HttpServletRequest request,
		ModelMap modelMap) throws SQLException{

		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		NewsEntity news = new NewsEntity();
		news.setId(id);
		news.setTitle(title);
		news.setBuilding_id(building_id);
		news.setType(type);
		news.setLevel(level);
		news.setStatus(status);

		modelMap.put("dataList", newsService.listNews(news,pager,user));
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}


	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addNews
	* @Description:
	* @param user
	* @param start
	* @param limit
	* @param title
	* @param building_ids
	* @param type
	* @param level
	* @param status
	* @param content
	* @param request
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("addNews")
	public String addNews(UserDto user,
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,

			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "building_ids", defaultValue = "") String building_ids,
			@RequestParam(value = "type", defaultValue = "") Integer type,
			@RequestParam(value = "level", defaultValue = "") Integer level,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			@RequestParam(value = "content", defaultValue = "") String content,
			HttpServletRequest request,
			ModelMap modelMap) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		NewsEntity news = new NewsEntity();
		news.setBuilding_id(DataStatus.NONE_INT);
		news.setContent(content);
		news.setLevel(level);
		news.setSend_time(new Date());
		news.setStatus(status);
		news.setTitle(title);
		news.setType(type);


		List<String> messages = newsService.addNews(news,building_ids);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return "jsonView";
	}


	/**
	* @Title: modifyNews
	* @Description:
	* @param user
	* @param start
	* @param limit
	* @param title
	* @param building_ids
	* @param type
	* @param level
	* @param status
	* @param content
	* @param request
	* @param modelMap
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	@RequestMapping("modifyNews")
	public String modifyNews(UserDto user,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "title", defaultValue = "") String title,
			@RequestParam(value = "building_id", defaultValue = "") Integer building_id,
			@RequestParam(value = "type", defaultValue = "") Integer type,
			@RequestParam(value = "level", defaultValue = "") Integer level,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			@RequestParam(value = "content", defaultValue = "") String content,
			HttpServletRequest request,
			ModelMap modelMap) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		NewsEntity news = new NewsEntity();
		news.setId(id);
		news.setBuilding_id(building_id);
		news.setContent(content);
		news.setLevel(level);
		news.setSend_time(new Date());
		news.setStatus(status);
		news.setTitle(title);
		news.setType(type);


		List<String> messages = newsService.modifyNews(news);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return "jsonView";
	}

	/**
	* @Title: getNewsDetail
	* @Description: 获取新闻的详情
	* @param modelMap
	* @param id
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getNewsDetail")
	public String getNewsDetail(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException{

		NewsEntity news = newsService.getNewsDetail(id);
		modelMap.put("data", news);
		return "jsonView";
	}

	/**删除一个或多个新闻信息
	 * @param roleEntity
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("deleteNews")
	public String deleteNews(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = newsService.deleteNews(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return "jsonView";
	}


	/**
	 * @throws IOException
	 * @throws IllegalStateException
	* @Title: uploadImg
	* @Description: 上传新闻图片
	* @param modelMap
	* @return void
	* @throws
	*/
	@RequestMapping("/uploadImg")
	public void uploadImg(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		String pic_name = newsService.saveNewsImg(multipartRequest);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.flush();
		String ret = "";
		if(null != pic_name){
			String pic_url = SystemGlobals.getPreference("news.pic.url")+"/"+pic_name;
			ret = "<script type='text/javascript'>parent.OnUploadCompleted(0,'"+pic_url+"')</script>";

		}else{
			ret = "<script type='text/javascript'>parent.OnUploadCompleted(1,null,null,'上传失败图片大小超过限制!')</script>";
		}
        out.write(ret);
	}
}
