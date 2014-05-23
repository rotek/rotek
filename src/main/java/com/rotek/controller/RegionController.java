/**
* @FileName: RegionController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-5 下午04:47:02
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

import com.rotek.constant.DataStatus;
import com.rotek.entity.RegionEntity;
import com.cta.platform.util.ListPager;
import com.rotek.service.impl.RegionService;

/**
 * @ClassName: RegionController
 * @Description: 区域
 * @author chenwenpeng
 * @date 2013-7-5 下午04:47:02
 *
 */
@Controller
@RequestMapping("/admin/region")
public class RegionController {
	@Autowired
	private RegionService regionService;

	/**
	* @Title: toRegions
	* @Description:
	* @return
	* @return String
	* @throws
	*/
	@RequestMapping("toRegions")
	public String toRegions(){

		return "admin/region/regions";
	}

	/**
	* @Title: listRegions
	* @Description:
	* @param modelMap
	* @param start
	* @param limit
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listRegions")
	public String listRegions(ModelMap modelMap,
			@RequestParam(value = "start", defaultValue = "0") Integer start,
			@RequestParam(value = "limit", defaultValue = "15") Integer limit) throws SQLException{
		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		List<RegionEntity> regions = regionService.listRegions(pager);

		modelMap.put("dataList", regions);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}

	/**
	* @Title: addRegion
	* @Description: 添加区域
	* @param modelMap
	* @param region
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	@RequestMapping("addRegion")
	public String addRegion(ModelMap modelMap,RegionEntity region) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		System.out.println(region.getName());
		region.setCity_id(DataStatus.NONE_INT);
		region.setSort(null == region.getSort() ? DataStatus.NONE_INT : region.getSort() - 1);
		List<String> messages = regionService.addRegion(region);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: getRegionDetail
	* @Description:
	* @param modelMap
	* @param id
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getRegionDetail")
	public String getRegionDetail(ModelMap modelMap,
			@RequestParam(value = "id", defaultValue = "") Integer id) throws SQLException{

		RegionEntity region = regionService.getRegionDetail(id);
		System.out.println(region.getName());
		modelMap.put("data", region);
		return "jsonView";
	}


	/**
	* @Title: modifyRegion
	* @Description:
	* @param modelMap
	* @param region
	* @return
	* @throws SQLException
	* @throws IllegalAccessException
	* @throws InvocationTargetException
	* @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	@RequestMapping("modifyRegion")
	public String modifyRegion(ModelMap modelMap,RegionEntity region) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		List<String> messages = regionService.modifyRegion(region);
		System.out.println(region.getName());
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: deleteRegion
	* @Description:
	* @param ids
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteRegion")
	public String deleteRegion(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			ModelMap modelMap) throws SQLException{

		List<String> messages = regionService.deleteRegion(ids);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}



	/**
	* @Title: listBuildings_s
	* @Description: 列出区划的简单信息
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listRegions_s")
	public String listRegions_s(ModelMap modelMap) throws SQLException{

		List<Map<String,Object>> regions = regionService.listRegions_s();
		modelMap.put("dataList", regions);
		return "jsonView";
	}


	/**
	* @Title: listRegions_s
	* @Description:
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listRegions_sort")
	public String listRegions_sort(ModelMap modelMap) throws SQLException{

		List<Map<String,Object>> regions = regionService.listRegions_sort();
		modelMap.put("dataList", regions);
		return "jsonView";
	}
}
