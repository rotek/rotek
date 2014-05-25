var LbasePagingGrid = function(gridConfig) {
	/** 基本配置 */
	var id = gridConfig.gridId;
	var gridCmReaderConfig = gridConfig.gridCmReaderConfig;
	var baseParams = gridConfig.baseParams ? gridConfig.baseParams : {};
	var viewConfig = gridConfig.viewConfig ? gridConfig.viewConfig : null;
	var gridListener = gridConfig.listeners ? gridConfig.listeners : null;
	var tbar = gridConfig.tbar ? gridConfig.tbar : null;
	/** 选择模式 */
	var sm = gridConfig.sm ? new Ext.grid.CheckboxSelectionModel() : false;
	/** *开始基本组装** */
	this.setBase(gridCmReaderConfig, sm);
	var store = this.setStore(gridConfig.gridId, baseParams);
	this.store = store;
	this.pages = true;/* 标志分页了，删除记录时更新分页信息 */
	if (gridConfig.enableColumnHide == false) {
		this.enableColumnHide = false;
	}
	LbasePagingGrid.superclass.constructor.call(this, {
		id : id ? id : "",
		store : store,
		cm : new Ext.grid.ColumnModel(this.columns),
		sm : sm,
		tbar : tbar,
		// enableColumnHide:false,
		listeners : gridListener,
		viewConfig : viewConfig,
		loadMask : true
	});
};
Ext.extend(LbasePagingGrid, Ext.grid.GridPanel, {
	/**
	 * 设置列和读取器
	 */
	setBase : function(gridCmReaderConfig, sm) {
		var cmarray = [ new Ext.grid.RowNumberer() ];
		if (sm)
			cmarray.push(sm);
		var rdarray = [];
		var oname = {};
		var cmobj = {};
		var rdobj = {};
		for ( var i = 0; i < gridCmReaderConfig.length; i++) {
			oname = gridCmReaderConfig[i];
			cmobj = {};
			cmobj.header = oname.show;
			cmobj.dataIndex = oname.tag;
			if (oname.width)
				cmobj.width = oname.width * 1;
			if (oname.id)
				cmobj.id = oname.id;
			if (oname.hidden)
				cmobj.hidden = oname.hidden;
			if (oname.editor)
				cmobj.editor = oname.editor;
			if (oname.renderer)
				cmobj.renderer = oname.renderer;
			cmobj.sortable = true;
			rdobj = {};
			rdobj.name = oname.tag;
			cmarray.push(cmobj);
			rdarray.push(rdobj);
		}
		/**
		 * var Record = Ext.data.Record.create([ {name: 'id', type: 'string'},
		 * {name: 'name', type: 'string'}, {name: 'descn', type: 'string'} ]);
		 */
		this.gRecord = Ext.data.Record.create(rdobj);
		this.columns = cmarray;
		this.baseRrecord = Ext.data.Record.create(rdarray);
		/**
		 * this.reader = new Ext.data.JsonReader({ totalProperty: "totalSize",
		 * root: "data" }, this.baseRrecord )
		 */
	},
	/** 设置存储器*** */
	setStore : function(gridId, baseParams, data) {
		if (typeof data == 'undefined')
			data = [];
		var store2 = new Ext.data.Store({
			proxy : new Ext.data.MemoryProxy(data),
			baseParams : baseParams,
			reader : new Ext.data.JsonReader({}, this.baseRrecord)
		});
		store2.load();
		return store2;
	},
	/***/
	delRows : function(records) {
		if (records) {
			var count = 0;
			if (typeof (records.length) == 'number')// 当删除多条记录时
			{
				for ( var i = 0; i < records.length; i++) {
					this.store.remove(records[i]);
				}
				count = records.length;
			} else {
				this.store.remove(records);// 当删除一条记录时
				count = 1;
			}
			if (this.pages  ) {
				this.store.totalLength = this.store.getTotalCount() - count;// 更改工具条显示信息
				if(this.pagebar)this.pagebar.updateInfo();// 更改工具条显示信息
			}
			this.getView().refresh();
		}
	}
});
function toString2(value, whitelist)
{

	var m = {
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        };
    var a,
        i,
        k,
        l,
        r = /["\\\x00-\x1f\x7f-\x9f]/g,
        v;

    switch (typeof value) {
    case 'string':
    	if(value.replace(' ','').length==0)return "\"\"";
        return r.test(value) ?
            '"' + value.replace(r, function (a) {
                var c = m[a];
                if (c) {
                    return c;
                }
                c = a.charCodeAt();
                return '\\u00' + Math.floor(c / 16).toString(16) +
                                           (c % 16).toString(16);
            }) + '"' :
            '"' + value + '"';

    case 'number':

        return isFinite(value) ? String(value) : 'null';

    case 'boolean':
    case 'null':
        return String(value);

    case 'object':

        if (!value) {
            return 'null';
        }

        if (typeof value.toJSON === 'function') {
            return toString2(value.toJSON());
        }
        a = [];
        if (typeof value.length === 'number' &&
                !(value.propertyIsEnumerable('length'))) {

            l = value.length;
            for (i = 0; i < l; i += 1) {
                a.push(toString2(value[i], whitelist) || 'null');
            }

            return '[' + a.join(',') + ']';
        }
        if (whitelist) {

            l = whitelist.length;
            for (i = 0; i < l; i += 1) {
                k = whitelist[i];
                if (typeof k === 'string') {
                    v = toString2(value[k], whitelist);
                    if (v) {
                        a.push(toString2(k) + ':' + v);
                    }
                }
            }
        } else {

            for (k in value) {
                if (typeof k === 'string') {
                    v = toString2(value[k], whitelist);
                    if (v || v==='' ||v=="") {
                        a.push(toString2(k) + ':' + v);
                    }
                }
            }
        }
        return '{' + a.join(',') + '}';
    }

}