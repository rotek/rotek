/**
* @FileName: ExceptionResolver.java
* @Package com.cta.platform.resolver
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-4 下午03:23:19
* @version V1.0
*/
package com.cta.platform.resolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

/**
 * @ClassName: ExceptionResolver
 * @Description: 异常处理
 * @author chenwenpeng
 * @date 2013-6-4 下午03:23:19
 *
 */
public class ExceptionResolver extends SimpleMappingExceptionResolver{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ExceptionResolver.class);
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.error("统一出错处理", ex);
		if (isJsonRequest(request)) {
			//ModelAndView ret = new ModelAndView("jsonView");
			//ret.addObject("fail", ex.getMessage());
			//return null;

			ModelAndView ret = new ModelAndView("jsonView");
			ret.addObject("failed", ex.getMessage());
			return ret;
		}else{
			ModelAndView ret = new ModelAndView("/error/500");
			return ret;
		}
	}

	/**
	* @Title: isJsonRequest
	* @Description:
	* @param @param request
	* @param @return
	* @return boolean
	* @throws
	*/
	private boolean isJsonRequest(HttpServletRequest request) {
		String type = request.getHeader("X-Requested-With");
		if(!StringUtils.isEmpty(type)){
			return true;
		}
		return false;
	}
}
