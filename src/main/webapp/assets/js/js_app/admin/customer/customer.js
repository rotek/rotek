//设置客户信息
Ext.ns("ROTEK.CUSTOMER");
ROTEK.CUSTOMER.params = {
	//全局gridpanel的参数
	gridParam : {
			url :   basePath + "/admin/customer/listCustomers",
		    dataList:[{
		          index:'ID',
		          header:'客户ID'
		      },{
		    	  index:'KHLB',
		          header:'客户类别',
			      renderer:function(value){
			    	  if(1==value){
			    		  return "<span style='color:green;'>代理商</span>";
				      }else{
				    	  return "<span style='color:red'>客户</span>";
				      }
				  }
		      },{
		          index:'R_CUSTOMER_ID',
		          header:'所属上级'
		      },{
		          index:'MC',
		          header:'客户名称'
		      },{
		          index:'TXDZ',
		          header:'通信地址'
		      },{
		          index:'LXFS',
		          header:'联系方式'
		      },{
		          index:'LXR',
		          header:'联系人'
		      },{
		          index:'LXDH',
		          header:'联系电话'
		      },{
		          index:'DLQY',
		          header:'代理区域'
		      },{
		          index:'SSJB',
		          header:'所属级别'
		      },{
		          index:'JWDDZ',
		          header:'经纬度地址'
		      },{
		        index:'STATUS',
		        header:'角色状态',
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
		addUrl : basePath + "/admin/customer/addCustomer",
		detailUrl : basePath + "/admin/customer/getCustomerDetail",
		modifyUrl : basePath + "/admin/customer/modifyCustomer",
		dropUrl : basePath + "/admin/customer/deleteCustomer"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(ROTEK.CUSTOMER.params.gridParam);
var toolbar = new CTA.common.Toolbar();
//添加
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function() {
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
			width : '80%',
			height : 500,
		    layout : 'fit',
		    handler : saveHandler
		  });
	
	  //定义添加窗口中的form
	  var formPanel = new CTA.common.SFormPanel({
	    items : [{
	        xtype : 'combo',
	        fieldLabel : '客户类别',
	        emptyText : '请选择客户类别',
	        name : 'KHLB',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["代理商", "1"],["客户", "2"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        hiddenName : 'KHLB',
	        mode : 'local',
	        editable : false
	      },{
	        fieldLabel : '客户名称',
	        emptyText : '请输入客户名称',
	        name : 'MC',
	        minLength : 1,
	        maxLength: 100
	      },{
	        fieldLabel : '通信地址',
	        emptyText : '请输入通信地址',
	        name : 'TXDZ',
	        minLength : 1,
	        maxLength: 200
	      },{
	        fieldLabel : '联系方式',
	        emptyText : '请输入联系方式',
	        name : 'LXFS',
	        minLength : 1,
	        maxLength: 50
	      },{
	        fieldLabel : '联系人',
	        emptyText : '请输入联系人',
	        name : 'LXR',
	        minLength : 1,
	        maxLength: 50
	     },{
	        fieldLabel : '联系电话',
	        emptyText : '请输入联系电话',
	        name : 'LXDH',
	        minLength : 1,
	        maxLength: 50
	     },{
	        fieldLabel : '代理区域',
	        emptyText : '请输入代理区域',
	        name : 'DLQY',
	        minLength : 1,
	        maxLength: 50
	     },{
	    	 xtype : 'combo',
	         fieldLabel : '所属级别',
	         emptyText : '请选择所属级别',
	         name : 'SSJB',
	         triggerAction : 'all',
	         store : new Ext.data.SimpleStore({
	           fields : ['label', 'value'],
	           data : [["一级代理商", "1"],["二级代理商", "2"],["二级代理商", "2"]]
	         }),
	         displayField : 'label',
	         valueField : 'value',
	         hiddenName : '所属级别',
	         mode : 'local',
	         editable : false
	     },{
	         fieldLabel : '经纬度地址',
	         emptyText : '请输入经纬度地址',
	         name : 'JWDDZ',
	         minLength : 1,
	         maxLength: 50    	 
	     },{
	        xtype : 'combo',
	        fieldLabel : '角色状态',
	        emptyText : '请选择角色状态',
	        name : 'STATUS',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["启用", "1"],["禁用", "-1"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        hiddenName : 'STATUS',
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
	  var roleId = selections[0].get("ID");
	  Ext.Ajax.request({
	    url : ROTEK.CUSTOMER.params.url.detailUrl,
	    params : {id : ID},
	    success : function(response) {
	      var data = Ext.util.JSON.decode(response.responseText).data;
	
	      var role_info_formPanel = new CTA.common.SFormPanel({
	        items : [{
	          xtype : 'hidden',
	          fieldLabel : '客户ID',
	          name : 'ID',
	          readOnly:true
	        },{
	          xtype : 'hidden',
	          fieldLabel : '客户类别',
	          name : 'KHLB',
	          readOnly:true
	        },{
	          fieldLabel : '客户名称',
	          emptyText : '请输入客户名称',
	          name : 'MC',
	          allowBlank : false       	
	        },{
	          fieldLabel : '通信地址',
	          emptyText : '请输入通信地址',
	          name : 'TXDZ',
	          allowBlank : false
	        },{
	          fieldLabel : '联系方式',
	          emptyText : '请输入联系方式',
	          name : 'LXFS',
	          allowBlank : false
	        },{
	          fieldLabel : '联系人',
	          emptyText : '请输入联系人姓名',
	          name : 'LXR',
	          allowBlank : false
	        },{
	          fieldLabel : '联系电话',
	          emptyText : '请输入联系电话',
	          name : 'LXDH',
	          allowBlank : false
	        },{
	          fieldLabel : '代理区域',
	          emptyText : '请输入代理区域',
	          name : 'DLQY',
	          allowBlank : false
	        },{
	          xtype : 'combo',
	          name : 'SSJB',
	          triggerAction : 'all',
	          fieldLabel : '所属级别',
	          emptyText : '请选择所属级别',
	          store : new Ext.data.SimpleStore({
	            fields : ['label', 'value'],
	            data : [["一级代理商", "1"],["二级代理商", "2"],["三级代理商", "3"]]
	          }),
	          displayField : 'label',
	          valueField : 'value',
	          hiddenName : 'SSJB',
	          editable : false,
	          mode : 'local',
	          renderer : function(value){
	          }
	        },{
	          fieldLabel : '经纬度地址',
	          emptyText : '请输入经纬度地址',
	          name : 'JWDDZ',
	          allowBlank : false
	        },{
	          xtype : 'combo',
	          name : 'STATUS',
	          triggerAction : 'all',
	          fieldLabel : '角色状态',
	          emptyText : '请选择角色状态',
	          store : new Ext.data.SimpleStore({
	            fields : ['label', 'value'],
	            data : [["启用", "1"],["禁用", "-1"]]
	          }),
	          displayField : 'label',
	          valueField : 'value',
	          hiddenName : 'STATUS',
	          editable : false,
	          mode : 'local',
	          renderer : function(value){
	          }
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
				fieldLabel : '客户名称',
				emptyText : '请输入客户名称',
				name : 'KHMC',
				allowBlank : true,
				minLength : 1,
				maxLength : 100
			}, {
				xtype : 'combo',
				fieldLabel : '客户类别',
				emptyText : '请选择客户类别',
				name : 'KHLB',
				triggerAction : 'all',
				store : new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data : [ [ "代理商", "1" ], [ "客户", "2" ] ]
				})
			}, {
				fieldLabel : '联系人',
				emptyText : '请输入联系人',
				name : 'LXR',
				allowBlank : true,
				minLength : 1,
				maxLength : 50
			}, {
				xtype : 'combo',
				fieldLabel : '客户状态',
				emptyText : '请选择客户状态',
				name : 'STATUS',
				triggerAction : 'all',
				store : new Ext.data.SimpleStore({
					fields : [ 'label', 'value' ],
					data : [ [ "启用", "1" ], [ "禁用", "-1" ] ]
				}),
				displayField : 'label',
				valueField : 'value',
				hiddenName : 'STATUS',
				mode : 'local',
				allowBlank : true,
				editable : false
			} ],
			data : CTA.common.Constant.queryParams
		});
	
		var queryHandler = function(){
			var params = formPanel.getForm().getFieldValues() ;
			CTA.common.Constant.queryParams = {};
			Ext.apply(CTA.common.Constant.queryParams,params);
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
			ids.push(item.get("ID"));
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
