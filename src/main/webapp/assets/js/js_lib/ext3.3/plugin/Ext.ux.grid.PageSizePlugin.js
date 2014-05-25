
Ext.namespace('Ext.ux.grid');
Ext.ux.grid.PageSizePlugin = function() {
    Ext.ux.grid.PageSizePlugin.superclass.constructor.call(this, {
        store: new Ext.data.SimpleStore({
            fields: ['text', 'value'],
            data: [['2', 2],['10', 10], ['20', 20], ['50', 50], ['100', 100]]
        }),
        mode: 'local',
        displayField: 'text',
        valueField: 'value',
        editable: false,
        allowBlank: false,
        triggerAction: 'all',
        width: 60
    });
};

Ext.extend(Ext.ux.grid.PageSizePlugin, Ext.form.ComboBox, {
    init: function(paging) {
        paging.on('render', this.onInitView, this);
    },

    onInitView: function(paging) {
        paging.add('-','每页',this,'条');
        this.setValue(paging.pageSize);
        this.on('select', this.onPageSizeChanged, paging);
    },

    onPageSizeChanged: function(combo) {
        this.pageSize = parseInt(combo.getValue());
        try{
       		 pageRowCount = this.pageSize;
       	}catch(e){}
        this.doLoad(0);
    }
});
