//设置客户资料信息
Ext.ns("ROTEK.UNPROCESSINFO");
ROTEK.UNPROCESSINFO.params = {
	//全局gridpanel的参数
	gridParam : {
			url :   basePath + "/admin/UnProcessInfo/listUnProcessInfos",
		    dataList:[{
		          index:'id',
		          header:'信息ID',
		          width : 30,
				  align : 'center'
		      },{
		    	  index:'customer_mc',
		          header:'客户名称',
		          width : 50,
		  		  align : 'center'
		      },{
		    	  index:'project_mc',
		          header:'工程名称',
		          width : 50,
		  		  align : 'center'
		      },{
		    	  index:'errorinfolb',
		          header:'信息类别',	
		          renderer:function(value){
			    	  if(1==value){
			    		  return "<span style='color:black;'>报警未处理</span>";
				      }else if(2==value){
			    		  return "<span style='color:black;'>提醒未处理</span>";
				      }else if(3==value){
			    		  return "<span style='color:black;'>工程师未及时回复</span>";
				      }
				  },
		          width : 50,
		  		  align : 'center'
		      },{
		    	  index:'specific_bh',
		          header:'零件部位编号',			 
		          width : 50,
		  		  align : 'center'
		      },{
		    	  index:'specific_part',
		          header:'零件部位名称',			 
		          width : 50,
		  		  align : 'center'
		      },{
		          index:'jlrq',
		          header:'记录日期',
		          width : 50,
		  		  align : 'center',
		  		  renderer : function(value){
					 return new Date(parseFloat(value)).format("Y-m-d");
				  }
		      },{
			        index:'isdealed',
			        header:'是否已经处理',
	                width : 30,
			        align : 'center',
			        renderer:function(value){
			          if(1==value){
			            return "<span style='color:green;'>处理完毕</span>";
			          }else{
			            return "<span style='color:red'>未处理</span>";
			          }
			        }
			  },{
		        index:'status',
		        header:'信息状态',
                width : 30,
		        align : 'center',
		        renderer:function(value){
		          if(1==value){
		            return "<span style='color:green;'>有效</span>";
		          }else{
		            return "<span style='color:red'>无效</span>";
		          }
		        }
		      }]
	},
	url : {
		addUrl : basePath + "/admin/UnProcessInfo/addUnProcessInfo",
		detailUrl : basePath + "/admin/UnProcessInfo/getUnProcessInfoDetail",
		modifyUrl : basePath + "/admin/UnProcessInfo/modifyUnProcessInfo",
		dropUrl : basePath + "/admin/UnProcessInfo/deleteUnProcessInfo",
		listCustomersUrl : basePath + "/admin/UnProcessInfo/listCustomers",
		listProjectUrl: basePath + "/admin/UnProcessInfo/listProject"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.UNPROCESSINFO.params.gridParam);
var toolbar = new CTA.common.Toolbar();
//添加
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function() {
		
		var customerCache = {};
		var saveHandler = function(){
	        //检查表单是否填写好
	        if(formPanel.getForm().isValid()){
	          CTA.common.Mask.showMask({target:'addWindow'});
	          formPanel.commit({
	            url : ROTEK.UNPROCESSINFO.params.url.addUrl
	          });
	        }
	    };
		  //定义添加的窗口
		  var addWindow = new CTA.common.SaveWindow({
			id : 'addWindow',
			width : '40%',
			height : 400,
		    layout : 'fit',
		    handler : saveHandler
		  });
	
	  //定义添加窗口中的form
	  var formPanel = new CTA.common.SFormPanel({
	    items : [{  
	    	id : 'customer_combo',
	        xtype : 'combo',
	        fieldLabel : '客户名称',
	        labelAlign : 'left',
	        labelWidth : 120,
	        emptyText : '请选择客户名称',
	        name : 'r_customer_id',
	        triggerAction : 'all',
	        store : new Ext.data.Store({
				reader : new Ext.data.JsonReader({
					root : 'dataList',
					fields : [ {
						name : 'id'
					}, {
						name : 'mc'
					} ]
				}),
				proxy : new Ext.data.HttpProxy({
					url : ROTEK.UNPROCESSINFO.params.url.listCustomersUrl
				}),
				autoLoad : true
			}),
	        listeners : {
	        	select: function(combo,item,index){
	        		
	        		console.log(item);
	        		console.log(index);
	        		var purl = basePath + "/admin/UnProcessInfo/listProject?r_customer_id=" + item.id;
	        		console.log(purl);
        			Ext.Ajax.request({
        				url : purl, 
	        		    success : function(response) {
	        			      var projectList = Ext.util.JSON.decode(response.responseText).dataList;
	        			      console.log(projectList);
	        			      customerCache.projectList = [];
	        			      Ext.each(projectList, function(item) {
      			    			  var arr = new Array();
      			    			  arr.push(item.gcmc + "");
      			    			  arr.push(item.id);
	        			    	  customerCache.projectList.push(arr);
        			    	  }); 
	        			      console.log(projectList);
	        			      Ext.getCmp('projectlist').getStore().loadData(customerCache.projectList);
	        			      return true;
	        		    }
	        	    });
	        	}
	        },
	        displayField : 'mc',
	        valueField : 'id',
	        editable : false,
	        allowBlank : false,
	        hiddenName : 'r_customer_id',
	        mode : 'local'
	      },{
			     id : 'projectlist',
			     xtype : 'combo',
			     fieldLabel : '工程名称',
			     emptyText : '请选择工程名称',
			     name : 'r_project_id',
			     triggerAction : 'all',
			     displayField : 'gcmc',
				 valueField : 'id',
				 hiddenName : 'r_project_id',
				 allowBlank : true,
				 readonly : true,
				 store : new Ext.data.SimpleStore({
			            fields : ['gcmc', 'id'],
			            data : []
			    }),
			    mode : 'local',
			    editable : false
		  },{
				xtype : 'combo',
				fieldLabel : '未处理信息类别',
				emptyText : '请选择未处理信息类别',
				name : 'errorinfolb',
				triggerAction : 'all',
				store : new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data : [ [ "报警未处理", "1" ], [ "提醒未处理", "2" ], [ "工程师未及时回复", "3" ] ]
				}),
				displayField : 'label',
				valueField : 'value',
				hiddenName : 'errorinfolb',
				mode : 'local',
				allowBlank : true,
				editable : false
		  },{
		        fieldLabel : '零件部位编号',
		        emptyText : '请输零件部位编号',
		        name : 'specific_bh',
		        minLength : 1,
		        maxLength: 100
		  },{
		        fieldLabel : '零件部位名称',
		        emptyText : '请输零件部位名称',
		        name : 'specific_part',
		        minLength : 1,
		        maxLength: 100
		  },{
			xtype : 'datefield',
			fieldLabel : '记录日期',
			labelAlign : 'left',
			labelWidth : 120,
			emptyText : '请选择记录日期',
			name : 'jlrq',
			format:'Y-m-d',
			editable : false,
			allowBlank : true
		   },{
		     xtype : 'combo',
		     fieldLabel : '信息状态',
		     labelAlign : 'left',
		     labelWidth : 120,
		     emptyText : '请选择信息状态',
		     name : 'status',
		     triggerAction : 'all',
		     store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["启用", "1"],["禁用", "-1"]]
		     }),
		     displayField : 'label',
		     valueField : 'value',
		     hiddenName : 'status',
		     mode : 'local',
		     allowBlank : false,
		     editable : false
		  }],
		  listeners: {
		        click: {
		            element: 'customer_combo', //bind to the underlying el property on the panel
		            fn: function(){ console.log('click customer_combo'); }
		        }
		    }
	  });
	  addWindow.add(formPanel);
	  addWindow.show();
	});
}

//修改信息
if(toolbar.get("button_modify")){
	toolbar.get("button_modify").setHandler(function() {
	  var customerCache = {};
	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }
	  var ID = selections[0].get("id");
	  Ext.Ajax.request({
	    url : ROTEK.UNPROCESSINFO.params.url.detailUrl,
	    params : {id : ID},
	    success : function(response) {
	      var data = Ext.util.JSON.decode(response.responseText).data;
	      var customerdoc_info_formPanel = new CTA.common.SFormPanel({
	    	items : [{
		          xtype : 'hidden',
		          fieldLabel : '信息ID',
		          name : 'id',
		          readOnly:true
		        },{    
			      id : 'khmc',
			      xtype : 'combo',
			      fieldLabel : '客户名称',
			      labelAlign : 'left',
			      labelWidth : 120,
			      emptyText : '请选择客户',
			      name : 'r_customer_id',
			      hiddenName : 'r_customer_id',
			      triggerAction : 'all',
			      displayField : 'mc',
			      valueField : 'id',
			      editable : false,
			      allowBlank : false,
			      store : new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'dataList',
						fields : [ {
							name : 'id'
						}, {
							name : 'mc'
						} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : ROTEK.UNPROCESSINFO.params.url.listCustomersUrl
					}),
					autoLoad : true,
					listeners : {
						load : function (){
							Ext.getCmp('khmc').setValue(data.r_customer_id);
						}
	    			}
     			}),
     			listeners : {
	        	    select: function(combo,item,index){		        		
	        	    	console.log(item);
	        	    	console.log(index);
	        	    	var purl = basePath + "/admin/UnProcessInfo/listProject?r_customer_id=" + item.id;
		        		console.log(purl);
	        	    	Ext.Ajax.request({
	        	    		url : purl,
	        	    		success : function(response) {
	        			      var projectList = Ext.util.JSON.decode(response.responseText).dataList;
	        			      console.log(projectList);
	        			      customerCache.projectList = [];
	        			      Ext.each(projectList, function(item) {
      			    			  var arr = new Array();
      			    			  arr.push(item.gcmc + "");
      			    			  arr.push(item.id);
	        			    	  customerCache.projectList.push(arr);
        			    	  });  
	        			      Ext.getCmp('projectlist').getStore().loadData(customerCache.projectList);
//	        			      Ext.getCmp('projectlist').setValue("");
	        			      return true;
	        		    }
	        	    });
	        	  }
    			}
		  },{
				id : 'projectlist',
			     xtype : 'combo',
			     fieldLabel : '工程名称',
			     emptyText : '请选择工程名称',
			     name : 'r_project_id',
			     triggerAction : 'all',
			     displayField : 'gcmc',
				 valueField : 'id',
				 hiddenName : 'r_project_id',
				 allowBlank : true,
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
							url : basePath + "/admin/UnProcessInfo/listProject?r_customer_id=" + data.r_customer_id
						}),
						autoLoad : true,
						listeners : {
							load : function (){
								Ext.getCmp('projectlist').setValue(data.r_project_id);
							}
						}

				}),
			    mode : 'local',
			    editable : false
		  },{
				xtype : 'combo',
				fieldLabel : '未处理信息类别',
				emptyText : '请选择未处理信息类别',
				name : 'errorinfolb',
				triggerAction : 'all',
				store : new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data : [ [ "报警未处理", "1" ], [ "提醒未处理", "2" ], [ "工程师未及时回复", "3" ] ]
				}),
				displayField : 'label',
				valueField : 'value',
				hiddenName : 'errorinfolb',
				mode : 'local',
				allowBlank : true,
				editable : false
		  },{
			fieldLabel : '零件部位编号',
			emptyText : '请输入零件部位编号',
			name : 'specific_bh',
			allowBlank : false       	
		  },{
				fieldLabel : '零件部位名称',
				emptyText : '请输入零件部位名称',
				name : 'specific_part',
				allowBlank : false       	
		  },{
			 xtype : 'datefield',
			 fieldLabel : '记录日期',
			 labelAlign : 'left',
			 labelWidth : 120,
			 emptyText : '请选择投诉时间',
			 name : 'jlrq',
			 format:'Y-m-d',
			 editable : false,
			 allowBlank : true
		   },{
	         xtype : 'combo',
	         name : 'status',
	         triggerAction : 'all',
	         fieldLabel : '投诉信息状态',
	         emptyText : '请选择投诉信息状态',
	         store : new Ext.data.SimpleStore({
	           fields : ['label', 'value'],
	           data : [["启用", "1"],["禁用", "-1"]]
	         }),
	         displayField : 'label',
	         valueField : 'value',
	         hiddenName : 'status',
	         editable : false,
	         mode : 'local',
	         renderer : function(value){
	         }
	       }],
	       data : data
	      });
	
	      var updateWindow = new CTA.common.UpdateWindow({
	    	  id : 'updateWindow',
			  width : '40%',
			  height : 300,
			  layout : 'border',
			  items : [ customerdoc_info_formPanel ],
				handler : function() {
					//检查表单是否填写好
					if (customerdoc_info_formPanel.getForm().isValid()) {
						CTA.common.Mask.showMask({
							target : 'updateWindow'
						});
						customerdoc_info_formPanel.commit({
							url : ROTEK.UNPROCESSINFO.params.url.modifyUrl
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
			items : [{
				fieldLabel : '零件部位编号',
				emptyText : '请输入零件部位编号',
				name : 'specific_bh',
				allowBlank : true,
				minLength : 1,
				maxLength : 100
			},{
				fieldLabel : '零件部位名称',
				emptyText : '请输入零件部位名称',
				name : 'specific_part',
				allowBlank : true,
				minLength : 1,
				maxLength : 100
			},{
				xtype : 'combo',
				fieldLabel : '信息状态',
				emptyText : '请选择信息状态',
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
				allowBlank : true,
				editable : false
			} ],
			data : CTA.common.Constant.queryParams
		});
	
		var queryHandler = function(){
			var params = formPanel.getForm().getFieldValues() ;
			CTA.common.Constant.queryParams = {};
			Ext.apply(CTA.common.Constant.queryParams, params);
			gridPanel.getStore().reload();
		};
		// 查询窗口
		var queryWindow = new CTA.common.QueryWindow({
			width : 500,
			height : 250,
			layout : 'border',
			closeAction: 'hide',
			items : [formPanel],
			handler : queryHandler
		});
	
		queryWindow.show();
	});
}

//删除
if(toolbar.get("button_drop")){
	toolbar.get("button_drop").setHandler(function() {

		var selections = gridPanel.getSelectionModel().getSelections();
		if(!selections || selections.length <= 0){
			Ext.Msg.alert('提示', '请选择您要操作的数据!');
			return;
		}
		var ids = [];
		Ext.each(selections,function(item){
			ids.push(item.get("id"));
		});
	
		Ext.Msg.confirm("警告","您确定删除这  "+ids.length+" 条数据吗？",function(button){
		    if('yes' == button){
		    	CTA.common.Mask.showMask();
		    	CTA.common.Ajax.request({
		    		url : ROTEK.UNPROCESSINFO.params.url.dropUrl,
		    		params : {ids : ids.toString()}
		    	});
		    }
		});
	});
}

var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
