/**
* @FileName: AboutController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-8-12 下午03:36:35
* @version V1.0
*/
package com.rotek.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.rotek.dto.UserDto;
import com.rotek.service.impl.AboutService;

/**
 * @ClassName: AboutController
 * @Description:
 * @author chenwenpeng
 * @date 2013-8-12 下午03:36:35
 *
 */
@Controller
@RequestMapping("/admin/about")
public class AboutController {

	@Autowired
	private AboutService aboutService;
	/**
	* @Title: toAbouts
	* @Description:
	* @param modelMap
	* @return
	* @return String
	* @throws
	*/
	@RequestMapping("/toAbouts")
	public String toAbouts(ModelMap modelMap){

		//上传的新闻图片路径
		String about_pic_upload_url = SystemGlobals.getPreference("about.pic.upload.url");
		modelMap.put("about_pic_upload_url", about_pic_upload_url);
		return "admin/about/abouts";
	}

	/**
	* @Title: listAbouts
	* @Description:
	* @param user
	* @param start
	* @param limit
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listAbouts")
	public String listAbouts(UserDto user,
		@RequestParam(value = "start", defaultValue = "0") Integer start,
		@RequestParam(value = "limit", defaultValue = "15") Integer limit,
		ModelMap modelMap) throws SQLException{

		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);


		modelMap.put("dataList", aboutService.listAbouts(pager));
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @Title: addAbout
	* @Description:
	* @param user
	* @param request
	* @param modelMap
	* @param type
	* @param content
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	@RequestMapping("addAbout")
	public String addAbout(UserDto user,HttpServletRequest request,ModelMap modelMap,
			@RequestParam(value = "type", defaultValue = "") Integer type,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			@RequestParam(value = "content", defaultValue = "") String content) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{


		List<String> messages = aboutService.addAbout(type,status,content);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: getAboutDetail
	* @Description:
	* @param user
	* @param request
	* @param modelMap
	* @param type
	* @param content
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	@RequestMapping("getAboutDetail")
	public String getAboutDetail(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		Map<String,Object> about = aboutService.getAboutDetail(id);
		modelMap.put("data", about);
		return "jsonView";
	}

	/**
	* @Title: modifyAbout
	* @Description:
	* @param user
	* @param request
	* @param modelMap
	* @param id
	* @param type
	* @param content
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	@RequestMapping("modifyAbout")
	public String modifyAbout(UserDto user,HttpServletRequest request,ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "type", defaultValue = "") Integer type,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			@RequestParam(value = "content", defaultValue = "") String content) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		List<String> messages = aboutService.modifyAbout(id,type,status,content);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: deleteAbout
	* @Description:
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteAbout")
	public String deleteAbout(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = aboutService.deleteAbout(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}



	/**
	 * @throws IOException
	 * @throws IllegalStateException
	* @Title: uploadImg
	* @Description: 上传图片
	* @param modelMap
	* @return void
	* @throws
	*/
	@RequestMapping("/uploadImg")
	public void uploadImg(ModelMap modelMap,HttpServletRequest request,HttpServletResponse response) throws IllegalStateException, IOException{
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		String pic_name = aboutService.saveAboutImg(multipartRequest);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.flush();
		String ret = "";
		if(null != pic_name){
			String pic_url = SystemGlobals.getPreference("about.pic.url")+"/"+pic_name;
			ret = "<script type='text/javascript'>parent.OnUploadCompleted(0,'"+pic_url+"')</script>";

		}else{
			ret = "<script type='text/javascript'>parent.OnUploadCompleted(1,null,null,'上传失败图片大小超过限制!')</script>";
		}
        out.write(ret);
	}
}
