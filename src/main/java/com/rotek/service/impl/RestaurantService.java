/**
* @FileName: RestaurantService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-4 上午11:53:13
* @version V1.0
*/
package com.rotek.service.impl;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.rotek.constant.DataStatus;
import com.rotek.constant.RangeType;
import com.rotek.constant.RestaurantStatus;
import com.rotek.constant.SettlementType;
import com.rotek.constant.TaskType;
import com.rotek.dao.impl.RestaurantDao;
import com.rotek.dao.impl.TaskDao;
import com.rotek.dto.RestaurantDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.DepartmentEntity;
import com.rotek.entity.RestaurantDetailEntity;
import com.rotek.entity.RestaurantEntity;
import com.rotek.entity.TaskEntity;
import com.rotek.platform.config.SystemGlobals;
import com.rotek.platform.util.ListPager;
import com.rotek.platform.util.ValidateUtil;
import com.rotek.util.DateUtils;
import com.rotek.util.FileUtils;
import com.danga.MemCached.MemCachedClient;

/**
 * @ClassName: RestaurantService
 * @Description: restaurant service
 * @author chenwenpeng
 * @date 2013-7-4 上午11:53:13
 *
 */
@Service
public class RestaurantService {

	@Autowired
	private RestaurantDao restaurantDao;
	@Autowired
	private TaskDao taskDao;

	/**
	 * @throws SQLException
	* @Title: listRestaurants_s
	* @Description:
	* @param name
	* @param range
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurants_s(String name,
			Integer region) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select id,name from mf_restaurant where status <> -1");
		//查询未分配的
		if(RangeType.UNDISTRIBUTED == region){
			sql.append(" and id not in (select distinct rest_id from mf_dep_restaurant)");
		}
		//模糊查询
		if(StringUtils.isNotBlank(name)){
			sql.append(" and name like '%"+name.trim()+"%'");
		}
		sql.append(" order by id desc");
		return restaurantDao.listRestaurants_s(sql.toString());
	}

	/**
	 * @param user 
	 * @throws SQLException
	* @Title: listRestaurants
	* @Description:
	* @param restaurant
	* @param pager
	* @return
	* @return List<RestaurantDto>
	* @throws
	*/
	public List<RestaurantDto> listRestaurants(UserDto user, RestaurantDto restaurant,
			ListPager pager) throws SQLException {

		StringBuilder sql = new StringBuilder();
		List<Object> params = new LinkedList<Object>();
		sql.append("select r.id, r.name, r.alias,r.telephone, r.deli_type, r.send_type, r.status,s.commission_type settlement_type,s.discount,period from mf_restaurant r left join mf_settlement s  on r.id = s.rest_id where 1 = 1");

		sql.append(" and r.id in (select rest_id from mf_dep_restaurant where dep_id in (select id from mf_department where id = ? or super_dep_id = ?))");
		params.add(user.getDep_id());
		params.add(user.getDep_id());
		
		if(null != restaurant.getId()){
			sql.append(" and r.id = ?");
			params.add(restaurant.getId());
		}
		if(StringUtils.isNotBlank(restaurant.getName())){
			sql.append(" and r.name like '%").append(restaurant.getName()).append("%'");
		}
		if(StringUtils.isNotBlank(restaurant.getAlias())){
			sql.append(" and r.alias like '%").append(restaurant.getAlias()).append("%'");
		}
		if(null != restaurant.getDeli_type()){
			sql.append(" and r.deli_type = ?");
			params.add(restaurant.getDeli_type());
		}

		if(null != restaurant.getRegion_id()){
			sql.append(" and r.region_id = ?");
			params.add(restaurant.getRegion_id());
		}
		if(null != restaurant.getStatus()){
			sql.append(" and r.status = ?");
			params.add(restaurant.getStatus());
		}

		sql.append(" order by r.id desc");

		List<RestaurantDto> restaurants = restaurantDao.listRestaurants(sql.toString(),params.toArray(),pager);
		for(RestaurantDto rest : restaurants){
			Integer rest_id = rest.getId();
			//店铺所属的管理部门
			List<DepartmentEntity> deps = restaurantDao.listDeparts(rest_id);
			for(DepartmentEntity dep : deps){
				if(deps.size()>1){
					rest.setDep_name("<span style=\'color:green;\'>"+(null == rest.getDep_name() ? "" : rest.getDep_name()) + dep.getDep_name()+"  </span>");
				}else {
					rest.setDep_name((null == rest.getDep_name() ? "" : rest.getDep_name()) + dep.getDep_name()+"  ");
				}
			}
			List<Map<String,Object>> deliverers = restaurantDao.listDeliverers(rest_id);
			//加载店铺送餐人
			for(Map<String,Object> d : deliverers){
				String deliverer_name = (String) d.get("realname");
				//校内送餐人
				rest.setDeliverer_name((null == rest.getDeliverer_name() ? "" : rest.getDeliverer_name()) + deliverer_name+"|  ");
			}
			//加载店铺结算方式
			if(null != rest.getSettlement_type()){

				Integer settlement_type = rest.getSettlement_type();
				Double discount = rest.getDiscount();
				Integer period = rest.getPeriod();
				StringBuilder setttlement_display = new StringBuilder();
				if(SettlementType.BYORDERPRICE.getType() == settlement_type){

					setttlement_display.append(SettlementType.BYORDERPRICE.getName()).append("（").append(discount).append("/").append(period).append("）");
				}else if(SettlementType.BYORDERMENUCOUNT.getType() == settlement_type){

					setttlement_display.append(SettlementType.BYORDERMENUCOUNT.getName()).append("（").append(discount).append("/").append(period).append("）");
				}
				rest.setSettlement_display(setttlement_display.toString());
			}

		}
		return restaurants;
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurants_sort
	* @Description:
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurants_sort() throws SQLException {

		return restaurantDao.listRestaurants_sort();
	}

	/**
	 * @param content 
	 * @param user 
	 * @throws ParseException
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addRestaurant
	* @Description:
	* @param restaurant
	* @param multipartRequest
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> addRestaurant(RestaurantEntity restaurant,
			String content, MultipartHttpServletRequest multipartRequest, UserDto user) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException, IllegalStateException, IOException, ParseException {

		List<String> messages = ValidateUtil.validate(restaurant);
		RestaurantEntity rest_= restaurantDao.getRestaurantByAlias(restaurant.getAlias());
		if(messages.size()>0 || null != rest_){
			messages.add(null == rest_ ? "" : "店铺的别名已经存在！");
			return messages;
		}
		//保存店铺信息
		Integer restId = restaurantDao.addRestaurant(restaurant);
		if(null == restId){
			throw new SQLException();
		}
		//在店铺详情表中创建一条数据
		RestaurantDetailEntity rest_detail = this.createRestDetailEntity(restId);
		rest_detail.setContent(content);
		restaurantDao.addRestaurantDetail(rest_detail);

		MultipartFile pic = multipartRequest.getFile("rest_pic");
		if(null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("restaurant.pic.path")+"/"+restId;
			long rest_maxPic = SystemGlobals.getLongPreference("restaurant.pic.maxSize", 102400);
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, rest_maxPic);
			if(null != pic_name){
				//更新店铺的图片
				restaurantDao.updateRestaurantPic(pic_name,restId);
			}else {
				messages.add("店铺图片必须在 "+rest_maxPic/1024 +"k 以内!");
				return messages;
			}
		}
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		//给店铺添加管理部门
		if(null != restId && null != user && null != user.getDep_id()){
			restaurantDao.setRestaurantDepartment(restId,user.getDep_id());
		}else {
			throw new SQLException();
		}
		return null;
	}

	/**
	 * @throws ParseException
	* @Title: createRestDetailEntity
	* @Description:
	* @param restId
	* @return
	* @return RestaurantDetailEntity
	* @throws
	*/
	private RestaurantDetailEntity createRestDetailEntity(Integer restId) throws ParseException {

		RestaurantDetailEntity restaurantDetail = new RestaurantDetailEntity();
		restaurantDetail.setRest_id(restId);

		restaurantDetail.setOpen_time_am(DateUtils.parseTime(SystemGlobals.getPreference("restaurant.default.am.openTime")));
		restaurantDetail.setClose_time_am(DateUtils.parseTime(SystemGlobals.getPreference("restaurant.default.am.closeTime")));
		restaurantDetail.setOpen_time_pm(DateUtils.parseTime(SystemGlobals.getPreference("restaurant.default.pm.openTime")));
		restaurantDetail.setClose_time_pm(DateUtils.parseTime(SystemGlobals.getPreference("restaurant.default.pm.closeTime")));
		return restaurantDetail;
	}

	/**
	 * @param content 
	 * @throws IOException
	 * @throws IllegalStateException
	 * @throws SQLException
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyRestaurant
	* @Description: 修改店铺信息
	* @param restaurant
	* @param multipartRequest
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyRestaurant(RestaurantEntity restaurant,
			String content, MultipartHttpServletRequest multipartRequest) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException, SQLException, IllegalStateException, IOException {

		List<String> messages = ValidateUtil.validate(restaurant);
		RestaurantEntity rest_= restaurantDao.getRestaurantByAlias(restaurant.getAlias());
		if(messages.size()>0 || null == restaurant.getId() || (null != rest_ && !rest_.getId().equals(restaurant.getId()))){
			messages.add((null != rest_ && !rest_.getId().equals(restaurant.getId())) ? "店铺的别名已经存在！" : "");
			return messages;
		}
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		
		restaurantDao.modifyRestaurant(restaurant);
		//更新店铺介绍
		//restaurantDao.updateRestContent(restaurant.getId(),content);

		Integer restId = restaurant.getId();
		MultipartFile pic = multipartRequest.getFile("rest_pic");
		if(null != pic && StringUtils.isNotBlank(pic.getOriginalFilename())){
			String pic_location = SystemGlobals.getPreference("restaurant.pic.path")+"/"+restId;
			long rest_maxPic = SystemGlobals.getLongPreference("restaurant.pic.maxSize", 102400);
			//删除店铺已经存在的图片
			FileUtils.clearPic(pic_location, restaurant.getPic());
			//保存图片
			String pic_name = FileUtils.savePic(pic, pic_location, rest_maxPic);
			if(null != pic_name){
				//更新店铺的数据库中图片
				restaurantDao.updateRestaurantPic(pic_name,restId);
			}else {
				messages.add("店铺图片必须在 "+rest_maxPic/1024 +"k 以内!");
				return messages;
			}
		}
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: getRestaurantDetail
	* @Description: 获取店铺的详情
	* @param id
	* @return
	* @return String
	* @throws
	*/
	public RestaurantDto getRestaurantDetail(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return restaurantDao.getRestaurantDetail(id);
	}

	/**
	 * @throws SQLException
	* @Title: deleteRestaurant
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteRestaurant(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_restaurant set status = ?");
		sql.append(" where id in ("+ids.trim()+")");
		restaurantDao.modifyStatus(sql.toString(),new Integer[]{DataStatus.DISABLED});
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return messages;
	}

	/**
	 * @throws ParseException
	 * @throws SQLException
	* @Title: setReserveTime
	* @Description:
	* @param ids
	* @param open_time_am
	* @param close_time_am
	* @param open_time_pm
	* @param close_time_pm
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> setReserveTime(String ids, String open_time_am,
			String close_time_am, String open_time_pm, String close_time_pm) throws SQLException, ParseException {
		List<String> messages = new LinkedList<String>();
		if(StringUtils.isBlank(ids)){
			messages.add("请选择要操作的数据!");
			return messages;
		}
		Date close_time_am_ = DateUtils.parseDateTimeByTime(close_time_am);
		Date open_time_am_ = DateUtils.parseDateTimeByTime(open_time_am);
		Date close_time_pm_ = DateUtils.parseDateTimeByTime(close_time_pm);
		Date open_time_pm_ = DateUtils.parseDateTimeByTime(open_time_pm);

		if(DateUtils.compareDateTime(close_time_am_, open_time_am_)
			&& DateUtils.compareDateTime(close_time_pm_, open_time_pm_)
			&& DateUtils.compareDateTime(open_time_pm_,close_time_am_)){

			for(String rest_id_ : ids.split(",")){
				Integer rest_id = Integer.parseInt(rest_id_);
				String sql = "update mf_rest_detail set open_time_am = ? , close_time_am = ?,open_time_pm = ?,close_time_pm = ? where rest_id = ?";
				restaurantDao.setReserveTime(sql,new Object[]{open_time_am,close_time_am,open_time_pm,close_time_pm,rest_id});

				//添加系统任务
				this.addTask(rest_id,open_time_am_,close_time_am_,open_time_pm_,close_time_pm_);
			}
		}else {

			messages.add("打烊时间必须 大于 营业时间<br/>");
			messages.add("上午打烊时间必须 小于 下午营业时间!");
			return messages;
		}
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: addTask 添加系统任务
	* @Description:
	* @param rest_id
	* @param open_time_am_
	* @param close_time_am_
	* @param open_time_pm_
	* @param close_time_pm_
	* @return void
	* @throws
	*/
	private void addTask(Integer rest_id, Date open_time_am_, Date close_time_am_, Date open_time_pm_, Date close_time_pm_) throws SQLException{

		//先清除店铺的打烊或者营业任务
		taskDao.clearTask(rest_id,TaskType.RESTAURANTCLOSE);
		taskDao.clearTask(rest_id,TaskType.RESTAURANTOPEN);

		TaskEntity task = new TaskEntity();
		task.setTarget_id(rest_id);

		task.setTime(open_time_am_);
		task.setType(TaskType.RESTAURANTOPEN);
		taskDao.addTask(task);

		task.setTime(close_time_am_);
		task.setType(TaskType.RESTAURANTCLOSE);
		taskDao.addTask(task);


		task.setTime(open_time_pm_);
		task.setType(TaskType.RESTAURANTOPEN);
		taskDao.addTask(task);


		task.setTime(close_time_pm_);
		task.setType(TaskType.RESTAURANTCLOSE);
		taskDao.addTask(task);
	}
	/**
	 * @param rank
	 * @throws SQLException
	* @Title: setReserveDeliverer
	* @Description:
	* @param ids
	* @param deliverer_id
	* @param region
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> setRestaurantDeliverer(String ids, Integer deliverer_id,
			String building_ids, Integer rank) throws SQLException {
		List<String> messages = new LinkedList<String>();
		if(StringUtils.isEmpty(ids)){
			messages.add("请选择要分配的店铺!");
			return messages;
		}

		if(StringUtils.isNotBlank(building_ids) && null != rank){

			for(String id : ids.split(",")){
				Integer rest_id = Integer.parseInt(id);
				for(String b_id : building_ids.split(",")){
					Integer building_id = Integer.parseInt(b_id);
					restaurantDao.setRestaurantDeliverer(rest_id,building_id,deliverer_id,rank);
				}
			}
		}else {
			//清除店铺的所有配送员
			restaurantDao.clearRestDeliverer(ids);
			//重新添加region下的配送员
			for(String id : ids.split(",")){
				Integer rest_id = Integer.parseInt(id);
				restaurantDao.setRestaurantDeliverer(rest_id,deliverer_id);
			}
		}

		return null;
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurantType
	* @Description:
	* @param id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurantType(Integer id) throws SQLException {
		if(null == id ){
			return null;
		}
		return restaurantDao.listRestaurantType(id);
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurantType_all
	* @Description:
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurantType_all() throws SQLException {

		return restaurantDao.listRestaurantType_all();
	}

	/**
	 * @throws SQLException
	* @Title: setRestaurantType
	* @Description:
	* @param id
	* @param type_ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> setRestaurantType(Integer id, String type_ids) throws SQLException {

		if(null == id || StringUtils.isBlank(type_ids)){
			List<String> messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
			return messages;
		}
		//先清理店铺的类型信息
		restaurantDao.clearRestaurantType(id);
		//添加店铺的类型
		for(String type_id : type_ids.split(",")){

			restaurantDao.setRestaurantType(id,Integer.parseInt(type_id));
		}
		return null;
	}

	/**
	 * @throws SQLException
	* @Title: setRestaurantSettlement
	* @Description:
	* @param ids
	* @param setttlement_type
	* @param discount
	* @param period
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> setRestaurantSettlement(String ids,
			Integer setttlement_type, Double discount, Integer period) throws SQLException {

		if(StringUtils.isBlank(ids) || null == setttlement_type || null == discount || null == period){
			List<String> messages =  new LinkedList<String>();
			messages.add("保存店铺结算方式出错！");
		}
		//先清除店铺的结算信息
		restaurantDao.clearRestaurantSettlement(ids);
		//设置店铺结算信息
		for(String id : ids.split(",")){
			restaurantDao.setRestaurantSettlement(Integer.parseInt(id),setttlement_type,discount,period);
		}
		return null;
	}

	/**
	 * @param integer
	 * @param name
	 * @throws SQLException
	* @Title: listRestaurants_dep
	* @Description:
	* @param dep_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurants_dep(String rest_name, Integer dep_id) throws SQLException {
		if(null == dep_id){
			return null;
		}
		StringBuilder sql = new StringBuilder();
		sql.append("select r.id,r.name from mf_restaurant r where r.id in");
		sql.append(" (select rest_id from mf_dep_restaurant where dep_id in (select id from mf_department where super_dep_id = ? or id = ?))");

		if(StringUtils.isNotEmpty(rest_name)){
			sql.append(" and r.name like '%").append(rest_name).append("%'");
		}

		sql.append(" and r.status <> -1");
		sql.append(" order by r.sort desc");
		return restaurantDao.listRestaurants_dep(sql.toString(),new Integer[]{dep_id,dep_id});
	}

	/**
	 * @throws SQLException
	* @Title: getRestaurantDetail_other
	* @Description:
	* @param id
	* @return
	* @return RestaurantDto
	* @throws
	*/
	public RestaurantDto getRestaurantDetail_other(Integer id) throws SQLException {
		if(null == id){
			return null;
		}
		return restaurantDao.getRestaurantDetail_other(id);
	}

	/**
	 * @throws SQLException
	* @Title: listRestaurant_menus
	* @Description:
	* @param rest_id
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurant_menus(Integer rest_id) throws SQLException {

		if(null == rest_id){
			return null;
		}

		return restaurantDao.listRestaurant_menus(rest_id);
	}

	/**
	 * @param depId
	 * @throws SQLException
	* @Title: listRestaurants_building
	* @Description: 列出某个楼宇下面的送达店铺
	* @param buildingId
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRestaurants_building_dep(Integer buildingId, Integer depId) throws SQLException {

		if(null == buildingId || null == depId){
			return null;
		}

		return restaurantDao.listRestaurants_building_dep(buildingId,depId);
	}

	/**
	 * @throws SQLException 
	* @Title: closeRestaurant
	* @Description: 
	* @param @param ids
	* @param @return 
	* @return List<String> 
	* @throws 
	*/ 
	public List<String> closeRestaurant(String ids,UserDto user) throws SQLException {
		
		if(StringUtils.isBlank(ids) && null != user && null != user.getDep_id()){
			
			ids = this.getRestaurants_dep(user.getDep_id());
		}else if(StringUtils.isBlank(ids) || null == user || null == user.getDep_id()){
			return new LinkedList<String>();
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_restaurant set status = ?");
		sql.append(" where id in ("+ids.trim()+")");
		restaurantDao.modifyStatus(sql.toString(),new Integer[]{RestaurantStatus.CLOSED});
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return null;
	}
	
	
	/**
	* @Title: openRestaurant
	* @Description: 
	* @param @param ids
	* @param @param user
	* @param @return
	* @param @throws SQLException 
	* @return List<String> 
	* @throws 
	*/ 
	public List<String> openRestaurant(String ids,UserDto user) throws SQLException {
		
		if(StringUtils.isBlank(ids) && null != user && null != user.getDep_id()){
			ids = this.getRestaurants_dep(user.getDep_id());
		}else if(StringUtils.isBlank(ids) || null == user || null == user.getDep_id()){
			return new LinkedList<String>();
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_restaurant set status = ?");
		sql.append(" where id in ("+ids.trim()+")");
		restaurantDao.modifyStatus(sql.toString(),new Integer[]{RestaurantStatus.OPENED});
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return null;
	}

	/**
	* @Title: getRestaurants_dep
	* @Description: 
	* @param @param dep_id
	* @param @return
	* @param @throws SQLException 
	* @return String 
	* @throws 
	*/ 
	private String getRestaurants_dep(Integer dep_id) throws SQLException {
		
		StringBuilder ids_ = new StringBuilder();
		List<Integer> restIds = restaurantDao.getRestIds_dep(dep_id);
		if(null != restIds && restIds.size()>0){
			for(Integer restId : restIds){
				ids_.append(restId).append(",");
			}
			ids_.deleteCharAt(ids_.length()-1);
		}
		return ids_.toString();
	}
}

