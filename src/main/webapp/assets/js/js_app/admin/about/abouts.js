//设置关于我们信息
Ext.ns("CTA.about");
CTA.about.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/about/listAbouts",
		    dataList:[{
		          index:'id',
		          header:'ID'
		      },{
		    	  index:'type',
		    	  header:'类型',
		    	  renderer:function(value){
		    		  if(1==value){
		    			  return "关于我们";
		    		  }else if(2 == value){
		    			  return "帮助中心";
		    		  }else if(3 == value){
		    			  return "招纳贤才";
		    		  }else if(4 == value){
		    			  return "店铺加盟";
		    		  }else if(5 == value){
		    			  return "服务协议";
		    		  }else {
		    			  return "未知类型";
		    		  }
		    	  }
		      },{
		    	  index:'status',
		          header:'状态',
		          renderer : function(value){
		        	  if(1==value){
				            return "<span style='color:green;'>有效</span>";
			          }else{
			            return "<span style='color:red'>无效</span>";
			          }
		          }
		      }]
	},
	url : {
		addUrl : basePath + "/admin/about/addAbout",
		detailUrl : basePath + "/admin/about/getAboutDetail",
		modifyUrl : basePath + "/admin/about/modifyAbout",
		dropUrl : basePath + "/admin/about/deleteAbout"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.about.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){

		      var saveHandler = function(){
		          //检查表单是否填写好
		          if(formPanel.getForm().isValid()){
		            CTA.common.Mask.showMask({target:'addWindow'});
		            formPanel.commit({
		              url : CTA.about.params.url.addUrl
		            });
		          }
		      };
		  	  //定义添加的窗口
		  	  var addWindow = new CTA.common.SaveWindow({
		  		id : 'addWindow',
		  	    width : '80%',
		  	    height : 580,
		  	    layout : 'fit',
		  	    handler : saveHandler
		  	  });

		    //定义添加窗口中的form
		    var formPanel = new CTA.common.SFormPanel({
		  	bodyStyle : 'overflow-x:hidden; overflow-y:scroll;padding-right :20px',
		      defaults:{
		      	anchor:'95%',
		  		layout : 'column',
		  		columnWidth : .5,
		      	anchor:'100%',
		      	msgTarget:'qtip',
		      	allowBlank : false
		      },
		      items : [{
		  	        xtype : 'combo',
		  	        fieldLabel : '信息类型',
		  	        emptyText : '请选择信息类型',
		  	        name : 'type',
		  	        hiddenName : 'type',
		  	        triggerAction : 'all',
		  	        store : new Ext.data.SimpleStore({
		  	          fields : ['label', 'value'],
		  	          data : [["关于我们","1"],["帮助中心","2"],["招贤纳士","3"],["店铺加盟","4"],["服务协议","5"]]
		  	        }),
		  	        displayField : 'label',
		  	        valueField : 'value',
		  	        mode : 'local',
		  	        editable : false
		  		},{
			        xtype : 'combo',
			        fieldLabel : '信息状态',
			        emptyText : '请选择信息状态',
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
		  				id:'content',
		  				xtype : 'fckeditor',
		  		        border : false,
		  		        frame:true,
		  	        	name: 'content',
		  	            fieldLabel: '页面内容',
		  	            value : '如果您要上传新闻图片，请使用IE或者火狐浏览器，或者开启双核浏览器的兼容模式',
		  	            frame:true,
		  	            height : 490,
		  				anchor : '100%'
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
    url : CTA.about.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
		  	bodyStyle : 'overflow-x:hidden; overflow-y:scroll;padding-right :20px',
		      defaults:{
		      	anchor:'95%',
		  		layout : 'column',
		  		columnWidth : .5,
		      	anchor:'100%',
		      	msgTarget:'qtip',
		      	allowBlank : false
		      },
		      items : [{
		    	  xtype : 'hidden',
		    	  name : 'id'
		      },{
		  	        xtype : 'combo',
		  	        fieldLabel : '信息类型',
		  	        emptyText : '请选择信息类型',
		  	        name : 'type',
		  	        hiddenName : 'type',
		  	        triggerAction : 'all',
		  	        store : new Ext.data.SimpleStore({
		  	          fields : ['label', 'value'],
		  	          data : [["关于我们","1"],["帮助中心","2"],["招贤纳士","3"],["店铺加盟","4"],["服务协议","5"]]
		  	        }),
		  	        displayField : 'label',
		  	        valueField : 'value',
		  	        mode : 'local',
		  	        editable : false
		  		},{
			        xtype : 'combo',
			        fieldLabel : '信息状态',
			        emptyText : '请选择信息状态',
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
		  				id:'content',
		  				xtype : 'fckeditor',
		  		        border : false,
		  		        frame:true,
		  	        	name: 'content',
		  	            fieldLabel: '页面内容',
		  	            value : '如果您要上传新闻图片，请使用IE或者火狐浏览器，或者开启双核浏览器的兼容模式',
		  	            frame:true,
		  	            height : 490,
		  				anchor : '100%'
		  		}],
		  		data : data
		    });

      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
	    width : '80%',
	    height : 580,
	    layout : 'fit',
        items : [formPanel],
        handler : function(){
        	 //检查表单是否填写好
            if(formPanel.getForm().isValid()){
            	CTA.common.Mask.showMask({target:'updateWindow'});
    			formPanel.commit({
    				url : CTA.about.params.url.modifyUrl
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
	    		url : CTA.about.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
