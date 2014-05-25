//设置按钮信息
Ext.ns("CTA.button");
CTA.button.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/button/listButtons",
		    dataList:[{
		          index:'id',
		          header:'按钮ID'
		      },{
		          index:'button_name',
		          header:'按钮名称'
		      },{
		        index:'action',
		        header:'按钮动作'
		      },{
		    	  index:'memo',
		    	  header:'按钮描述'
		      },{
		        index:'status',
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
		addUrl : basePath + "/admin/button/addButton",
		detailUrl : basePath + "/admin/button/getButtonDetail",
		modifyUrl : basePath + "/admin/button/modifyButton",
		dropUrl : basePath + "/admin/button/deleteButton",
		listButtonUrl :  basePath + "/admin/button/listButtons_s"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.button.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.button.params.url.addUrl
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
        fieldLabel : '按钮名称',
        emptyText : '请输入按钮名称',
        name : 'button_name',
        minLength:1,
        maxLength: 50
      },{
        fieldLabel : '按钮动作',
        emptyText : '请输入按钮动作',
        name : 'action',
        minLength:1,
        maxLength: 50
      },{
    	  fieldLabel : '按钮描述',
    	  emptyText : '请输入按钮描述',
    	  name : 'memo',
    	  minLength:1,
    	  maxLength: 200
      },{
    	  fieldLabel : '按钮图片',
    	  emptyText : '请输入按钮图片',
    	  name : 'icon',
    	  minLength:1,
    	  maxLength: 200,
    	  allowBlank : true
      },{
			xtype : 'combo',
			fieldLabel : '按钮排序',
			emptyText : '点击设置按钮排序',
			name : 'sort',
			hiddenName : 'sort',
			triggerAction : 'all',
			displayField : 'button_name',
			valueField : 'sort',
			editable : false,
			store : new Ext.data.Store({
				reader : new Ext.data.JsonReader({
					root : 'dataList',
					fields : [ {
						name : 'sort'
					}, {
						name : 'button_name'
					} ]
				}),
				proxy : new Ext.data.HttpProxy({
					url : CTA.button.params.url.listButtonUrl
				})
			})
		},{
	        xtype : 'combo',
	        fieldLabel : '菜单状态',
	        emptyText : '请选择菜单状态',
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

//修改信息
toolbar.regModifyHandler(function(){
  var selections = gridPanel.getSelectionModel().getSelections();
  if(!selections || selections.length <= 0){
    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
    return;
  }
  var id = selections[0].get("id");
  Ext.Ajax.request({
    url : CTA.button.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
    	  items : [{
    		  	xtype : 'hidden',
    	        fieldLabel : '按钮ID',
    	        name : 'id'
    	      },{
    	        fieldLabel : '按钮名称',
    	        emptyText : '请输入按钮名称',
    	        name : 'button_name',
    	        minLength:1,
    	        maxLength: 50
    	      },{
    	        fieldLabel : '按钮动作',
    	        emptyText : '请输入按钮动作',
    	        name : 'action',
    	        minLength:1,
    	        maxLength: 50
    	      },{
    	    	  fieldLabel : '按钮描述',
    	    	  emptyText : '请输入按钮描述',
    	    	  name : 'memo',
    	    	  minLength:1,
    	    	  maxLength: 200
    	      },{
    	    	  fieldLabel : '按钮图片',
    	    	  emptyText : '请输入按钮图片',
    	    	  name : 'icon',
    	    	  minLength:1,
    	    	  maxLength: 200,
    	    	  allowBlank : true
    	      },{
    				xtype : 'combo',
    				fieldLabel : '按钮排序',
    				emptyText : '点击设置按钮排序',
    				name : 'sort',
    				hiddenName : 'sort',
    				triggerAction : 'all',
    				displayField : 'button_name',
    				valueField : 'sort',
    				editable : false,
    				store : new Ext.data.Store({
    					reader : new Ext.data.JsonReader({
    						root : 'dataList',
    						fields : [ {
    							name : 'sort'
    						}, {
    							name : 'button_name'
    						} ]
    					}),
    					proxy : new Ext.data.HttpProxy({
    						url : CTA.button.params.url.listButtonUrl
    					})
    				})
    			},{
    		        xtype : 'combo',
    		        fieldLabel : '菜单状态',
    		        emptyText : '请选择菜单状态',
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
                    url : CTA.button.params.url.modifyUrl
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
	    		url : CTA.button.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
