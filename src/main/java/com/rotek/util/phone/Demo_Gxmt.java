package com.rotek.util.phone;
import java.io.UnsupportedEncodingException;


/**
 * 发送个性短信
 * @author acer
 *
 */
public class Demo_Gxmt {
	
	public static void main(String[] args) throws UnsupportedEncodingException {
		//输入软件序列号和密码
		String sn="SDK-WSS-010-01886";
		String pwd="242506";
		Client client=new Client(sn,pwd);		
		//个性短信发送		
		String result_mt = client.gxmt("手机号1,手机号2", "短信内容1,短信内容2", "", "", "");
		//输出返回标识
		System.out.print(result_mt);
	}
}
