package com.rotek.util.phone;
import java.io.UnsupportedEncodingException;

/**
 * 短信发送
 * @author acer


 */
public class Demo_Mt {
	public static void main(String[] args) throws UnsupportedEncodingException{
		//输入软件序列号和密码
		String sn="SDK-WSS-010-01886";
		String pwd="242506";
		Client client=new Client(sn,pwd);
		
		//短信发送		
		String result_mt = client.mt("15801377079", "测试@@", "", "", "success");
		if(result_mt.startsWith("-"))//以负号判断是否发送成功
		{
			System.out.print("发送失败！返回值为："+result_mt+"请查看webservice返回值对照表");
			return;
		}
		//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
		System.out.print("发送成功，返回值为："+result_mt);
	}

}
