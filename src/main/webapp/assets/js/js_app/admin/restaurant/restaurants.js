//设置店铺信息
Ext.ns("CTA.restaurant");
CTA.restaurant.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/restaurant/listRestaurants",
		    dataList:[{
		          index:'id',
		          header:'店铺ID'
		      },{
		          index:'name',
		          header:'店铺名字'
		      },{
		        index:'alias',
		        header:'店铺别名'
		      },{
		    	  index:'telephone',
		    	  header:'店铺电话'
		      },{
		    	  index:'settlement_display',
		    	  header:'店铺的结算方式',
		    	  renderer:function(value){
			          if(!value){
			            return "<span style='color:red;'>未分配</span>";
			          }else{
			            return value;
			          }
			        }
		      },{
		    	  index:'deli_type',
		    	  header:'配送方式',
		    	  renderer:function(value){
			          if(value){
			        	  return 1 == parseInt(value) ? "我们送" : "店铺送";
			          }else{
			            return "未指定";
			          }
			        }
		      },{
		    	  index:'send_type',
		    	  header:'订单发送方式',
		    	  renderer:function(value){
			          if(value){
			        	  if(1 == parseInt(value)){
			        		  return "短信";
			        	  }else if(2 == parseInt(value)){
			        		  return "无线打印机";
			        	  }else if(3 == parseInt(value)){
			        		  return "后台查看";
			        	  }else {
			        		  return "<span style='color:red;'>未指定</span>";
			        	  }
			          }else{
			            return "<span style='color:red;'>未指定</span>";
			          }
			        }
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
		    	  index:'deliverer_name',
		    	  header:'配送员',
		    	  renderer:function(value){
		    		  if(!value){
		    			  return "<span style='color:red;'>未分配</span>";
		    		  }else{
		    			  return value;
		    		  }
		    	  }
		      },{
		        index:'status',
		        header:'店铺状态',
		        renderer:function(value){
		          if(1==value){
		            return "<span style='color:green;'>营业中</span>";
		          }else if(2 == value){
		            return "<span style='color:red'>已打烊</span>";
		          }else if(3 ==value){
		        	  return "<span style='color:red'>打烊可预订</span>";
		          }else if(-1 == value){
		        	  return "<span style='color:red'>已删除</span>";
		          }
		        }
		      }]
	},
	url : {
		addUrl : basePath + "/admin/restaurant/addRestaurant",
		detailUrl : basePath + "/admin/restaurant/getRestaurantDetail",
		modifyUrl : basePath + "/admin/restaurant/modifyRestaurant",
		dropUrl : basePath + "/admin/restaurant/deleteRestaurant",
		closeRestUrl : basePath + "/admin/restaurant/closeRestaurant",
		openRestUrl : basePath + "/admin/restaurant/openRestaurant",
		detail_otherUrl : basePath + "/admin/restaurant/getRestaurantDetail_other",

		listRegionsUrl : basePath + "/admin/region/listRegions_s",
		listRestaurants_sort : basePath + "/admin/restaurant/listRestaurants_sort",
		setReserveTime : basePath + "/admin/restaurant/setReserveTime",
		listDeliverers_s_dep : basePath + "/admin/deliverer/listDeliverers_s_dep",
		listBuildings_dep : basePath + "/admin/building/listBuildings_dep",
		setRestaurantDeliverer : basePath + "/admin/restaurant/setRestaurantDeliverer",
		listRestaurantType : basePath + "/admin/restaurant/listRestaurantType",
		listRestaurantType_all : basePath + "/admin/restaurant/listRestaurantType_all",
		setRestaurantType : basePath + "/admin/restaurant/setRestaurantType",
		setRestaurantSettlement : basePath + "/admin/restaurant/setRestaurantSettlement"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.restaurant.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);


//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.restaurant.params.url.addUrl
          });
        }
    };
	  //定义添加的窗口
	  var addWindow = new CTA.common.SaveWindow({
		id : 'addWindow',
	    width : 950,
	    height : 470,
	    layout : 'fit',
	    handler : saveHandler
	  });

  //定义添加窗口中的form
  var formPanel = new CTA.common.SFormPanel({
	fileUpload : true,
	bodyStyle : 'overflow-x:hidden; overflow-y:scroll;padding-right :20px',
    items : [{
          fieldLabel : '店铺名称',
          emptyText : '请输入店铺名称',
          name : 'name',
          minLength:1,
          maxLength: 100
        },{
    	  fieldLabel : '店铺别名',
    	  emptyText : '请输入店铺别名',
    	  name : 'alias',
          minLength:1,
          maxLength: 50
        },{
        	fieldLabel : '店铺地址',
        	emptyText : '请输入店铺地址',
        	name : 'address',
        	minLength:1,
            maxLength: 100
        },{
        	fieldLabel : '店铺电话',
        	emptyText : '请输入店铺电话',
        	name : 'telephone',
        	vtype : 'telephone'
        },{
        	fieldLabel : '店铺公告',
        	emptyText : '请输入店铺公告',
        	name : 'notice',
        	minLength:1,
            maxLength: 150
        },{
        	xtype : 'numberfield',
        	fieldLabel : '起送金额',
        	emptyText : '请输入店铺起送金额',
        	name : 'deli_price'
        },{
        	xtype : 'numberfield',
        	fieldLabel : '默认送餐费',
        	emptyText : '请输入送餐费',
        	name : 'carriage'
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
					url : CTA.restaurant.params.url.listRegionsUrl
				})
			})
		},{
	        xtype : 'combo',
	        fieldLabel : '配送方式',
	        emptyText : '请选择店铺配送方式',
	        name : 'deli_type',
	        hiddenName : 'deli_type',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["我们送", "1"],["店铺送", "2"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        mode : 'local',
	        editable : false
	   },{
	        xtype : 'combo',
	        fieldLabel : '订单通知',
	        emptyText : '请选择店铺订单通知方式',
	        name : 'send_type',
	        hiddenName : 'send_type',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["短信通知", "1"],["无线打印机", "2"],["后台查看", "3"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        mode : 'local',
	        editable : false
	   },{
			xtype : 'combo',
			fieldLabel : '店铺排序',
			emptyText : '点击设置店铺排序',
			name : 'sort',
			hiddenName : 'sort',
			triggerAction : 'all',
			displayField : 'name',
			valueField : 'sort',
			editable : false,
			allowBlank : true,
			store : new Ext.data.Store({
				reader : new Ext.data.JsonReader({
					root : 'dataList',
					fields : [ {
						name : 'sort'
					}, {
						name : 'name'
					} ]
				}),
				proxy : new Ext.data.HttpProxy({
					url : CTA.restaurant.params.url.listRestaurants_sort
				})
			})
		},{
		        xtype : 'combo',
		        fieldLabel : '店铺状态',
		        emptyText : '请选择配店铺',
		        name : 'status',
		        hiddenName : 'status',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["营业中", "1"],["已打烊", "2"],["打烊可预订", "3"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        mode : 'local',
		        editable : false
	      },{
	        	fieldLabel : '店铺图片',
	        	name : 'rest_pic',
				text : "点击上传店铺图片",
				inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
				blankText : '请上传店铺图片'
	        },{
				id:'content',
				xtype : 'fckeditor',
		        border : false,
		        frame:true,
	        	name: 'content',
	            fieldLabel: '新闻内容',
	            value : '如果您要上传新闻图片，请使用IE或者火狐浏览器，或者开启双核浏览器的兼容模式',
	            frame:true,
	            height : 490,
				anchor : '100%'
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
    url : CTA.restaurant.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;
      var join_date = Ext.util.JSON.decode(response.responseText).join_date;
      if(join_date){
    	  data.join_date = join_date;
      }

      var formPanel = new CTA.common.SFormPanel({
    		fileUpload : true,
    		bodyStyle : 'overflow-x:hidden; overflow-y:scroll;padding-right :20px',
    	    items : [{
    	    	  xtype : 'hidden',
	  	          fieldLabel : '店铺ID',
		          name : 'id'
		        },{
    	    	  xtype : 'hidden',
	  	          fieldLabel : '店铺起送份数',
		          name : 'deli_count'
		        },{
		        	xtype : 'hidden',
	  	          fieldLabel : '店铺加入日期',
		          name : 'join_date'
		        },{
    	          fieldLabel : '店铺名称',
    	          emptyText : '请输入店铺名称',
    	          name : 'name',
    	          minLength:1,
    	          maxLength: 100
    	        },{
    	    	  fieldLabel : '店铺别名',
    	    	  emptyText : '请输入店铺别名',
    	    	  name : 'alias',
    	          minLength:1,
    	          maxLength: 50
    	        },{
    	        	fieldLabel : '店铺地址',
    	        	emptyText : '请输入店铺地址',
    	        	name : 'address',
    	        	minLength:1,
    	            maxLength: 100
    	        },{
    	        	fieldLabel : '店铺电话',
    	        	emptyText : '请输入店铺电话',
    	        	name : 'telephone',
    	        	vtype : 'telephone'
    	        },{
    	        	fieldLabel : '店铺公告',
    	        	emptyText : '请输入店铺公告',
    	        	name : 'notice',
    	        	minLength:1,
    	            maxLength: 150
    	        },{
    	        	xtype : 'numberfield',
    	        	fieldLabel : '起送金额',
    	        	emptyText : '请输入店铺起送金额',
    	        	name : 'deli_price'
    	        },{
    	        	xtype : 'numberfield',
    	        	fieldLabel : '默认送餐费',
    	        	emptyText : '请输入送餐费',
    	        	name : 'carriage'
    	        },{
    	    	  	id : 'rest_region',
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
    						url : CTA.restaurant.params.url.listRegionsUrl
    					}),
    					autoLoad : true,
    					listeners : {
    						load : function (){
    							Ext.getCmp('rest_region').setValue(data.region_id);
    						}
    					}
    				})
    			},{
    		        xtype : 'combo',
    		        fieldLabel : '配送方式',
    		        emptyText : '请选择店铺配送方式',
    		        name : 'deli_type',
    		        hiddenName : 'deli_type',
    		        triggerAction : 'all',
    		        store : new Ext.data.SimpleStore({
    		          fields : ['label', 'value'],
    		          data : [["我们送", "1"],["店铺送", "2"]]
    		        }),
    		        displayField : 'label',
    		        valueField : 'value',
    		        mode : 'local',
    		        editable : false
    		   },{
    		        xtype : 'combo',
    		        fieldLabel : '订单通知',
    		        emptyText : '请选择店铺订单通知方式',
    		        name : 'send_type',
    		        hiddenName : 'send_type',
    		        triggerAction : 'all',
    		        store : new Ext.data.SimpleStore({
    		          fields : ['label', 'value'],
    		          data : [["短信通知", "1"],["无线打印机", "2"],["后台查看", "3"]]
    		        }),
    		        displayField : 'label',
    		        valueField : 'value',
    		        mode : 'local',
    		        editable : false
    		   },{
    				xtype : 'combo',
    				fieldLabel : '店铺排序',
    				emptyText : '点击设置店铺排序',
    				name : 'sort',
    				hiddenName : 'sort',
    				triggerAction : 'all',
    				displayField : 'name',
    				valueField : 'sort',
    				editable : false,
    				store : new Ext.data.Store({
    					reader : new Ext.data.JsonReader({
    						root : 'dataList',
    						fields : [ {
    							name : 'sort'
    						}, {
    							name : 'name'
    						} ]
    					}),
    					proxy : new Ext.data.HttpProxy({
    						url : CTA.restaurant.params.url.listRestaurants_sort
    					})
    				})
    			},{
    			        xtype : 'combo',
    			        fieldLabel : '店铺状态',
    			        emptyText : '请选择配店铺',
    			        name : 'status',
    			        hiddenName : 'status',
    			        triggerAction : 'all',
    			        store : new Ext.data.SimpleStore({
    			          fields : ['label', 'value'],
    			          data : [["已删除",-1],["营业中", "1"],["已打烊", "2"],["打烊可预订",3]]
    			        }),
    			        displayField : 'label',
    			        valueField : 'value',
    			        mode : 'local',
    			        allowBlank : true,
    			        editable : false
    		      },{
    		        	fieldLabel : '店铺图片',
    		        	name : 'pic',
    					blankText : '请上传店铺门头照',
    					allowBlank : true,
    					readOnly : true

    		      },{
    		    	  fieldLabel : '修改店铺图片',
    		    	  name : 'rest_pic',
    		    	  text : "点击上传店铺门头照",
    		    	  inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
    		    	  blankText : '请上传店铺门头照',
    		    	  allowBlank : true
    		     }],
    		        data : data
    	  });

      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
    	 width : 950,
 	    height : 420,
	    layout : 'fit',
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
            	CTA.common.Mask.showMask({target:'updateWindow'});
                  formPanel.commit({
                    url : CTA.restaurant.params.url.modifyUrl
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
  	    	fieldLabel : '店铺ID',
	        emptyText : '请输入店铺',
	        name : 'id',
	        allowBlank : true
	      },{
	        fieldLabel : '店铺名称',
	        emptyText : '请输入店铺名称',
	        name : 'name',
	        allowBlank : true,
	        editable : false,
	        minLength:1,
	        maxLength: 50
	      },{
	        fieldLabel : '店铺别名',
	        emptyText : '请输入店铺别名',
	        name : 'alias',
	        minLength:1,
	        maxLength: 50,
	        allowBlank : true
	      },{
		        xtype : 'combo',
		        fieldLabel : '配送方式',
		        emptyText : '请选择店铺配送方式',
		        name : 'deli_type',
		        hiddenName : 'deli_type',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["我们送", "1"],["店铺送", "2"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        mode : 'local',
		        editable : false
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
						url : CTA.restaurant.params.url.listRegionsUrl
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
		        fieldLabel : '店铺状态',
		        emptyText : '请选择店铺状态',
		        name : 'status',
		        hiddenName : 'status',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["已删除",-1],["营业中", "1"],["已打烊", "2"],["打烊可预订", "3"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        mode : 'local',
		        editable : false
	      }],
		  data : CTA.common.Constant.queryParams
	  });

	var queryHandler = function(){
		var params = formPanel.getForm().getFieldValues();
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
	    		url : CTA.restaurant.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
	});
});

//打烊
toolbar.regCloseRestHandler(function(){

	var selections = gridPanel.getSelectionModel().getSelections();
	if(!selections || selections.length <= 0){
		Ext.Msg.confirm('提示', '您没有选择任何店铺，这样会打烊您管理的所有店铺，确定要继续吗？',function(button){
			if('yes' == button){
				CTA.common.Mask.showMask();
				CTA.common.Ajax.request({
					url : CTA.restaurant.params.url.closeRestUrl,
					params : {ids : ""}
				});
			}
		});
		return;
	}
	var ids = [];
	Ext.each(selections,function(item){
		ids.push(item.get("id"));
	});

	Ext.Msg.confirm("警告","您确定打烊这  "+ids.length+" 个店铺吗？",function(button){
		if('yes' == button){
			CTA.common.Mask.showMask();
			CTA.common.Ajax.request({
				url : CTA.restaurant.params.url.closeRestUrl,
				params : {ids : ids.toString()}
			});
		}
	});
});

//营业
toolbar.regOpenRestHandler(function(){

	var selections = gridPanel.getSelectionModel().getSelections();
	if(!selections || selections.length <= 0){
		Ext.Msg.confirm('提示', '您没有选择任何店铺，这样会营业您管理的所有店铺，确定要继续吗？',function(button){
			if('yes' == button){
				CTA.common.Mask.showMask();
				CTA.common.Ajax.request({
					url : CTA.restaurant.params.url.openRestUrl,
					params : {ids : ""}
				});
			}
		});
		return;
	}
	var ids = [];
	Ext.each(selections,function(item){
		ids.push(item.get("id"));
	});

	Ext.Msg.confirm("警告","您确定营业这  "+ids.length+" 个店铺吗？",function(button){
		if('yes' == button){
			CTA.common.Mask.showMask();
			CTA.common.Ajax.request({
				url : CTA.restaurant.params.url.openRestUrl,
				params : {ids : ids.toString()}
			});
		}
	});
});

//设置店铺营业时间
toolbar.regSetReserveTimeHandler(function(){
	var selections = gridPanel.getSelectionModel().getSelections();
	if(!selections || selections.length <= 0){
		Ext.Msg.alert('提示', '请选择您要操作的数据!');
		return;
	}
	var ids = [];
	Ext.each(selections,function(item){
		ids.push(item.get("id"));
	});

	var saveHandler = function(){

        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
      	  var params = formPanel.getForm().getValues();
      	  params.ids = ids;

	      CTA.common.Ajax.request({
	    	  url : CTA.restaurant.params.url.setReserveTime,
	    		params : params
	    	});
        }
    };
	  //定义设置时间的窗口
	  var addWindow = new CTA.common.SaveWindow({
		title : '设置 ' + ids.length + " 个店铺的营业时间",
		id : 'addWindow',
	    width : 450,
	    height : 270,
	    layout : 'fit',
	    handler : saveHandler
	  });

  //定义设置时间窗口中的form
  var formPanel = new CTA.common.SFormPanel({
    items : [{
    	  xtype : 'timefield',
          fieldLabel : '上午营业时间',
          emptyText : '请选择上午营业时间',
          name : 'open_time_am',
          minValue: '8:00 AM',
          maxValue: '20:00 PM',
          format:'H:i:s',
          increment: 15,
          editable : false
        },{
    	  xtype : 'timefield',
          fieldLabel : '上午打烊时间',
          emptyText : '请选择上午打烊时间',
          name : 'close_time_am',
          minValue: '8:00 AM',
          maxValue: '20:00 PM',
          format:'H:i:s',
          increment: 15,
          editable : false
        },{
    	  xtype : 'timefield',
          fieldLabel : '下午营业时间',
          emptyText : '请选择下午营业时间',
          name : 'open_time_pm',
          minValue: '8:00 AM',
          maxValue: '20:00 PM',
          format:'H:i:s',
          increment: 15,
          editable : false
        },{
        	xtype : 'timefield',
        	fieldLabel : '下午打烊时间',
        	emptyText : '请选择下午打烊时间',
        	name : 'close_time_pm',
        	minValue: '8:00 AM',
        	maxValue: '20:00 PM',
        	format:'H:i:s',
        	increment: 15,
        	editable : false
        }]
  });
  addWindow.add(formPanel);
  addWindow.show();
});


//设置店铺配送员
toolbar.regSetRestaurantDelivererHandler(function(){
	var selections = gridPanel.getSelectionModel().getSelections();
	if(!selections || selections.length <= 0){
		Ext.Msg.alert('提示', '请选择您要操作的数据!');
		return;
	}
	var ids = [];
	Ext.each(selections,function(item){
		ids.push(item.get("id"));
	});
	var saveHandler = function(){
		//检查表单是否填写好
		if(formPanel.getForm().isValid()){
			CTA.common.Mask.showMask({target:'addWindow'});
			var params = formPanel.getForm().getFieldValues();
			params.ids = ids.join();

			CTA.common.Ajax.request({
				url : CTA.restaurant.params.url.setRestaurantDeliverer,
				params : params
			});
		}
	};
	//定义设置时间的窗口
	var addWindow = new CTA.common.SaveWindow({
		title : '设置 ' + ids.length + " 个店铺的配送员",
		id : 'addWindow',
		width : 500,
		height : 270,
		layout : 'fit',
		handler : saveHandler
	});

	//定义设置配送员窗口中的form
	var formPanel = new CTA.common.SFormPanel({
		items : [{
			xtype : 'combo',
			fieldLabel : '分配配送员',
			emptyText : '点击设置店铺配送员',
			name : 'deliverer_id',
			hiddenName : 'deliverer_id',
			triggerAction : 'all',
			displayField : 'realname',
			valueField : 'id',
			editable : false,
			allowBlank : false,
			store : new Ext.data.Store({
				reader : new Ext.data.JsonReader({
					root : 'dataList',
					fields : [ {
						name : 'id'
					}, {
						name : 'realname'
					} ]
				}),
				proxy : new Ext.data.HttpProxy({
					url : CTA.restaurant.params.url.listDeliverers_s_dep
				})
			})
		},{
	        xtype : 'combo',
	        fieldLabel : '配送范围',
	        emptyText : '配送范围可以为空，为空表示该店铺的默认配送员',
	        name : 'building_ids',
	        hiddenName : 'building_ids',
	        triggerAction : 'all',
	        typeAhead: true,
	        selectOnFocus:true,
	        allowBlank : true,
	        tpl:'<tpl for="."><div class="x-combo-list-item"><span><input type="checkbox" {[values.check?"checked":""]}  value="{[values.name]}" />  </span><span>  {name}</span></div></tpl>',
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
					url : CTA.restaurant.params.url.listBuildings_dep
				})
			}),
	        editable : false,
	        onSelect : function(record, index){
	            if(this.fireEvent('beforeselect', this, record, index) !== false){
	                record.set('check',!record.get('check'));
	                var strvalue=[];//传入后台的值
	                this.store.each(function(rc){
	                    if(rc.get('check')){
	                       strvalue.push(rc.get('id'));
	                    }
	                });

	              this.setValue(strvalue.join(","));
	              this.fireEvent('select', this, record, index);
	           }
	        }
	   },{
	        xtype : 'combo',
	        fieldLabel : '配送员的订单接收级别',
	        emptyText : '先选择送范围,才能设置该配送员在这些范围订单接收级别',
	        name : 'rank',
	        hiddenName : 'rank',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["高", "1"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        mode : 'local',
	        editable : false,
	        allowBlank : true
      }]
	});
	addWindow.add(formPanel);
	addWindow.show();
});

//设置店铺类型
toolbar.regSetRestaurantTypeHandler(function(){
	var selections = gridPanel.getSelectionModel().getSelections();
	if(!selections || selections.length <= 0){
		Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，默认只操作第一条!');
		return;
	}

	 Ext.Ajax.request({
		    url : CTA.restaurant.params.url.listRestaurantType,
		    params : {id :selections[0].get("id")},
		    success : function(response) {

		      var restTypes = Ext.util.JSON.decode(response.responseText).data;

		      var store = new Ext.data.Store({
				    autoLoad:true,
				    proxy:new Ext.data.HttpProxy({
				        url : CTA.restaurant.params.url.listRestaurantType_all
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

		     var cm = new Ext.grid.ColumnModel([
		             new Ext.grid.CheckboxSelectionModel(),{
		                 header:'类型ID',
		                 dataIndex:'id',
		                 renderer : function(v, metaData, record, rowIndex, colIndex, store){
		              	   var cate_id = record.data.id;
		              	   for(var i = 0;i<restTypes.length;i++){
		              		   var cate_id_ = restTypes[i].id;
		              		   if(cate_id == cate_id_){
		              			   restTypesGrid.getSelectionModel().selectRow(rowIndex,true);
		              			   return cate_id;
		              		   }
		              	   }
		              	   return cate_id;
		                 }
		             },{
		          	   header:'类型名称',
		          	   dataIndex:'name'
		             }]);

		     var restTypesGrid = new Ext.grid.EditorGridPanel({
		  	   region : 'center',
		         store: store,
		         cm: cm,
		         sm:new Ext.grid.CheckboxSelectionModel(),
		         width : 300,
		         height: 270,
		         frame: true,
		         view:new Ext.grid.GridView({
		             forceFit:true
		         })
		     });

		      var updateWindow = new CTA.common.UpdateWindow({
		    	id : 'updateWindow_other',
		    	title : '设置 ' + selections[0].get("name") + " 的类型",
		    	width : 600,
				height : 350,
				layout : 'border',
		        items : [restTypesGrid],
		        handler : function(){
		            	var rest_id = selections[0].get("id");
						var typeList_ = restTypesGrid.getSelectionModel().getSelections();
						var type_ids = [];
						for(var i = 0;i<typeList_.length;i++){
							type_ids.push(typeList_[i].data.id);
						}
						if(!typeList_ || typeList_.length <= 0){
							Ext.Msg.alert('提示', '请选择您要操作的数据!');
							return;
						}

						CTA.common.Mask.showMask({target:'updateWindow_other'});
						CTA.common.Ajax.request({
			              url : CTA.restaurant.params.url.setRestaurantType,
			              params : {
			            	  id : rest_id,
			            	  type_ids : type_ids.toString()
			              }
			            });
		        }
		      });

		      updateWindow.show();
		    }
     });
});

//设置店铺结算方式
toolbar.regSetRestaurantSettlementHandler(function(){

		var selections = gridPanel.getSelectionModel().getSelections();
		if(!selections || selections.length <= 0){
			Ext.Msg.alert('提示', '请选择您要操作的数据!');
			return;
		}
		var ids = [];
		Ext.each(selections,function(item){
			ids.push(item.get("id"));
		});

		var saveHandler = function(){
			 //检查表单是否填写好
	        if(formPanel.getForm().isValid()){
	          CTA.common.Mask.showMask({target:'addWindow'});
	      	  var params = formPanel.getForm().getValues();
	      	  params.ids = ids.toString();

		      CTA.common.Ajax.request({
		    	  url : CTA.restaurant.params.url.setRestaurantSettlement,
		    		params : params
		    	});
	        }
		};

		var addWindow = new CTA.common.SaveWindow({
			title : '设置 ' + ids.length + " 个店铺的结算方式",
			id : 'addWindow',
			width : 450,
			height : 270,
			layout : 'fit',
			handler : saveHandler
		});


		var formPanel = new CTA.common.SFormPanel({
			items : [{
				id : 'settlement_type',
		        xtype : 'combo',
		        fieldLabel : '结算方式',
				emptyText : '点击设置店铺结算方式',
		        name : 'setttlement_type',
		        hiddenName : 'setttlement_type',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["按订单金额", "1"],["按订单菜品数", "2"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        mode : 'local',
		        editable : false,
		        listeners : {
		                'select': function(combo,item,index){
		                	var settlement_type = item.data.value;
		                	//按照订单金额
		                	if(1 == settlement_type){
		                		Ext.getCmp('percent').setValue(0);
		                		Ext.getCmp("slider_settlement").plugins.getText = function(thumb){
		    		                return String.format('<b>{0}%</b>', thumb.value);
		    		            };

		    		            Ext.getCmp("slider_settlement").on({
		    		                'changecomplete':function(slider, newValue){
		    		                	Ext.getCmp('percent').setValue(newValue/100);
		    		                }   //进度条完成触发
		    		           })

		    		            Ext.getCmp("slider_settlement").increment = 1;
		    		            Ext.getCmp("slider_settlement").maxValue = 100;
		    		            Ext.getCmp("slider_settlement").minValue = 1;
		                	}else if(2 == settlement_type){
		                		Ext.getCmp('percent').setValue(0);
		                		Ext.getCmp("slider_settlement").plugins.getText = function(thumb){
		    		                return String.format('<b>{0}/角</b>', thumb.value);
		    		            };
		    		            Ext.getCmp("slider_settlement").on({
		    		                'changecomplete':function(slider, newValue){
		    		                	Ext.getCmp('percent').setValue(newValue/10);
		    		                }   //进度条完成触发
		    		           })

		    		            Ext.getCmp("slider_settlement").increment = 1;
		    		            Ext.getCmp("slider_settlement").maxValue = 1000;
		    		            Ext.getCmp("slider_settlement").minValue = 0;
		                	}

		                }
	           }
		   },{
				id : 'percent',
				fieldLabel : '订单利润',
				emptyText : '滑动滚动条，设置订单利润',
				name : 'discount',
				maxLength: 50,
				msgTarget:'qtip',
				autoShow : false,
				anchor:'100%',
				readOnly:true
			},{
				id : 'slider_settlement',
				xtype:'slider',
				fieldLabel : '设置利润',
				msgTarget:'qtip',
				anchor:'100%',
				textAlign : 'right',
				allowBlank : false,
				increment: 0.1,
		        minValue: 0,
		        maxValue: 100,
		        plugins: new Ext.slider.Tip({
		            getText: function(thumb){
		                return String.format('<b>{0}%</b>', thumb.value);
		            }
		        }),
		        clickToChange:true//允许单击改变进度值
			},{
		        xtype : 'combo',
		        fieldLabel : '结算周期',
				emptyText : '点击设置店铺结算周期',
		        name : 'period',
		        hiddenName : 'period',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["当面结算", "1"],["按周结算", "7"],["按月结算", "7"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        mode : 'local',
		        editable : false
		    }]
		});
		addWindow.add(formPanel);
		addWindow.show();
});

//查看店铺详情
toolbar.regViewDetailHandler(function(){
	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }
	  var id = selections[0].get("id");
	  Ext.Ajax.request({
	    url : CTA.restaurant.params.url.detail_otherUrl,
	    params : {id : id},
	    success : function(response) {
	      var data = Ext.util.JSON.decode(response.responseText).data;
	      var open_time_am = Ext.util.JSON.decode(response.responseText).open_time_am;
	      var close_time_am = Ext.util.JSON.decode(response.responseText).close_time_am;
	      var open_time_pm = Ext.util.JSON.decode(response.responseText).open_time_pm;
	      var close_time_pm = Ext.util.JSON.decode(response.responseText).close_time_pm;
	      if(open_time_am){
	    	  data.open_time_am = open_time_am;
	      }
	      if(close_time_am){
	    	  data.close_time_am = close_time_am;
	      }
	      if(open_time_pm){
	    	  data.open_time_pm = open_time_pm;
	      }
	      if(close_time_pm){
	    	  data.close_time_pm = close_time_pm;
	      }


	      var formPanel = new CTA.common.SFormPanel({
	    		fileUpload : true,
	    	    items : [{
	    	          fieldLabel : '店铺名称',
	    	          name : 'name'
	    	        },{
	    	        	fieldLabel : '平均送餐时间',
	    	        	name : 'deli_interval'
	    	        },{
	    	        	fieldLabel : '菜单总数',
	    	        	name : 'food_count'
	    	        },{
	    	        	fieldLabel : '订单总数',
	    	        	name : 'order_count'
	    	        },{
	    	        	fieldLabel : '用于评论总数',
	    	        	name : 'recommend_count'
	    	        },{
	    	        	fieldLabel : '口味评价',
	    	        	name : 'taste'
	    	        },{
	    	        	fieldLabel : '速度评价',
	    	        	name : 'speed'
	    	        },{
	    	        	fieldLabel : '态度评价',
	    	        	name : 'attitude'
	    	        },{
	    	        	fieldLabel : '上午营业时间',
	    	        	name : 'open_time_am'
	    	        },{
	    	        	fieldLabel : '上午打烊时间',
	    	        	name : 'close_time_am'
	    	        },{
	    	        	fieldLabel : '下午营业时间',
	    	        	name : 'open_time_pm'
	    	        },{
	    	        	fieldLabel : '下午打烊时间',
	    	        	name : 'close_time_pm'
	    	        }],
	    		    data : data
	    	  });

	      var detailWindow = new CTA.common.Window({
	    	title : '查看店铺详情',
	    	width : 650,
		    height : 450,
		    layout : 'fit',
	        items : [formPanel]
	      });
	      detailWindow.show();
	    },
	    failure : function() {
	      CTA.common.Mask.hideMask();
	      Ext.Msg.alert('提示', '操作失败!');
	    }
	  });
});

var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
