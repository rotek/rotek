/**
 * from submit
 * @author changzhenzhong
 * */
var isObject=new function(){
	var obj=new Object();
	obj.list=[
	    {name:"way"},
	    {name:"applicant"},
	    {name:"address"},
	    {name:"code"},
	    {name:"corporate"},
	    {name:"funds"},
	    {name:"nature"},
	    {name:"industry"},
	    {name:"begin_date",isdate:true},
	    {name:"product"},
	    {name:"number",isints:true},
	    {name:"market"},
	    {name:"email",isemail:true},
	    {name:"tel" ,istel:true},
	    {name:"fax" ,isfax:true},
	    {name:"contact"},
	    {name:"contact_tel",istel:true},
	    {name:"mobi",isMobi:true},
	    {name:"url",isNull:true},
	    {name:"honor",isNull:true},
	    {name:"other",isNull:true}
	    ];
	//匹配手机号
	obj.isMobi=function(str){
		var result=str.match(/^1[3|4|5|8][0-9]\d{4,8}$/);
		if(result==null)
			return false;
		return true;
	};
	//检查是否为空
	obj.isNull=function(str){
		if(str==""){
			return false;
		}else{
			return true;
		}
	};
	//检查是否为整形
	obj.isint=function(str)
	{
		var result=str.match(/^(-|\+)?\d+$/);
		if(result==null)
			return false;
		return true;
	};
	// 检查是否为 YYYY-MM-DD || YYYY/MM/DD 的日期格式
	obj.isdate=function(str){
		var result=str.match(/^(\d{4})(-|\/)(\d{1,2})\2(\d{1,2})$/);
		if(result==null)
			return false;
		var d=new Date(result[1], result[3]-1, result[4]);
		return (d.getFullYear()==result[1] && d.getMonth()+1==result[3] && d.getDate()==result[4]);
	};
	// 判断输入是否是有效的电子邮件
	obj.isemail=function (str)
	{
		var result=str.match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
		if(result==null)
			return false;
		return true;
	};
	//匹配国内电话号码(0511-4405222 或 021-87888822)
	obj.istell=function(str)
	{
	if(str.length!=12) return false;
	var result=str.match(/\d{3}-\d{8}|\d{4}-\d{7}/);
	if(result==null) return false;
	return true;
	};
	//校验是否为(0-10000)的整数
	obj.isints=function(str)
	{
	var result=str.match(/^[0-9]$|^([1-9])([0-9]){0,3}$|^10000$/);
	if(result==null) return false;
	return true;
	};
	//匹配腾讯QQ号
	obj.isqq=function(str)
	{
	var result=str.match(/[1-9][0-9]{4,}/);
	if(result==null) return false;
	return true;
	};
	//匹配身份证(15位或18位)
	obj.isidcard=function(str)
	{
	var result=str.match(/\d{15}|\d{18}/);
	if(result==null) return false;
	return true;
	};
	obj.root=function(){
		for ( var i=0;i<obj.list.length;i++) {
			var json=obj.list[i];
			if(!json.isNull){
				var val=document.getElementById(json.name).value;
				if(obj.isNull(val)){
					if(json.isemail){
						if(!obj.isemail(val)){
							alert("请检查email格式是否正确");
							return false;
						}
					}
					if(json.isints){
						if(!obj.isints(val)){
							alert("员工人数格式不正确请输入大于0的数字");
							return false;
						}
					}
					if(json.istel){
						alert("!obj.istell(val)="+!obj.istell(val));
						if(!obj.istell(val)){
							alert("电话号码格式不正确应输入区号加电话号码");
							return false;
						}
					}
					if(json.isMobi){
						if(!obj.isMobi(val)){
							alert("手机号码格式不正确");
							return false;
						}
					}
					if(json.isfax){
						if(!obj.istell(val)){
							alert("传真格式不正确应输入区号加传真号码");
							return false;
						}
					}
					if(json.isdate){
						if(!obj.isdate(val)){
							alert("日期格式不正确例:(2011-01-01)");
							return false;
						}
					}

				}else{
					alert("请检查信息是否完整");
					return false;
				}
			}

		}
		return true;
	};
	return obj;
}();