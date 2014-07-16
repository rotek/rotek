/** 管理泵组信息  */
Ext.ns("ROTEK.ALGORITHMS1");
ROTEK.ALGORITHMS1.params = {
	gridParam : {
		url : basePath + "/admin/algorithms/listAlgorithms/1",
		dataList : [ {
			index : 'id',
			header : 'ID',
			width : 30,
			align : 'center'
		}, {
			index : 'customer_name',
			header : '用户名称',
			width : 40,
			align : 'center'
		}, {
			index : 'project_name',
			header : '工程名称',
			width : 40,
			align : 'center'
		}, {
			index : 'component_group_name',
			header : '零件组名称',
			width : 40,
			align : 'center'
		}, {
			index : 'component_name',
			header : '零件名称',
			width : 40,
			align : 'center'
		}, {
			index : 'ljghsj',
			header : '更换时间',
			width : 30,
			align : 'center',
			renderer : function(value){
				return new Date(parseFloat(value)).format("Y-m-d");
			}
		}, {
			index : 'ljedyxsj',
			header : '额定运行时间',
			width : 30,
			align : 'center',
			renderer : function(value){
				return new Date(parseFloat(value)).format("Y-m-d");
			}
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
		addUrl : basePath + "/admin/algorithms/addAlgorithms/1",
		detailUrl : basePath + "/admin/algorithms/getComDetail",
		modifyUrl : basePath + "/admin/algorithms/modifyAlgorithms/1",
		dropUrl : basePath + "/admin/algorithms/deleteAlgorithms",
		listProejctUrl : basePath + "/admin/algorithms/selectProjectByType/1"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.ALGORITHMS1.params.gridParam);
var toolbar = new CTA.common.Toolbar();

//添加工程信息
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function() {
		var saveHandler = function() {
			//检查表单是否填写好
			if (formPanel.getForm().isValid()) {
				formPanel.commit({
					url : ROTEK.ALGORITHMS1.params.url.addUrl
				});
			}
		};
		//定义添加的窗口
		var addWindow = new CTA.common.SaveWindow({
			id : 'addWindow',
			width : '50%',
			height : 437,
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
				url : ROTEK.ALGORITHMS1.params.url.listProejctUrl
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
				url : basePath + "/admin/algorithms/selectGroupByPid/0/1"
			})
		})		

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
 									url : basePath + "/admin/algorithms/selectGroupByPid/" + ProjectCombox.value + "/1"
 								});
	     						GroupStore.removeAll() ;  //清空缓存的数据
	     						Ext.getCmp('GROUPCOMB').setValue("");
	     						Ext.getCmp('GROUPCOMB').setRawValue("");
	     						GroupStore.load();
	     					}
	     				}
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
	     			} ]
	     		} ]
	     	} {
	     		xtype : 'container',
	     		autoEl : {},
	     		layout : 'column',
	     		width : 900,
	     		items : [ {
	     			layout : 'form',
	     			border : false,
	     			items : [ {
	     				xtype : 'combo',
	        			fieldLabel : '零件类别',
	        			emptyText : '请零件类别',
	        			name : 'r_component_group_id',
	        			hiddenName : 'r_component_group_id',
	        			triggerAction : 'all',
	        			displayField : 'label',
	        			valueField : 'value',
	        			editable : false,
	        			allowBlank : false,
	        			store : new Ext.data.SimpleStore({
	    					fields : [ 'label', 'value' ],
	    					data : [[ "泵", "1" ], [ "砂滤器", "2" ], [ "碳滤器", "3" ], [ "软化器", "4" ]
	    						, [ "过滤器", "5" ], [ "膜", "6" ], [ "紫外杀菌器", "7" ], [ "水箱", "8" ], [ "加药装置", "9" ]]
	    				}),
	     				width : 445,
	     				listeners : {
	     					select : function(ProjectCombox, record, index) {
	     						GroupStore.proxy = new Ext.data.HttpProxy({
 									url : basePath + "/admin/algorithms/selectGroupByPid/" + ProjectCombox.value + "/1"
 								});
	     						GroupStore.removeAll() ;  //清空缓存的数据
	     						Ext.getCmp('GROUPCOMB').setValue("");
	     						Ext.getCmp('GROUPCOMB').setRawValue("");
	     						GroupStore.load();
	     					}
	     				}
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
						allowBlank : false,
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
						allowBlank : false,
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
						xtype : 'numberfield',
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
						xtype : 'numberfield',
						fieldLabel : '额定电导率',
						emptyText : '请输入额定电导率',
						name : 'edddl',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'numberfield',
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
						xtype : 'numberfield',
						fieldLabel : '额定余氯',
						emptyText : '请输入额定余氯',
						name : 'edylv',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'numberfield',
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
						xtype : 'numberfield',
						fieldLabel : '额定硬度',
						emptyText : '请输入额定硬度',
						name : 'edyd',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'numberfield',
						fieldLabel : '额定液位计',
						emptyText : '请输入额定液位计',
						name : 'edywj',
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
						xtype : 'numberfield',
						fieldLabel : '额定TDS值',
						emptyText : '请输入额定TDS值',
						name : 'edtds',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'numberfield',
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
						xtype : 'numberfield',
						fieldLabel : '额定浊度',
						emptyText : '请输入额定浊度',
						name : 'edzdu',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'numberfield',
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
						xtype : 'numberfield',
						fieldLabel : '额定COD值',
						emptyText : '请输入额定COD值',
						name : 'edcod',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'numberfield',
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
						xtype : 'numberfield',
						fieldLabel : '额定氨氮',
						emptyText : '请输入额定氨氮',
						name : 'edad',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'numberfield',
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
						xtype : 'numberfield',
						fieldLabel : '额定总磷',
						emptyText : '请输入额定总磷',
						name : 'edzl',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'numberfield',
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
						xtype : 'numberfield',
						fieldLabel : '额定污泥浓度',
						emptyText : '请输入额定污泥浓度',
						name : 'edwnnd',
						width : 180
					} ]
				}, {
					layout : 'form',
					border : false,
					items : [ {
						xtype : 'textfield',
						fieldLabel : '其他信息',
	    				emptyText : '请输入其他信息',
	    				name : 'other_info',
						width : 180
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
			url : ROTEK.ALGORITHMS1.params.url.detailUrl,
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
						url : ROTEK.ALGORITHMS1.params.url.listProejctUrl
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
						url : basePath + "/admin/algorithms/selectGroupByPid/0/1"
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
		 									url : basePath + "/admin/algorithms/selectGroupByPid/" + ProjectCombox.value + "/1"
		 								});
			     						GroupStore.removeAll() ;  //清空缓存的数据
			     						Ext.getCmp('GROUPCOMB').setValue("");
			     						Ext.getCmp('GROUPCOMB').setRawValue("");
			     						GroupStore.load();
			     					}
			     				}
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
								allowBlank : false,
								width : 180,
								value : data.specific_part
							} ]
						}, {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'textfield',
								fieldLabel : '零件编号',
								emptyText : '请输入零件编号',
								name : 'specific_bh',
								allowBlank : false,
								width : 180,
								value : data.specific_bh
							} ],
						} ]
					}, {
						xtype : 'container',
						layout : 'column',
						width : 900,
						items : [ {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'numberfield',
								fieldLabel : '额定扬程流量',
								emptyText : '请输入额定扬程流量',
								name : 'edll',
								width : 150,
								value : data.edll
							} ],
							data : data.edll
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
								width : 150,
								value : new Date(parseFloat(data.edghsj)).format("Y-m-d")
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
								xtype : 'numberfield',
								fieldLabel : '额定电导率',
								emptyText : '请输入额定电导率',
								name : 'edddl',
								width : 180,
								value : data.edddl
							} ]
						}, {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'numberfield',
								fieldLabel : '额定PH值',
								emptyText : '请输入额定PH值',
								name : 'edph',
								width : 180,
								value : data.edph
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
								xtype : 'numberfield',
								fieldLabel : '额定余氯',
								emptyText : '请输入额定余氯',
								name : 'edylv',
								width : 180,
								value : data.edylv
							} ]
						}, {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'numberfield',
								fieldLabel : '额定温度',
								emptyText : '请输入额定温度',
								name : 'edwd',
								width : 180,
								value : data.edwd
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
								xtype : 'numberfield',
								fieldLabel : '额定硬度',
								emptyText : '请输入额定硬度',
								name : 'edyd',
								width : 180,
								value : data.edyd
							} ]
						}, {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'numberfield',
								fieldLabel : '额定液位计',
								emptyText : '请输入额定液位计',
								name : 'edywj',
								minLength : 1,
								width : 180,
								value : data.edywj
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
								xtype : 'numberfield',
								fieldLabel : '额定TDS值',
								emptyText : '请输入额定TDS值',
								name : 'edtds',
								width : 180,
								value : data.edtds
							} ]
						}, {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'numberfield',
								fieldLabel : '额定压力',
								emptyText : '请输入额定压力',
								name : 'edyl',
								width : 180,
								value : data.edyl
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
								xtype : 'numberfield',
								fieldLabel : '额定浊度',
								emptyText : '请输入额定浊度',
								name : 'edzdu',
								width : 180,
								value : data.edzdu
							} ]
						}, {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'numberfield',
								fieldLabel : '额定SDI值',
								emptyText : '请输入额定SDI值',
								name : 'edsdi',
								width : 180,
								value : data.edsdi
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
								xtype : 'numberfield',
								fieldLabel : '额定COD值',
								emptyText : '请输入额定COD值',
								name : 'edcod',
								width : 180,
								value : data.edcod
							} ]
						}, {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'numberfield',
								fieldLabel : '额定BOD值',
								emptyText : '请输入额定BOD值',
								name : 'edbod',
								width : 180,
								value : data.edbod
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
								xtype : 'numberfield',
								fieldLabel : '额定氨氮',
								emptyText : '请输入额定氨氮',
								name : 'edad',
								width : 180,
								value : data.edad
							} ]
						}, {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'numberfield',
								fieldLabel : '额定总氮',
								emptyText : '请输入额定总氮',
								name : 'edzd',
								width : 180,
								value : data.edzd
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
								xtype : 'numberfield',
								fieldLabel : '额定总磷',
								emptyText : '请输入额定总磷',
								name : 'edzl',
								width : 180,
								value : data.edzl
							} ]
						}, {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'numberfield',
								fieldLabel : '额定悬浮物',
								emptyText : '请输入额定悬浮物',
								name : 'edxfw',
								width : 180,
								value : data.edxfw
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
								xtype : 'numberfield',
								fieldLabel : '额定污泥浓度',
								emptyText : '请输入额定污泥浓度',
								name : 'edwnnd',
								width : 180,
								value : data.edwnnd
							} ]
						}, {
							layout : 'form',
							border : false,
							items : [ {
								xtype : 'textfield',
								fieldLabel : '其他信息',
			    				emptyText : '请输入其他信息',
			    				name : 'other_info',
								width : 180,
								value : data.other_info
							} ]
						} ]
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
								url : ROTEK.ALGORITHMS1.params.url.modifyUrl
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

//查看详情
if(toolbar.get("button_viewDetail")){
	toolbar.get("button_viewDetail").setHandler(function() {
	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }
	  var id = selections[0].get("id");
	  Ext.Ajax.request({
		    url : ROTEK.ALGORITHMS1.params.url.detailUrl,
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
							url : ROTEK.ALGORITHMS1.params.url.listProejctUrl
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
							url : basePath + "/admin/algorithms/selectGroupByPid/0/1"
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
				        			hiddenName : 'r_project_id',
				        			triggerAction : 'all',
				        			displayField : 'gcmc',
				        			valueField : 'id',
				        			editable : false,
				        			allowBlank : false,
				        			store : ProjectStore,
				        			disabled : true,
				     				width : 445,
				     				value : data.project_name,
				     				//value : data.r_project_id,
				     				listeners : {
				     					select : function(ProjectCombox, record, index) {
				     						GroupStore.proxy = new Ext.data.HttpProxy({
			 									url : basePath + "/admin/algorithms/selectGroupByPid/" + ProjectCombox.value + "/1"
			 								});
				     						GroupStore.removeAll() ;  //清空缓存的数据
				     						Ext.getCmp('GROUPCOMB').setValue("");
				     						Ext.getCmp('GROUPCOMB').setRawValue("");
				     						GroupStore.load();
				     					}
				     				}
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
				     				id : 'GROUPCOMB',
				        			fieldLabel : '组',
				        			emptyText : '请选择组',
				        			name : 'id',
				        			hiddenName : 'r_component_group_id',
				        			triggerAction : 'all',
				        			displayField : 'group_mc',
				        			valueField : 'id',
				        			editable : false,
				        			disabled : true,
				        			allowBlank : false,
				        			store : GroupStore,
				     				width : 445,
				     				value : data.group_name
				     				//value : data.r_component_group_id
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
									allowBlank : false,
									width : 180,
									disabled : true,
									value : data.specific_part
								} ]
							}, {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'textfield',
									fieldLabel : '零件编号',
									emptyText : '请输入零件编号',
									name : 'specific_bh',
									allowBlank : false,
									disabled : true,
									width : 180,
									value : data.specific_bh
								} ],
							} ]
						}, {
							xtype : 'container',
							layout : 'column',
							width : 900,
							items : [ {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'numberfield',
									fieldLabel : '额定扬程流量',
									emptyText : '请输入额定扬程流量',
									name : 'edll',
									disabled : true,
									width : 150,
									value : data.edll
								} ],
								data : data.edll
							}, {
								layout : 'form',
								border : false,
								items : [{
									xtype : 'datefield',
									fieldLabel : '维护保养时间',
									emptyText : '请输入保养时间',
									name : 'edghsj',
									editable : false,
									format:'Y-m-d',
									allowBlank : true,
									width : 150,
									disabled : true ,
									value : new Date(parseFloat(data.edghsj)).format("Y-m-d")
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
									xtype : 'numberfield',
									fieldLabel : '额定电导率',
									emptyText : '请输入额定电导率',
									name : 'edddl',
									disabled : true,
									width : 180,
									value : data.edddl
								} ]
							}, {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'numberfield',
									fieldLabel : '额定PH值',
									emptyText : '请输入额定PH值',
									disabled : true,
									name : 'edph',
									width : 180,
									value : data.edph
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
									xtype : 'numberfield',
									fieldLabel : '额定余氯',
									emptyText : '请输入额定余氯',
									name : 'edylv',
									disabled : true,
									width : 180,
									value : data.edylv
								} ]
							}, {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'numberfield',
									fieldLabel : '额定温度',
									emptyText : '请输入额定温度',
									name : 'edwd',
									disabled : true,
									width : 180,
									value : data.edwd
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
									xtype : 'numberfield',
									fieldLabel : '额定硬度',
									emptyText : '请输入额定硬度',
									disabled : true,
									name : 'edyd',
									width : 180,
									value : data.edyd
								} ]
							}, {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'numberfield',
									fieldLabel : '额定液位计',
									emptyText : '请输入额定液位计',
									name : 'edywj',
									disabled : true,
									minLength : 1,
									width : 180,
									value : data.edywj
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
									xtype : 'numberfield',
									fieldLabel : '额定TDS值',
									emptyText : '请输入额定TDS值',
									disabled : true,
									name : 'edtds',
									width : 180,
									value : data.edtds
								} ]
							}, {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'numberfield',
									fieldLabel : '额定压力',
									emptyText : '请输入额定压力',
									disabled : true,
									name : 'edyl',
									width : 180,
									value : data.edyl
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
									xtype : 'numberfield',
									fieldLabel : '额定浊度',
									emptyText : '请输入额定浊度',
									disabled : true,
									name : 'edzdu',
									width : 180,
									value : data.edzdu
								} ]
							}, {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'numberfield',
									fieldLabel : '额定SDI值',
									emptyText : '请输入额定SDI值',
									disabled : true,
									name : 'edsdi',
									width : 180,
									value : data.edsdi
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
									xtype : 'numberfield',
									fieldLabel : '额定COD值',
									emptyText : '请输入额定COD值',
									disabled : true,
									name : 'edcod',
									width : 180,
									value : data.edcod
								} ]
							}, {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'numberfield',
									fieldLabel : '额定BOD值',
									emptyText : '请输入额定BOD值',
									disabled : true,
									name : 'edbod',
									width : 180,
									value : data.edbod
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
									xtype : 'numberfield',
									fieldLabel : '额定氨氮',
									emptyText : '请输入额定氨氮',
									disabled : true,
									name : 'edad',
									width : 180,
									value : data.edad
								} ]
							}, {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'numberfield',
									fieldLabel : '额定总氮',
									emptyText : '请输入额定总氮',
									disabled : true,
									name : 'edzd',
									width : 180,
									value : data.edzd
								} ]
							} ]
						}, {
							xtype : 'container',
							layout : 'column',
							width : 900,
							items : [{
								layout : 'form',
								border : false,
								items : [{
									xtype : 'numberfield',
									fieldLabel : '额定总磷',
									emptyText : '请输入额定总磷',
									disabled : true,
									name : 'edzl',
									width : 180,
									value : data.edzl
								}]
							}, {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'numberfield',
									fieldLabel : '额定悬浮物',
									emptyText : '请输入额定悬浮物',
									disabled : true,
									name : 'edxfw',
									width : 180,
									value : data.edxfw
								}]
							}]
						}, {
							xtype : 'container',
							layout : 'column',
							width : 900,
							items : [ {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'numberfield',
									fieldLabel : '额定污泥浓度',
									emptyText : '请输入额定污泥浓度',
									disabled : true,
									name : 'edwnnd',
									width : 180,
									value : data.edwnnd
								} ]
							}, {
								layout : 'form',
								border : false,
								items : [ {
									xtype : 'textfield',
									fieldLabel : '其他信息',
				    				emptyText : '请输入其他信息',
									disabled : true,
				    				name : 'other_info',
									width : 180,
									value : data.other_info
								} ]
							} ]
						}]
					});
				  var detailWindow = new CTA.common.Window({
				    	title : '查看零件详情',
				    	width : '50%',
						height : 437,
						layout : 'border',
				        items : [formPanel]
			      });
			      detailWindow.show();
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
					url : ROTEK.ALGORITHMS1.params.url.dropUrl,
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

