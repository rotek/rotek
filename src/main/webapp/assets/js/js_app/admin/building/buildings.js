//设置楼宇信息
Ext.ns("CTA.building");
CTA.building.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/building/listBuildings",
		    dataList:[{
		          index:'id',
		          header:'楼宇ID'
		      },{
		          index:'name',
		          header:'楼宇名称'
		      },{
		        index:'alias',
		        header:'楼宇别名'
		      },{
		    	  index:'address',
		    	  header:'具体地址'
		      },{
		    	  index:'order_count',
		    	  header:'楼宇订单数'
		      },{
		    	  index:'dep_name',
		    	  header:'管理部门',
		    	  renderer:function(value){
			          if(!value){
			            return "<span style='color:red;'>未分配</span>";
			          }else{
			            return value;
			          }
			        }
		      },{
		        index:'status',
		        header:'楼宇状态',
		        renderer:function(value){
		          if(1==value){
		            return "<span style='color:green;'>有效</span>";
		          }else{
		            return "<span style='color:red'>无效</span>";
		          }
		        }
		      }]
	},
	url : {
		addUrl : basePath + "/admin/building/addBuilding",
		detailUrl : basePath + "/admin/building/getBuildingDetail",
		modifyUrl : basePath + "/admin/building/modifyBuilding",
		dropUrl : basePath + "/admin/building/deleteBuilding",
		queryUrl : basePath + "/admin/building/queryBuilding",

		listRegionsUrl : basePath + "/admin/region/listRegions_s",
		listDepartmentsUrl : basePath + "/admin/department/listDepartments_s",

		listRestaurants_building_dep : basePath + "/admin/restaurant/listRestaurants_building_dep",
		listRestaurants_dep : basePath + "/admin/restaurant/listRestaurants_dep",
		setBuildingRestaurants : basePath + "/admin/building/setBuildingRestaurants"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.building.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.building.params.url.addUrl
          });
        }
    };
	  //定义添加的窗口
	  var addWindow = new CTA.common.SaveWindow({
		id : 'addWindow',
	    width : 500,
	    height : 320,
	    layout : 'fit',
	    handler : saveHandler
	  });

  //定义添加窗口中的form
  var formPanel = new CTA.common.SFormPanel({
    items : [{
        fieldLabel : '楼宇名称',
        emptyText : '请输入楼宇名称',
        name : 'name',
        minLength:1,
        maxLength: 50
      },{
        fieldLabel : '楼宇别名',
        emptyText : '请输入楼宇别名',
        name : 'alias',
        vtype : 'en_only',
        minLength:1,
        maxLength: 50
      },{
    	  fieldLabel : '楼宇地址',
    	  emptyText : '请输入楼宇地址',
    	  name : 'address',
    	  minLength:1,
    	  maxLength: 100
      },{
			xtype : 'combo',
			fieldLabel : '所属区域',
			emptyText : '点击设置楼宇所属区域',
			name : 'region_id',
			hiddenName : 'region_id',
			triggerAction : 'all',
			displayField : 'name',
			valueField : 'id',
			editable : false,
			store : new Ext.data.Store({
				reader : new Ext.data.JsonReader({
					root : 'dataList',
					fields : [ {
						name : 'id'
					}, {
						name : 'name'
					} ]
				}),
				proxy : new Ext.data.HttpProxy({
					url : CTA.building.params.url.listRegionsUrl
				})
			})
		},{
	        xtype : 'combo',
	        fieldLabel : '楼宇状态',
	        emptyText : '请选择楼宇状态',
	        name : 'status',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["启用", "1"],["禁用", "-1"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        hiddenName : 'status',
	        mode : 'local',
	        editable : false
	      }]
  });
  addWindow.add(formPanel);
  addWindow.show();
});

//修改信息
toolbar.regModifyHandler(function(){
  var selections = gridPanel.getSelectionModel().getSelections();
  if(!selections || selections.length <= 0){
    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
    return;
  }
  var id = selections[0].get("id");
  Ext.Ajax.request({
    url : CTA.building.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
    	    items : [{
    	    	xtype : 'hidden',
    	        fieldLabel : 'ID',
    	        name : 'id',
    	      },{
    	        fieldLabel : '楼宇名称',
    	        emptyText : '请输入楼宇名称',
    	        name : 'name',
    	        minLength:1,
    	        maxLength: 50
    	      },{
    	        fieldLabel : '楼宇别名',
    	        emptyText : '请输入楼宇别名',
    	        name : 'alias',
    	        vtype : 'en_only',
    	        minLength:1,
    	        maxLength: 50
    	      },{
    	    	  xtype : 'numberfield',
    	    	  fieldLabel : '订单总数',
    	    	  emptyText : '请输入订单总数(无特殊需求请不要修改)',
    	    	  name : 'order_count'
    	      },{
    	    	  fieldLabel : '楼宇地址',
    	    	  emptyText : '请输入楼宇地址',
    	    	  name : 'address',
    	    	  minLength:1,
    	    	  maxLength: 100
    	      },{
    				xtype : 'combo',
    				id : 'building_region',
    				fieldLabel : '所属区域',
    				emptyText : '点击设置楼宇所属区域',
    				name : 'region_id',
    				hiddenName : 'region_id',
    				triggerAction : 'all',
    				displayField : 'name',
    				valueField : 'id',
    				editable : false,
    				store : new Ext.data.Store({
    					reader : new Ext.data.JsonReader({
    						root : 'dataList',
    						fields : [ {
    							name : 'id'
    						}, {
    							name : 'name'
    						} ]
    					}),
    					proxy : new Ext.data.HttpProxy({
    						url : CTA.building.params.url.listRegionsUrl
    					})
    				}),
					autoLoad : true,
					listeners : {
						load : function (){
							Ext.getCmp('building_region').setValue(data.region_id);
						}
					}
    			},{
    		        xtype : 'combo',
    		        fieldLabel : '楼宇状态',
    		        emptyText : '请选择楼宇状态',
    		        name : 'status',
    		        triggerAction : 'all',
    		        store : new Ext.data.SimpleStore({
    		          fields : ['label', 'value'],
    		          data : [["启用", "1"],["禁用", "-1"]]
    		        }),
    		        displayField : 'label',
    		        valueField : 'value',
    		        hiddenName : 'status',
    		        mode : 'local',
    		        editable : false
    		      }],
    		      data : data
    	  });

      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
    	width : 500,
		height : 320,
		layout : 'border',
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
            	CTA.common.Mask.showMask({target:'updateWindow'});
                  formPanel.commit({
                    url : CTA.building.params.url.modifyUrl
                  });
            }
        }
      });

      updateWindow.show();
    },
    failure : function() {
      CTA.common.Mask.hideMask();
      Ext.Msg.alert('提示', '操作失败!');
    }
  });
});

//查询
toolbar.regQueryHandler(function(){

	  var formPanel = new CTA.common.SFormPanel({
  	    items : [{
  	    	xtype : 'numberfield',
  	    	fieldLabel : '楼宇ID',
	        emptyText : '请输入楼宇名称',
	        name : 'id',
	        allowBlank : true
	      },{
	        fieldLabel : '楼宇名称',
	        emptyText : '请输入楼宇名称',
	        name : 'name',
	        allowBlank : true,
	        minLength:1,
	        maxLength: 50
	      },{
	        fieldLabel : '楼宇别名',
	        emptyText : '请输入楼宇别名',
	        name : 'alias',
	        vtype : 'en_only',
	        minLength:1,
	        maxLength: 50,
	        allowBlank : true
	      },{
	    	  fieldLabel : '楼宇地址',
	    	  emptyText : '请输入楼宇地址',
	    	  name : 'address',
	    	  minLength:1,
	    	  maxLength: 100,
	    	  allowBlank : true
	      },{
				xtype : 'combo',
				id : 'department',
				fieldLabel : '所属部门',
				editable : false,
				emptyText : '点击选择所属部门',
				name : 'dep_id',
				hiddenName : 'dep_id',
				triggerAction : 'all',
				displayField : 'dep_name',
				valueField : 'id',
				allowBlank : true,
				store : new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'dataList',
						fields : [ {
							name : 'id'
						}, {
							name : 'dep_name'
						} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : CTA.building.params.url.listDepartmentsUrl
					}),
					autoLoad : true,
					listeners : {
						load : function (){
							Ext.getCmp('department').setValue(undefined === CTA.common.Constant.queryParams.dep_id ? '' : CTA.common.Constant.queryParams.dep_id);
						}
					}
				})
			},{
				xtype : 'combo',
				id : 'building_region',
				fieldLabel : '所属区域',
				emptyText : '点击设置楼宇所属区域',
				name : 'region_id',
				hiddenName : 'region_id',
				triggerAction : 'all',
				displayField : 'name',
				valueField : 'id',
				editable : false,
				store : new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'dataList',
						fields : [ {
							name : 'id'
						}, {
							name : 'name'
						} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : CTA.building.params.url.listRegionsUrl
					}),
					autoLoad : true,
					listeners : {
						load : function (){
							Ext.getCmp('building_region').setValue(undefined === CTA.common.Constant.queryParams.region_id ? '' : CTA.common.Constant.queryParams.region_id);
						}
					}
				}),
				allowBlank : true
			},{
		        xtype : 'combo',
		        fieldLabel : '楼宇状态',
		        emptyText : '请选择楼宇状态',
		        name : 'status',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["启用", "1"],["禁用", "-1"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        hiddenName : 'status',
		        mode : 'local',
		        editable : false,
		        allowBlank : true
		      }],
		      data : CTA.common.Constant.queryParams
	  });

	var queryHandler = function(){
		var params = formPanel.getForm().getFieldValues() ;
		Ext.apply(CTA.common.Constant.queryParams,params);

		gridPanel.getStore().reload();
		gridPanel.doLayout();
	};
	// 查询窗口
	var queryWindow = new CTA.common.QueryWindow({
		width : 500,
		height : 300,
		layout : 'border',
		closeAction: 'hide',
		items : [formPanel],
		handler : queryHandler
	});

	queryWindow.show();
});


//删除
toolbar.regDropHandler(function(){

	var selections = gridPanel.getSelectionModel().getSelections();
	if(!selections || selections.length <= 0){
		Ext.Msg.alert('提示', '请选择您要操作的数据!');
		return;
	}
	var ids = [];
	Ext.each(selections,function(item){
		ids.push(item.get("id"));
	});

	Ext.Msg.confirm("警告","您确定删除这  "+ids.length+" 条数据吗？",function(button){
	    if('yes' == button){
	    	CTA.common.Mask.showMask();
	    	CTA.common.Ajax.request({
	    		url : CTA.building.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});

//设置送达店铺
toolbar.regSetRestaurantDeliveryHandler(function(){

	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }
	  var id = selections[0].get("id");
	  Ext.Ajax.request({
	    url : CTA.building.params.url.listRestaurants_building_dep,
	    params : {id : id},
	    success : function(response) {
		      var dataList = Ext.util.JSON.decode(response.responseText).dataList;

		      var store = new Ext.data.Store({
		    	autoLoad : true,
		  	    proxy:new Ext.data.HttpProxy({
		  	        url : CTA.building.params.url.listRestaurants_dep//列出所有可分配店铺ID
		  	    }),
		  	    // baseParams:{limit:20},
		  	    reader:new Ext.data.JsonReader({
		  	        root:'dataList',
		  	        id:'reader_'
		  	    }, Ext.data.Record.create([
		  	        {
		  	            name:'id',
		  	            type:'int'
		  	        },{
		  	        	name:'name',
		  	        	type:'string'
		  	        }
		  	    ]))
		  	});

		   var sm = new Ext.grid.CheckboxSelectionModel();
		   var cm = new Ext.grid.ColumnModel([sm,{
	               header:'店铺ID',
	               dataIndex:'id',
	               renderer : function(v, metaData, record, rowIndex, colIndex, store){
	              	   var restaurant_id = record.data.id;
	              	   for(var i = 0;i<dataList.length;i++){
	              		   var restaurant_id_ = dataList[i].id;
	              		   if(restaurant_id == restaurant_id_){
	              			   restaurantsGrid.getSelectionModel().selectRow(rowIndex,true);
	              			   return restaurant_id;
	              		   }
	              	   }
	              	   return restaurant_id;
	                 }
     },{
  	   header:'店铺名称',
  	   dataIndex:'name'
   }]);

		   var restaurantsGrid = new Ext.grid.GridPanel({
		  	   region : 'center',
		       store: store,
		       cm: cm,
		       sm:sm,
		       bodyStyle : 'overflow-x:hidden; overflow-y:scroll',
		       width : 300,
		       height: 100,
		       frame: true,
		       view:new Ext.grid.GridView({
		           forceFit:true
		       }),
		       tbar:[{
		    	   	id : 'restaurant_name',
		    	   	xtype : 'textfield',
		    	   	width : '100%',
		    	   	emptyText : '请输入店铺名字',
		    	   	label : '查询'
		        }, '-', '-',{
		            text:'查询',
		            icon : basePath+"/assets/images/toolbar/query.png",
		            listeners : {
		            	"click" : function(){
		            		//查询数据的范围
		            			restaurantsGrid.getStore().baseParams = {
		            			name : Ext.getCmp('restaurant_name').getValue()
		            		};
		            		restaurantsGrid.getStore().reload();
		            	}
		            }
		        }, '-', {
		            text:'刷新',
		            icon : basePath+"/assets/images/toolbar/refresh.gif",
		            listeners : {
		            	"click" : function(){
		            			restaurantsGrid.getStore().baseParams = {
		            			name : ''
		            		};
		            			restaurantsGrid.getStore().reload();
		            	}
		            }
		        }]
		   });

		  	var saveHandler = function(){
		          //检查是否选择好
		  		var selections = restaurantsGrid.getSelectionModel().getSelections();
		  		if(!selections || selections.length <= 0){
		  				Ext.Msg.alert('提示', '请选择您要分配的楼宇信息!');
		  		    	return;
		  		}
		  		var restaurant_ids = [];
		  		Ext.each(selections,function(value){
		  			restaurant_ids.push(value.get("id"));
		  		});
	            CTA.common.Mask.showMask({target:'addWindow'});
	            CTA.common.Ajax.request({
		    		url : CTA.building.params.url.setBuildingRestaurants,
		    		params : {
		    			building_id : id,
		    			restaurant_ids : restaurant_ids.toString()
		    		}
		    	});
		      };
		  	  //定义添加的窗口
		  	  var addWindow = new CTA.common.SaveWindow({
		  		id : 'addWindow',
		  		title : '分配楼宇 送达店铺',
		  	    width : 550,
		  	    height : 450,
		  	    layout : 'border',
		  	    handler : saveHandler,
		  	    items : [restaurantsGrid]
		  	  });
		  	  addWindow.show();
	    },
	    failure : function() {
	        CTA.common.Mask.hideMask();
	        Ext.Msg.alert('提示', '获取信息失败!');
	      }
	  });

});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
