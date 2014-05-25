//验证的公共类
//提示方式为qtip时候需要显式调用
Ext.QuickTips.init();
Ext.apply(Ext.form.VTypes,{
	'email' : function(value){
		if(value){
			if(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/.test(value)){
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	},
	'telephone' : function(value){
		if(value){
			if(/^1[3|4|5|8][0-9]\d{8}$/.test(value)){
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	},
	'password' : function(value){
		if(value){
			if(/^[\u4E00-\u9FA5]+/.test(value)){
				return false;
			}else {
				return true;
			}
		}else {
			return true;
		}
	},
	'en_only' : function(value){
		if(value){
			if(/^[a-zA-Z]+$/g.test(value)){
				return true;
			}else {
				return false;
			}
		}else {
			return false;
		}
	},
	'date_time' : function(value){
		if(value){
			if(/^\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2}/.test(value) || /^\d{4}-\d{2}-\d{2}/.test(value)){
				return true;
			}else {
				return false;
			}
		}else {
			return true;
		}
	},
	date_timeText : '请输入正确格式的时间(例如 2013-08-15 20:30:55)',
	en_onlyText : '只能输入英文字母',
	passwordText : '密码不能包含中文',
	emailText : '请输入正确的email',
	telephoneText : '请输入11位电话号码'
});