package com.rotek.util.phone;
import java.io.UnsupportedEncodingException;

/**
 * 注册方法
 * @author acer
 *
 */
public class Demo_Register {

	public static void main(String[] args) throws UnsupportedEncodingException{
		//输入软件序列号和密码
		String sn="SDK-WSS-010-01886";
		String pwd="242506";
		Client client=new Client(sn,pwd);		

		//如为第一次使用，请先注册，注册方法如下，为方便在后来的合作成功客服人员给您提供服务请如实填写下列信息
		//只需注册一次即可
		//返回值说明：注册成功返回0 注册成功
		String result_register = client.register("山东省", "青岛市", "服务业", "青岛酷客外卖有限公司", "陈文鹏", "82363016", "13589353194", "threenoodles@163.com", "88888888", "青岛市 城阳区","266700");
		if(result_register.startsWith("-"))
		{
			if(result_register=="-1 操作失败")
			{
			System.out.print("重复注册");
			return;
			}
			System.out.print("注册失败，返回值为："+result_register+"。请检查输入内容");
		}else {
			System.out.print("恭喜您，注册成功！");
		}
		
	}
}
