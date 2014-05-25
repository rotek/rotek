//设置礼品中心信息
Ext.ns("CTA.gift");
CTA.gift.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/gift/listGifts",
		    dataList:[{
		          index:'id',
		          header:'礼品ID'
		      },{
		          index:'name',
		          header:'礼品名称'
		      },{
		    	  index:'points',
		    	  header:'礼品积分'
		      },{
		        index:'pic',
		        header:'礼品图片'
		      },{
		        index:'status',
		        header:'礼品状态',
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
		addUrl : basePath + "/admin/gift/addGift",
		detailUrl : basePath + "/admin/gift/getGiftDetail",
		modifyUrl : basePath + "/admin/gift/modifyGift",
		dropUrl : basePath + "/admin/gift/deleteGift"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.gift.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);

//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.gift.params.url.addUrl
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
    fileUpload : true,
    items : [{
        fieldLabel : '礼品名称',
        emptyText : '请输入菜单名称',
        name : 'name',
        minLength:1,
        maxLength: 50
      },{
    	 xtype : 'numberfield',
        fieldLabel : '礼品积分',
        emptyText : '请输入兑换所需积分',
        name : 'points',
        minLength:1,
        maxLength: 10
      },{
	        xtype : 'combo',
	        fieldLabel : '礼品状态',
	        emptyText : '请选择礼品状态',
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
	      	fieldLabel : '礼品图片',
	    	name : 'pic',
			text : "点击上传菜品图片",
			inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
			blankText : '请上传礼品图片'
	  },{
    	  xtype : 'htmleditor',
    	  fieldLabel : '礼品描述',
    	  emptyText : '请输入礼品描述',
    	  name : 'descr'
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
    url : CTA.gift.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
    	    fileUpload : true,
    	    items : [{
    	    	xtype : 'hidden',
    	    	name : 'id'
    	    },{
    	        fieldLabel : '礼品名称',
    	        emptyText : '请输入礼品名称',
    	        name : 'name',
    	        minLength:1,
    	        maxLength: 50
    	      },{
    	    	 xtype : 'numberfield',
    	        fieldLabel : '礼品积分',
    	        emptyText : '请输入兑换所需积分',
    	        name : 'points',
    	        minLength:1,
    	        maxLength: 10
    	      },{
    		        xtype : 'combo',
    		        fieldLabel : '礼品状态',
    		        emptyText : '请选择礼品状态',
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
    		      	fieldLabel : '礼品图片',
    		    	name : 'pic',
    		    	emptyText : '请输入兑换所需积分',
    		    	readOnly : true
    		  },{
    			  fieldLabel : '修改图片',
    			  name : 'pic_modify',
    			  text : "点击上传菜品图片",
    			  inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
    			  allowBlank :true,
    			  blankText : '请上传礼品图片'
    		  },{
    	    	  xtype : 'htmleditor',
    	    	  fieldLabel : '礼品描述',
    	    	  emptyText : '请输入礼品描述',
    	    	  name : 'descr'
    	      }],
    	      data : data
    	  });

      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
    	width : '80%',
		height : 500,
		layout : 'border',
        items : [formPanel],
        handler : function(){
            //检查表单是否填写好
            if(formPanel.getForm().isValid()){
              CTA.common.Mask.showMask({target:'updateWindow'});
              formPanel.commit({
                url : CTA.gift.params.url.modifyUrl
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
			  	xtype : 'numberfield',
		        fieldLabel : '礼品ID',
		        emptyText : '请输入礼品ID',
		        name : 'id',
		        allowBlank : true,
		        minLength:1,
		        maxLength: 50
		      },{
		        fieldLabel : '礼品名称',
		        emptyText : '请输入礼品名称',
		        name : 'name',
		        allowBlank : true,
		        minLength:1,
		        maxLength: 50
		      },{
		    	 xtype : 'numberfield',
		        fieldLabel : '礼品积分',
		        emptyText : '请输入兑换所需积分',
		        name : 'points',
		        allowBlank : true,
		        minLength:1,
		        maxLength: 10
		      },{
		        xtype : 'combo',
		        fieldLabel : '礼品状态',
		        emptyText : '请选择礼品状态',
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
	    		url : CTA.gift.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
