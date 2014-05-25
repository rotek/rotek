//设置店铺菜单信息
Ext.ns("CTA.restaurant.menu");
CTA.restaurant.menu.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/restMenu/listMenus",
		    dataList:[{
		          index:'id',
		          header:'菜单ID'
		      },{
		          index:'name',
		          header:'菜单名称'
		      },{
		        index:'price',
		        header:'菜单价格'
		      },{
		    	  index:'pic',
		    	  header:'菜单图片',
		    	  renderer:function(value){
			          if(value){
			            return value;
			          }else{
			            return "无";
			          }
			        }
		      },{
		    	  index:'rest_name',
		    	  header:'菜品所属店铺'
		      },{
		        index:'status',
		        header:'是否推荐',
		        renderer:function(value){
		          if(2==value){
		            return "<span style='color:green;'>店长推荐</span>";
		          }else{
		            return "普通菜品";
		          }
		        }
		      },{
		    	  index:'status',
		    	  header:'菜单状态',
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
		addUrl : basePath + "/admin/restMenu/addMenu",
		detailUrl : basePath + "/admin/restMenu/getMenuDetail",
		modifyUrl : basePath + "/admin/restMenu/modifyMenu",
		dropUrl : basePath + "/admin/restMenu/deleteMenu",
		listButtonUrl :  basePath + "/admin/button/listButtons_s",

		listRestaurants_s : basePath + "/admin/restaurant/listRestaurants_dep",
		listCategories_s : basePath + "/admin/menuCategory/listCategories_s"

	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.restaurant.menu.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.restaurant.menu.params.url.addUrl
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
	fileUpload : true,
    items : [{
        fieldLabel : '菜单名称',
        emptyText : '请输入菜单名称',
        name : 'name',
        minLength:1,
        maxLength: 50
      },{
    	xtype : 'numberfield',
        fieldLabel : '菜单价格',
        emptyText : '请输入菜单价格',
        name : 'price'
      },{
			xtype : 'combo',
			fieldLabel : '所属菜品大类',
			emptyText : '点击设置所属菜品大类',
			name : 'cate_id',
			hiddenName : 'cate_id',
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
					url : CTA.restaurant.menu.params.url.listCategories_s
				})
			})
		},{
			xtype : 'combo',
			fieldLabel : '所属店铺',
			emptyText : '点击设置所属店铺',
			name : 'rest_id',
			hiddenName : 'rest_id',
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
					url : CTA.restaurant.menu.params.url.listRestaurants_s
				})
			})
		},{
	        xtype : 'combo',
	        fieldLabel : '是否推荐',
	        emptyText : '请选择菜单是否推荐',
	        name : 'recommend',
	        hiddenName : 'recommend',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["店长推荐", "2"],["普通菜品", "1"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        mode : 'local',
	        editable : false,
	        listeners : {
	        	'select': function(combo,item,index){
	        		var menu_type = item.data.value;
	        		//店长推荐
	        		if(1 == menu_type){
	        			Ext.getCmp("menu_pic").enable();
	        		}else {
	        			Ext.getCmp("menu_pic").disable();
	        		}
	        	}
	        }
		},{
			xtype : 'combo',
			fieldLabel : '菜单状态',
			emptyText : '请选择菜单状态',
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
	      },{
	          fieldLabel : '菜品配料',
	          emptyText : '请输入菜品配料',
	          name : 'mix',
	          allowBlank : true,
	          minLength:1,
	          maxLength: 200
	      },{
	    	  fieldLabel : '菜品描述',
	    	  emptyText : '请输入菜品描述',
	    	  name : 'descr',
	    	  allowBlank : true,
	    	  minLength:1,
	    	  maxLength: 200
        },{
		    	id : 'menu_pic',
		      	fieldLabel : '菜品图片',
		    	name : 'menu_pic',
				text : "点击上传菜品图片",
				allowBlank : true,
				inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
				blankText : '请上传菜品图片'
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
    url : CTA.restaurant.menu.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
    		fileUpload : true,
    	    items : [{
    	    	xtype : 'hidden',
    	        fieldLabel : '菜单ID',
    	        name : 'id'
    	      },{
    	        fieldLabel : '菜单名称',
    	        emptyText : '请输入菜单名称',
    	        name : 'name',
    	        minLength:1,
    	        maxLength: 50
    	      },{
    	    	xtype : 'numberfield',
    	        fieldLabel : '菜单价格',
    	        emptyText : '请输入菜单价格',
    	        name : 'price'
    	      },{
    				xtype : 'combo',
    				fieldLabel : '所属菜品大类',
    				emptyText : '点击设置所属菜品大类',
    				name : 'cate_id',
    				hiddenName : 'cate_id',
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
    						url : CTA.restaurant.menu.params.url.listCategories_s
    					})
    				})
    			},{
    				id : 'restaurant_id',
    				xtype : 'combo',
    				fieldLabel : '所属店铺',
    				emptyText : '点击设置所属店铺',
    				name : 'rest_id',
    				hiddenName : 'rest_id',
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
    						url : CTA.restaurant.menu.params.url.listRestaurants_s
    					}),
    					autoLoad : true,
    					listeners : {
    						load : function (){
    							Ext.getCmp('restaurant_id').setValue(data.rest_id);
    						}
    					}
    				})
    			},{
    		        xtype : 'combo',
    		        fieldLabel : '是否推荐',
    		        emptyText : '请选择菜单是否推荐',
    		        name : 'recommend',
    		        hiddenName : 'recommend',
    		        triggerAction : 'all',
    		        store : new Ext.data.SimpleStore({
    		          fields : ['label', 'value'],
    		          data : [["店长推荐", "1"],["普通菜品", "2"]]
    		        }),
    		        displayField : 'label',
    		        valueField : 'value',
    		        mode : 'local',
    		        editable : false,
    		        listeners : {
    		        	'select': function(combo,item,index){
    		        		var menu_type = item.data.value;
    		        		//店长推荐
    		        		if(1 == menu_type){
    		        			Ext.getCmp("menu_pic").enable();
    		        			Ext.getCmp("menu_pic_modify").enable();
    		        		}else {
    		        			Ext.getCmp("menu_pic").disable();
    		        			Ext.getCmp("menu_pic_modify").disable();
    		        		}
    		        	}
    		        }
    			},{
    				xtype : 'combo',
    				fieldLabel : '菜单状态',
    				emptyText : '请选择菜单状态',
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
    		      },{
    		          fieldLabel : '菜品配料',
    		          emptyText : '请输入菜品配料',
    		          name : 'mix',
    		          allowBlank : true,
    		          minLength:1,
    		          maxLength: 200
    		      },{
    		    	  fieldLabel : '菜品描述',
    		    	  emptyText : '请输入菜品描述',
    		    	  name : 'descr',
    		    	  allowBlank : true,
    		    	  minLength:1,
    		    	  maxLength: 200
    	        },{
    			    	id : 'menu_pic',
    			      	fieldLabel : '菜品图片',
    			    	name : 'pic',
    					allowBlank : true,
    					readOnly : true,
    					blankText : '请上传菜品图片'
		      },{
		    	  id : 'menu_pic_modify',
		    	  fieldLabel : '修改店铺图片',
		    	  name : 'menu_pic',
		    	  text : "点击上传菜品图片",
		    	  inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
		    	  blankText : '请上传菜品图片',
		    	  allowBlank : true
		        }],
    	    data : data
    	  });

      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
    	width : 600,
		height : 400,
		layout : 'border',
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
            	CTA.common.Mask.showMask({target:'updateWindow'});
    			formPanel.commit({
    				url : CTA.restaurant.menu.params.url.modifyUrl
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
		        fieldLabel : '菜单ID',
		        emptyText : '请输入菜单ID',
		        name : 'id',
		        allowBlank : true
		      },{
		        fieldLabel : '菜单名称',
		        emptyText : '请输入菜单名称',
		        name : 'name',
		        allowBlank : true
		      },{
  				xtype : 'combo',
				fieldLabel : '所属菜品大类',
				emptyText : '点击设置所属菜品大类',
				name : 'cate_id',
				hiddenName : 'cate_id',
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
						url : CTA.restaurant.menu.params.url.listCategories_s
					})
				}),
		      allowBlank : true
			},{
  				xtype : 'combo',
				fieldLabel : '所属店铺',
				emptyText : '点击设置所属店铺',
				name : 'rest_id',
				hiddenName : 'rest_id',
				triggerAction : 'all',
				displayField : 'name',
				valueField : 'id',
				editable : false,
				allowBlank : true,
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
						url : CTA.restaurant.menu.params.url.listRestaurants_s
					})
				})
			},{
		        xtype : 'combo',
		        fieldLabel : '是否推荐',
		        emptyText : '请选择菜单是否推荐',
		        name : 'recommend',
		        hiddenName : 'recommend',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["店长推荐", "1"],["普通菜单", "2"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        mode : 'local',
		        editable : false,
		        allowBlank : true
		      },{
			        xtype : 'combo',
			        fieldLabel : '菜单状态',
			        emptyText : '请选择菜单状态',
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
	    		url : CTA.restaurant.menu.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
