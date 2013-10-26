package com.lasun.mobile.assistant.utils;

/**
 * <pre>
 * 业务名:
 * 功能说明: 
 * 编写日期:	2011-10-11
 * 作者:	 李勇
 * 
 * 历史记录
 * 1、修改日期：
 *    修改人：
 *    修改内容：
 * </pre>
 */
import java.util.regex.Pattern;

import android.widget.EditText;

import com.lansun.mobile.assistant.constant.ValidateConstant;

public class ValidateUtil {

	private boolean isRequestFocus; 

	public ValidateUtil() {
		isRequestFocus = true;
	}

	 
	public static boolean validateFillOrderUsername(String mUsername) {
		String fUsername = "[0-9]*";
		boolean result = Pattern.matches(fUsername, mUsername.trim());
		return result;
	}

	 
	public static boolean validateSpecialCharacter(String mInput) {
		String mp = "^[_\nA-Za-z0-9\u4e00-\u9fa5]+$";
		boolean result = Pattern.matches(mp, mInput.trim());
		return result;
	}

	 
	public static boolean validateNumber(String mNumber) {
		String pMobileNumber = "^[^%]{6,20}$";
		boolean result = Pattern.matches(pMobileNumber, mNumber);
		return result;
	}

	 
	public static boolean validateEmail(String email) {
		String pEmail = "^\\w+((-\\w+)|(\\.\\w+))*\\@[A-Za-z0-9]+((\\.|-)[A-Za-z0-9]+)*\\.[A-Za-z0-9]+$";
		boolean result = Pattern.matches(pEmail, email);
		return result;
	}

	 
	public static boolean validateIDNumber(String idNumber) {
		String pIdNumber = "(^\\d{15}$)|(^\\d{17}([0-9]|X|x)$)";
		boolean result = Pattern.matches(pIdNumber, idNumber);
		return result;
	}

	 
	public static boolean validatePhoneNumber(String phoneNumber) {
		String pPhoneNumber = "^\\d{3,4}-\\d{7,9}$";
		boolean result = Pattern.matches(pPhoneNumber, phoneNumber);
		return result;
	}

	 
	public static boolean validateMobileNumber(String mobileNumber) {
		String pMobileNumber = "^(14|13|15|18)[0-9]{9}$";
		boolean result = Pattern.matches(pMobileNumber, mobileNumber);
		return result;
	}

	 
	public static boolean validateZipCode(String zipCode) {
		String pZipCode = "^[0-9]{6}$";
		boolean result = Pattern.matches(pZipCode, zipCode);
		return result;
	}

	public static final String VALIDATE_URL = "^(http|https|ftp)://[a-zA-Z0-9-.]+.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?[a-zA-Z0-9-._?,\'/\\+&amp;%\\$\\#\\=~]*$";

	 
	public static boolean validator(String input, String regularExpression) {
		if (ValidateConstant.VALIDATE_BLANK.equals(regularExpression)) {
			return !"".equals(input);
		}
		return Pattern.matches(regularExpression, input);
	}

	public boolean validateEditText(EditText editText) {
		return validateEditText(editText, "缺少必要信息");
	}

	public boolean validateEditText(EditText editText, String error) {
		return validateEditText(editText, error,
				ValidateConstant.VALIDATE_BLANK);
	}

	public boolean validateEditText(EditText editText, String error,
			String regularExpression) {
		if (!validator(editText.getText().toString().trim(), regularExpression)) {
			editText.setError(error);
			if (isRequestFocus) {
				editText.requestFocus();
			}
			return false;
		} else {
			return true;
		}
	}
}
