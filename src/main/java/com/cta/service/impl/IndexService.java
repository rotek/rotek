/**
 * @FileName: IndexService.java
 * @Package com.cta.service
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-18 下午04:09:38
 * @version V1.0
 */
package com.cta.service.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cta.dao.impl.IndexDao;
import com.cta.entity.BuildingEntity;
import com.cta.entity.RegionEntity;

/**
 * @ClassName: IndexService
 * @Description: 首页的业务逻辑
 * @author chenwenpeng
 * @date 2013-5-18 下午04:09:38
 *
 */
@Service
public class IndexService {

	@Autowired
	private IndexDao indexDao;

	/**
	 * @param regions 
	 * @param buildings_gr2
	 * @throws SQLException
	 * @param buildings_gr
	 * @param buildings_hot
	 * @param buildings_all
	 * @Title: listBuildings
	 * @Description: 列出所有的楼宇
	 * @param @return
	 * @return List<BuildingEntity>
	 * @throws
	 */
	public void listBuildings(List<RegionEntity> regions, Map<String,List<BuildingEntity>> buildings,
			List<BuildingEntity> buildings_hot) throws SQLException {

		List<BuildingEntity> buildings_all = null;
		List<RegionEntity> _regions = null;
		
		buildings_all = null;
		_regions = null;
		if (null == buildings_all || null == _regions) {
			String sql = "select id,name,alias,region_id,order_count from mf_building where status = 1 order by status,order_count desc";
			buildings_all = indexDao.listBuildings(sql);
			sql = "select id, name from mf_region where status = 1 order by sort desc";
			_regions = indexDao.listRegions(sql);
		}
		//迭代去除所有区域
		for(RegionEntity region : _regions){
			regions.add(region);
			buildings.put(region.getId()+"", new ArrayList<BuildingEntity>());
		}
		//迭代取出普通楼宇，热门楼宇(前20个楼宇)，团餐楼宇
		int j = 0;
		for (int i = 0; i < buildings_all.size(); i++) {
			BuildingEntity building = buildings_all.get(i);
			List<BuildingEntity> _buildings = buildings.get(building.getRegion_id()+"");
			if(null != _buildings){
				_buildings.add(building);
			}
			if (j <= 20) {
				buildings_hot.add(building);
			}
			j++;
		}
	}
}
