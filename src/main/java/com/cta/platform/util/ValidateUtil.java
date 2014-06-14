/**
* @FileName: ValidateUtil.java
* @Package com.cta.util
* @Description: TODO
* @author chenwenpeng
* @date 2013-6-6 上午09:05:00
* @version V1.0
*/
package com.cta.platform.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;

import com.cta.platform.constant.RegexType;
import com.cta.platform.persistence.annotation.Column;
import com.cta.platform.persistence.annotation.Length;
import com.cta.platform.persistence.annotation.NotEmpty;
import com.cta.platform.persistence.annotation.NotNull;
import com.cta.platform.persistence.annotation.Size;
import com.cta.platform.persistence.annotation.TelePhone;

/**
 * @ClassName: ValidateUtil
 * @Description: 验证字段数据是否合法的工具类
 * @author chenwenpeng
 * @date 2013-6-6 上午09:05:00
 *
 */
public class ValidateUtil {
	/**
	* @Title: validate
	* @Description: 根据实体类注解验证
	* @param @param <T>
	* @param @param entity
	* @param @return
	* @param @throws IllegalAccessException
	* @param @throws InvocationTargetException
	* @param @throws NoSuchMethodException
	* @return List<String>
	* @throws
	*/
	@SuppressWarnings("unchecked")
	public static <T> List<String> validate(T entity) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		List<String> messages = new LinkedList<String>();
		Class<?> clazz = entity.getClass();
		while (null != clazz && !clazz.getName().equals(Object.class.getName())) {
		    for(Field field : clazz.getDeclaredFields()){
		    	int modifier = field.getModifiers();
		    	if(Modifier.isStatic(modifier) || Modifier.isFinal(modifier)){
		    		continue;
		    	}
		    	Column column = field.getAnnotation(Column.class);
	    		String columnName = column.name();

		    	String message_length = validateLength(field,entity,columnName);
		    	if(null != message_length){
		    		messages.add(message_length);
		    	}

		    	String message_size = validateSize(field,entity,columnName);
		    	if(null != message_size){
		    		messages.add(message_size);
		    	}

		    	String message_null = validateNull(field,entity,columnName);
		    	if(null != message_null){
		    		messages.add(message_null);
		    	}

		    	String message_empty = validateEmpty(field,entity,columnName);
		    	if(null != message_empty){
		    		messages.add(message_empty);
		    	}

		    	String message_telephone = validateTelephone(field, entity, columnName);
		    	if(null != message_telephone){
		    		messages.add(message_telephone);
		    	}
		    }
		    clazz = clazz.getSuperclass();
		}
		return messages;
	}

	/**
	* @Title: validateNull
	* @Description: 验证是否为NULL
	* @param @param <T>
	* @param @param field
	* @param @param entity
	* @param @param columnName
	* @param @return
	* @param @throws IllegalAccessException
	* @param @throws InvocationTargetException
	* @param @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	public static <T> String validateNull(Field field,T entity,String columnName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String message = null;
		if(field.isAnnotationPresent(NotNull.class)){
			NotNull annotation = field.getAnnotation(NotNull.class);
    		String value = BeanUtils.getProperty(entity, columnName);
    		if(null == value){
    			message = annotation.message();
    		}
    	}
		return message;
	}

	/**
	 * @Title: validateEmpty
	 * @Description: 验证是否为空
	 * @param @param <T>
	 * @param @param field
	 * @param @param entity
	 * @param @param columnName
	 * @param @return
	 * @param @throws IllegalAccessException
	 * @param @throws InvocationTargetException
	 * @param @throws NoSuchMethodException
	 * @return String
	 * @throws
	 */
	public static <T> String validateEmpty(Field field,T entity,String columnName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String message = null;
		if(field.isAnnotationPresent(NotEmpty.class)){
			NotEmpty annotation = field.getAnnotation(NotEmpty.class);
			String value = BeanUtils.getProperty(entity, columnName);
			if(StringUtils.isBlank(value)){
				message = annotation.message();
			}
		}
		return message;
	}
	/**
	* @Title: validateSize
	* @Description:验证数值大小
	* @param @param <T>
	* @param @param field
	* @param @param entity
	* @param @param columnName
	* @param @return
	* @param @throws IllegalAccessException
	* @param @throws InvocationTargetException
	* @param @throws NoSuchMethodException
	* @return String
	* @throws
	*/
	public static <T> String validateSize(Field field,T entity,String columnName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException{
		String message = null;
		if(field.isAnnotationPresent(Size.class)){
    		Size annotation = field.getAnnotation(Size.class);
    		int minInt = annotation.minIntSize();
    		int maxInt = annotation.maxIntSize();

    		double minDouble = annotation.minDoubleSize();
    		double maxDouble = annotation.maxDoubleSize();

    		long minLong = annotation.minLongSize();
    		long maxLong = annotation.maxLongSize();

    		String value = BeanUtils.getProperty(entity, columnName);
    		if(minInt != Integer.MIN_VALUE){
    			if(Integer.parseInt(value) < minInt){
    				message = annotation.message();
    				return message;
    			}
    		}
    		if(maxInt != Integer.MAX_VALUE){
    			if(Integer.parseInt(value) > maxInt){
    				message = annotation.message();
    				return message;
    			}
    		}

    		if(minDouble != Double.MIN_VALUE){
    			if(Double.parseDouble(value) < minDouble){
    				message = annotation.message();
    				return message;
    			}
    		}
    		if(maxDouble != Double.MAX_VALUE){
    			if(Double.parseDouble(value) > maxDouble){
    				message = annotation.message();
    				return message;
    			}
    		}

    		if(minLong != Long.MIN_VALUE){
    			if(Long.parseLong(value) < minLong){
    				message = annotation.message();
    				return message;
    			}
    		}
    		if(maxLong != Long.MAX_VALUE){
    			if(Long.parseLong(value) > maxLong){
    				message = annotation.message();
    				return message;
    			}
    		}
    	}
		return message;
	}
	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: validateLength
	* @Description: 验证字符串长度
	* @param
	* @return void
	* @throws
	*/
	private static <T> String validateLength(Field field,T entity,String columnName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String message = null;
		if(field.isAnnotationPresent(Length.class)){
    		Length annotation = field.getAnnotation(Length.class);
    		int min = annotation.minLength();
    		int max = annotation.maxLength();

    		String value = BeanUtils.getProperty(entity, columnName);
    		if(StringUtils.isNotBlank(value)){
    			if(value.length() < min || value.length() > max){
    				message = annotation.message();
    			}
    		//如果选项为空，同时最小长度可以为0，则验证通过
    		}else if(0 == min){

    			message = null;
    		} else {
    			message = annotation.message();
    		}
    	}
		return message;
	}

	/**
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	* @Title: validateTelephone
	* @Description: 验证电话
	* @param @param <T>
	* @param @param field
	* @param @param entity
	* @param @param columnName
	* @param @return
	* @return String
	* @throws
	*/
	private static <T> String validateTelephone (Field field,T entity,String columnName) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
		String message = null;
		if(field.isAnnotationPresent(TelePhone.class)){
			TelePhone annotation = field.getAnnotation(TelePhone.class);
    		String value = BeanUtils.getProperty(entity, columnName);

    		if(ValidateUtil.Validate(value, RegexType.REGEX_PHONE)){
    			return message;
    		}else {
    			message = annotation.message();
    		}
    	}
		return message;
	}

	/**
	* @Title: Validate
	* @Description: 验证值是否符合regex
	* @param @param value
	* @param @param regex
	* @param @return
	* @return boolean
	* @throws
	*/
	public static boolean Validate(String value,String regex){

		Pattern p = Pattern.compile(regex);
		if(StringUtils.isEmpty(value)){
			return false;
		}
		Matcher m = p.matcher(value);
		return m.find();
	}

	public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {
//		RoleEntity role = new RoleEntity();
//		role.setMemo("");
//
//		List<String> messages = ValidateUtil.validate(role);
//		System.out.println(messages);
	}
}
