//SEO信息
Ext.ns("CTA.seo");
CTA.seo.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/seo/listSEOs",
		    dataList:[{
		          index:'id',
		          header:'ID'
		      },{
		          index:'title',
		          header:'网页标题'
		      },{
		        index:'keywords',
		        header:'网页关键词'
		      },{
		    	  index:'description',
		    	  header:'网页描述'
		      },{
		        index:'type',
		        header:'SEO类型',
		        renderer:function(value){
		          if(1==value){
		            return "<span style='color:red;'>首页</span>";
		          }else if(2 == value){
		            return "<span style='color:green;'>店铺列表</span>";
		          }else if(3 == value){
		        	  return "<span style='color:green;'>店铺详情</span>";
		          }else if(4 == value){
		        	  return "新闻详情";
		          }else if(5 == value){
		        	  return "关于我们";
		          }else if(6 == value){
		        	  return "帮助中心";
		          }else if(7 == value){
		        	  return "诚聘英才";
		          }else if(8 == value){
		        	  return "店铺加盟";
		          }else if(9 == value){
		        	  return "服务协议";
		          }else if(10 == value){
		        	  return "美食点评";
		          }else if(11 == value){
		        	  return "礼品中心";
		          }else if(12 == value){
		        	  return "登录";
		          }else if(13 == value){
		        	  return "个人中心";
		          }else if(14 == value){
		        	  return "订单确认";
		          }else if(15 == value){
		        	  return "注册";
		          }else {
		        	  return "未知类型";
		          }
		        }
		      }]
	},
	url : {
		addUrl : basePath + "/admin/seo/addSEO",
		detailUrl : basePath + "/admin/seo/getSEODetail",
		modifyUrl : basePath + "/admin/seo/modifySEO",
		dropUrl : basePath + "/admin/seo/deleteSEO"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.seo.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.seo.params.url.addUrl
          });
        }
    };
	  //定义添加的窗口
	  var addWindow = new CTA.common.SaveWindow({
		id : 'addWindow',
	    width : 800,
	    height : 480,
	    layout : 'fit',
	    handler : saveHandler
	  });

  //定义添加窗口中的form
  var formPanel = new CTA.common.SFormPanel({
    items : [{
	    xtype : 'combo',
	    fieldLabel : 'SEO类型',
	    emptyText : '请选择SEO类型',
	    name : 'type',
	    hiddenName : 'type',
	    triggerAction : 'all',
	    store : new Ext.data.SimpleStore({
	      fields : ['label', 'value'],
	      data : [["首页", "1"],
	              ["店铺列表", "2"],
	              ["店铺详情", "3"],
	              ["新闻详情", "4"],
	              ["关于我们", "5"],
	              ["帮助中心", "6"],
	              ["诚聘英才", "7"],
	              ["店铺加盟", "8"],
	              ["服务协议", "9"],
	              ["美食点评", "10"],
	              ["礼品中心", "11"],
	              ["登录", "12"],
	              ["个人中心", "13"],
	              ["确认订单", "14"],
	              ["注册", "15"]]
	    }),
	    displayField : 'label',
	    valueField : 'value',
	    mode : 'local',
	    style : 'margin-bottom:10px',
	    editable : false
	 },{
		xtype : 'htmleditor',
        fieldLabel : '网页title',
        emptyText : '请输入网页title(请先理解规则以后再进行设置)',
        name : 'title',
        height : 100,
        minLength:1,
        maxLength: 100
      },{
        xtype : 'htmleditor',
        fieldLabel : '网页关键词',
        emptyText : '请输入网页关键词(请先理解规则以后再进行设置)',
        name : 'keywords',
        height : 100,
        minLength:1,
        maxLength: 200
      },{
    	  xtype : 'htmleditor',
    	  fieldLabel : '网页描述',
    	  emptyText : '请输入网页描述(请先理解规则以后再进行设置)',
    	  name : 'description',
    	  height : 100,
    	  minLength:1,
    	  maxLength: 200
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
  var id = selections[0].get("id");
  Ext.Ajax.request({
    url : CTA.seo.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
    	    items : [{
    	        xtype : 'hidden',
    	        name : 'id'
    	    },{
    		    xtype : 'combo',
    		    fieldLabel : 'SEO类型',
    		    emptyText : '请选择SEO类型',
    		    name : 'type',
    		    hiddenName : 'type',
    		    triggerAction : 'all',
    		    store : new Ext.data.SimpleStore({
    		      fields : ['label', 'value'],
    		      data : [["首页", "1"],
    		              ["店铺列表", "2"],
    		              ["店铺详情", "3"],
    		              ["新闻详情", "4"],
    		              ["关于我们", "5"],
    		              ["帮助中心", "6"],
    		              ["诚聘英才", "7"],
    		              ["店铺加盟", "8"],
    		              ["服务协议", "9"],
    		              ["美食点评", "10"],
    		              ["礼品中心", "11"],
    		              ["登录", "12"],
    		              ["个人中心", "13"],
    		              ["确认订单", "14"],
    		              ["注册", "15"]]
    		    }),
    		    displayField : 'label',
    		    valueField : 'value',
    		    mode : 'local',
    		    style : 'margin-bottom:10px',
    		    editable : false
    		 },{
    			xtype : 'htmleditor',
    	        fieldLabel : '网页title',
    	        emptyText : '请输入网页title(请先理解规则以后再进行设置)',
    	        name : 'title',
    	        height : 100,
    	        minLength:1,
    	        maxLength: 100
    	      },{
    	        xtype : 'htmleditor',
    	        fieldLabel : '网页关键词',
    	        emptyText : '请输入网页关键词(请先理解规则以后再进行设置)',
    	        name : 'keywords',
    	        height : 100,
    	        minLength:1,
    	        maxLength: 200
    	      },{
    	    	  xtype : 'htmleditor',
    	    	  fieldLabel : '网页描述',
    	    	  emptyText : '请输入网页描述(请先理解规则以后再进行设置)',
    	    	  name : 'description',
    	    	  height : 100,
    	    	  minLength:1,
    	    	  maxLength: 200
    		  }],
    		  data : data
    });

      //Ext.getCmp('manager_role').setValue(data.role_name);
      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
    	width : 800,
	    height : 480,
	    layout : 'fit',
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
              CTA.common.Mask.showMask({target:'updateWindow'});
              formPanel.commit({
                url : CTA.seo.params.url.modifyUrl
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
		        fieldLabel : 'SEOID',
		        emptyText : '请输入SEOID',
		        name : 'id'
		  },{
		    xtype : 'combo',
		    fieldLabel : 'SEO类型',
		    emptyText : '请选择SEO类型',
		    name : 'type',
		    hiddenName : 'type',
		    triggerAction : 'all',
		    store : new Ext.data.SimpleStore({
		      fields : ['label', 'value'],
		      data : [["首页", "1"],
		              ["店铺列表", "2"],
		              ["店铺详情", "3"],
		              ["新闻详情", "4"],
		              ["关于我们", "5"],
		              ["帮助中心", "6"],
		              ["诚聘英才", "7"],
		              ["店铺加盟", "8"],
		              ["服务协议", "9"],
		              ["美食点评", "10"],
		              ["礼品中心", "11"],
		              ["登录", "12"],
		              ["个人中心", "13"],
		              ["确认订单", "14"],
		              ["注册", "15"]]
		    }),
		    displayField : 'label',
		    valueField : 'value',
		    mode : 'local',
		    style : 'margin-bottom:10px',
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
		height : 250,
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
	    		url : CTA.seo.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
