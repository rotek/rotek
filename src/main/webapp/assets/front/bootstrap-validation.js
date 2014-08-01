/*
表单校验
使用说明：
$("#formid").validate([     --表单ID
 {
 	id: "inputid",           --控件ID
 	required: true,          --控件是否必填，默认false
 	requiredSelect: true,    --下拉框是否必选，默认false。错误信息会直接显示在下拉框右边，而不是弹出的popup。
 	email: true,             --电子邮件格式，默认false
 	mobile: true,            --手机号码格式，默认false
 	phone: true,             --电话号码格式，默认false
 	url: true,               --网页地址格式，默认false
 	zip: true,               --邮政编码格式，默认false
 	IDnumber: true,          --身份证号码格式，默认false
 	date: true,              --日期格式，默认false
 	number: true,            --数字格式，包括正负数，0，小数，默认false
 	digits: true,            --正整数格式，默认false
 	charNumber: true,        --英文字母和数字，默认false
 	halfChar: true,          --半角字符，默认false
 	fullChar: true,          --全角字符，默认false
 	equalTo: "inputid",      --值与指定ID的值一致，默认null。一般用于设置密码的校验
 	smallTo: "inputid",      --日期<=指定ID的日期，默认null。用于开始日期与结束日期的校验
 	bigTo: "inputid",        --日期>=指定ID的日期，默认null。用于开始日期与结束日期的校验
 	fileFormat: ".jpg,.png", --上传文件后缀，默认null
 	digitsLength: 4,         --固定长度的正整数，默认null
 	maxLength: 20,           --最大长度，默认null
 	minLength: 5,            --最小长度，默认null
 	rangeLength: [5,20],     --长度范围，默认null
 	maxValue: 9000,          --最大数值，默认null
 	minValue: 300,           --最小数值，默认null
 	rangeValue: [300,9000],  --数值范围，默认null
 	validateFunc: true,      --调用自定义函数function validateFunc(id){...}，默认false。一般用于复杂的校验，例如ajax。函数的返回值为错误信息。
 	placement: "top"         --弹出错误信息的位置。默认"bottom"。如果是下拉框，此设置无效。值：top,bottom,left,right
 },
 {id:"inputid", xxxxx}
]);
*/
;(function($){
$.fn.extend({
  "validate" : function(rules){
    //内置错误信息
    var errMsgs = {
        required: "不可为空",
        requiredSelect: "必须选择一项",
        email: "请输入正确格式的电子邮件",
        mobile: "请输入正确格式的手机号码",
        phone: "请输入区号-电话号码",
        url: "请输入合法的网址",
        zip: "请输入正确格式的邮政编码",
        IDnumber: "请输入正确格式的身份证号",
        date: "请输入合法的日期",
        number: "请输入合法的数字",
        digits: "只能输入整数",
        charNumber: "只能输入英文字母和数字",
        halfChar: "只能输入半角字符",
        fullChar: "只能输入全角字符",
        equalTo: "请再次输入相同的值",
        smallTo: "请输入较小的日期",
        bigTo: "请输入较大的日期",
        fileFormat: "请选择 {0} 格式的文件",
        digitsLength: "请输入 {0} 位数字",
        maxLength: "长度不能超过 {0} 位",
        minLength: "长度不能少于 {0} 位",
        rangeLength: "长度应在 {0} 和 {1} 之间",
        maxValue: "数字不能大于 {0}",
        minValue: "数字不能小于 {0}",
        rangeValue: "数字应在 {0} 和 {1} 之间"
    };
    
    //设置控件弹出提示框属性，及焦点离开事件
    for (var idx in rules) {
      var rule = rules[idx];
      var inputObj = $('#'+rule.id);
      
      var pm = "bottom";
      if (rule.placement){pm = rule.placement;}
      if (!rule.requiredSelect){inputObj.popover({container: "body", placement: pm, trigger: "focus hover"});}
      
      inputObj.parent("div").addClass("has-feedback");
      if (!rule.requiredSelect){
    	  inputObj.after('<span class="glyphicon form-control-feedback"></span>');
      }else{
    	  inputObj.after('<span class="glyphicon control-label"></span>').after('<span class="control-label"></span>');
      }
      
      inputObj.attr("validate-rule",JSON.stringify(rule));
      inputObj.blur(function(){$(this).validateResult($(this).goValidate(errMsgs));});
    }
    
    //表单提交前再查一次
    $(this).submit(function(e){
      var validateOkFlg = true;
      for (var idx in rules) {
    	var rule = rules[idx];
    	var inputObj = $('#'+rule.id);
    	
    	var okFlg = inputObj.goValidate(errMsgs);
    	inputObj.validateResult(okFlg);
    	if (!okFlg) {
    	  validateOkFlg = false;
    	}
      }
      if (!validateOkFlg){
        e.preventDefault();
      }
    });
  },
  //单控件校验(仅供内部调用)
  "goValidate" : function(errMsgs){
    //校验规则
    var rule = JSON.parse($(this).attr("validate-rule"));
    var value = $(this).val();
    //属性值，用于显示错误信息
    var attrString = "data-content";

    if (rule.required && value == ""){
      $(this).attr(attrString, errMsgs.required);
      return false;
    }
    if (rule.requiredSelect && value == ""){
      $(this).attr(attrString, errMsgs.requiredSelect);
      return false;
    }
    if (!rule.required && !rule.requiredSelect && value == ""){
      $(this).removeAttr(attrString);
      return true;
    }
    if (rule.email && !/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)){
      $(this).attr(attrString, errMsgs.email);
      return false;
    }
    if (rule.mobile && !/^1\d{10}$/.test(value)){
      $(this).attr(attrString, errMsgs.mobile);
      return false;
    }
    if (rule.phone && !/^\d{3}-\d{8}|\d{4}-\d{7}$/.test(value)){
      $(this).attr(attrString, errMsgs.phone);
      return false;
    }
    if (rule.url && !/^[a-zA-z]+:\/\/[^\s]*$/.test(value)){
      $(this).attr(attrString, errMsgs.url);
      return false;
    }
    if (rule.zip && !/^[1-9]\d{5}$/.test(value)){
      $(this).attr(attrString, errMsgs.zip);
      return false;
    }
    if (rule.IDnumber && !/^\d{15}(\d{2}[0-9xX])?$/.test(value)){
      $(this).attr(attrString, errMsgs.IDnumber);
      return false;
    }
    if (rule.date && !/^\d{4}-((1[0-2])|(0?[1-9]))-(([12][0-9])|(3[01])|(0?[1-9]))$/.test(value)){
      $(this).attr(attrString, errMsgs.date);
      return false;
    }
    if (rule.number && !(/^[+-]?\d+[.]?\d*$/.test(value) || /^[+-]?\d*[.]?\d+$/.test(value))){
      $(this).attr(attrString, errMsgs.number);
      return false;
    }
    if (rule.digits && !/^\d+$/.test(value)){
      $(this).attr(attrString, errMsgs.digits);
      return false;
    }
    if (rule.charNumber && !/^[A-Za-z0-9]+$/.test(value)){
      $(this).attr(attrString, errMsgs.charNumber);
      return false;
    }
    if (rule.halfChar && !/^[\x00-\xff]+$/.test(value)){
      $(this).attr(attrString, errMsgs.halfChar);
      return false;
    }
    if (rule.fullChar && !/^[^\x00-\xff]+$/.test(value)){
      $(this).attr(attrString, errMsgs.fullChar);
      return false;
    }
    if (rule.equalTo && value != $("#"+rule.equalTo).val()){
      $(this).attr(attrString, errMsgs.equalTo);
      return false;
    }
    if (rule.smallTo && $("#"+rule.smallTo).val() != ""){
      var value2 = $("#"+rule.smallTo).val();
      if (/^\d{4}-((1[0-2])|(0?[1-9]))-(([12][0-9])|(3[01])|(0?[1-9]))$/.test(value2)){
        var dt = Date.parse(value.replace(/-/g, "/"));
        var dt2 = Date.parse(value2.replace(/-/g, "/"));
        if (dt > dt2){
          $(this).attr(attrString, errMsgs.smallTo);
          return false;
        }
      }
    }
    if (rule.bigTo && $("#"+rule.bigTo).val() != ""){
      var value2 = $("#"+rule.bigTo).val();
      if (/^\d{4}-((1[0-2])|(0?[1-9]))-(([12][0-9])|(3[01])|(0?[1-9]))$/.test(value2)){
        var dt = Date.parse(value.replace(/-/g, "/"));
        var dt2 = Date.parse(value2.replace(/-/g, "/"));
        if (dt < dt2){
          $(this).attr(attrString, errMsgs.bigTo);
          return false;
        }
      }
    }
    if (rule.fileFormat){
      var lowValue = value.toLowerCase();
      var formats = rule.fileFormat.split(",");
      var isMatch = false;
      for (var idx in formats) {
        var reg = new RegExp("\\" + formats[idx].toLowerCase() + "$");
        if (reg.test(lowValue)) {
          isMatch = true;
          break;
        }
      }
      if (!isMatch){
        $(this).attr(attrString, errMsgs.fileFormat.replace("{0}", rule.fileFormat));
        return false;
      }
    }
    if (rule.digitsLength && (value.length != rule.digitsLength || !/^\d+$/.test(value))){
      $(this).attr(attrString, errMsgs.digitsLength.replace("{0}", rule.digitsLength));
      return false;
    }
    if (rule.maxLength && value.length > rule.maxLength){
      $(this).attr(attrString, errMsgs.maxLength.replace("{0}", rule.maxlength));
      return false;
    }
    if (rule.minLength && value.length < rule.minLength){
      $(this).attr(attrString, errMsgs.minLength.replace("{0}", rule.minlength));
      return false;
    }
    if (rule.rangeLength && ((value.length < rule.rangeLength[0]) || (value.length > rule.rangeLength[1]))){
      $(this).attr(attrString, errMsgs.rangeLength.replace("{0}", rule.rangeLength[0]).replace("{1}", rule.rangeLength[1]));
      return false;
    }
    if (rule.maxValue && value > rule.maxValue){
      $(this).attr(attrString, errMsgs.maxValue.replace("{0}", rule.maxValue));
      return false;
    }
    if (rule.minValue && value < rule.minValue){
      $(this).attr(attrString, errMsgs.minValue.replace("{0}", rule.minValue));
      return false;
    }
    if (rule.rangeValue && ((value < rule.rangeValue[0]) || (value > rule.rangeValue[1]))) {
      $(this).attr(attrString, errMsgs.rangeValue.replace("{0}", rule.rangeValue[0]).replace("{1}", rule.rangeValue[1]));
      return false;
    }
    if (rule.validateFunc) {
    	var rtn = validateFunc(rule.id);
    	if (typeof(rtn) != "undefined" && rtn.length > 0) {
          $(this).attr(attrString, rtn);
          return false;
    	}
    }
    $(this).removeAttr(attrString);
    return true;
  },
  //处理校验结果(仅供内部调用)
  "validateResult" : function(validateOkFlg){
    //校验规则
    var rule = JSON.parse($(this).attr("validate-rule"));
    var parDiv = $(this).parent("div");
    //结果图标
    var span = parDiv.find("span:first");
    //结果信息(只有select控件需要此span)
    var spanMsg = span.next();
    
    if (validateOkFlg){
      parDiv.removeClass("has-error");
      parDiv.addClass("has-success");
      span.removeClass("glyphicon-remove");
      span.addClass("glyphicon-ok");
      if (rule.requiredSelect){
        spanMsg.text("");
      }
    }else{
      parDiv.addClass("has-error");
      parDiv.removeClass("has-success");
      span.addClass("glyphicon-remove");
      span.removeClass("glyphicon-ok");
      if (rule.requiredSelect){
        spanMsg.text($(this).attr("data-content"));
      }
    }
  }
});
})(jQuery);