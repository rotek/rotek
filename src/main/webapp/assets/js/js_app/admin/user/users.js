//设置用户信息
Ext.ns("CTA.user");
CTA.user.params = {
	//全局gridpanel的参数
	gridParam : {
			url :   basePath + "/admin/user/listUsers",
			//id,nick_name,telephone,order_count,gold,reg_time,enabled
		    dataList:[{
		          index:'id',
		          header:'用户ID'
		      },{
		          index:'nick_name',
		          header:'用户名'
		      },{
		        index:'telephone',
		        header:'用户收餐电话'
		      },{
		    	  index:'order_count',
		    	  header:'用户订单总数'
		      },{
		    	  index:'gold',
		    	  header:'用户积分'
		      },{
		    	  index:'telephone',
		    	  header:'用户收餐电话'
		      },{
		    	  index:'reg_time',
		    	  header:'用户注册时间',
		    	  renderer : function(value){
						return new Date(parseFloat(value)).format("Y-m-d");
					}
		      },{
		        index:'enabled',
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
		addUrl : basePath + "/admin/user/addUser",
		detailUrl : basePath + "/admin/user/getUserDetail",
		modifyUrl : basePath + "/admin/user/modifyUser",
		dropUrl : basePath + "/admin/user/deleteUser",
		authorityUrl : basePath + "/admin/role/listAuthority"

	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.user.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.user.params.url.addUrl
          });
        }
    };
	  //定义添加的窗口
	  var addWindow = new CTA.common.SaveWindow({
		id : 'addWindow',
	    width : 550,
	    height : 350,
	    layout : 'fit',
	    handler : saveHandler
	  });

  //定义添加窗口中的form
  var formPanel = new CTA.common.SFormPanel({
    items : [{
        fieldLabel : '用户名称',
        emptyText : '请输入用户名称',
        name : 'nick_name',
        minLength: 3,
        maxLength: 20
      },{
    	  fieldLabel : '用户密码',
    	  emptyText : '请输入用户密码',
    	  name : 'password',
    	  minLength: 3,
    	  maxLength: 30
      },{
      	fieldLabel : '用户电话',
    	emptyText : '请输入用户电话',
    	name : 'telephone',
    	vtype : 'telephone',
    	maxLength : 11
      },{
    	  xtype : 'numberfield',
    	  fieldLabel : '用户积分',
    	  emptyText : '请输入用户积分',
    	  name : 'gold',
    	  maxLength: 10
	    },{
          xtype : 'combo',
          fieldLabel : '用户状态',
          emptyText : '请选择用户状态',
          name : 'enabled',
          hiddenName : 'enabled',
          triggerAction : 'all',
          store : new Ext.data.SimpleStore({
            fields : ['label', 'value'],
            data : [["启用", "1"],["禁用", "-1"]]
          }),
          displayField : 'label',
          valueField : 'value',
          mode : 'local',
          editable : false
        },{
          fieldLabel : '真实姓名',
          emptyText : '请输入真实姓名 (可为空)',
          name : 'real_name',
          allowBlank : true,
          maxLength: 10
        },{
    	  xtype : 'numberfield',
    	  fieldLabel : '用户QQ',
    	  emptyText : '用户QQ (可为空)',
    	  name : 'qq',
    	  allowBlank : true,
    	  maxLength: 30
	    },{
	  	  fieldLabel : '用户Email',
		  emptyText : '请输入用户Email (可为空)',
		  allowBlank : true,
		  name : 'email',
		  vtype : 'email'
	    },{
	        xtype : 'combo',
	        fieldLabel : '用户性别',
	        emptyText : '请选择用户性别 (可为空)',
	        name : 'gender',
	        hiddenName : 'gender',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["女", "1"],["男", "2"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        mode : 'local',
	        allowBlank : true,
	        editable : false
      }]
  });
  addWindow.add(formPanel);
  addWindow.show();
});

//修改角色信息
toolbar.regModifyHandler(function(){
  var selections = gridPanel.getSelectionModel().getSelections();
  if(!selections || selections.length <= 0){
    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
    return;
  }
  var roleId = selections[0].get("id");
  Ext.Ajax.request({
    url : CTA.user.params.url.detailUrl,
    params : {id : roleId},
    success : function(response) {
      var user = Ext.util.JSON.decode(response.responseText).user;
      var user_detail = Ext.util.JSON.decode(response.responseText).user_detail;
      var reg_time = Ext.util.JSON.decode(response.responseText).reg_time;
      var data = {};
      if(user_detail){
    	  user_detail.detail_id = user_detail.id;
    	  data = Ext.apply(user_detail,user);
      }else {
    	  data = user;
      }
      data.reg_time = reg_time;

      var formPanel = new CTA.common.SFormPanel({
    	    items : [{
    	    	xtype : 'hidden',
    	    	name : 'id'
    	    },{
    	    	xtype : 'hidden',
    	    	name : 'detail_id'
    	    },{
    	    	xtype : 'hidden',
    	    	name : 'reg_time'
    	    },{
    	    	xtype : 'hidden',
    	    	name : 'un_comment'
    	    },{
    	    	xtype : 'hidden',
    	    	name : 'order_count'
    	    },{
    	        fieldLabel : '用户名称',
    	        emptyText : '请输入用户名称',
    	        name : 'nick_name',
    	        minLength: 3,
    	        maxLength: 20
    	      },{
    	    	  fieldLabel : '用户密码',
    	    	  emptyText : '请输入用户密码',
    	    	  name : 'password',
    	    	  minLength: 3,
    	    	  maxLength: 30
    	      },{
    	      	fieldLabel : '用户电话',
    	    	emptyText : '请输入用户电话',
    	    	name : 'telephone',
    	    	vtype : 'telephone',
    	    	maxLength : 11
    	      },{
    	    	  xtype : 'numberfield',
    	    	  fieldLabel : '用户积分',
    	    	  emptyText : '请输入用户积分',
    	    	  name : 'gold',
    	    	  maxLength: 10
    		    },{
    	          xtype : 'combo',
    	          fieldLabel : '用户状态',
    	          emptyText : '请选择用户状态',
    	          name : 'enabled',
    	          hiddenName : 'enabled',
    	          triggerAction : 'all',
    	          store : new Ext.data.SimpleStore({
    	            fields : ['label', 'value'],
    	            data : [["启用", "1"],["禁用", "-1"]]
    	          }),
    	          displayField : 'label',
    	          valueField : 'value',
    	          mode : 'local',
    	          editable : false
    	        },{
    	          fieldLabel : '真实姓名',
    	          emptyText : '请输入真实姓名 (可为空)',
    	          name : 'real_name',
    	          allowBlank : true,
    	          maxLength: 10
    	        },{
    	    	  xtype : 'numberfield',
    	    	  fieldLabel : '用户QQ',
    	    	  emptyText : '用户QQ (可为空)',
    	    	  name : 'qq',
    	    	  allowBlank : true,
    	    	  maxLength: 30
    		    },{
    		  	  fieldLabel : '用户Email',
    			  emptyText : '请输入用户Email (可为空)',
    			  allowBlank : true,
    			  name : 'email',
    			  vtype : 'email'
    		    },{
    		        xtype : 'combo',
    		        fieldLabel : '用户性别',
    		        emptyText : '请选择用户性别 (可为空)',
    		        name : 'gender',
    		        hiddenName : 'gender',
    		        triggerAction : 'all',
    		        store : new Ext.data.SimpleStore({
    		          fields : ['label', 'value'],
    		          data : [["女", "1"],["男", "2"]]
    		        }),
    		        displayField : 'label',
    		        valueField : 'value',
    		        mode : 'local',
    		        allowBlank : true,
    		        editable : false
    	      }],
    	      data : data
    	  });

      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
        width : 550,
        height : 400,
        layout : 'border',
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
            	CTA.common.Mask.showMask({target:'updateWindow'});

				formPanel.commit({
		            url : CTA.user.params.url.modifyUrl
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
	        fieldLabel : '用户ID',
	        emptyText : '请输入用户ID',
	        name : 'id',
	        allowBlank : true,
	        maxLength: 20
	      },{
  	        fieldLabel : '用户名称',
  	        emptyText : '请输入用户名称',
  	        name : 'nick_name',
  	        allowBlank : true,
  	        maxLength: 20
	  	 },{
	      	fieldLabel : '用户电话',
	    	emptyText : '请输入用户电话',
	    	name : 'telephone',
	    	vtype : 'telephone',
	    	allowBlank : true,
	    	maxLength : 11
	      },{
	    	  xtype : 'numberfield',
	    	  fieldLabel : '用户积分',
	    	  emptyText : '将查询大于输入积分的用户',
	    	  name : 'gold',
	    	  allowBlank : true,
	    	  maxLength: 10
		    },{
	          xtype : 'combo',
	          fieldLabel : '用户状态',
	          emptyText : '请选择用户状态',
	          name : 'enabled',
	          hiddenName : 'enabled',
	          triggerAction : 'all',
	          store : new Ext.data.SimpleStore({
	            fields : ['label', 'value'],
	            data : [["启用", "1"],["禁用", "-1"]]
	          }),
	          displayField : 'label',
	          valueField : 'value',
	          mode : 'local',
	          allowBlank : true,
	          editable : false
	        },{
		    	  xtype : 'datefield',
		    	  fieldLabel : '注册起始时间',
		    	  emptyText : '请选择注册起始时间',
		    	  name : 'start_time',
		    	  format:'Y-m-d',
		    	  editable : false,
		    	  allowBlank : true
		      },{
		    	  xtype : 'datefield',
		    	  fieldLabel : '注册结束时间',
		    	  emptyText : '请选择注册结束时间',
		    	  name : 'end_time',
		    	  format:'Y-m-d',
		    	  editable : false,
		    	  allowBlank : true
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
	    		url : CTA.user.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
