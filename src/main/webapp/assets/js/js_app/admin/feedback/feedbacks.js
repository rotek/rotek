//设置反馈信息
Ext.ns("CTA.feedback");
CTA.feedback.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/feedback/listFeedbacks",
		    dataList:[{
		          index:'id',
		          header:'反馈ID'
		      },{
		          index:'user_name',
		          header:'反馈用户'
		      },{
		    	  index:'type',
		    	  header:'反馈类型',
		    	  renderer : function(value){
		    		  return "普通反馈";
		    	  }
		      },{
		    	  index:'user_name',
		    	  header:'反馈用户'
		      },{
		    	  index:'time',
		    	  header:'反馈时间',
		    	  renderer : function(value){
						return new Date(parseFloat(value)).format("Y-m-d H:i:s");
					}
		      },{
		        index:'status',
		        header:'反馈状态',
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
		detailUrl : basePath + "/admin/feedback/getFeedbackDetail",
		replyFeedback : basePath + "/admin/feedback/replyFeedback",
		dropUrl : basePath + "/admin/feedback/deleteFeedback",
		listButtonUrl :  basePath + "/admin/button/listButtons_s"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.feedback.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);

//回复用户反馈
toolbar.regReplyHandler(function(){

	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }
	  var id = selections[0].get("id");
	  Ext.Ajax.request({
	    url : CTA.feedback.params.url.detailUrl,
	    params : {id : id},
	    success : function(response) {
	      var data = Ext.util.JSON.decode(response.responseText).data;
	      var time = Ext.util.JSON.decode(response.responseText).time;
	      if(time){
	    	  data.time = time;
	      }

	      var formPanel = new CTA.common.SFormPanel({
			  	labelAlign: 'left',
		        labelWidth: 70, // label settings here cascade unless overridden
		        frame:true,
			    bodyStyle : (data.replyList && data.replyList.length>3) ? 'overflow-x:hidden; overflow-y:scroll;padding:5px 25px 10px 5px' : 'padding:5px 25px 10px 5px',
			  items : [{
				  	xtype : 'hidden',
				  	name : 'id'
			  	},{
			        fieldLabel : '用户ID',
			        emptyText : '请输入用户ID',
			        name : 'user_id',
			        allowBlank : true
			      },{
			        fieldLabel : '用户名称',
			        emptyText : '请输入用户名称',
			        name : 'user_name',
			        allowBlank : true
			      },{
				        xtype : 'combo',
				        fieldLabel : '反馈类型',
				        emptyText : '请选择反馈类型',
				        name : 'type',
				        triggerAction : 'all',
				        store : new Ext.data.SimpleStore({
				          fields : ['label', 'value'],
				          data : [["普通反馈", "1"]]
				        }),
				        displayField : 'label',
				        valueField : 'value',
				        hiddenName : 'type',
				        mode : 'local',
				        editable : false,
				        allowBlank : true
			      },{
				        xtype : 'combo',
				        fieldLabel : '反馈状态',
				        emptyText : '请选择反馈状态',
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
				        editable : false,
				        allowBlank : true
				  },{
			    	  fieldLabel : '反馈时间',
			    	  emptyText : '请选择反馈时间',
			    	  name : 'time',
			    	  editable : false,
			    	  allowBlank : true
			      },{
			          xtype:"displayfield",
			          name:"content",
			          fieldLabel:"反馈内容",
			          style : 'margin-top:10px;margin-bottom:10px'
			        }],
			        data : data
			  });

	      if(data.replyList && data.replyList.length >0){
	    	  Ext.each(data.replyList,function(reply,index){
	    		  var reply_detail = reply.replier_name + "："+reply.content;
	    		  formPanel.add({
		    			fieldLabel : '回复详情',
				        value : reply_detail
	    		  });
	    	  });
	      }

	      formPanel.add({
	    	  xtype : 'htmleditor',
	    	  fieldLabel : '回复内容',
	    	  emptyText : '请输入回复内容',
	    	  name : 'reply_content'
	      });

			var replyHandler = function(){
				 //检查表单是否填写好
	            if(formPanel.getForm().isValid()){
	            	CTA.common.Mask.showMask({target:'updateWindow'});
	                  formPanel.commit({
	                    url : CTA.feedback.params.url.replyFeedback
	                  });
	            }
			};
			// 回复窗口
			var replyWindow = new CTA.common.UpdateWindow({
				id : 'updateWindow',
				title : '回复用户反馈信息',
				width : '80%',
				height : 500,
				layout : 'fit',
				closeAction: 'hide',
				items : [formPanel],
				handler : replyHandler
			});

			replyWindow.show();
    }
  });

});


//查看详情
toolbar.regViewDetailHandler(function(){

	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }
	  var id = selections[0].get("id");
	  Ext.Ajax.request({
	    url : CTA.feedback.params.url.detailUrl,
	    params : {id : id},
	    success : function(response) {
	      var data = Ext.util.JSON.decode(response.responseText).data;
	      var time = Ext.util.JSON.decode(response.responseText).time;
	      if(time){
	    	  data.time = time;
	      }

	      var formPanel = new CTA.common.SFormPanel({
			  	labelAlign: 'left',
		        labelWidth: 70, // label settings here cascade unless overridden
		        frame:true,
			    bodyStyle : (data.replyList && data.replyList.length>7) ? 'overflow-x:hidden; overflow-y:scroll;padding:5px 25px 10px 5px' : 'padding:5px 25px 10px 5px',
			  items : [{
				  	xtype : 'hidden',
				  	name : 'id'
			  	},{
			        fieldLabel : '用户ID',
			        emptyText : '请输入用户ID',
			        name : 'user_id',
			        allowBlank : true
			      },{
			        fieldLabel : '用户名称',
			        emptyText : '请输入用户名称',
			        name : 'user_name',
			        allowBlank : true
			      },{
				        xtype : 'combo',
				        fieldLabel : '反馈类型',
				        emptyText : '请选择反馈类型',
				        name : 'type',
				        triggerAction : 'all',
				        store : new Ext.data.SimpleStore({
				          fields : ['label', 'value'],
				          data : [["普通反馈", "1"]]
				        }),
				        displayField : 'label',
				        valueField : 'value',
				        hiddenName : 'type',
				        mode : 'local',
				        editable : false,
				        allowBlank : true
			      },{
				        xtype : 'combo',
				        fieldLabel : '反馈状态',
				        emptyText : '请选择反馈状态',
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
				        editable : false,
				        allowBlank : true
				  },{
			    	  fieldLabel : '反馈时间',
			    	  emptyText : '请选择反馈时间',
			    	  name : 'time',
			    	  editable : false,
			    	  allowBlank : true
			      },{
			          xtype:"displayfield",
			          name:"content",
			          fieldLabel:"反馈内容",
			          style : 'margin-top:10px;margin-bottom:10px'
			        }],
			        data : data
			  });

	      if(data.replyList && data.replyList.length >0){
	    	  Ext.each(data.replyList,function(reply,index){
	    		  var reply_detail = reply.replier_name + "："+reply.content;
	    		  formPanel.add({
		    			fieldLabel : '回复详情',
				        value : reply_detail
	    		  });
	    	  });
	      }

			// 回复窗口
			var replyWindow = new CTA.common.Window({
				id : 'updateWindow',
				title : '用户反馈信息详情',
				width : '80%',
				height : 500,
				layout : 'fit',
				closeAction: 'hide',
				items : [formPanel]
			});
			replyWindow.show();
	    }
});
});

//查询
toolbar.regQueryHandler(function(){

	  var formPanel = new CTA.common.SFormPanel({
		  items : [{
		        fieldLabel : '反馈ID',
		        emptyText : '请输入反馈ID',
		        name : 'id',
		        allowBlank : true
		      },{
		        fieldLabel : '用户ID',
		        emptyText : '请输入用户ID',
		        name : 'user_id',
		        allowBlank : true
		      },{
		        fieldLabel : '用户名称',
		        emptyText : '请输入用户名称',
		        name : 'user_name',
		        allowBlank : true
		      },{
			        xtype : 'combo',
			        fieldLabel : '反馈类型',
			        emptyText : '请选择反馈类型',
			        name : 'type',
			        triggerAction : 'all',
			        store : new Ext.data.SimpleStore({
			          fields : ['label', 'value'],
			          data : [["普通反馈", "1"]]
			        }),
			        displayField : 'label',
			        valueField : 'value',
			        hiddenName : 'type',
			        mode : 'local',
			        editable : false,
			        allowBlank : true
		      },{
			        xtype : 'combo',
			        fieldLabel : '反馈状态',
			        emptyText : '请选择反馈状态',
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
			        editable : false,
			        allowBlank : true
			  },{
		    	  xtype : 'datefield',
		    	  fieldLabel : '起始时间',
		    	  emptyText : '请选择反馈起始时间',
		    	  name : 'start_time',
		    	  format:'Y-m-d',
		    	  editable : false,
		    	  allowBlank : true
		      },{
		    	  xtype : 'datefield',
		    	  fieldLabel : '反馈结束时间',
		    	  emptyText : '请选择反馈结束时间',
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
		height : 330,
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
	    		url : CTA.feedback.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
