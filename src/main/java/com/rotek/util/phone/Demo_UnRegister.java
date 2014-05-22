package com.rotek.util.phone;
import java.io.UnsupportedEncodingException;

/**
 * 注销序列号
 * @author acer
 *
 */
public class Demo_UnRegister {
	public static void main(String[] args) throws UnsupportedEncodingException{
		//输入软件序列号和密码
		String sn="";
		String pwd="";
		Client client=new Client(sn,pwd);
		
		//注销方法
		String result_UnRegisterResult=client.UnRegister();
		
		if(result_UnRegisterResult.startsWith("-"))
		{
			System.out.print("注销失败,返回"+result_UnRegisterResult);
		}else
		{
			System.out.print("注销成功："+result_UnRegisterResult);
		}
	}
}
