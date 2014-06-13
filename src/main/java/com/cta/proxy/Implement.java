/**
 * 
 */
package com.cta.proxy;

import jxl.common.Logger;

/**
 * @author chenwenpeng
 *
 */
public class Implement implements Interface{

	Logger logger = Logger.getLogger(Implement.class);
	
	public Implement(){
		logger.info("Implement 的无参数构造器被调用");
	}
	public Implement(int a){
		logger.info("Implement 有参构造器被调用");
	}
	@Override
	public void sayHellow() {
		// TODO Auto-generated method stub
		
		logger.info("hellow world,implement !");
	}
}
