/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.rotek.util;

import java.io.UnsupportedEncodingException;

import org.apache.log4j.Logger;

import com.cta.platform.config.SystemGlobals;
import com.rotek.util.phone.Client;

/**
 * @ClassName: OrderUtils
 * @Description: 订单的工具类
 * @author chenwenpeng
 * @date 2013-7-9 下午11:42:08
 */
public class ShortMessageUtils {
	/** The logger. */
    private static Logger logger = Logger.getLogger(ShortMessageUtils.class);
	/**
	 * 给摸个电话发送验证码，返回发送的验证码
	 * @param phone
	 * @param session
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public static boolean sendMessage(String phone,String message) throws UnsupportedEncodingException {
		
		String sn=SystemGlobals.getPreference("sms.name", "");
		String pwd=SystemGlobals.getPreference("sms.password", "");
		//短信发送
		Client client = new Client(sn,pwd);
		String result_mt = client.mt(phone, message, "", "", "success");
		if(result_mt.startsWith("-")){
			//以负号判断是否发送成功
			System.out.print("发送失败！返回值为："+result_mt+"请查看webservice返回值对照表");
			return false;
		}else{
			//输出返回标识，为小于19位的正数，String类型的。记录您发送的批次。
			System.out.print("发送成功，返回值为："+result_mt);
			return true;
		}
	}
}
