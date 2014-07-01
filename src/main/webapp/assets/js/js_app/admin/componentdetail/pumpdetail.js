/** 管理泵组信息  */
Ext.ns("ROTEK.COMPONENTD.PUMP");
ROTEK.COMPONENTD.PUMP.params = {
	gridParam : {
		url : basePath + "/admin/componentdetail/listComDetail/1",
		dataList : [ {
			index : 'id',
			header : '零件ID',
			width : 40,
			align : 'center'
		}, {
			index : 'project_name',
			header : '工程名称',
			width : 100,
			align : 'center'
		}, {
			index : 'group_mc',
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
		},/* {
			index : 'edll',
			header : '额定扬程流量',
			width : 25,
			align : 'center'
		}, {
			index : 'edghsj',
			header : '额定维护保养时间',
			width : 25,
			align : 'center'
		}, {
			index : 'edddl',
			header : '额定电导率',
			width : 25,
			align : 'center'
		}, {
			index : 'edph',
			header : '额定PH值',
			width : 25,
			align : 'center'
		}, {
			index : 'edylv',
			header : '额定余氯',
			width : 25,
			align : 'center'
		}, {
			index : 'edwd',
			header : '额定温度',
			width : 25,
			align : 'center'
		}, {
			index : 'edyd',
			header : '额定硬度',
			width : 25,
			align : 'center'
		}, {
			index : 'edtds',
			header : '额定TDS值',
			width : 25,
			align : 'center'
		}, {
			index : 'edzdu',
			header : '额定浊度',
			width : 25,
			align : 'center'
		}, {
			index : 'edyl',
			header : '额定压力',
			width : 25,
			align : 'center'
		}, {
			index : 'edsdi',
			header : '额定SDI值',
			width : 25,
			align : 'center'
		}, {
			index : 'edcod',
			header : '额定COD值',
			width : 25,
			align : 'center'
		}, {
			index : 'edbod',
			header : '额定BOD值',
			width : 25,
			align : 'center'
		}, {
			index : 'edad',
			header : '额定氨氮',
			width : 25,
			align : 'center'
		}, {
			index : 'edzd',
			header : '额定总氮',
			width : 25,
			align : 'center'
		}, {
			index : 'edzl',
			header : '额定总磷',
			width : 25,
			align : 'center'
		}, {
			index : 'edxfw',
			header : '额定悬浮物',
			width : 25,
			align : 'center'
		}, {
			index : 'edywj',
			header : '额定液位计',
			width : 25,
			align : 'center'
		}, {
			index : 'edwnnd',
			header : '额定污泥浓度',
			width : 25,
			align : 'center'
		},*/{
			index : 'other_info',
			header : '其他信息',
			width : 25,
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
		addUrl : basePath + "/admin/componentdetail/addComDetail/1",
		detailUrl : basePath + "/admin/componentdetail/getComDetailDetail",
		modifyUrl : basePath + "/admin/componentdetail/modifyComDetail/1",
		dropUrl : basePath + "/admin/componentdetail/deleteComDetail",
		listProejctUrl : basePath + "/admin/componentdetail/selectProjectByType"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.COMPONENTD.PUMP.params.gridParam);
var toolbar = new CTA.common.Toolbar();

//添加工程信息
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function() {
		var saveHandler = function() {
			//检查表单是否填写好
			if (formPanel.getForm().isValid()) {
				formPanel.commit({
					url : ROTEK.COMPONENTD.PUMP.params.url.addUrl
				});
			}
		};
		//定义添加的窗口
		var addWindow = new CTA.common.SaveWindow({
			id : 'addWindow',
			width : '50%',
			height : 470,
			layout : 'fit',
			handler : saveHandler
		});

		//定义添加窗口中的form
		var formPanel = new CTA.common.SFormPanel({
			fileUpload : true,
			items : [ {
	     		xtype : 'container',
	     		autoEl : {},
	     		layout : 'column',
	     		width : 900,
	     		items : [ {
	     			layout : 'form',
	     			border : false,
	     			items : [ {
	     				xtype : 'combo',
	        			fieldLabel : '工程',
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
	        					url : ROTEK.COMPONENTD.PUMP.params.url.listProejctUrl
	        				})
	        			}),
	     				width : 445
	     			} ]
	     		} ]
	     	}, {
	     		xtype : 'container',
	     		autoEl : {},
	     		layout : 'column',
	     		width : 900,
	     		items : [ {
	     			layout : 'form',
	     			border : false,
	     			items : [ {
	     				xtype : 'combo',
	        			fieldLabel : '组',
	        			emptyText : '请选择组',
	        			name : 'id',
	        			hiddenName : 'id',
	        			triggerAction : 'all',
	        			displayField : 'group_mc',
	        			valueField : 'id',
	        			editable : false,
	        			store : new Ext.data.Store({
	        				reader : new Ext.data.JsonReader({
	        					root : 'dataList',
	        					fields : [ {
	        						name : 'id'
	        					}, {
	        						name : 'group_mc'
	        					} ]
	        				}),
	        				proxy : new Ext.data.HttpProxy({
	        					url : ROTEK.COMPONENTD.PUMP.params.url.listProejctUrl
	        				})
	        			}),
	     				width : 445
	     			} ]
	     		} ]
	     	}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '零件名称',
						emptyText : '请输入零件名称',
						name : 'specific_part',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '零件编号',
						emptyText : '请输入零件编号',
						name : 'specific_bh',
						width : 180
					} ]
				} ]
			}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定扬程流量',
						emptyText : '请输入额定扬程流量',
						name : 'edll',
						width : 150
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [{
						xtype : 'datefield',
						fieldLabel : '维护保养时间',
						emptyText : '请输入保养时间',
						name : 'edghsj',
						format:'Y-m-d',
						editable : false,
						allowBlank : true,
						width : 150
					}]
				} ]
			}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定电导率',
						emptyText : '请输入额定电导率',
						name : 'edddl',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定PH值',
						emptyText : '请输入额定PH值',
						name : 'edph',
						width : 180
					} ]
				} ]
			}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定余氯',
						emptyText : '请输入额定余氯',
						name : 'edylv',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定温度',
						emptyText : '请输入额定温度',
						name : 'edwd',
						width : 180
					} ]
				} ]
			}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定硬度',
						emptyText : '请输入额定硬度',
						name : 'edyd',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定硬度',
						emptyText : '请输入额定硬度',
						name : 'edyd',
						minLength : 1,
						width : 180
					} ]
				} ]
			}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定TDS值',
						emptyText : '请输入额定TDS值',
						name : 'edtds',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定压力',
						emptyText : '请输入额定压力',
						name : 'edyl',
						width : 180
					} ]
				} ]
			}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定浊度',
						emptyText : '请输入额定浊度',
						name : 'edzdu',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定SDI值',
						emptyText : '请输入额定SDI值',
						name : 'edsdi',
						width : 180
					} ]
				} ]
			}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定COD值',
						emptyText : '请输入额定COD值',
						name : 'edcod',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定BOD值',
						emptyText : '请输入额定BOD值',
						name : 'edbod',
						width : 180
					} ]
				} ]
			}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定氨氮',
						emptyText : '请输入额定氨氮',
						name : 'edad',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定总氮',
						emptyText : '请输入额定总氮',
						name : 'edzd',
						width : 180
					} ]
				} ]
			}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定总磷',
						emptyText : '请输入额定总磷',
						name : 'edzl',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定悬浮物',
						emptyText : '请输入额定悬浮物',
						name : 'edxfw',
						width : 180
					} ]
				} ]
			}, {
				xtype : 'container',
				layout : 'column',
				width : 900,
				items : [ {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定液位计',
						emptyText : '请输入额定液位计',
						name : 'edywj',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '额定污泥浓度',
						emptyText : '请输入额定污泥浓度',
						name : 'edwnnd',
						width : 180
					} ]
				} ]
			}, {
	     		xtype : 'container',
	     		autoEl : {},
	     		layout : 'column',
	     		width : 900,
	     		items : [ {
	     			layout : 'form',
	     			border : false,
	     			items : [ {
	     				xtype : 'textfield',
	     				fieldLabel : '其他信息',
	    				emptyText : '请输入其他信息',
	    				name : 'others',
	    				width : 445
	     		    } ]
	     		} ]
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
			url : ROTEK.COMPONENTD.PUMP.params.url.detailUrl,
			params : {
				id : id
			},
			success : function(response) {
				var data = Ext.util.JSON.decode(response.responseText).data;
				var formPanel = new CTA.common.SFormPanel({
					fileUpload : true,
					items : [{
		    			xtype : 'combo',
		    			fieldLabel : '工程',
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
		    					url : ROTEK.COMPONENTD.PUMP.params.url.listProejctUrl
		    				})
		    			})
		    		}, {
		    			xtype : 'combo',
		    			fieldLabel : '组',
		    			emptyText : '请选择组',
		    			name : 'id',
		    			hiddenName : 'id',
		    			triggerAction : 'all',
		    			displayField : 'group_mc',
		    			valueField : 'id',
		    			editable : false,
		    			store : new Ext.data.Store({
		    				reader : new Ext.data.JsonReader({
		    					root : 'dataList',
		    					fields : [ {
		    						name : 'id'
		    					}, {
		    						name : 'group_mc'
		    					} ]
		    				}),
		    				proxy : new Ext.data.HttpProxy({
		    					url : ROTEK.COMPONENTD.PUMP.params.url.listProejctUrl
		    				})
		    			})
		    		}, {
						fieldLabel : '零件编号',
						emptyText : '请输入零件编号',
						name : 'specific_bh',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '零件名称',
						emptyText : '请输入零件名称',
						name : 'specific_part',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定扬程流量',
						emptyText : '请输入额定扬程流量',
						name : 'edll',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定维护保养时间',
						emptyText : '请输入额定维护保养时间',
						name : 'edghsj',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定电导率',
						emptyText : '请输入额定电导率',
						name : 'edddl',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定PH值',
						emptyText : '请输入额定PH值',
						name : 'edph',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定余氯',
						emptyText : '请输入额定余氯',
						name : 'edylv',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定温度',
						emptyText : '请输入额定温度',
						name : 'edwd',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定硬度',
						emptyText : '请输入额定硬度',
						name : 'edyd',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定TDS值',
						emptyText : '请输入额定TDS值',
						name : 'edtds',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定浊度',
						emptyText : '请输入额定浊度',
						name : 'edzdu',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定压力',
						emptyText : '请输入额定压力',
						name : 'edyl',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定SDI值',
						emptyText : '请输入额定SDI值',
						name : 'edsdi',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定COD值',
						emptyText : '请输入额定COD值',
						name : 'edcod',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定BOD值',
						emptyText : '请输入额定BOD值',
						name : 'edbod',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定氨氮',
						emptyText : '请输入额定氨氮',
						name : 'edad',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定总氮',
						emptyText : '请输入额定总氮',
						name : 'edzd',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定总磷',
						emptyText : '请输入额定总磷',
						name : 'edzl',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定悬浮物',
						emptyText : '请输入额定悬浮物',
						name : 'edxfw',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定液位计',
						emptyText : '请输入额定液位计',
						name : 'edywj',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '额定污泥浓度',
						emptyText : '请输入额定污泥浓度',
						name : 'edwnnd',
						minLength : 1,
						maxLength : 100
					}, {
						fieldLabel : '其他信息',
						emptyText : '请输入其他信息',
						name : 'others',
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
								url : ROTEK.COMPONENTD.PUMP.params.url.modifyUrl
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
					url : ROTEK.COMPONENTD.PUMP.params.url.dropUrl,
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






items: [ {
	xtype : 'fieldset',
	title : '',
	autoWidth : true,
	labelWidth : 65,
	labelSeparator : ':',
	autoHeight : true,
	defaults : {
		width : 150,
		allowBlank : false,
		xtype : 'textfield',
		msgTarget : 'side'
	},
	labelAlign : 'left',
	items : [ {
		xtype : 'textfield',
		fieldLabel : '單據類型'
	}, {
		xtype : 'textfield',
		fieldLabel : '單據號碼'
	} ]
},

{
	xtype : 'fieldset',
	title : '',
	autoWidth : true,
	labelWidth : 65,
	labelSeparator : ':',
	autoHeight : true,
	defaults : {
		width : 150,
		allowBlank : false,
		xtype : 'textfield',
		msgTarget : 'side'
	},
	labelAlign : 'left',

	items : [

	{
		xtype : 'container',
		autoEl : {},
		layout : 'column',
		width : 900,
		items : [ {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客戶編號',
				width : 150
			} ]
		}, {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'combo',
				fieldLabel : '客戶分類',
				width : 150,
				store : newExt.data.SimpleStore({
					fields : [ "value", "text" ],
					data : [ [ '0', '一般客戶' ], [ '1', '經銷商' ] ]
				}),
				emptyText : '--請選擇--',
				mode : 'local',
				triggerAction : 'all',
				valueField : 'value',
				displayField : 'text',
				readOnly : true
			} ]
		} ]
	}, {
		xtype : 'container',
		autoEl : {},
		layout : 'column',
		width : 900,
		items : [ {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客戶名稱',
				width : 150
			} ]
		}, {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '聯絡電話',
				width : 150
			} ]
		} ]
	}, {
		xtype : 'container',
		autoEl : {},
		layout : 'column',
		width : 900,
		items : [ {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '聯絡人',
				width : 150
			} ]
		}, {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '傳真號碼',
				width : 150
			} ]
		} ]
	}, {
		xtype : 'container',
		autoEl : {},
		layout : 'column',
		width : 900,
		items : [ {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '電子郵件',
				width : 367
			} ]
		} ]
	}, {
		xtype : 'container',
		autoEl : {},
		layout : 'column',
		width : 900,
		items : [ {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '地址',
				width : 367
			} ]
		} ]
	} //

	]

}, {
	xtype : 'fieldset',
	title : '',
	autoWidth : true,
	labelWidth : 65,
	labelSeparator : ':',
	autoWidth : true,
	autoHeight : true,
	defaults : {
		width : 150,
		allowBlank : false,
		xtype : 'textfield',
		msgTarget : 'side'
	},
	labelAlign : 'left',
	items : [

	{
		xtype : 'container',
		autoEl : {},
		layout : 'column',
		width : 900,
		items : [ {
			layout : 'form',
			border : false,
			items : newExt.form.DateField({
				autoCreate : {
					tag : 'input',
					type : 'text',
					size : 20
				},
				fieldLabel : '需求日期',
				format : 'Y-m-d',
				value : '',
				width : 150
			})
		}, {
			layout : 'form',
			border : false,
			items : newExt.form.DateField({
				autoCreate : {
					tag : 'input',
					type : 'text',
					size : 20
				},
				fieldLabel : '指定時間',
				format : 'Y-m-d',
				value : '',
				width : 150
			})
		} ]
	}, {
		xtype : 'container',
		autoEl : {},
		layout : 'column',
		width : 900,
		items : [ {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '裝機地點',
				width : 150
			} ]
		}, {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '轉出單號',
				width : 150
			} ]
		} ]
	}, {
		xtype : 'container',
		autoEl : {},
		layout : 'column',
		width : 900,
		items : [ {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '客戶環境',
				width : 150
			} ]
		}, {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '訂單號碼',
				width : 150
			} ]
		} ]
	}, {
		xtype : 'container',
		autoEl : {},
		layout : 'column',
		width : 900,
		items : [ {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '需求項目',
				width : 150
			} ]
		}, {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textfield',
				fieldLabel : '專案編號',
				width : 150
			} ]
		} ]
	}, {
		xtype : 'container',
		autoEl : {},
		layout : 'column',
		width : 900,
		items : [ {
			layout : 'form',
			border : false,
			items : [ {
				xtype : 'textarea',
				fieldLabel : '注意事項',
				width : 367
			} ]
		} ]
	} //
	]

}, {
	xtype : 'fieldset',
	title : '',
	autoWidth : true,
	labelWidth : 65,
	labelSeparator : ':',
	autoWidth : true,
	autoHeight : true,
	defaults : {
		width : 150,
		allowBlank : false,
		xtype : 'textfield',
		msgTarget : 'side'
	},
	labelAlign : 'left',
	items : [ {
		fieldLabel : '品號'
	}, {
		fieldLabel : '品名'
	}, {
		fieldLabel : '規格'
	}

	]

} ]











