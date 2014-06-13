/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.cta.platform.constant;

/**
 * @ClassName: CacheName
 * @Description: 缓存的命名
 * @author chenwenpeng
 * @date 2013-5-23 下午8:23:21
 */
public class CacheName {

	/** The String CATCHE_BUILDINGS*/
	public static final String CACHE_BUILDINGS = "buildings";
	/** The String CATCHE_BUILDING*/
	public static final String CACHE_BUILDING = "building_";
	/** The String CATCHE_REGIONS*/
	public static final String CACHE_REGIONS = "regions";
	/** The String CATCHE_RESTAURANTS 某个区域下面所有店铺的缓存*/
	public static final String CACHE_RESTAURANTS = "restaurant_list_";
	/** The String CATCHE_RESTAURANT 店铺的缓存*/
	public static final String CACHE_RESTAURANT = "restaurant";
	/** The String CACHE_RESTAURANTS_CATRGORY 店铺分类信息的缓存*/
	public static final String CACHE_RESTAURANTS_CATRGORY = "restaurant_category_list_";
	/** The String CACHE_RESTAURANT_MENULIST 店铺菜单列表*/
	public static final String CACHE_RESTAURANT_MENULIST = "restaurant_menu_list_";
	/** The String CACHE_RESTAURANT_MENULIST_RECOMMEND 店长推荐菜单*/
	public static final String CACHE_RESTAURANT_MENULIST_RECOMMEND = "restaurant_menu_list_recommend_";
	/** The String CACHE_BUILDING_NEWSLIST*/
	public static final String CACHE_BUILDING_LATEST_NEWSLIST = "building_news_list_";
	/** The String CACHE_BUILDING_NEWS 某个楼宇下面的所有新闻*/
	public static final String CACHE_BUILDING_NEWSLIST = "building_news_list_";
	/** The String CACHE_BUILDING_NEWS 某个楼宇下面的所有轮播信息*/
	public static final String CACHE_BUILDING_BROADCAST = "building_broadcast_list_";
	
}
