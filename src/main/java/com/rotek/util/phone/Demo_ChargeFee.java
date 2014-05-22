package com.rotek.util.phone;
import java.io.UnsupportedEncodingException;
/**
 * 充值方法
 * @author acer
 *
 */

public class Demo_ChargeFee {
	public static void main()throws UnsupportedEncodingException{
		//输入您的软件序列号和密码
		String sn="SDK-WSS-010-01886";
		String pwd="242506";

		Client client=new Client(sn,pwd);
		
		//充值，参数依次为充值卡号和密码
		String result_charge = client.chargeFee("SDK-WSS-010-01624", "107144");
		if (result_charge.startsWith("-")) {
		}else {
			System.out.print("充值成功,最新余额为：" + client.getBalance() + "条");
		}
	}
	
}
