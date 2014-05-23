/**
\* @FileName: RestaurantController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-4 下午01:52:10
* @version V1.0
*/
package com.rotek.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rotek.constant.DataStatus;
import com.rotek.dto.RestaurantDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.RestaurantEntity;
import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.rotek.service.impl.RestaurantService;
import com.rotek.util.DateUtils;

@Controller
@RequestMapping("/admin/restaurant")
public class RestaurantController {

	@Autowired
	private RestaurantService restaurantService;

	/**
	* @Title: toRestaurants
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("toRestaurants")
	public String toRestaurants(ModelMap modelMap){

		//上传的新闻图片路径
		String news_pic_upload_url = SystemGlobals.getPreference("news.pic.upload.url");
		modelMap.put("news_pic_upload_url", news_pic_upload_url);
		return "admin/restaurant/restaurants";
	}
	/**
	 * @throws SQLException
	* @Title: listRestaurants
	* @Description: 获取所有店铺的信息
	* @param @param id
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("listRestaurants")
	public String listRestaurants(ModelMap modelMap,
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			RestaurantDto restaurant,UserDto user) throws SQLException{
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		List<RestaurantDto> restaurants = restaurantService.listRestaurants(user,restaurant,pager);
		modelMap.put("dataList", restaurants);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @Title: listRestaurants_s
	* @Description: 列出店铺的简单信息
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listRestaurants_s")
	public String listBuildings_s(ModelMap modelMap,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "range", defaultValue = "0") Integer range) throws SQLException{

		List<Map<String,Object>> restaurants = restaurantService.listRestaurants_s(name,range);
		modelMap.put("dataList", restaurants);
		return "jsonView";
	}

	/**
	 * @Title: listRestaurants_sort
	 * @Description: 列出店铺的排序信息
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 * @return String
	 * @throws
	 */
	@RequestMapping("listRestaurants_sort")
	public String listRestaurants_sort(ModelMap modelMap) throws SQLException{

		List<Map<String,Object>> restaurants = restaurantService.listRestaurants_sort();
		modelMap.put("dataList", restaurants);
		return "jsonView";
	}



	/**
	 * @Title: listRestaurants_sort
	 * @Description: 列出受我管理的店铺
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 * @return String
	 * @throws
	 */
	@RequestMapping("listRestaurants_dep")
	public String listRestaurants_dep(ModelMap modelMap,UserDto user,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "range", defaultValue = "0") Integer range) throws SQLException{

		List<Map<String,Object>> restaurants = restaurantService.listRestaurants_dep(name,user.getDep_id());
		modelMap.put("dataList", restaurants);
		return "jsonView";
	}

	/**
	* @Title: listRestaurants_building
	* @Description: 列出某个楼宇的送达店铺（受我管理）
	* @param modelMap
	* @param user
	* @param buildingId
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listRestaurants_building_dep")
	public String listRestaurants_building_dep(ModelMap modelMap,UserDto user,
			@RequestParam(value = "id", defaultValue = "0") Integer id) throws SQLException{

		List<Map<String,Object>> restaurants = restaurantService.listRestaurants_building_dep(id,user.getDep_id());
		modelMap.put("dataList", restaurants);
		return "jsonView";
	}


	/**
	 * @throws ParseException
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addRestaurant
	* @Description: 添加店铺的信息
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("addRestaurant")
	public void addRestaurant(ModelMap modelMap,
			HttpServletResponse response,
			HttpServletRequest request,
			UserDto user,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "alias", defaultValue = "") String alias,
			@RequestParam(value = "address", defaultValue = "") String address,
			@RequestParam(value = "telephone", defaultValue = "") String telephone,
			@RequestParam(value = "notice", defaultValue = "") String notice,
			@RequestParam(value = "deli_price", defaultValue = "") Double deli_price,
			@RequestParam(value = "carriage", defaultValue = "") Double carriage,
			@RequestParam(value = "region_id", defaultValue = "") Integer region_id,
			@RequestParam(value = "deli_type", defaultValue = "") Integer deli_type,
			@RequestParam(value = "send_type", defaultValue = "") Integer send_type,
			@RequestParam(value = "sort", defaultValue = "0") Integer sort,
			@RequestParam(value = "content", defaultValue = "") String content,
			@RequestParam(value = "status", defaultValue = "") Integer status) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, IllegalStateException, ParseException{

			MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

			RestaurantEntity restaurant = new RestaurantEntity();

			restaurant.setName(name);
			restaurant.setAlias(alias);
			restaurant.setAddress(address);
			restaurant.setTelephone(telephone);
			restaurant.setNotice(notice);
			restaurant.setDeli_price(deli_price);
			restaurant.setCarriage(carriage);
			restaurant.setRegion_id(region_id);
			restaurant.setDeli_type(deli_type);
			restaurant.setSend_type(send_type);
			restaurant.setSort(null == sort ? DataStatus.NONE_INT : sort);
			restaurant.setStatus(status);

			restaurant.setJoin_date(new Date());
			restaurant.setPic("nopic");
			restaurant.setDeli_count(0);

			List<String> messages = null;
			try{
				messages = restaurantService.addRestaurant(restaurant,content,multipartRequest,user);
			}catch (Exception e) {
				messages = new ArrayList<String>();
				messages.add("保存出错，如果错误继续出现，请联系工程师解决!");
			}
			JSONObject json = new JSONObject();
			 json.put("success", null == messages ? true : false);
			 json.put("messages", messages);
			 response.setStatus(200);
			 response.setContentType("text/html;charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.write(json.toString());
	}


	/**
	 * @throws SQLException
	* @Title: getRestaurantDetail
	* @Description:
	* @param modelMap
	* @param id
	* @return
	* @return String
	* @throws
	*/
	@RequestMapping("getRestaurantDetail")
	public String getRestaurantDetail(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException{

		RestaurantDto restaurant = restaurantService.getRestaurantDetail(id);
		if(null != restaurant && null != restaurant.getJoin_date()){

			modelMap.put("join_date", DateUtils.formatDate(restaurant.getJoin_date()));
		}
		modelMap.put("data", restaurant);
		return "jsonView";
	}


	/**
	 * @throws SQLException
	 * @Title: getRestaurantDetail
	 * @Description: 查看店铺详情的其他信息
	 * @param modelMap
	 * @param id
	 * @return
	 * @return String
	 * @throws
	 */
	@RequestMapping("getRestaurantDetail_other")
	public String getRestaurantDetail_other(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException{

		RestaurantDto restaurant = restaurantService.getRestaurantDetail_other(id);
		if(null != restaurant && null != restaurant.getOpen_time_am()){
			modelMap.put("open_time_am", DateUtils.formatTime(restaurant.getOpen_time_am()));
		}
		if(null != restaurant && null != restaurant.getClose_time_am()){
			modelMap.put("close_time_am", DateUtils.formatTime(restaurant.getClose_time_am()));
		}
		if(null != restaurant && null != restaurant.getOpen_time_pm()){
			modelMap.put("open_time_pm", DateUtils.formatTime(restaurant.getOpen_time_pm()));
		}
		if(null != restaurant && null != restaurant.getClose_time_pm()){
			modelMap.put("close_time_pm", DateUtils.formatTime(restaurant.getClose_time_pm()));
		}

		modelMap.put("data", restaurant);
		return "jsonView";
	}
	/**
	 * @throws ParseException
	 * @throws IllegalStateException
	 * @throws IOException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Title: modifyRestaurant
	 * @Description:更新店铺的信息
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 * @return String
	 * @throws
	 */
	@RequestMapping("modifyRestaurant")
	public void modifyRestaurant(ModelMap modelMap,
			HttpServletResponse response,
			HttpServletRequest request,

			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "alias", defaultValue = "") String alias,
			@RequestParam(value = "address", defaultValue = "") String address,
			@RequestParam(value = "pic", defaultValue = "") String pic,
			@RequestParam(value = "telephone", defaultValue = "") String telephone,
			@RequestParam(value = "notice", defaultValue = "") String notice,
			@RequestParam(value = "deli_price", defaultValue = "") Double deli_price,
			@RequestParam(value = "carriage", defaultValue = "") Double carriage,
			@RequestParam(value = "region_id", defaultValue = "") Integer region_id,
			@RequestParam(value = "deli_type", defaultValue = "") Integer deli_type,
			@RequestParam(value = "send_type", defaultValue = "") Integer send_type,
			@RequestParam(value = "sort", defaultValue = "0") Integer sort,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			@RequestParam(value = "join_date", defaultValue = "") Date join_date,
			@RequestParam(value = "content", defaultValue = "") String content,
			@RequestParam(value = "deli_count", defaultValue = "") Integer deli_count) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, IOException, IllegalStateException, ParseException{

		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		RestaurantEntity restaurant = new RestaurantEntity();
		restaurant.setId(id);
		restaurant.setName(name);
		restaurant.setAlias(alias);
		restaurant.setPic(StringUtils.isEmpty(pic)? DataStatus.NONE_STRING : pic);
		restaurant.setAddress(address);
		restaurant.setTelephone(telephone);
		restaurant.setNotice(notice);
		restaurant.setDeli_price(deli_price);
		restaurant.setCarriage(carriage);
		restaurant.setRegion_id(region_id);
		restaurant.setDeli_type(deli_type);
		restaurant.setSend_type(send_type);
		restaurant.setSort(null == sort ? DataStatus.NONE_INT : sort);
		restaurant.setStatus(status);
		restaurant.setDeli_count(deli_count);
		restaurant.setJoin_date(join_date);


		List<String> messages = null;
		try{
			messages = restaurantService.modifyRestaurant(restaurant,content,multipartRequest);
		}catch (Exception e) {
			messages = new ArrayList<String>();
			messages.add("修改出错，如果错误继续出现，请联系工程师解决!");
		}
		JSONObject json = new JSONObject();
		json.put("success", null == messages ? true : false);
		json.put("messages", messages);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json.toString());
	}


	/**删除一个或多个店铺信息
	 * @param roleEntity
	 * @param model
	 * @return
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 */
	@RequestMapping("deleteRestaurant")
	public String deleteRestaurant(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = restaurantService.deleteRestaurant(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: closeRestaurant
	* @Description:
	* @param @param ids
	* @param @param model
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("closeRestaurant")
	public String closeRestaurant(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model,UserDto user) throws SQLException{

		List<String> messages = restaurantService.closeRestaurant(ids,user);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: openRestaurant
	* @Description:
	* @param @param ids
	* @param @param model
	* @param @param user
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("openRestaurant")
	public String openRestaurant(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model,UserDto user) throws SQLException{

		List<String> messages = restaurantService.openRestaurant(ids,user);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: setReserveTime
	* @Description: 设置店铺的营业时间
	* @param model
	* @param open_time_am
	* @param close_time_am
	* @param open_time_pm
	* @param close_time_pm
	* @return
	* @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("setReserveTime")
	public String setReserveTime(ModelMap model,
			@RequestParam(value="ids", defaultValue="") String ids,
			@RequestParam(value="open_time_am", defaultValue="") String open_time_am,
			@RequestParam(value="close_time_am", defaultValue="") String close_time_am,
			@RequestParam(value="open_time_pm", defaultValue="") String open_time_pm,
			@RequestParam(value="close_time_pm", defaultValue="") String close_time_pm) throws Exception {


		List<String> messages = restaurantService.setReserveTime(ids,open_time_am,close_time_am,open_time_pm,close_time_pm);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**
	 * @Title: setReserveTime
	 * @Description: 设置店铺的送餐人
	 * @param model
	 * @param open_time_am
	 * @param close_time_am
	 * @param open_time_pm
	 * @param close_time_pm
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 */
	@RequestMapping("setRestaurantDeliverer")
	public String setRestaurantDeliverer(ModelMap model,
			@RequestParam(value="ids", defaultValue="") String ids,
			@RequestParam(value="deliverer_id", defaultValue="") Integer deliverer_id,
			@RequestParam(value="building_ids", defaultValue="") String building_ids,
			@RequestParam(value="rank", defaultValue="") Integer rank) throws Exception {


		List<String> messages = restaurantService.setRestaurantDeliverer(ids,deliverer_id,building_ids,rank);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: listRestaurantType_all
	* @Description: 列出所有的店铺的类型
	* @param model
	* @param ids
	* @param deliverer_id
	* @param region
	* @return
	* @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("listRestaurantType_all")
	public String listRestaurantType_all(ModelMap model) throws Exception {

		List<Map<String,Object>> types = restaurantService.listRestaurantType_all();
		model.put("dataList", types);
		return "jsonView";
	}

	/**
	 * @Title: listRestaurantType
	 * @Description: 列出店铺的类型
	 * @param model
	 * @param ids
	 * @param deliverer_id
	 * @param region
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 */
	@RequestMapping("listRestaurantType")
	public String listRestaurantType(ModelMap model,
			@RequestParam(value="id", defaultValue="") Integer id) throws Exception {

		List<Map<String,Object>> restTypes = restaurantService.listRestaurantType(id);
		model.put("data", restTypes);
		return "jsonView";
	}


	/**
	* @Title: setRestaurantDeliverer
	* @Description: 设置店铺的送餐人
	* @param model
	* @param ids
	* @param deliverer_id
	* @param region
	* @return
	* @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("setRestaurantType")
	public String setRestaurantDeliverer(ModelMap model,
			@RequestParam(value="id", defaultValue="") Integer id,
			@RequestParam(value="type_ids", defaultValue="") String type_ids) throws Exception {


		List<String> messages = restaurantService.setRestaurantType(id,type_ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**
	 * @Title: setRestaurantSettlement
	 * @Description: 设置店铺的结算方式
	 * @param model
	 * @param ids
	 * @param deliverer_id
	 * @param region
	 * @return
	 * @throws Exception
	 * @return String
	 * @throws
	 */
	@RequestMapping("setRestaurantSettlement")
	public String setRestaurantSettlement(ModelMap model,
			@RequestParam(value="ids", defaultValue="") String ids,
			@RequestParam(value="discount", defaultValue="") Double discount,
			@RequestParam(value="setttlement_type", defaultValue="") Integer setttlement_type,
			@RequestParam(value="period", defaultValue="") Integer period) throws Exception {


		List<String> messages = restaurantService.setRestaurantSettlement(ids,setttlement_type,discount,period);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: listRestaurant_menus
	* @Description: 列出店铺的所有有效菜单
	* @param model
	* @param rest_id
	* @return
	* @throws Exception
	* @return String
	* @throws
	*/
	@RequestMapping("listRestaurant_menus")
	public String listRestaurant_menus(ModelMap model,
			@RequestParam(value="rest_id", defaultValue="") Integer rest_id) throws Exception {

		List<Map<String,Object>> menus = restaurantService.listRestaurant_menus(rest_id);
		model.put("dataList", menus);
		return "jsonView";
	}




	@InitBinder
    protected void initBinder(HttpServletRequest request,
    	    ServletRequestDataBinder binder) throws Exception {
    	    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    	    CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
    	    binder.registerCustomEditor(Date.class, dateEditor);
    }
}
