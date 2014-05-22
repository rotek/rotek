/**
* @FileName: PollingController.java
* @Package com.rotek.controller.android
* @Description: TODO
* @author chenwenpeng
* @date 2013-9-2 上午08:49:00
* @version V1.0
*/
package com.rotek.controller.android;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.rotek.constant.AndroidStatus;
import com.rotek.service.impl.android.PollingService;
import com.rotek.util.DateUtils;

/**
 * @ClassName: PollingController
 * @Description:
 * @author chenwenpeng
 * @date 2013-9-2 上午08:49:00
 *
 */
@Controller
@RequestMapping("")
public class PollingController {

	@Autowired
	private PollingService pollingService;


	/**判断配送员用户是否能登陆
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/login/login_deliver")
	public @ResponseBody Map<String,Object> login_deliver(ModelMap model,HttpServletResponse response,
			@RequestParam(value = "userName",defaultValue="") String username,
			@RequestParam(value = "password",defaultValue="") String password) throws Exception {
		//判断用户是否可以登录
		Map<String,Object> result = pollingService.login_deliver(username,password);
		return result;
	}


	/**
	 * @throws SQLException
	* @Title: listNewOrders
	* @Description:
	* @param model
	* @param time_last
	* @param deliver_id
	* @param start
	* @return
	* @return String
	* @throws
	*/
	@RequestMapping("/polling/listNewOrders")
	public String listNewOrders(ModelMap model,
			@RequestParam(value = "time_last", defaultValue = "") Date time_last,
			@RequestParam(value = "deliver_id", defaultValue = "") Integer deliver_id) throws SQLException {

		if(null == deliver_id){
			model.put("status", AndroidStatus.NOLOGIN);
		}else {
			//返回用户最后一个订单的时间
			List<Map<String,Object>> orderList = pollingService.listNewOrders(time_last,deliver_id);
			model.put("status", AndroidStatus.SUCCESS);
			model.put("time_last", DateUtils.formatDateTime());
			model.put("order_list", orderList);
		}
		return "jsonView";
	}

	/**设置用户的订单为已经配送
	 * @param order_id
	 * @throws SQLException
	 * @throws IOException
	 */
	@RequestMapping("/polling/setOrderDelivered")
	public void setOrderDelivered(HttpServletResponse response,Integer order_id) throws SQLException, IOException {

		String result = pollingService.setOrderDelivered(order_id);
		response.setStatus(200);
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(result);
	}

	@InitBinder
    protected void initBinder(HttpServletRequest request,
    	    ServletRequestDataBinder binder) throws Exception {
    	    DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	    CustomDateEditor dateEditor = new CustomDateEditor(fmt, true);
    	    binder.registerCustomEditor(Date.class, dateEditor);
    }
}
