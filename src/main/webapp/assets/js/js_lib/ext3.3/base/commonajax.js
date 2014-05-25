/**
 *
 */
function ajaxFn(url,successfn,failurefn,params){
	if(params==null)params = {};
	Ext.Ajax.request({
	    url: url,
	    success: function(response) {
	       // Ext.Msg.alert('成功', response.responseText);
	        successfn(response);
	    },
	    failure: function(response) {

	    	failurefn(response);
	    },
	    params: params//{ name: 'value' }
	});

}
