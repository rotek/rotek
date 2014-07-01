/**  管理紫外杀菌器组信息  */
Ext.ns("ROTEK.COMPONENT.UVSTER");
ROTEK.COMPONENT.UVSTER.params = {
	gridParam : {
		url : basePath + "/admin/componentgroup/listComGroup/7",
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
			index : 'pp',
			header : '灯管品牌',
			width : 40,
			align : 'center'
		}, {
			index : 'xh',
			header : '灯管型号',
			width : 40,
			align : 'center'
		}, {
			index : 'dgsm',
			header : '灯管寿命（小时）',
			width : 50,
			align : 'center'
		}, {
			index : 'gl',
			header : '灯管功率',
			width : 50,
			align : 'center'
		}, {
			index : 'sl',
			header : '灯管数量',
			width : 50,
			align : 'center'
		}, {
			index : 'cll',
			header : '处理量',
			width : 50,
			align : 'center'
		}, {
			index : 'ckdj',
			header : '灯管参考单价',
			width : 50,
			align : 'center'
		}, {
			index : 'others',
			header : '其它',
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
		addUrl : basePath + "/admin/componentgroup/addComGroup/7",
		detailUrl : basePath + "/admin/componentgroup/getComGroupDetail",
		modifyUrl : basePath + "/admin/componentgroup/modifyComGroup/7",
		dropUrl : basePath + "/admin/componentgroup/deleteComGroup",
		listProejctUrl : basePath + "/admin/componentgroup/listProjectByStatus"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.COMPONENT.UVSTER.params.gridParam);
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
					url : ROTEK.COMPONENT.UVSTER.params.url.addUrl
				});
			}
		};
		//定义添加的窗口
		var addWindow = new CTA.common.SaveWindow({
			id : 'addWindow',
			width : '50%',
			height : 450,
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
    					url : ROTEK.COMPONENT.UVSTER.params.url.listProejctUrl
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
				fieldLabel : '灯管品牌',
				emptyText : '请输入灯管品牌',
				name : 'pp',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '灯管型号',
				emptyText : '请输入灯管型号',
				name : 'xh',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '灯管功率',
				emptyText : '请输入灯管功率',
				name : 'gl',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '灯管处理量',
				emptyText : '请输入处理量',
				name : 'cll',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '灯管寿命（小时）',
				emptyText : '请输入灯管寿命',
				name : 'dgsm',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '灯管数量',
				emptyText : '请输入灯管数量',
				name : 'sl',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '灯管参考单价',
				emptyText : '请输入灯管参考单价',
				name : 'ckdj',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '其它',
				emptyText : '请输入其它',
				name : 'others',
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
			url : ROTEK.COMPONENT.UVSTER.params.url.detailUrl,
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
		    					url : ROTEK.COMPONENT.UVSTER.params.url.listProejctUrl
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
						fieldLabel : '灯管品牌',
						emptyText : '请输入灯管品牌',
						name : 'pp',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '灯管型号',
						emptyText : '请输入灯管型号',
						name : 'xh',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '灯管功率',
						emptyText : '请输入灯管功率',
						name : 'gl',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '灯管处理量',
						emptyText : '请输入处理量',
						name : 'cll',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '灯管寿命（小时）',
						emptyText : '请输入灯管寿命',
						name : 'dgsm',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '灯管数量',
						emptyText : '请输入灯管数量',
						name : 'sl',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '灯管参考单价',
						emptyText : '请输入灯管参考单价',
						name : 'ckdj',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '其它',
						emptyText : '请输入其它',
						name : 'others',
						minLength : 1,
						maxLength : 100
					}],
					data : data
				});
	
				var updateWindow = new CTA.common.UpdateWindow({
					id : 'updateWindow',
					width : '50%',
					height : 450,
					layout : 'border',
					items : [ formPanel ],
					handler : function() {
						//检查表单是否填写好
						if (formPanel.getForm().isValid()) {
							CTA.common.Mask.showMask({
								target : 'updateWindow'
							});
							formPanel.commit({
								url : ROTEK.COMPONENT.UVSTER.params.url.modifyUrl
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
				fieldLabel : '灯管品牌',
				emptyText : '请输入灯管品牌',
				name : 'pp',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '灯管型号',
				emptyText : '请输入灯管型号',
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
					url : ROTEK.COMPONENT.UVSTER.params.url.dropUrl,
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