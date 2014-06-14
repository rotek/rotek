//设置城市区划
Ext.ns("CTA.region");
CTA.region.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/region/listRegions",
		    dataList:[{
		          index:'id',
		          header:'区域ID'
		      },{
		          index:'name',
		          header:'区域名称'
		      },{
		        index:'sort',
		        header:'区域排序'
		      },{
			        index:'type',
			        header:'区域类型',
			        renderer:function(value){
			          if(1==value){
			            return "高校区域";
			          }else if(2 == value){
			            return "普通区域";
			          }else if(3 == value){
			        	  return "企业团餐";
			          }
			        }
			  },{
		        index:'status',
		        header:'区域状态',
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
		addUrl : basePath + "/admin/region/addRegion",
		detailUrl : basePath + "/admin/region/getRegionDetail",
		modifyUrl : basePath + "/admin/region/modifyRegion",
		dropUrl : basePath + "/admin/region/deleteRegion",

		listRegionsUrl : basePath + "/admin/region/listRegions_sort"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.region.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.region.params.url.addUrl
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
        fieldLabel : '区域名称',
        emptyText : '请输入区域名称',
        name : 'name',
        minLength:1,
        maxLength: 50
      },{
        xtype : 'combo',
        fieldLabel : '区域类型',
        emptyText : '请选择区域类型',
        name : 'type',
        hiddenName : 'type',
        triggerAction : 'all',
        store : new Ext.data.SimpleStore({
          fields : ['label', 'value'],
          data : [["高校区域", "1"],["普通区域", "2"],["企业团餐", "3"]]
        }),
        displayField : 'label',
        valueField : 'value',
        mode : 'local',
        editable : false
      },{
		xtype : 'combo',
		fieldLabel : '区域排序',
		emptyText : '点击设置区域排序',
		name : 'sort',
		hiddenName : 'sort',
		triggerAction : 'all',
		displayField : 'name',
		valueField : 'sort',
		editable : false,
		allowBlank : true,
		store : new Ext.data.Store({
			reader : new Ext.data.JsonReader({
				root : 'dataList',
				fields : [ {
					name : 'sort'
				}, {
					name : 'name'
				} ]
			}),
			proxy : new Ext.data.HttpProxy({
				url : CTA.region.params.url.listRegionsUrl
			})
		})
	},{
        xtype : 'combo',
        fieldLabel : '楼宇状态',
        emptyText : '请选择楼宇状态',
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
    url : CTA.region.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
    	    items : [{
    	    	 xtype : 'hidden',
    	    	 name : 'id'
    	       },{
    	    	 xtype : 'hidden',
    	    	 name : 'city_id'
    	       },{
    	        fieldLabel : '区域名称',
    	        emptyText : '请输入区域名称',
    	        name : 'name',
    	        minLength:1,
    	        maxLength: 50
    	      },{
    	        xtype : 'combo',
    	        fieldLabel : '区域类型',
    	        emptyText : '请选择区域类型',
    	        name : 'type',
    	        hiddenName : 'type',
    	        triggerAction : 'all',
    	        store : new Ext.data.SimpleStore({
    	          fields : ['label', 'value'],
    	          data : [["高校区域", "1"],["普通区域", "2"],["企业团餐", "3"]]
    	        }),
    	        displayField : 'label',
    	        valueField : 'value',
    	        mode : 'local',
    	        editable : false
    	      },{
    			xtype : 'combo',
    			fieldLabel : '区域排序',
    			emptyText : '点击设置区域排序',
    			name : 'sort',
    			hiddenName : 'sort',
    			triggerAction : 'all',
    			displayField : 'name',
    			valueField : 'sort',
    			editable : false,
    			store : new Ext.data.Store({
    				reader : new Ext.data.JsonReader({
    					root : 'dataList',
    					fields : [ {
    						name : 'sort'
    					}, {
    						name : 'name'
    					} ]
    				}),
    				proxy : new Ext.data.HttpProxy({
    					url : CTA.region.params.url.listRegionsUrl
    				})
    			})
    		},{
    	        xtype : 'combo',
    	        fieldLabel : '楼宇状态',
    	        emptyText : '请选择楼宇状态',
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
                    url : CTA.region.params.url.modifyUrl
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
	    		url : CTA.region.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});