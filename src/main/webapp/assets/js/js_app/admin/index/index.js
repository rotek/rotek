//首页
Ext.ns("CTA.index");
CTA.index.Index = {
		//初始化并组装控件
		_init : function(){

			this.centerPanel = Ext.getCmp("center_index");
			var self = this;
			new Ext.Viewport({
				id : 'viewport',
			    layout:'border',
			    items:[{
			    	id : 'west_accordion',
			        region: 'west',
			        layout:'fit',
			        items:self.left_accordion,
			        split: true,
			        border :false,
			        collapsible: true,
			        width: 190,
			        minSize: 120,
			        maxSize: 200
			    },
			    {
			    		id : 'noth_head',
			        	region:"north",
			        	layout:'fit',
			        	height:0,
			        	collapsible: true,
			        	items:[self.topBox],
			        	bbar:["===欢迎您，尊敬的  ",{text : username},'  ===','->','-',{text:'安全退出',icon : basePath + '/assets/images/toolbar/shutdown.png',handler:function(){
			        		Ext.MessageBox.confirm('系统提示', '您确定要退出系统吗？',
			        				function(btn){
			        					if(btn=='yes'){
			        						window.location.href=basePath+"/admin/login/logout";
			        					}
			        				}
			        		);
			        	}},'-']
			      },
			      {
			        region: 'center',
			        border :false,
			        layout:'fit',
			        items:[self.ceter_tabs]
			    }]
			});
		},
		// 发送请求，返回用户的所能管理的所有菜单
		_createLeftMenu : function(){
			var left_accordion = new Ext.Panel({
				id : 'left_accordion',
			    layout:'accordion',
			    iconCls:'nav',
			    extraCls:'roomtypegridbbar',
			    defaults: {
			        // 应用到每个包含的panel
			        bodyStyle: 'padding:2px'
			    },
			    layoutConfig: {
			        // 这里是布局相关的配置项
			        titleCollapse: false,
			        animate: true,
			        activeOnTop: false
			    },
			    items :[{id : 'loading_',html : '<img style="margin-top:70%;width:170px;height:30px"  src="'+basePath+'/assets/images/index/loading_menu.gif"></img>'}]
			});
			left_accordion.on("render",function(){



				var self = CTA.index.Index;
				Ext.Ajax.request({
					url : basePath+'/admin/index/getMenuList',
					success : function(response) {

						self.left_accordion.remove("loading_");

						var json_menuList = Ext.util.JSON.decode(response.responseText);//根据JSON获取

						for(var i = 0;i<json_menuList.length;i++){
							var super_menu = json_menuList[i];
							var accordion_key = self._createLeftMenu_key(super_menu);
							self.left_accordion.add(accordion_key);
						}
						self.left_accordion.doLayout();
					},
					failure : function() {
						alert('加载权限信息失败，请退出后重试，如果继续异常请联系工程师解决！');
					}

				});
			});
			this.left_accordion = left_accordion;
		},
		//创建左侧的手风琴琴键
		_createLeftMenu_key : function(super_menu){
			var sub_menu = this._createLeftMenu_key_subMenu(super_menu.subMenuList);//创建字节点
			var menu ={
					id : Math.random(),
					titleCollapse : true,
					title: super_menu.name,
					items : sub_menu//添加子节点
			};
			return menu;
		},
		//创建手风琴的琴键下面的子菜单列表
		_createLeftMenu_key_subMenu : function(json_submemuList){
			var self = this;
			if(undefined == json_submemuList || null == json_submemuList || json_submemuList.length<=0){
				return [];
			}
			var submenuList = new Ext.tree.TreePanel({
				id : Math.random(),
			    rootVisible:false,
			    border:false,
			    useArrows : false,
			    loader:new Ext.tree.TreeLoader({
			        dataUrl:''
			    })
			});
			var root = new Ext.tree.TreeNode({
				id : Math.random(),
				text:'根节点',
				draggable:false,
				leaf:false,
				expand:false
			});
			for(var i = 0;i<json_submemuList.length;i++){
				var submenu = json_submemuList[i];
				var child = new Ext.tree.TreeNode({
					id : Math.random(),
				    text:submenu.name,
				    href : submenu.url,
				    draggable:false,
				    leaf:true,
				    expand:true
				});
				root.appendChild(child);
			}
			submenuList.setRootNode(root);
			submenuList.on("click",function(node,event){
				event.stopEvent();
				var conf = {
						id : node.id,
						title : node.text,
						url : node.attributes.href
					};
				self.openTab(conf);
				});
			return submenuList;
		},
		//创建头部的组件
		_createTopBox : function(){
			var topBox = new Ext.BoxComponent({ // raw element
							id : 'north_heade_item',
						    el: 'header',
						    autoHeight :true
						});
			this.topBox = topBox;
		},
		//创建中部的tabpanel
		_createCenterPanel : function(){
			var ceter_tabs = new Ext.TabPanel({
			    id:'center_index',
			    border :false,
			    activeTab: 0,
			    resizeTabs:true, // turn on tab resizing
			    enableTabScroll:true,
			    region: 'center',
			    defaults: {autoScroll:true},
			    plugins: new Ext.ux.TabCloseMenu(),
			    items:[
					new Ext.Panel({
						id : 'todo_today_panel',
						title:'代办事项',
					    baseCls:'x-plain',
					    layout:'fit',
					    layoutConfig: {columns:3},
					    defaults: {frame:true,autoScroll:true},
					    items:[{
					    	items : [{
					    		id : 'todo_today',
			                    layout : 'fit',
			        		    labelAlign : 'left',
			        		    bodyStyle:'padding:100px 25px 25px 50px',
			                    items : [{
			                    	xtype : 'box'
			                    	//,html:'<span style="color:red;font-size:20px;">今日，订单信息加载中...<br/></span><br/><span style="color:red;font-size:20px">今日，礼品兑换信息加载中...<br/></span>',
								}]
			                }]
					    }]
					})]
			  });
			this.ceter_tabs = ceter_tabs;
		},
		//打开一个tab，如果这个tab已经打开，那么就把这个tab设置为active
		openTab : function(config){
			if(config.id && config.title && config.url){
				config.url =basePath + config.url;
				var tab = {
			        id:config.id,
			        title:config.title,
			        closable:true,
			        html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="' + config.url + '"></iframe>'
			    };
				this.centerPanel.add(tab);
				this.setTabActive(config.id);
			}else {
				throw Error("打开tab的id,title,url不能为空！");
			}
		},
		//设置一个tab为激活状态
		setTabActive : function(id){
			if(!id){
				return;
			}
			var tab = this.centerPanel.getComponent(id);
			this.centerPanel.setActiveTab(tab);
		},
		//加载待办事项
		loadTodos : function(){

			Ext.Ajax.request({
				url : basePath + '/admin/index/listTodo',
				params : {},
				success : function(response) {
					var result = Ext.util.JSON.decode(response.responseText);
					if(result && result.data){
						Ext.getCmp('todo_today').removeAll();

						if(result.data.count_order){

							Ext.getCmp('todo_today').add({
								xtype : 'box',
								html:'<span style="color:green;font-size:20px;">今日订单 ：'+result.data.count_order+' 个<br/></span>',
							});
						}else {

							Ext.getCmp('todo_today').add({
								xtype : 'box',
								html:'<span style="color:red;font-size:20px;">今日订单 ：暂无用户订单信息<br/></span>',
							});
						}

						if(result.data.count_gift){

							Ext.getCmp('todo_today').add({
								xtype : 'box',
								html:'<span style="color:green;font-size:20px;">今日礼品兑换 ：'+result.data.count_gift+' 个<br/></span>',
							});
						}else {
							Ext.getCmp('todo_today').add({
								xtype : 'box',
								html:'<br/><span style="color:red;font-size:20px">今日礼品兑换 ：暂无礼品兑换信息<br/></span>',
							});
						}

						Ext.getCmp("todo_today_panel").doLayout();
					}
				},
				failure : function() {
					Ext.Msg.alert('提示', '加载待办事项失败，请刷新重试！');
				}
			});
		}
};


//======================================系统入口========================================
Ext.onReady(function(){
	CTA.index.Index._createLeftMenu();
	CTA.index.Index._createTopBox();
	CTA.index.Index._createCenterPanel();
	CTA.index.Index._init();
	//CTA.index.Index.loadTodos();

});