/**
* @FileName: RegionService.java
* @Package com.rotek.service.impl
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-5 下午04:47:49
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
import com.rotek.dao.impl.RegionDao;
import com.rotek.entity.RegionEntity;
import com.cta.platform.util.ListPager;
import com.cta.platform.util.ValidateUtil;

/**
 * @ClassName: RegionService
 * @Description: 区域
 * @author chenwenpeng
 * @date 2013-7-5 下午04:47:49
 *
 */
@Service
public class RegionService {

	@Autowired
	private RegionDao regionDao;

	/**
	 * @throws SQLException
	* @Title: listRegions_s
	* @Description:
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRegions_s() throws SQLException {

		return regionDao.listRegions_s();
	}


	/**
	* @Title: listRegions_sort
	* @Description:
	* @return
	* @throws SQLException
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<Map<String, Object>> listRegions_sort() throws SQLException {

		return regionDao.listRegions_sort();
	}

	/**
	* @Title: listRegions
	* @Description:
	* @param pager
	* @return
	* @return List<Map<String,Object>>
	* @throws
	*/
	public List<RegionEntity> listRegions(ListPager pager) throws SQLException {

		return regionDao.listRegions(pager);
	}


	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	* @Title: addRegion
	* @Description:
	* @param region
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> addRegion(RegionEntity region) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		if(null != region && null == region.getSort()){
			region.setSort(DataStatus.NONE_INT);
		}
		List<String> messages = ValidateUtil.validate(region);
		if(messages.size()>0){
			return messages;
		}

		regionDao.addBuilding(region);
		return null;
	}


	/**
	 * @throws SQLException
	* @Title: getRegionDetail
	* @Description:
	* @param id
	* @return
	* @return RegionEntity
	* @throws
	*/
	public RegionEntity getRegionDetail(Integer id) throws SQLException {

		if(null == id){
			return null;
		}

		return regionDao.getRegionDetail(id);
	}


	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws SQLException
	* @Title: modifyRegion
	* @Description:
	* @param region
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> modifyRegion(RegionEntity region) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {

		List<String> messages = ValidateUtil.validate(region);
		if(messages.size()>0 || null == region.getId()){
			return messages;
		}
		//比对一下排序信息，如果修改了就-1
		RegionEntity region_ = regionDao.getRegionDetail(region.getId());
		region.setSort(null != region_ && region_.getSort().equals(region.getSort()) ? region.getSort() : region.getSort() - 1);

		regionDao.modifyRegion(region);
		return null;
	}


	/**
	 * @throws SQLException
	* @Title: deleteRegion
	* @Description:
	* @param ids
	* @return
	* @return List<String>
	* @throws
	*/
	public List<String> deleteRegion(String ids) throws SQLException {

		List<String> messages = null;
		if(StringUtils.isBlank(ids)){
			messages = new LinkedList<String>();
			messages.add("请选择您要操作的数据!");
		}
		StringBuilder sql = new StringBuilder();
		sql.append("update mf_region set status = ").append(DataStatus.DISABLED);
		sql.append(" where id in ("+ids.trim()+")");
		regionDao.deleteRegion(sql.toString());
		return messages;
	}
}
