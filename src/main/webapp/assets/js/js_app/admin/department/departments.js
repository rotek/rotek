//设置部门信息
Ext.ns("CTA.department");
CTA.department.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/department/listDepartments",
		    dataList:[{
		          index:'id',
		          header:'部门ID'
		      },{
		          index:'dep_name',
		          header:'部门'
		      },{
		        index:'super_dep_name',
		        header:'上级部门'
		      },{
		    	  index:'memo',
		    	  header:'部门描述'
		      },{
		        index:'status',
		        header:'部门状态',
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
		addUrl : basePath + "/admin/department/addDepartment",
		detailUrl : basePath + "/admin/department/getDepartmentDetail",
		modifyUrl : basePath + "/admin/department/modifyDepartment",
		dropUrl : basePath + "/admin/department/deleteDepartment",
		listDepartmentsUrl : basePath + "/admin/department/listDepartments_s",
		listDepartments_superUrl : basePath + "/admin/department/listDepartments_super",

		listDeliverers_s : basePath + "/admin/deliverer/listDeliverers_s",
		listDeliverers_dep : basePath + "/admin/department/listDeliverers_dep",
		updateDeliverers : basePath + "/admin/department/updateDeliverers",

		listBuildings_s : basePath + "/admin/building/listBuildings_s",
		listBuildings_dep : basePath + "/admin/department/listBuildings_dep",
		updateBuildings : basePath + "/admin/department/updateBuildings",

		listRestaurants_s : basePath + "/admin/restaurant/listRestaurants_s",
		listRestaurants_dep : basePath + "/admin/department/listRestaurants_dep",
		updateRestaurants : basePath + "/admin/department/updateRestaurants"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.department.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);

//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.department.params.url.addUrl
          });
        }
    };
	  //定义添加的窗口
	  var addWindow = new CTA.common.SaveWindow({
		id : 'addWindow',
	    width : 500,
	    height : 350,
	    layout : 'fit',
	    handler : saveHandler
	  });

  //定义添加窗口中的form
  var formPanel = new CTA.common.SFormPanel({
    items : [{
        fieldLabel : '部门名称',
        emptyText : '请输入部门名称',
        name : 'dep_name',
        minLength:1,
        maxLength: 30
      },{
        fieldLabel : '部门描述',
        emptyText : '请输入部门描述',
        name : 'memo',
        minLength:1,
        maxLength: 30
      },{
			xtype : 'combo',
			id : 'super_dep',
			fieldLabel : '上级部门',
			editable : false,
			emptyText : '点击设置上级部门',
			name : 'super_dep_id',
			hiddenName : 'super_dep_id',
			triggerAction : 'all',
			displayField : 'dep_name',
			valueField : 'id',
			allowBlank : false,
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
					url : CTA.department.params.url.listDepartments_superUrl
				})
			})
		},{
			xtype : 'combo',
			fieldLabel : '部门排序',
			editable : false,
			emptyText : '点击设置部门排序',
			name : 'sort',
			hiddenName : 'sort',
			triggerAction : 'all',
			displayField : 'dep_name',
			valueField : 'id',
			allowBlank : false,
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
					url : CTA.department.params.url.listDepartmentsUrl
				})
			})
		},{
	        xtype : 'combo',
	        fieldLabel : '部门状态',
	        emptyText : '请选择部门状态',
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
    url : CTA.department.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
    	  height : 350,
    	    items : [{
    	    	xtype : 'hidden',
    	        fieldLabel : '部门ID',
    	        name : 'id'
    	      },{
    	        fieldLabel : '部门名称',
    	        emptyText : '请输入部门名称',
    	        name : 'dep_name',
    	        minLength:1,
    	        maxLength: 30
    	      },{
    	        fieldLabel : '部门描述',
    	        emptyText : '请输入部门描述',
    	        name : 'memo',
    	        minLength:1,
    	        maxLength: 30
    	      },{
    				xtype : 'combo',
    				id : 'super_department',
    				fieldLabel : '上级部门',
    				editable : false,
    				emptyText : '点击设置上级部门',
    				name : 'super_dep_id',
    				hiddenName : 'super_dep_id',
    				triggerAction : 'all',
    				displayField : 'dep_name',
    				valueField : 'id',
    				allowBlank : false,
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
    						url : CTA.department.params.url.listDepartments_superUrl
    					}),
    					autoLoad : true,
        				listeners : {
        					load : function (){
    	    					Ext.getCmp('super_department').setValue(data.super_dep_id);
        					}
        				}
    				})
    			},{
    				xtype : 'combo',
    				fieldLabel : '部门排序',
    				editable : false,
    				emptyText : '点击设置部门排序',
    				name : 'sort',
    				hiddenName : 'sort',
    				triggerAction : 'all',
    				displayField : 'dep_name',
    				valueField : 'id',
    				allowBlank : false,
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
    						url : CTA.department.params.url.listDepartmentsUrl
    					})
    				})
    			},{
    		        xtype : 'combo',
    		        fieldLabel : '部门状态',
    		        emptyText : '请选择部门状态',
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

      //Ext.getCmp('manager_role').setValue(data.name);
      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
        width : 500,
        height : 350,
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
              CTA.common.Mask.showMask({target:'updateWindow'});
              formPanel.commit({
                url : CTA.department.params.url.modifyUrl
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
    	  height : 350,
  	    items : [{
  	        fieldLabel : '部门ID',
  	        emptyText : '请输入部门ID',
  	        allowBlank : true,
  	        name : 'id'
  	      },{
  	        fieldLabel : '部门名称',
  	        emptyText : '请输入部门名称',
  	        allowBlank : true,
  	        name : 'dep_name',
  	      },{
  	        fieldLabel : '部门描述',
  	        emptyText : '请输入部门描述',
  	        allowBlank : true,
  	        name : 'memo',
  	      },{
  				xtype : 'combo',
  				id : 'super_department_',
  				fieldLabel : '上级部门',
  				editable : false,
  				emptyText : '点击设置上级部门',
  				name : 'super_dep_id',
  				hiddenName : 'super_dep_id',
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
  						url : CTA.department.params.url.listDepartments_superUrl
  					})
  				})
  			},{
  				xtype : 'combo',
  				fieldLabel : '部门排序',
  				editable : false,
  				emptyText : '点击设置部门排序',
  				name : 'sort',
  				hiddenName : 'sort',
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
  						url : CTA.department.params.url.listDepartmentsUrl
  					})
  				})
  			},{
  		        xtype : 'combo',
  		        fieldLabel : '部门状态',
  		        emptyText : '请选择部门状态',
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
  		        allowBlank : true,
  		        editable : false
  		      }],
		  data : CTA.common.Constant.queryParams
	  });

	var queryHandler = function(){
		var params = formPanel.getForm().getFieldValues() ;
		CTA.common.Constant.queryParams = {};
		Ext.apply(CTA.common.Constant.queryParams,params);

		gridPanel.getStore().reload();
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
	    		url : CTA.department.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});

//设置送餐人
toolbar.regSetDepartmentDelivererHandler(function(){

	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }
	  var id = selections[0].get("id");
	  Ext.Ajax.request({
	    url : CTA.department.params.url.listDeliverers_dep,
	    params : {id : id},
	    success : function(response) {
		      var dataList = Ext.util.JSON.decode(response.responseText).dataList;

		      var store = new Ext.data.Store({
		    	autoLoad : true,
		  	    proxy:new Ext.data.HttpProxy({
		  	        url : CTA.department.params.url.listDeliverers_s
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
		  	        	name:'realname',
		  	        	type:'string'
		  	        }
		  	    ]))
		  	});

		   var sm = new Ext.grid.CheckboxSelectionModel();
		   var cm = new Ext.grid.ColumnModel([
            sm,{
	               header:'配送员ID',
	               dataIndex:'id',
	               renderer : function(v, metaData, record, rowIndex, colIndex, store){
	              	   var deliver_id = record.data.id;
	              	   for(var i = 0;i<dataList.length;i++){
	              		   var deliver_id_ = dataList[i];
	              		   if(deliver_id == deliver_id_){
	              			   deliverersGrid.getSelectionModel().selectRow(rowIndex,true);
	              			   return deliver_id;
	              		   }
	              	   }
	              	   return deliver_id;
	                 }
           },{
        	   header:'配送员名字',
        	   dataIndex:'realname'
         }]);

		   var deliverersGrid = new Ext.grid.GridPanel({
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
		    	   	id : 'deliver_name',
		    	   	xtype : 'textfield',
		    	   	width : '100%',
		    	   	emptyText : '请输入配送员名字',
		    	   	label : '查询'
		        }, '-', '-',

		        {
		        	id : 'range_undistributed',
		        	xtype : 'radio',
	                boxLabel: '未分配',
	                name: 'region',
	                inputValue: '未分配'
	            }, '-', {
	            	id : 'range_all',
	            	checked: true,
	            	xtype : 'radio',
	                boxLabel: '全部',
	                name: 'region',
	                inputValue: '全部'
	            }, '-',{
		            text:'查询',
		            icon : basePath+"/assets/images/toolbar/query.png",
		            listeners : {
		            	"click" : function(){
		            		//查询数据的范围
		            		var range = 1;
		            		Ext.getCmp('range_all').checked ? range = 0 : range = 1;
		            		deliverersGrid.getStore().baseParams = {
		            			realname : Ext.getCmp('deliver_name').getValue(),
		            			range : range
		            		};
		            		deliverersGrid.getStore().reload();
		            	}
		            }
		        }, '-', {
		            text:'刷新',
		            icon : basePath+"/assets/images/toolbar/refresh.gif",
		            listeners : {
		            	"click" : function(){
		            		deliverersGrid.getStore().baseParams = {
		            			realname : ''
		            		};
		            		deliverersGrid.getStore().reload();
		            	}
		            }
		        }]
		   });

		  	var saveHandler = function(){
		          //检查是否选择好
		  		var selections = deliverersGrid.getSelectionModel().getSelections();
		  		if(!selections || selections.length <= 0){
		  				Ext.Msg.alert('提示', '请选择您要分配的配送员!');
		  		    	return;
		  		}
		  		var deliverer_ids = [];
		  		Ext.each(selections,function(value){
		  			deliverer_ids.push(value.get("id"));
		  		});
	            CTA.common.Mask.showMask({target:'addWindow'});
	            CTA.common.Ajax.request({
		    		url : CTA.department.params.url.updateDeliverers,
		    		params : {
		    			dep_id : id,
		    		    deliverer_ids : deliverer_ids.toString()
		    		}
		    	});

		      };
		  	  //定义添加的窗口
		  	  var addWindow = new CTA.common.SaveWindow({
		  		id : 'addWindow',
		  		title : '分配送餐人',
		  	    width : 550,
		  	    height : 450,
		  	    layout : 'border',
		  	    handler : saveHandler,
		  	    items : [deliverersGrid]
		  	  });
		  	  addWindow.show();
	    },
	    failure : function() {
	        CTA.common.Mask.hideMask();
	        Ext.Msg.alert('提示', '获取信息失败!');
	      }
	  });

});

//设置楼宇
toolbar.regSetBuildingHandler(function(){

	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }
	  var id = selections[0].get("id");
	  Ext.Ajax.request({
	    url : CTA.department.params.url.listBuildings_dep,
	    params : {id : id},
	    success : function(response) {
		      var dataList = Ext.util.JSON.decode(response.responseText).dataList;

		      var store = new Ext.data.Store({
		    	autoLoad : true,
		  	    proxy:new Ext.data.HttpProxy({
		  	        url : CTA.department.params.url.listBuildings_s
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
		   var cm = new Ext.grid.ColumnModel([
          sm,{
	               header:'楼宇ID',
	               dataIndex:'id',
	               renderer : function(v, metaData, record, rowIndex, colIndex, store){
	              	   var buidling_id = record.data.id;
	              	   for(var i = 0;i<dataList.length;i++){
	              		   var buidling_id_ = dataList[i];
	              		   if(buidling_id == buidling_id_){
	              			   buildingsGrid.getSelectionModel().selectRow(rowIndex,true);
	              			   return buidling_id;
	              		   }
	              	   }
	              	   return buidling_id;
	                 }
         },{
      	   header:'楼宇名称',
      	   dataIndex:'name'
       }]);

		   var buildingsGrid = new Ext.grid.GridPanel({
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
		    	   	id : 'building_name',
		    	   	xtype : 'textfield',
		    	   	width : '100%',
		    	   	emptyText : '请输入楼宇名字',
		    	   	label : '查询'
		        }, '-', '-',

		        {
		        	id : 'range_undistributed',
		        	xtype : 'radio',
	                boxLabel: '未分配',
	                name: 'region',
	                inputValue: '未分配'
	            }, '-', {
	            	id : 'range_all',
	            	checked: true,
	            	xtype : 'radio',
	                boxLabel: '全部',
	                name: 'region',
	                inputValue: '全部'
	            }, '-',{
		            text:'查询',
		            icon : basePath+"/assets/images/toolbar/query.png",
		            listeners : {
		            	"click" : function(){
		            		//查询数据的范围
		            		var range = 1;
		            		Ext.getCmp('range_all').checked ? range = 0 : range = 1;
		            			buildingsGrid.getStore().baseParams = {
		            			name : Ext.getCmp('building_name').getValue(),
		            			range : range
		            		};
		            		buildingsGrid.getStore().reload();
		            	}
		            }
		        }, '-', {
		            text:'刷新',
		            icon : basePath+"/assets/images/toolbar/refresh.gif",
		            listeners : {
		            	"click" : function(){
		            			buildingsGrid.getStore().baseParams = {
		            			realname : ''
		            		};
		            		buildingsGrid.getStore().reload();
		            	}
		            }
		        }]
		   });

		  	var saveHandler = function(){
		          //检查是否选择好
		  		var selections = buildingsGrid.getSelectionModel().getSelections();
		  		if(!selections || selections.length <= 0){
		  				Ext.Msg.alert('提示', '请选择您要分配的楼宇信息!');
		  		    	return;
		  		}
		  		var building_ids = [];
		  		Ext.each(selections,function(value){
		  			building_ids.push(value.get("id"));
		  		});
	            CTA.common.Mask.showMask({target:'addWindow'});
	            CTA.common.Ajax.request({
		    		url : CTA.department.params.url.updateBuildings,
		    		params : {
		    			dep_id : id,
		    			building_ids : building_ids.toString()
		    		}
		    	});

		      };
		  	  //定义添加的窗口
		  	  var addWindow = new CTA.common.SaveWindow({
		  		id : 'addWindow',
		  		title : '分配楼宇',
		  	    width : 550,
		  	    height : 450,
		  	    layout : 'border',
		  	    handler : saveHandler,
		  	    items : [buildingsGrid]
		  	  });
		  	  addWindow.show();
	    },
	    failure : function() {
	        CTA.common.Mask.hideMask();
	        Ext.Msg.alert('提示', '获取信息失败!');
	      }
	  });

});

//分配店铺
toolbar.regSetRestaurantHandler(function(){

	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }
	  var id = selections[0].get("id");
	  Ext.Ajax.request({
	    url : CTA.department.params.url.listRestaurants_dep,
	    params : {id : id},
	    success : function(response) {
		      var dataList = Ext.util.JSON.decode(response.responseText).dataList;

		      var store = new Ext.data.Store({
		    	autoLoad : true,
		  	    proxy:new Ext.data.HttpProxy({
		  	        url : CTA.department.params.url.listRestaurants_s
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
		   var cm = new Ext.grid.ColumnModel([
        sm,{
	               header:'店铺ID',
	               dataIndex:'id',
	               renderer : function(v, metaData, record, rowIndex, colIndex, store){
	              	   var restaurant_id = record.data.id;
	              	   for(var i = 0;i<dataList.length;i++){
	              		   var restaurant_id_ = dataList[i];
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
		        }, '-', '-',

		        {
		        	id : 'range_undistributed',
		        	xtype : 'radio',
	                boxLabel: '未分配',
	                name: 'region',
	                inputValue: '未分配'
	            }, '-', {
	            	id : 'range_all',
	            	checked: true,
	            	xtype : 'radio',
	                boxLabel: '全部',
	                name: 'region',
	                inputValue: '全部'
	            }, '-',{
		            text:'查询',
		            icon : basePath+"/assets/images/toolbar/query.png",
		            listeners : {
		            	"click" : function(){
		            		//查询数据的范围
		            		var range = 1;
		            		Ext.getCmp('range_all').checked ? range = 0 : range = 1;
		            			restaurantsGrid.getStore().baseParams = {
		            			name : Ext.getCmp('restaurant_name').getValue(),
		            			range : range
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
		    		url : CTA.department.params.url.updateRestaurants,
		    		params : {
		    			dep_id : id,
		    			restaurant_ids : restaurant_ids.toString()
		    		}
		    	});
		      };
		  	  //定义添加的窗口
		  	  var addWindow = new CTA.common.SaveWindow({
		  		id : 'addWindow',
		  		title : '分配楼宇',
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
