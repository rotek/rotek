//设置管理员信息
Ext.ns("CTA.manager");
CTA.manager.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/manager/listManagers",
		    dataList:[{
		          index:'id',
		          header:'用户ID'
		      },{
		          index:'name',
		          header:'用户名称'
		      },{
		        index:'realname',
		        header:'真实姓名'
		      },{
		    	  index:'telephone',
		    	  header:'电话'
		      },{
		    	  index:'email',
		    	  header:'EMAIL'
		      },{
		    	  index:'companyname',
		    	  header:'所属公司'
		      },{
		    	  index:'rolename',
		    	  header:'用户角色'
		      },{
		        index:'status',
		        header:'用户状态',
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
		addUrl : basePath + "/admin/manager/addManager",
		detailUrl : basePath + "/admin/manager/getManagerDetail",
		modifyUrl : basePath + "/admin/manager/modifyManager",
		dropUrl : basePath + "/admin/manager/deleteManager",
		listRolesUrl : basePath + "/admin/role/listRoles_combo",

		listDepartmentsUrl : basePath + "/admin/department/listDepartments_s"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.manager.params.gridParam);
var toolbar = new CTA.common.Toolbar();
//添加按钮的事件
if (toolbar.get("button_add")) {
	toolbar.get("button_add").setHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.manager.params.url.addUrl
          });
        }
    };
	  //定义添加的窗口
	  var addWindow = new CTA.common.SaveWindow({
		id : 'addWindow',
	    width : 500,
	    height : 380,
	    layout : 'fit',
	    handler : saveHandler
	  });

	  //定义添加窗口中的form
	  var formPanel = new CTA.common.SFormPanel({
	    items : [{
	        fieldLabel : '用户名',
	        emptyText : '请输入用户名',
	        name : 'name',
	        minLength:6,
	        maxLength: 30
	      },{
	        fieldLabel : '用户密码',
	        emptyText : '请输入密码',
	        name : 'password',
	        vtype:'password',
	        minLength:6,
	        maxLength: 30
	      },{
	    	  fieldLabel : '真实姓名',
	    	  emptyText : '请输入真实姓名',
	    	  name : 'real_name',
	    	  minLength:1,
	    	  maxLength: 30
	      },{
	    	  xtype:'numberfield',
	    	  fieldLabel : '电话',
	    	  emptyText : '请输入电话',
	    	  name : 'phone',
	    	  vtype : 'telephone'
	      },{
	    	  fieldLabel : 'EMail',
	    	  emptyText : '请输入EMail地址',
	    	  name : 'email',
	    	  minLength:1,
	    	  maxLength: 30
	      },{
	    	  fieldLabel : '所属公司',
	    	  emptyText : '请输入公司名称',
	    	  name : 'companyname',
	    	  minLength:1,
	    	  maxLength: 30
	      },{
	          xtype : 'combo',
	          name : 'question',
	          triggerAction : 'all',
	          fieldLabel : '密保问题',
	          emptyText : '请选择密保问题',
	          store : new Ext.data.SimpleStore({
	            fields : ['value'],
	            data : [["我的小学班主任是？"],["我的初中班主任是？"],["我的高中班主任是？"],["我的媳妇是？"],["我最爱的游戏是？"],["我最该的电影是？"]]
	          }),
	          displayField : 'value',
	          valueField : 'value',
	          hiddenName : 'question',
	          editable : false,
	          mode : 'local'
	     },{
	    	  fieldLabel : '密保答案',
	    	  emptyText : '请输入密保答案',
	    	  name : 'answer',
	    	  minLength:1,
	    	  maxLength: 30
	    },{
				xtype : 'combo',
				fieldLabel : '用户角色',
				editable : false,
				emptyText : '请选择用户角色',
				name : 'role_id',
				triggerAction : 'all',
				displayField : 'name',
				valueField : 'id',
				hiddenName : 'role_id',
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
						url : CTA.manager.params.url.listRolesUrl
					})
				})
			},{
		        xtype : 'combo',
		        fieldLabel : '用户状态',
		        emptyText : '请选择状态',
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
  var id = selections[0].get("id");
  Ext.Ajax.request({
    url : CTA.manager.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
    	  	height : 350,
    	    items : [{
    	    	xtype : 'hidden',
    	        fieldLabel : '用户ID',
    	        name : 'id',
	            readOnly:true
    	      },{
    	    	  fieldLabel : '用户名',
    	    	  emptyText : '请输入管理员用户名',
    	    	  name : 'name',
    	    	  minLength:6,
    	    	  maxLength: 30
    	      },{
    	        fieldLabel : '用户密码',
    	        emptyText : '请输入管理员密码',
    	        name : 'password',
    	        vtype:'password',
    	        minLength:6,
    	        maxLength: 30
    	      },{
    	    	  fieldLabel : '真实姓名',
    	    	  emptyText : '请输入管理员真实姓名',
    	    	  name : 'real_name',
    	    	  minLength:1,
    	    	  maxLength: 30
    	      },{
    	    	  xtype : 'numberfield',
    	    	  fieldLabel : '管理员电话',
    	    	  emptyText : '请输入管理员电话',
    	    	  name : 'phone',
    	    	  vtype : 'telephone'
    	      },{
    	          xtype : 'combo',
    	          name : 'question_secu',
    	          triggerAction : 'all',
    	          fieldLabel : '密保问题',
    	          emptyText : '请选择密保问题',
    	          store : new Ext.data.SimpleStore({
    	            fields : ['value'],
    	            data : [["我的小学班主任是？"],["我的初中班主任是？"],["我的高中班主任是？"],["我的媳妇是？"],["我最爱的游戏是？"],["我最该的电影是？"]]
    	          }),
    	          displayField : 'value',
    	          valueField : 'value',
    	          hiddenName : 'question_secu',
    	          editable : false,
    	          mode : 'local'
    	     },{
    	    	  fieldLabel : '密保答案',
    	    	  emptyText : '请输入密保答案',
    	    	  name : 'answer',
    	    	  minLength:1,
    	    	  maxLength: 30
    	    	},{
    	        xtype : 'combo',
    	        fieldLabel : '管理员状态',
    	        emptyText : '请选择管理员状态',
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
    	        editable : false
    	      },{
    				xtype : 'combo',
    				id : 'manager_role',
    				fieldLabel : '管理员角色',
    				emptyText : '点击设置管理员角色',
    				name : 'role_id',
    				triggerAction : 'all',
    				displayField : 'name',
    				valueField : 'id',
    				hiddenName : 'role_id',
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
    						url : CTA.manager.params.url.listRolesUrl
    					}),
    					autoLoad : true,
        				listeners : {
        					load : function (){
    	    					Ext.getCmp('manager_role').setValue(data.role_id);
    	    					data.role_id = null;
        					}
        				}
    				})
    			},{
    				xtype : 'combo',
    				id : 'manager_dep',
    				fieldLabel : '所属部门',
    				editable : false,
    				emptyText : '点击设置角色所属部门',
    				name : 'dep_id',
    				hiddenName : 'dep_id',
    				triggerAction : 'all',
    				displayField : 'dep_name',
    				valueField : 'id',
    				allowBlank : true,
    				store : new Ext.data.Store({
    					reader : new Ext.data.JsonReader({
    						root : 'dataList',
    						fields : [ {
    							name : 'id'
    						}, {
    							name : 'dep_name'
    						} ]
    					}),
    					proxy : new Ext.data.HttpProxy({
    						url : CTA.manager.params.url.listDepartmentsUrl
    					}),
    					autoLoad : true,
    					listeners : {
    						load : function (){
    							Ext.getCmp('manager_dep').setValue(data.dep_id);
    						}
    					}
    				})
    			}],
    	      data : data
    	  });

      //Ext.getCmp('manager_role').setValue(data.name);
      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
        width : 500,
        height : 350,
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
              CTA.common.Mask.showMask({target:'updateWindow'});
              formPanel.commit({
                url : CTA.manager.params.url.modifyUrl
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
		        fieldLabel : '用户名',
		        emptyText : '请输入管理员用户名',
		        name : 'name',
		        allowBlank : true
		      },{
		    	  fieldLabel : '真实姓名',
		    	  emptyText : '请输入管理员真实姓名',
		    	  name : 'real_name',
		    	  allowBlank : true
		      },{
		    	  xtype:'numberfield',
		    	  fieldLabel : '管理员电话',
		    	  emptyText : '请输入管理员电话',
		    	  name : 'phone',
		    	  allowBlank : true,
		    	  vtype : 'telephone'
		      },{
		        xtype : 'combo',
		        fieldLabel : '管理员状态',
		        emptyText : '请选择管理员状态',
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
		        allowBlank : true,
		        editable : false
		      }],
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
		height : 300,
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
	    		url : CTA.manager.params.url.dropUrl,
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
