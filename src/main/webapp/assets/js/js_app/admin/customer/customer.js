//设置客户信息
Ext.ns("ROTEK.CUSTOMER");
ROTEK.CUSTOMER.params = {
	//全局gridpanel的参数
	gridParam : {
			url :   basePath + "/admin/customer/listCustomers/3",
		    dataList:[{
		          index:'id',
		          header:'客户ID'
		      },{
		          index:'mc',
		          header:'客户名称'
		      },{
		    	  index:'khlb',
		          header:'客户类别',
			      renderer:function(value){
			    	  if(1==value){
			    		  return "<span style='color:green;'>一级代理商</span>";
				      }else if(2==value){
			    		  return "<span style='color:green;'>二级代理商</span>";
				      }else{
				    	  return "<span style='color:black'>客户</span>";
				      }
				  }
		      },{
		    	  index:'super_mc',
		          header:'所属上级'
		      },{
		          index:'txdz',
		          header:'通信地址'
		      },{
		          index:'lxfs',
		          header:'联系方式'
		      },{
		          index:'lxr',
		          header:'联系人'
		      },{
		          index:'lxdh',
		          header:'联系电话'
		      },{
		          index:'dlqy',
		          header:'代理区域'
		      },{
		          index:'jwddz',
		          header:'经纬度地址'
		      },{
		        index:'status',
		        header:'客户状态',
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
		addUrl : basePath + "/admin/customer/addCustomer/3",
		detailUrl : basePath + "/admin/customer/getCustomerDetail",
		modifyUrl : basePath + "/admin/customer/modifyCustomer",
		dropUrl : basePath + "/admin/customer/deleteCustomer",
		listAllAgentsUrl : basePath + "/admin/customer/listAllAgents"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.CUSTOMER.params.gridParam);
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
	            url : ROTEK.CUSTOMER.params.url.addUrl
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
	        fieldLabel : '客户名称',
	        emptyText : '请输入客户名称',
	        name : 'mc',
	        minLength : 1,
	        maxLength: 100
	      },{
	        fieldLabel : '通信地址',
	        emptyText : '请输入通信地址',
	        name : 'txdz',
	        minLength : 1,
	        maxLength: 200
	      },{
	        fieldLabel : '联系方式',
	        emptyText : '请输入联系方式',
	        name : 'lxfs',
	        minLength : 1,
	        maxLength: 50
	      },{
	        fieldLabel : '联系人',
	        emptyText : '请输入联系人',
	        name : 'lxr',
	        minLength : 1,
	        maxLength: 50
	     },{
	        fieldLabel : '联系电话',
	        emptyText : '请输入联系电话',
	        name : 'lxdh',
	        minLength : 1,
	        maxLength: 50
	     },{
	         fieldLabel : '经纬度地址',
	         emptyText : '请输入经纬度地址',
	         name : 'jwddz',
	         minLength : 1,
	         maxLength: 50,  
	         allowBlank : true
	     },{
		     id : 'agentlist',
		     xtype : 'combo',
		     fieldLabel : '所属代理商',
		     emptyText : '请选择所属代理商',
		     name : 'r_customer_id',
		     triggerAction : 'all',
		     displayField : 'mc',
			 valueField : 'id',
			 hiddenName : 'r_customer_id',
			 allowBlank : true,
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
						url : ROTEK.CUSTOMER.params.url.listAllAgentsUrl
					}),
					autoLoad : true
				}),
				
		    editable : false,
		    disabled : false
		},{
		        xtype : 'combo',
		        fieldLabel : '客户状态',
		        emptyText : '请选择客户状态',
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
	    url : ROTEK.CUSTOMER.params.url.detailUrl,
	    params : {id : ID},
	    success : function(response) {
	      var data = Ext.util.JSON.decode(response.responseText).data;
	      console.log(data);
	      console.log(data.khlb);
	      var customer_info_formPanel = new CTA.common.SFormPanel({
	        items : [{
	          xtype : 'hidden',
	          fieldLabel : '客户ID',
	          name : 'id',
	          readOnly:true
	        },{
	          xtype : 'hidden',
	          fieldLabel : '客户类别',
	          name : 'khlb',
	          editable : false, 
	          readOnly:true
	        },{
	          fieldLabel : '客户名称',
	          emptyText : '请输入客户名称',
	          name : 'mc',
	          allowBlank : false       	
	        },{
	          fieldLabel : '通信地址',
	          emptyText : '请输入通信地址',
	          name : 'txdz',
	          allowBlank : false
	        },{
	          fieldLabel : '联系方式',
	          emptyText : '请输入联系方式',
	          name : 'lxfs',
	          allowBlank : false
	        },{
	          fieldLabel : '联系人',
	          emptyText : '请输入联系人姓名',
	          name : 'lxr',
	          allowBlank : false
	        },{
	          fieldLabel : '联系电话',
	          emptyText : '请输入联系电话',
	          name : 'lxdh',
	          allowBlank : false
	        },{
	          fieldLabel : '经纬度地址',
	          emptyText : '请输入经纬度地址',
	          name : 'jwddz',
	          allowBlank : true
	        },{
	          xtype : 'combo',
	          name : 'status',
	          triggerAction : 'all',
	          fieldLabel : '客户状态',
	          emptyText : '请选择客户状态',
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
			  height : 500,
			  layout : 'border',
			  items : [ customer_info_formPanel ],
				handler : function() {
					//检查表单是否填写好
					if (customer_info_formPanel.getForm().isValid()) {
						CTA.common.Mask.showMask({
							target : 'updateWindow'
						});
						customer_info_formPanel.commit({
							url : ROTEK.CUSTOMER.params.url.modifyUrl
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
				fieldLabel : '客户名称',
				emptyText : '请输入客户名称',
				name : 'mc',
				allowBlank : true,
				minLength : 1,
				maxLength : 100
			}, {
				fieldLabel : '联系人',
				emptyText : '请输入联系人',
				name : 'lxr',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				xtype : 'combo',
				fieldLabel : '客户状态',
				emptyText : '请选择客户状态',
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
		    		url : ROTEK.CUSTOMER.params.url.dropUrl,
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
