/** 管理泵组信息  */
Ext.ns("ROTEK.COMPONENT.PUMP");
ROTEK.COMPONENT.PUMP.params = {
	gridParam : {
		url : basePath + "/admin/componentgroup/listComGroup/1",
		dataList : [ {
			index : 'id',
			header : '组ID',
			width : 40,
			align : 'center'
		}, {
			index : 'project_name',
			header : '工程名称',
			width : 100,
			align : 'center'
		}, {
			index : 'group_bh',
			header : '组编号',
			width : 50,
			align : 'center'
		}, {
			index : 'group_mc',
			header : '组名称',
			width : 50,
			align : 'center'
		}, {
			index : 'pp',
			header : '品牌',
			width : 50,
			align : 'center'
		}, {
			index : 'xh',
			header : '型号',
			width : 50,
			align : 'center'
		}, {
			index : 'gl',
			header : '功率',
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
		addUrl : basePath + "/admin/componentgroup/addComGroup/1",
		detailUrl : basePath + "/admin/componentgroup/getComGroupDetail",
		modifyUrl : basePath + "/admin/componentgroup/modifyComGroup/1",
		dropUrl : basePath + "/admin/componentgroup/deleteComGroup",
		listProejctUrl : basePath + "/admin/componentgroup/listProjectByStatus",
		viewAlgorithmUrl : basePath + "/admin/algorithms/getComGroupDetail",
		/* 1 --> 算法1 */
		setAlgorithmUrl : basePath + "/admin/algorithms/setAlgorithm/1" 
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.COMPONENT.PUMP.params.gridParam);
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
					url : ROTEK.COMPONENT.PUMP.params.url.addUrl
				});
			}
		};
		//定义添加的窗口
		var addWindow = new CTA.common.SaveWindow({
			id : 'addWindow',
			width : '50%',
			height : 270,
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
    					url : ROTEK.COMPONENT.PUMP.params.url.listProejctUrl
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
				fieldLabel : '品牌',
				emptyText : '请输入品牌',
				name : 'pp',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '型号',
				emptyText : '请输入型号',
				name : 'xh',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '功率',
				emptyText : '请输入功率',
				name : 'gl',
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
			url : ROTEK.COMPONENT.PUMP.params.url.detailUrl,
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
		    					url : ROTEK.COMPONENT.PUMP.params.url.listProejctUrl
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
						fieldLabel : '品牌',
						emptyText : '请输入品牌',
						name : 'pp',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '型号',
						emptyText : '请输入型号',
						name : 'xh',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '功率',
						emptyText : '请输入功率',
						name : 'gl',
						minLength : 1,
						maxLength : 100
					}],
					data : data
				});
	
				var updateWindow = new CTA.common.UpdateWindow({
					id : 'updateWindow',
					width : '50%',
					height : 270,
					layout : 'border',
					items : [ formPanel ],
					handler : function() {
						//检查表单是否填写好
						if (formPanel.getForm().isValid()) {
							CTA.common.Mask.showMask({
								target : 'updateWindow'
							});
							formPanel.commit({
								url : ROTEK.COMPONENT.PUMP.params.url.modifyUrl
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
				fieldLabel : '品牌',
				emptyText : '请输入品牌',
				name : 'pp',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '型号',
				emptyText : '请输入组型号',
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

//设置报警算法
if(toolbar.get("button_setAlgorithm")){
	toolbar.get("button_setAlgorithm").setHandler(function() {
		var selections = gridPanel.getSelectionModel().getSelections();
		if (!selections || selections.length <= 0) {
			Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
			return;
		}
		var id = selections[0].get("id");
		Ext.Ajax.request({
			url : ROTEK.COMPONENT.PUMP.params.url.viewAlgorithmUrl,
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
						name : 'r_component_group_id',
						readOnly : true,
						value : data.id
					},{
						xtype : 'hidden',
						fieldLabel : '客户ID',
						name : 'r_customer_id',
						readOnly : true,
						value : data.customer_id
					},{
						xtype : 'hidden',
						fieldLabel : '工程ID',
						name : 'r_project_id',
						readOnly : true,
						value : data.r_project_id
					}, {
						fieldLabel : '算法类别',
						name : 'algorithm_type',
						value : '算法1',
						readOnly : true,
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '客户名称',
						minLength : 1,
						maxLength : 100,
						readOnly : true,
						value : data.customer_name
					}, {
						fieldLabel : '工程名称',
						minLength : 1,
						maxLength : 100,
						readOnly : true,
						value : data.project_name
					}, {
						fieldLabel : '组名称',
						minLength : 1,
						maxLength : 100,
						readOnly : true,
						value : data.group_mc
					}, {
						xtype : 'datefield',
						fieldLabel : '零件更换时间',
						emptyText : '请输入零件更换时间',
						name : 'ljghsj',
						format:'Y-m-d',
						editable : false,
						allowBlank : true,
						width : 150
					}, {
						fieldLabel : '额定运行时间',
						emptyText : '请输入额定运行时间',
						name : 'ljedyxsj',
						minLength : 1,
						maxLength : 100
					}, {
						xtype : 'textarea',
						readOnly : true,
						fieldLabel : '算法说明',
						value : '1.累计运行时间（年）达到N年，转化为小时数，记为Na，Na=设置小时， 固定值。\r\n'
						    +'2.服务档案记录的上一次更换时间T，若当前时间记为Td，距离上一次更换'
						    +'的时间差为Ta=(T-Td)（注：转化为小时数），既是在服务档案中对应的零'
						    +'件目前的累计运行时间，数据库中保存为累计运行时间。\r\n'
						    +'3.Ta>Na时发送提醒，之后累计运行时间清零，提醒记录保存到数据库中的【提醒报警信息表】。\r\n'
						    +'4.计算公式：(T-Td)>Na',
						height: 150
					}]
				});
	
				var setWindow = new CTA.common.SetWindow({
					id : 'setWindow',
					width : '40%',
					height : 415,
					layout : 'border',
					items : [ formPanel ],
					handler : function() {
						//检查表单是否填写好
						if (formPanel.getForm().isValid()) {
							CTA.common.Mask.showMask({
								target : 'setWindow'
							});
							formPanel.commit({
								url : ROTEK.COMPONENT.PUMP.params.url.setAlgorithmUrl
							});
						}
					}
				});
				setWindow.show();
			},
			failure : function() {
				CTA.common.Mask.hideMask();
				Ext.Msg.alert('提示', '操作失败!');
			}
		});
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
					url : ROTEK.COMPONENT.PUMP.params.url.dropUrl,
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
