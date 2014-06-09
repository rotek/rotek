Ext.ns("CTA.common");
//定义所有前台方法的工具类
CTA.common = {
	//常量类
	Constant : {
		queryParams : {},
		Mask : null
	},
	Mask: {
		//创建遮罩
		showMask : function(settings){
			settings = Ext.apply({
				target : Ext.getBody(),
				msg : '处理中...'
			},settings);
			CTA.common.Constant.Mask = new Ext.LoadMask(settings.target, settings);
			CTA.common.Constant.Mask.show();
		},
		//隐藏遮罩
		hideMask : function(){
			if(CTA.common.Constant.Mask){
				CTA.common.Constant.Mask.hide();
			}
		}
	},
	//创建gridPanel
	GridPanel : {
		createGridPanel : function(settings){
			//初始化查询参数
			CTA.common.Constant.queryParams = {};
			var url = "";
			var _fields = [];
			var _columns = [];
			//带复选框
			_columns.push(new Ext.grid.CheckboxSelectionModel());
			if(settings.url){
				url = settings.url;
				delete settings.url;
			}
			Ext.each(settings.dataList,function(value){
				var field = {
					name:value.index,
		            type:'string'
				};
				var column = {
			        header:value.header,
			        dataIndex:value.index
				};
				if(value.width){
					column.width = value.width;
				}
				if(typeof(value.align) != "undefined" ){
					column.align = value.align;
				}
				if(value.renderer && Function == value.renderer.constructor){
					column.renderer = value.renderer;
				}
				_fields.push(field);
				_columns.push(column);
			});

			var store = new Ext.data.Store({
			    autoLoad:true,
			    proxy:new Ext.data.HttpProxy({
			        url : url
			    }),
			    reader:new Ext.data.JsonReader({
			        totalProperty:'totalCount',
			        root:'dataList',
			        id:'reader_'
			    }, Ext.data.Record.create(_fields)),
			    listeners : {
			    	beforeload : function(thiz,options) {
			    		Ext.apply(thiz.baseParams, CTA.common.Constant.queryParams);
			    	}
			    }
			});
			var columns = new Ext.grid.ColumnModel(_columns);
			var gridPanel = new Ext.grid.GridPanel({
				id : 'gridPanel',
			    region: 'center',
			    border :false,
			    layout:'fit',
			    autoScroll:true,
			    collapsible:true,
			    loadMask:true,
			    store:store,
			    cm:columns,
			    sm:new Ext.grid.CheckboxSelectionModel(),
			    view:new Ext.grid.GridView({
			        forceFit:true
			    }),
			    bbar:new Ext.PagingToolbar({
			        pageSize:15,
			        store:store,
			        displayInfo:true,
			        displayMsg:'显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			        emptyMsg:"没有记录"
			    })
			});

			return gridPanel;
		}
	},
	//ajax请求的封装
	Ajax : {
		request : function(config){
			config = Ext.apply({
	             url : '',
	             params : '',
	             method : 'POST',
	             success : function(response) {
		            	CTA.common.Mask.hideMask();
		                response = Ext.util.JSON.decode(response.responseText);
		                if(true == response.success){
		                	CTA.common.Mask.hideMask();
		                	Ext.Msg.alert('成功提示', '恭喜您，操作成功！',function() {
	                		if(Ext.getCmp("gridPanel")){
	    		            	Ext.getCmp("gridPanel").getStore().reload();
	    		            }
	    		            if(Ext.getCmp("addWindow")){
	    		            	Ext.getCmp("addWindow").close();
	    		            }
	    		            if(Ext.getCmp("updateWindow")){
	    		            	Ext.getCmp("updateWindow").close();
	    		            }
	    		            if(Ext.getCmp("updateWindow_other")){
	    		            	Ext.getCmp("updateWindow_other").close();
	    		            }
		                  });
		                }else {
		                	var messages = response.messages;
		                	 if(messages && messages.length>0){
						            var msg = "";
						            for(var i = 0;i<messages.length;i++){
						              msg += messages[i];
						              msg += "<br/>";
						            }
						            Ext.Msg.alert('操作失败', msg);
						            return;
						     }
					         Ext.Msg.alert('失败提示', "操作失败，请刷新后重试！");
		                }
		              },
		              failure : function() {
		            	  CTA.common.Mask.hideMask();
		            	  Ext.Msg.alert('出错提示', "操作失败，请刷新后重试!");
		              }
			},config);
			Ext.Ajax.request(config);
		}
	}
};
