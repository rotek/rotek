/**@description 封装前台toolbar
 * @author chenwenpeng
 * @time 2013-3-28 下午03:33:41
 */

Ext.ns("CTA.common");
CTA.common.Toolbar = Ext.extend(Ext.Toolbar,{
	constructor : function(){
		config = Ext.apply({
			region : 'north',
			//如果region类型为north，那么必须制定高度，如果不指定不显示
			height : 28
		});
		
		CTA.common.Toolbar.superclass.constructor.call(this,config);
		var self = this;
		Ext.each(buttonInfoList,function(buttonInfo){
			self.add({
				id : "button_" + buttonInfo.action,
				icon : basePath+"/assets/images/toolbar/"+ buttonInfo.icon,
				cls : 'x-btn-text-icon',
				style : 'margin:0px 10px 0px 0px',
				text : buttonInfo.name,
				isVisible : true,
				handler : function(){}
			});
		});
		
	},
	regAddHandler : function(func){
		this._regButtonHandler(func,this.buttonShow.add,basePath+"/assets/images/toolbar/add.png","添加");
	},
	_regButtonHandler : function(func,isShow,icon,text){
		if(isShow){
			this.add({
				icon : icon,
				cls : 'x-btn-text-icon',
				style : 'margin:0px 10px 0px 0px',
				text : text,
				isVisible : isShow,
				handler : func
			});
		}
	}
});
