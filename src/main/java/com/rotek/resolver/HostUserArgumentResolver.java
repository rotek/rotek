/**
 * @FileName: HostUserArgumentResolver.java
 * @Package com.cta.platform.resolver
 * @Description: TODO
 * @author chenwenpeng
 * @date 2013-5-6 下午02:07:45
 * @version V1.0
 */
package com.rotek.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;

import com.rotek.constant.SessionParams;
import com.rotek.dto.UserDto;

/**
 * @ClassName: HostUserArgumentResolver
 * @Description: 自定义参数 如：UserEntity user
 * @author chenwenpeng
 * @date 2013-5-6 下午02:07:45
 *
 */
public class HostUserArgumentResolver implements WebArgumentResolver {
	/**
	 * (no Javadoc) Title: resolveArgument Description:
	 *
	 * @param methodParameter
	 * @param webRequest
	 * @return
	 * @throws Exception
	 * @see org.springframework.web.bind.support.WebArgumentResolver#resolveArgument(org.springframework.core.MethodParameter,
	 *      org.springframework.web.context.request.NativeWebRequest)
	 */
	@Override
	public Object resolveArgument(MethodParameter methodParameter,
			NativeWebRequest webRequest) throws Exception {
		if (methodParameter.getParameterType().equals(UserDto.class)
				&& methodParameter.getParameterName().equals(SessionParams.USER)) {
			UserDto member = (UserDto) webRequest.getAttribute(
					"user", RequestAttributes.SCOPE_SESSION);
			if (member != null) {
				return member;
			} else {
				return UNRESOLVED;
			}
		}
		return UNRESOLVED;
	}

}
