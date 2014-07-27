//设置客户资料信息
Ext.ns("ROTEK.CUSTOMERDOC");
ROTEK.CUSTOMERDOC.params = {
	//全局gridpanel的参数
	gridParam : {
			url :   basePath + "/admin/customerdoc/listCustomerDocs/1",
		    dataList:[{
		          index:'id',
		          header:'证件ID',
		          width : 30,
				  align : 'center'
		      },{
		    	  index:'super_mc',
		          header:'证件所有人',
		          width : 50,
		  		  align : 'center'
		      },{
		          index:'khzlmc',
		          header:'证件名称',
     	          width : 50,
		  		  align : 'center'
		      },{
		    	  index:'khzllb',
		          header:'证件类别',
			      renderer:function(value){
			    	  if(1==value){
			    		  return "<span style='color:black;'>文档</span>";
				      }else if(2==value){
			    		  return "<span style='color:black;'>演示稿</span>";
				      }else if(3==value){
			    		  return "<span style='color:black;'>图片</span>";
				      }else if(4==value){
			    		  return "<span style='color:black;'>视频</span>";
				      }else if(5==value){
				    	  return "<span style='color:black'>监测图</span>";
				      }else if(6==value){
				    	  return "<span style='color:black'>代理商证件</span>";
				      }
				  },
		          width : 50,
		  		  align : 'center'
		      },{
		          index:'dlszjyxq',
		          header:'证件有效期',
		          width : 50,
		  		  align : 'center',
		  		  renderer : function(value){
					 return new Date(parseFloat(value)).format("Y-m-d");
				  }
		      },{
		        index:'status',
		        header:'证件状态',
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
		addUrl : basePath + "/admin/customerdoc/addCustomerDoc",
		detailUrl : basePath + "/admin/customerdoc/getCustomerDocDetail",
		modifyUrl : basePath + "/admin/customerdoc/modifyCustomerDoc",
		dropUrl : basePath + "/admin/customerdoc/deleteCustomerDoc",
		listCustomersUrl : basePath + "/admin/customer/listAllAgents"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.CUSTOMERDOC.params.gridParam);
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
	            url : ROTEK.CUSTOMERDOC.params.url.addUrl
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
		fileUpload : true,
	    items : [{
	        fieldLabel : '证件名称',
	        labelAlign : 'left',
	        labelWidth : 120,
	        emptyText : '请输入证件名称',
	        name : 'khzlmc',
	        minLength : 1,
	        maxLength: 100,
	        allowBlank : false
	      },{
		     xtype : 'combo',
		     fieldLabel : '证件类别',
		     labelAlign : 'left',
             labelWidth : 120,
		     emptyText : '请选择证件类别',
		     name : 'khzllb',
		     triggerAction : 'all',
		     store : new Ext.data.SimpleStore({
		       fields : ['label', 'value'],
		       data : [["代理商证件", "6"]]
		     }),
		     displayField : 'label',
		     valueField : 'value',
		     hiddenName : 'khzllb',
		     editable : false,
		     allowBlank : false,
		     mode : 'local'
	      },{        	  	
	        xtype : 'combo',
	        fieldLabel : '证件所有人',
	        labelAlign : 'left',
	        labelWidth : 120,
	        emptyText : '请选择证件所有人',
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
					url : ROTEK.CUSTOMERDOC.params.url.listCustomersUrl
				}),
				autoLoad : true
			})
	      },{
			fieldLabel : '证件附件',
			labelAlign : 'left',
			labelWidth : 120,
			name : 'khzlfj',
			text : "点击上传有效证件附件",
			inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
			blankText : '请上传有效证件附件',
			allowBlank : true
		  },{
			xtype : 'datefield',
			fieldLabel : '证件有效期',
			labelAlign : 'left',
			labelWidth : 120,
			emptyText : '请选择证件资料有效期',
			name : 'dlszjyxq',
			format:'Y-m-d',
			editable : false,
			allowBlank : true
		  },{
		     xtype : 'combo',
		     fieldLabel : '证件状态',
		     labelAlign : 'left',
		     labelWidth : 120,
		     emptyText : '请选择证件状态',
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
	    url : ROTEK.CUSTOMERDOC.params.url.detailUrl,
	    params : {id : ID},
	    success : function(response) {
	      var data = Ext.util.JSON.decode(response.responseText).data;
	      var customerdoc_info_formPanel = new CTA.common.SFormPanel({
	    	fileUpload : true,
	    	items : [{
	          xtype : 'hidden',
	          fieldLabel : '客户资料ID',
	          name : 'id',
	          readOnly:true
	        },{
		      fieldLabel : '证件名称',
		      emptyText : '请输入证件名称',
		      name : 'khzlmc',
		      allowBlank : false       	
		    },{
			  xtype : 'combo',
			  fieldLabel : '证件类别',
			  labelAlign : 'left',
	          labelWidth : 120,
			  emptyText : '请选择证件类别',
			  name : 'khzllb',
			  triggerAction : 'all',
			  store : new Ext.data.SimpleStore({
			       fields : ['label', 'value'],
			       data : [["代理商证件", "6"]]
			  }),
	          renderer:function(value){
	        	  console.log(value);
		    	  if(1==value){
		    		  return "<span style='color:green;'>文档</span>";
			      }else if(2==value){
		    		  return "<span style='color:green;'>演示稿</span>";
			      }else if(3==value){
		    		  return "<span style='color:green;'>图片</span>";
			      }else if(4==value){
		    		  return "<span style='color:green;'>视频</span>";
			      }else if(5==value){
		    		  return "<span style='color:green;'>监测图</span>";
			      }else if(6==value){
		    		  return "<span style='color:green;'>代理商证件</span>";
			      }
			  },
			  displayField : 'label',
			  valueField : 'value',
			  hiddenName : 'khzllb',
			  editable : false,
			  allowBlank : false,
			  mode : 'local'
		    },{    
		      id : 'khzlsyr',
		      xtype : 'combo',
		      fieldLabel : '证件所有人',
		      labelAlign : 'left',
		      labelWidth : 120,
		      emptyText : '请选择证件所有人',
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
					url : ROTEK.CUSTOMERDOC.params.url.listCustomersUrl
				}),
				autoLoad : true,
				listeners : {
					load : function (){
						Ext.getCmp('khzlsyr').setValue(data.r_customer_id);
					}
				}

				})
		   },{
			 fieldLabel : '有效证件附件',
			 labelAlign : 'left',
			 labelWidth : 120,
			 name : 'khzlfj',
			 readOnly : true
		   },{
			 xtype : 'datefield',
			 fieldLabel : '证件有效期',
			 labelAlign : 'left',
			 labelWidth : 120,
			 emptyText : '请选择证件资料有效期',
			 name : 'dlszjyxq',
			 format:'Y-m-d',
			 editable : false,
			 allowBlank : true
		   },{
	         xtype : 'combo',
	         name : 'status',
	         triggerAction : 'all',
	         fieldLabel : '证件状态',
	         emptyText : '请选择证件状态',
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
							url : ROTEK.CUSTOMERDOC.params.url.modifyUrl
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
				fieldLabel : '证件名称',
				emptyText : '请输入证件名称',
				name : 'khzlmc',
				allowBlank : true,
				minLength : 1,
				maxLength : 100
			}, {
			     xtype : 'combo',
			     fieldLabel : '证件类别',
			     labelAlign : 'left',
	             labelWidth : 120,
			     emptyText : '请选择证件类别',
			     name : 'khzllb',
			     triggerAction : 'all',
			     store : new Ext.data.SimpleStore({
			       fields : ['label', 'value'],
			       data : [["代理商证件", "6"]]
			     }),
			     displayField : 'label',
			     valueField : 'value',
			     hiddenName : 'khzllb',
			     editable : false,
			     allowBlank : true,
			     mode : 'local'
			}, {
				xtype : 'combo',
				fieldLabel : '证件状态',
				emptyText : '请选择证件状态',
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
		    		url : ROTEK.CUSTOMERDOC.params.url.dropUrl,
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
