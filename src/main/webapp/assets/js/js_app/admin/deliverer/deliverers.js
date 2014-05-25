//设置配送员信息
Ext.ns("CTA.deliverer");
CTA.deliverer.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/deliverer/listDeliverers",
		    dataList:[{
		          index:'id',
		          header:'配送员ID'
		      },{
		          index:'realname',
		          header:'配送员姓名'
		      },{
		        index:'telephone',
		        header:'配送员电话'
		      },{
		    	  index:'send_type',
		    	  header:'订单通知方式',
		    	  renderer:function(value){
			          if(1==value){
			            return "短信通知";
			          }else{
			            return "其他方式";
			          }
			        }
		      },{
		    	  index:'gener',
		    	  header:'配送员性别',
		    	  renderer:function(value){
			          if(1==value){
			            return "女";
			          }else{
			            return "男";
			          }
			        }
		      },{
		    	  index:'dep_name',
		    	  header:'管理部门',
		    	  renderer:function(value){
			          if(!value){
			            return "<span style='color:red;'>未分配</span>";
			          }else{
			            return value;
			          }
			        }
		      },{
		        index:'status',
		        header:'配送员状态',
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
		addUrl : basePath + "/admin/deliverer/addDeliverer",
		detailUrl : basePath + "/admin/deliverer/getDelivererDetail",
		modifyUrl : basePath + "/admin/deliverer/modifyDeliverer",
		dropUrl : basePath + "/admin/deliverer/deleteDeliverer",
		queryUrl : basePath + "/admin/deliverer/queryDeliverer",

		listDepartmentsUrl : basePath + "/admin/department/listDepartments_s",
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.deliverer.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.deliverer.params.url.addUrl
          });
        }
    };
	  //定义添加的窗口
	  var addWindow = new CTA.common.SaveWindow({
		id : 'addWindow',
	    width : 500,
	    height : 320,
	    layout : 'fit',
	    handler : saveHandler
	  });

  //定义添加窗口中的form
  var formPanel = new CTA.common.SFormPanel({
    items : [{
          fieldLabel : '配送员姓名',
          emptyText : '请输入配送员姓名',
          name : 'realname',
          minLength:1,
          maxLength: 10
        },{
    	  fieldLabel : '配送员电话',
    	  emptyText : '请输入配送员电话',
    	  name : 'telephone',
    	  vtype : 'telephone'
      },{
	        xtype : 'combo',
	        fieldLabel : '配送员性别',
	        emptyText : '请选择配送员性别',
	        name : 'gender',
	        hiddenName : 'gender',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["男", "1"],["女", "2"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        mode : 'local',
	        editable : false
      },{
    	  xtype : 'combo',
    	  fieldLabel : '通知方式',
    	  emptyText : '请选择配送员订单通知方式',
    	  name : 'send_type',
    	  hiddenName : 'send_type',
    	  triggerAction : 'all',
    	  store : new Ext.data.SimpleStore({
    		  fields : ['label', 'value'],
    		  data : [["短信通知", "1"],["其他方式", "3"]]
    	  }),
    	  displayField : 'label',
    	  valueField : 'value',
    	  mode : 'local',
    	  editable : false
     },{
	        xtype : 'combo',
	        fieldLabel : '配送员状态',
	        emptyText : '请选择配送员状态',
	        name : 'status',
	        hiddenName : 'status',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["启用", "1"],["禁用", "-1"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        mode : 'local',
	        editable : false
	      }]
  });
  addWindow.add(formPanel);
  addWindow.show();
});

//修改信息
toolbar.regModifyHandler(function(){
  var selections = gridPanel.getSelectionModel().getSelections();
  if(!selections || selections.length <= 0){
    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
    return;
  }
  var id = selections[0].get("id");
  Ext.Ajax.request({
    url : CTA.deliverer.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
    	    items : [{
    	    	xtype : 'hidden',
    	        fieldLabel : '配送员ID',
    	        name : 'id'
    	      },{
    	        fieldLabel : '配送员姓名',
    	        emptyText : '请输入配送员姓名',
    	        name : 'realname',
    	        minLength:1,
    	        maxLength: 10
    	      },{
    	    	  fieldLabel : '配送员电话',
    	    	  emptyText : '请输入配送员电话',
    	    	  name : 'telephone',
    	    	  vtype : 'telephone'
    	      },{
    	    	  xtype : 'combo',
    	    	  fieldLabel : '通知方式',
    	    	  emptyText : '请选择配送员订单通知方式',
    	    	  name : 'send_type',
    	    	  hiddenName : 'send_type',
    	    	  triggerAction : 'all',
    	    	  store : new Ext.data.SimpleStore({
    	    		  fields : ['label', 'value'],
    	    		  data : [["短信通知", "1"],["其他方式", "3"]]
    	    	  }),
    	    	  displayField : 'label',
    	    	  valueField : 'value',
    	    	  mode : 'local',
    	    	  editable : false
    	     },{
    		        xtype : 'combo',
    		        fieldLabel : '配送员性别',
    		        emptyText : '请选择配送员性别',
    		        name : 'gender',
    		        hiddenName : 'gender',
    		        triggerAction : 'all',
    		        store : new Ext.data.SimpleStore({
    		          fields : ['label', 'value'],
    		          data : [["男", "1"],["女", "2"]]
    		        }),
    		        displayField : 'label',
    		        valueField : 'value',
    		        mode : 'local',
    		        editable : false
    		      },{
    			        xtype : 'combo',
    			        fieldLabel : '配送员状态',
    			        emptyText : '请选择配送员状态',
    			        name : 'status',
    			        hiddenName : 'status',
    			        triggerAction : 'all',
    			        store : new Ext.data.SimpleStore({
    			          fields : ['label', 'value'],
    			          data : [["启用", "1"],["禁用", "-1"]]
    			        }),
    			        displayField : 'label',
    			        valueField : 'value',
    			        mode : 'local',
    			        editable : false
    			      }],
    			      data : data
    	  });

      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
    	width : 500,
		height : 320,
		layout : 'border',
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
            	CTA.common.Mask.showMask({target:'updateWindow'});
                  formPanel.commit({
                    url : CTA.deliverer.params.url.modifyUrl
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

//查询
toolbar.regQueryHandler(function(){

	  var formPanel = new CTA.common.SFormPanel({
  	    items : [{
	        fieldLabel : '配送员ID',
	        emptyText : '请输入配送员ID',
	        name : 'id',
	        allowBlank : true
	      },{
	        fieldLabel : '配送员姓名',
	        emptyText : '请输入配送员姓名',
	        name : 'realname',
	        allowBlank : true
	      },{
	    	  fieldLabel : '配送员电话',
	    	  emptyText : '请输入配送员电话',
	    	  name : 'telephone',
	  	      allowBlank : true
	      },{
				xtype : 'combo',
				id : 'department',
				fieldLabel : '所属部门',
				editable : false,
				emptyText : '点击选择所属部门',
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
						url : CTA.deliverer.params.url.listDepartmentsUrl
					}),
					autoLoad : true,
					listeners : {
						load : function (){
							Ext.getCmp('department').setValue(undefined === CTA.common.Constant.queryParams.dep_id ? '' : CTA.common.Constant.queryParams.dep_id);
						}
					}
				})
			},{
		        xtype : 'combo',
		        fieldLabel : '配送员性别',
		        emptyText : '请选择配送员性别',
		        name : 'gender',
		        hiddenName : 'gender',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["男", "1"],["女", "2"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        mode : 'local',
		        editable : false,
		        allowBlank : true
		   },{
			        xtype : 'combo',
			        fieldLabel : '配送员状态',
			        emptyText : '请选择配送员状态',
			        name : 'status',
			        hiddenName : 'status',
			        triggerAction : 'all',
			        store : new Ext.data.SimpleStore({
			          fields : ['label', 'value'],
			          data : [["启用", "1"],["禁用", "-1"]]
			        }),
			        displayField : 'label',
			        valueField : 'value',
			        mode : 'local',
			        editable : false,
			        allowBlank : true
			  }],
		      data : CTA.common.Constant.queryParams
	  });

	var queryHandler = function(){
		var params = formPanel.getForm().getFieldValues() ;
		Ext.apply(CTA.common.Constant.queryParams,params);

		gridPanel.getStore().reload();
		gridPanel.doLayout();
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


//删除
toolbar.regDropHandler(function(){

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
	    		url : CTA.deliverer.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
