/**@description 封装了 增加，删除，修改，查询的 window样式
 * @author chenwenpeng
 * @time 2013-3-28 下午03:33:41
 */

Ext.ns("CTA.common");

CTA.common.Window = Ext.extend(Ext.Window,{
	constructor : function(config){
		config = Ext.apply({
			plain : true,
			resizable:false,
            modal:true,
            closable:false
		},config);
		CTA.common.Window.superclass.constructor.call(this, config);
		this._addCloseButton();
		this._setEscEvent();
	},
	_addCloseButton : function(){
		var self = this;
		this.addButton({
			id:'cta-close-button',
            text:'关闭(esc)',
            handler:function () {
                self.close();
            }
		});
	},
	_setEscEvent : function () {
        this.on('show', function (c) {
            var km = new Ext.KeyMap(c.getEl(), {
                key:Ext.EventObject.ESC,
                fn:function () {
                    this.close();
                },
                scope:this
            });
            km.enable();
        });
    }
});

CTA.common.SaveWindow = Ext.extend(CTA.common.Window,{
		constructor : function(config){
		config = Ext.apply({
			buttonText:'保存(enter)',
			title:'保存'
			},config);
		
		CTA.common.SaveWindow.superclass.constructor.call(this, config);
        this._addButton(config.handler);
        this._setEnterEvent(config.handler);
	},
	_addButton : function(handler){
		this.addButton({
			text : this.buttonText,
			handler : handler
		});
	},
	_setEnterEvent : function(handler){
		 this.on('show', function (c) {
            var km = new Ext.KeyMap(c.getEl(), {
                key:Ext.EventObject.ENTER,
                fn:function(){
                	handler();
                },
                scope:this
            });
            km.enable();
        });
	}
});

CTA.common.UpdateWindow = Ext.extend(CTA.common.SaveWindow,{

	constructor : function(config){
		config = Ext.apply({
			buttonText : "修改(enter)",
			title : "修改"
		},config);
		CTA.common.UpdateWindow.superclass.constructor.call(this,config);
	}
});

CTA.common.DescWindow = Ext.extend(CTA.common.Window,{
	constructor : function(config){
		config = Ext.apply({
			title : "算法说明"
		},config);
		CTA.common.DescWindow.superclass.constructor.call(this,config);
	}
});

CTA.common.QueryWindow = Ext.extend(CTA.common.SaveWindow,{

	constructor : function(config){
		config = Ext.apply({
			buttonText : "查询(enter)",
			title : "查询"
		},config);
		CTA.common.UpdateWindow.superclass.constructor.call(this,config);
	}
});
