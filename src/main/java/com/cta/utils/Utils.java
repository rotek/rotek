/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.cta.utils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;

/**
 * @ClassName: Utils
 * @Description: 系统工具类
 * @author chenwenpeng
 * @date 2013-6-7 下午10:55:22
 */
public class Utils {

	/**
	 * 除法，取scale精度的结果，如果取到整数，那么就四舍五入
	 * 
	 * @param dividend
	 * @param divisor
	 * @param scale
	 * @return
	 */
	public static double divide(double dividend, double divisor, int scale) {
		if (scale < 0) {
			throw new IllegalArgumentException(
					"The scale must be a positive integer or zero");
		}
		BigDecimal b1 = new BigDecimal(Double.toString(dividend));
		BigDecimal b2 = new BigDecimal(Double.toString(divisor));
		return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/**
	 * @Title: getRandomCode
	 * @Description: 获取num个随机数
	 * @param @param num
	 * @param @return
	 * @return String
	 * @throws
	 */
	public static String getRandomCode(Integer num) {
		StringBuilder number = new StringBuilder();
		Random random = new Random();
		for (int i = 0; i < num; i++) {
			number.append(random.nextInt(10));
		}
		return number.toString();
	}
	
	/**
     * <p>设置session</p>
     * 
     * @param request http请求
     * @param name session名字
     * @param value session值
     */
    public static void setSession(HttpServletRequest request, final String name, final Object value) {
        if (StringUtils.isEmpty(name)) return;
        if (value != null) {
            request.getSession().setAttribute(name, value);
        }else{
            request.getSession().removeAttribute(name);
        }
    }
	
    /**
     * <p>取得session</p>
     * 
     * @param request http请求
     * @param name session名字
     * @return session值
     */
    @SuppressWarnings("unchecked")
    public static <T> T getSession(HttpServletRequest request, final String name) {
        return (T) request.getSession().getAttribute(name);
    }
	

	public static void main(String[] args) throws InstantiationException,
			IllegalAccessException, ClassNotFoundException, ParseException {

		// CacheEngine cacheEngine = (CacheEngine)
		// Class.forName("com.cta.platform.cache.MemcacheEngine").newInstance();
		// System.out.println(cacheEngine);
		// System.out.println(Utils.getRandomCode(4));

		//
		// String time = "23:36:22";
		// String begin = "18:50:00";
		// Date date = new Date();
		// SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		// System.out.println(date);
		// System.out.println(sdf.parse(time).getTime() <
		// sdf.parse(begin).getTime());

		int score = (int) Utils.divide(5, 2, 0);
		System.out.println(score);

	}
}
