//设置菜单信息
Ext.ns("CTA.menu");
CTA.menu.params = {
	// gridpanel的参数
	gridParam : {
		url : basePath + "/admin/menu/listMenus",
		dataList : [ {
			index : 'id',
			header : '菜单ID'
		}, {
			index : 'name',
			header : '菜单名称'
		}, {
			index : 'url',
			header : '菜单URL'
		}, {
			index : 'status',
			header : '角色状态',
			renderer : function(value) {
				if (1 == value) {
					return "<span style='color:green;'>有效</span>";
				} else {
					return "<span style='color:red'>无效</span>";
				}
			}
		} ]
	},
	url : {
		addUrl : basePath + "/admin/menu/addMenu",
		detailUrl : basePath + "/admin/menu/getMenuDetail",
		modifyUrl : basePath + "/admin/menu/modifyMenu",
		dropUrl : basePath + "/admin/menu/deleteMenu",
		listButtonUrl : basePath + "/admin/button/listButtons_s"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.menu.params.gridParam);
var toolbar = new CTA.common.Toolbar();
// 添加按钮的事件
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function() {
		var saveHandler = function() {
			// 检查表单是否填写好
			if (formPanel.getForm().isValid()) {
				CTA.common.Mask.showMask({
					target : 'addWindow'
				});
				formPanel.commit({
					url : CTA.menu.params.url.addUrl
				});
			}
		};
		// 定义添加的窗口
		var addWindow = new CTA.common.SaveWindow({
			id : 'addWindow',
			width : 500,
			height : 320,
			layout : 'fit',
			handler : saveHandler
		});

		// 定义添加窗口中的form
		var formPanel = new CTA.common.SFormPanel({
			items : [ {
				fieldLabel : '菜单名称',
				emptyText : '请输入菜单名称',
				name : 'name',
				minLength : 1,
				maxLength : 50
			}, {
				fieldLabel : '菜单URL',
				emptyText : '请输入菜单URL',
				name : 'url',
				minLength : 1,
				maxLength : 250
			}, {
				xtype : 'combo',
				fieldLabel : '上级菜单',
				emptyText : '点击设置上级菜单',
				name : 'super_menu_id',
				hiddenName : 'super_menu_id',
				triggerAction : 'all',
				valueField : 'id',
				displayField : 'name',
				editable : false,
				allowBlank : false,
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
						url : basePath + "/admin/menu/listMenus_super"
					})
				})
			}, {
				xtype : 'combo',
				fieldLabel : '菜单排序',
				emptyText : '点击设置菜单排序',
				name : 'sort',
				hiddenName : 'sort',
				triggerAction : 'all',
				displayField : 'name',
				valueField : 'sort',
				editable : false,
				allowBlank : false,
				store : new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'dataList',
						fields : [ {
							name : 'sort'
						}, {
							name : 'name'
						} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : basePath + "/admin/menu/listMenus_sort"
					})
				})
			}, {
				xtype : 'combo',
				fieldLabel : '菜单状态',
				emptyText : '请选择菜单状态',
				name : 'status',
				triggerAction : 'all',
				store : new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data : [ [ "启用", "1" ], [ "禁用", "-1" ] ]
				}),
				displayField : 'label',
				valueField : 'value',
				hiddenName : 'status',
				mode : 'local',
				editable : false
			} ]
		});
		addWindow.add(formPanel);
		addWindow.show();
	});
}


// 修改信息
if(toolbar.get("button_modify")){
	toolbar.get("button_modify").setHandler(function() {
	var selections = gridPanel.getSelectionModel().getSelections();
	if (!selections || selections.length <= 0) {
		Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
		return;
	}
	var id = selections[0].get("id");
	Ext.Ajax.request({
		url : CTA.menu.params.url.detailUrl,
		params : {
			id : id
		},
		success : function(response) {
			var data = Ext.util.JSON.decode(response.responseText).data;

			var formPanel = new CTA.common.SFormPanel({
				items : [
						{
							xtype : 'hidden',
							fieldLabel : '菜单ID',
							name : 'id',
							readOnly : true
						},
						{
							fieldLabel : '菜单名称',
							emptyText : '请输入菜单名称',
							name : 'name',
							minLength : 1,
							maxLength : 50
						},
						{
							fieldLabel : '菜单URL',
							emptyText : '请输入菜单URL',
							name : 'url',
							minLength : 1,
							maxLength : 250
						},
						{
							xtype : 'combo',
							id : 'super_menu',
							fieldLabel : '上级菜单',
							emptyText : '点击设置上级菜单',
							name : 'super_menu_id',
							hiddenName : 'super_menu_id',
							triggerAction : 'all',
							valueField : 'id',
							displayField : 'name',
							editable : false,
							allowBlank : false,
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
									url : basePath
											+ "/admin/menu/listMenus_super"
								}),
								autoLoad : true,
								listeners : {
									load : function() {
										Ext.getCmp('super_menu').setValue(
												data.super_menu_id);
										data.role_id = null;
									}
								}
							})
						},
						{
							xtype : 'combo',
							fieldLabel : '菜单排序',
							emptyText : '点击设置菜单排序',
							name : 'sort',
							hiddenName : 'sort',
							triggerAction : 'all',
							displayField : 'name',
							valueField : 'sort',
							editable : false,
							allowBlank : false,
							store : new Ext.data.Store({
								reader : new Ext.data.JsonReader({
									root : 'dataList',
									fields : [ {
										name : 'sort'
									}, {
										name : 'name'
									} ]
								}),
								proxy : new Ext.data.HttpProxy({
									url : basePath
											+ "/admin/menu/listMenus_sort"
								})
							})
						}, {
							xtype : 'combo',
							fieldLabel : '菜单状态',
							emptyText : '请选择菜单状态',
							name : 'status',
							triggerAction : 'all',
							store : new Ext.data.SimpleStore({
								fields : [ 'label', 'value' ],
								data : [ [ "启用", "1" ], [ "禁用", "-1" ] ]
							}),
							displayField : 'label',
							valueField : 'value',
							hiddenName : 'status',
							mode : 'local',
							editable : false
						} ],
				data : data
			});

			var buttonList = data.buttonList;

			var store = new Ext.data.Store({
				autoLoad : true,
				proxy : new Ext.data.HttpProxy({
					url : CTA.menu.params.url.listButtonUrl
				}),
				// baseParams:{limit:20},
				reader : new Ext.data.JsonReader({
					root : 'dataList',
					id : 'reader_'
				}, Ext.data.Record.create([ {
					name : 'id',
					type : 'int'
				}, {
					name : 'name',
					type : 'string'
				} ]))
			});

			var cm = new Ext.grid.ColumnModel([
					new Ext.grid.CheckboxSelectionModel(),
					{
						header : '按钮ID',
						dataIndex : 'id',
						renderer : function(v, metaData, record, rowIndex,
								colIndex, store) {
							var button_id = record.data.id;
							for (var i = 0; i < buttonList.length; i++) {
								var button_id_ = buttonList[i].id;
								if (button_id == button_id_) {
									buttonsGrid.getSelectionModel().selectRow(
											rowIndex, true);
									return button_id;
								}
							}
							return button_id;
						}
					}, {
						header : '按钮名称',
						dataIndex : 'name'
					} ]);

			var buttonsGrid = new Ext.grid.EditorGridPanel({
				region : 'south',
				store : store,
				cm : cm,
				sm : new Ext.grid.CheckboxSelectionModel(),
				width : 300,
				height : 270,
				frame : true,
				view : new Ext.grid.GridView({
					forceFit : true
				})
			});

			var updateWindow = new CTA.common.UpdateWindow({
				id : 'updateWindow',
				width : 600,
				height : 500,
				layout : 'border',
				items : [ formPanel, buttonsGrid ],
				handler : function() {
					// 检查表单是否填写好
					if (formPanel.getForm().isValid()) {
						CTA.common.Mask.showMask({
							target : 'updateWindow'
						});

						var params = formPanel.getForm().getValues();
						var buttonList_ = buttonsGrid.getSelectionModel()
								.getSelections();
						var buttonList = [];
						for (var i = 0; i < buttonList_.length; i++) {
							buttonList.push(buttonList_[i].data.id);
						}
						params.buttons = buttonList.toString();
						CTA.common.Ajax.request({
							url : CTA.menu.params.url.modifyUrl,
							params : params
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
// 查询
if(toolbar.get("button_query")){
		toolbar.get("button_query").setHandler(function() {
	
		var formPanel = new CTA.common.SFormPanel({
			items : [ {
				fieldLabel : '菜单ID',
				emptyText : '请输入菜单ID',
				name : 'id',
				allowBlank : true
			}, {
				fieldLabel : '菜单名称',
				emptyText : '请输入菜单名称',
				name : 'name',
				allowBlank : true
			}, {
				fieldLabel : '菜单URL',
				emptyText : '请输入菜单URL',
				name : 'url',
				allowBlank : true
			}, {
				xtype : 'combo',
				fieldLabel : '上级菜单',
				emptyText : '点击设置上级菜单',
				name : 'super_menu_id',
				hiddenName : 'super_menu_id',
				triggerAction : 'all',
				valueField : 'id',
				displayField : 'name',
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
						url : basePath + "/admin/menu/listMenus_super"
					})
				})
			}, {
				xtype : 'combo',
				fieldLabel : '菜单排序',
				emptyText : '点击设置菜单排序',
				name : 'sort',
				hiddenName : 'sort',
				triggerAction : 'all',
				displayField : 'name',
				valueField : 'sort',
				editable : false,
				allowBlank : true,
				store : new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'dataList',
						fields : [ {
							name : 'sort'
						}, {
							name : 'name'
						} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : basePath + "/admin/menu/listMenus_sort"
					})
				})
			}, {
				xtype : 'combo',
				fieldLabel : '菜单状态',
				emptyText : '请选择菜单状态',
				name : 'status',
				triggerAction : 'all',
				store : new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data : [ [ "启用", "1" ], [ "禁用", "-1" ] ]
				}),
				displayField : 'label',
				valueField : 'value',
				hiddenName : 'status',
				mode : 'local',
				editable : false,
				allowBlank : true
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
			height : 300,
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
					url : CTA.menu.params.url.dropUrl,
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
