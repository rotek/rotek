//设置新闻信息
Ext.ns("CTA.news");
CTA.news.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/news/listNews",
		    dataList:[{
		          index:'id',
		          header:'新闻ID'
		      },{
		          index:'title',
		          header:'新闻标题'
		      },{
		        index:'building_name',
		        header:'新闻展示楼宇'
		      },{
		    	  index:'type',
		    	  header:'新闻类型',
		    	  renderer : function(value){
		    		  if(1==value){
				            return "普通新闻";
				      }else if(2 == value){
				    	  	return "打折促销";
				      }else if(3 == value){
				    	  return "通知公告";
				      }else {
				    	  return "未知类型";
				      }
		    	  }
		      },{
		        index:'level',
		        header:'新闻优先级',
		        renderer:function(value){
		          if(1==value){
		            return "低级";
		          }else if(2 == value){
		            return "较低";
		          }else if(3 == value){
		        	  return "中级";
		          }else if(4 == value){
		        	  return "较高";
		          }else if(5 == value){
		        	  return "高级";
		          }else {
		        	  return "未分配";
		          }
		        }
		      },{
		    	  index:'send_time',
		    	  header:'发布时间',
		    	  renderer : function(value){
						return new Date(parseFloat(value)).format("Y-m-d H:i:s");
					}
		      },{
		    	  index:'status',
		    	  header:'新闻状态',
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
		addUrl : basePath + "/admin/news/addNews",
		detailUrl : basePath + "/admin/news/getNewsDetail",
		modifyUrl : basePath + "/admin/news/modifyNews",
		dropUrl : basePath + "/admin/news/deleteNews",
		listButtonUrl :  basePath + "/admin/button/listButtons_s",

		listBuildings_dep : basePath + "/admin/building/listBuildings_dep",

		listRestaurants_s : basePath + "/admin/restaurant/listRestaurants_dep",
		listCategories_s : basePath + "/admin/menuCategory/listCategories_s"

	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.news.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.news.params.url.addUrl
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
        fieldLabel : '新闻标题',
        emptyText : '请输入新闻标题',
        name : 'title',
        minLength:1,
        maxLength: 50
      },{
	        xtype : 'combo',
	        fieldLabel : '新闻展示楼宇',
	        emptyText : '点击设置新闻展示楼宇',
	        name : 'building_ids',
	        hiddenName : 'building_ids',
	        triggerAction : 'all',
	        typeAhead: true,
	        selectOnFocus:true,
	        editable : false,
	        tpl:'<tpl for="."><div class="x-combo-list-item"><span><input type="checkbox" {[values.check?"checked":""]}  value="{[values.name]}" />  </span><span>  {name}</span></div></tpl>',
	        store : new Ext.data.Store({
				reader : new Ext.data.JsonReader({
					root : 'dataList',
					fields : [ {
						name : 'id'
					}, {
						name : 'name'
					} ]
				}),
				proxy : new Ext.data.HttpProxy({
					url : CTA.news.params.url.listBuildings_dep
				})
			}),
	        editable : false,
	        onSelect : function(record, index){
	            if(this.fireEvent('beforeselect', this, record, index) !== false){
	                record.set('check',!record.get('check'));
	                var strvalue=[];//传入后台的值
	                this.store.each(function(rc){
	                    if(rc.get('check')){
	                       strvalue.push(rc.get('id'));
	                    }
	                });

	              this.setValue(strvalue.join(","));
	              this.fireEvent('select', this, record, index);
	           }
	        }
	   },{
	        xtype : 'combo',
	        fieldLabel : '新闻类型',
	        emptyText : '请选择新闻类型',
	        name : 'type',
	        hiddenName : 'type',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["普通新闻", "1"],["打折促销", "2"],["通知公告", "3"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        mode : 'local',
	        editable : false
		},{
			xtype : 'combo',
			fieldLabel : '新闻优先级',
			emptyText : '请选择新闻优先级',
			name : 'level',
			hiddenName : 'level',
			triggerAction : 'all',
			store : new Ext.data.SimpleStore({
				fields : ['label', 'value'],
				data : [["低级", "1"],["较低", "2"],["中级", "3"],["较高", "3"],["高级", "3"]]

			}),
			displayField : 'label',
			valueField : 'value',
			mode : 'local',
			editable : false
		},{
			xtype : 'combo',
			fieldLabel : '新闻状态',
			emptyText : '请选择新闻状态',
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
	            fieldLabel: '新闻内容',
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
    url : CTA.news.params.url.detailUrl,
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
    	        fieldLabel : '新闻标题',
    	        emptyText : '请输入新闻标题',
    	        name : 'title',
    	        minLength:1,
    	        maxLength: 50
    	      },{
    	    	  	id : 'news_building',
    		        xtype : 'combo',
    		        fieldLabel : '新闻展示楼宇',
    		        emptyText : '点击设置新闻展示楼宇',
    		        name : 'building_id',
    		        hiddenName : 'building_id',
    		        triggerAction : 'all',
    		        typeAhead: true,
    		        editable : false,
    		        displayField : 'name',
    				valueField : 'id',
    		        store : new Ext.data.Store({
    					reader : new Ext.data.JsonReader({
    						root : 'dataList',
    						fields : [ {
    							name : 'id'
    						}, {
    							name : 'name'
    						} ]
    					}),
    					proxy : new Ext.data.HttpProxy({
    						url : CTA.news.params.url.listBuildings_dep
    					}),
    					autoLoad : true,
    					listeners : {
    						load : function (){
    							Ext.getCmp('news_building').setValue(data.building_id);
    						}
    					}
    				})
    		   },{
    		        xtype : 'combo',
    		        fieldLabel : '新闻类型',
    		        emptyText : '请选择新闻类型',
    		        name : 'type',
    		        hiddenName : 'type',
    		        triggerAction : 'all',
    		        store : new Ext.data.SimpleStore({
    		          fields : ['label', 'value'],
    		          data : [["普通新闻", "1"],["打折促销", "2"],["通知公告", "3"]]
    		        }),
    		        displayField : 'label',
    		        valueField : 'value',
    		        mode : 'local',
    		        editable : false
    			},{
    				xtype : 'combo',
    				fieldLabel : '新闻优先级',
    				emptyText : '请选择新闻优先级',
    				name : 'level',
    				hiddenName : 'level',
    				triggerAction : 'all',
    				store : new Ext.data.SimpleStore({
    					fields : ['label', 'value'],
    					data : [["低级", "1"],["较低", "2"],["中级", "3"],["较高", "4"],["高级", "4"]]

    				}),
    				displayField : 'label',
    				valueField : 'value',
    				mode : 'local',
    				editable : false
    			},{
    				xtype : 'combo',
    				fieldLabel : '新闻状态',
    				emptyText : '请选择新闻状态',
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
    		            fieldLabel: '新闻内容',
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
    				url : CTA.news.params.url.modifyUrl
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
		        fieldLabel : '新闻ID',
		        emptyText : '请输入新闻ID',
		        name : 'id',
		        allowBlank : true
	      },{
	        fieldLabel : '新闻标题',
	        emptyText : '请输入新闻标题',
	        name : 'title',
	        minLength:1,
	        maxLength: 50,
	        allowBlank : true
	      },{
	    	  	id : 'news_building',
		        xtype : 'combo',
		        fieldLabel : '新闻展示楼宇',
		        emptyText : '点击设置新闻展示楼宇',
		        name : 'building_id',
		        hiddenName : 'building_id',
		        triggerAction : 'all',
		        typeAhead: true,
		        editable : false,
		        allowBlank : true,
		        displayField : 'name',
				valueField : 'id',
		        store : new Ext.data.Store({
					reader : new Ext.data.JsonReader({
						root : 'dataList',
						fields : [ {
							name : 'id'
						}, {
							name : 'name'
						} ]
					}),
					proxy : new Ext.data.HttpProxy({
						url : CTA.news.params.url.listBuildings_dep
					}),
					autoLoad : true,
					listeners : {
						load : function (){
							Ext.getCmp('news_building').setValue(CTA.common.Constant.queryParams.building_id ? "":CTA.common.Constant.queryParams.building_id);
						}
					}
				}),
				allowBlank : true
		   },{
		        xtype : 'combo',
		        fieldLabel : '新闻类型',
		        emptyText : '请选择新闻类型',
		        name : 'type',
		        hiddenName : 'type',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["普通新闻", "1"],["打折促销", "2"],["通知公告", "3"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        mode : 'local',
		        editable : false,
		        allowBlank : true
			},{
				xtype : 'combo',
				fieldLabel : '新闻优先级',
				emptyText : '请选择新闻优先级',
				name : 'level',
				hiddenName : 'level',
				triggerAction : 'all',
				store : new Ext.data.SimpleStore({
					fields : ['label', 'value'],
					data : [["低级", "1"],["较低", "2"],["中级", "3"],["较高", "4"],["高级", "4"]]

				}),
				displayField : 'label',
				valueField : 'value',
				mode : 'local',
				editable : false,
				allowBlank : true
			},{
				xtype : 'combo',
				fieldLabel : '新闻状态',
				emptyText : '请选择新闻状态',
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
	    		url : CTA.news.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
