/**
 * 
 */
package com.cta.proxy;

import java.lang.reflect.InvocationTargetException;

/**
 * @author chenwenpeng
 * 
 */
public class Reflect {

	public static void main(String[] args) throws ClassNotFoundException,
			NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException,
			InstantiationException {

//		Interface target = new Implement();
		
		Class<Implement> impClass = (Class<Implement>) Class.forName("com.cta.proxy.Implement");
		impClass.newInstance();
		
//		DebugProxy proxy = new DebugProxy();
//		proxy.setObj(Implement.class.newInstance());
//		Interface dog = (Interface) Proxy.newProxyInstance(
//				Implement.class.getClassLoader(),
//				Implement.class.getInterfaces(), proxy);
//		dog.sayHellow();
		
		
		System.out.println(new Implement());
		System.out.println(Implement.class.newInstance());
	}
}
