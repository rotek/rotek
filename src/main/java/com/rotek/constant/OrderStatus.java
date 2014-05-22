/**
 * Copyright (c) 2013 chenwenpeng
 * All rights reserved.
 * Version V1.0
 */
package com.rotek.constant;

/**
 * @ClassName: OrderDeliverStatus
 * @Description: 订单的配送状态
 * @author chenwenpeng
 * @date 2013-7-12 下午10:02:59
 */
public class OrderStatus {
	//-2(订单通知失败),-1(订单已删除), 1成功下单, 2烹饪中, 3配送中, 4配送完成(订单生效)，5已经评论),6(订单已经生成，但是短信没有发送成功),7(订单时间未到，短信没有发送),8(企业团餐的订单，还没有提交，可以修改),(9企业团餐的订单，已经下发店铺,已经不能修改)
	public static final int FAILD_ADVICE = -2;
	public static final int INVALID = -1;
	public static final int COOKING = 2;
	public static final int DELIVERING = 3;
	public static final int DELIVERED = 4;
	public static final int COMMENT = 5;
	public static final int RESERVE = 7;
	public static final int GROUPRESERVATION_UNSUBMIT = 8;
	public static final int GROUPRESERVATION_SUBMITED = 9;
}
