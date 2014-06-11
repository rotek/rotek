package com.cta.platform.config;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.xml.DOMConfigurator;
/**用于加载框架的配置信息，初始化框架的参数，只在框架内部使用！
 * @author 陈文鹏
 *2013-3-23下午7:47:23
 */
public class ConfigInitializer implements ServletContextListener{
	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextInitialized(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		ServletContext context = event.getServletContext();
		String configFile = context.getInitParameter("com.cta.platform.config.file");
		String lo4jFile = context.getInitParameter("com.cta.platform.lo4j.file");

		if(StringUtils.isNotEmpty(configFile)){
			SystemGlobals.loadConfig(configFile);
			System.out.println("---------->site_system_init success!");
		}
		if(StringUtils.isNotEmpty(lo4jFile)){
			String prefix = context.getRealPath("/");
			 DOMConfigurator.configure(prefix+lo4jFile);//log4j.xml用DOMConfigurator初始化
	         System.out.println("---------->site_log_4j_init success");
		}
	}


	/* (non-Javadoc)
	 * @see javax.servlet.ServletContextListener#contextDestroyed(javax.servlet.ServletContextEvent)
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {

	}
}
