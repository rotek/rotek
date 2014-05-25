//订单报表
Ext.ns("CTA.order");
CTA.order.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/order/listOrders",
		    dataList:[{
		          index:'id',
		          header:'订单ID'
			  },{
			    	index:'rest_name',
			    	header:'店铺名称'
		      },{
		          index:'user_name',
		          header:'下单用户称',
		          renderer : function(value){
		        	  if(value){
		        		  return value;
		        	  }
		        	  return "管理员添加";
		          }
		      },{
		    	  index:'phone',
		    	  header:'收餐电话'
		      },{
		        index:'address',
		        header:'订单地址'
		      },{
		    	  index:'remark',
		    	  header:'订单备注'
		      },{
		    	  index:'carriage',
		    	  header:'送餐费'
		      },{
		    	  index:'totalPrice',
		    	  header:'订单价格'
		      },{
		    	  index:'type',
		    	  header:'订单类型',
		    	  renderer:function(value){
		    		  if(1==value){
		    			  return "浏览器";
		    		  }else if(2 == value){
		    			  return "<span style='color:green;'>预定订单</span>";
		    		  }else if(3 == value){
		    			  return "<span style='color:green;'>安卓客户端</span>";
		    		  }else if(4 == value){
		    			  return "<span style='color:green;'>苹果客户端</span>";
		    		  }else if(5 == value){
		    			  return "<span style='color:green;'>企业团餐</span>";
		    		  }else if(6 == value){
		    			  return "<span style='color:green;'>管理员添加</span>";
		    		  }
		    	  }
		      },{
		    	  index:'ordered_time',
		    	  header:'订单提交时间',
		    	  renderer : function(value){
						return new Date(parseFloat(value)).format("Y-m-d H:i:s");
					}
		      },{
		    	  index:'status',
		    	  header:'订单状态',
		    	  renderer:function(value){
		    		  if(2==value){
		    			  return "烹饪中";
		    		  }else if(2 == value){
		    			  return "烹饪中";
		    		  }else if(3 == value){
		    			  return "配送中";
		    		  }else if(4 == value){
		    			  return "<span style='color:green;'>配送完成</span>";
		    		  }else if(5 == value){
		    			  return "<span style='color:green;'>已评价</span>";
		    		  }else if(7 == value){
		    			  return "<span style='color:#ff9900;'>下单时间未到</span>";
		    		  }else if (-1 == value){
		    			  return "<span style='color:#FF0000;'>已删除</span>";
		    		  }
		    	  }
		      }]
	},
	url : {
		addUrl : basePath + "/admin/order/addOrder",
		detailUrl : basePath + "/admin/order/getOrderDetail",
		modifyUrl : basePath + "/admin/restMenu/modifyMenu",
		dropUrl : basePath + "/admin/order/deleteOrder",
		exportData : basePath + "/admin/order/exportOrders",

		modifyStatusUrl : basePath + "/admin/order/modifyOrderStatus",
		listRestaurants_dep : basePath + "/admin/restaurant/listRestaurants_dep",
		listCategories_s : basePath + "/admin/menuCategory/listCategories_s",
		listRestaurant_menus : basePath + "/admin/restaurant/listRestaurant_menus",
		listBuildings_dep : basePath + "/admin/building/listBuildings_dep"

	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.order.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);

//添加
toolbar.regAddHandler(function(){
	  var order_obj = {
			  rest_id : '',
			  menus_submit : [],
			  menus : [],
			  totalPrice : 0,
			  calculateOrder : function(){

					order_obj.totalPrice = 0;
					order_obj.menus_submit = [];
					Ext.each(order_obj.menus, function (data, index){
						var combo  = Ext.getCmp(data[0]+'').items.items[0];
						if(combo.getStore().reader.jsonData){
							var menu_id_=combo.getValue();
							var menu_price_ = null;
							Ext.each(combo.getStore().reader.jsonData.dataList,function(data_,index_){
								if(menu_id_ && menu_id_ == data_['id']){
									menu_price_ =data_['price'];
									return false;
								}
							});

							var menu_num_ = Ext.getCmp(data[1]+'').items.items[0].getValue();
							if(!menu_price_){
								return true;
							}
							if(!menu_num_){
								return true;
							}
							if(!menu_price_){
								return true;
							}
							order_obj.totalPrice += menu_price_*menu_num_;
							order_obj.menus_submit.push([menu_id_,menu_num_]);
						}
				    });
			  }
	  };
	  var formPanel_menus = new Ext.FormPanel({
			id : 'formPanel_add',
			bodyStyle : 'overflow-x:hidden; overflow-y:scroll;padding:5px',
			height : '200',
			region: 'center',
			labelAlign : 'left',
			buttonAlign : 'right',
			frame : true,
			labelWidth : 65,
			monitorValid : true,
			items : [ {
				layout : 'column',
				border : false,
				labelSeparator : ':',
				defaults : {
					layout : 'form',
					border : false,
					columnWidth : .5
				},
				items : [{
					style:"margin:5px 0 10px 0;",
					items : [ {
						xtype : 'tbbutton',
						name : 'menu_count',
						allowBlank : true,
						text : '添加菜单',
						anchor : '90%',
						listeners : {
							click : function(){
								if(!order_obj.rest_id){
									Ext.Msg.alert("友情提示","请先选择“订单所属店铺”");
									return;
								}
								var menu_id = Math.random();
								var menu_num_id = Math.random();
								order_obj.menus.push([menu_id,menu_num_id]);
								formPanel_menus.add({
									id : menu_id,
									fieldLabel : '菜品名称',
									items : [ {
										xtype : 'combo',
										emptyText : '请选择菜品名称',
										allowBlank : false,
										anchor : '90%',
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
												},{
													name : 'price'
												} ]
											}),
											proxy : new Ext.data.HttpProxy({
												url : CTA.order.params.url.listRestaurant_menus+"?rest_id="+order_obj.rest_id
											})

										}),
										listeners : {
											'select' : function(combo,item,index){

												order_obj.calculateOrder();
												Ext.getCmp("totalPride_").setText("￥"+order_obj.totalPrice);
												Ext.getCmp("price_spread").setValue("");
												Ext.getCmp("totalPrice_display").setValue("￥"+order_obj.totalPrice);
											}
								        }
									} ]
								});
								formPanel_menus.add({
									id : menu_num_id,
									fieldLabel : '菜品数量',
									items : [ {
										xtype : 'combo',
										emptyText : '请选择菜品数量',
										allowBlank : false,
										anchor : '90%',
										triggerAction : 'all',
								        store : new Ext.data.SimpleStore({
								          fields : ['label', 'value'],
								          data : (function(){
								        	  		var datas_ = [];
								        	  		for(var i =1 ;i<=100;i++){
								        	  			var data_=[i+"份",i];
								        	  			datas_.push(data_);
								        	  		}
								        	  		return datas_;
								          })()
								        }),
								        displayField : 'label',
								        valueField : 'value',
								        mode : 'local',
								        editable : false,
								       listeners : {
								    	   'collapse' : function(){
								    		    order_obj.calculateOrder();

												Ext.getCmp("totalPride_").setText("￥"+order_obj.totalPrice);
												Ext.getCmp("price_spread").setValue("");
												Ext.getCmp("totalPrice_display").setValue("￥"+order_obj.totalPrice);
											}
								       }
									} ]
								});

								formPanel_menus.doLayout();
							}
						}
					} ]
				},{
					style:"margin:5px 0 10px 0;",
					items : [ {
						xtype : 'tbbutton',
						name : 'menu_count',
						allowBlank : true,
						text : '清空菜单',
						anchor : '90%',
						listeners : {
							click : function(){
								Ext.each(order_obj.menus, function (data, index){
									formPanel_menus.remove(data[0]+"");
									formPanel_menus.remove(data[1]+"");
							    });
								order_obj.totalPrice = 0;
								order_obj.menus_submit = [];
								order_obj.menus=[];

								Ext.getCmp("totalPride_").setText("￥"+order_obj.totalPrice);
								Ext.getCmp("price_spread").setValue("");
								Ext.getCmp("totalPrice_display").setValue("￥"+order_obj.totalPrice);
							}
						}
					} ]
				},{
					style:"margin:5px 0 10px 0;",
					columnWidth : 1.0,
					items : [ {
						id : 'totalPride_',
						xtype : 'label',
						fieldLabel : '菜单总价格',
						name : 'menu_count',
						allowBlank : true,
						text : '￥0',
						anchor : '90%'
					} ]
				} ]
			} ]
		});

  var panel_menus = new Ext.Panel({
      title : '添加菜单',
      region: 'east',
      width : 300,
      layout:'fit',
      autoScroll : true,//自动显示滚动条
      closable: true,
      collapsible: true,
      collapsed : true,
      items : [formPanel_menus]
  });


//添加订单信息
  var formPanel = new CTA.common.SFormPanel({
    items : [{
		xtype : 'combo',
		region: 'center',
		fieldLabel : '订单所属店铺',
		emptyText : '点击设置订单所属店铺',
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
				url : CTA.order.params.url.listRestaurants_dep
			})
		}),
		listeners : {
        	'select': function(combo,item,index){
        		order_obj.rest_id = combo.value;

        		Ext.each(order_obj.menus, function (data, index){
					formPanel_menus.remove(data[0]+"");
					formPanel_menus.remove(data[1]+"");
			    });
				order_obj.menus=[];
				order_obj.totalPrice = 0;
				order_obj.menus_submit = [];
				Ext.getCmp("totalPride_").setText("￥"+order_obj.totalPrice);
				Ext.getCmp("price_spread").setValue("");

				if(Ext.getCmp("totalPrice_display").getValue()){
					Ext.getCmp("totalPrice_display").setValue("￥"+order_obj.totalPrice);
				}
        	}
        }
	},{
			xtype : 'combo',
			fieldLabel : '订单所属楼宇',
			emptyText : '点击输入订单所属楼宇',
			name : 'building_id',
			triggerAction : 'all',
			hiddenName : 'building_id',
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
					url : CTA.order.params.url.listBuildings_dep
				})
			})
	},{
		id : 'totalPrice_display',
        fieldLabel : '订单总价格',
        emptyText : '点击可以添加菜单信息',
        readOnly : true,
        listeners : {
        	'focus' : function(){
        		panel_menus.expand();
        	}
        }
	},{
		xtype : 'numberfield',
		id : 'price_spread',
		name : 'price_spread',
		value : '',
		fieldLabel : '补差价',
		emptyText : '输入价格补差价(可为空)',
		allowBlank : true,
		listeners :{
			blur : function(){
				order_obj.calculateOrder();

				var sprea_price = this.getValue();
				order_obj.totalPrice += sprea_price?parseFloat(sprea_price):0;

				Ext.getCmp("totalPride_").setText("￥"+order_obj.totalPrice);
				Ext.getCmp("totalPrice_display").setValue("￥"+order_obj.totalPrice);
			}
		}
	},{
		xtype : 'numberfield',
		name : 'carriage',
		fieldLabel : '送餐费',
		emptyText : '输入送餐费'
	},{
		xtype : 'numberfield',
		name : 'phone',
		fieldLabel : '收餐电话',
		emptyText : '输入收餐电话(可为空)',
		vtype : 'telephone',
		allowBlank : true
	},{
		name : 'address',
		fieldLabel : '收餐地址',
		emptyText : '输入收餐地址(可为空)',
		minLength:1,
	    maxLength: 100,
		allowBlank : true
   },{
		xtype : 'datefield',
		name : 'ordered_time',
		fieldLabel : '订单日期',
		emptyText : '输入设置订单日期',
		format:'Y-m-d',
        editable : false,
        selectOnFocus:true
   }]
  });


  var saveHandler = function(){
      //检查表单是否填写好
      if(formPanel.getForm().isValid()){
        CTA.common.Mask.showMask({target:'addWindow'});
        var params = formPanel.getForm().getFieldValues();
        if(params.ordered_time){
        	params.ordered_time = Ext.util.Format.date(params.ordered_time, 'Y-m-d');
		}
        params.price_total = order_obj.totalPrice;
        var menus = [];
        Ext.each(order_obj.menus_submit,function(data,index){
        	menus.push(data.join(";"));
        });
        params.menus = menus.join(",");

        CTA.common.Ajax.request({
    		url : CTA.order.params.url.addUrl,
    		params : params
    	});

      }
  };
	  //定义添加的窗口
	  var addWindow = new CTA.common.SaveWindow({
		id : 'addWindow',
	    width : 700,
	    height : 400,
	    layout : 'border',
	    handler : saveHandler,
	    items : [panel_menus,formPanel]
	  });

  addWindow.show();
});


//修改订单状态
toolbar.regSetStatusHandler(function(){

	var selections = gridPanel.getSelectionModel().getSelections();
	if(!selections || selections.length <= 0){
		Ext.Msg.alert('提示', '请选择您要操作的数据!');
		return;
	}
	var ids = [];
	Ext.each(selections,function(item){
		ids.push(item.get("id"));
	});

	var formPanel = new CTA.common.SFormPanel({
		  items : [{
			        xtype : 'combo',
			        fieldLabel : '订单状态',
			        emptyText : '请选择订单状态',
			        name : 'status',
			        triggerAction : 'all',
			        store : new Ext.data.SimpleStore({
			          fields : ['label', 'value'],
			          data : [["烹饪中", "2"],["配送中", "3"],["配送完成", "4"],["已评论", "5"]]
			        }),
			        displayField : 'label',
			        valueField : 'value',
			        hiddenName : 'status',
			        mode : 'local',
			        editable : false,
			        allowBlank : false
			      }]
	  });

	var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
    	title : '修改 '+ids.length+" 条数据状态",
    	width : 600,
		height : 200,
		layout : 'border',
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
            	CTA.common.Mask.showMask({target:'updateWindow'});

            	var params = formPanel.getForm().getValues();
            	params.ids = ids.join(",");

    	    	CTA.common.Ajax.request({
    	    		url : CTA.order.params.url.modifyStatusUrl,
    	    		params : params
    	    	});
            }
        }
      });

	updateWindow.show();
});


//查询
toolbar.regQueryHandler(function(){

	  var formPanel = new CTA.common.SFormPanel({
		  items : [{
		        fieldLabel : '订单ID',
		        emptyText : '请输入订单ID',
		        name : 'id',
		        allowBlank : true
		      },{
		        fieldLabel : '店铺名称',
		        emptyText : '请输入订单所属店铺',
		        name : 'rest_name',
		        allowBlank : true
		      },{
		    	  fieldLabel : '下单用户名',
		    	  emptyText : '请输入下单用户名',
		    	  name : 'user_name',
		    	  allowBlank : true
		      },{
		    	  fieldLabel : '下单用户电话',
		    	  emptyText : '请输入下单用户电话',
		    	  name : 'phone',
		    	  allowBlank : true
		      },{
					xtype : 'combo',
					fieldLabel : '订单所属楼宇',
					emptyText : '点击选择订单所属楼宇',
					name : 'building_id',
					triggerAction : 'all',
					hiddenName : 'building_id',
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
							url : CTA.order.params.url.listBuildings_dep
						})
					})
			},{
			        xtype : 'combo',
			        fieldLabel : '订单类型',
			        emptyText : '请选择订单类型',
			        name : 'type',
			        hiddenName : 'type',
			        triggerAction : 'all',
			        store : new Ext.data.SimpleStore({
			          fields : ['label', 'value'],
			          data : [["浏览器", "1"],["预定订单", "2"],["安卓客户端", "3"],["苹果客户端", "4"],["企业团餐", "5"],["管理员添加", "5"]]

			        }),
			        displayField : 'label',
			        valueField : 'value',
			        mode : 'local',
			        editable : false,
			        allowBlank : true
		      },{
		    	  xtype : 'combo',
		    	  fieldLabel : '订单状态',
		    	  emptyText : '请选择订单状态',
		    	  name : 'status',
		    	  triggerAction : 'all',
		    	  store : new Ext.data.SimpleStore({
		    		  fields : ['label', 'value'],
		    		  data : [["已删除", "-1"],["烹饪中", "2"],["配送中", "3"],["配送完成", "4"],["已评论", "5"],["预定未发送", "7"]]
		    	  }),
		    	  displayField : 'label',
		    	  valueField : 'value',
		    	  hiddenName : 'status',
		    	  mode : 'local',
		    	  editable : false,
		    	  allowBlank : true
		      },{
		    	  xtype : 'datefield',
		    	  fieldLabel : '订单起始时间',
		    	  emptyText : '请选择订单起始时间',
		    	  name : 'start_time',
		    	  format:'Y-m-d',
		    	  editable : false,
		    	  allowBlank : true
		      },{
		    	  xtype : 'datefield',
		    	  fieldLabel : '订单结束时间',
		    	  emptyText : '请选择订单结束时间',
		    	  name : 'end_time',
		    	  format:'Y-m-d',
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
		width : 600,
		height : 380,
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
	    		url : CTA.order.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});
//查看详情
toolbar.regViewDetailHandler(function(){
	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }

	  var id = selections[0].get("id");
	  Ext.Ajax.request({
		    url : CTA.order.params.url.detailUrl,
		    params : {id : id},
		    success : function(response) {
			      var data = Ext.util.JSON.decode(response.responseText).data;

			      var formPanel = new Ext.FormPanel({
				        labelAlign: 'left',
				        labelWidth: 70, // label settings here cascade unless overridden
				        frame:true,
				        bodyStyle:'padding:10px 5px 0',
				        bodyStyle : (data.menus && data.menus.length>5) ? 'overflow-x:hidden; overflow-y:scroll;padding:5px' : '',
				        width: 600,
				        items: [{
				            layout:'column',
				            items:[{
				                columnWidth:.5,
				                layout: 'form',
				                items: [{
				                    xtype:'textfield',
				                    fieldLabel: '订单店铺',
				                    value : data.rest_name,
				                    anchor:'95%'
				                }, {
				                    xtype:'textfield',
				                    fieldLabel: '店铺电话',
				                    value : data.rest_phone,
				                    anchor:'95%'
				                }]
				            },{
				                columnWidth:.5,
				                layout: 'form',
				                items: [{
				                    xtype:'textfield',
				                    fieldLabel: '下单用户',
				                    value : data.user_name,
				                    anchor:'95%'
				                },{
				                    xtype:'textfield',
				                    fieldLabel: '下单时间',
				                    value : new Date(parseFloat(data.ordered_time)).format("Y-m-d H:i:s"),
				                    anchor:'95%'
				                }]
				            },{
				            	columnWidth:.5,
				            	layout: 'form',
				            	items: [{
				            		xtype:'textfield',
				            		fieldLabel: '收餐地址',
				            		value : data.address,
				            		anchor:'95%'
				            	},{
				            		xtype:'textfield',
				            		fieldLabel: '收餐电话',
				            		value : data.phone,
				            		anchor:'95%'
				            	}]
				            },{
				            	columnWidth:.5,
				            	layout: 'form',
				            	items: [{
				            		xtype:'textfield',
				            		fieldLabel: '菜单总价格',
				            		value : data.totalPrice,
				            		anchor:'95%'
				            	},{
				            		xtype:'textfield',
				            		fieldLabel: '送餐费',
				            		value : data.carriage,
				            		anchor:'95%'
				            	}]
				            },{
				            	columnWidth:.5,
				            	layout: 'form',
				            	items: [{
				            		xtype:'textfield',
				            		fieldLabel: '订单备注',
				            		value : data.remark,
				            		anchor:'95%'
				            	},{
				            		xtype:'textfield',
				            		fieldLabel: '订单状态',
				            		value : (function(value){
				  		    		  if(2==value){
						    			  return "烹饪中";
						    		  }else if(2 == value){
						    			  return "烹饪中";
						    		  }else if(3 == value){
						    			  return "配送中";
						    		  }else if(4 == value){
						    			  return "配送完成";
						    		  }else if(5 == value){
						    			  return "已评价";
						    		  }else if(7 == value){
						    			  return "下单时间未到";
						    		  }else if (-1 == value){
						    			  return "已删除";
						    		  }
						    	  })(data.status),
				            		anchor:'95%'
				            	}]
				            },{
				            	columnWidth:.5,
				            	layout: 'form',
				            	items: [{
				            		xtype:'textfield',
				            		fieldLabel: '配送员',
				            		value : data.deliverer_name,
				            		anchor:'95%'
				            	},{
				            		xtype:'textfield',
				            		fieldLabel: '配送员电话',
				            		value : data.deliverer_phone,
				            		anchor:'95%'
				            	}]
				            },{
					        	columnWidth:.5,
					        	layout: 'form',
					        	items: [{
				            		xtype:'textfield',
				            		fieldLabel: '订单类型',
				            		value : (function(value){
				            			 if(1==value){
				   		    			  return "浏览器";
				   		    		  }else if(2 == value){
				   		    			  return "<span style='color:green;'>预定订单</span>";
				   		    		  }else if(3 == value){
				   		    			  return "<span style='color:green;'>安卓客户端</span>";
				   		    		  }else if(4 == value){
				   		    			  return "<span style='color:green;'>苹果客户端</span>";
				   		    		  }else if(5 == value){
				   		    			  return "<span style='color:green;'>企业团餐</span>";
				   		    		  }else if(6 == value){
				   		    			  return "<span style='color:green;'>管理员添加</span>";
				   		    		  }
				            		})(data.type),
				            		anchor:'95%'
				            	}]
				            },{
				            	columnWidth:.5,
				            	layout: 'form',
				            	items: [{
					            	xtype:'textfield',
					            	fieldLabel: '配送方',
					            	value : (function(type){
					            			if(1 == type){
					            				return "我们配送";
					            			}else if(2 == type){
					            				return "店铺配送";
					            			}else {
					            				return "未设置";
					            			}
					            	}(data.deli_type)),
					            	anchor:'95%'
					            }]
					        }]
				        }]
				    });

			     Ext.each(data.menus,function(menu,index){

			    	 formPanel.add({
			    		  columnWidth:.99,
			    		  layout: 'form',
			    		  style : (function(){
			    			  return 0==index? 'margin-top:30px':'margin-top:0px';
			    		  })(index),
			    		  items: [{
			    			  xtype:'textfield',
			    			  fieldLabel: '菜单 '+(index+1),
			    			  value : menu.name + "*" + menu.count + "=" +"￥"+(menu.price*menu.count) ,
			    			  anchor:'98%'
			    		  }]
			    	  });
			     });

				  var detailWindow = new CTA.common.Window({
				    	title : '查看订单详情',
				    	width : 750,
					    height : 430,
					    layout : 'fit',
				        items : [formPanel]
				      });
				      detailWindow.show();
		    }
	    });
});
//导出数据
toolbar.regExportDataHandler(function(){
	var selections = gridPanel.getSelectionModel().getSelections();
	if(!selections || selections.length <= 0){
		Ext.Msg.confirm("警告","您没有选择要导出的订单，默认导出当前查询的所有数据，继续吗？",function(button){
		    if('yes' == button){
		    	var params = [];

		    	params.push("?r=1");
		    	params.push("building_id="+(CTA.common.Constant.queryParams.building_id?CTA.common.Constant.queryParams.building_id:""));
		    	params.push("end_time="+(CTA.common.Constant.queryParams.end_time?Ext.util.Format.date(CTA.common.Constant.queryParams.end_time,'Y-m-d'):""));
		    	params.push("id="+(CTA.common.Constant.queryParams.id?CTA.common.Constant.queryParams.id:""));
		    	params.push("phone="+(CTA.common.Constant.queryParams.phone?CTA.common.Constant.queryParams.phone:""));
		    	params.push("rest_name="+(CTA.common.Constant.queryParams.rest_name?CTA.common.Constant.queryParams.rest_name:""));
		    	params.push("start_time="+(CTA.common.Constant.queryParams.start_time?Ext.util.Format.date(CTA.common.Constant.queryParams.start_time,'Y-m-d'):""));
		    	params.push("status="+(CTA.common.Constant.queryParams.status?CTA.common.Constant.queryParams.status:""));
		    	params.push("type="+(CTA.common.Constant.queryParams.type?CTA.common.Constant.queryParams.type:""));
		    	params.push("user_name="+(CTA.common.Constant.queryParams.user_name?CTA.common.Constant.queryParams.user_name:""));
		    	window.location.href=CTA.order.params.url.exportData+params.join("&");

		    }
		 });
		return;
	}
	var ids = [];
	Ext.each(selections,function(item){
		ids.push(item.get("id"));
	});

	Ext.Msg.confirm("警告","您确定导出这  "+ids.length+" 条数据吗？",function(button){
	    if('yes' == button){

	    	var params = [];

	    	params.push("?r=1");
	    	params.push("building_id="+(CTA.common.Constant.queryParams.building_id?CTA.common.Constant.queryParams.building_id:""));
	    	params.push("end_time="+(CTA.common.Constant.queryParams.end_time?Ext.util.Format.date(CTA.common.Constant.queryParams.end_time,'Y-m-d'):""));
	    	params.push("id="+(CTA.common.Constant.queryParams.id?CTA.common.Constant.queryParams.id:""));
	    	params.push("phone="+(CTA.common.Constant.queryParams.phone?CTA.common.Constant.queryParams.phone:""));
	    	params.push("rest_name="+(CTA.common.Constant.queryParams.rest_name?CTA.common.Constant.queryParams.rest_name:""));
	    	params.push("start_time="+(CTA.common.Constant.queryParams.start_time?Ext.util.Format.date(CTA.common.Constant.queryParams.start_time,'Y-m-d'):""));
	    	params.push("status="+(CTA.common.Constant.queryParams.status?CTA.common.Constant.queryParams.status:""));
	    	params.push("type="+(CTA.common.Constant.queryParams.type?CTA.common.Constant.queryParams.type:""));
	    	params.push("user_name="+(CTA.common.Constant.queryParams.user_name?CTA.common.Constant.queryParams.user_name:""));
	    	params.push("ids="+ids.join(","));

	    	window.location.href=CTA.order.params.url.exportData+params.join("&");
	    }
	 });
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
