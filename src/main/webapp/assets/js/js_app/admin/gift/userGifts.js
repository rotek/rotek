//设置用户已经兑换的礼品信息
Ext.ns("CTA.userGift");
CTA.userGift.params = {
	//全局gridpanel的参数
	gridParam : {
			url :   basePath + "/admin/gift/listUserGifts",
			//id,nick_name,telephone,order_count,gold,reg_time,enabled
		    dataList:[{
		          index:'exchange_id',
		          header:'兑换单号'
		      },{
		          index:'gift_name',
		          header:'兑换礼品'
		      },{
		        index:'user_name',
		        header:'兑换用户'
		      },{
		    	  index:'exchange_time',
		    	  header:'兑换时间',
		    	  renderer : function(value){
						return new Date(parseFloat(value)).format("Y-m-d");
		    	  }
		      },{
		        index:'exchange_status',
		        header:'兑换状态',
		        renderer:function(value){
		          if(1==value){
		            return "<span style='color:green;'>兑换中</span>";
		          }else if(2 == value){
		            return "兑换完成";
		          }else if(-1 == value){
		        	  return "<span style='color:red;'>兑换作废</span>";;
		          }else {
		        	  return "未知状态";
		          }
		        }
		      }]
	},
	url : {
		detailUrl : basePath + "/admin/gift/getUserGiftDetail",
		modifyStatusUrl : basePath + "/admin/gift/modifyUserGiftStatus",
		dropUrl : basePath + "/admin/gift/deleteUserGift"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.userGift.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);

//查询
toolbar.regQueryHandler(function(){

	  var formPanel = new CTA.common.SFormPanel({
  	    items : [{
  	    	xtype : 'numberfield',
	        fieldLabel : '兑换单号',
	        emptyText : '请输入兑换单号',
	        name : 'exchange_id',
	        allowBlank : true,
	        maxLength: 20
	      },{
  	        fieldLabel : '兑换用户名称',
  	        emptyText : '请输入兑换用户名称',
  	        name : 'user_name',
  	        allowBlank : true,
  	        maxLength: 20
	  	 },{
	    	  fieldLabel : '兑换礼品名称',
	    	  emptyText : '请输入兑换礼品名称',
	    	  name : 'gift_name',
	    	  allowBlank : true,
	    	  maxLength: 20
		    },{
	          xtype : 'combo',
	          fieldLabel : '兑换状态',
	          emptyText : '请选择兑换状态',
	          name : 'exchange_status',
	          hiddenName : 'exchange_status',
	          triggerAction : 'all',
	          store : new Ext.data.SimpleStore({
	            fields : ['label', 'value'],
	            data : [["兑换中", "1"],["兑换完成", "2"]]
	          }),
	          displayField : 'label',
	          valueField : 'value',
	          mode : 'local',
	          allowBlank : true,
	          editable : false
	        },{
		    	  xtype : 'datefield',
		    	  fieldLabel : '兑换起始时间',
		    	  emptyText : '请选择兑换起始时间',
		    	  name : 'start_time',
		    	  format:'Y-m-d',
		    	  editable : false,
		    	  allowBlank : true
		      },{
		    	  xtype : 'datefield',
		    	  fieldLabel : '兑换结束时间',
		    	  emptyText : '请选择兑换结束时间',
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


//修改兑换订单状态
toolbar.regSetStatusHandler(function(){

	var selections = gridPanel.getSelectionModel().getSelections();
	if(!selections || selections.length <= 0){
		Ext.Msg.alert('提示', '请选择您要操作的数据!');
		return;
	}
	var ids = [];
	Ext.each(selections,function(item){
		ids.push(item.get("exchange_id"));
	});

	var formPanel = new CTA.common.SFormPanel({
		  items : [{
			        xtype : 'combo',
			        fieldLabel : '兑换状态',
			        emptyText : '请选择兑换状态',
			        name : 'exchange_status',
			        triggerAction : 'all',
			        store : new Ext.data.SimpleStore({
			          fields : ['label', 'value'],
			          data : [["兑换中", "1"],["兑换完成", "2"]]
			        }),
			        displayField : 'label',
			        valueField : 'value',
			        hiddenName : 'exchange_status',
			        mode : 'local',
			        editable : false,
			        allowBlank : false
			      }]
	  });

	var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
    	title : '修改 '+ids.length+" 条数据状态",
    	width : 600,
		height : 200,
		layout : 'border',
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
            	CTA.common.Mask.showMask({target:'updateWindow'});

            	var params = formPanel.getForm().getValues();
            	params.ids = ids.join(",");

    	    	CTA.common.Ajax.request({
    	    		url : CTA.userGift.params.url.modifyStatusUrl,
    	    		params : params
    	    	});
            }
        }
      });

	updateWindow.show();
});


//查看详情
toolbar.regViewDetailHandler(function(){
	  var selections = gridPanel.getSelectionModel().getSelections();
	  if(!selections || selections.length <= 0){
	    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
	    return;
	  }

	  var id = selections[0].get("exchange_id");
	  Ext.Ajax.request({
		    url : CTA.userGift.params.url.detailUrl,
		    params : {id : id},
		    success : function(response) {

			      var data = Ext.util.JSON.decode(response.responseText).data;

			      var formPanel = new CTA.common.SFormPanel({
			    	    items : [{
			    	        fieldLabel : '用户名称',
			    	        name : 'user_name'
			    	      },{
			    	    	  fieldLabel : '用户电话',
			    	    	  name : 'user_telephone'
			    	      },{
			    	    	  fieldLabel : '礼品名称',
			    	    	  name : 'gift_name'
			    	      },{
			    	    	  xtype : 'numberfield',
			    	    	  fieldLabel : '礼品积分',
			    	    	  name : 'points'
			    	      },{
			    	    	  fieldLabel : '兑换时间',
			    	    	  name : 'exchange_time'
			    	      },{
			    	          xtype : 'combo',
			    	          fieldLabel : '兑换状态',
			    	          emptyText : '请选择兑换状态',
			    	          name : 'exchange_status',
			    	          hiddenName : 'exchange_status',
			    	          triggerAction : 'all',
			    	          store : new Ext.data.SimpleStore({
			    	            fields : ['label', 'value'],
			    	            data : [["兑换中", "1"],["兑换完成", "2"],["兑换作废", "-1"]]
			    	          }),
			    	          displayField : 'label',
			    	          valueField : 'value',
			    	          mode : 'local',
			    	          editable : false
			    	        },{
						          xtype:"displayfield",
						          name:"descr",
						          fieldLabel:"礼品描述",
						          style : 'margin-top:10px;margin-bottom:10px'
						   }],
			    	      data : data
			    	  });

				  var detailWindow = new CTA.common.Window({
				    	title : '查看兑换订单详情',
				    	width : 750,
					    height : 400,
					    layout : 'fit',
				        items : [formPanel]
				      });
				      detailWindow.show();
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
		ids.push(item.get("exchange_id"));
	});

	Ext.Msg.confirm("警告","您确定删除这  "+ids.length+" 条数据吗？",function(button){
	    if('yes' == button){
	    	CTA.common.Mask.showMask();
	    	CTA.common.Ajax.request({
	    		url : CTA.userGift.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
