/*******************************************************************************
 * Copyright (c) 2011 by lasun Corporation all right reserved.
 * 2011-10-20 
 * 
 *******************************************************************************/
package com.lansun.mobile.assistant.constant;

/**
 * <pre>
 * 业务名:
 * 功能说明: 
 * 编写日期:	2011-10-20
 * 作者:	 刘斌
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
public class ValidateConstant {
	/**
	 * 最底级别验证，仅要求非空
	 */
	public static final int VALIDATELEVEL_BLAND = 0x1;

	/**
	 * 要求符合给定的正则表达式
	 */
	public static final int VALIDATELEVEL_REGEX = 0x2;

	/**
	 * 验证Email的正确性
	 */

	public static final String VALIDATE_EMAIL = "^\\w+((-\\w+)|(\\.\\w+))*\\@[0-9a-zA-Z]+((-|\\.)[0-9a-zA-Z]+)*\\.[0-9a-zA-Z]+$";

	/**
	 * 非空验证
	 */
	public static final String VALIDATE_BLANK = "BLANK";

	/**
	 * 身份证验证
	 */
	public static final String VALIDATE_IDCARD = "^\\d{15}|(\\d{17}(\\d|X|x))$";

	/**
	 * 固定电话
	 */
	public static final String VALIDATE_PHONE = "^(d+-)?(d{4}-?d{7}|d{3}-?d{8}|^d{7,8})(-d+)?$";

	/**
	 * 移动电话
	 */
	public static final String VALIDATE_MOBILEPHONE = "^(14|13|15|18)\\d{9}$";

	/**
	 * 邮政编码
	 */
	public static final String VALIDATE_POSTCODE = "^[1-9]\\d{5}$";

	/**
	 * 数字验证
	 */
	public static final String VALIDATE_NUMBER = "^\\d+$";

}
