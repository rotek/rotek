/**
* @FileName: OrderController.java
* @Package com.rotek.controller
* @Description: TODO
* @author chenwenpeng
* @date 2013-7-26 下午04:35:41
* @version V1.0
*/
package com.rotek.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.format.Colour;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.rotek.constant.DataStatus;
import com.rotek.constant.OrderDeliverType;
import com.rotek.constant.OrderStatus;
import com.rotek.constant.OrderType;
import com.rotek.dto.OrderDto;
import com.rotek.dto.UserDto;
import com.rotek.entity.OrderEntity;
import com.cta.platform.config.SystemGlobals;
import com.cta.platform.util.ListPager;
import com.rotek.service.impl.OrderService;
import com.rotek.util.DateUtils;

/**
 * @ClassName: OrderController
 * @Description: 订单报表
 * @author chenwenpeng
 * @date 2013-7-26 下午04:35:41
 *
 */
@Controller
@RequestMapping("/admin/order")
public class OrderController {

	@Autowired
	private OrderService orderService;
	/**
	* @Title: toOrders
	* @Description:
	* @return
	* @return String
	* @throws
	*/
	@RequestMapping("toOrders")
	public String toOrders(){

		return "admin/order/orders";
	}

	/**
	* @Title: listOrders
	* @Description: 列出所有订单
	* @param start
	* @param limit
	* @param start_time
	* @param end_time
	* @param request
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("listOrders")
	public String listOrders(
		@RequestParam(value = "start", defaultValue = "0") Integer start,
		@RequestParam(value = "limit", defaultValue = "15") Integer limit,

		@RequestParam(value = "id", defaultValue = "") Integer id,
		@RequestParam(value = "rest_name", defaultValue = "") String rest_name,
		@RequestParam(value = "user_name", defaultValue = "") String user_name,
		@RequestParam(value = "phone", defaultValue = "") String phone,
		@RequestParam(value = "building_id", defaultValue = "") Integer building_id,
		@RequestParam(value = "type", defaultValue = "") Integer type,
		@RequestParam(value = "status", defaultValue = "") Integer status,

		@RequestParam(value = "start_time", defaultValue = "") Date start_time,
		@RequestParam(value = "end_time", defaultValue = "") Date end_time,

		HttpServletRequest request,
		ModelMap modelMap,UserDto user) throws SQLException{

		ListPager pager = new ListPager();
		Integer pageNo = (start / limit);
		pager.setRowsPerPage(limit);
		pager.setPageNo(pageNo);

		OrderDto order = new OrderDto();
		order.setId(id);
		order.setRest_name(rest_name);
		order.setUser_name(user_name);
		order.setPhone(phone);
		order.setBuilding_id(building_id);
		order.setType(type);
		order.setStatus(status);

		List<OrderDto> orders = orderService.listOrders(user,order,pager,start_time,end_time);
		modelMap.put("dataList", orders);
		modelMap.put("totalCount", pager.getTotalRows());
		return "jsonView";
	}


	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: addOrder
	* @Description: 添加订单
	* @param address
	* @param building_id
	* @param carriage
	* @param menus
	* @param ordered_time
	* @param phone
	* @param price_spread
	* @param price_total
	* @param rest_id
	* @param request
	* @param modelMap
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("addOrder")
	public String addOrder(
			@RequestParam(value = "address", defaultValue = "") String address,
			@RequestParam(value = "building_id", defaultValue = "") Integer building_id,
			@RequestParam(value = "carriage", defaultValue = "") Double carriage,
			@RequestParam(value = "menus", defaultValue = "") String menus,
			@RequestParam(value = "ordered_time", defaultValue = "") Date ordered_time,
			@RequestParam(value = "phone", defaultValue = "") String phone,
			@RequestParam(value = "price_total", defaultValue = "0") Double price_total,
			@RequestParam(value = "rest_id", defaultValue = "") Integer rest_id,

			HttpServletRequest request,
			ModelMap modelMap) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException{

		OrderEntity order = new OrderEntity();
		order.setAddress(address);
		order.setBuilding_id(building_id);
		order.setCarriage(carriage);
		order.setOrdered_time(ordered_time);
		order.setPhone(StringUtils.isBlank(phone)?SystemGlobals.getPreference("default.deliverer.telephone") : phone);
		order.setRemark(DataStatus.NONE_STRING);
		order.setRest_id(rest_id);
		order.setStatus(OrderStatus.DELIVERED);
		order.setTotalPrice(price_total);
		order.setType(OrderType.ORDER_ADD);
		order.setUser_id(DataStatus.NONE_INT);


		List<String> messages = orderService.addOrder(order,menus,price_total);
		modelMap.put("success", null == messages ? true : false);
		modelMap.put("messages", messages);
		return "jsonView";
	}


	/**
	* @Title: modifyOrderStatus
	* @Description:
	* @param ids
	* @param status
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("modifyOrderStatus")
	public String modifyOrderStatus(
			@RequestParam(value="ids", defaultValue="") String ids,
			@RequestParam(value="status", defaultValue="") Integer status,
			ModelMap model) throws SQLException{

		List<String> messages = orderService.modifyOrderStatus(ids,status);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: deleteOrder
	* @Description: 删除订单
	* @param ids
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("deleteOrder")
	public String deleteOrder(
			@RequestParam(value="ids", defaultValue="") String ids,
			ModelMap model) throws SQLException{

		List<String> messages = orderService.deleteOrder(ids);
		model.put("success", null == messages ? true : false);
		model.put("messages", messages);
		return "jsonView";
	}

	/**
	* @Title: getOrderDetail
	* @Description:获取订单的详情
	* @param id
	* @param model
	* @return
	* @throws SQLException
	* @return String
	* @throws
	*/
	@RequestMapping("getOrderDetail")
	public String getOrderDetail(
			@RequestParam(value="id", defaultValue="") Integer id,
			ModelMap model) throws SQLException{

		OrderDto order = orderService.getOrderDetail(id);
		model.put("data", order);
		return "jsonView";
	}

	/**
	 * @throws WriteException
	 * @throws RowsExceededException
	 * @throws IOException
	* @Title: exportOrders
	* @Description: 导出订单
	* @param id
	* @param rest_name
	* @param user_name
	* @param phone
	* @param building_id
	* @param type
	* @param status
	* @param start_time
	* @param end_time
	* @param request
	* @param modelMap
	* @param user
	* @throws SQLException
	* @return void
	* @throws
	*/
	@RequestMapping("exportOrders")
	public void exportOrders(
			@RequestParam(value = "ids", defaultValue = "") String ids,
			@RequestParam(value = "id", defaultValue = "") Integer id,
			@RequestParam(value = "rest_name", defaultValue = "") String rest_name,
			@RequestParam(value = "user_name", defaultValue = "") String user_name,
			@RequestParam(value = "phone", defaultValue = "") String phone,
			@RequestParam(value = "building_id", defaultValue = "") Integer building_id,
			@RequestParam(value = "type", defaultValue = "") Integer type,
			@RequestParam(value = "status", defaultValue = "") Integer status,

			@RequestParam(value = "start_time", defaultValue = "") Date start_time,
			@RequestParam(value = "end_time", defaultValue = "") Date end_time,

			HttpServletRequest request,
			HttpServletResponse response,
			ModelMap modelMap,UserDto user) throws SQLException, IOException, RowsExceededException, WriteException{

			ListPager pager = new ListPager();
			pager.setPageNo(0);
			pager.setRowsPerPage(10000);

			OrderDto order = new OrderDto();
			order.setId(id);
			order.setRest_name(rest_name);
			order.setUser_name(user_name);
			order.setPhone(phone);
			order.setBuilding_id(building_id);
			order.setType(type);
			order.setStatus(status);

			List<OrderDto> orders = orderService.exportOrders(user,order,pager,start_time,end_time,ids);

			response.reset();//重置response
		    response.setContentType("application/vnd.ms-excel");// 定义输出类型，
		    response.addHeader("Content-Disposition", "attachment;filename=dingdan-"+DateUtils.formatDateTime(new Date())+".xls");

		    java.io.OutputStream os = response.getOutputStream();
		    WritableWorkbook wwb = Workbook.createWorkbook(os);//此处建立路径
		    WritableSheet ws = wwb.createSheet("订单统计", 0);//建立工作簿

		    int charTitle = 11;// 标题字体大小
		    int charNormal = 10;// 标题字体大小

		    // 用于标题
		    jxl.write.WritableFont titleFont = new jxl.write.WritableFont(WritableFont.createFont("宋体"), charTitle, WritableFont.BOLD);
		    jxl.write.WritableCellFormat titleFormat = new jxl.write.WritableCellFormat(titleFont);
		    titleFormat.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		    titleFormat.setVerticalAlignment(VerticalAlignment.CENTRE); // 垂直对齐
		    titleFormat.setAlignment(Alignment.CENTRE); // 水平对齐
		    titleFormat.setWrap(true); // 是否换行
		    titleFormat.setBackground(Colour.GRAY_25);// 背景色暗灰-25%
		    // 用于正文
		    WritableFont normalFont = new WritableFont(WritableFont.createFont("宋体"), charNormal);
		    jxl.write.WritableCellFormat normalFormat = new jxl.write.WritableCellFormat(normalFont);
		    normalFormat.setBorder(Border.ALL, BorderLineStyle.THIN); // 线条
		    normalFormat.setVerticalAlignment(VerticalAlignment.TOP); // 垂直对齐
		    normalFormat.setAlignment(Alignment.CENTRE);// 水平对齐
		    normalFormat.setWrap(true); // 是否换行

			ws.addCell(new Label(0, 0, "订单所属店铺",titleFormat));
			ws.setColumnView(0, 25);
			ws.addCell(new Label(1, 0, "店铺电话",titleFormat));
			ws.setColumnView(1, 25);
			ws.addCell(new Label(2, 0, "下单用户",titleFormat));
			ws.setColumnView(2, 25);
			ws.addCell(new Label(3, 0, "订单价格(不含送餐费)",titleFormat));
			ws.setColumnView(3, 25);
			ws.addCell(new Label(4, 0, "送餐费",titleFormat));
			ws.setColumnView(4, 25);
			ws.addCell(new Label(5, 0, "收餐地址",titleFormat));
			ws.setColumnView(5, 25);
			ws.addCell(new Label(6, 0, "订单备注",titleFormat));
			ws.setColumnView(6, 25);
			ws.addCell(new Label(7, 0, "收餐电话",titleFormat));
			ws.setColumnView(7, 25);
			ws.addCell(new Label(8, 0, "配送员",titleFormat));
			ws.setColumnView(8, 25);
			ws.addCell(new Label(9, 0, "配送员电话",titleFormat));
			ws.setColumnView(9, 25);
			ws.addCell(new Label(10, 0, "菜单详情",titleFormat));
			ws.setColumnView(10, 25);
			ws.addCell(new Label(11, 0, "配送方",titleFormat));
			ws.setColumnView(11, 25);
			ws.addCell(new Label(12, 0, "下单时间",titleFormat));
			ws.setColumnView(12, 25);
			ws.addCell(new Label(13, 0, "订单状态",titleFormat));
			ws.setColumnView(13, 25);

			for(int i = 0;i<orders.size();i++){
				OrderDto order_ = orders.get(i);
				ws.addCell(new Label(0,i+1,order_.getRest_name(),normalFormat));
				ws.addCell(new Label(1,i+1,order_.getRest_phone(),normalFormat));
				ws.addCell(new Label(2,i+1,order_.getUser_name(),normalFormat));
				ws.addCell(new Label(3,i+1,order_.getTotalPrice()+"",normalFormat));
				ws.addCell(new Label(4,i+1,order_.getCarriage()+"",normalFormat));
				ws.addCell(new Label(5,i+1,order_.getAddress(),normalFormat));
				ws.addCell(new Label(6,i+1,order_.getRemark(),normalFormat));
				ws.addCell(new Label(7,i+1,order_.getPhone(),normalFormat));
				ws.addCell(new Label(8,i+1,order_.getDeliverer_name(),normalFormat));
				ws.addCell(new Label(9,i+1,order_.getDeliverer_phone(),normalFormat));

				StringBuilder menu_str = new StringBuilder();
				for(Map<String,Object> menu : order_.getMenus()){
					Double price = (Double)(null == menu.get("price") ? 0 : menu.get("price"));
					Integer count = (Integer)(null ==menu.get("count") ? 1 : menu.get("count"));
					menu_str.append(menu.get("name")).append("*").append(menu.get("count")).append("=").append(price * count);
					ws.addCell(new Label(10,i+1,menu_str.toString(),normalFormat));
				}
				ws.addCell(new Label(11,i+1,this.getDeliverType(order_.getDeli_type()),normalFormat));
				ws.addCell(new Label(12,i+1,DateUtils.formatDateTime(order_.getOrdered_time()),normalFormat));
				ws.addCell(new Label(13,i+1,this.getOrderStatus(order_.getStatus()),normalFormat));
			}

			wwb.write(); //写入Exel工作表
			wwb.close();//关闭Excel工作薄对象
		}

	/**
	* @Title: getOrderStatus
	* @Description:
	* @param s
	* @return
	* @return String
	* @throws
	*/
	private String getOrderStatus(Integer s){
		String status = "未知状态";
		if(null == s){
			return status;
		}
		if(OrderStatus.COOKING == s){
			status = "烹饪中";
		}else if(OrderStatus.DELIVERING == s){
			status = "配送中";
		}else if(OrderStatus.DELIVERED == s){
			status = "已配送";
		}else if(OrderStatus.COMMENT == s){
			status = "已评价";
		}else if(OrderStatus.FAILD_ADVICE == s){
			status = "订单短信通知失败";
		}else if(OrderStatus.INVALID == s){
			status = "订单已删除";
		}else if(OrderStatus.RESERVE == s){
			status = "预定订单";
		}else {
			status = "未知状态";
		}
		return status;
	}

	/**
	* @Title: getDeliverType
	* @Description: 由谁配送
	* @param deliverType
	* @return
	* @return String
	* @throws
	*/
	private String getDeliverType(Integer deliverType){
		String status = "未设置";
		if(null ==deliverType){
			return status;
		}else if(deliverType == OrderDeliverType.ORDER_DELIVER_OUR){
			status = "我们配送";
		}else if(deliverType == OrderDeliverType.ORDER_DELIVER_RESTAURANT){
			status = "店铺配送";
		}
		return status;
	}

	@InitBinder
    protected void initBinder(HttpServletRequest request,
    	    ServletRequestDataBinder binder) throws Exception {
    	    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
    	    CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
    	    binder.registerCustomEditor(Date.class, dateEditor);
    }
}
