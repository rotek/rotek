//设置系统任务信息
Ext.ns("CTA.task");
CTA.task.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/task/listTasks",
		    dataList:[{
		          index:'id',
		          header:'ID'
		      },{
		          index:'target_id',
		          header:'目标ID'
		      },{
		    	  index:'time',
		    	  header:'任务执行时间',
		    	  renderer : function(value){
						return new Date(parseFloat(value)).format("Y-m-d H:i:s");
					}
		      },{
		    	  index:'type',
		    	  header:'任务类型',
		    	  renderer:function(value){
		    		  if(1==value){
		    			  return "<span style='color:red'>预定订单</span>";
		    		  }else if(2 == value){
		    			  return "店铺打烊";
		    		  }else if(3 == value){
		    			  return "<span style='color:green'>店铺营业</span>";
		    		  }
		    	  }
		      }]
	},
	url : {
		dropUrl : basePath + "/admin/task/deleteTask"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.task.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);

//查询
toolbar.regQueryHandler(function(){

	  var formPanel = new CTA.common.SFormPanel({
		  items : [{
			  	xtype : 'numberfield',
		        fieldLabel : '任务目标ID',
		        emptyText : '请输入任务目标ID',
		        name : 'target_id',
		        allowBlank : true
	      },{
		        xtype : 'combo',
		        fieldLabel : '任务类型',
		        emptyText : '请选择任务类型',
		        name : 'type',
		        hiddenName : 'type',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["订单预定任务", "1"],["店铺自动打烊任务", "2"],["店铺自动营业任务", "3"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        mode : 'local',
		        editable : false,
		        allowBlank : true
			},{
				xtype : 'datefield',
				format:'Y-m-d H:i:s',
		        fieldLabel : '起始时间',
		        emptyText : '请输入任务起始时间',
		        vtype : 'date_time',
		        name : 'start_time',
		        allowBlank : true
			},{
				xtype : 'datefield',
				format:'Y-m-d H:i:s',
				fieldLabel : '结束时间',
				emptyText : '请输入任务结束时间',
				vtype : 'date_time',
				name : 'end_time',
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

	Ext.Msg.confirm("警告","系统任务数据删除后将无法恢复，确定继续删除这"+ids.length+" 条数据吗？",function(button){
	    if('yes' == button){
	    	CTA.common.Mask.showMask();
	    	CTA.common.Ajax.request({
	    		url : CTA.task.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
