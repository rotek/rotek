/** 设置算法 管理  */
Ext.ns("ROTEK.ALGORITHMS7");
ROTEK.ALGORITHMS7.params = {
	gridParam : {
		url : basePath + "/admin/algorithms/listAlgorithms/7",
		dataList : [ {
			index : 'id',
			header : 'ID',
			width : 20,
			align : 'center'
		}, {
			index : 'customer_name',
			header : '用户名称',
			width : 30,
			align : 'center'
		}, {
			index : 'project_name',
			header : '工程名称',
			width : 30,
			align : 'center'
		}, {
			index : 'component_group_name',
			header : '零件组名称',
			width : 30,
			align : 'center'
		}, {
			index : 'component_name',
			header : '零件名称',
			width : 30,
			align : 'center'
		}, {
			index : 'specificpart_param',
			header : '零件参数',
			width : 30,
			align : 'center'
		}, {
			index : 'sitereal_field_name',
			header : '现场参数',
			width : 30,
			align : 'center'
		}, {
			index : 'glq_cebfb',
			header : '超额百分比设定值',
			width : 30,
			align : 'center',
		}, {
			index : 'mxt_csllcesj',
			header : '超额时间设定值',
			width : 30,
			align : 'center',
		},{
			index : 'status',
			header : '状态',
			renderer : function(value) {
				if (1 == value) {
					return "<span style='color:green;'>有效</span>";
				} else {
					return "<span style='color:red'>无效</span>";
				}
			},
			width : 20,
			align : 'center'
		} ]
	},
	url : {
		addUrl : basePath + "/admin/algorithms/addAlgorithms/7",
		detailUrl : basePath + "/admin/algorithms/getAlgorithmDetail",
		modifyUrl : basePath + "/admin/algorithms/modifyAlgorithms/7",
		dropUrl : basePath + "/admin/algorithms/deleteAlgorithms",
		listProejctUrl : basePath + "/admin/algorithms/selectProjectByType/0"  // 1、托管服务   2、EMC工程     0、不限
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.ALGORITHMS7.params.gridParam);
var toolbar = new CTA.common.Toolbar();

//添加工程信息
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function() {
		var saveHandler = function() {
			//检查表单是否填写好
			if (formPanel.getForm().isValid()) {
				formPanel.commit({
					url : ROTEK.ALGORITHMS7.params.url.addUrl
				});
			}
		};
		//定义添加的窗口
		var addWindow = new CTA.common.SaveWindow({
			id : 'addWindow',
			width : '40%',
			height : 510,
			layout : 'fit',
			handler : saveHandler
		});

		// 读取工程数据
		var ProjectStore = new Ext.data.Store({
			reader : new Ext.data.JsonReader({
				root : 'projectList',
				fields : [ 
				     { name : 'id'}, 
				     { name : 'gcmc'},
				     { name : 'customer_name'},
				     { name : 'r_customer_id'}
				]
			}),
			proxy : new Ext.data.HttpProxy({
				url : ROTEK.ALGORITHMS7.params.url.listProejctUrl
			})
		})
		
		// 读取零件分类数据
		var ComponentTypeStore = new Ext.data.SimpleStore({
			fields : [ 'label', 'value' ],
			data : [[ "泵", "1" ], [ "砂滤器", "2" ], [ "碳滤器", "3" ], [ "软化器", "4" ]
				, [ "过滤器", "5" ], [ "膜", "6" ], [ "紫外杀菌器", "7" ], [ "水箱", "8" ], [ "加药装置", "9" ]]
		})
		
		// 监测分类数据
		var MonitorTypeStore = new Ext.data.SimpleStore({
			fields : [ 'label', 'value' ],
			data : [[ "托管工程-水质监测", "1" ], [ "托管工程-流量监测", "2" ], [ "托管工程-压力监测", "3" ], [ "EMC工程-水质监测", "4" ]
				, [ "EMC工程-流量监测", "5" ], [ "EMC工程-压力监测", "6" ]]
		})
		
		// 读取零件组数据
		var GroupStore = new Ext.data.Store({
			reader : new Ext.data.JsonReader({
				root : 'grouptList',
				fields : [ {name : 'id'}, {name : 'group_mc'} ]
			}),
			proxy : new Ext.data.HttpProxy({
				url : basePath + "/admin/algorithms/selectGroupByPid/0/0"
			})
		})		
		
		// 读取零件数据
		var GroupDetailStore = new Ext.data.Store({
			reader : new Ext.data.JsonReader({
				root : 'groupDetailList',
				fields : [ {name : 'id'}, {name : 'specific_part'}, {name : 'specific_bh'} ]
			}),
			proxy : new Ext.data.HttpProxy({
				url : basePath + "/admin/algorithms/selectGroupDetailByIds/0/0/0"
			})
		})

		// 读取零件参数数据
		var ComponentParamsStore = new Ext.data.Store({
			reader : new Ext.data.JsonReader({
				root : 'paramList',
				fields : [ {name : 'columnComment'} ]
			}),
			proxy : new Ext.data.HttpProxy({
				url : basePath + "/admin/algorithms/getParamsByGroupId/0"
			})
		})
		
		// 读取现场表
		var LocalTableStore = new Ext.data.Store({
			reader : new Ext.data.JsonReader({
				root : 'locTableList',
				fields : [ {name : 'tableName'} ]
			}),
			proxy : new Ext.data.HttpProxy({
				url : basePath + "/admin/algorithms/getLocalTables"
			})
		})
		
		// 读取现场表对应的字段
		var LocalTableColumnStore = new Ext.data.Store({
			reader : new Ext.data.JsonReader({
				root : 'localParamList',
				fields : [ {name : 'columnName'} ]
			}),
			proxy : new Ext.data.HttpProxy({
				url : basePath + "/admin/algorithms/selectLocalParams/aa"
			})
		})

		//定义添加窗口中的form
		var formPanel = new CTA.common.SFormPanel({
			items : [{
 				xtype : 'combo',
    			fieldLabel : '工程',
    			id:'PROJECT_COMBO',
    			emptyText : '请选择工程',
    			name : 'id',
    			hiddenName : 'r_project_id',
    			triggerAction : 'all',
    			displayField : 'gcmc',
    			valueField : 'id',
    			editable : false,
    			allowBlank : false,
    			store : ProjectStore,
 				width : 445,
 				listeners : {
 					'select' : function(ComboObj, record, index) {
 						GroupStore.proxy = new Ext.data.HttpProxy({
							url : basePath + "/admin/algorithms/selectGroupByPid/" + record.get('id') + "/0"
						});
 						Ext.getCmp('CUSTOMER_FIELD').setValue(record.get('customer_name'));
 						Ext.getCmp('CUSTOMER_FIELD_ID').setValue(record.get('r_customer_id'));
 						
 						if(Ext.getCmp('GROUPCOMB').getValue() != ""){
 							GroupStore.removeAll() ;  //清空缓存的数据
 							Ext.getCmp('GROUPCOMB').setValue("");
 	 						Ext.getCmp('GROUPCOMB').setRawValue("");
 						}
 						if(Ext.getCmp('LEIBIE_COMBO').getValue() != ""){
 	 						Ext.getCmp('LEIBIE_COMBO').setValue("");
 						}
 						if(Ext.getCmp('COMPONENT_COMBO').getValue() != ""){
 							GroupDetailStore.removeAll() ;  //清空缓存的数据
 	 						Ext.getCmp('COMPONENT_COMBO').setValue("");
 	 						Ext.getCmp('COMPONENT_COMBO').setRawValue("");
 						}
 						if(Ext.getCmp('COMPONENT_CODE').getValue() != ""){
 	 						Ext.getCmp('COMPONENT_CODE').setValue("");
 						}
 						GroupDetailStore.load();
 						GroupStore.load();
 					}
 				}
 			},{
				xtype : 'textfield',
				id : 'CUSTOMER_FIELD',
				fieldLabel : '客户名称',
				emptyText : '客户名称',
				//name : 'customer_name',
				readOnly : true ,
				width : 180
			},{
				xtype : 'hidden',
				id : 'CUSTOMER_FIELD_ID',
				fieldLabel : '客户ID',
				name : 'r_customer_id',
				readOnly : true,
			},{
				xtype : 'combo',
				id : 'MONITOR_COMBO',
    			fieldLabel : '监测类别',
    			emptyText : '请监测类别',
    			name : 'value',
    			hiddenName : 'monitor_type',
    			triggerAction : 'all',
    			displayField : 'label',
    			valueField : 'value',
    			mode : 'local',
    			editable : false,
    			allowBlank : false,
    			store : MonitorTypeStore,
 				width : 445
 			},{
				xtype : 'combo',
				id : 'LEIBIE_COMBO',
    			fieldLabel : '零件类别',
    			emptyText : '请零件类别',
    			name : 'value',
    			hiddenName : 'algorithm_type',
    			triggerAction : 'all',
    			displayField : 'label',
    			valueField : 'value',
    			mode : 'local',
    			editable : false,
    			allowBlank : false,
    			store : ComponentTypeStore,
 				width : 445,
 				listeners : {
 					'select' : function(ComboObj, record, index) {
 						GroupStore.proxy = new Ext.data.HttpProxy({
							url : basePath + "/admin/algorithms/selectGroupByPid/"+Ext.getCmp('PROJECT_COMBO').getValue()+"/" +ComboObj.value
						});
 						if(Ext.getCmp('GROUPCOMB').getValue() != ""){
 							GroupStore.removeAll() ;  //清空缓存的数据
 	 						Ext.getCmp('GROUPCOMB').setValue("");
 	 						Ext.getCmp('GROUPCOMB').setRawValue("");
 						}
 						if(Ext.getCmp('COMPONENT_COMBO').getValue() != ""){
 							GroupDetailStore.removeAll() ;  //清空缓存的数据
 	 						Ext.getCmp('COMPONENT_COMBO').setValue("");
 	 						Ext.getCmp('COMPONENT_COMBO').setRawValue("");
 						}
 						if(Ext.getCmp('COMPONENT_CODE').getValue() != ""){
 	 						Ext.getCmp('COMPONENT_CODE').setValue("");
 						}
 						GroupStore.load();
 						GroupDetailStore.load();
 					}
 				}
 			},{
 				xtype : 'combo',
 				id : 'GROUPCOMB',
    			fieldLabel : '组',
    			emptyText : '请选择组',
    			name : 'id',
    			hiddenName : 'r_component_group_id',
    			triggerAction : 'all',
    			displayField : 'group_mc',
    			valueField : 'id',
    			editable : false,
    			allowBlank : false,
    			store : GroupStore,
 				width : 445,
 				listeners : {
 					'select' : function(ComboObj, record, index) {
 						GroupDetailStore.proxy = new Ext.data.HttpProxy({
							url : basePath + "/admin/algorithms/selectGroupDetailByIds/"+Ext.getCmp('PROJECT_COMBO').getValue()+"/"+record.get('id')+"/"+Ext.getCmp('LEIBIE_COMBO').getValue()
						});
 						if(Ext.getCmp('COMPONENT_COMBO').getValue() != ""){
 							GroupDetailStore.removeAll() ;  //清空缓存的数据
 	 						Ext.getCmp('COMPONENT_COMBO').setValue("");
 	 						Ext.getCmp('COMPONENT_COMBO').setRawValue("");
 						}
 						if(Ext.getCmp('COMPONENT_CODE').getValue() != ""){
 	 						Ext.getCmp('COMPONENT_CODE').setValue("");
 						}
 						GroupDetailStore.load();
 					}
 				}
 			},{
 				xtype : 'combo',
    			fieldLabel : '零件名称',
    			id:'COMPONENT_COMBO',
    			emptyText : '请选择零件名称',
    			name : 'specific_part',
    			hiddenName : 'r_component_detail_id',
    			triggerAction : 'all',
    			displayField : 'specific_part',
    			valueField : 'id',
    			editable : false,
    			allowBlank : false,
    			store : GroupDetailStore,
 				width : 445,
 				listeners : {
 					'select' : function(ComboObj, record, index) {
 						Ext.getCmp('COMPONENT_CODE').setValue(record.get('specific_bh'));
 						ComponentParamsStore.load();
 					}
 				}
 			},{
				xtype : 'textfield',
				id : 'COMPONENT_CODE',
				fieldLabel : '零件编号',
				emptyText : '请输入零件编号',
				//name : 'specific_bh',
				readOnly : true ,
				allowBlank : false,
				width : 180
			},{
 				xtype : 'combo',
 				id : 'COMPON_PARAM',
    			fieldLabel : '零件参数',
    			emptyText : '请选择零件参数',
    			name : 'columnComment',
    			hiddenName : 'specificpart_param',
    			triggerAction : 'all',
    			displayField : 'columnComment',
    			valueField : 'columnComment',
    			editable : false,
    			allowBlank : false,
    			store : ComponentParamsStore,
 				width : 445,
 				listeners : {
 					'select' : function(ComboObj, record, index) {
 						var tempParam = Ext.getCmp('COMPON_PARAM').getValue();
 						var componentId = Ext.getCmp('COMPONENT_COMBO').getValue();
 						
 						CTA.common.Ajax.request({
 							url :  basePath + "/admin/algorithms/getParamsValue",
 							params : {
 								componentId : componentId,
 								paramName : tempParam
 							},
 							success : function(response) {
 								
 							}
 						});
 					}
 				}
 			},{
				xtype : 'textfield',
				id : 'PAREM_VALUE',
				fieldLabel : '参数额定值(s):',
				emptyText : '请输入参数额定值',
				//name : 'specific_bh',
				readOnly : true ,
				allowBlank : false,
				width : 180
			}, {
				fieldLabel : '超额百分比设定值(N):',
				emptyText : '请输入超额百分比设定值',
				name : 'glq_cebfb',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '超额时间设定值(N):',
				emptyText : '请输入超额时间设定值',
				name : 'mxt_csllcesj',
				minLength : 1,
				maxLength : 100
			},{
 				xtype : 'combo',
 				id : 'LOCAL_TABLES',
    			fieldLabel : '现场表',
    			emptyText : '请选择现场表',
    			name : 'tableName',
    			hiddenName : 'sitereal_table_name',
    			triggerAction : 'all',
    			displayField : 'tableName',
    			valueField : 'tableName',
    			editable : false,
    			allowBlank : false,
    			store : LocalTableStore,
 				width : 445,
 				listeners : {
 					'select' : function(ComboObj, record, index) {
 						LocalTableColumnStore.proxy = new Ext.data.HttpProxy({
							url : basePath + "/admin/algorithms/selectLocalParams/"+Ext.getCmp('LOCAL_TABLES').getValue()
						});
 						if(Ext.getCmp('LOCAL_TABLE_PARAM').getValue() != ""){
 							GroupDetailStore.removeAll() ;  //清空缓存的数据
 	 						Ext.getCmp('LOCAL_TABLE_PARAM').setValue("");
 	 						Ext.getCmp('LOCAL_TABLE_PARAM').setRawValue("");
 						}
 						LocalTableColumnStore.load();
 					}
 				}
 			},{
 				xtype : 'combo',
    			fieldLabel : '现场表参数',
    			id:'LOCAL_TABLE_PARAM',
    			emptyText : '请选择现场表参数',
    			name : 'columnName',
    			hiddenName : 'sitereal_field_name',
    			triggerAction : 'all',
    			displayField : 'columnName',
    			valueField : 'columnName',
    			editable : false,
    			allowBlank : false,
    			store : LocalTableColumnStore,
 				width : 445
 			},{
				xtype : 'textfield',
				fieldLabel : '提示别名',
				emptyText : '请输入提示别名',
				name : 'algorithm_alias',
				allowBlank : false,
				width : 180
			},{
				xtype : 'textfield',
				fieldLabel : '提示内容',
				emptyText : '请输入提示内容',
				name : 'tip_content',
				allowBlank : false,
				width : 180
			}]
		});
		addWindow.add(formPanel);
		addWindow.show();
	});
}

//修改信息
if(toolbar.get("button_modify")){
	toolbar.get("button_modify").setHandler(function() {
		var selections = gridPanel.getSelectionModel().getSelections();
		if (!selections || selections.length <= 0) {
			Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
			return;
		}
		var id = selections[0].get("id");
		Ext.Ajax.request({
			url : ROTEK.ALGORITHMS7.params.url.detailUrl,
			params : {
				id : id
			},
			success : function(response) {
				var data = Ext.util.JSON.decode(response.responseText).data;
				// 读取工程数据
				var ProjectStore = new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'projectList',
						fields : [ 
						     { name : 'id'}, 
						     { name : 'gcmc'},
						     { name : 'customer_name'},
						     { name : 'r_customer_id'}
						]
					}),
					proxy : new Ext.data.HttpProxy({
						url : ROTEK.ALGORITHMS7.params.url.listProejctUrl
					}),
					autoLoad : true,
    				listeners : {
    					load : function (){
	    					Ext.getCmp('PROJECT_COMBO').setValue(data.r_project_id);
    					}
    				}
				})
				
				// 读取零件分类数据
				var ComponentTypeStore = new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data : [
				        [ "泵", "1" ], [ "砂滤器", "2" ], [ "碳滤器", "3" ], [ "软化器", "4" ], 
				        [ "过滤器", "5" ], [ "膜", "6" ], [ "紫外杀菌器", "7" ], [ "水箱", "8" ], [ "加药装置", "9" ]
				    ]
				})
				// 监测分类数据
				var MonitorTypeStore = new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data : [[ "托管工程-水质监测", "1" ], [ "托管工程-流量监测", "2" ], [ "托管工程-压力监测", "3" ], [ "EMC工程-水质监测", "4" ]
						, [ "EMC工程-流量监测", "5" ], [ "EMC工程-压力监测", "6" ]]
				})
				// 读取零件组数据
				var GroupStore = new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'grouptList',
						fields : [ {name : 'id'}, {name : 'group_mc'} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : basePath + "/admin/algorithms/selectGroupByPid/0/0"
					})/*,
					autoLoad : true,
    				listeners : {
    					load : function (){
    						console.log("1 == " + data.r_component_group_id);
	    					Ext.getCmp('GROUPCOMB').setValue(data.r_component_group_id);
    					}
    				}*/
				})		
				
				// 读取零件数据
				var GroupDetailStore = new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'groupDetailList',
						fields : [ {name : 'id'}, {name : 'specific_part'}, {name : 'specific_bh'} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : basePath + "/admin/algorithms/selectGroupDetailByIds/0/0/0"
					})/*,
					autoLoad : true,
    				listeners : {
    					load : function (){
    						console.log("2 == " + data.r_component_detail_id);
	    					Ext.getCmp('COMPONENT_COMBO').setValue(data.r_component_detail_id);
    					}
    				}*/
				})
				
				// 读取零件参数数据
				var ComponentParamsStore = new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'paramList',
						fields : [ {name : 'columnComment'} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : basePath + "/admin/algorithms/getParamsByGroupId/0"
					})
				})
				
				// 读取现场表
				var LocalTableStore = new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'locTableList',
						fields : [ {name : 'tableName'} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : basePath + "/admin/algorithms/getLocalTables"
					})
				})
				
				// 读取现场表对应的字段
				var LocalTableColumnStore = new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'localParamList',
						fields : [ {name : 'columnName'} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : basePath + "/admin/algorithms/selectLocalParams/aa"
					})
				})

				//定义修改窗口中的form
				var formPanel = new CTA.common.SFormPanel({
					items : [{
						xtype : 'hidden',
						id : 'ID',
						name : 'id',
						value:data.id,
						readOnly : true,
					},{
		 				xtype : 'combo',
		    			fieldLabel : '工程',
		    			id:'PROJECT_COMBO',
		    			emptyText : '请选择工程',
		    			name : 'id',
		    			hiddenName : 'r_project_id',
		    			triggerAction : 'all',
		    			displayField : 'gcmc',
		    			valueField : 'id',
		    			editable : false,
		    			allowBlank : false,
		    			store : ProjectStore,
		 				width : 445,
		 				//value : data.project_name,
		 				listeners : {
		 					'select' : function(ComboObj, record, index) {
		 						GroupStore.proxy = new Ext.data.HttpProxy({
									url : basePath + "/admin/algorithms/selectGroupByPid/" + record.get('id') + "/0"
								});
		 						Ext.getCmp('CUSTOMER_FIELD').setValue(record.get('customer_name'));
		 						Ext.getCmp('CUSTOMER_FIELD_ID').setValue(record.get('r_customer_id'));
		 						
		 						if(Ext.getCmp('GROUPCOMB').getValue() != ""){
		 							GroupStore.removeAll() ;  //清空缓存的数据
		 							Ext.getCmp('GROUPCOMB').setValue("");
		 	 						Ext.getCmp('GROUPCOMB').setRawValue("");
		 						}
		 						if(Ext.getCmp('LEIBIE_COMBO').getValue() != ""){
		 	 						Ext.getCmp('LEIBIE_COMBO').setValue("");
		 						}
		 						if(Ext.getCmp('COMPONENT_COMBO').getValue() != ""){
		 							GroupDetailStore.removeAll() ;  //清空缓存的数据
		 	 						Ext.getCmp('COMPONENT_COMBO').setValue("");
		 	 						Ext.getCmp('COMPONENT_COMBO').setRawValue("");
		 						}
		 						if(Ext.getCmp('COMPONENT_CODE').getValue() != ""){
		 	 						Ext.getCmp('COMPONENT_CODE').setValue("");
		 						}
		 						GroupDetailStore.load();
		 						GroupStore.load();
		 					}
		 				}
		 			},{
						xtype : 'textfield',
						id : 'CUSTOMER_FIELD',
						fieldLabel : '客户名称',
						emptyText : '客户名称',
						//name : 'customer_name',
						value : data.customer_name,
						readOnly : true ,
						width : 180
					},{
						xtype : 'hidden',
						id : 'CUSTOMER_FIELD_ID',
						fieldLabel : '客户ID',
						name : 'r_customer_id',
						value:data.r_customer_id,
						readOnly : true,
					},{
						xtype : 'combo',
						id : 'MONITOR_COMBO',
		    			fieldLabel : '监测类别',
		    			emptyText : '请监测类别',
		    			name : 'value',
		    			hiddenName : 'monitor_type',
		    			triggerAction : 'all',
		    			displayField : 'label',
		    			valueField : 'value',
		    			mode : 'local',
		    			editable : false,
		    			allowBlank : false,
		    			store : MonitorTypeStore,
		 				width : 445
		 			},{
						xtype : 'combo',
						id : 'LEIBIE_COMBO',
		    			fieldLabel : '零件类别',
		    			emptyText : '请零件类别',
		    			name : 'value',
		    			hiddenName : 'algorithm_type',
		    			triggerAction : 'all',
		    			displayField : 'label',
		    			valueField : 'value',
		    			mode : 'local',
		    			editable : false,
		    			allowBlank : false,
		    			store : ComponentTypeStore,
		    			value : data.algorithm_type,
		    			width : 445,
		 				listeners : {
		 					'select' : function(ComboObj, record, index) {
		 						GroupStore.proxy = new Ext.data.HttpProxy({
									url : basePath + "/admin/algorithms/selectGroupByPid/"+Ext.getCmp('PROJECT_COMBO').getValue()+"/" +ComboObj.value
								});
		 						if(Ext.getCmp('GROUPCOMB').getValue() != ""){
		 							GroupStore.removeAll() ;  //清空缓存的数据
		 	 						Ext.getCmp('GROUPCOMB').setValue("");
		 	 						Ext.getCmp('GROUPCOMB').setRawValue("");
		 						}
		 						if(Ext.getCmp('COMPONENT_COMBO').getValue() != ""){
		 							GroupDetailStore.removeAll() ;  //清空缓存的数据
		 	 						Ext.getCmp('COMPONENT_COMBO').setValue("");
		 	 						Ext.getCmp('COMPONENT_COMBO').setRawValue("");
		 						}
		 						if(Ext.getCmp('COMPONENT_CODE').getValue() != ""){
		 	 						Ext.getCmp('COMPONENT_CODE').setValue("");
		 						}
		 						GroupStore.load();
		 						GroupDetailStore.load();
		 					}
		 				}
		 			},{
		 				xtype : 'combo',
		 				id : 'GROUPCOMB',
		    			fieldLabel : '组',
		    			emptyText : '请选择组',
		    			name : 'id',
		    			hiddenName : 'r_component_group_id',
		    			triggerAction : 'all',
		    			displayField : 'group_mc',
		    			valueField : 'id',
		    			editable : false,
		    			allowBlank : false,
		    			value : data.r_component_group_id,
		    			store : GroupStore,
		 				width : 445,
		 				listeners : {
		 					'select' : function(ComboObj, record, index) {
		 						GroupDetailStore.proxy = new Ext.data.HttpProxy({
									url : basePath + "/admin/algorithms/selectGroupDetailByIds/"+Ext.getCmp('PROJECT_COMBO').getValue()+"/"+record.get('id')+"/"+Ext.getCmp('LEIBIE_COMBO').getValue()
								});
		 						if(Ext.getCmp('COMPONENT_COMBO').getValue() != ""){
		 							GroupDetailStore.removeAll() ;  //清空缓存的数据
		 	 						Ext.getCmp('COMPONENT_COMBO').setValue("");
		 	 						Ext.getCmp('COMPONENT_COMBO').setRawValue("");
		 						}
		 						if(Ext.getCmp('COMPONENT_CODE').getValue() != ""){
		 	 						Ext.getCmp('COMPONENT_CODE').setValue("");
		 						}
		 						GroupDetailStore.load();
		 					}
		 				}
		 			},{
		 				xtype : 'combo',
		    			fieldLabel : '零件名称',
		    			id:'COMPONENT_COMBO',
		    			emptyText : '请选择零件名称',
		    			name : 'specific_part',
		    			hiddenName : 'r_component_detail_id',
		    			triggerAction : 'all',
		    			displayField : 'specific_part',
		    			valueField : 'id',
		    			editable : false,
		    			allowBlank : false,
		    			store : GroupDetailStore,
		    			value : data.r_component_detail_id,
		 				width : 445,
		 				listeners : {
		 					'select' : function(ComboObj, record, index) {
		 						Ext.getCmp('COMPONENT_CODE').setValue(record.get('specific_bh'));
		 						ComponentParamsStore.load();
		 					}
		 				}
		 			},{
		 				xtype : 'combo',
		 				id : 'COMPON_PARAM',
		    			fieldLabel : '零件参数',
		    			emptyText : '请选择零件参数',
		    			name : 'columnComment',
		    			hiddenName : 'columnComment',
		    			triggerAction : 'all',
		    			displayField : 'columnComment',
		    			valueField : 'columnComment',
		    			editable : false,
		    			allowBlank : false,
		    			//alue : data.r_component_group_id,
		    			store : ComponentParamsStore,
		 				width : 445,
		 			},{
						xtype : 'textfield',
						id : 'PAREM_VALUE',
						fieldLabel : '参数额定值(s):',
						emptyText : '请输入参数额定值',
						//name : 'specific_bh',
						readOnly : true ,
						allowBlank : false,
						width : 180
					},{
						xtype : 'textfield',
						id : 'COMPONENT_CODE',
						fieldLabel : '零件编号',
						emptyText : '请输入零件编号',
						//name : 'specific_bh',
						value : data.component_bh,
						readOnly : true ,
						allowBlank : false,
						width : 180
					},{
		 				xtype : 'combo',
		 				id : 'COMPON_PARAM',
		    			fieldLabel : '零件参数',
		    			emptyText : '请选择零件参数',
		    			name : 'columnComment',
		    			hiddenName : 'specificpart_param',
		    			triggerAction : 'all',
		    			displayField : 'columnComment',
		    			valueField : 'columnComment',
		    			editable : false,
		    			allowBlank : false,
		    			value : data.specificpart_param,
		    			store : ComponentParamsStore,
		 				width : 445,
		 			}, {
		 				fieldLabel : '超额百分比设定值(N):',
						emptyText : '请输入超额百分比设定值',
						name : 'glq_cebfb',
						value : data.glq_cebfb,
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '超额时间设定值(N):',
						emptyText : '请输入超额时间设定值',
						name : 'mxt_csllcesj',
						minLength : 1,
						maxLength : 100
					},{
		 				xtype : 'combo',
		 				id : 'LOCAL_TABLES',
		    			fieldLabel : '现场表',
		    			emptyText : '请选择现场表',
		    			name : 'tableName',
		    			hiddenName : 'sitereal_table_name',
		    			triggerAction : 'all',
		    			displayField : 'tableName',
		    			valueField : 'tableName',
		    			editable : false,
		    			allowBlank : false,
		    			store : LocalTableStore,
		 				width : 445,
		 				listeners : {
		 					'select' : function(ComboObj, record, index) {
		 						LocalTableColumnStore.proxy = new Ext.data.HttpProxy({
									url : basePath + "/admin/algorithms/selectLocalParams/"+Ext.getCmp('LOCAL_TABLES').getValue()
								});
		 						if(Ext.getCmp('LOCAL_TABLE_PARAM').getValue() != ""){
		 							GroupDetailStore.removeAll() ;  //清空缓存的数据
		 	 						Ext.getCmp('LOCAL_TABLE_PARAM').setValue("");
		 	 						Ext.getCmp('LOCAL_TABLE_PARAM').setRawValue("");
		 						}
		 						LocalTableColumnStore.load();
		 					}
		 				}
		 			},{
		 				xtype : 'combo',
		    			fieldLabel : '现场表参数',
		    			id:'LOCAL_TABLE_PARAM',
		    			emptyText : '请选择现场表参数',
		    			name : 'columnName',
		    			hiddenName : 'sitereal_field_name',
		    			triggerAction : 'all',
		    			displayField : 'columnName',
		    			valueField : 'columnName',
		    			editable : false,
		    			allowBlank : false,
		    			store : LocalTableColumnStore,
		 				width : 445
		 			},{
						xtype : 'textfield',
						fieldLabel : '提示别名',
						emptyText : '请输入提示别名',
						name : 'algorithm_alias',
						allowBlank : false,
						value:data.algorithm_alias,
						width : 180
					},{
						xtype : 'textfield',
						fieldLabel : '提示内容',
						emptyText : '请输入提示内容',
						name : 'tip_content',
						allowBlank : false,
						value:data.tip_content,
						width : 180
					}]
				});
	
				var updateWindow = new CTA.common.UpdateWindow({
					id : 'updateWindow',
					width : '40%',
					height : 510,
					layout : 'border',
					items : [ formPanel ],
					handler : function() {
						//检查表单是否填写好
						if (formPanel.getForm().isValid()) {
							CTA.common.Mask.showMask({
								target : 'updateWindow'
							});
							formPanel.commit({
								url : ROTEK.ALGORITHMS7.params.url.modifyUrl
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
}

//查询
if(toolbar.get("button_query")){
	toolbar.get("button_query").setHandler(function() {
		var formPanel = new CTA.common.SFormPanel({
			items : [ {
				fieldLabel : '用户名称',
				emptyText : '请输入用户名称',
				name : 'customer_name',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '工程名称',
				emptyText : '请输入工程名称',
				name : 'project_name',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '组名称',
				emptyText : '请输入组名称',
				name : 'component_group_name',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '零件名称',
				emptyText : '请输入零件名称',
				name : 'component_name',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '提示别名',
				emptyText : '请输入提示别名',
				name : 'algorithm_alias',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '提示内容',
				emptyText : '请输入提示内容',
				name : 'tip_content',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}],
			data : CTA.common.Constant.queryParams
		});
	
		var queryHandler = function() {
			var params = formPanel.getForm().getFieldValues();
			CTA.common.Constant.queryParams = {};
			Ext.apply(CTA.common.Constant.queryParams, params);
			gridPanel.getStore().reload();
		};
		
		// 查询窗口
		var queryWindow = new CTA.common.QueryWindow({
			width : 500,
			height : 260,
			layout : 'border',
			closeAction : 'hide',
			items : [ formPanel ],
			handler : queryHandler
		});
		queryWindow.show();
	});
}

// 查看算法说明
if(toolbar.get("button_algorithmDesc")){
	toolbar.get("button_algorithmDesc").setHandler(function() {
		var formPanel = new CTA.common.SFormPanel({
			items : [ {
				xtype : 'tbtext',
				readOnly : true,
				text : '1. 在水质、压力的监测中，计算是否超限。<p>&nbsp;</p>'
				       + '2. 计算式：|M-s|/s>n%|N ',
				height: 60
			}]
		});
	
		// 查看算法说明窗口
		var descWindow = new CTA.common.DescWindow({
			width : 500,
			height : 249,
			layout : 'border',
			items : [ formPanel ]
		});
		descWindow.show();
	});
}

//删除
if(toolbar.get("button_drop")){
	toolbar.get("button_drop").setHandler(function() {
		var selections = gridPanel.getSelectionModel().getSelections();
		if (!selections || selections.length <= 0) {
			Ext.Msg.alert('提示', '请选择您要操作的数据!');
			return;
		}
		var ids = [];
		Ext.each(selections, function(item) {
			ids.push(item.get("id"));
		});
	
		Ext.Msg.confirm("警告", "您确定删除这  " + ids.length + " 条数据吗？", function(button) {
			if ('yes' == button) {
				CTA.common.Mask.showMask();
				CTA.common.Ajax.request({
					url : ROTEK.ALGORITHMS7.params.url.dropUrl,
					params : {
						ids : ids.toString()
					}
				});
			}
		});
	});
}
	
var viewport = new Ext.Viewport({
	layout : 'border',
	items : [ gridPanel, toolbar ]
});

