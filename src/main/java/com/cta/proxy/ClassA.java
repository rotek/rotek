/**
 * 
 */
package com.cta.proxy;

/**
 * @author chenwenpeng
 * 
 */
public class ClassA {

	public Integer pubAttr = 10;

	private Integer priAttr = 1;

	public void methodA(Integer arg) {

		this.priAttr = arg;
		System.out.println("METHOD A priAttr: " + this.priAttr);
	}

	public void methodB() {

		System.out.println("METHOD B priAttr: " + this.priAttr);
	}

	private void methodC() {

		System.out.println("METHOD C priAttr: " + this.priAttr);
	}

}
