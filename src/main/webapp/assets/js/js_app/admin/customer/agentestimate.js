//设置客户资料信息
Ext.ns("ROTEK.AGENTESTIMATE");
ROTEK.AGENTESTIMATE.params = {
	//全局gridpanel的参数
	gridParam : {
			url :   basePath + "/admin/AgentEstimateInfo/listAgentEstimateInfos",
		    dataList:[{
		          index:'id',
		          header:'评价ID',
		          width : 30,
				  align : 'center'
		      },{
		    	  index:'customer_mc',
		          header:'代理商名称',
		          width : 50,
		  		  align : 'center'
		      },{
		          index:'dlsxjpj',
		          header:'代理商星级评价',
		          renderer:function(value){
			    	  if(10==value){
			    		  return "<span style='color:red;bold:true'>* * * * *</span>";
				      }else if((9==value)||(8==value)){
			    		  return "<span style='color:red;bold:true'>* * * *</span>";
				      }else if((7==value)||(6==value)){
			    		  return "<span style='color:red;bold:true'>* * *</span>";
				      }else if((4==value)||(5==value)){
			    		  return "<span style='color:red;bold:true'>* *</span>";
				      }else {
				    	  return "<span style='color:red;bold:true'>*</span>";
				      }
				  },
     	          width : 50,
		  		  align : 'center'
		      },{
		    	  index:'dlsglxz',
		          header:'代理商管理细则',			 
		          width : 50,
		  		  align : 'center'
		      },{
		        index:'status',
		        header:'评价信息状态',
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
		addUrl : basePath + "/admin/AgentEstimateInfo/addAgentEstimateInfo",
		detailUrl : basePath + "/admin/AgentEstimateInfo/getAgentEstimateInfoDetail",
		modifyUrl : basePath + "/admin/AgentEstimateInfo/modifyAgentEstimateInfo",
		dropUrl : basePath + "/admin/AgentEstimateInfo/deleteAgentEstimateInfo",
		listCustomersUrl : basePath + "/admin/AgentEstimateInfo/listCustomers"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.AGENTESTIMATE.params.gridParam);
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
	            url : ROTEK.AGENTESTIMATE.params.url.addUrl
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
	        xtype : 'combo',
	        fieldLabel : '代理商名称',
	        labelAlign : 'left',
	        labelWidth : 120,
	        emptyText : '请选择代理商',
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
					url : ROTEK.AGENTESTIMATE.params.url.listCustomersUrl
				}),
				autoLoad : true
			})
	      },{
	    	xtype : 'combo',
	        fieldLabel : '代理商星级分数',
	        labelAlign : 'left',
	        labelWidth : 120,
	        emptyText : '请选择代理商星级分数',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
			   fields : ['label', 'value'],
			   data : [["10", "10"]]
			}),
			displayField : 'label',
		    valueField : 'value',
		    hiddenName : 'dlsxjpj',
		    mode : 'local',
	        name : 'dlsxjpj',
	        minLength : 1,
	        maxLength: 100,
	        allowBlank : false,
	        editable : false
	      },{
			xtype : 'textarea',
			fieldLabel : '代理商管理细则',
			emptyText : '请输入代理商管理细则',
			name : 'dlsglxz',
			height : 70,
			width : 230
		   },{
		     xtype : 'combo',
		     fieldLabel : '评价信息状态',
		     labelAlign : 'left',
		     labelWidth : 120,
		     emptyText : '请选择评价信息状态',
		     name : 'status',
		     triggerAction : 'all',
		     store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data :  [ [ "有效", "1" ], [ "无效", "-1" ] ]
		     }),
		     displayField : 'label',
		     valueField : 'value',
		     hiddenName : 'status',
		     mode : 'local',
		     allowBlank : false,
		     editable : false
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
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }
	  var ID = selections[0].get("id");
	  Ext.Ajax.request({
	    url : ROTEK.AGENTESTIMATE.params.url.detailUrl,
	    params : {id : ID},
	    success : function(response) {
	      var data = Ext.util.JSON.decode(response.responseText).data;
	      var customerdoc_info_formPanel = new CTA.common.SFormPanel({
	    	items : [{
		          xtype : 'hidden',
		          fieldLabel : '代理商评价ID',
		          name : 'id',
		          readOnly:true
		        },{    
			      id : 'dlsmc',
			      xtype : 'combo',
			      fieldLabel : '代理商名称',
			      labelAlign : 'left',
			      labelWidth : 120,
			      emptyText : '请选择代理商',
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
						url : ROTEK.AGENTESTIMATE.params.url.listCustomersUrl
					}),
					autoLoad : true,
					listeners : {
						load : function (){
							Ext.getCmp('dlsmc').setValue(data.r_customer_id);
						}
	    			}
     			})
			},{
		    	xtype : 'combo',
		        fieldLabel : '代理商星级分数',
		        labelAlign : 'left',
		        labelWidth : 120,
		        emptyText : '请选择代理商星级分数',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
				   fields : ['label', 'value'],
				   data : [["1", "1"],["2", "2"],["3", "3"],["4", "4"],["5", "5"],["6", "6"],["7", "7"],["8", "8"],["9", "9"],["10", "10"]]
				}),
				displayField : 'label',
			    valueField : 'value',
			    hiddenName : 'dlsxjpj',
			    mode : 'local',
		        name : 'dlsxjpj',
		        minLength : 1,
		        maxLength: 100,
		        allowBlank : false,
		        editable : false
		    },{
				xtype : 'textarea',
				fieldLabel : '代理商管理细则',
				name : 'dlsglxz'
		   },{
	         xtype : 'combo',
	         name : 'status',
	         triggerAction : 'all',
	         fieldLabel : '评价信息状态',
	         emptyText : '请选择评价信息状态',
	         store : new Ext.data.SimpleStore({
	           fields : ['label', 'value'],
	           data :  [ [ "有效", "1" ], [ "无效", "-1" ] ]
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
							url : ROTEK.AGENTESTIMATE.params.url.modifyUrl
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
		    	xtype : 'combo',
		        fieldLabel : '代理商星级分数',
		        labelAlign : 'left',
		        labelWidth : 120,
		        emptyText : '请选择代理商星级分数',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
				   fields : ['label', 'value'],
				   data : [["1", "1"],["2", "2"],["3", "3"],["4", "4"],["5", "5"],["6", "6"],["7", "7"],["8", "8"],["9", "9"],["10", "10"]]
				}),
				displayField : 'label',
			    valueField : 'value',
			    hiddenName : 'dlsxjpj',
			    mode : 'local',
		        name : 'dlsxjpj',
		        minLength : 1,
		        maxLength: 100,
		        allowBlank : false,
		        editable : false
		    },{
				xtype : 'combo',
				fieldLabel : '评价信息状态',
				emptyText : '请选择评价信息状态',
				name : 'status',
				triggerAction : 'all',
				store : new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data :  [ [ "有效", "1" ], [ "无效", "-1" ] ]
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
		    		url : ROTEK.AGENTESTIMATE.params.url.dropUrl,
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
