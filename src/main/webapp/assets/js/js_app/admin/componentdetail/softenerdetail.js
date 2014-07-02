/** 管理 软化器 组零件 信息  */
Ext.ns("ROTEK.COMPONENTD.SOFTER");
ROTEK.COMPONENTD.SOFTER.params = {
	gridParam : {
		url : basePath + "/admin/componentdetail/listComDetail/4/1",
		dataList : [ {
			index : 'id',
			header : '零件ID',
			width : 40,
			align : 'center'
		}, {
			index : 'project_name',
			header : '工程名称',
			width : 50,
			align : 'center'
		}, {
			index : 'group_name',
			header : '组名称',
			width : 50,
			align : 'center'
		}, {
			index : 'specific_part',
			header : '零件名称',
			width : 50,
			align : 'center'
		}, {
			index : 'specific_bh',
			header : '零件编号',
			width : 50,
			align : 'center'
		}, {
			index : 'edghsj',
			header : '额定更换时间',
			width : 25,
			align : 'center',
			renderer : function(value){
				return new Date(parseFloat(value)).format("Y-m-d");
			}
		}, {
			index : 'status',
			header : '状态',
			renderer : function(value) {
				if (1 == value) {
					return "<span style='color:green;'>有效</span>";
				} else {
					return "<span style='color:red'>无效</span>";
				}
			},
			width : 40,
			align : 'center'
		} ]
	},
	url : {
		addUrl : basePath + "/admin/componentdetail/addComDetail/4",
		detailUrl : basePath + "/admin/componentdetail/getComDetail",
		modifyUrl : basePath + "/admin/componentdetail/modifyComDetail/4",
		dropUrl : basePath + "/admin/componentdetail/deleteComDetail",
		listProejctUrl : basePath + "/admin/componentdetail/selectProjectByType/1"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.COMPONENTD.SOFTER.params.gridParam);
var toolbar = new CTA.common.Toolbar();

//添加工程信息
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function() {
		var saveHandler = function() {
			//检查表单是否填写好
			if (formPanel.getForm().isValid()) {
				formPanel.commit({
					url : ROTEK.COMPONENTD.SOFTER.params.url.addUrl
				});
			}
		};
		//定义添加的窗口
		var addWindow = new CTA.common.SaveWindow({
			id : 'addWindow',
			width : '40%',
			height : 245,
			layout : 'fit',
			handler : saveHandler
		});

		var ProjectStore = new Ext.data.Store({
			reader : new Ext.data.JsonReader({
				root : 'projectList',
				fields : [ {
					name : 'id'
				}, {
					name : 'gcmc'
				} ]
			}),
			proxy : new Ext.data.HttpProxy({
				url : ROTEK.COMPONENTD.SOFTER.params.url.listProejctUrl
			})
		})
		var GroupStore = new Ext.data.Store({
			reader : new Ext.data.JsonReader({
				root : 'grouptList',
				fields : [ {
					name : 'id'
				}, {
					name : 'group_mc'
				} ]
			}),
			proxy : new Ext.data.HttpProxy({
				url : basePath + "/admin/componentdetail/selectGroupByPid/0/1"
			})
		})		

		//定义添加窗口中的form
		var formPanel = new CTA.common.SFormPanel({
			fileUpload : true,
			items : [ {
 				xtype : 'combo',
    			fieldLabel : '工程',
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
 					select : function(ProjectCombox, record, index) {
 						GroupStore.proxy = new Ext.data.HttpProxy({
							url : basePath + "/admin/componentdetail/selectGroupByPid/" + ProjectCombox.value + "/1"
						});
 						GroupStore.removeAll() ;  //清空缓存的数据
 						Ext.getCmp('GROUPCOMB').setValue("");
 						Ext.getCmp('GROUPCOMB').setRawValue("");
 						GroupStore.load();
 					}
 			   }
	     	}, {
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
 				width : 445
	     			
	     	}, {
				xtype : 'textfield',
				fieldLabel : '零件名称',
				emptyText : '请输入零件名称',
				name : 'specific_part',
				allowBlank : false,
				width : 180
			}, {
				xtype : 'textfield',
				fieldLabel : '零件编号',
				emptyText : '请输入零件编号',
				name : 'specific_bh',
				allowBlank : false,
				width : 180
			}, {
				xtype : 'datefield',
				fieldLabel : '额定更换时间',
				emptyText : '请输入额定更换时间',
				name : 'edghsj',
				format:'Y-m-d',
				editable : false,
				allowBlank : true,
				width : 150
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
			url : ROTEK.COMPONENTD.SOFTER.params.url.detailUrl,
			params : {
				id : id
			},
			success : function(response) {
				var data = Ext.util.JSON.decode(response.responseText).data;
				var ProjectStore = new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'projectList',
						fields : [ {
							name : 'id'
						}, {
							name : 'gcmc'
						} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : ROTEK.COMPONENTD.SOFTER.params.url.listProejctUrl
					})
				})
				var GroupStore = new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'grouptList',
						fields : [ {
							name : 'id'
						}, {
							name : 'group_mc'
						} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : basePath + "/admin/componentdetail/selectGroupByPid/0/1"
					})
				})
				
				var formPanel = new CTA.common.SFormPanel({
					fileUpload : true,
					items : [{
						xtype : 'hidden',
						fieldLabel : 'ID',
						name : 'id',
						readOnly : true,
						value : data.id
					 }, {
	     				xtype : 'combo',
	        			fieldLabel : '工程',
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
	     				value : data.r_project_id,
	     				listeners : {
	     					select : function(ProjectCombox, record, index) {
	     						GroupStore.proxy = new Ext.data.HttpProxy({
 									url : basePath + "/admin/componentdetail/selectGroupByPid/" + ProjectCombox.value + "/1"
 								});
	     						GroupStore.removeAll() ;  //清空缓存的数据
	     						Ext.getCmp('GROUPCOMB').setValue("");
	     						Ext.getCmp('GROUPCOMB').setRawValue("");
	     						GroupStore.load();
	     					}
	     				}
			     	}, {
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
	     				//value : data.group_name,
	     				value : data.r_component_group_id
			     	}, {
						xtype : 'textfield',
						fieldLabel : '零件名称',
						emptyText : '请输入零件名称',
						name : 'specific_part',
						allowBlank : false,
						width : 180,
						value : data.specific_part
					}, {
						xtype : 'textfield',
						fieldLabel : '零件编号',
						emptyText : '请输入零件编号',
						name : 'specific_bh',
						allowBlank : false,
						width : 180,
						value : data.specific_bh
					}, {
						xtype : 'datefield',
						fieldLabel : '额定更换时间',
						emptyText : '请输入额定更换时间',
						name : 'edghsj',
						format:'Y-m-d',
						editable : false,
						allowBlank : true,
						width : 150,
						value : new Date(parseFloat(data.edghsj)).format("Y-m-d")
					}]
				});
	
				var updateWindow = new CTA.common.UpdateWindow({
					id : 'updateWindow',
					width : '50%',
					height : 437,
					layout : 'border',
					items : [ formPanel ],
					handler : function() {
						//检查表单是否填写好
						if (formPanel.getForm().isValid()) {
							CTA.common.Mask.showMask({
								target : 'updateWindow'
							});
							formPanel.commit({
								url : ROTEK.COMPONENTD.SOFTER.params.url.modifyUrl
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
				fieldLabel : '工程名称',
				emptyText : '请输入工程名称',
				name : 'project_name',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '组名称',
				emptyText : '请输入组名称',
				name : 'group_name',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '零件名称',
				emptyText : '请输入零件名称',
				name : 'specific_part',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '零件编号',
				emptyText : '请输入零件编号',
				name : 'specific_bh',
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
			height : 220,
			layout : 'border',
			closeAction : 'hide',
			items : [ formPanel ],
			handler : queryHandler
		});
		queryWindow.show();
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
					url : ROTEK.COMPONENTD.SOFTER.params.url.dropUrl,
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

