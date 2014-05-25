//轮播信息
Ext.ns("CTA.broadcast");
CTA.broadcast.params = {
	//gridpanel的参数
	gridParam : {
			url : basePath + "/admin/broadcast/listBroadcasts",
		    dataList:[{
		          index:'id',
		          header:'轮播ID'
		      },{
		        index:'alt',
		        header:'轮播别名'
		      },{
		    	  index:'name',
		    	  header:'图片名称'
		      },{
		    	  index:'target',
		    	  header:'轮播地址'
		      },{
		    	  index:'building_name',
		    	  header:'展示的楼宇'
		      },{
		        index:'status',
		        header:'轮播状态',
		        renderer:function(value){
		          if(1==value){
		        	  return "<span style='color:green;'>有效</span>";
		          }else if(0 == value){
		        	  return "<span>默认</span>";
		          }else{
		        	  return "<span style='color:red'>无效</span>";
		          }
		        }
		      }]
	},

	url : {
		addUrl : basePath + "/admin/broadcast/addBroadcast",
		detailUrl : basePath + "/admin/broadcast/getBroadcastDetail",
		modifyUrl : basePath + "/admin/broadcast/modifyBroadcast",
		dropUrl : basePath + "/admin/broadcast/deleteBroadcast",

		listBuildings_dep : basePath + "/admin/building/listBuildings_dep"
	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.broadcast.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.broadcast.params.url.addUrl
          });
        }
    };
	  //定义添加的窗口
	  var addWindow = new CTA.common.SaveWindow({
		id : 'addWindow',
	    width : 550,
	    height : 320,
	    layout : 'fit',
	    handler : saveHandler
	  });

  //定义添加窗口中的form
  var formPanel = new CTA.common.SFormPanel({
	  fileUpload : true,
	  items : [{
        fieldLabel : '轮播别名',
        emptyText : '用于seo，非常重要，请注明本张图片的描述',
        name : 'alt',
        minLength:1,
        maxLength: 50
      },{
    	  fieldLabel : '轮播地址',
    	  emptyText : '请输入轮播地址',
    	  name : 'target',
    	  minLength:1,
    	  maxLength: 100
      },{
	        xtype : 'combo',
	        fieldLabel : '轮播显示楼宇',
	        emptyText : '点击设置轮播显示楼宇',
	        name : 'building_ids',
	        hiddenName : 'building_ids',
	        triggerAction : 'all',
	        typeAhead: true,
	        selectOnFocus:true,
	        allowBlank : true,
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
					url : CTA.broadcast.params.url.listBuildings_dep
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
	        fieldLabel : '轮播状态',
	        emptyText : '如果其他区域没有设置轮播，则会显示默认轮播',
	        name : 'status',
	        hiddenName : 'status',
	        triggerAction : 'all',
	        store : new Ext.data.SimpleStore({
	          fields : ['label', 'value'],
	          data : [["启用", "1"],["禁用", "-1"],["默认", "0"]]
	        }),
	        displayField : 'label',
	        valueField : 'value',
	        mode : 'local',
	        editable : false
	      },{
	    	inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
        	fieldLabel : '轮播图片',
        	name : 'name',
			text : "点击上传轮播图片",
			blankText : '请上传轮播图片'
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
    url : CTA.broadcast.params.url.detailUrl,
    params : {id : id},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var formPanel = new CTA.common.SFormPanel({
    	  fileUpload : true,
    	  items : [{
    		  xtype : 'hidden',
              name : 'id'
            },{
            fieldLabel : '轮播别名',
            emptyText : '用于seo，非常重要，请注明本张图片的描述',
            name : 'alt',
            minLength:1,
            maxLength: 50
          },{
        	  fieldLabel : '轮播地址',
        	  emptyText : '请输入轮播地址',
        	  name : 'target',
        	  minLength:1,
        	  maxLength: 100
          },{
        	  	id : 'buildingId_m',
		        xtype : 'combo',
		        fieldLabel : '轮播楼宇',
		        emptyText : '点击修改轮播展示的楼宇',
		        name : 'buildingId',
		        hiddenName : 'buildingId',
		        triggerAction : 'all',
		        displayField : 'name',
		        valueField : 'id',
		        editable : false,
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
						url : CTA.broadcast.params.url.listBuildings_dep
					}),
					autoLoad : true,
					listeners : {
						load : function (){
							Ext.getCmp('buildingId_m').setValue(data.buildingId);
						}
					}
				})
    	  },{
    	        xtype : 'combo',
    	        fieldLabel : '轮播状态',
    	        emptyText : '如果其他区域没有设置轮播，则会显示默认轮播',
    	        name : 'status',
    	        hiddenName : 'status',
    	        triggerAction : 'all',
    	        store : new Ext.data.SimpleStore({
    	          fields : ['label', 'value'],
    	          data : [["启用", "1"],["禁用", "-1"],["默认", "0"]]
    	        }),
    	        displayField : 'label',
    	        valueField : 'value',
    	        mode : 'local',
    	        editable : false
    	      },{
            	  fieldLabel : '轮播图片',
            	  name : 'name',
            	  readOnly : true,
            	  minLength:1,
            	  maxLength: 100
              },{
    	    	inputType : 'file', // 可以通过这个属性直接指定form表单的类型为上传文件的类型；
            	fieldLabel : '修改轮播图片',
            	name : 'name_',
    			text : "点击上传轮播图片",
    			blankText : '请上传轮播图片',
    			allowBlank : true
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
                    url : CTA.broadcast.params.url.modifyUrl
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
  	    	fieldLabel : '轮播ID',
	        emptyText : '请输入轮播ID',
	        name : 'id',
	        allowBlank : true
	      },{
	        fieldLabel : '轮播别名',
	        emptyText : '请输入轮播别名',
	        name : 'alt',
	        allowBlank : true,
	        minLength:1,
	        maxLength: 50
	      },{
	    	  fieldLabel : '轮播地址',
	    	  emptyText : '请输入轮播地址',
	    	  name : 'target',
	    	  minLength:1,
	    	  maxLength: 100,
	    	  allowBlank : true
	      },{
      	  	id : 'buildingId_m',
	        xtype : 'combo',
	        fieldLabel : '轮播楼宇',
	        emptyText : '点击修改轮播展示的楼宇',
	        name : 'buildingId',
	        hiddenName : 'buildingId',
	        triggerAction : 'all',
	        displayField : 'name',
	        valueField : 'id',
	        editable : false,
	        allowBlank : true,
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
					url : CTA.broadcast.params.url.listBuildings_dep
				}),
				autoLoad : true,
				listeners : {
					load : function (){
						Ext.getCmp('buildingId_m').setValue(undefined === CTA.common.Constant.queryParams.buildingId ? '' : CTA.common.Constant.queryParams.buildingId);
					}
				}
			})
	    },{
		        xtype : 'combo',
		        fieldLabel : '轮播状态',
		        emptyText : '请选择轮播状态',
		        name : 'status',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["启用", "1"],["禁用", "-1"],["默认", "0"]]
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
		Ext.apply(CTA.common.Constant.queryParams,params);

		gridPanel.getStore().reload();
		gridPanel.doLayout();
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
	    		url : CTA.broadcast.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
