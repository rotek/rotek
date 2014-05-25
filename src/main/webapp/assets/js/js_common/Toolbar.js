/**@description 封装前台toolbar
 * @author chenwenpeng
 * @time 2013-3-28 下午03:33:41
 */

Ext.ns("CTA.common");
CTA.common.Toolbar = Ext.extend(Ext.Toolbar,{
	buttonShow : {
		add : false,
		drop : false,
		modify : false,
		query : false,
		save : false,
		cancle : false,
		viewDetail : false,
		setDepartmentDeliverer : false,
		setRestaurantDeliverer : false,
		setDepartment : false,
		setRestaurant : false,
		setReserveTime : false,
		setRestaurantType : false,
		setRestaurantSettlement : false,
		setStatus : false,
		exportData : false,
		setRestaurantDelivery : false,
		reply : false,
		closeRest : false,
		openRest : false
	},
	constructor : function(config){
		config = Ext.apply({
			region : 'north',
			//如果region类型为north，那么必须制定高度，如果不指定不显示
			height : 28,
			buttonShow : {}
		},{
			buttonShow : config
		});
		CTA.common.Toolbar.superclass.constructor.call(this,config);
	},
	regAddHandler : function(func){
		this._regButtonHandler(func,this.buttonShow.add,basePath+"/assets/images/toolbar/add.png","添加");
	},
	regDropHandler : function(func){
		this._regButtonHandler(func,this.buttonShow.drop,basePath+"/assets/images/toolbar/drop.png","删除");
	},
	regModifyHandler : function(func){
		this._regButtonHandler(func,this.buttonShow.modify,basePath+"/assets/images/toolbar/modify.png","修改");
	},
	regQueryHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.query, basePath+"/assets/images/toolbar/query.png", "查询");
	},
	regSetDepartmentDelivererHandler : function(func){
		this._regButtonHandler(func,this.buttonShow.setDepartmentDeliverer,basePath+"/assets/images/toolbar/add_deliverer_department.png","分配部门配送员");
	},
	regSetBuildingHandler : function(func){
		this._regButtonHandler(func,this.buttonShow.setBuilding,basePath+"/assets/images/toolbar/add_building.png","分配楼宇");
	},
	regSetRestaurantHandler : function(func){
		this._regButtonHandler(func,this.buttonShow.setRestaurant,basePath+"/assets/images/toolbar/add_restaurant.png","分配店铺");
	},
	regViewDetailHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.viewDetail, basePath+"/assets/images/toolbar/query_detail.png", "查看详情");
	},
	regSetReserveTimeHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.setReserveTime, basePath+"/assets/images/toolbar/setReserveTime.png", "设置店铺营业时间");
	},
	regSetRestaurantDelivererHandler : function(func){
		this._regButtonHandler(func,this.buttonShow.setRestaurantDeliverer,basePath+"/assets/images/toolbar/add_deliverer_restaurant.png","添加工作日志");
	},
	regSetRestaurantTypeHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.setRestaurantType, basePath+"/assets/images/toolbar/setRestaurantType.png", "设置店铺类型");
	},
	regSetRestaurantSettlementHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.setRestaurantSettlement, basePath+"/assets/images/toolbar/setRestSettlement.png", "设置店铺结算方式");
	},
	regSetStatusHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.setStatus, basePath+"/assets/images/toolbar/setStatus.png", "修改状态");
	},
	regExportDataHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.exportData, basePath+"/assets/images/toolbar/export.png", "导出");
	},
	regSetRestaurantDeliveryHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.setRestaurantDelivery, basePath+"/assets/images/toolbar/setRestaurant_delivery.png", "设置送达店铺");
	},
	regReplyHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.reply, basePath+"/assets/images/toolbar/reply.png", "回复");
	},
	regCloseRestHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.closeRest, basePath+"/assets/images/toolbar/setRestClosed.png", "打样");
	},
	regOpenRestHandler : function(func){
		this._regButtonHandler(func, this.buttonShow.openRest, basePath+"/assets/images/toolbar/setRestOpen.png", "营业");
	},
	_regButtonHandler : function(func,isShow,icon,text){
		if(isShow){
			this.add({
				icon : icon,
				cls : 'x-btn-text-icon',
				style : 'margin:0px 10px 0px 0px',
				text : text,
				handler : func
			});
		}
	}
});