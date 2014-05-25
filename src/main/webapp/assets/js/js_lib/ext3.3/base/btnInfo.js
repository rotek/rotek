/**
 *
 */
/***
 * var btnkeys ="btnId1,btnId2,btnId3";
 * */
function ajaxBtnInfo(basepath,url,btnkeys,successfn,failurefn)
{
	var url = typeof url=='undefined' ||url==null  ?basepath+"/admin/menu/getbtninfo":url;
	Ext.Ajax.request({
	    url: url,
	    success: function(response) {
	       // Ext.Msg.alert('成功', response.responseText);
	       if(typeof successfn != 'undefined' ) successfn(response);
	    },
	    failure: function(response) {
	    	 if(typeof failurefn != 'undefined' )failurefn(response);
	    },
	    params: {btnkeys:btnkeys}
	});

}