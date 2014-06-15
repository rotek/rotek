/**  管理工程信息  */
Ext.ns("ROTEK.Project");
ROTEK.Project.params = {
	gridParam : {
		url : basePath + "/admin/projectinfo/listProjects",
		dataList : [ {
			index : 'id',
			header : '工程ID',
			width : 30,
			align : 'center'
		}, {
			index : 'gcmc',
			header : '工程名称',
			width : 80,
			align : 'center'
		}, {
			index : 'gcbh',
			header : '工程编号',
			width : 50,
			align : 'center'
		}, {
			index : 'gcjs',
			header : '工程介绍',
			width : 130,
			align : 'center'
		}, {
			index : 'azsj',
			header : '安装时间',
			width : 50,
			renderer : function(value){
				return new Date(parseFloat(value)).format("Y-m-d");
			},
			align : 'center'
		}, {
			index : 'tysj',
			header : '投运时间',
			width : 50,
			renderer : function(value){
				return new Date(parseFloat(value)).format("Y-m-d");
			},
			align : 'center'
		}, {
			index : 'gclb',
			header : '工程类别',
			renderer : function(value) {
				if (1 == value) {
					return "普通工程";
				} else {
					return "EMC工程";
				}
			},
			width : 30,
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
			width : 30,
			align : 'center'
		} ]
	},
	url : {
		addUrl : basePath + "/admin/projectinfo/addProject",
		detailUrl : basePath + "/admin/projectinfo/getProjectDetail",
		modifyUrl : basePath + "/admin/projectinfo/modifyProject",
		dropUrl : basePath + "/admin/projectinfo/deleteProject"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.Project.params.gridParam);
var toolbar = new CTA.common.Toolbar();

//添加工程信息
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function() {
		var saveHandler = function() {
			//检查表单是否填写好
			if (formPanel.getForm().isValid()) {
				CTA.common.Mask.showMask({
					target : 'addWindow'
				});
				formPanel.commit({
					url : ROTEK.Project.params.url.addUrl
				});
			}
		};
		//定义添加的窗口
		var addWindow = new CTA.common.SaveWindow({
			id : 'addWindow',
			width : '80%',
			height : 500,
			layout : 'fit',
			handler : saveHandler
		});

		//定义添加窗口中的form
		var formPanel = new CTA.common.SFormPanel({
			fileUpload : true,
			items : [{
				fieldLabel : '工程名称',
				emptyText : '请输入工程名称',
				name : 'gcmc',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '工程编号',
				emptyText : '请输入工程编号',
				name : 'gcbh',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '工程型号',
				emptyText : '请输入工程型号',
				name : 'gcxh',
				minLength : 1,
				maxLength : 100
			}, {
				xtype : 'combo',
				fieldLabel : '工程类别',
				emptyText : '请选择工程类别',
				name : 'gclb',
				triggerAction : 'all',
				store : new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data : [[ "普通工程", "1" ], [ "EMC工程", "2" ]]
				}),
				displayField : 'label',
				valueField : 'value',
				hiddenName : 'gclb',
				mode : 'local',
				editable : false
			}, {
				xtype : 'textarea',
				fieldLabel : '工程介绍',
				emptyText : '请输入工程介绍',
				name : 'gcjj',
				height : 70,
				width : 230
			}, {
				fieldLabel : '工程图片',
				name : 'gczp',
				text : "点击上传工程图片",
				inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
				blankText : '请上传工程图片'
			}, {
				xtype : 'textarea',
				fieldLabel : '技术参数简介',
				emptyText : '请输入技术参数简介',
				name : 'jscsjj',
				height : 70,
				width : 230
			}, {
				fieldLabel : '技术参数附件',
				name : 'jscsfj',
				text : "点击上传技术参数附件",
				inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
				blankText : '请上传技术参数附件'
			}, {
				fieldLabel : '工程零件',
				emptyText : '请输入工程零件',
				name : 'gclj',
				minLength : 1,
				maxLength : 100
			}, {
				xtype : 'datefield',
				fieldLabel : '安装时间',
				emptyText : '请选择安装时间',
				name : 'azsj',
				format:'Y-m-d',
				editable : false,
				allowBlank : true
			}, {
				xtype : 'datefield',
				fieldLabel : '投运时间',
				emptyText : '请输入投运时间',
				name : 'tysj',
				format:'Y-m-d',
				editable : false,
				allowBlank : true
			} ]
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
			url : ROTEK.Project.params.url.detailUrl,
			params : {
				id : id
			},
			success : function(response) {
				var data = Ext.util.JSON.decode(response.responseText).data;
	
				var formPanel = new CTA.common.SFormPanel({
					fileUpload : true,
					items : [ {
						xtype : 'hidden',
						name : 'id'
					}, {
						fieldLabel : '工程名称',
						emptyText : '请输入工程名称',
						name : 'gcmc',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '工程编号',
						emptyText : '请输入工程编号',
						name : 'gcbh',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '工程型号',
						emptyText : '请输入工程型号',
						name : 'gcxh',
						minLength : 1,
						maxLength : 100
					}, {
						xtype : 'combo',
						fieldLabel : '工程类别',
						emptyText : '请选择工程类别',
						name : 'gclb',
						triggerAction : 'all',
						store : new Ext.data.SimpleStore({
							fields : [ 'label', 'value' ],
							data : [[ "普通工程", "1" ], [ "EMC工程", "2" ]]
						}),
						displayField : 'label',
						valueField : 'value',
						hiddenName : 'gclb',
						mode : 'local',
						editable : false
					}, {
						xtype : 'textarea',
						fieldLabel : '工程介绍',
						emptyText : '请输入工程介绍',
						name : 'gcjj',
						height : 70,
						width : 230
					}, {
						fieldLabel : '工程图片',
						name : 'gczp',
						text : "点击上传工程图片",
						inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
						blankText : '请上传工程图片'
					}, {
						xtype : 'textarea',
						fieldLabel : '技术参数简介',
						emptyText : '请输入技术参数简介',
						name : 'jscsjj',
						height : 70,
						width : 230
					}, {
						fieldLabel : '技术参数附件',
						name : 'jscsfj',
						text : "点击上传技术参数附件",
						inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
						blankText : '请上传技术参数附件'
					}, {
						fieldLabel : '工程零件',
						emptyText : '请输入工程零件',
						name : 'gclj',
						minLength : 1,
						maxLength : 100
					}, {
						xtype : 'datefield',
						fieldLabel : '安装时间',
						emptyText : '请选择安装时间',
						name : 'azsj',
						format:'Y-m-d',
						editable : false,
						allowBlank : true
					}, {
						xtype : 'datefield',
						fieldLabel : '投运时间',
						emptyText : '请输入投运时间',
						name : 'tysj',
						format:'Y-m-d',
						editable : false,
						allowBlank : true
					} ],
					data : data
				});
	
				var updateWindow = new CTA.common.UpdateWindow({
					id : 'updateWindow',
					width : '80%',
					height : 500,
					layout : 'border',
					items : [ formPanel ],
					handler : function() {
						//检查表单是否填写好
						if (formPanel.getForm().isValid()) {
							CTA.common.Mask.showMask({
								target : 'updateWindow'
							});
							formPanel.commit({
								url : ROTEK.Project.params.url.modifyUrl
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
				fieldLabel : '工程ID',
				emptyText : '请输入工程ID',
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
				fieldLabel : '工程编号',
				emptyText : '请输入工程编号',
				name : 'gcbh',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '工程型号',
				emptyText : '请输入工程型号',
				name : 'gcxh',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				xtype : 'combo',
				fieldLabel : '工程类别',
				emptyText : '请选择工程类别',
				name : 'gclb',
				triggerAction : 'all',
				store : new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data : [ [ "普通工程", "1" ], [ "EMC工程", "2" ] ]
				}),
				displayField : 'label',
				valueField : 'value',
				hiddenName : 'gclb',
				mode : 'local',
				allowBlank : true,
				editable : false
			} ],
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
					url : ROTEK.Project.params.url.dropUrl,
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
