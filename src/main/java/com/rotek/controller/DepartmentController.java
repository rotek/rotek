/**
* @FileName: DepartmentController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-27 上午10:43:21
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

import com.rotek.dto.DepartmentDto;
import com.rotek.entity.DepartmentEntity;
import com.cta.platform.util.ListPager;
import com.rotek.service.impl.DepartmentService;

/**
 * @ClassName: DepartmentController
 * @Description: 部门信息设置
 * @author chenwenpeng
 * @date 2013-6-27 上午10:43:21
 *
 */
@Controller
@RequestMapping("/admin/department")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;
	/**
	* @Title: toDepartments
	* @Description:
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("toDepartments")
	public String toDepartments(){

		return "admin/department/departments";
	}

	/**
	* @Title: listDepartments
	* @Description:
	* @param @param start
	* @param @param limit
	* @param @param id
	* @param @param dep_name
	* @param @param super_dep_id
	* @param @param memo
	* @param @param sort
	* @param @param status
	* @param @param modelMap
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listDepartments")
	public String listDepartments(
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit,

			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "dep_name", defaultValue = "") String dep_name,
			@RequestParam(value = "super_dep_id", defaultValue = "") Integer super_dep_id,
			@RequestParam(value = "memo", defaultValue = "") String memo,
			@RequestParam(value = "sort", defaultValue = "") Integer sort,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			ModelMap modelMap) throws SQLException{
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		DepartmentEntity department = new DepartmentEntity();
		department.setId(id);
		department.setDep_name(dep_name);
		department.setSuper_dep_id(super_dep_id);
		department.setMemo(memo);
		department.setSort(sort);
		department.setStatus(status);

		List<DepartmentDto> departmentList = departmentService.listDepartments(department,pager);
		modelMap.put("dataList", departmentList);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}


	/**
	* @Title: listDepartments
	* @Description: 列出上级部门的简单信息
	* @param @param start
	* @param @param limit
	* @param @param id
	* @param @param dep_name
	* @param @param super_dep_id
	* @param @param memo
	* @param @param sort
	* @param @param status
	* @param @param modelMap
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listDepartments_super")
	public String listDepartments_super(ModelMap modelMap) throws SQLException{

		List<Map<String,Object>> departmentList = departmentService.listDepartments_super();
		modelMap.put("dataList", departmentList);
		return "jsonView";
	}


	/**
	 * @Title: listDepartments
	 * @Description: 列出部门的简单信息
	 * @param @param start
	 * @param @param limit
	 * @param @param id
	 * @param @param dep_name
	 * @param @param super_dep_id
	 * @param @param memo
	 * @param @param sort
	 * @param @param status
	 * @param @param modelMap
	 * @param @return
	 * @param @throws SQLException
	 * @return String
	 * @throws
	 */
	@RequestMapping("listDepartments_s")
	public String listDepartments_s(ModelMap modelMap) throws SQLException{

		List<Map<String,Object>> departmentList = departmentService.listDepartments();
		modelMap.put("dataList", departmentList);
		return "jsonView";
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addDepartment
	* @Description:
	* @param @param modelMap
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("addDepartment")
	public String addDepartment(
			@RequestParam(value = "dep_name", defaultValue = "") String dep_name,
			@RequestParam(value = "super_dep_id", defaultValue = "") Integer super_dep_id,
			@RequestParam(value = "memo", defaultValue = "") String memo,
			@RequestParam(value = "sort", defaultValue = "") Integer sort,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			ModelMap modelMap) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		DepartmentEntity department = new DepartmentEntity();
		department.setDep_name(dep_name);
		department.setSuper_dep_id(super_dep_id);
		department.setMemo(memo);
		department.setSort(sort);
		department.setStatus(status);

		List<String> messages = departmentService.addDepartment(department);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: getDepartmentDetail
	* @Description:
	* @param @param id
	* @param @param modelMap
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getDepartmentDetail")
	public String getDepartmentDetail(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap modelMap) throws SQLException{

		DepartmentDto department = departmentService.getDepartmentDetail(id);
		modelMap.put("data", department);
		return "jsonView";
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: modifyDepartment
	* @Description:
	* @param @param modelMap
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("modifyDepartment")
	public String modifyDepartment(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "dep_name", defaultValue = "") String dep_name,
			@RequestParam(value = "super_dep_id", defaultValue = "") Integer super_dep_id,
			@RequestParam(value = "memo", defaultValue = "") String memo,
			@RequestParam(value = "sort", defaultValue = "") Integer sort,
			@RequestParam(value = "status", defaultValue = "") Integer status,
			ModelMap modelMap) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		DepartmentEntity department = new DepartmentEntity();
		department.setId(id);
		department.setDep_name(dep_name);
		department.setSuper_dep_id(super_dep_id);
		department.setMemo(memo);
		department.setSort(sort);
		department.setStatus(status);

		List<String> messages = departmentService.modifyDepartment(department);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: deleteDepartment
	* @Description: 删除部门信息
	* @param @param id
	* @param @param modelMap
	* @param @return
	* @param @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteDepartment")
	public String deleteDepartment(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap modelMap) throws SQLException{

		List<String> messages = departmentService.deleteDepartment(ids);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

	/**
	 * @throws SQLException
	* @Title: listDeliverers_dep
	* @Description: 列出部门管理的配送员信息
	* @param @param id
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("listDeliverers_dep")
	public String listDeliverers_dep(
			@RequestParam(value = "id", defaultValue = "") Integer id,
			ModelMap modelMap) throws SQLException{

		List<Integer> deliverIds =departmentService.listDeliverers_dep(id);
		modelMap.put("dataList", deliverIds);
		return "jsonView";
	}

	/**
	 * @throws SQLException
	* @Title: SetDeliverers
	* @Description: 设置配送员
	* @param @param dep_id
	* @param @param deliverer_ids
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("updateDeliverers")
	public String updateDeliverers(
			@RequestParam(value = "dep_id", defaultValue = "") Integer dep_id,
			@RequestParam(value = "deliverer_ids", defaultValue = "") String deliverer_ids,
			ModelMap modelMap) throws SQLException{

		List<String> messages = departmentService.updateDeliverers(dep_id,deliverer_ids);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: listBuilding_dep
	* @Description:列出该部门分配的楼宇信息
	* @param id
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listBuildings_dep")
	public String listBuildings_dep(
			ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException{

		List<Integer> buildingIds =departmentService.listBuildings_dep(id);
		modelMap.put("dataList", buildingIds);
		return "jsonView";
	}

	/**
	 * @throws SQLException
	* @Title: SetDeliverers
	* @Description: 设置楼宇
	* @param @param dep_id
	* @param @param building_ids
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("updateBuildings")
	public String updateBuildings(
			@RequestParam(value = "dep_id", defaultValue = "") Integer dep_id,
			@RequestParam(value = "building_ids", defaultValue = "") String building_ids,
			ModelMap modelMap) throws SQLException{

		List<String> messages = departmentService.updateBuildings(dep_id,building_ids);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: listRestaurants_dep
	* @Description:列出该部门分配的店铺信息
	* @param id
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listRestaurants_dep")
	public String listRestaurants_dep(
			ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException{

		List<Integer> restaurantIds = departmentService.listRestaurants_dep(id);
		modelMap.put("dataList", restaurantIds);
		return "jsonView";
	}

	/**
	 * @throws SQLException
	* @Title: updateRestaurants
	* @Description: 设置部门店铺
	* @param @param dep_id
	* @param @param building_ids
	* @param @param modelMap
	* @param @return
	* @return String
	* @throws
	*/
	@RequestMapping("updateRestaurants")
	public String updateRestaurants(
			@RequestParam(value = "dep_id", defaultValue = "") Integer dep_id,
			@RequestParam(value = "restaurant_ids", defaultValue = "") String restaurant_ids,
			ModelMap modelMap) throws SQLException{

		List<String> messages = departmentService.updateRestaurants(dep_id,restaurant_ids);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

}
