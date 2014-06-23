/**  管理零件类别信息  */
Ext.ns("ROTEK.ComType");
ROTEK.ComType.params = {
	gridParam : {
		url : basePath + "/admin/componenttype/listComponentTypes",
		dataList : [ {
			index : 'id',
			header : '零件类别ID',
			width : 45,
			align : 'center'
		}, {
			index : 'name',
			header : '零件类别名称',
			width : 100,
			align : 'center'
		}, {
			index : 'describe',
			header : '零件类别说明',
			width : 80,
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
		addUrl : basePath + "/admin/componenttype/addComponentType",
		detailUrl : basePath + "/admin/componenttype/getComponentTypeDetail",
		modifyUrl : basePath + "/admin/componenttype/modifyComponentType",
		dropUrl : basePath + "/admin/componenttype/deleteComponentType"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.ComType.params.gridParam);
var toolbar = new CTA.common.Toolbar();

//添加零件类别信息
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function() {
		var saveHandler = function() {
			//检查表单是否填写好
			if (formPanel.getForm().isValid()) {
				formPanel.commit({
					url : ROTEK.ComType.params.url.addUrl
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
				fieldLabel : '零件类别名称',
				emptyText : '请输入零件类别名称',
				name : 'name',
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '零件类别说明',
				emptyText : '请输入零件类别说明',
				name : 'describe',
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
			url : ROTEK.ComType.params.url.detailUrl,
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
						fieldLabel : '零件类别名称',
						emptyText : '请输入零件类别名称',
						name : 'name',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '零件类别说明',
						emptyText : '请输入零件类别说明',
						name : 'describe',
						minLength : 1,
						maxLength : 100
					}],
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
								url : ROTEK.ComType.params.url.modifyUrl
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
				fieldLabel : '零件类别ID',
				emptyText : '请输入零件类别ID',
				name : 'id',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '零件类别名称',
				emptyText : '请输入零件类别名称',
				name : 'name',
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
					url : ROTEK.ComType.params.url.dropUrl,
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
