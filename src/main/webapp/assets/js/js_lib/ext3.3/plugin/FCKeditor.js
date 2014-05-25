/**
var oFCKeditorOptions = {
    BasePath: 'fckeditor/',
    Config: {
        BaseHref: 'http://localhost:8080/hrcis/',
        SkinPath: '../../fckeditor/editor/skins/office2003/',
        CustomConfigurationsPath: '../../fckeditor/fckconfig.js',
        ProcessHTMLEntities: true,
        ProcessNumericEntities: false
    },
    AllowQueryStringDebu: false,
    ToolbarSet: 'XXX'
};

*/
Ext.form.FCKeditor = function(config){
	this.useSource = false;
    this.config     = config;
    Ext.form.FCKeditor.superclass.constructor.call(this, config);
    this.MyisLoaded = false;
    this.MyValue    = '';

    this.fckInstance= undefined; // to avoid using FCKAPI, this is a reference to instance created on FCKeditor_OnComplete
};
Ext.extend(Ext.form.FCKeditor, Ext.form.TextArea, {
    onRender : function(ct, position){
        if(!this.el){
            this.defaultAutoCreate = {
                tag: "textarea",
                style:"width:100px;height:60px;",
                autocomplete: "off"
            };
        }
        Ext.form.TextArea.superclass.onRender.call(this, ct, position);
        //Hide textarea to stop flashing up before FCKEditor renders.
        this.hideMode = "visibility"; // set hideMode to visibility, to retain height.
        this.hidden = true; // hide textarea
        if(this.grow){
            this.textSizeEl = Ext.DomHelper.append(document.body, {
                tag: "pre", cls: "x-form-grow-sizer"
            });
            if(this.preventScrollbars){
                this.el.setStyle("overflow", "hidden");
            }
            this.el.setHeight(this.growMin);
        }
        setTimeout("loadFCKeditor('"+this.name+"',"+ this.config.height +");",100);
    },
    setValue : function(value){
        this.MyValue = value;
        if (this.MyisLoaded){
          FCKeditorSetValue(this.name,value);
        }
        Ext.form.TextArea.superclass.setValue.apply(this,[value]);
    },
    getValue : function(){
        if (this.MyisLoaded){
            value = FCKeditorGetValue(this.name);
            Ext.form.TextArea.superclass.setValue.apply(this,[value]);
            return Ext.form.TextArea.superclass.getValue.call(this);
        } else {
            return this.MyValue;
        }
    },
    getRawValue : function(){
        if (this.MyisLoaded){
            value=FCKeditorGetValue(this.name);
            Ext.form.TextArea.superclass.setRawValue.apply(this,[value]);
            return Ext.form.TextArea.superclass.getRawValue.call(this);
        } else {
            return this.MyValue;
        }
    }
});
Ext.reg('fckeditor', Ext.form.FCKeditor);
function loadFCKeditor(element, height){
    oFCKeditor                         = new FCKeditor(element);
    oFCKeditor.Height                 = height;
    Ext.apply(oFCKeditor,oFCKeditorOptions);
    oFCKeditor.ReplaceTextarea();
}
function FCKeditor_OnComplete(editorInstance){
	var xvalue =  Ext.getCmp(editorInstance.Name).MyValue;
    Ext.getCmp(editorInstance.Name).MyisLoaded = true;
    Ext.getCmp(editorInstance.Name).fckInstance = editorInstance;


    if( Ext.getCmp(editorInstance.Name).useSource &&Ext.getCmp(editorInstance.Name).useSource==true)
    {
    	 if( editorInstance.EditMode== FCK_EDITMODE_WYSIWYG)
    	{
    		 editorInstance.SwitchEditMode();
    		 //editorInstance.innerText  = xvalue;
    		 Ext.getCmp(editorInstance.Name).setValue(xvalue);

    	}

    }

}
function FCKeditorSetValue(name,value){
    if (name != undefined){
        var extCmp = Ext.getCmp(name);
        if (extCmp != undefined) {
            extCmp.fckInstance.SetData(value);
        }
    }
}
function FCKeditorGetValue(name){
    if (name != undefined){
        var data = '';
        var extCmp = Ext.getCmp(name);

        if (extCmp != undefined) {
            data = extCmp.fckInstance.GetData();
        }
        return data;
    }
}
