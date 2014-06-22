/**@description 封装了 本系统的formpanel
 * @author chenwenpeng
 * @time 2013-3-28 下午03:33:41
 */
Ext.ns("CTA.common");
CTA.common.SFormPanel = Ext.extend(Ext.FormPanel,{
	constructor : function(config){
		config = Ext.apply({
			region : 'center',
			defaultType: 'textfield',
		    frame:true,
		    bodyStyle:'padding:15px 15px 10px 10px',
		    labelWidth: 80, // label settings here cascade unless overridden
		    labelAlign : 'left',
		    items : [],
		    defaults:{
		    	layout : 'column',
				columnWidth : .5,
		    	anchor:'100%',
		    	msgTarget:'qtip',
		    	allowBlank : false
		    },
		    commit : function(settings){
		    	settings = Ext.apply({
			        url : "",
			        method : 'POST',
			        submitEmptyText:false,//不提交emptytext
			        success : this._successCallback(settings),
			        failure : function(action,response) {
			        	response = Ext.util.JSON.decode(response.response.responseText);
			        	
			      	  	CTA.common.Mask.hideMask();
				          var messages = response.messages;
				          if(messages && messages.length>0){
				            var msg = "";
				            for(var i = 0;i<messages.length;i++){
				              msg += messages[i];
				              msg += "<br/>";
				            }
				            Ext.Msg.alert('保存出错', msg);
				            return;
				          }else {
				        	  //如果错误信息不存在，那么就认为是操作成功
				        	  Ext.Msg.alert('成功提示', "操作成功，恭喜您！");
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
				          }
				          //Ext.Msg.alert('出错提示', "保存出错，如果重试后异常仍出现，请联系工程师解决!");
			      }
			    },settings);
		    	//提交，commit ---> submit 函数名不能一样，如果一样那么constructor.call 会覆盖掉 submit，导致提交方法失效
		    	this.getForm().submit(settings);
		    },
		    //成功以后的
		    _successCallback : function(config){
		    	if(config.success){
		    		return config.success;
		    	}
		    	return (function(response) {
		        	CTA.common.Mask.hideMask();
		            Ext.Msg.alert('成功提示', '恭喜您，保存成功!');
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
		    }
		},config);
		//赋值
		if(config.data){
			Ext.each(config.items,function(item){
				if(item.name && undefined != config.data[item.name] && !item.value){
					if(item.xtype == "datefield"){
						item.value = Ext.util.Format.date(new Date(config.data[item.name]), 'Y-m-d');
					}else {
						item.value = config.data[item.name];
					}
				}
			});
		}
		CTA.common.SFormPanel.superclass.constructor.call(this,config);
	}
});