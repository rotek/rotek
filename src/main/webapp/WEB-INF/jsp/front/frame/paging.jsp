<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:if test="${pager.totalRows>0 }">
<table>
  <tr>
    <td>
      <a href="javaScript:<c:choose><c:when test="${pager.pageNo==0}">void(0)</c:when><c:otherwise>goPage(0)</c:otherwise></c:choose>">首页</a>
		  <a href="javaScript:<c:choose><c:when test="${pager.pageNo==0}">void(0)</c:when><c:otherwise>goPage(${pager.pageNo-1})</c:otherwise></c:choose>">&lt;&lt;&nbsp;上一页</a>
		  <a href="javaScript:<c:choose><c:when test="${pager.pageNo==(pager.totalPages-1)}">void(0)</c:when><c:otherwise>goPage(${pager.pageNo+1})</c:otherwise></c:choose>">下一页&nbsp;&gt;&gt;</a>
		  <a href="javaScript:<c:choose><c:when test="${pager.pageNo==(pager.totalPages-1)}">void(0)</c:when><c:otherwise>goPage(${pager.totalPages-1 })</c:otherwise></c:choose>">尾页</a>
		  <span>共</span><span><font style="color: #c00">${pager.pageNo+1}</font>/${pager.totalPages}</span><span>页</span>
		  <span>共</span><span><font style="color: #c00">${pager.totalRows}</font></span><span>条</span>
		  <span><input type="text" id="_pageNowNo_" value="${pager.pageNo+1}"/></span>
		  <span>页</span>
		  </td><td><span><input type="button" id="_pageGoNo_" value="确定" /></span>
		</td>
  </tr>
</table>
</c:if>
<script type="text/javascript" >
$("#_pageNowNo_").keydown(function(event){
	if (event.which == 13 && $(this).val() != '') {
		goPage($(this).val() - 1);
	}
});
$("#_pageNowNo_").keyup(function(){
  var value = $(this).val();
  if (value == '') {
  }else if (!(/^\d+$/.test(value))){
	  $(this).val('${pager.pageNo+1}');
  }else if (value < 1){
    $(this).val('1');
  }/* else if (value > ${pager.totalPages}) {
    $(this).val('${pager.totalPages}');
  } */
});
$("#_pageNowNo_").blur(function(){
	if ($(this).val() == '') {
		$(this).val('${pager.pageNo+1}');
	}
});
$("#_pageGoNo_").click(function(){
	var value = $("#_pageNowNo_").val();
  if (value == '' || !(/^\d+$/.test(value))) {
    $("#_pageNowNo_").val('${pager.pageNo+1}');
  }else if (value < 1){
	  $("#_pageNowNo_").val('1');
  }/* else if (value > ${pager.totalPages}}) {
    $("#_pageNowNo_").val('${pager.totalPages}');
  } */
  goPage($("#_pageNowNo_").val() - 1);
});
$(function(){window.top.scrollTo(0,0);});
</script>
