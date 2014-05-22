/**
* @FileName: BuildingController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-4 上午09:26:46
* @version V1.0
*/
package com.rotek.controller;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.dto.BuildingDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.BuildingEntity;
import com.rotek.platform.util.ListPager;
import com.rotek.service.impl.BuildingService;
import com.danga.MemCached.MemCachedClient;

/**
 * @ClassName: BuildingController
 * @Description: 楼宇的controller
 * @author chenwenpeng
 * @date 2013-7-4 上午09:26:46
 *
 */
@Controller
@RequestMapping("/admin/building")
public class BuildingController {

	@Autowired
	private BuildingService buildingService;

	/**
	* @Title: toBuildings
	* @Description:
	* @return
	* @return String
	* @throws
	*/
	@RequestMapping("toBuildings")
	public String toBuildings(){

		return "admin/building/buildings";
	}

	/**
	* @Title: listBuildings_s
	* @Description: 列出楼宇的信息
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listBuildings")
	public String listBuildings(ModelMap modelMap,UserDto user,
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "alias", defaultValue = "") String alias,
			@RequestParam(value = "region_id", defaultValue = "") Integer region_id,
			@RequestParam(value = "dep_id", defaultValue = "") Integer dep_id,
			@RequestParam(value = "status", defaultValue = "") Integer status) throws SQLException{
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		BuildingDto buildingEntity = new BuildingDto();
		buildingEntity.setId(id);
		buildingEntity.setName(name);
		buildingEntity.setAlias(alias);
		buildingEntity.setRegion_id(region_id);
		buildingEntity.setDep_id(dep_id);
		buildingEntity.setStatus(status);

		List<BuildingDto> buildings = buildingService.listBuildings(buildingEntity,user,pager);
		modelMap.put("dataList", buildings);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @Title: listBuildings_s
	* @Description: 列出楼宇的简单信息(有用信息)
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listBuildings_s")
	public String listBuildings_s(ModelMap modelMap,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "range", defaultValue = "0") Integer range) throws SQLException{

		List<Map<String,Object>> buildings = buildingService.listBuildings_s(name,range);
		modelMap.put("dataList", buildings);
		return "jsonView";
	}

	/**
	 * @Title: listBuildings_dep
	 * @Description: 根据部门信息，列出受这个部门管理的楼宇信息
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 * @return String
	 * @throws
	 */
	@RequestMapping("listBuildings_dep")
	public String listBuildings_dep(ModelMap modelMap,UserDto user,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "range", defaultValue = "0") Integer range) throws SQLException{

		List<Map<String,Object>> buildings = buildingService.listBuildings_dep(user.getDep_id());
		modelMap.put("dataList", buildings);
		return "jsonView";
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addBuilding
	* @Description: 添加楼宇的信息
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("addBuilding")
	public String addBuilding(ModelMap modelMap,UserDto user,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "alias", defaultValue = "") String alias,
			@RequestParam(value = "address", defaultValue = "") String address,
			@RequestParam(value = "region_id", defaultValue = "") Integer region_id,
			@RequestParam(value = "status", defaultValue = "") Integer status) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setName(name.trim());
		buildingEntity.setAlias(alias.trim());
		buildingEntity.setAddress(address);
		buildingEntity.setRegion_id(region_id);
		buildingEntity.setStatus(status);
		buildingEntity.setOrder_count(0);

		List<String> messages = buildingService.addBuilding(buildingEntity,user);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return "jsonView";
	}

	/**
	* @Title: getBuildingDetail
	* @Description: 列出楼宇的详细信息
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getBuildingDetail")
	public String getBuildingDetail(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException{

		BuildingEntity building = buildingService.getBuildingDetail(id);
		modelMap.put("data", building);
		return "jsonView";
	}


	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Title: addBuilding
	 * @Description: 修改楼宇的信息
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 * @return String
	 * @throws
	 */
	@RequestMapping("modifyBuilding")
	public String modifyBuilding(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "alias", defaultValue = "") String alias,
			@RequestParam(value = "address", defaultValue = "") String address,
			@RequestParam(value = "region_id", defaultValue = "") Integer region_id,
			@RequestParam(value = "order_count", defaultValue = "") Integer order_count,
			@RequestParam(value = "status", defaultValue = "") Integer status) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setId(id);
		buildingEntity.setName(name.trim());
		buildingEntity.setAlias(alias.trim());
		buildingEntity.setAddress(address);
		buildingEntity.setRegion_id(region_id);
		buildingEntity.setStatus(status);
		buildingEntity.setOrder_count(order_count);

		List<String> messages = buildingService.modifyBuilding(buildingEntity);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return "jsonView";
	}


	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @Title: queryBuilding
	 * @Description: 查询楼宇的信息
	 * @param modelMap
	 * @return
	 * @throws SQLException
	 * @return String
	 * @throws
	 */
	@RequestMapping("queryBuilding")
	public String queryBuilding(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "name", defaultValue = "") String name,
			@RequestParam(value = "alias", defaultValue = "") String alias,
			@RequestParam(value = "address", defaultValue = "") String address,
			@RequestParam(value = "region_id", defaultValue = "") Integer region_id,
			@RequestParam(value = "order_count", defaultValue = "") Integer order_count,
			@RequestParam(value = "status", defaultValue = "") Integer status) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		BuildingEntity buildingEntity = new BuildingEntity();
		buildingEntity.setId(id);
		buildingEntity.setName(name.trim());
		buildingEntity.setAlias(alias.trim());
		buildingEntity.setAddress(address);
		buildingEntity.setRegion_id(region_id);
		buildingEntity.setStatus(status);
		buildingEntity.setOrder_count(order_count);

		List<String> messages = buildingService.modifyBuilding(buildingEntity);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: deleteBuilding
	* @Description: 删除楼宇信息
	* @param @param id
	* @param @param modelMap
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteBuilding")
	public String deleteBuilding(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap modelMap) throws SQLException{

		List<String> messages = buildingService.deleteBuilding(ids);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return "jsonView";
	}


	/**
	* @Title: setRestaurantsDelivery
	* @Description: 设置送达店铺
	* @param building_id
	* @param restaurant_ids
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("setBuildingRestaurants")
	public String setBuildingRestaurants(
			@RequestParam(value = "building_id", defaultValue = "") Integer building_id,
			@RequestParam(value = "restaurant_ids", defaultValue = "") String restaurant_ids,
			ModelMap modelMap) throws SQLException{

		List<String> messages = buildingService.setBuildingRestaurants(building_id,restaurant_ids);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		
		//清除前台的缓存数据 TODO
		MemCachedClient mc = new MemCachedClient();
		mc.flushAll();
		return "jsonView";
	}

}
