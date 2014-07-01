/**  管理 膜 组信息  */
Ext.ns("ROTEK.COMPONENT.FILM");
ROTEK.COMPONENT.FILM.params = {
	gridParam : {
		url : basePath + "/admin/componentgroup/listComGroup/6",
		dataList : [ {
			index : 'id',
			header : '组ID',
			width : 30,
			align : 'center'
		}, {
			index : 'project_name',
			header : '工程名称',
			width : 80,
			align : 'center'
		}, {
			index : 'group_bh',
			header : '组编号',
			width : 40,
			align : 'center'
		}, {
			index : 'group_mc',
			header : '组名称',
			width : 50,
			align : 'center'
		}, {
			index : 'plfs',
			header : '排列方式',
			width : 40,
			align : 'center'
		}, {
			index : 'pp',
			header : '膜品牌',
			width : 40,
			align : 'center'
		}, {
			index : 'xh',
			header : '膜型号',
			width : 40,
			align : 'center'
		}, {
			index : 'gg',
			header : '膜壳规格（N芯）',
			width : 50,
			align : 'center'
		}, {
			index : 'csl',
			header : '产水量',
			width : 50,
			align : 'center'
		}, {
			index : 'hsl',
			header : '膜回收率',
			width : 50,
			align : 'center'
		}, {
			index : 'sl',
			header : '膜总数量数量',
			width : 50,
			align : 'center'
		}, {
			index : 'ckdj',
			header : '膜参考单价',
			width : 50,
			align : 'center'
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
		addUrl : basePath + "/admin/componentgroup/addComGroup/6",
		detailUrl : basePath + "/admin/componentgroup/getComGroupDetail",
		modifyUrl : basePath + "/admin/componentgroup/modifyComGroup/6",
		dropUrl : basePath + "/admin/componentgroup/deleteComGroup",
		listProejctUrl : basePath + "/admin/componentgroup/listProjectByStatus"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.COMPONENT.FILM.params.gridParam);
var toolbar = new CTA.common.Toolbar();

//添加工程信息
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function() {
		var saveHandler = function() {
			//检查表单是否填写好
			if (formPanel.getForm().isValid()) {
//				CTA.common.Mask.showMask({
//					target : 'addWindow'
//				});
				formPanel.commit({
					url : ROTEK.COMPONENT.FILM.params.url.addUrl
				});
			}
		};
		//定义添加的窗口
		var addWindow = new CTA.common.SaveWindow({
			id : 'addWindow',
			width : '50%',
			height : 530,
			layout : 'fit',
			handler : saveHandler
		});

		//定义添加窗口中的form
		var formPanel = new CTA.common.SFormPanel({
			fileUpload : true,
			items : [{
    			xtype : 'combo',
    			fieldLabel : '所属工程',
    			emptyText : '请选择工程',
    			name : 'id',
    			hiddenName : 'id',
    			triggerAction : 'all',
    			displayField : 'gcmc',
    			valueField : 'id',
    			editable : false,
    			store : new Ext.data.Store({
    				reader : new Ext.data.JsonReader({
    					root : 'dataList',
    					fields : [ {
    						name : 'id'
    					}, {
    						name : 'gcmc'
    					} ]
    				}),
    				proxy : new Ext.data.HttpProxy({
    					url : ROTEK.COMPONENT.FILM.params.url.listProejctUrl
    				})
    			})
    		}, {
				fieldLabel : '组编号',
				emptyText : '请输入组编号',
				name : 'group_bh',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '组名称',
				emptyText : '请输入组名称',
				name : 'group_mc',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '排列方式',
				emptyText : '请输入排列方式',
				name : 'plfs',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '膜品牌',
				emptyText : '请输入滤芯品牌',
				name : 'pp',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '膜型号',
				emptyText : '请输入膜型号',
				name : 'xh',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '膜壳规格（N芯）',
				emptyText : '请输入膜壳规格（N芯）',
				name : 'gg',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '产水量',
				emptyText : '请输入产水量',
				name : 'csl',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '膜回收率',
				emptyText : '请输入膜回收率',
				name : 'hsl',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '膜总数量',
				emptyText : '请输入膜总数量',
				name : 'sl',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '膜参考单价',
				emptyText : '请输入膜参考单价',
				name : 'ckdj',
				minLength : 1,
				maxLength : 100
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
			url : ROTEK.COMPONENT.FILM.params.url.detailUrl,
			params : {
				id : id
			},
			success : function(response) {
				var data = Ext.util.JSON.decode(response.responseText).data;
				var formPanel = new CTA.common.SFormPanel({
					fileUpload : true,
					items : [{
						xtype : 'hidden',
						fieldLabel : '组ID',
						name : 'id',
						readOnly : true
					}, {
		    			xtype : 'combo',
		    			fieldLabel : '所属工程',
		    			emptyText : '请选择工程',
		    			name : 'r_project_id',
		    			hiddenName : 'r_project_id',
		    			triggerAction : 'all',
		    			displayField : 'gcmc',
		    			valueField : 'id',
		    			allowBlank : true,
		    			editable : false,
		    			store : new Ext.data.Store({
		    				reader : new Ext.data.JsonReader({
		    					root : 'dataList',
		    					fields : [ {
		    						name : 'id'
		    					}, {
		    						name : 'gcmc'
		    					} ]
		    				}),
		    				proxy : new Ext.data.HttpProxy({
		    					url : ROTEK.COMPONENT.FILM.params.url.listProejctUrl
		    				})
		    			})
		    		}, {
						fieldLabel : '组编号',
						emptyText : '请输入组编号',
						name : 'group_bh',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '组名称',
						emptyText : '请输入组名称',
						name : 'group_mc',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '排列方式',
						emptyText : '请输入排列方式',
						name : 'plfs',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '膜品牌',
						emptyText : '请输入滤芯品牌',
						name : 'pp',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '膜型号',
						emptyText : '请输入膜型号',
						name : 'xh',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '膜壳规格（N芯）',
						emptyText : '请输入膜壳规格（N芯）',
						name : 'gg',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '产水量',
						emptyText : '请输入产水量',
						name : 'csl',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '膜回收率',
						emptyText : '请输入膜回收率',
						name : 'hsl',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '膜总数量',
						emptyText : '请输入膜总数量',
						name : 'sl',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '膜参考单价',
						emptyText : '请输入膜参考单价',
						name : 'ckdj',
						minLength : 1,
						maxLength : 100
					}],
					data : data
				});
	
				var updateWindow = new CTA.common.UpdateWindow({
					id : 'updateWindow',
					width : '50%',
					height : 530,
					layout : 'border',
					items : [ formPanel ],
					handler : function() {
						//检查表单是否填写好
						if (formPanel.getForm().isValid()) {
							CTA.common.Mask.showMask({
								target : 'updateWindow'
							});
							formPanel.commit({
								url : ROTEK.COMPONENT.FILM.params.url.modifyUrl
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
				xtype : 'numberfield',
				fieldLabel : '组ID',
				emptyText : '请输入组ID',
				name : 'id',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '工程名称',
				emptyText : '请输入工程名称',
				name : 'gcmc',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '组编号',
				emptyText : '请输入组编号',
				name : 'group_bh',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '组名称',
				emptyText : '请输入组名称',
				name : 'group_mc',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '膜品牌',
				emptyText : '请输入膜品牌',
				name : 'pp',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '膜型号',
				emptyText : '请输入膜型号',
				name : 'xh',
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
			height : 250,
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
					url : ROTEK.COMPONENT.FILM.params.url.dropUrl,
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