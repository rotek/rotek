//设置角色信息
Ext.ns("CTA.role");
CTA.role.params = {
	//全局gridpanel的参数
	gridParam : {
			url :   basePath + "/admin/role/listRoles",
		    dataList:[{
		          index:'id',
		          header:'角色id'
		      },{
		          index:'name',
		          header:'角色名称'
		      },{
		        index:'status',
		        header:'角色状态',
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
		addUrl : basePath + "/admin/role/addRole",
		detailUrl : basePath + "/admin/role/getRoleDetail",
		modifyUrl : basePath + "/admin/role/modifyRole",
		dropUrl : basePath + "/admin/role/deleteRole",
		authorityUrl : basePath + "/admin/role/listAuthority"

	}
};

var gridPanel = CTA.common.GridPanel.createGridPanel(CTA.role.params.gridParam);
var toolbar = new CTA.common.Toolbar(authority);
//添加
toolbar.regAddHandler(function(){
	var saveHandler = function(){
        //检查表单是否填写好
        if(formPanel.getForm().isValid()){
          CTA.common.Mask.showMask({target:'addWindow'});
          formPanel.commit({
            url : CTA.role.params.url.addUrl
          });
        }
    };
	  //定义添加的窗口
	  var addWindow = new CTA.common.SaveWindow({
		id : 'addWindow',
	    width : 400,
	    height : 280,
	    layout : 'fit',
	    handler : saveHandler
	  });

  //定义添加窗口中的form
  var formPanel = new CTA.common.SFormPanel({
    items : [{
        fieldLabel : '角色名称',
        emptyText : '请输入角色名称',
        name : 'name',
        maxLength: 50
      },{
        xtype : 'combo',
        fieldLabel : '角色状态',
        emptyText : '请选择角色状态',
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

//修改角色信息
toolbar.regModifyHandler(function(){
  var selections = gridPanel.getSelectionModel().getSelections();
  if(!selections || selections.length <= 0){
    Ext.Msg.alert('提示', '请选择您要操作的数据，如果选择多条，只修改第一条!');
    return;
  }
  var roleId = selections[0].get("id");
  Ext.Ajax.request({
    url : CTA.role.params.url.detailUrl,
    params : {id : roleId},
    success : function(response) {
      var data = Ext.util.JSON.decode(response.responseText).data;

      var role_info_formPanel = new CTA.common.SFormPanel({
        items : [{
          xtype : 'hidden',
          fieldLabel : '角色ID',
          name : 'id',
          readOnly:true
        },{
          fieldLabel : '角色名称',
          emptyText : '请输入角色名称',
          name : 'name',
          allowBlank : false
        },{
          xtype : 'combo',
          name : 'status',
          triggerAction : 'all',
          fieldLabel : '角色状态',
          emptyText : '请选择角色状态',
          store : new Ext.data.SimpleStore({
            fields : ['label', 'value'],
            data : [["启用", "1"],["禁用", "-1"]]
          }),
          displayField : 'label',
          valueField : 'value',
          hiddenName : 'status',
          editable : false,
          mode : 'local',
          renderer : function(value){
          }
        }],
        data : data
      });

      var Tree = Ext.tree;
      // 定义根节点的Loader
      var treeloader = new Tree.TreeLoader( {
        dataUrl : CTA.role.params.url.authorityUrl,
        baseParams : {
          'roleId' : roleId
        }
      });
      // 异步加载根节点
      var rootnode = new Tree.AsyncTreeNode( {
        id : 'menu_1',
        text : '权限目录',
        draggable : false, // 根节点不容许拖动
        expanded : true,
        checked : false
      });
      var role_info_treePanel = new Tree.TreePanel( {
        id : 'role_info_treePanel',
        region : 'center',
        title : '设置权限',
        collapseMode : 'mini',
        checkModel: 'cascade',
        autoHeight:false,
        height : 200,
        autoScroll: true,
        animate : true,
        collapsible : true,  //是否可以折叠
        split : true,
        rootVisible : false, // 不显示显示根节点
        border : true, // 边框
        animate : true, // 动画效果
        autoScroll : true, // 自动滚动
        enableDD : false, // 拖拽节点
        containerScroll : true,
        checked : false,
        lines : true,
        loader : treeloader,
        listeners : {
          "checkchange" : function(node, checked) {
            //如果是子节点那么级联父节点
            //如果是父节点那么级联子节点
            //菜单的父节点不级联
            if(node.attributes.leaf){
              var parentNode = node.parentNode;
              Ext.each(parentNode.childNodes,function(c,i){
                if(c.getUI().checkbox.checked){
                  parentNode.getUI().checkbox.checked = true;
                  return false;
                }
                if(i == parentNode.childNodes.length -1){
                  parentNode.getUI().checkbox.checked = false;
                }
              });
            }else {
              if(node.childNodes.length>0 && !node.childNodes[0].attributes.leaf){
                return;
              }
              Ext.each(node.childNodes,function(child){
                //role_info_treePanel.getChecked();应该是根据节点的attributes.checked属性做判断，所以手动选中
                child.attributes.checked = checked;
                child.getUI().checkbox.checked = checked;
              });
            }
               }
        }
      });
      role_info_treePanel.setRootNode(rootnode);
      rootnode.expand(false, false);
      role_info_treePanel.expandAll();

      var tabs = new Ext.TabPanel({
        activeTab: 0,
        height : 385,
        deferredRender : true,
        items:[{
          id : "role_info",
          title : data.name + " 的基本信息",
          layout : 'border',
          items : [role_info_formPanel]
        },{
          id : "role_authority",
          title : data.name + " 的权限信息",
          layout : 'border',
          items : [role_info_treePanel]
        }]
      });

      var updateWindow = new CTA.common.UpdateWindow({
    	id : 'updateWindow',
        width : 550,
        height : 450,
        items : [tabs],
        handler : function(){
	        if(role_info_formPanel.getForm().isValid()){
	        	var params = role_info_formPanel.getForm().getValues();
	            var menuList = [];
	            var nodeList = role_info_treePanel.getChecked();
	            for(var i = 0;i<nodeList.length;i++){
	              var node = nodeList[i];
	              //如果node 是叶子节点，说明是按钮,
	              //如果想让一个角色拥有一个菜单的权限，
	              //必须最少给这个角色在这个菜单下面添加按钮权限，否则该角色不会拥有这个权限
	              if(node.leaf == true){
	                var menuId = node.parentNode.id;
	                var buttonId = node.id;

	                menuList.push(menuId+";"+buttonId);
	              }
	            }
	            params.authority = menuList.toString();

	            CTA.common.Mask.showMask({target:'updateWindow',msg:'处理中...'});
	            CTA.common.Ajax.request({
	              url : CTA.role.params.url.modifyUrl,
	              params : params
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
		        fieldLabel : '角色ID',
		        emptyText : '请输入角色名称',
		        allowBlank : true,
		        name : 'id'
		      },{
			        fieldLabel : '角色名称',
			        emptyText : '请输入角色名称',
			        allowBlank : true,
			        name : 'name'
			  },{
		        xtype : 'combo',
		        fieldLabel : '角色状态',
		        emptyText : '请选择角色状态',
		        name : 'status',
		        triggerAction : 'all',
		        store : new Ext.data.SimpleStore({
		          fields : ['label', 'value'],
		          data : [["启用", "1"],["禁用", "-1"]]
		        }),
		        displayField : 'label',
		        valueField : 'value',
		        hiddenName : 'status',
		        allowBlank : true,
		        mode : 'local'
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
		width : 350,
		height : 280,
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
	    		url : CTA.role.params.url.dropUrl,
	    		params : {ids : ids.toString()}
	    	});
	    }
});
});


var viewport = new Ext.Viewport({
  layout:'border',
  items:[gridPanel,toolbar]
});
