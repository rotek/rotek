/**
 * 
 */
package com.cta.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author chenwenpeng
 *
 */
public class DebugProxy implements InvocationHandler{

	private Object obj;
	
	public void setObj(Object obj){
		this.obj = obj;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		
		method.invoke(obj, args);
		return null;
	}
}
