/**
* @FileName: SystemIniter.java
* @Package com.rotek.struts.init
* @Description: TODO
* @author chenwenpeng
* @date 2013-5-3 下午03:59:59
* @version V1.0
*/
package com.rotek.platform.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;

import com.rotek.platform.config.SystemGlobals;

/**
 * @ClassName: SystemIniter
 * @Description: 负责系统初始化的监听器
 * @author chenwenpeng
 * @date 2013-5-3 下午03:59:59
 *
 */
public class SystemIniterListener implements ServletContextListener{

	/** (no Javadoc)
	* Title: contextDestroyed
	* Description:
	* @param arg0
	* @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	*/
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	/** (no Javadoc)
	* Title: contextInitialized
	* Description:
	* @param arg0
	* @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	*/
	@Override
	public void contextInitialized(ServletContextEvent event) {

		ServletContext context = event.getServletContext();
		String file_name = context.getInitParameter("com.rotek.platform.config.file");
		if(StringUtils.isNotBlank(file_name)){
			SystemGlobals.loadConfig(file_name.trim());
		}
	}
}
