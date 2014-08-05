/**
 * Copyright (c ) 2014 cqa
 *
 * All rights reserved.
 *
 */
package com.cta.utils;

import java.util.UUID;

import org.apache.commons.lang.RandomStringUtils;

/**
 * <p>随机数工具类, null线程安全</p>
 * <pre>
 * <b>randomNumeric</b> 指定长度的随机数字
 * <b>randomAlphaNum</b> 指定长度的随机英数字(大写字母、小写字母和数字)
 * <b>randomUppAlphaNum</b> 指定长度的随机英数字(大写字母和数字)
 * <b>randomLowAlphaNum</b> 指定长度的随机英数字(小写字母和数字)
 * <b>randomUUID</b> UUID随机数(小写字母，数字及中横线'-')。固定长度36位。
 * </pre>
 */
public class RandomUtil {

    /** 大写字母和数字 */
    private static final char[] CHAR_UPP = new char[]{
            48,49,50,51,52,53,54,55,56,57,
            65,66,67,68,69,70,71,72,73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90
        };
    /** 小写字母和数字 */
    private static final char[] CHAR_LOW = new char[]{
            48,49,50,51,52,53,54,55,56,57,
            97,98,99,100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,115,116,117,118,119,120,121,122
        };
    
    /**
     * <p>指定长度的随机数字</p>
     */
    public static String randomNum(final int length) {
        if (length < 1) return "";
        return RandomStringUtils.randomNumeric(length);
    }
    
    /**
     * <p>指定长度的随机英数字(大写字母、小写字母和数字)</p>
     */
    public static String randomAlphaNum(final int length) {
        if (length < 1) return "";
        return RandomStringUtils.randomAlphanumeric(length);
    }
    
    /**
     * <p>指定长度的随机英数字(大写字母和数字)</p>
     */
    public static String randomUppAlphaNum(final int length) {
        if (length < 1) return "";
        return RandomStringUtils.random(length, 0, 0, false, false, CHAR_UPP);
    }
    
    /**
     * <p>指定长度的随机英数字(小写字母和数字)</p>
     */
    public static String randomLowAlphaNum(final int length) {
        if (length < 1) return "";
        return RandomStringUtils.random(length, 0, 0, false, false, CHAR_LOW);
    }
    
    /**
     * <p>UUID随机数(小写字母，数字及中横线'-')。</p>
     * <pre>
     * 固定长度：36位
     * 固定格式：AAAAAAAA-BBBB-CCCC-DDDD-EEEEEEEEEEEE
     * </pre>
     */
    public static String randomUUID() {
        return UUID.randomUUID().toString();
    }
    
}
