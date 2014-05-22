/**
 * @FileName: MonitoringAspect.java
 * @Package com.rotek.monitoring
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-8-27 上午11:44:16
 * @version V1.0
 */
package com.rotek.monitoring;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * @ClassName: MonitoringAspect
 * @Description:监听系统重点异常，出现异常后发送邮件或通知
 * @author chenwenpeng
 * @date 2013-8-27 上午11:44:16
 *
 */
public class MonitoringAspect {
	public void doAfter(JoinPoint jp) {
		System.out.println("log Ending method: "
				+ jp.getTarget().getClass().getName() + "."
				+ jp.getSignature().getName());
	}

	public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
		long time = System.currentTimeMillis();
		Object retVal = pjp.proceed();
		time = System.currentTimeMillis() - time;
		System.out.println("process time: " + time + " ms");
		return retVal;
	}

	public void doBefore(JoinPoint jp) {
		System.out.println("log Begining method: "
				+ jp.getTarget().getClass().getName() + "."
				+ jp.getSignature().getName());
	}

	public void doThrowing(JoinPoint jp, Throwable ex) {
		System.out.println("method " + jp.getTarget().getClass().getName()
				+ "." + jp.getSignature().getName() + " throw exception");
		System.out.println(ex.getMessage());
	}

	private void sendEx(String ex) {
		// TODO 发送短信或邮件提醒


	}
}
